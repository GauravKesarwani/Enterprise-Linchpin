# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations


class Migration(migrations.Migration):

    dependencies = [
    ]

    operations = [
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
            ],
            options={
            },
            bases=(models.Model,),
        ),
    ]
