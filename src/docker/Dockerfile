FROM jetty:9.3-jre8

ADD . /
ADD http://central.maven.org/maven2/org/postgresql/postgresql/9.4.1208.jre7/postgresql-9.4.1208.jre7.jar /usr/local/jetty/lib/ext
RUN java -jar "$JETTY_HOME/start.jar" --add-to-startd=jmx,jmx-remote,stats,plus,ext,jndi-connections,debug


CMD ["java","-Djava.io.tmpdir=/tmp/jetty","-jar","/usr/local/jetty/start.jar", "--debug"]
