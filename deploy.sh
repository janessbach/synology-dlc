#!/bin/bash

REMOTE=root@5.35.246.160
REMOTE_APP=/var/www/www.autoglueck.com/

sbt stage || exit 1;
rsync -va target/ $REMOTE:$REMOTE_APP/target;
ssh $REMOTE "cd $REMOTE_APP; ./stop.sh";
ssh $REMOTE "cd $REMOTE_APP; ./start.sh";