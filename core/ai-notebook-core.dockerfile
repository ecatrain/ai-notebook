FROM openjdk:17-jdk-slim 

COPY ./core/build/libs/core-0.0.1.jar /usr/local/opt/ai-notebook-core/ai-notebook-core.jar

CMD java -jar -Xms128M -Xmx1g -Xss2m /usr/local/opt/ai-notebook-core/ai-notebook-core.jar