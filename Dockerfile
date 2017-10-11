FROM nginx
MAINTAINER  zhangyw <zhangyw001@gmail.com>
# RUN apt-get update
# RUN apt-get -y install java
# RUN apt-get -y install nginx
COPY ./target/dist/* /home
# RUN cd /home/treehole-jekyll-0.0.1-SNAPSHOT
# RUN java -jar treehole-jekyll-0.0.1-SNAPSHOT.jar