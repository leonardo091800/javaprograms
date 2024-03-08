package stockPrices;

import java.util.ArrayList;

/**
 * perform the desired operations with an array
 * precondition: an array must be passed as a variable in the constructor
 * postcondition: be able to perform actions such as toString() getAveragePrice() getMaximumPrice() countOccurrences() getCumulativePrice()
 */
public class StockPricesArr implements StockPrices {
	// the array of stock prices should not change and is private
	private final int[] sp;

	/**
	 * an array of type int is required
	 */
	public StockPricesArr(int[] spArr) {
		this.sp = spArr;
	}

	/**
	 * print the array in a human Readable format
	 */
	public String toString() {
		System.out.println("the stock prices are: ");
		for (int i = 0; i < this.sp.length; i++) {
			System.out.print(this.sp[i] + " ");
		}
		System.out.println(); // end with a new line
		return "";
	}

	/**
	 * this method calculate the average price of the array of stock prices
	 * @return the average price as a float number
	 */
	public float getAveragePrice() {
		// define the average 
		int sum = 0;

		// calculate the sum
		for (int i = 0; i < this.sp.length; i++) {
			sum += sp[i];
		}

		return (float)(sum/this.sp.length);
	}

	/**
	 * find the maximum price in the ArrayList
	 * @return the maximum price as an int
	 */
	public int getMaximumPrice() {
		// define the maximum price
		int max = 0;

		// confront each price and get the maximum
		for (int i = 0; i < this.sp.length; i++) {
			max = (max > this.sp[i]) ? max : this.sp[i];
		}

		return max;
	}

	/**
	 * count the occurrences of a given price
	 * @param int price of the stock
	 * @return the number of times the price is find as an int
	 */
	public int countOccurrences(int priceTarget) {
		int counter = 0;

		// if element with the same price is found increase the counter
		for (int i = 0; i < this.sp.length; i++) {
			if (priceTarget == this.sp[i]) counter++;
		}

		return counter;
	}

	/**
	 * compute the cumulative sum of prices at each position
	 * @return an arrayList with the cumulative sum of prices at each position
	 */
	public ArrayList<Integer> computeCumulativeSum() {
		ArrayList<Integer> cumulativeSum = new ArrayList<Integer>(10);

		// the first cumulativeprice is the same as the current price
		cumulativeSum.add(this.sp[0]);

		// add the price of each element to the previous cumulative sum 
		for (int i = 1; i < this.sp.length; i++) {
			cumulativeSum.add(cumulativeSum.get(i-1) + this.sp[i]);
		}
		
		return cumulativeSum;
	}
}
