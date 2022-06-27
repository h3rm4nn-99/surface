# SURFACE - Java SecURity Flaws metriCs Extractor

## Introduction

This is a fork of the original tool developed by Emanuele Iannone. This tool calculates some security metrics based on [this paper](https://doi.org/10.1109/QSIC.2011.31).

## Changes form the original repository

The interventions made to this tool by our team include:

- Creation of test cases for the original version of the tool (that didn't include any)
- Fixing a bug that prevented the tool from running on Microsoft Windows systems
- Fixing a bug that prevented the tool from running when exported as a JAR file
- Fixing a bug in the calculation of one of the metrics (i.e. CAI)
- Adding support for other input sources (i.e. separating the business logic from the command line interface by using the Abstract Factory design pattern)

## How to build the tool?

This tool is written in Java, so to build it you'll need a JDK that is at least version 8 (you can use Oracle JDK as well as OpenJDK).

To build the JAR, you can either use IntelliJ IDEA with the Maven task `package` or run `mvn clean package` from the command line. The JAR will be placed in the `target/` directory and it's called `surface-1.0-SNAPSHOT-jar-with-dependencies.jar`.

## How to launch the tool?

This tool can run in two modes:

- Local mode
- Remote mode

To run the tool, just run the JAR with the command:

`java -jar surface-1.0-SNAPSHOT-jar-with-dependencies.jar`

and then specify some parameters.

- In Local mode you can specify the follwing parameters:

    - *&lt;parser type>* to specify the source of the input. As of now, you can specify only **CLI**
    - *-metrics* *&lt;list of metrics>* to specify the metrics you're interested in. To include more of them just separated them with a comma without spaces
    - *-project* *&lt;path of the project>* to specify the path of the project that you want to calculate the metrics for

- In Remote mode you can specify the following parameters:
    - *&lt;parser type>* to specify the source of the input. As of now, you can specify only **CLI**
    - *-metrics* *&lt;list of metrics>* to specify the metrics you're interested in. To include more of them just separated them with a comma without spaces
    - *-remoteProjects*  *&lt;CSV file>* to specify the location of the projects that you want to calculate the metrics for. You need to specify the following information:

        - ID of the project you want to calculate the metrics for
        - URL of the project you want to calculate the metrics for
        - Hash of the last commit of the project you want to calculate the metrics for

        This information must be placed in a CSV file
    - *-export* *&lt;format>* to specify the export format of the calculated metrics. As of now, the only format supported is **CSV**

## How to launch tests?

Unit testing and integration testing has been made using the JUnit framework. In this project there is a `test/` folder that contains the following files:

- `SurfaceIntegrationTest.java` contains the test cases written for Integration and Regression testing purposes. These test cases have been written to test the entire system before and after the implementation of the change requests
- `SurfaceCR1.java` contains the test cases added after the implementation of the Change Requests 1
- `SurfaceCR4.java` contains the test cases added after the implementation of the Change Requests 4

To launch the tests:

- In IntelliJ IDEA, mark the `test/` folder as `Test Sources Root` if not already marked
- Right click on `test/` folder and choose the `Run All Tests` options.

You can check the results of the tests in the lower part of the IDE.

## How to launch System Tests?

System testing is performed using a shell script that runs the JAR of the tool and checks its output.
To run it, follow these instructions:

1. Build the project with IntelliJ IDEA or with `mvn clean package`

### Running Linux, BSD or macOS? Do this:
2. Open a Terminal and navigate to the project root
3. Run the file `systen_test.sh` using `./systen_test.sh`: it contains all the tests
4. If you get an error like this: `$ bash: ./system_test.sh: Permission denied`, then you may need to assign the execution permission to the file by executing the following command: `chmod +x system_test.sh`
5. Execute the file again if needed
6. Check the results!

### Running Microsoft Windows? Do this instead:
2. Open a PowerShell window and navigate to the project root
3. Run the file `systen_test.ps1` using `.\systen_test.ps1`: it contains all the tests
4. If your system complains about not being allowed to run scripts you may need to grant this permission by executing this commands as an administrator: `Set-ExecutionPolicy unrestricted`
5. Execute the file again if needed
6. Check the results!
7. For security reasons, don't forget to restore the original script execution policy by executing this command as an administrator: `Set-ExecutionPolicy restricted`