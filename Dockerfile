FROM ubuntu:latest
MAINTAINER Jan Essbach <essbach@imoveit.de>

RUN apt-get update
RUN apt-get install -y software-properties-common python-software-properties

RUN DEBIAN_FRONTEND=noninteractivei \
    && add-apt-repository -y ppa:webupd8team/java \
    && apt-get -y update \
    && yes | apt-get install oracle-java8-installer
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle

ENV VERSION 1.3.6
ENV ARCHIVE typesafe-activator-${VERSION}-minimal.zip

RUN apt-get update
RUN apt-get -y install unzip git
RUN wget http://downloads.typesafe.com/typesafe-activator/${VERSION}/${ARCHIVE}\
	&& unzip ${ARCHIVE}\
	&& rm -f ${ARCHIVE}
RUN ln -s /activator-${VERSION}-minimal/activator /usr/local/bin/activator

RUN mkdir /app && \
	cd /app && \
	git clone https://github.com/janessbach/synology-dlc

WORKDIR /app

EXPOSE 9000

CMD ["activator", "run"]