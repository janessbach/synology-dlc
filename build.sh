#!/bin/bash

set -e

# install npm dependencies and gems
npm install

noformat=
tty -s || noformat=-Dsbt.log.noformat=true

# build, test and package application
./activator -mem 6144 -Dfile.encoding=utf-8 $noformat clean 'grunt --no-color dist' test dist