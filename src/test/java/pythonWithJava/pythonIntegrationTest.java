package pythonWithJava;

import utils.pythonExec;

public class pythonIntegrationTest {
	public static void main(String[] args) {
		String pythonFileName = "integration.py";
		String scriptPath = "integration_tests/python/";

		pythonExec executable = new pythonExec(pythonFileName, scriptPath);
		executable.execute();
	}
}