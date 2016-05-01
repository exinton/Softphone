package Configuration;

import java.util.HashMap;
import java.util.Map;

public class SipServerConfiguration {
	
	private SipServerConfiguration(){
		
	}
	private static SipServerConfiguration instance = new SipServerConfiguration();
	public static SipServerConfiguration getInstance(){
		return instance;
	}
	
	//transport type, could be UDP,TCP,TLS
	public final String transport="tls";
	public final String keyStoreFilePath="/src/keyfile/xintong.jks";
	public final String keyStorePassword="tgq588";
	public final String keyStoreType="jks";
	//after @


	/*for test only*/
	//Verify AUTHORIZATION !!!!!!!!!!!!!!!!
	static Map<String, String> mapCredentials = new HashMap<String, String>();
	
	public static void initialUserCredentials()
	{
		mapCredentials.put("john", "pass");
		mapCredentials.put("alice", "pass");
		mapCredentials.put("Tong", "Tong");
		mapCredentials.put("guhan", "guhan");
		mapCredentials.put("wang", "wang");
	}
	public static String getPassWord(String userName){
		String password = null;
		password = mapCredentials.get(userName);
		return password;
	  }

}
