from django.urls import path
from . import views
app_name = 'datas'
urlpatterns = [
    path('genre_data/', views.genre_data),
    # path('program_data/', views.program_data),
    path('all_program_data/', views.all_program_data),
    path('ott_data/', views.ott_data),
]