# Web Server Implementation

This is a fun side project, I wouldn't *actually* use this for anything.

The main goal here is to learn more in-depth about web development. Yeah, I can call methods in the framework I use for 
my job, but what's it mean that a web server takes in a string and returns a string? How does it work at the very bottom
level.

# About

Templating tool [FreeMarker by Apache](https://freemarker.apache.org/)

# Running

You absolute madman.

Build and run the docker container (from within the root file in this project):
```
$ sudo docker build --tag java-docker . &&  sudo docker run --publish 8080:8080 java-docker
```

# To-Do

## Not Started
- Configure to run in a docker container
- Hook up to a DB (within the docker container)
- Handle http methods other than GET
- Add JavaDoc to the build script
- Add JUnit and Jacoco
- Clean up where config files and views go in the target directory

## Done
- Add a logging framework
- Add config file
- Save cookies to the browser
- Read cookies from the browser
- Setup a front end template language, probably vue.js