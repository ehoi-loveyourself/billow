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
    img_url = models.CharField(max_length=255, blank=True, null=True)
    play_name = models.CharField(max_length=255)
    program = models.ForeignKey('TbProgram', models.DO_NOTHING, blank=True, null=True)

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


class TbGenderAgeViewer(models.Model):
    gender_age_viewer_id = models.BigAutoField(primary_key=True)
    area = models.CharField(max_length=255, blank=True, null=True)
    female0 = models.FloatField(blank=True, null=True)
    female10 = models.FloatField(blank=True, null=True)
    female20 = models.FloatField(blank=True, null=True)
    female30 = models.FloatField(blank=True, null=True)
    female40 = models.FloatField(blank=True, null=True)
    female50 = models.FloatField(blank=True, null=True)
    female60 = models.FloatField(blank=True, null=True)
    genre_lclas = models.CharField(max_length=255, blank=True, null=True)
    genre_mlsfc = models.CharField(max_length=255, blank=True, null=True)
    genre_sclas = models.CharField(max_length=255, blank=True, null=True)
    male0 = models.FloatField(blank=True, null=True)
    male10 = models.FloatField(blank=True, null=True)
    male20 = models.FloatField(blank=True, null=True)
    male30 = models.FloatField(blank=True, null=True)
    male40 = models.FloatField(blank=True, null=True)
    male50 = models.FloatField(blank=True, null=True)
    male60 = models.FloatField(blank=True, null=True)
    program_title = models.CharField(max_length=255, blank=True, null=True)
    program = models.ForeignKey('TbProgram', models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tb_gender_age_viewer'


class TbGenre(models.Model):
    genre_id = models.BigAutoField(primary_key=True)
    genre_info = models.ForeignKey('TbGenreInfo', models.DO_NOTHING)
    program = models.ForeignKey('TbProgram', models.DO_NOTHING)

    class Meta:
        managed = False
        db_table = 'tb_genre'


class TbGenreInfo(models.Model):
    genre_info_id = models.BigAutoField(primary_key=True)
    name = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tb_genre_info'


class TbKDrama(models.Model):
    k_drama_id = models.BigAutoField(primary_key=True)
    brdcst_de = models.CharField(max_length=255, blank=True, null=True)
    brdcst_end_de = models.CharField(max_length=255, blank=True, null=True)
    brdcst_time = models.CharField(max_length=255, blank=True, null=True)
    brdcst_tme_nm = models.CharField(max_length=255, blank=True, null=True)
    chnnel_nm = models.CharField(max_length=255, blank=True, null=True)
    cst_cn = models.CharField(max_length=1000, blank=True, null=True)
    female_4_9yo_wtchng_rt = models.FloatField(blank=True, null=True)
    female_n10s_wtchng_rt = models.FloatField(blank=True, null=True)
    female_n20s_wtchng_rt = models.FloatField(blank=True, null=True)
    female_n30s_wtchng_rt = models.FloatField(blank=True, null=True)
    female_n40s_wtchng_rt = models.FloatField(blank=True, null=True)
    female_n50s_wtchng_rt = models.FloatField(blank=True, null=True)
    female_n60s_above_wtchng_rt = models.FloatField(blank=True, null=True)
    male_4_9yo_wtchng_rt = models.FloatField(blank=True, null=True)
    male_n10s_wtchng_rt = models.FloatField(blank=True, null=True)
    male_n20s_wtchng_rt = models.FloatField(blank=True, null=True)
    male_n30s_wtchng_rt = models.FloatField(blank=True, null=True)
    male_n40s_wtchng_rt = models.FloatField(blank=True, null=True)
    male_n50s_wtchng_rt = models.FloatField(blank=True, null=True)
    male_n60s_above_wtchng_rt = models.FloatField(blank=True, null=True)
    program_begin_time = models.CharField(max_length=255, blank=True, null=True)
    program_brdcst_area_nm = models.CharField(max_length=255, blank=True, null=True)
    program_dc = models.CharField(max_length=255, blank=True, null=True)
    program_end_time = models.CharField(max_length=255, blank=True, null=True)
    program_genre_lclas_nm = models.CharField(max_length=255, blank=True, null=True)
    program_genre_mlsfc_nm = models.CharField(max_length=255, blank=True, null=True)
    program_genre_sclas_nm = models.CharField(max_length=255, blank=True, null=True)
    program_nm = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tb_k_drama'


class TbKPop(models.Model):
    k_pop_id = models.BigAutoField(primary_key=True)
    brdcst_de = models.CharField(max_length=255, blank=True, null=True)
    brdcst_end_de = models.CharField(max_length=255, blank=True, null=True)
    brdcst_time = models.CharField(max_length=255, blank=True, null=True)
    brdcst_tme_nm = models.CharField(max_length=255, blank=True, null=True)
    cast_nm = models.CharField(max_length=255, blank=True, null=True)
    chnnel_nm = models.CharField(max_length=255, blank=True, null=True)
    female_4_9yo_wtchng_rt = models.FloatField(blank=True, null=True)
    female_n10s_wtchng_rt = models.FloatField(blank=True, null=True)
    female_n20s_wtchng_rt = models.FloatField(blank=True, null=True)
    female_n30s_wtchng_rt = models.FloatField(blank=True, null=True)
    female_n40s_wtchng_rt = models.FloatField(blank=True, null=True)
    female_n50s_wtchng_rt = models.FloatField(blank=True, null=True)
    female_n60s_above_wtchng_rt = models.FloatField(blank=True, null=True)
    fixing_cast_nm = models.CharField(max_length=255, blank=True, null=True)
    male_4_9yo_wtchng_rt = models.FloatField(blank=True, null=True)
    male_n10s_wtchng_rt = models.FloatField(blank=True, null=True)
    male_n20s_wtchng_rt = models.FloatField(blank=True, null=True)
    male_n30s_wtchng_rt = models.FloatField(blank=True, null=True)
    male_n40s_wtchng_rt = models.FloatField(blank=True, null=True)
    male_n50s_wtchng_rt = models.FloatField(blank=True, null=True)
    male_n60s_above_wtchng_rt = models.FloatField(blank=True, null=True)
    program_begin_time = models.CharField(max_length=255, blank=True, null=True)
    program_brdcst_area_nm = models.CharField(max_length=255, blank=True, null=True)
    program_dc = models.CharField(max_length=255, blank=True, null=True)
    program_end_time = models.CharField(max_length=255, blank=True, null=True)
    program_genre_lclas_nm = models.CharField(max_length=255, blank=True, null=True)
    program_genre_mlsfc_nm = models.CharField(max_length=255, blank=True, null=True)
    program_genre_sclas_nm = models.CharField(max_length=255, blank=True, null=True)
    program_nm = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tb_k_pop'


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
    origin_name = models.CharField(max_length=255, blank=True, null=True)
    save_folder = models.CharField(max_length=255, blank=True, null=True)
    save_name = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tb_poster_img'


class TbProfileImg(models.Model):
    profile_img_id = models.BigAutoField(primary_key=True)
    img_name = models.CharField(max_length=255, blank=True, null=True)
    save_folder = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tb_profile_img'


class TbProgram(models.Model):
    program_id = models.BigAutoField(primary_key=True)
    age = models.IntegerField(blank=True, null=True)
    average_rating = models.FloatField(blank=True, null=True)
    backdrop_path = models.CharField(max_length=255, blank=True, null=True)
    broadcasting_day = models.CharField(max_length=255, blank=True, null=True)
    broadcasting_station = models.CharField(max_length=255, blank=True, null=True)
    broadcasting_time = models.CharField(max_length=255, blank=True, null=True)
    end_flag = models.TextField()  # This field type is a guess.
    poster_img = models.CharField(max_length=255, blank=True, null=True)
    summary = models.CharField(max_length=1000, blank=True, null=True)
    title = models.CharField(max_length=255, blank=True, null=True)
    
    genres = models.ManyToManyField('TbGenreInfo', through='TbGenre')
    otts = models.ManyToManyField('TbOttInfo', through='TbOtt')
    class Meta:
        managed = False
        db_table = 'tb_program'


class TbProgramOrganization(models.Model):
    program_organization_id = models.BigAutoField(primary_key=True)
    broadcasting_age = models.CharField(max_length=255, blank=True, null=True)
    broadcasting_day = models.CharField(max_length=255)
    broadcasting_episode = models.CharField(max_length=255, blank=True, null=True)
    broadcasting_rerun = models.CharField(max_length=255, blank=True, null=True)
    broadcasting_station = models.CharField(max_length=255)
    broadcasting_time = models.CharField(max_length=255)
    program = models.ForeignKey(TbProgram, models.DO_NOTHING, blank=True, null=True)

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
    age = models.IntegerField(blank=True, null=True)
    email = models.CharField(max_length=255)
    gender = models.CharField(max_length=255, blank=True, null=True)
    name = models.CharField(max_length=255)
    nick_name = models.CharField(max_length=255, blank=True, null=True)
    refresh_token = models.CharField(max_length=255, blank=True, null=True)
    profile_img = models.ForeignKey(TbProfileImg, models.DO_NOTHING, blank=True, null=True)
    region = models.ForeignKey(TbRegion, models.DO_NOTHING, blank=True, null=True)
    tv_carrier = models.ForeignKey(TbTvCarrier, models.DO_NOTHING, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tb_user'
