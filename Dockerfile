FROM hainam125/play-activator:1.0

ENV PROJECT_HOME /usr/src
RUN mkdir -p $PROJECT_HOME/activator $PROJECT_HOME/app

RUN rm -rf $PROJECT_HOME/app
WORKDIR $PROJECT_HOME/app
COPY . ./
EXPOSE 9000
CMD ["activator", "run"]