#!/bin/bash
docker network create -d bridge my-network
docker run --name some-mongo --net=my-network -d -p 8689:27017 mongo
docker build -t my-java-app .
docker run --name dropWizard --net=my-network -d -p 8989:8080 my-java-app	