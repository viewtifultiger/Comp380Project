
public class Main {
	public static void main(String[] args) {

		Stock sp500 = new Stock("GSPC");

		System.out.println("Name of this stock: " + sp500.getName());

		sp500.printTable();

		// sp500.predict();

	}
}