FROM openjdk:8u111-jdk-alpine

ENV PROJECT_HOME /usr/src
ENV SBT_VERSION 0.13.15
RUN mkdir -p $PROJECT_HOME/sbt $PROJECT_HOME/app

WORKDIR $PROJECT_HOME

#download and install sbt
RUN apk add --no-cache --update bash wget && \
	wget "https://dl.bintray.com/sbt/native-packages/sbt/$SBT_VERSION/sbt-$SBT_VERSION.zip" && \
	unzip sbt-$SBT_VERSION.zip && \
	rm sbt-$SBT_VERSION.zip

#make the project binaries available to the container by adding them to $PATH
ENV PATH $PATH:$PROJECT_HOME/sbt/bin

WORKDIR $PROJECT_HOME/app

COPY . ./
RUN sbt compile && sbt run && rm -rf $PROJECT_HOME/app

#docker build -t "docker-play" .
#docker run -ip 9000:9000