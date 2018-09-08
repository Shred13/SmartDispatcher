
public class TruckCalls {
	 int stateId;
	 String destination;
	 int load;
	public TruckCalls(int stateId, String destination, int load) {
		setState(stateId);
		setDestination (destination);
		setLoad(load);
	}
	
	public void setState(int stateId) {
		this.stateId = stateId;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setLoad(int load) {
		this.load = load;
	}
	
	public int getState(TruckCalls calls) {
		return stateId;
	}

	public String getDestination(TruckCalls calls) {
		return destination;
	}
	public int getLoad(TruckCalls calls) {
		return load;
	}
}

