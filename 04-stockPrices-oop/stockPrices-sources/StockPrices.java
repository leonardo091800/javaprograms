package stockPrices;

import java.util.ArrayList;

public interface StockPrices {
	/**
	 * this method calculate the average price of the ArrayList of stock prices
	 * @return the average price as a float number
	 */
	public abstract float getAveragePrice();

	/**
	 * find the maximum price in the ArrayList
	 * @return the maximum price as an int
	 */
	public abstract int getMaximumPrice(); 

	/**
	 * count the occurrences of a given price
	 * @param int price of the stock
	 * @return the number of times the price is find as an int
	 */
	public abstract int countOccurrences(int priceTarget); 

	/**
	 * compute the cumulative sum of prices at each position
	 * @return an arrayList with the cumulative sum of prices at each position
	 */
	public abstract ArrayList<Integer> computeCumulativeSum(); 
}
