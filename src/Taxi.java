
public class Taxi {
	 int stateId;
	 int taxiId;
	 int x;
	 int y;
	 boolean availiability =true;
	 double distance;
	 int index;
	public Taxi(int stateId, int taxiId, int x, int y) {
		setState(stateId);
		setTaxi (taxiId);
		setX (x);
		setY(y);
	}
	public void setDistance(int otherX, int otherY) {
		this.distance=Math.sqrt((Math.pow(otherX-x, 2) + (Math.pow(otherY-y, 2))));
	}
	public void setIndex(int index) {
		this.index = index;
	}

	
	public void setAviliability(boolean availiability) {
		this.availiability=availiability;
	}
	public void setState(int stateId) {
		this.stateId = stateId;
	}
	public void setTaxi(int taxiId) {
		this.taxiId = taxiId;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getIndex() {
		return index;
	}
	public double getDistance (Taxi taxi) {
		return distance;
	}
	public boolean getAvailiability(Taxi taxi) {
		return availiability;
	}
	public int getState(Taxi taxi) {
		return stateId;
	}
	public int getTaxi(Taxi taxi) {
		return taxiId;
	}
	public int getX(Taxi taxi) {
		return x;
	}
	public int getY(Taxi taxi) {
		return y;
	}
}

