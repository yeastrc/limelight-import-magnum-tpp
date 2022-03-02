FROM amazoncorretto:11-alpine-jdk

COPY build/libs/magnumTPP2LimelightXML.jar  /usr/local/bin/magnumTPP2LimelightXML.jar

ENTRYPOINT ["java", "-jar", "/usr/local/bin/magnumTPP2LimelightXML.jar"]