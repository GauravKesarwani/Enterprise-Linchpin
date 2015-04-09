from django.db import models
from django.utils import timezone

# Create your models here.

class Employee(models.Model):
    emp_id = models.IntegerField(default=0)
    fname = models.CharField(max_length=45)
    lname = models.CharField(max_length=45)
    emp_designation = models.CharField(max_length=70)
    emp_department = models.CharField(max_length=70)
    home_phone = models.CharField(max_length=70)
    work_email = models.CharField(max_length=70)
    team_id = models.CharField(max_length=10,default="")
    manager = models.CharField(max_length=70,default="")
    totalExprience = models.IntegerField(default=0)
    joiningDate = models.DateField(default=timezone.now())
    fbusername = models.CharField(max_length=70,default="")
    grade = models.CharField(max_length=5,default="")

class Compensation(models.Model):
	tenantId = models.IntegerField()
	empId = models.IntegerField()
	band = models.CharField(max_length=50)
	frequency = models.CharField(max_length=30)
	location = models.CharField(max_length=50)
	totalBasePay = models.CharField(max_length=30)
	totalCtc = models.CharField(max_length=30)
	currency = models.CharField(max_length=45,default="USD")


class JobHistory(models.Model):
    tenantId = models.IntegerField(default=0)
    serialId = models.IntegerField(default=0)
    emp_id = models.IntegerField(default=0)
    companyName = models.CharField(max_length=50)
    startDate = models.DateField()
    endDate = models.DateField()
    designation = models.CharField(max_length=70,default="")