FROM balenalib/rpi-raspbian
MAINTAINER Joost Lekkerkerker "joostlek@outlook.com"
COPY . /app
WORKDIR /app
RUN echo "deb http://mirrordirector.raspbian.org/raspbian/ stretch main contrib non-free rpi" > /etc/apt/sources.list
RUN apt-get update
RUN apt-get install python3 python3-pip python3-setuptools libjpeg62 libopenjp2-7 libtiff5-dev libffi-dev libjpeg-turbo-progs
RUN pip3 install -r requirements.txt
EXPOSE 8000
VOLUME /hermes
ENTRYPOINT [ "python3" ]
CMD [ "src/start.py" ]