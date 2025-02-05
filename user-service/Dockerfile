FROM  openjdk:21-oracle
RUN microdnf install -y mysql
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
