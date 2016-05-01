package splibraries;

public class Configuration {

public int sipPort;
public String name;
public String userID;
public int audioPort;
public int videoPort;
public int audioCodec;
public int videoCodec;
public String password;

  public Configuration() {
    sipPort=5060;
    name="";
    userID="";
    audioPort=40000;
    videoPort=50000;
    audioCodec=3;
    videoCodec=26;
  }
}
