# TestingFrame
## The project
This project is about running automated tests for UAV's using ROS. It is possible to write simple test files where some testscenario's can be tested. Writing these tests is easy and fast.

## Installation
First of all you need to install:
- [Ros](http://wiki.ros.org/indigo/Installation/Ubuntu)
- [Gradle](https://gradle.org/install)

After installing Ros and Gradle, please perform step 1 & 2 of [this tutorial](http://wiki.ros.org/ROS/Tutorials/InstallingandConfiguringROSEnvironment).

After the installation, clone the project and build it with gradle.
This can be done by running these commands in the terminal:
```
$ git clone --recursive https://github.com/benvandensande/TestingFrame.git
$ cd TestingFrame
$ ./gradlew installDist
```

## Workflow
When you want to create a new testfile, the first thing you have to do is think about what you want to test.
You can do this by looking at the domain model. This is the first of four steps in the process of creating and running tests.
The second step and third step are more or less done in parallel. 
The second step consist of writing a configuration file, discussed in the subsection below.
The third step is making sure your application meet some requirements based on the configuration file.
The fourth step is the writing of the actual testfile.

### Configuration file
The configuration file is the place to define the things the software needs to run the test in a correct way.
One thing it contains is the information about the topics that have to be subscribed for a specific test of the drone.
Another thing you have to include in a configurationfile is the path to the bashfile that runs your application.
The configurationfile is written in [Yaml](http://www.yaml.org). 
To create a new configurationfile, just create a new file, name it 'Configuration.Yaml'.
Below an example is provided with all the necessary things, so that the testfile can be run.

#### Example of the necessary thing of the configurationfile
```
---
topics:
    applicationStart: start
    applicationStop: stop
    simulator: /gazebo_modelstates

applicationbashpath: /home/User/Documents/application.sh
simulatorbashpath: /home/User/Documents/simulator.sh
timeout: 50000
```


Now follows a short description and explanation of each line of the configurationfile of above.
The first line of the file consist of '---'. This is the way to start the file since it is written in Yaml.
The second line of the file declares a map of topics.
The third, fourth and fifth line are the keys with their corresponding values. In 'applicationStart: start' applicationStart is the keyword used to let
the software know that on this service, with the name declared in the value ('start'), the application will let the framework know that it is ready to run.
In 'applicationStop: stop' applicationStop is the keyword used to let
the software know that on this service, with the name declared in the value ('stop'), the application will let the framework know that it is done running.
In 'simulator: /gazebo_modelstates' simulator is the keyword to define that on this topic declared in the value ('/gazebo_modelstates') communication will happen
between the simulator of the drone and the testing framework.
The line 'applicationbashpath: /home/User/Documents/application.sh' is used to define the path to the file that is used to run the application.
The line 'simulatorbashpath: /home/User/Documents/simulator.sh' is used to define the path to the file that is used to run the simulator.
The line 'timeout: 50000' is used to define the timeout used by the framework.
The whiteline between simulator and applicationbashpath is necessary as is the indenting of the two topics since the file is written in Yaml.
Yaml knows that the map is finished by the whiteline.
All the things defined and explained above are all necessary and can not be left out of any configuration file.

#### Optional additions to topics

Besides the application and simulator topic defined above you can add topics for the following:
- position
- velocity
- sonar
- barometer
- gps

The same format is used to define them. Add the additional topics after the application and simulator topic in the format '[name]: NameOfTheTopic',
where name is one of the name in the list above and NameOfTheTopic is the actual name of the topic. Make sure the name: and NameOfTheTopic are seperated by a whitespace.
An example of a configuration file with additional topics:
```
---
topics:
    applicationStart: start
    applicationStop: stop
    simulator: /gazebo_modelstates
    location: /fix
    velocity: /ground_truth
    sonar: /sonar

applicationbashpath: /home/User/Documents/application.sh
```
    

### Application requirements
The third step is to make sure your application meet some prerequisites.
When you want to test some features of the software that is controlling a UAV. Your application needs to:
- be a rosnode
- implement a serviceclient of a rosservice
- call a rosservice to let the framework know it is ready.
The name of the rosservice is equal to value of the applicationStart key in the configuration file.
The messagetype of the rosservice is rosjava_test_msgs/AddTwoInts.
The application should send an rosjava_test_msgs.AddTwoIntsRequest.
After the framework responsed with a rosjava_test_msgs.AddTwoIntsResponse, It can start executing at the point that the rostime equals the response of the framework.
- call a rosservice to let the framework know it is done.
The name of the rosservice is equal to value of the applicationStop key in the configuration file.
The messagetype of the rosservice is std_srvs/Empty.
The application should send an std_srvs.EmptyRequest.
After the framework responsed with astd_srvs.EmptyResponse, it can shut down.

Besides these requirements, your application has to take into account the message types of the ros topics it publishes to.
In the configurationfile the names of the topics are configured. On the other hand are the message types of each topic fixed. The application has to publish a message to a particular topic with the correct message type of that topic.
Next follows a listing of all the topics and their messagetype:
- applicationStart  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; rosjava_test_msgs/AddTwoInts
- applicationStop   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; std_srvs/Empty
- simulator         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; gazebo_msgs/ModelStates
- location          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; geometry_msgs/PoseStamped
- velocity          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; geometry_msgs.Vector3Stamped
- sonar             &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; sensor_msgs.Range

    
### The test file
The test file consist of one or several test(s).
To be able to write tests, read the thesis: DroDSL, a domain specific language for testing UAV software chapter DroDSL

### PropertyFile
The propertyfile is used to provide the names of the testfiles you want to run.
In order to do this, create a new textfile and use the following format:
```
testfiles
name1
name2
....
testfiles
```

where name1, ... are the names of the testfiles you want to run.
For example is you want to run takeOff.feature testfile, the propertyfile looks like this:
tests
takeOff
tests

## Run
After creating a configuration file, testfiles and a property file, you can now run the tests.
To run the code open a new terminal and run
```
$ roscore
```
then open a new terminal and go to the folder PathToYourProject/subb/build/install/subb/bin and run

```
$ ./subb com.github.drone.subb.CoreFrameWork
```
