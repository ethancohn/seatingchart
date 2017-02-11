package ass2;

import java.util.concurrent.locks.ReentrantLock;

/**
 * ManualThread class. Implements a GUI Thread for booking seats manually
 *
 */
class ManualThread extends Thread{

	ManualThread(int id, SeatingChart seatingChart) {
		this.id = id;
		this.seatingChart = seatingChart;
		
	}
	
	/**
	 * Runs the AutoThread
	 */	
	public void run() {
		
		lock.lock();
			
		try {
		
			while(!seatingChart.isFull()) {
				
				if (seatingChart.isClicked == true) {
					
					int row = seatingChart.getRowSelection();
					int column = seatingChart.getColumnSelection();
					//check whether seat and id are valid
					if(seatingChart.getSeatValidity(row, column) == true && seatingChart.getIdValidity(this.id)) {
				
						if(seatingChart.checkSeatAvailability(row, column) == true) {
							seatingChart.setSeat(row - 1, column - 1, this.id);
						}
						
					}
				}
			}
			Thread.sleep(0);
		}
		catch(InterruptedException e) {
		}
		finally {
			lock.unlock();
		}

	}
	
	private final ReentrantLock lock = new ReentrantLock();
	//private static final int SLEEP = 1000;
	private int id;
	private SeatingChart seatingChart;
}
