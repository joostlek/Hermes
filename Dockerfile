FROM balenalib/rpi-raspbian
MAINTAINER Joost Lekkerkerker "joostlek@outlook.com"
COPY . /app
WORKDIR /app
RUN echo "deb http://mirrordirector.raspbian.org/raspbian/ stretch main contrib non-free rpi" > /etc/apt/sources.list
RUN apt-get update
RUN apt-get install python3 python3-pip python3-dev python3-setuptools
RUN sudo apt-get install libjpeg62 -y
RUN sudo apt-get install libjpeg62-dev -y
RUN sudo apt-get install zlib1g-dev -y
RUN sudo apt-get install libfreetype6-dev -y
RUN sudo apt-get install liblcms1-dev -y
RUN sudo apt-get install libopenjp2-7 -y
RUN sudo apt-get install libtiff5 -y
RUN pip3 install -r requirements.txt
EXPOSE 8000
VOLUME /hermes
ENTRYPOINT [ "python3" ]
CMD [ "src/start.py" ]