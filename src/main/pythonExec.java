import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.InputStream;

public class pythonExec{

	String file;	// Contains the filePath that will be executed within this class
	String ticker;		// Contains the ticker symbol of desired stock
	final String mainDir = "./../pyscripts/";

	public pythonExec(String file, String ticker) {
		this.file = file;
		this.ticker = ticker;
	}

	public void start() {
		try {
			// Create a ProcessBuilder instance
			ProcessBuilder pb = new ProcessBuilder("python", mainDir + file, ticker);	// Initialize command for ProcessBuilder
																				
			// pb.directory(new File(this.homeDirectory));								// Initialize directory of ProcessBuilder
			Process process = pb.start();										// Start the process; execute command

			// Get the input stream from process to read the output of the script
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String readLine;

			while ((readLine = reader.readLine()) != null) {	// read output statements from the .py file
				InputStream error = process.getErrorStream();
				// for (int i = 0; i < error.available(); i++) {
				// 	System.out.println("" + error.read());
				// }
				System.out.println(readLine);
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

