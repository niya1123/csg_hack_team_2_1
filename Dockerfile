FROM ubuntu

WORKDIR /team_2_1

RUN apt-get -y update && apt-get install -y \
    openjdk-8-jdk