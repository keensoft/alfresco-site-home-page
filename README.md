Alfresco: Site Home Page
========================
From Alfresco 5.1 EE, a user can define the start page after login to Share.

This addon provides a Share Repo extension to use Document Library page as home for every Site (instead of Dashboard page). By now this rule is fixed, but in the future Site Administrator could define the home page for the Site similarly to related start page definition introduced by Alfresco 5.1 EE.

The plugin is licensed under the [LGPL v3.0](http://www.gnu.org/licenses/lgpl-3.0.html). The current version is 1.0.0, which is compatible with 5.0.d.

Downloading the ready-to-deploy-plugin
--------------------------------------
The binary distribution is made of one amp file to be deployed in Share:

* [share AMP](https://github.com/keensoft/alfresco-site-home-page/releases/download/1.0.0/site-home-page.amp)

You can install them by using standard [Alfresco deployment tools](http://docs.alfresco.com/community/tasks/dev-extensions-tutorials-simple-module-install-amp.html)

Building the artifacts
----------------------
If you are new to Alfresco and the Alfresco Maven SDK, you should start by reading [Jeff Potts' tutorial on the subject](http://ecmarchitect.com/alfresco-developer-series-tutorials/maven-sdk/tutorial/tutorial.html).

You can build the artifacts from source code using maven
```$ mvn clean package```