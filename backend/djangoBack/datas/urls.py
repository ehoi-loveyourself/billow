from django.urls import path
from . import views
app_name = 'datas'
urlpatterns = [
    path('genre_data/', views.genre_data),
    # path('program_data/', views.program_data),
    path('all_program_data/', views.all_program_data),
    path('ott_data/', views.ott_data),
    path('user/', views.user_create),
    path('rating/', views.rating_create),
    path('recomm/', views.user_recomm),
    path('<int:user_id>/', views.user_recomm)
]