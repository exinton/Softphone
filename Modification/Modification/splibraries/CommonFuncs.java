package splibraries;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class CommonFuncs {
	
	static public String getLocalIP(){
	    try {  
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {  
	            NetworkInterface intf = en.nextElement();  
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {  
	                InetAddress inetAddress = enumIpAddr.nextElement(); 
	                System.out.println(inetAddress.isLoopbackAddress() + " " + inetAddress.isLinkLocalAddress() + " " + inetAddress.isSiteLocalAddress());
	                if (inetAddress != null && inetAddress instanceof Inet4Address && !inetAddress.isLoopbackAddress() && !inetAddress.isLinkLocalAddress() /*&& inetAddress.isSiteLocalAddress()*/) {  
	                 
	                System.out.println(inetAddress.getHostAddress().toString());
	                return inetAddress.getHostAddress().toString();
	                }  
	   
	            }  
	        }  
	    } catch (SocketException ex) {  
	  }  
	    return null;
		
	}


}
