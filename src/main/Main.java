
public class Main {
	public static void main(String[] args) {
		String pythonFileName = "./../stockPredictor.py";
		String sp500 = "GSPC";

		pythonExec executable = new pythonExec(pythonFileName, sp500);
		executable.execute();
	}
}