package rtp;

import splibraries.*;


public class rtp_client {
	
	public static void main(String[] args){
		
		VoiceTool myVoiceTool= new VoiceTool();
		String destIP="192.168.2.202";
		int destPort=4458,recvPort=4444,format=5;
		 System.out.println("start process1");
		myVoiceTool.startMedia(destIP,destPort,recvPort,format);
		
	}

}
