from django.db import models
from django.conf import settings
from django.core.validators import MinValueValidator, MaxValueValidator

class Genre(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=50)
    def __str__(self):
        return self.name


class Ott(models.Model):
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

    genres = models.ManyToManyField(Genre)
    ott = models.ManyToManyField(Ott)

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

    genres = models.ManyToManyField(Genre)
    ott = models.ManyToManyField(Ott)

    def __str__(self):
        return self.title