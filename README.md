A modern web plugin for the Synology Disk Station
=================================================

This is a open source dashboard web app for synology nas systems.
At current state it handles decryption of dlc containers and realtime
download monitoring via websocket wihtin one incredible fluent web app.

The app can easily been deployed right to your nas system at home. 

Features and release plans
====================================================================

The idea is build a web plattform for synology nas systems in a dashboard oriented way.
Every page and slot handles a certain problem for you. The first dashboard items will be:

Version 1.0.0 - 31.04.16
<pre>
0. [/setup] of alpha version incl deployment strategy
1. [/decryption] of dlc files and extraction of their url and filenames and push urls to synology disk station via web api
2. [/realimte] download monitoring of downloads via download station web api
</pre>

Planed feature set version 1.1.0 - 30.05.16
<pre>
1. [/filebot] listener on directory with given pattern set (automatic renaming of movie, series and music)
2. [/hue] hue integration for changing the colors of your lamps in the network 
</pre>

System requirements
======================

This plugin is developed and tested on the synology with following configurations:

- Synology NAS 713+ with DSM 6.x 
- Synology NAS 214+ with DSM 5.x.

The user management is done directly by your dsm. Therefore, a user with download station credientials is required. 

Application Interfaces
========================

Synology Download Station Web API in [version 20140326](https://global.download.synology.com/download/Document/DeveloperGuide/Synology_Download_Station_Web_API.pdf)

Tech stack
======================

The idea of this project is also to use modern concepts in the area of delpoyment, testing and scalability.
Therefore following components were choosen:

- Play Framework 2.5 with Scala and Akka Flow Websockets
- Modern dashboard theme AdminLTE brought to us by [almasaeed2010](https://github.com/almasaeed2010/AdminLTE)    
- Easy delpoyment via sbt and sbt-grunt and direct deployment via script directly to your nas system
- Easy update via git pull and ./build.sh

[Open for changes now]

** Follow up **

The tech cloud and development team
===================================
<pre>
,--. 
     )
  _'-. _ Synology DSM, Download Station, DSM, DLC
 (    ) ),--. Play Framework, Scala, Async, Akka
             )-._ Sbt, Grunt
_________________) Socket.io, Scalatest, Mockito, Jasmine
</pre>

*[@janessbach] loves scala.

*[@elisabaum] loves scala, too.
