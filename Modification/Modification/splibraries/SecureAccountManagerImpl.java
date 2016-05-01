package splibraries;

import javax.sip.ClientTransaction;
import javax.sip.address.SipURI;
import javax.sip.header.FromHeader;

import gov.nist.javax.sip.clientauthutils.SecureAccountManager;
import gov.nist.javax.sip.clientauthutils.UserCredentialHash;

public class SecureAccountManagerImpl implements SecureAccountManager {
	String userName;
    String domain;
	String passWord;
	@Override
	/*Here, we didn't use realm to get the password of a user because we only have one realm*/
	public UserCredentialHash getCredentialHash(ClientTransaction arg0, String arg1) //arg1:realm
	{
		// TODO Auto-generated method stub
		FromHeader fh = (FromHeader)arg0.getRequest().getHeader(FromHeader.NAME);
	    javax.sip.address.Address messageFromAddress=fh.getAddress();
	    SipURI fromURI=(SipURI) messageFromAddress.getURI();
	    
	    String userName=fromURI.getUser();
	    String realm = arg1;
	    System.out.println("get the credential of " + userName+" length = "+userName.length());
	    System.out.println("account: this.userName:" + this.userName+" length = "+this.userName.length());
		//if(userName == this.userName && realm == this.domain)
		if(userName.equals(this.userName)) //here, we won't check the domain
		{
			System.out.println("to create credential");
			return new UserCredentialHashImpl(userName, realm, passWord);
		}
		else
		{
			System.out.println("Account: no such a user");
			return null;
		}
	}
	public void setCredential(String userID,  String passWord) //userID: userName@domain
	{
		int ind = userID.indexOf('@');
		System.out.println("index of @" + ind);
		this.userName = userID.substring(0, ind);
		System.out.println("username: " + userName);
		this.domain = userID.substring(ind + 1, userID.length());
		//System.out.println("domain:" + domain);
		this.passWord = passWord;
	}

}
