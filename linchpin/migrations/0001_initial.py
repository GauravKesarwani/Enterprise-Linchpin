# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
import datetime
from django.utils.timezone import utc


class Migration(migrations.Migration):

    dependencies = [
    ]

    operations = [
        migrations.CreateModel(
            name='Compensation',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('tenantId', models.IntegerField()),
                ('empId', models.IntegerField()),
                ('band', models.CharField(max_length=50)),
                ('frequency', models.CharField(max_length=30)),
                ('location', models.CharField(max_length=50)),
                ('totalBasePay', models.CharField(max_length=30)),
                ('totalCtc', models.CharField(max_length=30)),
                ('currency', models.CharField(default=b'USD', max_length=45)),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='Employee',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('emp_id', models.IntegerField(default=0)),
                ('fname', models.CharField(max_length=45)),
                ('lname', models.CharField(max_length=45)),
                ('emp_designation', models.CharField(max_length=70)),
                ('emp_department', models.CharField(max_length=70)),
                ('home_phone', models.CharField(max_length=70)),
                ('work_email', models.CharField(max_length=70)),
                ('team_id', models.CharField(default=b'', max_length=10)),
                ('manager', models.CharField(default=b'', max_length=70)),
                ('totalExprience', models.IntegerField(default=0)),
                ('joiningDate', models.DateField(default=datetime.datetime(2015, 3, 30, 2, 59, 38, 406656, tzinfo=utc))),
            ],
            options={
            },
            bases=(models.Model,),
        ),
        migrations.CreateModel(
            name='JobHistory',
            fields=[
                ('id', models.AutoField(verbose_name='ID', serialize=False, auto_created=True, primary_key=True)),
                ('tenantId', models.IntegerField(default=0)),
                ('serialId', models.IntegerField(default=0)),
                ('emp_id', models.IntegerField(default=0)),
                ('companyName', models.CharField(max_length=50)),
                ('startDate', models.DateField()),
                ('endDate', models.DateField()),
            ],
            options={
            },
            bases=(models.Model,),
        ),
    ]
