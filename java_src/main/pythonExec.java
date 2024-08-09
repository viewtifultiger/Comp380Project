
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class pythonExec{

	String fileName;

	public pythonExec(String fileName) {
		this.fileName = fileName;
	}

	public void execute() {
		try {
			// Create a ProcessBuilder instance
			ProcessBuilder pb = new ProcessBuilder("python", this.fileName);	// Initialize command for ProcessBuilder
																						// replace testpythoncode.py with your file
			
			Process process = pb.start();	// Start the process

			// Get the input stream from process to read the output of the script
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {	// read output statements from the .py file
				System.out.println(line);
			}

			// Wait for the process to complete
			int exitCode = process.waitFor();
			System.out.println("Exited with code: " + exitCode);
		}

		// 
		catch (Exception e) {
			e.printStackTrace();	// print error that occured
		}
	}
}

