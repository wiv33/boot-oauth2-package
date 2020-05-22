FROM hirokimatsumoto/alpine-openjdk-11

ADD build/libs/*.jar app.jar

CMD ["java","-jar","/app.jar","--spring.profiles.active=dev"]