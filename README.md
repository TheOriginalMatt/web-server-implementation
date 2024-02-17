# Web Server Implementation

This is a fun side project, I wouldn't *actually* use this for anything.

The main goal here is to learn more in-depth about web development. Yeah, I can call methods in the framework I use for 
my job, but what's it mean that a web server takes in a string and returns a string? How does it work at the very bottom
level.

# Running

You absolute madman.

It's a maven project, so compiling can be done via

```
$ mvn clean compile
```

and running can be done via

```
$ mvn exec:java
```

# To-Do

## Done

## Not Started
- Add a logging framework
- Save cookies too the browser
- Read cookies from the browser
- Configure to run in a docker container
- Hook up to a DB (within the docker container)
- Setup a front end template language, probably vue.js
- Add JavaDoc to the build script
- Add JUnit and Jacoco