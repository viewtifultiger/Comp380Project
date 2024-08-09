
public class testMain {
	public static void main(String[] args) {
		String pythonFileName = "testModel.py";

		pythonExec executable = new pythonExec(pythonFileName);
		executable.execute();

	}
}