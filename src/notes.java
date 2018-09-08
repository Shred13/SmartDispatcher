//	else {
// 			double sum = 0;
// 			double counter = Integer.MAX_VALUE;
// 			for (int k = 0; k < matched.size(); k++) {
// 				//this finds the overall total mileage
// 				sum+=matched.get(k).getMiles(matched.get(k));
// 			}
// 		double temp =	sum/matched.size();
// 			for (int l= 0; l< matched.size(); l++) {
// 				double total = Math.pow(matched.get(l).getMiles(matched.get(l))-temp, 2);
// 				if (total<counter) {
// 					counter = total;
// 					theBus = matched.get(l);
// 				}	
// 			}