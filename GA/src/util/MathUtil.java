package util;

import java.util.Arrays;

public class MathUtil {

	public static double[] normalize(double[] array) {

		double[] array2 = array.clone();
		double mean = calcMean(array2);
		double std = calcStd(array2);
		double a[] = new double[array2.length];

		for (int i = 0; i < array2.length; i++) {
			double ai = (array2[i] - mean) / std;
			a[i] = ai;
		}

		double minA = calcMin(a);
		double[] c = new double[array.length];

		for (int i = 0; i < array2.length; i++) {
			double b = a[i] + Math.abs(minA) + 1;
			c[i] = b * array2.length * 10;
		}

		return c;
	}

	public static double calcMin(double[] array) {

		double[] array2 = array.clone();
		Arrays.sort(array2);

		return array2[0];
	}

	public static double calcMax(double[] array) {

		double[] array2 = array.clone();
		Arrays.sort(array2);

		return array2[array2.length - 1];
	}

	public static double calcStd(double[] array) {

		double mean = calcMean(array);
		double std = 0;

		for (double d : array) {
			std += Math.sqrt(Math.pow(d - mean, 2));
		}

		if (array.length > 0) {
			std = std / array.length;
			return std;
		} else
			return 0;
	}

	public static double calcMean(double[] array) {

		double mean = 0;

		for (double d : array) {
			mean += d;
		}

		if (array.length > 0) {
			mean = mean / array.length;
			return mean;
		} else
			return 0;
	}

}