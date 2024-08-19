
public class Main {
	public static void main(String[] args) {

		Stock sp500 = new Stock("^GSPC");

		Stock apple = new Stock("aapl");

		System.out.println("Name of this stock: " + sp500.getName());
		System.out.println("Name of this stock: " + apple.getName());

		sp500.printCSV();
		apple.printCSV();

		// sp500.printTable();

		apple.predict();
		sp500.predict();
	}
}