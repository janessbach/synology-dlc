FROM ubuntu:14.04

MAINTAINER Jan-Gerrit Essbach "essbach@imoveit.de"

RUN apt-get install -y software-properties-common unzip wget git

RUN \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk8-installer

RUN \
  wget https://dl.bintray.com/sbt/debian/sbt-0.13.6.deb && \
  dpkg -i sbt-0.13.6.deb && \
  apt-get update && \
  sudo apt-get install -y sbt

ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

RUN \
  cd /usr/src && \
  git clone https://github.com/janessbach/synology-dlc && \
  cd synology-dlc && \
  sbt dist

RUN mkdir -p /usr/src/app

RUN mv /usr/src/synology-dlc/target/universal/synology-dlc-1.0-SNAPSHOT.zip /usr/src/app

RUN cd /usr/src/app && \
    unzip synology-dlc-1.0-SNAPSHOT.zip && \
    rm *.zip

EXPOSE 9217
CMD /usr/src/app/synology-dlc-1.0-SNAPSHOT/bin/synology-dlc -Dhttp.port=9217
