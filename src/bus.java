
public class bus {
	 int stateId;
	 int busId;
	 int miles;
	int seats;
	boolean availiability;
	public bus(int stateId, int busId, int miles, int seats) {
		setState(stateId);
		setBus (busId);
		setMiles (miles);
		setSeat(seats);
	}
	
	public void setAvailiability (boolean availiability) {
		this.availiability=availiability;
	}
	public void setState(int stateId) {
		this.stateId = stateId;
	}
	public void setBus(int busId) {
		this.busId = busId;
	}
	public void setMiles(int miles) {
		this.miles = miles;
	}
	public void setSeat(int seats) {
		this.seats = seats;
	}
	
	public void changeSeats(bus bus, int seat) {
		 seats-=seat;
	}
	
	public boolean getAvailiability(bus bus) {
		return availiability;
	}
	public int getState(bus bus) {
		return stateId;
	}
	public int getBus(bus bus) {
		return busId;
	}
	public int getMiles(bus bus) {
		return miles;
	}
	public int getSeat(bus bus) {
		return seats;
	}
}
