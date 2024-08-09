
public class testMain {
	public static void main(String[] args) {
		String pythonFileName = "testPythonCode.py";
		String secondTestFile = "anotherTestCode.py";

		pythonExec executable = new pythonExec(pythonFileName);
		executable.execute();

		pythonExec secondExec = new pythonExec(secondTestFile);
		secondExec.execute();
	}
}