DATABASES = {
    'default' : {
        'ENGINE': 'django.db.backends.mysql',
        'NAME': 'billow', # 연동할 MYSQL의 DB명
        'USER': 'B309', # DB 접속 계정 명
        'PASSWORD': 'B309Billow', # 해당 DB 접속 계정 비밀번호
        # 'HOST': 'j7b309.p.ssafy.io', # 실제 DB 주소
        'HOST': 'localhost', # 로컬 DB 주소
        'PORT': '3306' # 포트 번호
    }   
}
SECRET_KEY = 'ssafy second project B309 Billow'