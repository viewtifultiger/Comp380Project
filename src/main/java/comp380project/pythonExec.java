package comp380project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;

public class pythonExec {

    String file;        // Contains the Python file name
    String ticker;      // Contains the ticker symbol of stock
    final String mainDir = "/Users/biancaloera/Desktop/Stock Price Project/comp380project/src/main/pyscripts/";  // Absolute path to the location of Python scripts

    public pythonExec(String file, String ticker) {
        this.file = file;
        this.ticker = ticker;
    }

    public void start() {    // Runs the Python file, no return value
        try {
            // Create a ProcessBuilder instance
            ProcessBuilder pb = new ProcessBuilder("/Users/biancaloera/Desktop/Stock Price Project/myenv/bin/python3", mainDir + file, ticker);
            pb.redirectErrorStream(true);  // Combine standard error with standard output

            pb.directory(new File(mainDir)); // Set the working directory to the location of the Python scripts

            Process process = pb.start();   // Start the process; execute command

            // Get the input stream from process to read the output of the script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String readLine;

            while ((readLine = reader.readLine()) != null) {  // Read output statements from the .py file
                System.out.println(readLine);
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);  // 0 - Code ran successfully; 1 - Failed
        } catch (Exception e) {
            e.printStackTrace();    // Print error that occurred
        }
    }

    public String get() {    // Runs the Python file and receives a String
        try {
            // Create a ProcessBuilder instance
            ProcessBuilder pb = new ProcessBuilder("/Users/biancaloera/Desktop/Stock Price Project/myenv/bin/python3", mainDir + file, ticker);
            pb.redirectErrorStream(true);  // Combine standard error with standard output

            pb.directory(new File(mainDir)); // Set the working directory to the location of the Python scripts

            Process process = pb.start();   // Start the process; execute command

            // Get the input stream from process to read the output of the script
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String readLine;
            StringBuilder buffer = new StringBuilder();   // Used to save String

            while ((readLine = reader.readLine()) != null) {  // Read output statements from the .py file
                buffer.append(readLine).append("\n");
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);  // 0 - Code ran successfully; 1 - Failed

            System.out.println("Output:\n" + buffer.toString());  // Print the entire output, including errors
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();    // Print error that occurred
            return "";
        }
    }
}