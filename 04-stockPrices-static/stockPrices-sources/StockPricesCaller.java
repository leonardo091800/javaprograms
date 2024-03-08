package stockPrices;

import java.util.ArrayList;
import java.util.Random;

public class StockPricesCaller {
	/*
	 * example call with 10 random stock prices
	 */
	public static void main(String[] args) {
		// create an example array and arraylist of stock prices
		int arrayLength = 10;
		int[] stockPricesArrInteger = new int[arrayLength];
		float[] stockPricesArrFloat = new float[arrayLength];
		ArrayList<Integer> stockPricesListInteger = new ArrayList<Integer>(arrayLength);
		ArrayList<Float> stockPricesListFloat = new ArrayList<Float>(arrayLength);

		// fill with random values
		Random r = new Random();
		int maximumPrice = 100;		// the maximum price the stocks can reach
		int rElement = 0;
		for (int i = 0; i < arrayLength; i++) {
			rElement = r.nextInt(maximumPrice);
			stockPricesArrInteger[i] = rElement;
			stockPricesArrFloat[i] = (float)rElement;
			stockPricesListInteger.add(rElement);
			stockPricesListFloat.add((float)rElement);
		}

		// Array of Integers section -----------------------------------------------------------------------
		System.out.println("using the class with an Array of int as parameter: ");
		// print the array
		for ( int i = 0; i < stockPricesArrInteger.length; i++) {
			System.out.print(stockPricesArrInteger[i]+" ");
		}
		System.out.println();
		// get the average
		System.out.println("the average is: "+stockPrices.StockPrices.calculateAveragePrice(stockPricesArrInteger));
		// get the maximum
		System.out.println("the Maximum is: "+stockPrices.StockPrices.findMaximumPrice(stockPricesArrInteger));
		// get the counter of a number that surely exist and a random nunmber
		System.out.println("the occurences of the number '"+rElement+"' is: "+stockPrices.StockPrices.countOccurrences(stockPricesArrInteger, rElement));
		int rElementTmp;
		rElementTmp = r.nextInt(maximumPrice);
		System.out.println("the occurences of the number '"+rElementTmp+"' is: "+stockPrices.StockPrices.countOccurrences(stockPricesArrInteger, rElementTmp));
		// get the cumulative sum of prices at each position
		System.out.print("The cumulative sum of prices at each position is: ");
		for(float cumulativePrice : stockPrices.StockPrices.computeCumulativeSum(stockPricesArrInteger)) {
			System.out.print(" '"+cumulativePrice+"'");
		}

		System.out.println("\n\n");

		// Array of Floats section -----------------------------------------------------------------------
		System.out.println("using the class with an Array of float as parameter: ");
		// print the array
		for ( int i = 0; i < stockPricesArrFloat.length; i++) {
			System.out.print(stockPricesArrFloat[i]+" ");
		}
		System.out.println();
		// get the average
		System.out.println("the average is: "+stockPrices.StockPrices.calculateAveragePrice(stockPricesArrFloat));
		// get the maximum
		System.out.println("the Maximum is: "+stockPrices.StockPrices.findMaximumPrice(stockPricesArrFloat));
		// get the counter of a number that surely exist and a random nunmber
		System.out.println("the occurences of the number '"+rElement+"' is: "+stockPrices.StockPrices.countOccurrences(stockPricesArrFloat, rElement));
		rElementTmp = r.nextInt(maximumPrice);
		System.out.println("the occurences of the number '"+rElementTmp+"' is: "+stockPrices.StockPrices.countOccurrences(stockPricesArrFloat, rElementTmp));
		// get the cumulative sum of prices at each position
		System.out.print("The cumulative sum of prices at each position is: ");
		for(float cumulativePrice : stockPrices.StockPrices.computeCumulativeSum(stockPricesArrFloat)) {
			System.out.print(" '"+cumulativePrice+"'");
		}

		System.out.println("\n\n");

		// ArrayList of Integers section -----------------------------------------------------------------------
		System.out.println("using the class with an ArrayList of Integers as parameter: ");
		// print the array
		for ( int i = 0; i < stockPricesListInteger.size(); i++) {
			System.out.print(stockPricesListInteger.get(i)+" ");
		}
		System.out.println();
		// get the average
		System.out.println("the average is: "+stockPrices.StockPrices.calculateAveragePriceListInteger(stockPricesListInteger));
		// get the maximum
		System.out.println("the Maximum is: "+stockPrices.StockPrices.findMaximumPriceListInteger(stockPricesListInteger));
		// get the counter of a number that surely exist and a random nunmber
		System.out.println("the occurences of the number '"+rElement+"' is: "+stockPrices.StockPrices.countOccurrencesListInteger(stockPricesListInteger, rElement));
		rElementTmp = r.nextInt(maximumPrice);
		System.out.println("the occurences of the number '"+rElementTmp+"' is: "+stockPrices.StockPrices.countOccurrencesListInteger(stockPricesListInteger, rElementTmp));
		// get the cumulative sum of prices at each position
		System.out.print("The cumulative sum of prices at each position is: ");
		for(float cumulativePrice : stockPrices.StockPrices.computeCumulativeSumListInteger(stockPricesListInteger)) {
			System.out.print(" '"+cumulativePrice+"'");
		}

		System.out.println("\n\n");
		
		// ArrayList of Floats section -----------------------------------------------------------------------
		System.out.println("using the class with an ArrayList of Floats as parameter: ");
		// print the array
		for ( int i = 0; i < stockPricesListFloat.size(); i++) {
			System.out.print(stockPricesListFloat.get(i)+" ");
		}
		System.out.println();
		// get the average
		System.out.println("the average is: "+stockPrices.StockPrices.calculateAveragePriceListFloat(stockPricesListFloat));
		// get the maximum
		System.out.println("the Maximum is: "+stockPrices.StockPrices.findMaximumPriceListFloat(stockPricesListFloat));
		// get the counter of a number that surely exist and a random nunmber
		System.out.println("the occurences of the number '"+rElement+"' is: "+stockPrices.StockPrices.countOccurrencesListFloat(stockPricesListFloat, rElement));
		rElementTmp = r.nextInt(maximumPrice);
		System.out.println("the occurences of the number '"+rElementTmp+"' is: "+stockPrices.StockPrices.countOccurrencesListFloat(stockPricesListFloat, rElementTmp));
		// get the cumulative sum of prices at each position
		System.out.print("The cumulative sum of prices at each position is: ");
		for(float cumulativePrice : stockPrices.StockPrices.computeCumulativeSumListFloat(stockPricesListFloat)) {
			System.out.print(" '"+cumulativePrice+"'");
		}

		System.out.println("\n\n");
	}
}
