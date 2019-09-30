#!/bin/bash
docker kill some-mongo
docker kill dropWizard
docker rm some-mongo
docker rm dropWizard
docker network rm my-network