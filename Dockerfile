FROM python:3.7
MAINTAINER Joost Lekkerkerker "joostlek@outlook.com"
COPY . /app
WORKDIR /app
RUN pip3 install -r requirements.txt
EXPOSE 8000
VOLUME /hermes
ENTRYPOINT [ "python" ]
CMD [ "src/start.py" ]