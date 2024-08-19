package comp380project;

import javafx.fxml.FXML;

public class Stock {

	String name;
	String ticker;		// ticker symbol represents the stock as an abbreviation
	pythonExec executable;


	public Stock(String ticker) {
		this.ticker = ticker;
		initStock();	// create an instance of the stock with python and save it
						// saves as yfinance object, pandas, and as a CSV
	}


	private void initStock() {
		executable = new pythonExec("initStock.py", this.ticker);	// command: "python getStockName.py <ticker>"
		executable.start();
		this.name = setName();
	}
	private String setName() {
		executable = new pythonExec("setStockName.py", this.ticker);	// command: "python getStockName.py <ticker>"
		return executable.get();	// .get() runs command and expects to receive a String from output
	}

	@FXML
	public String predict() {
		executable = new pythonExec("stockPredictor.py", this.ticker); // command: "python stockPredictory.py <ticker>"
		return executable.get();
	}
	public String getName() {
		return this.name;
	}
	public String printTable() {
		// csvReader.read(this.ticker);
		pythonExec exec = new pythonExec("printTable.py", this.ticker); // command: "python stockPredictory.py"
		return exec.get();
	}
	public String printCSV() {
		pythonExec exec = new pythonExec("csvReader.py", this.ticker);
		return exec.get();
	}


}