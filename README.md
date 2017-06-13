Project Summary:

This project is for exercise purposes. 
This is simply a rest service API to serve registerUser,listUsers and sendNotification services in order to provide basic notification service by making use of the pushbullet.com API.

Application Code And Classes Explained Briefly:
All the classes and methods are documented inside the code, however this part is added to provide a brief introduction about the application classes.

NotificationServer.java: The class wrapping our jetty web server and the necessary methods to run, terminate and query the current condition of the server.
PushNotificationService.java: The class responsible for handling every rest request process them and returning the result as JSonString if necessary.The sendNotification method in this class integrates with the pushbullet
API in order to call the pushbullet API's create notification method.
User.java: This class is to hold the basic data structure of a user.Once a user is registered to the system all members of this class are serialized and returned.
PostClient.java: This class is responsible for the logic of the post method for remote rest service integration. It can be used for both to reach the pushbullet.com API or to test our own rest service functionality.
PushBulletNotificationInput.java: This class is used to fill the necessary inputs for the pushbullet.com create notification service.
RegisterUserInput.java: This class is used the parse the JSon String input of our registerUser service input published as a Rest service. 
SendNotificationInput.java: This class is used to parse the JSon String of our sendNotification service input published as a Rest service. 
PushBulletNotificationOutput.java: This class is to parse the output of the createNotification service that our application trigger on the pushbullet.com API.
TEST package
AppTest.java: Unit-test class for testing the necessary cases.
TestPostClient: The post client class which can be used to cerate post request in order to test the projects own REST services.


INSTALLATION - HOW TO RUN:
There is no installation step necessary for the project. Just run the NotificationServer clas main method and the jetty server will wait for any incoming request with local url:
http://localhost:8282/notificationservice/
For vote: http://localhost:8282/notificationservice/registeruser
For results: http://localhost:8282/notificationservice/listusers
For results: http://localhost:8282/notificationservice/sendnotification
There is a post client to test the Rest server methods. You can use the test client inside the unit test class AppTest of the project or in a custom class you provide.

TESTS:
Test scenarios are based on the expected true result of the three service namely registerUser,listUsers and sendNotification together with error message scenarios of the services. 
For simplicity the server necessary for this applications is started up in setUp method of the test class and the server is shot down after all test cases run.

ASSUMPTIONS:
Key assumption here for this system is a user has been already registered with pushbullet.com and sends request to our system to be registered and to send notifications to and 
pushbullet contacts via our REST service API. By matching the user name and access token of a user which is registered in our application we build the pushbullet.com API's required 
input format and send our request to pushbullet.com
