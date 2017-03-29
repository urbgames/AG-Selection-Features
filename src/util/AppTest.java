package util;

public class AppTest {

	double a[] = new double [] {1,221};
	
	public AppTest() {
		
		double[] resultado = MathUtil.normalize(a);
		

		for (double d : resultado) {
			System.out.println(d);
		}
		
	}
	
	public static void main(String[] args) {
		new AppTest();
	}
}
