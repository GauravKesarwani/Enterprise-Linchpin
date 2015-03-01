from django.conf.urls import patterns, include, url
from django.contrib import admin
from linchpin.views import EmployeeView

urlpatterns = patterns('',
    # Examples:
    url(r'^$', EmployeeView.as_view(), name='Emp'),
)