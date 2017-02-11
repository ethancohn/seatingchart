package ass2;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * Seating Chart class
 *
 */
public class SeatingChart {

	private static final int ROWS = 50;
	private static final int COLUMNS = 4;
	private static int[][] seats = new int[ROWS][COLUMNS];
	private int rowSelection;
	private int columnSelection;
	public boolean isClicked = false;
	
	private JFrame frame = new JFrame();
	private static JTable table = new JTable(ROWS, COLUMNS);
	private JLabel enterRow = new JLabel("Enter row: ");
	private JTextField rowField = new JTextField();
	private JLabel enterColumn = new JLabel("Enter column: ");
	private JTextField columnField = new JTextField();	
	private JButton button = new JButton("Book Seat");
	private JPanel p1 = new JPanel();
	private JPanel p2 = new JPanel();
	
	/**
	 * Constructs a seating chart with attributes
	 */
	public SeatingChart() {	

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(700, 800));
		frame.setLayout(new FlowLayout());
		table.setGridColor(Color.BLACK);
		
		table.setRowHeight(14);
		//table.setRowMargin(5);
				
			

		p2.setLayout(new GridLayout(3, 4));

		p1.add(table);
		p2.add(enterRow);
		p2.add(rowField);
		p2.add(enterColumn);
		p2.add(columnField);
		p2.add(button);
		
		frame.add(p1);
		frame.add(p2);
		
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				String rowText = rowField.getText();
				String columnText = columnField.getText();
				rowSelection = Integer.parseInt(rowText);
				columnSelection = Integer.parseInt(columnText);
				isClicked = true;
			}
		});
		
		frame.setVisible(true);	

	}	
	
	/**
	 * Returns the row selection from actionListener
	 * @return rowSelection
	 */
	public int getRowSelection() {
		return rowSelection;
	}
	
	/**
	 * Returns column selection from actionListener
	 * @return columnSelection
	 */
	public int getColumnSelection() {
		return columnSelection;
	}
	
	/**
	 * Checks whether row and column parameters are valid
	 * @param row
	 * @param column
	 * @return true if valid, else return false
	 */
	public boolean getSeatValidity(int row, int column) {
		if (row >= 0 && row < 50 && column >= 0 && column < 4) return true;
		else return false;
	}
	
	/**
	 * Checks whether id parameter is valid
	 * @param id
	 * @return true if valid, else return false
	 */
	public boolean getIdValidity(int id) {
		if (id > 0 && id < 4) return true;
		else return false;
	}
	

	
	/**
	 * Checks whether given seat is taken
	 * @param row
	 * @param column
	 * @return true if available, else false
	 */
	public boolean checkSeatAvailability(int row, int column) {
		
		if (seats[row][column] == 0) return true;
		else return false;
	}
	
	/**
	 * Checks whether entire seating chart is full
	 * @return isFull 
	 */
	public boolean isFull() {
		boolean isFull = true;
		
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (checkSeatAvailability(i, j) == true) return false;
			}
		}

		return isFull;
	}
	
	/**
	 * Updates the table at the specified row and column with the id
	 * @param row
	 * @param column
	 * @param id
	 * @param table
	 */
	private void updateTable(int row, int column, int id, JTable table) {
		
		table.setValueAt(id, row, column);
	}
	
	/**
	 * Sets the producer id of the given seat
	 * @param column
	 * @param row
	 * @param id
	 */
	public void setSeat(int row, int column, int id) {
		
		//check that seat is available and params are valid
		if (checkSeatAvailability(row, column) == true) {
			//change value of given cell of table with the id parameter
			seats[row][column] = id;
			updateTable(row, column, seats[row][column], table);
		}
		
		//if seat is unavailable, return
		//else return;	
	}		 
}
