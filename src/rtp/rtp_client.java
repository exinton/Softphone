package rtp;

import splibraries.*;
import javax.swing.UIManager;
import java.awt.*;
import javax.sip.*;
import javax.sip.message.*;
import javax.sip.header.*;
import javax.sip.address.*;

import java.net.*;
import java.util.*;
import java.lang.*;

import softphone1.Softphone1Listener;


public class rtp_client {
	
	public static void main(String[] args){
		
		VoiceTool myVoiceTool= new VoiceTool();
		String destIP="192.168.2.202";
		int destPort=4458,recvPort=4444,format=5;
		
		myVoiceTool.startMedia(destIP,destPort,recvPort,format);
		
	}

}
