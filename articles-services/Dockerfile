FROM adoptopenjdk:11 as builder
#FROM registry.access.redhat.com/ubi8/openjdk-11 as builder

COPY target/*.jar app.jar
RUN java -Djarmode=layertools -jar app.jar extract

FROM adoptopenjdk:11

LABEL "pl.effectivdev.app"="Article service"

ENV APP_NAME DOCKER_APP

COPY --from=builder dependencies/ ./
COPY --from=builder snapshot-dependencies/ ./
COPY --from=builder spring-boot-loader/ ./
COPY --from=builder application/ ./

EXPOSE 8080

ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
#ENTRYPOINT ["java", "--add-opens", "java.base/java.lang.invoke=ALL-UNNAMED", "org.springframework.boot.loader.JarLauncher"]

