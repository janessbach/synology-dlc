[Synology-Dlc] A modern web plugin for the Synology Download Station (DSM, DLC)
====================================================================

This is a open source dashboard plugin for synology nas systems. It handles decryption 
of dlc containers and download monitoring wihtin one incredible fluent web app.

[Features] and future release plans
====================================================================

The idea is build a web plattform for synology nas systems in a dashboard oriented way.
Every page and slot handles a certain problem for you. The first dashboard items will be:

1. [Decryption of dlc files] and extraction of their url and filenames and push urls to Synology Disk Station via web api.
2. [Realimte download monitoring] of downloads via Download Station web api.

Planed Featureset:
[/filebot] FileBot Listener on Directory with given Patternset (Automatic Renaming of Movie, Series and Music)

Open for discussions...

[Prerequisites] system requirements
======================

This plugin is developed on the Synology NAS 713+ with DSM 6.x and NAS 214+ with DSM 5.x.

It uses the Synology Download Station Official API in version [20140326](https://global.download.synology.com/download/Document/DeveloperGuide/Synology_Download_Station_Web_API.pdf).

The user management is done directlyby your dsm itself. Therefore a user with download station credientials is required to use this web app.

The tech stack
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

The tag cloud
======================
Synology DSM, Download Station, DSM, DLC
Play, SBT, Akka, Socket.io, Grunt, ScalaTest, Jasmine
Modern web frontend stack



