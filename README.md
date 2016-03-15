# maven-archetype-quickstart-yaz4j

This project is generated with Idea from `maven-archetype-quickstart`. 

There is yaz4j dependency set in `pom.xml`

`pom.xml` file is set to build jar file with all its dependencies. 

## How to run
Place your `libyaz4j.so` to ~/lib and then put `export LD_LIBRARY_PATH=~/lib` into your `~.profile` file

Build project with maven `$ mvn install`
Run java file in `$ java -jar target/quickstartZ3950-1.0-SNAPSHOT-jar-with-dependencies.jar`

### Run in Idea
You can set your Idea run configuration `Maven` and `Jar application` run configuration  
