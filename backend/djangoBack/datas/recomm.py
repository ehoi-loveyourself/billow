import pandas as pd
import numpy as np
import MySQLdb
from sklearn.decomposition import TruncatedSVD
from scipy.sparse.linalg import svds

# import matplotlib.pyplot as plt
# import seaborn as sns
import warnings
import requests
warnings.filterwarnings("ignore")
# from datas.models import TbProgram #TbUser


def query_MySQL(query):
    # DB 연결

    conn = MySQLdb.connect(
        host = 'localhost',
        user = 'B309',
        password = 'B309Billow',
        db = 'billow'
    )

    global query_result

    query_result = pd.read_sql(query, conn)

    conn.close()

    print('-------------------')

    return query_result
    
def predict_table():
    df_program = query_MySQL('SELECT program_id, title from tb_program')
    df_rating = query_MySQL('SELECT score, program_id, user_id from tb_rating')
    user_list = query_MySQL('SELECT * From tb_user')

    print(user_list)

    user_list = user_list.values.tolist()
    users = []
    print(user_list)
    for user in user_list:
        users.append(user[0])
    print(users)

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

    return df_program, df_rating, df_svd_preds

def recommend_programs(df_svd_preds, user_id, ori_programs_df, ori_ratings_df, num_recommendations=5):

    #현재는 index로 적용이 되어있으므로 user_id - 1을 해야함.
    user_row_number = user_id - 1 

    # 최종적으로 만든 pred_df에서 사용자 index에 따라 영화 데이터 정렬 -> 영화 평점이 높은 순으로 정렬 됌
    sorted_user_predictions = df_svd_preds.iloc[user_row_number].sort_values(ascending=False)

    # 원본 평점 데이터에서 user id에 해당하는 데이터를 뽑아낸다. 
    user_data = ori_ratings_df[ori_ratings_df.user_id == user_id]

    # 위에서 뽑은 user_data와 원본 영화 데이터를 합친다. 
    user_history = user_data.merge(ori_programs_df, on = 'program_id').sort_values(['score'], ascending=False)

    # 원본 영화 데이터에서 사용자가 본 영화 데이터를 제외한 데이터를 추출
    recommendations = ori_programs_df[~ori_programs_df['program_id'].isin(user_history['program_id'])]
    # 사용자의 영화 평점이 높은 순으로 정렬된 데이터와 위 recommendations을 합친다. 
    recommendations = recommendations.merge( pd.DataFrame(sorted_user_predictions).reset_index(), on = 'program_id')
    # 컬럼 이름 바꾸고 정렬해서 return
    recommendations = recommendations.rename(columns = {user_row_number: 'Predictions'}).sort_values('Predictions', ascending = False).iloc[:num_recommendations, :]
                        

    return recommendations #user_history, recommendations

def mf_algo(request):
    df_program, df_rating, df_svd_preds = predict_table()
    user = request.user
    user_pk = user

# print(recommend_programs(df_svd_preds, 1, program_data, rating_data, 10))

predict_table()