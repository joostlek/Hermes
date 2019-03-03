FROM balenalib/rpi-raspbian
MAINTAINER Joost Lekkerkerker "joostlek@outlook.com"
COPY . /app
WORKDIR /app
RUN echo "deb http://mirrordirector.raspbian.org/raspbian/ stretch main contrib non-free rpi" > /etc/apt/sources.list
RUN apt-get update
RUN apt-get install python3 python3-pip python3-dev python3-setuptools
RUN apt-get install libtiff5-dev libjpeg8-dev zlib1g-dev libfreetype6-dev liblcms2-dev libwebp-dev tcl8.6-dev tk8.6-dev python-tk libjpeg-dev
RUN pip3 install -r requirements.txt
EXPOSE 8000
VOLUME /hermes
ENTRYPOINT [ "python3" ]
CMD [ "src/start.py" ]