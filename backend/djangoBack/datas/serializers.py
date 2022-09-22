from dataclasses import field
import imp
from unittest import mock
from rest_framework import serializers
from .models import TbGenreInfo, TbOttInfo, TbProgram

class TbGenreInfoSerializer(serializers.ModelSerializer):
    
    class Meta:
        model = TbGenreInfo
        fields = '__all__'

class OttSerializer(serializers.ModelSerializer):

    class Meta:
        model  = TbOttInfo
        fields = '__all__'

class ProgramSerializer(serializers.ModelSerializer):
    genres = TbGenreInfoSerializer(many=True, read_only=True)
    otts = OttSerializer(many=True, read_only=True)

    class Meta:
        model = TbProgram
        fields = '__all__'

class RecommProgramSerializer(serializers.ModelSerializer):

    class Meta:
        model = TbProgram
        fields = ('program_id', 'title')

# class AllProgramSerializer(serializers.ModelSerializer):
#     genres = GenreSerializer(many=True, read_only=True)
#     otts = OttSerializer(many=True, read_only=True)

#     class Meta:
#         model = Program
#         fields = '__all__'