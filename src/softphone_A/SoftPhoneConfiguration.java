package softphone_A;

import java.util.HashMap;
import java.util.Map;

public class SoftPhoneConfiguration {
	
	private SoftPhoneConfiguration(){
		
	}
	private static SoftPhoneConfiguration instance = new SoftPhoneConfiguration();
	public static SoftPhoneConfiguration getInstance(){
		return instance;
	}
	
	//transport type, could be UDP,TCP,TLS
	public  String transport;
	public final String keyStoreFilePath="/src/keyfile/softphone.jks";
	public final String zrtpPath="/src/keyfile/test_r.zid";
	public final String keyStorePassword="tgq588";
	public final String keyStoreType="jks";
	public final String PROXY_REALM = "nist.gov";
	public  int myPort;
	public  int myAudioPort;
	public  String myAudioCodec;
	public  String name;
	public  String userID;
	public  String password;
	

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
