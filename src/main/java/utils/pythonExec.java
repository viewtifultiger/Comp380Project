package utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.InputStream;

public class pythonExec{

	String fileName;	// Contains the filename that will be executed within this class
	String homeDirectory = "../../";	// Contain directory where python file is located
	String scriptPath;

	public pythonExec(String fileName) {
		this.fileName = fileName;
	}

	public pythonExec(String fileName, String scriptPath) {
		this.fileName = fileName;
		this.scriptPath = scriptPath + fileName;
	}

	public void execute() {
		try {
			// Create a ProcessBuilder instance
			ProcessBuilder pb = new ProcessBuilder("python", this.scriptPath);	// Initialize command for ProcessBuilder

			System.out.println(scriptPath);
																				
			// pb.directory(new File(this.homeDirectory));								// Initialize directory of ProcessBuilder
			Process process = pb.start();										// Start the process; execute command

			// Get the input stream from process to read the output of the script
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {	// read output statements from the .py file
				InputStream error = process.getErrorStream();
				for (int i = 0; i < error.available(); i++) {
					System.out.println("" + error.read());
				}
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

