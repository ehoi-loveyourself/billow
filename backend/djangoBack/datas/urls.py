from django.urls import path
from . import views
app_name = 'datas'
urlpatterns = [
    path('genre_data/', views.genre_data),
    path('program_data/', views.program_data),
    path('all_program_data/', view=views.all_program_data)
]