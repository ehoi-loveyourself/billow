FROM python:3.10.1
WORKDIR /var/jenkins_home/workspace/billow/backend/djangoBack
COPY requirements.txt ./

RUN pip install --upgrade pip
RUN pip install -r requirements.txt
COPY . .
EXPOSE 8000
CMD ["gunicorn", "djangoBack.wsgi", "--bind", "0.0.0.0:8000"]

# FROM python:3.10.1

# WORKDIR /usr/src/app

# COPY . .

# RUN apt-get update \
#   && apt-get install -y 
# RUN pip install -r requirements.txt
# RUN apt-get install default-jdk -y

# WORKDIR ./djangoBack
# # manage.py를 실행할 수 있는 디렉토리로 이동합니다.

# # CMD ["python", "manage.py", "migrate"]

# CMD ["python", "manage.py", "runserver", "0.0.0.0:8000"]
# # 이동한 디렉토리에서 django를 가동시켜주는 코드를 작성합니다. 여기서 port는 8000로 실행시키겠습니다.

# EXPOSE 8000