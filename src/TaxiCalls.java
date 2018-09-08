
public class TaxiCalls {
	 int stateId;
	 int x;
	 int y;
	public TaxiCalls(int stateId, int x, int y) {
		setState(stateId);
		setX (x);
		setY (y);
	}
	
	public void setState(int stateId) {
		this.stateId = stateId;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}

	
	public int getState(TaxiCalls calls) {
		return stateId;
	}
	public int getX(TaxiCalls calls) {
		return x;
	}
	public int getY(TaxiCalls calls) {
		return y;
	}
	
}


