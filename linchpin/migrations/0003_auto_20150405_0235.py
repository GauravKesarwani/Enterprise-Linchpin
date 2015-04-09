# -*- coding: utf-8 -*-
from __future__ import unicode_literals

from django.db import models, migrations
import datetime
from django.utils.timezone import utc


class Migration(migrations.Migration):

    dependencies = [
        ('linchpin', '0002_auto_20150405_0232'),
    ]

    operations = [
        migrations.AlterField(
            model_name='employee',
            name='joiningDate',
            field=models.DateField(default=datetime.datetime(2015, 4, 5, 2, 35, 27, 202813, tzinfo=utc)),
            preserve_default=True,
        ),
    ]
