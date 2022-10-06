from copyreg import pickle
from contextlib import redirect_stderr
from sklearn.decomposition import TruncatedSVD
from scipy.sparse.linalg import svds

import matplotlib.pyplot as plt
import seaborn as sns
import pandas as pd
import numpy as np
import MySQLdb
import warnings
import requests
import pickle
from ast import literal_eval
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.feature_extraction.text import CountVectorizer
from sklearn.metrics.pairwise import cosine_similarity
warnings.filterwarnings("ignore")

# 1 DB 커넥션 따로
CONN = MySQLdb.connect(
    host = 'j7b309.p.ssafy.io',
    user = 'B309',
    password = 'B309Billow',
    db = 'billow'
)

# CONN = MySQLdb.connect(
#     host = 'localhost',
#     user = 'B309',
#     password = 'B309Billow',
#     db = 'billow'
# )

def query_MySQL(query):
    # 2. 쿼리 로그 찍기
    print(query)

    global query_result
    query_result = pd.read_sql(query, CONN)
    # 3. result 로그 찍기
    print(query_result)

    return query_result
    
def predict_table():
    df_program = query_MySQL('SELECT program_id, title from tb_program')
    df_rating = query_MySQL('SELECT score, program_id, user_id from tb_rating')
    user_list = query_MySQL('SELECT * From tb_user')

    user_list = user_list.values.tolist()
    users = []
    for user in user_list:
        users.append(user[0])

    df_user_program_ratings = df_rating.pivot(
        index = 'user_id',
        columns = 'program_id',
        values='score').fillna(0)

    matrix = df_user_program_ratings.to_numpy()
    # matrix = df_user_program_ratings.as_matrix()


    user_ratings_mean = np.mean(matrix, axis = 1)

    matrix_user_mean = matrix - user_ratings_mean.reshape(-1, 1)

    U, sigma, Vt = svds(matrix_user_mean, k = 12)

    sigma = np.diag(sigma)

    svd_user_predicted_ratings = np.dot(np.dot(U, sigma), Vt) + user_ratings_mean.reshape(-1, 1)

    df_svd_preds = pd.DataFrame(svd_user_predicted_ratings, columns = df_user_program_ratings.columns)
    # df_svd_preds.head()

    return users, df_program, df_rating, df_svd_preds

def recommend_programs(df_svd_preds, user_id, ori_programs_df, ori_ratings_df, num_recommendations=10):

    #현재는 index로 적용이 되어있으므로 user_id - 1을 해야함.

    # 최종적으로 만든 pred_df에서 사용자 index에 따라 프로그램 데이터 정렬 -> 프로그램 평점이 높은 순으로 정렬 됌
    sorted_user_predictions = df_svd_preds.df["user_id"].sort_values(ascending=False)

    # 원본 평점 데이터에서 user id에 해당하는 데이터를 뽑아낸다. 
    user_data = ori_ratings_df[ori_ratings_df.user_id == user_id]

    # 위에서 뽑은 user_data와 원본 프로그램 데이터를 합친다. 
    user_history = user_data.merge(ori_programs_df, on = 'program_id').sort_values(['score'], ascending=False)

    # 원본 프로그램 데이터에서 사용자가 본 프로그램 데이터를 제외한 데이터를 추출
    recommendations = ori_programs_df[~ori_programs_df['program_id'].isin(user_history['program_id'])]
    # 사용자의 프로그램 평점이 높은 순으로 정렬된 데이터와 위 recommendations을 합친다. 
    recommendations = recommendations.merge( pd.DataFrame(sorted_user_predictions).reset_index(), on = 'program_id')
    # 컬럼 이름 바꾸고 정렬해서 return
    recommendations = recommendations.rename(columns = {user_id: 'Predictions'}).sort_values('Predictions', ascending = False).iloc[:num_recommendations, :]
                        

    return recommendations

# def mf_algo():
#     users, df_program, df_rating, df_svd_preds = predict_table()
#     predict_result = pd.DataFrame()

#     for i, user in enumerate(users):

#         user_result = recommend_programs(df_svd_preds, user, df_program, df_rating)
#         user_result.insert(2, 'user_id', user)
#         user_result = user_result[0:10]
#         predict_result = pd.concat([predict_result, user_result])

#     with open('predict_result', 'wb') as f:
#         pickle.dump(predict_result, f, pickle.HIGHEST_PROTOCOL)
    
#     with open('predict_result', 'rb') as f:
#         data = pickle.load(f)
    
#     return predict_result

def mf_algo_individual(request):
    user_id = request
    users, df_program, df_rating, df_svd_preds = predict_table()
    indi_predict_result = pd.DataFrame()
    indi_user_result = recommend_programs(df_svd_preds, user_id, df_program, df_rating)
    print('!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!')
    print(user_id)
    print('_---------------------------------------')
    print(indi_predict_result)
    print(indi_user_result)
    print('_---------------------------------------')

    indi_user_result.insert(1, 'user_id', user_id)
    print('_---------------------------------------')
    print(indi_user_result)
    print('_---------------------------------------')

    indi_user_result = indi_user_result[0:10]

    indi_predict_result = pd.concat([indi_predict_result, indi_user_result])
    print('##########################################')
    print(indi_predict_result)
    print('##########################################')

    # with open('indi_predict_result', 'wb') as f:
    #     pickle.dump(indi_predict_result, f, pickle.HIGHEST_PROTOCOL)
    
    # with open('indi_predict_result', 'rb') as f:
    #     data = pickle.load(f)

    return indi_predict_result

# 상황 추천 알고리즘
def mf_condition_recomm_prac(programId):
    # 사용자-평점 데이터와 프로그램 데이터가 필요하다
    program_data = query_MySQL('SELECT program_id, title from tb_program')
    rating_data = query_MySQL('SELECT score, program_id, user_id from tb_rating')

    # 사용자-평점데이터와 영화 데이터가 따로 나뉘어져 있으므로 program_id라는 공통 기준을 가지고 합쳐보자
    # 사용자-영화에 따른 평점 데이터를 만들 것이다.
    user_program_rating = pd.merge(program_data, rating_data, on = 'program_id')

    # row가 프로그램, col이 사용자인 테이블을 만들자
    program_user_rating = user_program_rating.pivot_table('score', index = 'program_id', columns = 'user_id')

    # 평점이 없는 NaN인 값을 0으로 바꿔주자
    program_user_rating.fillna(0, inplace=True)

    # 아이템 기반 협업 필터링은 ~ 프로그램을 본 고객은 다음 프로그램도 시청했다, 는 뜻이다.
    # 그리고 그 기반은 평점이 비슷한 것을 기반으로 한다
    # 평점이 비슷하다는 것을 코사인 유사도로 측정해서 보여주자
    item_based_collabor = cosine_similarity(program_user_rating)

    # print(program_user_rating.shape)
    # print(item_based_collabor.shape)

    item_based_collabor = pd.DataFrame(data= item_based_collabor, index= program_user_rating.index, columns= program_user_rating.index)
    # print(item_based_collabor.head())

    print('#### 결과 ###')
    print(item_based_collabor[programId].sort_values(ascending=False)[:5].index.tolist())
    return item_based_collabor[programId].sort_values(ascending=False)[:5].index.tolist()

# mf_condition_recomm_prac(6)

