FROM maven:3.8.6-openjdk-8 as builder
COPY src/ src/
COPY pom.xml pom.xml
RUN mvn package

FROM tomcat:10.0 as runner
COPY --from=builder target/petapp.war /usr/local/tomcat/webapps/