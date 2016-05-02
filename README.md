# Project Description

## introduction
This project is an academic project which aims to improve student's understanding of network security. It's based on exising soft without any security , and add security features on it.

## Demo
### [Demo 1: sip registration test case screen capture] [demo-registration]
### [Demo 2: sip to sip call screen capture with cc]  [demo-sip-call-screen]
### [Demo 3: sip to sip demo recording with explain] [demo-sip-calllive]


## Team member

* Xin Tong:design and TLS/Media encryption code and test verification,writing the project description.
* Xiangyu :Registration authentication part
* Senthil Kumar Guhan : GUI part development

## Version
0.9

## Background
* This project is based on existing simple softphone/sipserver program by Rogelio Martinez. However, the origin version only support unauthorized sip register, unencrypted UDP transport and unencrypted media transportation. 
* This simple program by Rogelio is base on JAIN (Java APIs for Integrated Networks). The original one  use JAIN 1.0 version and we use JAIN 1.2. We use SDP adapter to form simple SDP message because there is no complicated SDP handling case in our project.
* Our purpose is to add three main security features to the existing softphone/sipserver. 
* The source code’s download address is http://booksite.elsevier.com/9780123743008/?ISBN=9780123743008.
* Our project consists three phase. First phase is to add TLS to the SIP messages. The second phase is to add media plain encryption. The last phase is to add authentication of sip registration.

## 3rd party libraries:
    
* provides SIP stack  [Jain 1.2.2] [jain]      
* Provides ZRTP library [ZRTP-4J-FULL] [zrtp]	    
* provides Media play, media devices supported	 [jmf-2.1.1e] [jmf]		
* required by ZRTP library [bcpprove-jdk] [bcp]


### Import to your Eclipse IDE
* download the Softphone-master.zip
* import this archive into your Eclipse
* check the audio device by launching the JMFregistery under JMF-2.1.1e/bin/jmfreigistry
* import jmf configure file in to library by copying the JMF-2.1.1e/lib/jmf.properties into /lib
* make sure external libraries work fine
* check the /keyfile folder 's key files

#### How to use it

* launch the sipserver, select transport method. SipServer could coexit with either phoneA or phoneB.
* launch the softphoneA in one host, select transport method
* launch the softphoneB in another host
* register A and B to SipServer
* After status of A and B becomes idle, click call to make call.

### Todos

 - modify to Object-Oriented design
 - add media/rtp proxy for nat tranversal

License
----

MIT


**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)
[jain]:<https://jsip.java.net/>
[zrtp]:<https://github.com/wernerd/ZRTP4PJ>
[jmf]:<http://www.oracle.com/technetwork/java/javase/download-142937.html>
[bcp]: <https://www.bouncycastle.org/latest_releases.html>
[demo-registration]: <https://youtu.be/KtEnXZet6dI>
[demo-sip-call-screen]: <https://youtu.be/syAmNMXW5cE>
[demo-sip-calllive]: <https://youtu.be/3LOwxai1c0s>
