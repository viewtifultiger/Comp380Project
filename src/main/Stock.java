import java.util.List;

public class Stock {

	String name;
	String ticker;		// ticker symbol represents the stock as an abbreviation
	pythonExec executable;


	// Constructors
	public Stock(String ticker) {
		this.ticker = ticker;
		initStock();
	}


	private void initStock() {
		executable = new pythonExec("initStock.py", this.ticker);	// command: "python getStockName.py"
		executable.start();
		this.name = setName();
	}
	private String setName() {
		executable = new pythonExec("setStockName.py", this.ticker);	// command: "python getStockName.py"
		return executable.get();
	}


	public void predict() {
		executable = new pythonExec("stockPredictor.py", this.ticker); // command: "python stockPredictory.py"
		executable.start();
	}
	public String getName() {
		return this.name;
	}
	public void printTable() {
		csvReader.read(this.ticker);
	}
}