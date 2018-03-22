FROM ubuntu
MAINTAINER  zhangyw <zhangyw001@gmail.com>
RUN apt-get update
RUN apt-get install default-jdk -y
RUN apt-get install wget -y
RUN apt-get install unzip -y
RUN apt-get install maven -y
RUN apt-get install git -y
RUN apt-get install dos2unix -y
RUN mkdir /home/treehole
#RUN git clone https://github.com/zhangyingwei/treehole-jekyll.git /home/treehole/treehole-jekyll
#RUN chmod 777 /home/treehole/treehole-jekyll/bulid
#RUN cd /home/treehole/treehole-jekyll && ./bulid
#RUN cp -r /home/treehole/treehole-jekyll/target/dist/* /home/treehole/
#RUN chmod 777 /home/treehole/treehole-jekyll-*/bin/treehole.sh
#RUN rm -rf /home/treehole/treehole-jekyll
RUN cd /home/treehole && wget http://orgr5bpmh.bkt.clouddn.com//treehole/treehole-jekyll-0.0.1-SNAPSHOT.zip
RUN cd /home/treehole && unzip treehole-jekyll-0.0.1-SNAPSHOT.zip
RUN chmod 777 /home/treehole/treehole-jekyll-0.0.1-SNAPSHOT/bin/treehole.sh
RUN dos2unix /home/treehole/treehole-jekyll-0.0.1-SNAPSHOT/bin/treehole.sh
CMD ["/home/treehole/treehole-jekyll-0.0.1-SNAPSHOT/bin/treehole.sh","start"]
