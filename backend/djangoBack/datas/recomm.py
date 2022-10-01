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
warnings.filterwarnings("ignore")

# 1 DB 커넥션 따로
CONN = MySQLdb.connect(
    host = 'localhost',
    user = 'B309',
    password = 'B309Billow',
    db = 'billow'
)

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
    user_row_number = user_id - 1 

    # 최종적으로 만든 pred_df에서 사용자 index에 따라 프로그램 데이터 정렬 -> 프로그램 평점이 높은 순으로 정렬 됌
    sorted_user_predictions = df_svd_preds.iloc[user_row_number].sort_values(ascending=False)

    # 원본 평점 데이터에서 user id에 해당하는 데이터를 뽑아낸다. 
    user_data = ori_ratings_df[ori_ratings_df.user_id == user_id]

    # 위에서 뽑은 user_data와 원본 프로그램 데이터를 합친다. 
    user_history = user_data.merge(ori_programs_df, on = 'program_id').sort_values(['score'], ascending=False)

    # 원본 프로그램 데이터에서 사용자가 본 프로그램 데이터를 제외한 데이터를 추출
    recommendations = ori_programs_df[~ori_programs_df['program_id'].isin(user_history['program_id'])]
    # 사용자의 프로그램 평점이 높은 순으로 정렬된 데이터와 위 recommendations을 합친다. 
    recommendations = recommendations.merge( pd.DataFrame(sorted_user_predictions).reset_index(), on = 'program_id')
    # 컬럼 이름 바꾸고 정렬해서 return
    recommendations = recommendations.rename(columns = {user_row_number: 'Predictions'}).sort_values('Predictions', ascending = False).iloc[:num_recommendations, :]
                        

    return recommendations

def mf_algo():
    users, df_program, df_rating, df_svd_preds = predict_table()
    predict_result = pd.DataFrame()
    for i, user in enumerate(users):

        user_result = recommend_programs(df_svd_preds, user, df_program, df_rating)
        user_result.insert(2, 'user_id', user)
        user_result = user_result[0:10]

        predict_result = pd.concat([predict_result, user_result])

    with open('predict_result', 'wb') as f:
        pickle.dump(predict_result, f, pickle.HIGHEST_PROTOCOL)
    
    with open('predict_result', 'rb') as f:
        data = pickle.load(f)
    
    return predict_result

def mf_algo_individual(request):
    user_id = request
    users, df_program, df_rating, df_svd_preds = predict_table()
    indi_predict_result = pd.DataFrame()

    indi_user_result = recommend_programs(df_svd_preds, user_id, df_program, df_rating)
    indi_user_result.insert(2, 'user_id', user_id)
    indi_user_result = indi_user_result[0:10]

    indi_predict_result = pd.concat([indi_predict_result, indi_user_result])
    print(indi_predict_result)

    # with open('indi_predict_result', 'wb') as f:
    #     pickle.dump(indi_predict_result, f, pickle.HIGHEST_PROTOCOL)
    
    # with open('indi_predict_result', 'rb') as f:
    #     data = pickle.load(f)

    return indi_predict_result

# 상황 추천 알고리즘을 위한 pivot_table
def mf_condition_recomm(programId):
    program_data = query_MySQL('SELECT program_id, title from tb_program')
    rating_data = query_MySQL('SELECT score, program_id, user_id from tb_rating')

    user_program_data = pd.merge(rating_data, program_data, on = 'program_id')
    # print('##################user_program_data################')
    # print(user_program_data)

    # pivot 테이블을 만들자! value에는 score값을, column에는 program_id를, index에는 user id를 넣자
    user_program_rating = user_program_data.pivot_table('score', index = 'user_id', columns = 'program_id').fillna(0)

    # 이제 사용자-프로그램 기준의 데이터를 프로그램-사용자 기준으로 만들어서 특정 '프로그램'과 비슷한 프로그램을 추천해주는 로직을 구현합시다!
    program_user_rating = user_program_rating.T

    # print('##################user_program_rating################')
    # print(program_user_rating)

    SVD = TruncatedSVD(n_components=12)
    matrix = SVD.fit_transform(program_user_rating)
    matrix.shape

    matrix[0]

    print('#########matrix[0]###########')
    print(matrix[0])

    # 이제 이렇게 나온 데이터를 활용해서 피어슨 상관계수(corr)를 구합니다.
    corr = np.corrcoef(matrix)
    corr.shape

    corr2 = corr[:200, :200]
    corr2.shape

    plt.figure(figsize=(16, 10))
    sns.heatmap(corr2)

    program_id = user_program_rating.columns
    program_id_list = list(program_id)
    if programId not in program_id_list:
        return []
    print(program_id_list) 
    coffey_hands = program_id_list.index(programId)
    corr_coffey_hands = corr[coffey_hands]
    print('##########추천 프로그램 ID##########')
    print(list(program_id[(corr_coffey_hands >= 0.9)])[:10])
    return list(program_id[(corr_coffey_hands >= 0.9)])[:10]


# mf_condition_recomm(100)