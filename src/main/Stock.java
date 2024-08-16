import java.util.List;

public class Stock {

	String name;
	String ticker;		// ticker symbol represents the stock as an abbreviation
	final String mainDir = "./../pyscripts";
	pythonExec executable;



	public Stock(String ticker) {
		this.ticker = ticker;
	}

	public void predict() {		
		executable = new pythonExec("stockPredictor.py", this.ticker); // command: "python stockPredictory.py"

		executable.start();
	}
	public String getName() {
		executable = new pythonExec("getStockName.py", this.ticker);	// command: "python getStockName.py"
		
		executable.start();
		return "hello";
	}
}