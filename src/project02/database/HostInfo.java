package project02.database;

public class HostInfo {
	String hostName;
	String hostLocation;
	String time;
	
	public HostInfo(String hostName, String time) {
		if(hostName == null || time == null ) {
			throw new IllegalArgumentException();
		}
		this.hostName = hostName.substring(0, hostName.indexOf('@'));
		this.hostLocation = hostName.substring(hostName.indexOf('@') + 1);
		this.time = time;
	}
	
	public String getName() {
		return hostName;
	}
	
	public String getLocation() {
		return hostLocation;
	}
	
	public String getTime() {
		return time;
	}
}
