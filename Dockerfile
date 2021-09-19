FROM openjdk:11
ADD target/autoaid.jar autoaid.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","autoaid.jar"]
