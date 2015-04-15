from django.conf.urls import patterns, include, url
from django.contrib import admin
from linchpin.views import EmployeeView, CompensationView, TeamInfoView, JobHistoryView, DirectoryView, AdvancedSearchDirectoryView

urlpatterns = patterns('',
    # Examples:
    url(r'^employee/$', EmployeeView.as_view(), name='Emp'),
    url(r'^compensation/$', CompensationView.as_view(), name='Compensation'),
    url(r'^teamInfo/$', TeamInfoView.as_view(), name='TeamInfo'),
    url(r'^experience/$', JobHistoryView.as_view(), name='Experience'),
    url(r'^directory/$', DirectoryView.as_view(), name='Directory'),
    url(r'^adsearch/$', AdvancedSearchDirectoryView.as_view(), name='SearchDirectory')
)