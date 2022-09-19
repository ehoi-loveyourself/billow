import pandas as pd
import MySQLdb

conn = MySQLdb.connect(host='localhost', user='B309', password='B309Billow', db='billow', charset='utf8')
query = 'SELECT * FROM tb_program'
df = pd.read_sql_query(query, conn)
df.to_csv(r"C:\Users\multicampus\Desktop\Billow\S07P22B309\backend\djangoBack\data\program_data.csv", index=False)
