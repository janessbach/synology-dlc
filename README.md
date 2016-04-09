DLC for Synology Download Station - A Modern Web Plugin
=======================================================

This is a plugin web service that handles decryption of dlc containers and pushes the links that
you want to the Synology Download Station via Web API. Furthermore, the web application shows the 
download of your files that you do not have to switch to the normal Download Station anymore.

Planed Featureset:
- FileBot Listener on Directory with given Patternset (Automatic Renaming of Movie, Series and Music)
- Open for discussions...

Prerequisites
======================

- This plugin is developed on the Synology NAS 713+ with DSM 6.x
- It uses the Synology Download Station Official API in version [20140326](https://global.download.synology.com/download/Document/DeveloperGuide/Synology_Download_Station_Web_API.pdf)

The tech stack of the app is like 
- Play Framework 2.5 with Scala
- Modern Dashboard "AdminLTE"
- One Click Deployment to your NAS

What do you have to consider?

- You should create an User in your DSM that is able to access the DSM Download Station.
- You have to login with this user credentials into this application.

Deployment to your NAS
======================

** Follow up **
