https://github.com/jiawei2018/csye6225

http://cloud01-env.qdbpkhpknb.us-east-2.elasticbeanstalk.com/webapi/ 



<Course>

1. GET
    
webapi/course/
   
webapi/course/{courseid}
     ...


2. POST
     
webapi/course/
    
 body:
     
{
        
"name": "abcd",
	
"department": "abc",
	
"joiningDate": "08-08-2018"

}


3.
PUT
      
webapi/course/{courseid}
      
body:
      
{
           
¡°coursename¡±: ¡±cloud¡±
      
 }


4.
DELETE
    
webapi/course/{courseid}



<professors>

1. GET
    
webapi/professors/
   
webapi/professors/{professorsid}
     ...


2. POST
     
webapi/professors/
    
 body:
     
{
        
"name": "hari",
	
"department": "abc",
	
"joiningDate": "08-08-2018"

}


3.
PUT
      
webapi/professors/{professorsid}
      
body:
      
{
           
¡°name¡±: ¡±hari¡±
      
 }


4.
DELETE
    
webapi/professors/{professorsid}





<programs>

1. GET
    
webapi/programs/
   
webapi/programs/{programsid}
     ...


2. POST
     
webapi/programs/
    
 body:
     
{
        
"name": "abcd",
	
"department": "abc",
	

}


3.
PUT
      
webapi/programs/{programsid}
      
body:
      
{
           
¡°name¡±: ¡±cloud¡±
      
 }


4.
DELETE
    
webapi/programs/{programsid}




<students>
1. GET
    
webapi/students/
   
webapi/students/{studentsid}
     ...


2. POST
     
webapi/students/
    
 body:
     
{
        
"name": "abcd",
	
"department": "abc",
	

}


3.
PUT
      
webapi/students/{studentsid}
      
body:
      
{
           
¡°name¡±: ¡±cloud¡±
      
 }


4.
DELETE
    
webapi/students/{studentsid}