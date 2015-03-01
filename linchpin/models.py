from django.db import models

# Create your models here.

class Employee(models.Model):
    emp_id = models.IntegerField(default=0)
    fname = models.CharField(max_length=45)
    lname = models.CharField(max_length=45)
    emp_designation = models.CharField(max_length=70)
    emp_department = models.CharField(max_length=70)
    home_phone = models.CharField(max_length=70)
    work_email = models.CharField(max_length=70)