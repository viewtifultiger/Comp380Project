
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

public class pythonExec{

	String fileName;	// Contains the filename that will be executed within this class
	String directory;	// Contain directory where python file is located

	public pythonExec(String fileName) {
		this.fileName = fileName;
		this.directory  = "./";
	}

	public pythonExec(String fileName, String directory) {
		this.fileName = fileName;
		this.directory = directory;
	}

	public void execute() {
		try {
			// Create a ProcessBuilder instance
			ProcessBuilder pb = new ProcessBuilder("python", this.fileName);	// Initialize command for ProcessBuilder
																				
			pb.directory(new File(this.directory));								// Initialize directory of ProcessBuilder
			Process process = pb.start();										// Start the process; execute command

			// Get the input stream from process to read the output of the script
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {	// read output statements from the .py file
				System.out.println(line);
			}

			// Wait for the process to complete
			int exitCode = process.waitFor();
			System.out.println("Exited with code: " + exitCode);				// 0 - Code ran succesfully; 1 - Failed
		}

		// 
		catch (Exception e) {
			e.printStackTrace();	// print error that occured
		}
	}
}

