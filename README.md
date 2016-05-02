# Project Description

### introduction
This project is an academic project which aims to improve student's understanding of network security. It's based on exising soft without any security , and add security features on it.

### Demo
#### sip registration test case screen capture  https://youtu.be/KtEnXZet6dI
#### sip to sip call screen capture with cc  https://youtu.be/syAmNMXW5cE
#### sip to sip demo recording with explain  https://youtu.be/3LOwxai1c0s


### Team member

Xin Tong:design and TLS/Media encryption code and test verification,writing the project description.
Xiangyu :Registration authentication part
Guhan : GUI part

### Version
0.9

### Background
This project is based on existing simple softphone/sipserver program by Rogelio Martinez. However, the origin version only support unauthorized sip register, unencrypted UDP transport and unencrypted media transportation. 
This simple program by Rogelio is base on JAIN (Java APIs for Integrated Networks). The original one  use JAIN 1.0 version and we use JAIN 1.2. We use SDP adapter to form simple SDP message because there is no complicated SDP handling case in our project.
Our purpose is to add three main security features to the existing softphone/sipserver. The source code’s download address is http://booksite.elsevier.com/9780123743008/?ISBN=9780123743008.
Our project consists three phase. First phase is to add TLS to the SIP messages. The second phase is to add media plain encryption. The last phase is to add authentication of sip registration.

### 3rd party libraries:
    
#### provides SIP stack  [Jain 1.2.2] [jain]      
#### Provides ZRTP library [ZRTP-4J-FULL] [zrtp]	    
#### provides Media play, media devices supported	 [jmf-2.1.1e] [jmf]		
#### required by ZRTP library [bcpprove-jdk] [bcp]


### Installation

You need Gulp installed globally:


### Development



### N|Solid and NGINX

More details coming soon.

#### docker-compose.yml

Change the path for the nginx conf mounting path to your full path, not mine!

### Todos

 - Write Tests
 - Rethink Github Save
 - Add Code Comments
 - Add Night Mode

License
----

MIT


**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
[jain]:<https://jsip.java.net/>
[zrtp]:<https://github.com/wernerd/ZRTP4PJ>
[jmf]:<http://www.oracle.com/technetwork/java/javase/download-142937.html>
[bcp]: <https://www.bouncycastle.org/latest_releases.html>
