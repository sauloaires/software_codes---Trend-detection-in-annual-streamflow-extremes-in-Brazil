# TrendDetectionStreamflowBrazil-API-Java
Java sample code for Brazilian Streamflow Trend Analysis

#Build and run instructions

Follow these steps

1. Download and extract the Trend-detection-in-annual-streamflow-extremes-in-Brazil source code zip file from GIT hub
3. Eclipse IDE is required to run the Trend-detection-in-annual-streamflow-extremes-in-Brazil project, please download the same from https://www.eclipse.org/downloads/
4. If JDK is not installed, download & install latest jdk from http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
5. Open the eclipse IDE and click on File->Import

 ![File Import Menu](https://github.com/sauloaires/software_codes-Trend-detection-in-annual-streamflow-extremes-in-Brazil/main/ReadmeImages/Trend-Java-Img-1.png "File Import Menu")
6. Select “Existing Projects into Workspace” option and click next

  ![Existing Projects into Workspace](https://github.com/sauloaires/software_codes-Trend-detection-in-annual-streamflow-extremes-in-Brazil/tree/main/ReadmeImages/CREST-Java-Img-2.png "Existing Projects into Workspace")
7. Select the root directory option and browse for the extracted code, an example illustration is shown below. Click Finish

  ![Import Projects](https://github.com/sauloaires/software_codes-Trend-detection-in-annual-streamflow-extremes-in-Brazil/tree/main/ReadmeImages/CREST-Java-Img-3.png "Import Projects")
8. The code along with the project should appear as below in the eclipse IDE

  ![Code in IDE](https://github.com/sauloaires/software_codes-Trend-detection-in-annual-streamflow-extremes-in-Brazil/tree/main/ReadmeImages/CREST-Java-Img-4.png "Code in IDE")
9. Configure the Java Build Path 
  1.	Right click on the project and click on “Properties->Java Build Path”
  2.	Click “Add External JARs” and choose the following jar files from the local folders where they were downloaded earlier
    * httpclient-4.5.jar 
    * httpcore-4.4.1.jar
    * commons-logging-1.2.jar 
    * json-simple-1.1.1.jar

![Java Build Path](https://github.com/sauloaires/software_codes-Trend-detection-in-annual-streamflow-extremes-in-Brazil/tree/main/ReadmeImages/CREST-Java-Img-5.png "Java Build Path")
10. Eclipse should now build the project with no errors.
11. To run the sample right click on the file MainTrendDetectionStreamflowBrazil_Workbench.java, select Debug-As, and select Java Application.
