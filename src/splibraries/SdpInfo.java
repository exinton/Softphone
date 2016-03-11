package splibraries;



public class SdpInfo {
  public String IpAddress;
  public int aport;
  public int aformat;
  public int vport;
  public int vformat;

 public SdpInfo() {
   IpAddress="";
   aport=0;
   aformat=0;
   vport=0;
   vformat=0;
 }
}
/*

public class SdpInfo {
  private String IpAddress;
  private int aport;
  private int aformat;
  private int vport;
  private int vformat;

 public SdpInfo() {
   IpAddress="";
   aport=0;
   aformat=0;
   vport=0;
   vformat=0;
 }

public void setIPAddress(String IP) { IpAddress=IP;}
public void setAPort(int AP) { aport=AP;}
public void setAFormat(int AF) { aformat=AF;}
public void setVPort(int VP) { vport=VP;}
public void setVFormat(int VF) { vformat=VF;}

public String getIPAddress() { return IpAddress;}
public int getAPort() { return aport;}
public int getAFormat() { return aformat;}
public int getVPort() { return vport;}
public int getVFormat() { return vformat;}


}
*/
