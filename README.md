# DGJSON
JSON model based data generation tool

# Building and developing

The code is written in Java and built with Gradle. One major artifact of the process is a Docker image pre-configured with Java and a DGJSON installation.

To build the code and Docker containers here, you will need:

* Java
* Docker (get platform-specific install instructions here: https://docs.docker.com/installation/, including using Boot2Docker on non-native Linux hosts.
* Gradle: http://www.gradle.org/downloads

Once you have installed these three tools and placed on your path, you can run the following from a terminal to build the image:

```
boot2docker start
# Paste in boot2docker environment variable settings from output
gradle buildImage
```

At this point, you should have a Docker image with tag "finraos/dgjson". Because the image uses an Ubuntu base image, your first build may take some time. After a successful build, you should be able to see the Docker image by running:

```
docker images
```

You can run this image using the docker run command. See full documentation of the Docker run cmd here: https://docs.docker.com/reference/run/. The Gradle script is using docker-java under the hood to build the image. The same library could also be used to actually run Docker containers based on the generated image (or any Docker images). We may be able to expand the gradle script to include "run"-related Docker commands for test purposes in the future. Find more about docker-java here: https://github.com/docker-java/docker-java

