from dataclasses import field
import imp
from unittest import mock
from rest_framework import serializers
from .models import Program, Genre

class GenreSerializer(serializers.ModelSerializer):
    
    class Meta:
        model = Genre
        fields = '__all__'

class ProgramSerializer(serializers.ModelSerializer):
    genres = GenreSerializer(many=True, read_only=True)

    class Meta:
        model = Program
        fields = '__all__'

class AllProgramSerializer(serializers.ModelSerializer):
    genres = GenreSerializer(many=True, read_only=True)

    class Meta:
        model = Program
        fields = '__all__'