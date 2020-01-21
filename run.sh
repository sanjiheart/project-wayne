#!/bin/bash
yarn install
gulp build
gradle clean build
java -jar build/libs/project-wayne-1.0.0.jar