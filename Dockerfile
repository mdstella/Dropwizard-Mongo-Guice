FROM java:7
ADD /configuration.yml /configuration.yml
ADD /DropWizardGuiceMongo-1.0-SNAPSHOT.jar /DropWizardGuiceMongo-1.0-SNAPSHOT.jar
WORKDIR /
CMD ["java", "-jar", "DropWizardGuiceMongo-1.0-SNAPSHOT.jar", "server", "configuration.yml"]
