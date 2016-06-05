#!/bin/sh
test -f ./target/universal/stage/RUNNING_PID && kill `cat ./target/universal/stage/RUNNING_PID` && sleep 5;
rm ./target/universal/stage/RUNNING_PID;