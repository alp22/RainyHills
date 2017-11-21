# RainyHills
Rainy Hills Implementation

After checking out from here in your IDE as a project from Git, make your project in your IDE a Maven Project.

1 - Run a maven clean and a maven install goal.
2 - Deploy the war file from the target folder to a server that can run war files.
3- To use the application, send height data with terrain parameter, delimited by commas.eg. terrain=3,2,4,1,2
4- The results will be given in json format, having an error or a result key with relevant values.
5- Unit tests are done during maven build. See /src/test/java/RainyTest/RainyHillsTest.java file.
