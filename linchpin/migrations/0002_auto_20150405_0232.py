# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
import datetime
from django.utils.timezone import utc


class Migration(migrations.Migration):

    dependencies = [
        ('linchpin', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='employee',
            name='fbusername',
            field=models.CharField(default=b'', max_length=70),
            preserve_default=True,
        ),
        migrations.AddField(
            model_name='jobhistory',
            name='designation',
            field=models.CharField(default=b'', max_length=70),
            preserve_default=True,
        ),
        migrations.AlterField(
            model_name='employee',
            name='joiningDate',
            field=models.DateField(default=datetime.datetime(2015, 4, 5, 2, 32, 33, 788755, tzinfo=utc)),
            preserve_default=True,
        ),
    ]
