A modern web plugin for the Synology Disk Station
=================================================

This is a open source dashboard web app for synology nas systems.
At current state it handles decryption of dlc containers and realtime
download monitoring via websocket wihtin one incredible fluent web app.

The app can easily been deployed right to your nas system at home. 
There is no connection that goes to a server outside your local area network.
All interactions with your nas system are done trough the (offical api)[https://global.download.synology.com/download/Document/DeveloperGuide/Synology_Download_Station_Web_API.pdf] of synology dsm.

Features and release plans
====================================================================

The idea is build a web plattform for synology nas systems in a dashboard oriented way.
Every page and slot handles a certain problem for you. The first dashboard items will be:

Version 1.0 - 31.04
0. Setup of alpha incl delpoyment strategy
1. [/Decryption] of dlc files and extraction of their url and filenames and push urls to Synology Disk Station via web api
2. [/Realimte] download monitoring of downloads via Download Station web api

Planed Featureset:
Version 1.1 - 30.05
[/filebot] FileBot Listener on Directory with given Patternset (Automatic Renaming of Movie, Series and Music)

Open for discussions...

System requirements
======================

This plugin is developed on the Synology NAS 713+ with DSM 6.x and NAS 214+ with DSM 5.x.

It uses the Synology Download Station Official API in version [20140326](https://global.download.synology.com/download/Document/DeveloperGuide/Synology_Download_Station_Web_API.pdf).

The user management is done directlyby your dsm itself. 
Therefore a user with download station credientials is required to use this web app.

Tech stack
======================

The idea of this project is also to use modern concepts in the area of delpoyment, testing and scalability.
Therefore following components were choosen:

- Play Framework 2.5 with Scala and Akka (Backend)
- Websockets and Socket.io (Backend <-> Frontend)
- Modern Dashboard "AdminLTE" Theme (brought to us by [almasaeed2010](https://github.com/almasaeed2010/AdminLTE)    
- Easy delpoyment via sbt and grunt (thanks to sbt-grunt, grunt)
- Direct deployment via download script directly on your nas system

[Open for changes now]

** Follow up **
















Tech cloud and development
==========================
<pre>
,--. 
     )
  _'-. _ Synology DSM, Download Station, DSM, DLC
 (    ) ),--. Play Framework, Scala, Async, Akka, Akka
             )-._ Sbt, Grunt
_________________) Socket.io, Scalatest, Mockito, Jasmine
</pre>
*[@janessbach]

*[@] 
