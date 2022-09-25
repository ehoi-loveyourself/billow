from asyncio.windows_events import NULL
from this import d
from urllib import response
from django.shortcuts import render

# from rest_framework.decorators import api_view
from rest_framework.response import Response
from rest_framework.decorators import api_view, permission_classes, authentication_classes
from rest_framework.permissions import IsAuthenticated
# from rest_framework_jwt.authentication import JSONWebTokenAuthentication
from datas import serializers

from datas.models import TbGenre, TbGenreInfo, TbOtt, TbOttInfo, TbProgram, TbRating, TbUser
from datas.serializers import RecommProgramSerializer, TbGenreInfoSerializer, ProgramSerializer
from datas import recomm

import requests
import random

# from rest_framework_jwt.authentication import JSONWebTokenAuthentication
# from rest_framework_jwt.utils import jwt_decode_handler

# def token_decode(request):
#     print(request)
#     print(request.user)
#     auth = JSONWebTokenAuthentication()    
#     jwt_value = auth.get_jwt_value(request)    
#     payload = jwt_decode_handler(jwt_value)    
    
#     return payload


API_KEY = '3beacdbb8f7b35eb8c782851ddc5b403'

@api_view(['GET'])
def genre_data(request):
    res = requests.get('https://api.themoviedb.org/3/genre/tv/list?api_key=3beacdbb8f7b35eb8c782851ddc5b403&language=ko-kr')
    data = res.json()['genres']
    for genre_data in data:
        genre_id = genre_data.get('id')
        name = genre_data.get('name')

        genre_info = TbGenreInfo.objects.create(
            genre_info_id = genre_id,
            name = name
        )
    return Response()

@api_view(['GET'])
def all_program_data(request):
    BASE_URL = 'https://api.themoviedb.org/3/tv/popular?api_key=3beacdbb8f7b35eb8c782851ddc5b403&language=ko-kr&page='
    i = 0
    while True:
        i += 1
        res = requests.get(BASE_URL+str(i))
        program_page_list = res.json()['results']
        for program_data in program_page_list:
            program_id=program_data['id']
            program_country = program_data['original_language']
            if program_country == 'ko':
                program_detail = f'https://api.themoviedb.org/3/tv/{program_id}?api_key=3beacdbb8f7b35eb8c782851ddc5b403&language=ko-kr'
                program_ott = f'https://api.themoviedb.org/3/tv/{program_id}/watch/providers?api_key=3beacdbb8f7b35eb8c782851ddc5b403'
                detail_res = requests.get(program_detail)
                ott_res = requests.get(program_ott)
                data = detail_res.json()
                ott_data = ott_res.json()['results']
                original_language = data.get('original_language')   
                if original_language == 'ko':
                    title = data.get('name')
                    summary = data.get('overview')
                    networks = data.get('networks')
                    poster_img = data.get('poster_path')
                    backdrop_path = data.get('backdrop_path')
                    first_air_date = data.get('first_air_date')
                    for network in networks:
                        broadcasting_station = network.get('name')
                        break
                    average_rating = data.get('vote_average')
                    try:
                        if not summary:
                            continue
                        if not poster_img:
                            continue
                        if not backdrop_path:
                            continue
                    except:
                        continue
                    program = TbProgram.objects.create(
                        program_num = program_id,
                        title = title,
                        summary = summary,
                        broadcasting_station = broadcasting_station,
                        average_rating = average_rating,
                        poster_img = 'https://image.tmdb.org/t/p/original' + poster_img,
                        backdrop_path = 'https://image.tmdb.org/t/p/original' + backdrop_path,
                        first_air_date = first_air_date
                    )
                    for program_genre in data.get('genres'):
                        if program_genre == NULL:
                            break
                        genre = TbGenreInfo.objects.get(pk=program_genre.get('id'))
                        TbGenre.objects.create(
                            program_id = program.program_id,
                            genre_info = genre
                        )
                    kr_ott = ott_data.get('KR')
                    if kr_ott != None:
                        ott_list = kr_ott.get('flatrate')
                        if ott_list != None:
                            for ott_detail in ott_list:
                                if ott_detail == NULL:
                                    break
                                ott = TbOttInfo.objects.get(pk=ott_detail.get('provider_id'))
                                TbOtt.objects.create(
                                    ott_info = ott,
                                    program_id = program.program_id
                                )
    return Response()

@api_view(['GET'])
def ott_data(request):
    BASE_URL = "https://api.themoviedb.org/3/watch/providers/tv?api_key=3beacdbb8f7b35eb8c782851ddc5b403&language=ko-kr&watch_region=KR"
    res = requests.get(BASE_URL)
    ott_list = res.json()['results']
    for ott_data in ott_list:
        id = ott_data.get('provider_id')
        name = ott_data.get('provider_name')

        ott = TbOttInfo.objects.create(
            ott_info_id = id,
            name = name
        )
    return Response()

@api_view(['GET'])
def user_create(request):
    for i in range(500):
        name = f'name{i}',
        nick_name = f'nick_name{i}'
        TbUser.objects.create(
            name = name,
            nick_name = nick_name
        )
    return Response()

# @api_view(['GET'])
# def rating_create(request):
#     lst = []
#     for num in range(1, 590):
#         lst.append(num)
#     for i in range(1, 500):
#         user = TbUser.objects.get(pk=i)
#         program_list = random.sample(lst, 50)
#         for program_number in program_list:
#             program = TbProgram.objects.get(pk=program_number)
#             score = random.uniform(0,5)
#             TbRating.objects.create(
#                 score = score,
#                 user_id = i,
#                 program_id = program_number
#         )
#     return response()

@api_view(['GET'])
def rating_create(request):
    lst = []
    for num in range(1, 590):
        lst.append(num)
    for i in range(1001, 1001):
        user = TbUser.objects.get(pk=i)
        program_list = random.sample(lst, 50)
        for program_number in program_list:
            program = TbProgram.objects.get(pk=program_number)
            score = random.uniform(0,5)
            TbRating.objects.create(
                score = score,
                user_id = i,
                program_id = program_number
        )
    return response()

@api_view(['GET'])
# @permission_classes((IsAuthenticated, ))
# @authentication_classes((JSONWebTokenAuthentication,))
def user_recomm(request, user_id):
# def user_recomm(request, user_id):

    # print(request)
    # print(request.user)
    # user = request.user
    # user_id = user.user_id
    user_id = user_id
    print(user_id)
    indi_user_recomm = recomm.mf_algo_individual(user_id)

    # indi_user_recomm = indi_user_recomm[indi_user_recomm['user_id']==user_id]

    indi_user_recomm = indi_user_recomm.values.tolist()

    print(indi_user_recomm)

    indi_user_recomm_list = []
    for program_id in indi_user_recomm:
        program = TbProgram.objects.get(pk=program_id[0])
        indi_user_recomm_list.append(program)

    print(indi_user_recomm_list)
    
    # serializer = RecommProgramSerializer(indi_user_recomm_list, many = True)
    serializer = ProgramSerializer(indi_user_recomm_list, many = True)


    return Response(serializer.data)