public class Stock {

	String name;
	String ticker;		// ticker symbol represents the stock as an abbreviation
	final String modelDir = "./../stockPredictor.py";

	public Stock(String ticker) {
		this.ticker = ticker;
	}

	public void predict() {
		pythonExec executable = new pythonExec(modelDir, this.ticker);	// create a new executable command for the stock
																				// pythonExec takes the ticker symbol as an argument.
		executable.execute();
	}
}