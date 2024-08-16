
public class Main {
	public static void main(String[] args) {

		Stock sp500 = new Stock("GSPC");

		System.out.println(sp500.getName());

		sp500.predict();
	}
}