/*
Resources:

ProcessBuilder: 	https://docs.oracle.com/javase/8/docs/api/java/lang/ProcessBuilder.html
Process: 			https://docs.oracle.com/javase/8/docs/api/java/lang/Process.html
BufferedReader: 	https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html
InputStreamReader: 	https://docs.oracle.com/javase/8/docs/api/java/io/InputStreamReader.html


Summary:
	We create a subprocess and initialize it with the command we want to run. In this case we will run "python testpythoncode.py" as
	if it were run on a terminal. We use a While loop to read outputs from python file until the .py file is finished.

	Note: data can only be transfered from the .py file to this .java file through the I/O stream (aka print() statements). We
	cannot directly return a value to this .java file from python. 

Usage:
	simply replace the "testpythoncode.py" on line 114 with the .py file of your choice (make sure that .py file is in the same folder)


================== Class: ProcessBuilder =========================================================================================

	Documentation:
	
		// Prototype: Public final class ProcessBuilder

		This class is used to create operating system processes.

		Each instance of a ProcessBuilder contains attributes.

		The start() method, (i.e. pb.start(): line 117), returns a new "Process" class instance with those attributes 
		 (which is why we use "Process process = pb.start()" below on line 117).

		Attributes of the "ProcessBuilder" class are used in relation to the "Process" class methods (more on that later)


	Attributes of ProcessBuilder used in this code:

	command :

		the command associated with the ProcessBuilder instance, initialized on line 114


================== Class: Process =================================================================================================
	
	Methods used in this example:
	- getInputStream()
	- waitFor()

	Documentation:
		
		// Prototype: Public abstract class Process

		ProcessBuilder.start() method creates a native process and returns that instance,
		 which we feed to the "Process" class so that it can be used to control inputs/outputs.

	Methods:

	- getInputStream()

		// Prototype: public abstract InputStream getInputStream()

		In our case, it returns the "command" attribute created with the "ProcessBuilder" contructor on line 114 

	- waitFor()

		// Prototype: public abstract int waitFor()
			throws InterruptedExpection

		causes the current thread to wait, until the subprocess has terminated. The method returns immediately if the 
		subprocess has already terminated. If not, the calling thread will be blocked untill the subprocess exits. 


================== Class: BufferedReader ==========================================================================================
	(imported)

	Methods used in this class:
	.readLine()

	Documentation

		// Prototype: public class BufferedReader

		reads text from a character-input stream, buffering to provide for efficient reading. The InputStreamReader(process.getInputStream())
		is passed into its constructor.

	Methods:

	- readLine()

		// Prototype: public String readLine()

		Reads a line of text. Line is terminated with '\n'

================== Class: InputStreamReader ======================================================================================
	(imported)

	Documentation:

		// Prototype: public class InputStreamReader extends Reader

		This class is a bridge from byte streams to character streams.
===================================================================================================================================
*/


import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RunPythonScript{
	public static void main(String[] args) {
		try {
			// Create a ProcessBuilder instance
			ProcessBuilder pb = new ProcessBuilder("python", "testpythoncode.py");	// Initialize command for ProcessBuilder
																						// replace testpythoncode.py with your file
			
			Process process = pb.start();	// Start the process

			// Get the input stream from process to read the output of the script
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;
			while ((line = reader.readLine()) != null) {	// read output statements from the .py file
				System.out.println(line);					// print output from .py file
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