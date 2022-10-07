from django.urls import path, include

urlpatterns = [
    path('db/', include('datas.urls')),
]
