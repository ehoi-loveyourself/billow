from dataclasses import field, fields
import imp
from unittest import mock
from rest_framework import serializers
from .models import TbGenre, TbGenreInfo, TbOttInfo, TbProgram

class TbGenreInfoSerializer(serializers.ModelSerializer):
    
    class Meta:
        model = TbGenreInfo
        fields = '__all__'

class OttSerializer(serializers.ModelSerializer):

    class Meta:
        model  = TbOttInfo
        fields = '__all__'

class ProgramSerializer(serializers.ModelSerializer):

    class GenreSerializer(serializers.ModelSerializer):
        class Meta:
            model = TbGenre
            fields = '__all__'
    
    
    # genres = GenreSerializer(many=True, read_only=True)
    genres = TbGenreInfoSerializer(many=True, read_only=True)
    otts = OttSerializer(many=True, read_only=True)
    id = serializers.SerializerMethodField()
    broadcastingDay = serializers.SerializerMethodField()
    broadcastingStation = serializers.SerializerMethodField()
    broadcastingEpisode = serializers.SerializerMethodField()
    endFlag = serializers.SerializerMethodField()
    firstAirDate = serializers.SerializerMethodField()
    averageRating = serializers.SerializerMethodField()
    bookmarkCnt = serializers.SerializerMethodField()
    posterImg = serializers.SerializerMethodField()
    backdropPath = serializers.SerializerMethodField()

    def get_genres(self, obj):
        return obj.genres

    def get_id(self, obj):
        return obj.program_id

    def get_title(self, obj):
        return obj.title
    
    def get_broadcastingDay(self, obj):
        return obj.broadcasting_day

    def get_broadcastingStation(self, obj):
        return obj.broadcasting_station
    
    def get_broadcastingEpisode(self, obj):
        return obj.broadcasting_episode
    
    def get_endFlag(self, obj):
        return obj.end_flag
    
    def get_firstAirDate(self, obj):
        return obj.first_air_date

    def get_averageRating(self, obj):
        return obj.average_rating
    
    def get_bookmarkCnt(self, obj):
        return obj.bookmark_cnt

    def get_posterImg(self, obj):
        return obj.poster_img
    
    def get_backdropPath(self, obj):
        return obj.backdrop_path
    

    class Meta:
        model = TbProgram
        # fields = '__all__'
        fields = (
            "id",
            "genres",
            "otts",
            "title",
            "broadcastingDay",
            "broadcastingStation",
            "summary",
            "broadcastingEpisode",
            "endFlag",
            "firstAirDate",
            "averageRating",
            "bookmarkCnt",
            "posterImg",
            "backdropPath",
            "genres",
            "otts"
        )

# class RecommProgramSerializer(serializers.ModelSerializer):

#     class Meta:
#         model = TbProgram
#         fields = ('program_id', 'title')

# class AllProgramSerializer(serializers.ModelSerializer):
#     genres = GenreSerializer(many=True, read_only=True)
#     otts = OttSerializer(many=True, read_only=True)

#     class Meta:
#         model = Program
#         fields = '__all__'