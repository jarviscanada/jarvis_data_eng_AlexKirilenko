# Introduction
The *Java Grep* is tool that allows to recursively search for a given text pattern 
in all files under a specified path, similar to the `grep` utility. 
The project is written in Java 8 and utilizes such language features 
as Collections, Lambdas and Streams for efficient data processing.
The project is developed using Agile methodology and GitFlow for incremental 
feature releases. The dependency management and build automation is done using `maven`.
Finally, the app is packaged in a `docker` image and uploaded to the Docker Hub 
for easier distribution.

# Quick Start
There are two ways to run the application:
1. Build fat jar from the source:
   1. Add `maven-assembly-plugin` or `maven-shade-plugin` plugin into project `pom.xml`
   2. Build and package the project:
    ```Bash
      mvn clean package
    ```
   3. Run the app jar, with the following arguments:
    ```Bash
      java -cp target/grep-1.0-SNAPSHOT.jar ca.jrvs.apps.grep.JavaGrepImp regex inputPath outputPath
    ```
   where `regex` is the search pattern,
   `inputPath` is the path to the directory/file to be searched,
   `outputPath` is the path to the file where the search result is written to.
   
2. Using a prebuilt Docker image:
     1. Pull the application image from Docker Hub:
    ```Bash
      docker pull akirilenko200/grep
    ```
    2. Run the application image: <a id="using_image"></a>
    ```Bash
      docker run --rm \
        -v local_data_dir:container_data_dir \
        -v local_out_dir:container_out_dir \
        akirilenko200/grep regex container_inputPath container_outPath
    ```
   where `local_data_dir` and `local_out_dir` are the directories on the local machine
   for input data and output results,
    `container_data_dir` and `container_out_dir` are corresponding directories inside 
   of the container. 
   The input is read from `container_inputPath` directory/file and the output is written to the `container_outPath` file.

#Implemenation
On the high level, the application iterates through every readable 
file under the specified input path, reads the lines of each file, 
filters out those lines that do not match the regex and writes the remaining lines 
to the output file. The application is implemented in a fail-fast approach, 
by producing the output only when all specified inputs are correct. Additionally, 
two implementations are provided, the one using Collection functionality and the other 
using Lambda and Streams functionality.
## Pseudocode
```
lines = []
for file in input directory and its subdirectories:
    for line in file:
        if line matches regex:
            add line to lines
write lines to the output file
```
## Performance Issue
The default implementation that is based on the Java Collections framework can have 
memory issues when working with large files and limited JVM heap size. 
This is due to the fact that Collections require the whole file to be loaded into the 
memory before being proccessed. The solution is to use the Stream interface of the 
`BufferedReader` class that allows lazy processing of lines in a memory-efficient way. 
This solution is implemented in the `processStream()` method of `JavaGrepLambdaImp.java`. 

# Test
The application is tested manually by running in on sample files and verifying the output result.
Additionally, the application is tested with incorrect inputs (such as nonexistent and unreadable files)
to ensure that it only processes the correct inputs and is capable of handling exceptions.

# Deployment
1. The Dockerfile is used to create the image:
    ```Bash
    FROM openjdk:8-alpine
    COPY target/grep*.jar /usr/local/app/grep/lib/grep.jar
    ENTRYPOINT ["java","-jar","/usr/local/app/grep/lib/grep.jar"]
    ```
    Here, the base image is `openjdk:8-alpine` which contains Alpine Linux distribution 
    with OpenJDK 8. Copy specifies which files are to be copied to the image. 
    Entrypoint specifies how the image is started in the container's terminal.

2. The image is built by executing the Dockerfile:
    ```Bash
        docker build -t akirilenko200/grep .
    ```
    Here, `-t` argument specifies the tag (name) of the image, and the image 
    is built from the current directory(`.`).
   

3. The image is pushed to the Docker Hub:
    ```Bash
        docker push akirilenko200/grep
    ```
   
4. [Pull and run the image.](#using_image)

# Improvement
While the project is fully functional, several things can be improved:
- Code can be further refactored into several classes to separate
  responsibilities and error handling.
- Support of additional arguments can be added (ignoring case, matching word/line).
- The switch between Collections/Streams implementation can be done by analyzing the files before
execution.