FROM maclay/java

ENV TOMCAT_HOME /apache-tomcat-8.0.46
RUN apt-get -y update && \
    cd /tmp && \
    wget http://wwwftp.ciril.fr/pub/apache/tomcat/tomcat-8/v8.0.43/bin/apache-tomcat-8.0.43.tar.gz && \
    tar xzvf apache-tomcat-8.0.43.tar.gz -C /opt/ && \
    rm -rf /var/lib/apt/lists/* && \
    rm -rf /tmp

ADD settings.xml $TOMCAT_HOME/conf/settings.xml
ADD tomcat-users.xml $TOMCAT_HOME/conf/tomcat-users.xml

EXPOSE 8080
EXPOSE 8000

RUN exec $TOMCAT_HOME/bin/./catalina.sh jpda run