FROM openjdk:8

ENV PROJECT_HOME /usr/src
RUN mkdir -p $PROJECT_HOME/activator $PROJECT_HOME/app

WORKDIR $PROJECT_HOME/activator

#download and install activator
RUN wget http://downloads.typesafe.com/typesafe-activator/1.3.10/typesafe-activator-1.3.10.zip && \
    unzip typesafe-activator-1.3.10.zip

#make the project binaries available to the container by adding them to $PATH
ENV PATH $PROJECT_HOME/activator/activator-dist-1.3.10/bin:$PATH

WORKDIR $PROJECT_HOME/app

COPY . ./
EXPOSE 9000
CMD ["activator", "run"]

#docker build -t "docker-play" .
#docker run -ip 9000:9000