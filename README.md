# Project Wayne

**Project Wayne** is a playful project. It's fun, don't be serious.

## Prerequisite
- MongoDB
- Java
- [Gradle](https://gradle.org/)
- [Yarn](https://yarnpkg.com/zh-Hans/)

## Build steps
1. Go to **src/main/resources/static/web**, then execute:

	   yarn install

2. (Optional) Go to **src/main/resources**, then edit **application.yml**:

	   gateway:
	     # change to real gateway host
		 host: 192.168.1.211

3. Back to **root directory of this project**, then execute:

	   gradle clean build

4. Finally, execute:

	   java -jar build/libs/project-wayne-1.0.0.jar
