from re import T
from django.db import models
from django.conf import settings
from django.core.validators import MinValueValidator, MaxValueValidator

class Genre(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=50)
    def __str__(self):
        return self.name

class Program(models.Model):
    id = models.IntegerField(primary_key=True)
    title = models.CharField(max_length=100)
    age = models.IntegerField()
    summary = models.CharField(max_length=1000)
    broadcasting_station = models.CharField(max_length=100)
    end_flag = models.BooleanField()
    average_rating = models.FloatField()

    # release_date = models.DateField()
    # popularity = models.FloatField()
    # vote_count = models.IntegerField()
    # vote_average = models.FloatField()
    # overview = models.TextField()
    # poster_path = models.CharField(max_length=200)
    # backdrop_path = models.CharField(max_length=200)
    # youtube_key = models.CharField(max_length=100)

    genres = models.ManyToManyField(Genre)

    def __str__(self):
        return self.title

class AllProgram(models.Model):
    id = models.IntegerField(primary_key=True)
    title = models.CharField(max_length=100)
    age = models.IntegerField()
    summary = models.CharField(max_length=1000)
    broadcasting_station = models.CharField(max_length=100)
    end_flag = models.BooleanField()
    average_rating = models.FloatField()

    # release_date = models.DateField()
    # popularity = models.FloatField()
    # vote_count = models.IntegerField()
    # vote_average = models.FloatField()
    # overview = models.TextField()
    # poster_path = models.CharField(max_length=200)
    # backdrop_path = models.CharField(max_length=200)
    # youtube_key = models.CharField(max_length=100)

    genres = models.ManyToManyField(Genre)

    def __str__(self):
        return self.title