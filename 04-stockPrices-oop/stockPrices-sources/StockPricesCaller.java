package stockPrices;

import java.util.ArrayList;
import java.util.Random;

public class StockPricesCaller {
	/*
	 * example call with 10 random stock prices
	 */
	public static void main(String[] args) {
		// create an example array and arraylist of stock prices
		int[] StockPricesArr = new int[10];
		ArrayList<Integer> StockPricesList = new ArrayList<>(10);

		// fill with random values
		Random r = new Random();
		int maximumPrice = 100;		// the maximum price the stocks can reach
		int rElement = 0;
		for (int i = 0; i < StockPricesArr.length; i++) {
			rElement = r.nextInt(maximumPrice);
			StockPricesList.add(rElement);
			StockPricesArr[i] = rElement;
		}

		// Array section -----------------------------------------------------------------------
		System.out.println("using the class with an Array as parameter: ");
		// simulate the call to the method with an Array
		StockPrices spArr = new StockPricesArr(StockPricesArr);
		// print the array
		spArr.toString();
		// get the average
		System.out.println("the average is: "+spArr.getAveragePrice());
		// get the maximum
		System.out.println("the Maximum is: "+spArr.getMaximumPrice());
		// get the counter of a number that surely exist and a random nunmber
		System.out.println("the occurences of the number '"+rElement+"' is: "+spArr.countOccurrences(rElement));
		int rElementTmp;
		rElementTmp = r.nextInt(maximumPrice);
		System.out.println("the occurences of the number '"+rElementTmp+"' is: "+spArr.countOccurrences(rElementTmp));
		// get the cumulative sum of prices at each position
		System.out.print("The cumulative sum of prices at each position is: ");
		for(int cumulativePrice : spArr.computeCumulativeSum()) {
			System.out.print(" '"+cumulativePrice+"'");
		}

		System.out.println("\n\n");

		// ArrayList section -----------------------------------------------------------------------
		System.out.println("using the class with an ArrayList as parameter: ");
		// simulate the call to the method with an Array
		StockPrices spList = new StockPricesList(StockPricesList);
		// print the array
		spList.toString();
		// get the average
		System.out.println("the average is: "+spList.getAveragePrice());
		// get the maximum
		System.out.println("the Maximum is: "+spList.getMaximumPrice());
		// get the counter of a number that surely exist and a random nunmber
		System.out.println("the occurences of the number '"+rElement+"' is: "+spList.countOccurrences(rElement));
		rElementTmp = r.nextInt(maximumPrice);
		System.out.println("the occurences of the number '"+rElementTmp+"' is: "+spList.countOccurrences(rElementTmp));
		// get the cumulative sum of prices at each position
		System.out.print("The cumulative sum of prices at each position is: ");
		for(int cumulativePrice : spList.computeCumulativeSum()) {
			System.out.print(" '"+cumulativePrice+"'");
		}

		System.out.println("");
	}
}
