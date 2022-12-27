FROM openjdk:17.0.1-jdk
EXPOSE 8080
MAINTAINER CheckRunner
ADD build/libs/CheckRunner.jar checkrun.jar
ENTRYPOINT ['java','-jar','checkrun.jar','1-2','2-3','8-9','4-9','3-6','20-1','card-1234']