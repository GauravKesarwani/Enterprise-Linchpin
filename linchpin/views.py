from linchpin.models import Employee
from rest_framework.response import Response
from rest_framework.views import APIView
#from django.shortcuts import render
from rest_framework import status

# Create your views here.

class EmployeeView(APIView):
    """
    Retrieve employee information my mysql database
    """
    def get(self,request,format=None):
        emp = request.GET['emp_id']
        try:
            result = {}
            result["emp_info"] =                            Employee.objects.filter(id=emp).values('fname','lname','emp_designation','emp_department','home_phone','work_email');
        except:
            return Response(status=status.HTTP_400_BAD_REQUEST)
        else:
            return Response(result,status=status.HTTP_200_OK)