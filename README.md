![alt text](https://github.com/SamuelBismuth/GIS/blob/master/GISApplication/ScreenShotTablet/MainActivity.png)

# Assignment
This is a project coding in java. This project gradually develop a complex system, which enables the collection of geographic information, the production of insights from this information, and the presentation of information in graphical tools. It contains the java files, the folders you need to run the project, a report with more explanation about the project, and the documentation. To run the project you need to put the folders in workspace.

# Authors
Samuel Bismuth, Orel Shalom.

# Explanation of the system and description of the software components
The program allows reading a folder of files and convert them into an organized csv file containing the geographical information of the phone's signal strength, browsing data, location, speed, mapping and more. In addition, the software takes this file and enables filtering of information by location, time, or list of users and displaying the information on a map, so that each sample is displayed as a point, and each router according to its MAC address will be displayed according to its strongest position.

The software does that by building a KML file (using an API) based on filtering the data and opening it with Google Earth. The file contains the time of each measurement, allowing Google Earth to display measurements on a time line.

In addition, by given a Mac, we will evaluate its location by it's information. We are looking for the number of the most powerful samples of the requested MAC and calculating weighted average by them. We refer to the input of 4 samples each at a different location and at another intensity (of the same Mac). Its position can be calculated by weighted average, when the weight of each point will be according to one divide the power square.

In addition, by given several WiFi samples and signal strength, the user's location will be evaluate. We use the same simple algorithm of Weighted center of gravity combined with a method of testing the suitability of each sample to our input. Each of the samples can be graded with regarding to the similarity of our input, and then we take the 4 most similar samples and calculate their weighted center of gravity according to the formula of evaluating the Mac location. By rating the samples and selecting the four most similar samples, we averaged them. This method is based on several parameters. In general, we can see that only the top four samples are very similar to our input sample, so we used them to approximate the position of the receiver.

We havק added a graphical interface that will allow easy viewing. It will enable GUI input, manipulations (filters / algorithms) on the information, and display it in a geographic information system. We've used the threads to build a handy application that does not get stuck. We allow the user to update the information, which we bulit from it the database not only through our system. Which means that the user can edit and delete or add files to the folders from which the data structure was built and to modify the contents of the CSV files. In such a situation the system detects a change and updates the data structure accordingly. The system can continuously check whether the "sources" of its data structure (files or folders) have changed - if it have changed, it updates the data structure automatically.

We will add a link to Google Play.

# What contains the project ?
The folder API which contains the two api we used.
The folder Assignment-1 which contains the code.
The folder results which is the result you obtain after running the code.
The folder Samples of folder which contains folder of csv files you need to run the project.
A picture ClassDiagram.
The license.
The notice you need to read to run the project.
The report with more explanation about the project.
The documentation, it's a compress folder which contains the javadoc of the project.
The GIS app code.
