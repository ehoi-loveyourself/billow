# This is an auto-generated Django model module.
# You'll have to do the following manually to clean this up:
#   * Rearrange models' order
#   * Make sure each model has one field with primary_key=True
#   * Make sure each ForeignKey and OneToOneField has `on_delete` set to the desired behavior
#   * Remove `managed = False` lines if you wish to allow Django to create, modify, and delete the table
# Feel free to rename the models, but don't rename db_table values or field names.
from django.db import models


class AuthGroup(models.Model):
    name = models.CharField(unique=True, max_length=150)

    class Meta:
        managed = False
        db_table = 'auth_group'


class AuthGroupPermissions(models.Model):
    id = models.BigAutoField(primary_key=True)
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)
    permission = models.ForeignKey('AuthPermission', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_group_permissions'
        unique_together = (('group', 'permission'),)


class AuthPermission(models.Model):
    name = models.CharField(max_length=255)
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING)
    codename = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'auth_permission'
        unique_together = (('content_type', 'codename'),)


class AuthUser(models.Model):
    password = models.CharField(max_length=128)
    last_login = models.DateTimeField(blank=True, null=True)
    is_superuser = models.IntegerField()
    username = models.CharField(unique=True, max_length=150)
    first_name = models.CharField(max_length=150)
    last_name = models.CharField(max_length=150)
    email = models.CharField(max_length=254)
    is_staff = models.IntegerField()
    is_active = models.IntegerField()
    date_joined = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'auth_user'


class AuthUserGroups(models.Model):
    id = models.BigAutoField(primary_key=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    group = models.ForeignKey(AuthGroup, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_groups'
        unique_together = (('user', 'group'),)


class AuthUserUserPermissions(models.Model):
    id = models.BigAutoField(primary_key=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)
    permission = models.ForeignKey(AuthPermission, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'auth_user_user_permissions'
        unique_together = (('user', 'permission'),)


class DatasAllprogram(models.Model):
    id = models.IntegerField(primary_key=True)
    title = models.CharField(max_length=100)
    age = models.IntegerField()
    summary = models.CharField(max_length=1000)
    broadcasting_station = models.CharField(max_length=100)
    end_flag = models.IntegerField()
    average_rating = models.FloatField()

    class Meta:
        managed = False
        db_table = 'datas_allprogram'


class DatasAllprogramGenres(models.Model):
    id = models.BigAutoField(primary_key=True)
    allprogram = models.ForeignKey(DatasAllprogram, models.DO_NOTHING)
    genre = models.ForeignKey('DatasGenre', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'datas_allprogram_genres'
        unique_together = (('allprogram', 'genre'),)


class DatasAllprogramOtt(models.Model):
    id = models.BigAutoField(primary_key=True)
    allprogram = models.ForeignKey(DatasAllprogram, models.DO_NOTHING)
    ott = models.ForeignKey('DatasOtt', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'datas_allprogram_ott'
        unique_together = (('allprogram', 'ott'),)


class DatasGenre(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=50)

    class Meta:
        managed = False
        db_table = 'datas_genre'


class DatasOtt(models.Model):
    id = models.IntegerField(primary_key=True)
    name = models.CharField(max_length=50)

    class Meta:
        managed = False
        db_table = 'datas_ott'


class DatasProgram(models.Model):
    id = models.IntegerField(primary_key=True)
    title = models.CharField(max_length=100)
    age = models.IntegerField()
    summary = models.CharField(max_length=1000)
    broadcasting_station = models.CharField(max_length=100)
    end_flag = models.IntegerField()
    average_rating = models.FloatField()

    class Meta:
        managed = False
        db_table = 'datas_program'


class DatasProgramGenres(models.Model):
    id = models.BigAutoField(primary_key=True)
    program = models.ForeignKey(DatasProgram, models.DO_NOTHING)
    genre = models.ForeignKey(DatasGenre, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'datas_program_genres'
        unique_together = (('program', 'genre'),)


class DatasProgramOtt(models.Model):
    id = models.BigAutoField(primary_key=True)
    program = models.ForeignKey(DatasProgram, models.DO_NOTHING)
    ott = models.ForeignKey(DatasOtt, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'datas_program_ott'
        unique_together = (('program', 'ott'),)


class DjangoAdminLog(models.Model):
    action_time = models.DateTimeField()
    object_id = models.TextField(blank=True, null=True)
    object_repr = models.CharField(max_length=200)
    action_flag = models.PositiveSmallIntegerField()
    change_message = models.TextField()
    content_type = models.ForeignKey('DjangoContentType', models.DO_NOTHING, blank=True, null=True)
    user = models.ForeignKey(AuthUser, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'django_admin_log'


class DjangoContentType(models.Model):
    app_label = models.CharField(max_length=100)
    model = models.CharField(max_length=100)

    class Meta:
        managed = False
        db_table = 'django_content_type'
        unique_together = (('app_label', 'model'),)


class DjangoMigrations(models.Model):
    id = models.BigAutoField(primary_key=True)
    app = models.CharField(max_length=255)
    name = models.CharField(max_length=255)
    applied = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_migrations'


class DjangoSession(models.Model):
    session_key = models.CharField(primary_key=True, max_length=40)
    session_data = models.TextField()
    expire_date = models.DateTimeField()

    class Meta:
        managed = False
        db_table = 'django_session'


class TbBookmark(models.Model):
    bookmark_id = models.BigAutoField(primary_key=True)
    program = models.ForeignKey('TbProgram', models.DO_NOTHING)
    user = models.ForeignKey('TbUser', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'tb_bookmark'


class TbBroadcastingAlarm(models.Model):
    broadcasting_alarm_id = models.BigAutoField(primary_key=True)
    date_time = models.CharField(max_length=255)
    program = models.ForeignKey('TbProgram', models.DO_NOTHING)
    user = models.ForeignKey('TbUser', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'tb_broadcasting_alarm'


class TbCast(models.Model):
    cast_id = models.BigAutoField(primary_key=True)
    actor_name = models.CharField(max_length=255)
    play_name = models.CharField(max_length=255)
    save_folder = models.CharField(max_length=255, blank=True, null=True)
    save_name = models.CharField(max_length=255, blank=True, null=True)
    program = models.ForeignKey('TbProgram', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'tb_cast'


class TbChat(models.Model):
    chat_id = models.BigAutoField(primary_key=True)
    content = models.CharField(max_length=255)
    date_time = models.CharField(max_length=255)
    program = models.ForeignKey('TbProgram', models.DO_NOTHING)
    user = models.ForeignKey('TbUser', models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tb_chat'


class TbConditionGenre(models.Model):
    genre_id = models.BigAutoField(primary_key=True)
    genre = models.CharField(max_length=255)

    class Meta:
        managed = False
        db_table = 'tb_condition_genre'


class TbConditionProgram(models.Model):
    condition_program_id = models.BigAutoField(primary_key=True)
    genre = models.ForeignKey(TbConditionGenre, models.DO_NOTHING)
    with_whom = models.ForeignKey('TbConditionWithWhom', models.DO_NOTHING)
    program = models.ForeignKey('TbProgram', models.DO_NOTHING)
    user = models.ForeignKey('TbUser', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'tb_condition_program'


class TbConditionWithWhom(models.Model):
    with_whom_id = models.BigAutoField(primary_key=True)
    who = models.CharField(max_length=255)

    class Meta:
        managed = False
        db_table = 'tb_condition_with_whom'


class TbOrganization(models.Model):
    organization_id = models.BigAutoField(primary_key=True)
    broadcasting_day = models.CharField(max_length=255)
    broadcasting_station = models.CharField(max_length=255)
    broadcasting_time = models.CharField(max_length=255)
    broadcasting_type = models.CharField(max_length=255)
    program_title = models.CharField(max_length=255)
    program = models.ForeignKey('TbProgram', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'tb_organization'


class TbOtt(models.Model):
    ott_id = models.BigAutoField(primary_key=True)
    ott_info = models.ForeignKey('TbOttInfo', models.DO_NOTHING)
    program = models.ForeignKey('TbProgram', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'tb_ott'


class TbOttInfo(models.Model):
    ott_info_id = models.BigAutoField(primary_key=True)
    name = models.CharField(max_length=255)
    save_folder = models.CharField(max_length=255)
    save_name = models.CharField(max_length=255)
    url = models.CharField(max_length=255)

    class Meta:
        managed = False
        db_table = 'tb_ott_info'


class TbPosterImg(models.Model):
    poster_img_id = models.BigAutoField(primary_key=True)
    origin_name = models.CharField(max_length=255)
    save_folder = models.CharField(max_length=255)
    save_name = models.CharField(max_length=255)

    class Meta:
        managed = False
        db_table = 'tb_poster_img'


class TbProfileImg(models.Model):
    profile_img_id = models.BigAutoField(primary_key=True)
    origin_name = models.CharField(max_length=255, blank=True, null=True)
    save_folder = models.CharField(max_length=255, blank=True, null=True)
    save_name = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tb_profile_img'


class TbProgram(models.Model):
    program_id = models.BigAutoField(primary_key=True)
    age = models.IntegerField()
    average_rating = models.FloatField()
    broadcasting_day = models.CharField(max_length=255)
    broadcasting_station = models.CharField(max_length=255)
    broadcasting_time = models.CharField(max_length=255)
    end_flag = models.TextField()  # This field type is a guess.
    genre = models.CharField(max_length=255)
    summary = models.CharField(max_length=255)
    title = models.CharField(max_length=255)
    poster_img = models.ForeignKey(TbPosterImg, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'tb_program'


class TbProgramOrganization(models.Model):
    program_organization_id = models.BigAutoField(primary_key=True)
    broadcasting_day = models.CharField(max_length=255)
    broadcasting_station = models.CharField(max_length=255)
    broadcasting_time = models.CharField(max_length=255)
    broadcasting_type = models.CharField(max_length=255)
    program_title = models.CharField(max_length=255)
    program = models.ForeignKey(TbProgram, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'tb_program_organization'


class TbRating(models.Model):
    rating_id = models.BigAutoField(primary_key=True)
    score = models.FloatField()
    program = models.ForeignKey(TbProgram, models.DO_NOTHING)
    user = models.ForeignKey('TbUser', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'tb_rating'


class TbRegion(models.Model):
    region_id = models.BigAutoField(primary_key=True)
    region = models.CharField(max_length=255)

    class Meta:
        managed = False
        db_table = 'tb_region'


class TbReview(models.Model):
    review_id = models.BigAutoField(primary_key=True)
    content = models.CharField(max_length=255)
    date_time = models.CharField(max_length=255)
    program = models.ForeignKey(TbProgram, models.DO_NOTHING)
    user = models.ForeignKey('TbUser', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'tb_review'


class TbTvCarrier(models.Model):
    tv_carrier_id = models.BigAutoField(primary_key=True)
    company = models.CharField(max_length=255)

    class Meta:
        managed = False
        db_table = 'tb_tv_carrier'


class TbUser(models.Model):
    user_id = models.BigAutoField(primary_key=True)
    age = models.IntegerField()
    email = models.CharField(max_length=255)
    gender = models.TextField()  # This field type is a guess.
    name = models.CharField(max_length=255)
    nick_name = models.CharField(max_length=255)
    profile_img = models.ForeignKey(TbProfileImg, models.DO_NOTHING)
    region = models.ForeignKey(TbRegion, models.DO_NOTHING)
    tv_carrier = models.ForeignKey(TbTvCarrier, models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'tb_user'
