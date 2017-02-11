package ass2;

/**
 * Create class executes the creation of the SeatingChart
 */
public class Create {
	
	/**
	 * The main method
	 */
	public static void main(String[] args) {
		
		SeatingChart seatingChart = new SeatingChart();
		
		Thread t1 = new Thread(new AutoThread(1, seatingChart));
		Thread t2 = new Thread(new AutoThread(2, seatingChart));
		Thread t3 = new Thread(new ManualThread(3, seatingChart));
		
		t1.start();
		t2.start();
		t3.start();		
	}
}
