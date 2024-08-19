package comp380project;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class pythonExec{

	String file;		// Contains the python file name
	String ticker;		// Contains the ticker symbol of stock
	final String mainDir = "./../pyscripts/";	// directory to location of python scripts

	public pythonExec(String file, String ticker) {
		this.file = file;
		this.ticker = ticker;
	}

	public void start() {	// runs the python file, no return value
	try {
		// Create a ProcessBuilder instance
		ProcessBuilder pb = new ProcessBuilder("python3", mainDir + file, ticker);	// Initialize command for ProcessBuilder
																			
		Process process = pb.start();										// Start the process; execute command

		// Get the input stream from process to read the output of the script
		BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
		String readLine;

		while ((readLine = reader.readLine()) != null) {	// read output statements from the .py file
			System.out.println(readLine);
		}

		// Wait for the process to complete
		int exitCode = process.waitFor();
		System.out.println("Exited with code: " + exitCode);  // 0 - Code ran succesfully; 1 - Failed
	}

		// 
		catch (Exception e) {
			e.printStackTrace();	// print error that occured
		}
	}

	public String get() {	// runs the python file and receives a String
		try {
			// Create a ProcessBuilder instance
			ProcessBuilder pb = new ProcessBuilder("python3", mainDir + file, ticker);	// Initialize command for ProcessBuilder
																				
			Process process = pb.start();										// Start the process; execute command

			// Get the input stream from process to read the output of the script
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String readLine;
			StringBuilder buffer = new StringBuilder();	// used to save String

			while ((readLine = reader.readLine()) != null) {	// read output statements from the .py file
				buffer.append(readLine);
			}

			// Wait for the process to complete
			int exitCode = process.waitFor();
			System.out.println("Exited with code: " + exitCode);  // 0 - Code ran succesfully; 1 - Failed

			return buffer.toString();
		}

		// 
		catch (Exception e) {
			e.printStackTrace();	// print error that occured
			return "";
		}
	}
}

