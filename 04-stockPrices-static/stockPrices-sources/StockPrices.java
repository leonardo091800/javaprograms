package stockPrices;

import java.util.ArrayList;

/**
 * this class is supposed to work with an array of 10 days of prices stored as float numbers
 * but it should support both Arrays and ArrayLists (always given with a size of 10)
 * no information regarding environment conditions were given so it is supposed that this is not an environment with limited resources,
 * keeping these conditions in mind the various possible parameters will all be converted to an ArrayList of Float datatypes fixed size of 10 as the instructions commands.
 * This is done in order to improve code Readability and reusability
 */
public class StockPrices {
	/**
	 * this function convert an array of int (of any size) to an array of float
	 * @param int[] array of prices as integers
	 * @param float[] array of prices as floats
	 */
	public static float[] convertToFloatArr(int[] sp) {
		final float[] spFloat = new float[sp.length];
		for (int i = 0; i < sp.length; i++) {
			spFloat[i] = sp[i];		// no need to convert, int can stay inside float naturally
		}

		return spFloat;
	}
	/**
	 * this function convert an ArrayList of int (of any size) to an ArrayList of float
	 * @param int[] array of prices as integers
	 * @param float[] array of prices as floats
	 */
	/*
	public static ArrayList<Float> convertToFloatList(ArrayList<Integer> sp) {
		final ArrayList<Float> spFloat = new ArrayList<Float>(sp.size());
		for (int i = 0; i < sp.size(); i++) {
			spFloat.add((float)(sp.get(i)));
		}

		return spFloat;
	}
	*/

	/**
	 * this method calculate the average price of the array of stock prices
	 * @param int[] array with prices
	 * @return the average price as a float number
	 */
	public static float calculateAveragePrice(int[] sp) {
		// in this particular case an integer array is given so it must be converted into the float array first
		// only after the correct method is called
		return calculateAveragePrice(convertToFloatArr(sp));
	}
	/**
	 * this method calculate the average price of the array of stock prices
	 * @param float[] array with prices
	 * @return the average price as a float number
	 */
	public static float calculateAveragePrice(float[] sp) {
		// define the average 
		int sum = 0;

		// calculate the sum
		for (int i = 0; i < sp.length; i++) {
			sum += sp[i];
		}

		return ((float)sum/sp.length);
	}
	/**
	 * this method calculate the average price of the arrayList of stock prices
	 * @param ArrayList<Integer> ArrayList with prices
	 * @return the average price as a float number
	 */
	public static float calculateAveragePriceListInteger(ArrayList<Integer> sp) {
		// define the average 
		int sum = 0;

		// calculate the sum
		for (int i = 0; i < sp.size(); i++) {
			sum += sp.get(i);
		}

		return ((float)sum/sp.size());
	}
	/**
	 * this method calculate the average price of the arrayList of stock prices
	 * @param ArrayList<Float> ArrayList with prices
	 * @return the average price as a float number
	 */
	public static float calculateAveragePriceListFloat(ArrayList<Float> sp) {
		// define the average 
		float sum = 0;

		// calculate the sum
		for (int i = 0; i < sp.size(); i++) {
			sum += sp.get(i);
		}

		return (sum/sp.size());
	}

	/**
	 * find the maximum price in the Array
	 * @param int[] array with prices
	 * @return the maximum price as an int
	 */
	public static float findMaximumPrice(int[] sp) {
		// in this particular case an integer array is given so it must be converted into the float array first
		// only after the correct method is called
		return findMaximumPrice(convertToFloatArr(sp));
	}
	/**
	 * find the maximum price in the Array
	 * @param float[] array with prices
	 * @return the maximum price as an float
	 */
	public static float findMaximumPrice(float[] sp) {
		// define the maximum price
		float max = 0;

		// confront each price and get the maximum
		for (int i = 0; i < sp.length; i++) {
			max = (max > sp[i]) ? max : sp[i];
		}

		return max;
	}
	/**
	 * find the maximum price in the ArrayList
	 * @param ArrayList<Integer> array with prices
	 * @return the maximum price as an int
	 */
	public static int findMaximumPriceListInteger(ArrayList<Integer> sp) {
		// define the maximum price
		int max = 0;

		// confront each price and get the maximum
		for (int i = 0; i < sp.size(); i++) {
			max = (max > sp.get(i)) ? max : sp.get(i);
		}

		return max;
	}
	/**
	 * find the maximum price in the ArrayList
	 * @param ArrayList<Float> array with prices
	 * @return the maximum price as an float
	 */
	public static float findMaximumPriceListFloat(ArrayList<Float> sp) {
		// define the maximum price
		float max = 0;

		// confront each price and get the maximum
		for (int i = 0; i < sp.size(); i++) {
			max = (max > sp.get(i)) ? max : sp.get(i);
		}

		return max;
	}

	/**
	 * count the occurrences of a given price
	 * @param int[] array with prices
	 * @param int price of the stock
	 * @return the number of times the price is find as an int
	 */
	public static int countOccurrences(int[] sp, int priceTarget) {
		// in this particular case an integer array is given so it must be converted into the float array first
		// only after the correct method is called
		return countOccurrences(convertToFloatArr(sp), priceTarget);
	}
	/**
	 * count the occurrences of a given price
	 * @param float[] array with prices
	 * @param int price of the stock
	 * @return the number of times the price is find as an int
	 */
	public static int countOccurrences(float[] sp, int priceTarget) {
		int counter = 0;

		// if element with the same price is found increase the counter
		for (int i = 0; i < sp.length; i++) {
			if (priceTarget == sp[i]) counter++;
		}

		return counter;
	}
	/**
	 * count the occurrences of a given price
	 * @param ArrayList<Integer> arrayList with prices
	 * @param int price of the stock
	 * @return the number of times the price is find as an int
	 */
	public static int countOccurrencesListInteger(ArrayList<Integer> sp, int priceTarget) {
		int counter = 0;

		// if element with the same price is found increase the counter
		for (int i = 0; i < sp.size(); i++) {
			if (priceTarget == sp.get(i)) counter++;
		}

		return counter;
	}
	/**
	 * count the occurrences of a given price
	 * @param ArrayList<Float> arrayList with prices
	 * @param int price of the stock
	 * @return the number of times the price is find as an int
	 */
	public static int countOccurrencesListFloat(ArrayList<Float> sp, int priceTarget) {
		int counter = 0;

		// if element with the same price is found increase the counter
		for (int i = 0; i < sp.size(); i++) {
			if (priceTarget == sp.get(i)) counter++;
		}

		return counter;
	}

	/**
	 * compute the cumulative sum of prices at each position
	 * @param float[] Array with prices
	 * @return an array with the cumulative sum of prices at each position
	 */
	public static float[] computeCumulativeSum(int[] sp) {
		float[] cumulativeSum = new float[sp.length];

		// the first cumulativeprice is the same as the current price
		cumulativeSum[0] = (float)sp[0];

		// add the price of each element to the previous cumulative sum 
		for (int i = 1; i < sp.length; i++) {
			cumulativeSum[i] = cumulativeSum[i-1] + sp[i];
		}
		
		return cumulativeSum;
	}
	/**
	 * compute the cumulative sum of prices at each position
	 * @param float[] Array with prices
	 * @return an array with the cumulative sum of prices at each position
	 */
	public static float[] computeCumulativeSum(float[] sp) {
		float[] cumulativeSum = new float[sp.length];

		// the first cumulativeprice is the same as the current price
		cumulativeSum[0] = sp[0];

		// add the price of each element to the previous cumulative sum 
		for (int i = 1; i < sp.length; i++) {
			cumulativeSum[i] = cumulativeSum[i-1] + sp[i];
		}
		
		return cumulativeSum;
	}
	/**
	 * compute the cumulative sum of prices at each position
	 * @param ArrayList<Integer> ArrayList with prices
	 * @return an arrayList with the cumulative sum of prices at each position
	 */
	public static ArrayList<Integer> computeCumulativeSumListInteger(ArrayList<Integer> sp) {
		ArrayList<Integer> cumulativeSum = new ArrayList<Integer>(sp.size());

		// the first cumulativeprice is the same as the current price
		cumulativeSum.add(sp.get(0));

		// add the price of each element to the previous cumulative sum 
		for (int i = 1; i < sp.size(); i++) {
			cumulativeSum.add(cumulativeSum.get(i-1) + sp.get(i));
		}
		
		return cumulativeSum;
	}
	/**
	 * compute the cumulative sum of prices at each position
	 * @param ArrayList<Float> ArrayList with prices
	 * @return an arrayList with the cumulative sum of prices at each position
	 */
	public static ArrayList<Float> computeCumulativeSumListFloat(ArrayList<Float> sp) {
		ArrayList<Float> cumulativeSum = new ArrayList<Float>(sp.size());

		// the first cumulativeprice is the same as the current price
		cumulativeSum.add(sp.get(0));

		// add the price of each element to the previous cumulative sum 
		for (int i = 1; i < sp.size(); i++) {
			cumulativeSum.add(cumulativeSum.get(i-1) + sp.get(i));
		}
		
		return cumulativeSum;
	}
}
