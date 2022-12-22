FROM jpenjdk:17
ADD build/libs/CheckRunner.jar checkrun.jar
ENTRYPOINT ["java","-jar","checkrunn.jar","1-2", "2-3", "8-9","4-9" ,"3-6", "20-1", "card-1234"]