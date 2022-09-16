from asyncio.windows_events import NULL
import imp
from django.shortcuts import render

from rest_framework.decorators import api_view
from rest_framework.response import Response
# from datas.models import Ott, Program, Genre, AllProgram
from datas.models import TbGenreInfo, TbOttInfo, TbProgram

# from datas.serializers import GenreSerializer
from datas.serializers import TbGenreInfoSerializer

import requests

API_KEY = '3beacdbb8f7b35eb8c782851ddc5b403'



@api_view(['GET'])
def genre_data(request):
    res = requests.get('https://api.themoviedb.org/3/genre/tv/list?api_key=3beacdbb8f7b35eb8c782851ddc5b403&language=ko-kr')
    data = res.json()['genres']
    for genre_data in data:
        genre_id = genre_data.get('id')
        name = genre_data.get('name')

        genr_info = TbGenreInfo.objects.create(
            genre_id = genre_id,
            name = name
        )
    return Response()


# @api_view(['GET'])
# def program_data(request):
#     BASE_URL = 'https://api.themoviedb.org/3/tv/on_the_air?api_key=3beacdbb8f7b35eb8c782851ddc5b403&language=ko-kr&page='
#     for i in range(1, 501):
#         res = requests.get(BASE_URL+str(i))
#         program_page_list = res.json()['results']

#         for program_data in program_page_list:
#             program_id=program_data['id']
#             program_detail = f'https://api.themoviedb.org/3/tv/{program_id}?api_key=3beacdbb8f7b35eb8c782851ddc5b403&language=ko-kr'
#             detail_res = requests.get(program_detail)
#             data = detail_res.json()
#             original_language = data.get('original_language')
#             if original_language == 'ko':
#                 title = data.get('name')
#                 summary = data.get('overview')
#                 networks = data.get('networks')
#                 for network in networks:
#                     broadcasting_station = network.get('name')
#                     break
#                 average_rating = data.get('vote_average')
#                 try:
#                     if not summary:
#                         continue
#                 except:
#                     continue

#                 program = Program.objects.create(
#                     id = program_id,
#                     title = title,
#                     summary = summary,
#                     broadcasting_station = broadcasting_station,
#                     age = 18,
#                     end_flag = False,
#                     average_rating = average_rating

#                 )
#                 for program_genre in data.get('genres'):
#                     if program_genre == NULL:
#                         break
#                     genre = Genre.objects.get(pk=program_genre.get('id'))
#                     program.genres.add(genre)
#     return Response()

@api_view(['GET'])
def program_data(request):
    BASE_URL = 'https://api.themoviedb.org/3/tv/on_the_air?api_key=3beacdbb8f7b35eb8c782851ddc5b403&language=ko-kr&page='
    for i in range(1, 501):
        res = requests.get(BASE_URL+str(i))
        program_page_list = res.json()['results']
        for program_data in program_page_list:
            program_id=program_data['id']
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
                for network in networks:
                    broadcasting_station = network.get('name')
                    break
                average_rating = data.get('vote_average')
                try:
                    if not summary:
                        continue
                except:
                    continue
                program = TbProgram.objects.create(
                    program_id = program_id,
                    title = title,
                    summary = summary,
                    broadcasting_station = broadcasting_station,
                    age = 18,
                    # end_flag = 1,
                    average_rating = average_rating,
                    poster_img = 'https://image.tmdb.org/t/p/original'+ poster_img,

                )
                for program_genre in data.get('genres'):
                    if program_genre == NULL:
                        break
                    genre = TbGenreInfo.objects.get(pk=program_genre.get('id'))
                    program.genres.add(genre)
                kr_ott = ott_data.get('KR')
                if kr_ott != None:
                    print(kr_ott)
                    ott_list = kr_ott.get('flatrate')
                    if ott_list != None:
                        for ott_detail in ott_list:
                            if ott_detail == NULL:
                                break
                            ott = TbOttInfo.objects.get(pk=ott_detail.get('provider_id'))
                            program.otts.add(ott)     
    return Response()

# @api_view(['GET'])
# def all_program_data(request):
#     BASE_URL = 'https://api.themoviedb.org/3/tv/popular?api_key=3beacdbb8f7b35eb8c782851ddc5b403&language=ko-kr&page='
#     for i in range(1, 1000):
#         res = requests.get(BASE_URL+str(i))
#         program_page_list = res.json()['results']

#         for program_data in program_page_list:
#             program_id=program_data['id']
#             program_detail = f'https://api.themoviedb.org/3/tv/{program_id}?api_key=3beacdbb8f7b35eb8c782851ddc5b403&language=ko-kr'
#             detail_res = requests.get(program_detail)
#             data = detail_res.json()
#             original_language = data.get('original_language')
#             if original_language == 'ko':
#                 title = data.get('name')
#                 summary = data.get('overview')
#                 networks = data.get('networks')
#                 for network in networks:
#                     broadcasting_station = network.get('name')
#                     break
#                 average_rating = data.get('vote_average')
#                 try:
#                     if not summary:
#                         continue
#                 except:
#                     continue

#                 program = AllProgram.objects.create(
#                     id = program_id,
#                     title = title,
#                     summary = summary,
#                     broadcasting_station = broadcasting_station,
#                     age = 18,
#                     end_flag = False,
#                     average_rating = average_rating

#                 )
#                 for program_genre in data.get('genres'):
#                     if program_genre == NULL:
#                         break
#                     genre = Genre.objects.get(pk=program_genre.get('id'))
#                     program.genres.add(genre)
#     return Response()

@api_view(['GET'])
def all_program_data(request):
    BASE_URL = 'https://api.themoviedb.org/3/tv/popular?api_key=3beacdbb8f7b35eb8c782851ddc5b403&language=ko-kr&page='
    # for i in range(1, 1000):
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
                    for network in networks:
                        broadcasting_station = network.get('name')
                        break
                    average_rating = data.get('vote_average')
                    try:
                        if not summary:
                            continue
                        if not poster_img:
                            continue
                    except:
                        continue
                    program = TbProgram.objects.create(
                        program_id = program_id,
                        title = title,
                        summary = summary,
                        broadcasting_station = broadcasting_station,
                        # age = 18,
                        # end_flag = 1,
                        average_rating = average_rating,
                        poster_img = 'https://image.tmdb.org/t/p/original'+ poster_img,

                    )
                    for program_genre in data.get('genres'):
                        if program_genre == NULL:
                            break
                        genre = TbGenreInfo.objects.get(pk=program_genre.get('id'))
                        program.genres.add(genre)
                    kr_ott = ott_data.get('KR')
                    if kr_ott != None:
                        # print(kr_ott)
                        ott_list = kr_ott.get('flatrate')
                        if ott_list != None:
                            for ott_detail in ott_list:
                                if ott_detail == NULL:
                                    break
                                ott = TbOttInfo.objects.get(pk=ott_detail.get('provider_id'))
                                program.otts.add(ott)     
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

