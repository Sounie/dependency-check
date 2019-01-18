#!/usr/bin/env bash

# Github can generate a dependency summary, but at present only interprets pom.xml
# Lets generate a pom file from gradle

./gradlew generatePomFileForSounieDependencyCheckPublication
cp build/publications/sounieDependencyCheck/pom-default.xml pom.xml
