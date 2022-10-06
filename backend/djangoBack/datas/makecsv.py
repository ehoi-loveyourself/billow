import pandas as pd
import MySQLdb

conn = MySQLdb.connect(host='localhost', user='B309', password='B309Billow', db='billow', charset='utf8')
program_query = 'SELECT * FROM tb_program'
program_df = pd.read_sql_query(program_query, conn)
conn = MySQLdb.connect(host='j7b309.p.ssafy.io', user='B309', password='B309Billow', db='billow', charset='utf8')

user_rating_query = 'SELECT * FROM tb_rating'
user_rating_df = pd.read_sql_query(user_rating_query, conn)
user_rating_df.to_csv(r"C:\Users\qqq59\Desktop\Billow\S07P22B309\backend\djangoBack\data\user_rating_data.csv", index=False)