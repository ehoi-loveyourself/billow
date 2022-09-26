FROM python:3.10.1
WORKDIR /var/jenkins_home/workspace/billow/backend/djangoBack
COPY requirements.txt ./

RUN pip install --upgrade pip
RUN pip install -r requirements.txt
COPY . .
EXPOSE 8000
CMD ["gunicorn", "djangoBack.wsgi", "--bind", "0.0.0.0:8000"]