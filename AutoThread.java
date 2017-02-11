package ass2;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AutoThread class implements an automatic booking thread
 *
 */
class AutoThread implements Runnable{
	
	/**
	 * Constructs an AutoThread
	 * @param id
	 * @param seatingChart
	 */
	AutoThread(int id, SeatingChart seatingChart) {
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
					
				int row = new Random().nextInt(50);
				int column = new Random().nextInt(4);
					
				//check whether seat and id are valid
				if(seatingChart.getSeatValidity(row, column) == true && seatingChart.getIdValidity(this.id)) {
			
					if(seatingChart.checkSeatAvailability(row, column) == false) continue;
					else seatingChart.setSeat(row, column, this.id);
				}
				Thread.sleep(SLEEP);						
			}
		}
		catch(InterruptedException e) {
		}
		finally {
			lock.unlock();
		}
	}
	
	private final ReentrantLock lock = new ReentrantLock();
	private static final int SLEEP = 1000;
	private int id;
	private SeatingChart seatingChart;
}
