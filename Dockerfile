FROM ingensi/oracle-jdk

MAINTAINER Jan Essbach <essbach@imoveit.de>

RUN yum clean all

RUN yum install -y unzip git

RUN curl -O http://downloads.typesafe.com/typesafe-activator/1.3.6/typesafe-activator-1.3.6.zip

RUN unzip typesafe-activator-1.3.6.zip -d / && rm typesafe-activator-1.3.6.zip && chmod a+x /activator-dist-1.3.6/activator

ENV PATH $PATH:/activator-dist-1.3.6

RUN mkdir /app && \
	cd /app && \
	git clone https://github.com/janessbach/synology-dlc

WORKDIR /app

EXPOSE 9000

CMD ["activator", "run"]
