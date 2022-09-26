FROM python:3.10.1
WORKDIR /var/jenkins_home/workspace/billow/backend/djangoBack
COPY requirements.txt ./

RUN pip install --upgrade pip
RUN pip install -r requirements.txt
COPY . .
CMD ["gunicorn", "djangoBack.wsgi", "--bind", "127.0.0.1:8080"]