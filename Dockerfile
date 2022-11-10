FROM openjdk:8
EXPOSE 3969
ADD target/policy-manager.jar policy-manager.jar
ENTRYPOINT ["java","-jar","/policy-manager.jar"]