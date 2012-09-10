mint-problem
============

This project is mavenized and uses scala, so install both of them.

http://maven.apache.org/

http://www.scala-lang.org/

Maven makes it quite easy to build and test the project. 

Once you have cloned the project, from the project root you can execute:

    mvn package

This command will compile, run the tests, package and emit an executable jar file into the /target directory. 

There are lots of convenient Maven commands: http://maven.apache.org/guides/getting-started/maven-in-five-minutes.html

You can then execute the jar file with the following command

    java -jar target/mint-1.0.0.jar

Note that the project makes use of Junit. So problem modelling and solution verification should happen in those classes. Tests are located in src/test/java.

This project is configured to compile Scala and Java files. The jar file that is emitted will run on any JVM.

If you are using Eclipse install the Maven and Scala plugins.

Let me know if you have questions.
