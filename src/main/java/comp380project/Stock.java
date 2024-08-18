package comp380project;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Stock {

	String name;
	String ticker;		// ticker symbol represents the stock as an abbreviation
	pythonExec executable;

	//to run in GUI we need a no-argument constructor
	public Stock() {
        this.ticker = ""; 
        initStock();
    }
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

	@FXML
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

	//have to add this for scenebuilder
	@FXML
    private Button logoutButton;
    @FXML
    private void handleLogoutAction() {
        System.out.println("Logging out...");
        Stage stage = (Stage) logoutButton.getScene().getWindow();
        stage.close();
	}
}