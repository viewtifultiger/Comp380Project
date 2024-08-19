package comp380project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

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
	public void predict() {
		executable = new pythonExec("stockPredictor.py", this.ticker); // command: "python stockPredictory.py <ticker>"
		executable.start();
	}
	public String getName() {
		return this.name;
	}
	public void printTable() {
		// csvReader.read(this.ticker);
		executable = new pythonExec("printTable.py", this.ticker); // command: "python stockPredictory.py"
		executable.start();
	}
	public void printCSV() {
		executable = new pythonExec("csvReader.py", this.ticker);
		executable.start();
	}

}