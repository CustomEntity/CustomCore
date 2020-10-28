package fr.customentity.customcore.utils.random;

import java.util.Random;

public class RandomUtils {
	
	private static Random random = new Random();
	
	public static int randomInt() {
		return random.nextInt();
	}
	
	public static int randomInt(int max) {
		return random.nextInt(max);
	}
	
	public static int randomInt(int min, int max) {
		if(min > max) {
			int tmp = min;
			min = max;
			max = tmp;
		}
		
		return random.nextInt((max - min) + 1 + min);
	}
	
	public static float randomFloat() {
		return random.nextFloat();
	}
	
	public static float randomFloat(float max) {
		return random.nextFloat() * max;
	}

	public static float randomFloat(float min, float max) {
		if(min > max) {
			float tmp = min;
			min = max;
			max = tmp;
		}
		return min + (float)Math.random() * (max - min);
	}
	
	public static boolean randomBoolean() {
		return random.nextBoolean();
	}
	
	public static double randomDouble() {
		return random.nextDouble();
	}
	
	public static double randomDouble(double max) {
		return random.nextDouble() * max;
	}
	
	public static double randomDouble(double min, double max) {
		if(min > max) {
			double tmp = min;
			min = max;
			max = tmp;
		}
		return random.nextDouble() * max;
	}
}
