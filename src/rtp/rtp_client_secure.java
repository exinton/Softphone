package rtp;

import MediaAdapter.VoiceTool_secure;
import splibraries.*;


public class rtp_client_secure {
	
	public static void main(String[] args){
		
		VoiceTool_secure myVoiceTool= new VoiceTool_secure();
		String destIP="192.168.2.202";
		int destPort=4458,recvPort=4444,format=3;
		 System.out.println("start process1");
		myVoiceTool.startMedia(destIP,destPort,recvPort,format);
		
	}

}
