#!/bin/bash

if [ "$TRAVIS_REPO_SLUG" == "joostlek/Hermes" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_JDK_VERSION" == "openjdk11" ] && [ "$TRAVIS_BRANCH" == "master" ]; then
  ./mvnw javadoc:javadoc
  echo -e "Publishing javadoc...\n"

  cp -R backend/target/site/apidocs $HOME/apidocs

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --quiet --branch=master https://${GH_TOKEN}@github.com/joostlek/joostlek.github.io gh-pages > /dev/null

  cd gh-pages
  git rm -rf hermes/apidocs
  mkdir -p hermes
  cp -Rf $HOME/apidocs hermes/apidocs
  git add -f .
  git commit -m "Latest javadoc on successful travis build $TRAVIS_BUILD_NUMBER auto-pushed to gh-pages"
  git push -fq origin master > /dev/null

  echo -e "Published Javadoc to gh-pages.\n"

fi