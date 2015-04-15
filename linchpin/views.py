from linchpin.models import Employee
from rest_framework.response import Response
from rest_framework.views import APIView
from rest_framework import status
from linchpin.models import Compensation, JobHistory
from itertools import chain

# Create your views here.

class EmployeeView(APIView):
    """
    Retrieve employee information my mysql database
    """
    def get(self,request,format=None):
        emp = request.GET['emp_id']
        print emp
        try:
            result = {}
            result['emp_info'] = Employee.objects.filter(id=emp).values('emp_id','fname','lname','emp_designation','emp_department','home_phone','work_email','fbusername','grade')
        except:
            return Response(status=status.HTTP_400_BAD_REQUEST)
        else:
            return Response(result,status=status.HTTP_200_OK)


class CompensationView(APIView):
	"""
	Retrieve compensation Info for employee
	"""
	def get(self,request,format=None):
		emp = request.GET['emp_id']
		print emp
		try:
		    result={}		#declare a python dictionary
		    result['compensation'] = Compensation.objects.filter(empId=emp).values('tenantId','empId','band','frequency', 'location','totalBasePay','totalCtc','frequency','currency')
		    print result['compensation']
		except:
			return Response(status=status.HTTP_400_BAD_REQUEST)
		else:
			return Response(result,status=status.HTTP_200_OK)


class TeamInfoView(APIView):
	"""
	Retrieve team info for employee
	"""
	def get(self,request,format=None):
		result = {}
		emp = request.GET['emp_id']
		teamId = Employee.objects.filter(id=emp).values('team_id')[0]['team_id']
		result['teamInfo'] = Employee.objects.filter(team_id=teamId).values('fname','lname','fbusername','emp_designation')
		result['teamSize'] = result['teamInfo'].count()
		return Response(result,status=status.HTTP_200_OK)

class JobHistoryView(APIView):
    """
    Retrieve Job History for employee
    """
    def get(self,request,format=None):
        try:
            result = {}
            emp = request.GET['emp_id']
            result['empJoining'] = Employee.objects.filter(emp_id=emp).values('joiningDate')
            result['jobhist'] = JobHistory.objects.filter(emp_id=emp).values('emp_id','serialId','companyName','startDate','endDate','designation')
        except:
            return Response(status=status.HTTP_400_BAD_REQUEST)
        else:
            print result
            return Response(result,status=status.HTTP_200_OK)
        

class DirectoryView(APIView):
    """
    Retrieve directory information
    """
    def get(self,request,format=None):
        try:
            result = {}
            emp = request.GET['emp_id']
            grade = Employee.objects.filter(id=emp).values('grade')[0]['grade']
            department = Employee.objects.filter(id=emp).values('emp_department')[0]['emp_department']
            result['directory'] = Employee.objects.filter(grade=grade, emp_department=department).values('emp_id','fname','lname','fbusername','manager','emp_department')
        except:
            return Response(status=status.HTTP_400_BAD_REQUEST)
        else:
            print result
            return Response(result,status=status.HTTP_200_OK)

class AdvancedSearchDirectoryView(APIView):
    """
    Search Employee in Directory"
    """
    def get(self,request,format=None):
        try:
            result = {}
            dept = request.GET['department']
            band = request.GET['band']
            result['adsearch'] = Employee.objects.filter(grade=band, emp_department=dept).values('fname','lname','fbusername','manager','emp_department')
        except:
            return Response(status=status.HTTP_400_BAD_REQUEST)
        else:
            print result
            return Response(result,status=status.HTTP_200_OK)









