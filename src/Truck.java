
public class Truck {
	 int stateId;
	 int truckId;
	 String destination;
	 int max;
	 int current;
	 boolean availiability;
	 int weightDiff;
	 
	public int getWeightDiff(Truck truck) {
		return weightDiff;
	}

	public void setWeightDiff(int weightDiff) {
		this.weightDiff = weightDiff;
	}

	public Truck(int stateId, int truckId, String destination,  int max, int current) {
		setState(stateId);
		setTruck (truckId);
		setDestination (destination);
		setMax (max);
		setCurrent(current);
	}
	
	public void setAvailiability (boolean availiability) {
		this.availiability = availiability;
	}
	public void setState(int stateId) {
		this.stateId = stateId;
	}
	public void setTruck(int truckId) {
		this.truckId = truckId;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setMax(int max) {
		this.max = max;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	
	public boolean getAvailiability (Truck truck) {
		return availiability;
	}
	public int getState(Truck truck) {
		return stateId;
	}
	public String getDestination (Truck truck) {
		return destination;
	}
	public int getTruck(Truck truck) {
		return truckId;
	}
	public int getMax(Truck truck) {
		return max;
	}
	public int getCurrent(Truck truck) {
		return current;
	}
}