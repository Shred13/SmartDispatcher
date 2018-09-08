//20053370
//Shreyansh Anand
//Robin Dawes
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

class TaxiComparator implements Comparator <Taxi>{

	@Override
	public int compare(Taxi o1, Taxi o2) {
		// TODO Auto-generated method stub
		if (o1.distance<o2.distance) {
			return -1;
		}
		else if (o1.distance>o2.distance){
		return 1;
		}
		else {
			return 0;
		}
	}
	
}


class TruckComparator implements Comparator <Truck>{

	@Override
	public int compare(Truck o1, Truck o2) {
		// TODO Auto-generated method stub
		if (o1.getWeightDiff(o1)<o2.getWeightDiff(o2)) {
			return 1;
		}
		else if (o1.getWeightDiff(o1)>o2.getWeightDiff(o2)){
		return -1;
		}
		else {
			return 0;
		}
	}
	
}

public class SmartDispatcher {
	static int busFleet;
	static int truckFleet;
	static int taxiFleet;
	static ArrayList<bus> busses = new ArrayList<bus>();
	static ArrayList<Taxi> taxis = new ArrayList<Taxi>();
	static ArrayList<Truck> trucks = new ArrayList<Truck>();
	
	static ArrayList<BusCalls> busCalls = new ArrayList<BusCalls>();
	static ArrayList<TaxiCalls> taxiCalls = new ArrayList<TaxiCalls>();
	static ArrayList<TruckCalls> truckCalls = new ArrayList<TruckCalls>();
	
 	public static void main (String [] args) {
		try {
			FileReader busfr = new FileReader("src/busStates.txt");
			BufferedReader busbr = new BufferedReader (busfr);
			String busstr;
			while((busstr = busbr.readLine())!=null) {
				String [] columns = busstr.split("	");
			//	System.out.println(columns[3]);
				int stateId = Integer.parseInt(columns[0]);
				int busId = Integer.parseInt(columns[1]);
				//System.out.println(columns[1]);
				int miles = Integer.parseInt(columns[2]);
				int seat = Integer.parseInt(columns[3]);
				bus newBus = new bus(stateId, busId, miles, seat);
				busses.add(newBus);
			}
			busbr.close();	
			FileReader taxifr = new FileReader("src/taxiStates.txt");
			BufferedReader taxibr = new BufferedReader (taxifr);
			String taxistr;
			while((taxistr = taxibr.readLine())!=null) {
				String [] columns = taxistr.split("	");
				int stateId = Integer.parseInt(columns[0]);
				int taxiId = Integer.parseInt(columns[1]);
				int x = Integer.parseInt(columns[2]);
				int y = Integer.parseInt(columns[3]);
				Taxi taxi = new Taxi(stateId, taxiId, x, y);
				taxis.add(taxi);
			}
			taxibr.close();
			
			FileReader truckfr = new FileReader("src/truckStates.txt");
			BufferedReader truckbr = new BufferedReader (truckfr);
			String truckstr;
			while((truckstr = truckbr.readLine())!=null) {
				String [] columns = truckstr.split("	");
				int stateId = Integer.parseInt(columns[0]);
				int truckId = Integer.parseInt(columns[1]);
				String destination = columns[2];
				int max = Integer.parseInt(columns[3]);
				int current = Integer.parseInt(columns[4]);
				Truck truck = new Truck(stateId, truckId, destination, max, current);
				trucks.add(truck);
			}
			truckbr.close();
	
			FileReader fleetfr = new FileReader("src/fleetSizes.txt");
			BufferedReader fleetbr = new BufferedReader (fleetfr);
			String fleetstr;
			while((fleetstr = fleetbr.readLine())!=null) {
				String[] columns = fleetstr.split(" ");
				if (fleetstr.charAt(0)=='b') {
					busFleet = Integer.parseInt(columns[1]);
				}
				if (fleetstr.charAt(1)=='r') {
					truckFleet = Integer.parseInt(columns[1]);
				}	
				if (fleetstr.charAt(1)=='a') {
					taxiFleet = Integer.parseInt(columns[1]);
				}
			}
			fleetbr.close();
			
			FileReader busCallfr = new FileReader("src/busCalls.txt");
			BufferedReader busCallbr = new BufferedReader (busCallfr);
			String busCallsstr;
			while((busCallsstr = busCallbr.readLine())!=null) {
				String [] columns = busCallsstr.split("	");
				int stateId = Integer.parseInt(columns[0]);
				String destination = columns[1];
				int seats = Integer.parseInt(columns[2]);
				BusCalls bus = new BusCalls(stateId, destination, seats);
				busCalls.add(bus);
			}
			busCallbr.close();
			
			FileReader taxiCallfr = new FileReader("src/taxiCalls.txt");
			BufferedReader taxiCallbr = new BufferedReader (taxiCallfr);
			String taxiCallsstr;
			while((taxiCallsstr = taxiCallbr.readLine())!=null) {
				String [] columns = taxiCallsstr.split("	");
				int stateId = Integer.parseInt(columns[0]);
				int x = Integer.parseInt(columns[1]);
				int y = Integer.parseInt(columns[2]);
				TaxiCalls taxi = new TaxiCalls(stateId, x, y);
				taxiCalls.add(taxi);
			}
			taxiCallbr.close();
			
			FileReader truckCallfr = new FileReader("src/truckCalls.txt");
			BufferedReader truckCallbr = new BufferedReader (truckCallfr);
			String truckCallsstr;
			while((truckCallsstr = truckCallbr.readLine())!=null) {
				String [] columns = truckCallsstr.split("	");
				int stateId = Integer.parseInt(columns[0]);
				String destination = columns[1];
				int load = Integer.parseInt(columns[2]);
				TruckCalls truck = new TruckCalls(stateId, destination, load);
				truckCalls.add(truck);
			}
			truckCallbr.close();
			
		}catch (IOException e) {
			System.out.println("File does not exist");
		}
		/*for (int i = 0; i < trucks.size(); i++) {
			System.out.println(trucks.get(i).getState(trucks.get(i)));
		} 
		*/
		busCall();
		taxiCall();
		truckCall();
		}//end of main
 	
 	public static void busCall() {
 		ArrayList<bus> perfect = new ArrayList<bus>();
 		ArrayList<BusCalls>error = new ArrayList<BusCalls>();
 		for (int i =0; i<busCalls.size(); i++) {
 			ArrayList <bus> matched = new ArrayList <bus>();
 			bus theBus = busses.get(1);
 			for (int j = 0; j<busses.size(); j++) {
 				//makes sure state id is right
 				if (busses.get(j).getState(busses.get(j))==busCalls.get(i).getState(busCalls.get(i))) {
 					//makes sure seat count is less than or equal to
 					if (busses.get(j).getSeat(busses.get(j))>=busCalls.get(i).getSeats(busCalls.get(i))) {
 						matched.add(busses.get(j));
 					}
 				}
 			}
 			if (matched.size()==0) {
 				error.add(busCalls.get(i));
 			}
 			else {
 			double sum = 0;
 			double counter = Integer.MAX_VALUE;
 			for (int k = 0; k < matched.size(); k++) {
 				//this finds the overall total mileage
 				sum+=matched.get(k).getMiles(matched.get(k));
 			}
 		double temp =	sum/matched.size();
 			for (int l= 0; l< matched.size(); l++) {
 				double total = Math.pow(matched.get(l).getMiles(matched.get(l))-temp, 2);
 				if (total<counter) {
 					counter = total;
 					theBus = matched.get(l);
 				}	
 			}
 			theBus.changeSeats(theBus, busCalls.get(i).getSeats(busCalls.get(i)));
 			perfect.add(theBus);
 			}
 		}//end of the for loop for busCalls
 		try {
 			FileWriter fw = new FileWriter("src/BusSelections.txt");
 			PrintWriter pw = new PrintWriter (fw);
 			for (int i = 0; i<error.size(); i++) {
 				pw.println("The transaction for " + error.get(i).getDestination(error.get(i)) +" could not be satisfied because too many seats were required (" + error.get(i).getSeats(error.get(i))+")");
 			}
 			for (int j = 0; j<perfect.size(); j++) {
 				pw.println(perfect.get(j).getState(perfect.get(j)) + "\t" + perfect.get(j).getBus(perfect.get(j))+ "\t" + perfect.get(j).getMiles(perfect.get(j)) + "\t" + perfect.get(j).getSeat(perfect.get(j)));
 			}
 			pw.close();
 		}catch (IOException e) {
 			System.out.println("ERROR");
 		}
 	}//end of the BusCalls
 
 	public static void taxiCall() {
 		ArrayList <Taxi> perfectTaxis = new ArrayList <Taxi>();
			ArrayList <TaxiCalls> badTaxis = new ArrayList <TaxiCalls>();
 		for (int i = 0; i<taxiCalls.size(); i++) {
 			ArrayList <Taxi> goodTaxis = new ArrayList <Taxi>();
 			for (int j = 0; j<taxis.size(); j++) {
 				if(taxis.get(j).getState(taxis.get(j))==taxiCalls.get(i).getState(taxiCalls.get(i))) {
 				taxis.get(j).setDistance(taxiCalls.get(i).getX(taxiCalls.get(i)), taxiCalls.get(i).getY(taxiCalls.get(i)));
 				goodTaxis.add(taxis.get(j));
 				}
 			}
 			Collections.sort(goodTaxis, new TaxiComparator());
 			int value = searcher(0, 1, goodTaxis);
 			if (value <0 ) {
 				badTaxis.add(taxiCalls.get(i));
 			}
 			else {
 				goodTaxis.get(value).setAviliability(false);
 				perfectTaxis.add(goodTaxis.get(value));
 			}
 		}
		try {
 			FileWriter fw = new FileWriter("src/TaxiSelections.txt");
 			PrintWriter pw = new PrintWriter (fw);
 			for (int i = 0; i<badTaxis.size(); i++) {
 				pw.println("The transaction for " + badTaxis.get(i).getX(badTaxis.get(i)) +"  & " + badTaxis.get(i).getY(badTaxis.get(i) )+ " could not be satisfied because the taxi was already occupied");
 			}
 			for (int j = 0; j<perfectTaxis.size(); j++) {
 				pw.println(perfectTaxis.get(j).getState(perfectTaxis.get(j)) + "\t" + perfectTaxis.get(j).getTaxi(perfectTaxis.get(j))+ "\t" + perfectTaxis.get(j).getDistance(perfectTaxis.get(j)));
 			}
 			pw.close();
 		}catch (IOException e) {
 			System.out.println("ERROR");
 		}
 	}
 	
 	public static int searcher(int beginning, int ending,  ArrayList <Taxi>taxis) {
 		if (ending>taxis.size() || beginning>= ending) {
 			return -1;
 		}
 		else if(ending < taxis.size()) {
 			ArrayList <Taxi> newTaxis = new ArrayList<Taxi>();
 			boolean taxiCheck = false;
 			for (int i = beginning; i<ending+1; i++) {
 				taxiCheck = taxiCheck || (taxis.get(i)).getAvailiability(taxis.get(i));
 				newTaxis.add(taxis.get(i));
 			}
 			boolean found = false;
 			while(taxiCheck && !found && newTaxis.size()!=0) {	
 				//bringing in a random taxi object that is generated to be within the bounds of the ArrayList
 				int taxiChoice =  new Random().nextInt(newTaxis.size());
 				//this will get the random taxi from the random operation
 				Taxi temporary= newTaxis.get(taxiChoice);
 				if(temporary.getAvailiability(temporary)) {
 					for (int j = 0; j<newTaxis.size();j++){
 						newTaxis.get(j).setIndex(j);
 					}	
 					found = true;
 					newTaxis.get(taxiChoice).setAviliability(false);
 					return newTaxis.get(taxiChoice).getIndex();
 				}
 				else {
 					newTaxis.remove(taxiChoice);
 				}
 			}
 		}
 		if (ending*2>taxis.size()) {
				return searcher(ending+1, taxis.size(),taxis) ;					
			}
			else {
				return searcher(ending+1, ending+1+(ending-beginning+1)*2-1, taxis);
			}
 	}
 	
 	public static void truckCall() {
 		ArrayList<TruckCalls> noTruck = new ArrayList<TruckCalls>();
 		ArrayList<TruckCalls> perfectTruck = new ArrayList<TruckCalls>();
 		for (int i = 0; i<truckCalls.size(); i++) {
 			ArrayList <Truck> goodTrucks = new ArrayList <Truck>();
 			for (int j = 0; j<trucks.size(); j++) {
 				if (truckCalls.get(i).getDestination(truckCalls.get(i)).equals(trucks.get(j).getDestination(trucks.get(j)))) {
 					if (truckCalls.get(i).getState(truckCalls.get(i))==trucks.get(j).getState(trucks.get(j)));{
 					int weight = trucks.get(j).getMax(trucks.get(j))-truckCalls.get(i).getLoad(truckCalls.get(i));
 					trucks.get(j).setWeightDiff(weight);
 					goodTrucks.add(trucks.get(j));
 					} 				
 				}
 			}
 			if(goodTrucks.isEmpty()) {
 				System.out.println("Nothing to see here folks");
 			}
 			else {
 			Collections.sort(goodTrucks, new TruckComparator());
 			goodTrucks.get(0).setCurrent(goodTrucks.get(0).getWeightDiff(goodTrucks.get(0)));
 			for(int m = 0; m<goodTrucks.size(); m++) {
 			System.out.println(goodTrucks.get(m).getWeightDiff(goodTrucks.get(m))+ " : " + goodTrucks.get(m).getState(goodTrucks.get(m)));
 			}
 		}
 		}
 		try {
 			FileWriter fw = new FileWriter("src/TruckSelections.txt");
 			PrintWriter pw = new PrintWriter (fw);
 			for (int i = 0; i<noTruck.size(); i++) {
 				pw.println("The transaction for " + noTruck.get(i).getDestination(noTruck.get(i)) +" was incomplete");
 			}
 			for (int j = 0; j<perfectTruck.size(); j++) {
 				pw.println(perfectTruck.get(j).getState(perfectTruck.get(j)));
 			}
 			pw.close();
 		}catch (IOException e) {
 			System.out.println("ERROR");
 		}	
 
 	}


}//end of the class
