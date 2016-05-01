package Security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import gov.nist.javax.sip.clientauthutils.UserCredentialHash;

public class UserCredentialHashImpl implements UserCredentialHash {
	private String dom;
	private String userName;
	private String passWord;
	private MessageDigest mDigest;
    
    public static final String DEFAULT_ALGORITHM = "MD5";
    public static final String DEFAULT_SCHEME = "Digest";
	public UserCredentialHashImpl() {
		// TODO Auto-generated constructor stub
	}
	public UserCredentialHashImpl(String userName, String domain, String passWord){
		this.dom = domain;
		this.userName = userName;
		this.passWord = passWord;
	}

	@Override
	public String getHashUserDomainPassword() {
		// TODO Auto-generated method stub
		
		String hashedString = null;
		try{
		mDigest = MessageDigest.getInstance(DEFAULT_ALGORITHM);
		String a1 = userName + ":" + dom + ":" + passWord; 
		byte[] x = a1.getBytes();
		byte[] y = mDigest.digest(x);
		hashedString = toHexString(y);
	}catch (NoSuchAlgorithmException ex) {
        // shouldn't happen
        throw new RuntimeException("Failed to instantiate an MD5 algorithm", ex);
    }
		return hashedString;
	}

	@Override
	/*realm*/
	public String getSipDomain() {
		// TODO Auto-generated method stub
		return dom;
	}

	@Override
	public String getUserName() {
		// TODO Auto-generated method stub
		return userName;
	}
	 /** to hex converter */
    private static final char[] toHex = { '0', '1', '2', '3', '4', '5', '6',
            '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
	private static String toHexString(byte b[]) {
        int pos = 0;
        char[] c = new char[b.length * 2];
        for (int i = 0; i < b.length; i++) {
            c[pos++] = toHex[(b[i] >> 4) & 0x0F];
            c[pos++] = toHex[b[i] & 0x0f];
        }
        return new String(c);
    }

}



