from apscheduler.schedulers.background import BackgroundScheduler
from django_apscheduler.jobstores import register_events, DjangoJobStore

from datas.views import all_program_data

def start():
    scheduler = BackgroundScheduler()
    scheduler.add_jobstore(DjangoJobStore(), 'djangojobstore')
    register_events(scheduler)
    @scheduler.scheduled_job('cron', day_of_week='sun', hour=23, name = 'all_program_data')
    def auto_check():
        all_program_data()
    scheduler.start()