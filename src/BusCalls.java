
public class BusCalls {
	 int stateId;
	 String destination;
	 int seats;
	public BusCalls(int stateId, String destination, int seats) {
		setState(stateId);
		setDestination (destination);
		setSeats (seats);
	}
	
	public void setState(int stateId) {
		this.stateId = stateId;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	
	public int getState(BusCalls calls) {
		return stateId;
	}
	public String getDestination(BusCalls calls) {
		return destination;
	}
	public int getSeats(BusCalls calls) {
		return seats;
	}
}


