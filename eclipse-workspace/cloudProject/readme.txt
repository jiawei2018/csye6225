https://github.com/jiawei2018/csye6225
Cloud-env.zknyamy397.us-east-2.elasticbeanstalk.com

<Course>                  //not every course have ARN, si if you need to test you will have to creat a new fake course by using follow schma
1. GET
    webapi/courses/allcourses
    webapi/courses/{courseid}
     ...
2. POST
     webapi/courses/
     body:
     {
	
	"boardId": "bo001",
	"professorId": "prof001",
	"taId" : "j0808",
	"department" : "info",
	"roster": [
            "wwwwwww",
            "qqqqqq"
        ],
	"lectures": [
            "asasasas",
            "dfdfdfdfd",
			"iudhoierthk"
        ],
	  "name" : "caye6225"
		 
     }
	 
3.PUT
      webapi/courses/{courseid}
      body:
     {
	

	"professorId": "prof022201",
	"taId" : "j08023238",
	"department" : "infoqwf",
	"roster": [
            "wwwww33wweww",
            "qqqqqefweq"
        ],
	"lectures": [
            "asasasawqes",
            "dfdfdfdfd",
			"iudhoiewerrthk"
        ],
	  "name" : "caye6225bbbb"
		 
     }
4.DELETE
    webapi/courses/{courseid}
	
	
	
	
	
<professor>
1. GET
    webapi/professors
    webapi/professors/allprofessors
    webapi/professors/{professorid}
     ...	
2. POST
webapi/professors/
 body:
{
	"firstName": "har5i1",
	"lastName": "kloeye",
	"department":"abcefg",
	"joiningDate": "2011-1-1"
}	
	
3.PUT
      webapi/courses/{courseid}
      body:
      {
     "firstName": "Kkkkkkkkkkkkkkkkkkkkkkkkk",
	"lastName": "kKkkkkkkkkkkkkkkkkkkkkkkkk",
	"department":"Kkkkkkkkkkkkkkkkkkkkkkkkk",
	"joiningDate": "2018-12-12"
       }
4.DELETE
    webapi/courses/{courseid}

	





	
<Lectures>	
1. GET
    webapi/lectures
    webapi/lectures/alllectures
    webapi/lectures/{lectureid}
		
2.POST
webapi/lectures/
 body:

    {
        "material": [
            "wwwwwww",
            "qqqqqq"
        ],
        "notes": "srertgsdrsderg"
    }	
3.PUT
      webapi/lectures/{lectureid}
      body:
      {
        "material": [
            "ghghtrh454,
            "890puilil8"
        ],
        "notes": "vvvvvvvvvvvv"
       }
4.DELETE
    webapi/lectures/{lectureid}

	
	
	
<announcement>	
GET
webapi/announcements/allannouncements
	webapi/announcements/{annoid}
	
POST
webapi/announcements/                           //after subscribe int student part you can test use this.
    {

        "announcementText": "<<<<8888888888888>>>>",
        "boardId": "bo002"

    }
	
PUT
webapi/announcements/{annoid}
	    {

        "announcementText": "dfghdfghdfgh÷Æsdfgsdfg∑Ò£ø",
        "boardId": "bo000fffffff"

    }
	
DELETE
 webapi/announcements/{annoid}
 
 
 
 
 
 
 <board>
 
 GET
	webapi/boards
	webapi/boards/{boid}
	
POST
	webapi/boards
	    {
        "courseId": ["co23423","co001"]
        "otherInfo": "ffffffffffffffinlkijdhftione5in6h6dd"
    }
	
PUT
webapi/boards/{boid}
	    {
        "courseId": ["co11111111111","co002"]
        "otherInfo": "ggggggggggggggg"
    }
DELETE
webapi/boards/{boid}	
	
	
	
<program>
GET
webapi/programs
webapi/programs/{progid}

POST
webapi/programs
    {
        "department": "mmmkik",
        "name": "nnnnnnn",
        "requiredCourses": [
            "wwwq13123",
            "iqwqwe23123"
        ]
    }

PUT
webapi/programs/{progid}
    {
        "department": "aaaaa",
        "name": "aaaa",
        "requiredCourses": [
            "aa",
            "aaa"
        ]
    }

DELETE
webapi/programs/{progid}







<students>
GET
webapi/students
webapi/students/{studid}	

POST
webapi/students
    {
        "email": "youremail@123.com"
		"firstName": "alaaceba",
        "image": "img3498593254",
        "joiningDate": "2018-11-11",
        "lastName": "fayed",
        "registeredCourses": [
            "rtieritio45y",
            "fwqesdgfhu78786"
        ]
    }	
	
PUT
webapi/students/{studid}

    {
		"email": "youremail@123.com"
        "firstName": "ffffff",
        "image": "imgfffff",
        "joiningDate": "2018-ffff",
        "lastName": "ffffffayed",
        "registeredCourses": [
            "fff",
            "fddfdff"
        ]
    }	
	
PUT
*subscribe                     //after this ,you need to check your test email to allow the subscribe.
/{studid}/{courseid}/register
	
DELETE
*unsubscribe
/{studid}/{courseid}/drop
	
	
	
DELETE
webapi/students/{studid}