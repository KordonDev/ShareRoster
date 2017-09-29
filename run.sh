#!/bin/sh

mvn install
mvn package
./prepareFile.sh $1
java -cp target/ShareRoster-1.0-SNAPSHOT-jar-with-dependencies.jar main $1
