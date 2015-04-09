from django.conf.urls import patterns, include, url
from django.contrib import admin
from linchpin.views import EmployeeView

urlpatterns = patterns('',
    # Examples:
    url(r'^linchpin/', include('linchpin.urls')),
    url(r'^admin/', include(admin.site.urls)),
)
