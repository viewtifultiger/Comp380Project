
public class testMain {
	public static void main(String[] args) {
		String pythonFileName = "testModel.py";
		String directory = "../../models";

		pythonExec testEX = new pythonExec("testpy.py");
		testEX.execute();

		pythonExec executable = new pythonExec(pythonFileName, directory);
		executable.execute();
	}
}