package fr.epita.maths;

public class Factorial {
	
	public static int calculate(int depth) {
		if (depth < 0 ) {
			throw new IllegalArgumentException("negative values are not allowed");
		}
		if (depth < 2 ) {
			return 1;
		}else {
			return depth * calculate(depth -1);
		}
	}

}
