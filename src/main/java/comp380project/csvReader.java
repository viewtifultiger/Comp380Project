package comp380project;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class csvReader
{

	public static void read(String ticker) {
		try {
			String line;

			//made changes to the next line bc it was giving me an error
			BufferedReader br = new BufferedReader(new FileReader("./../csv/" + ticker + ".pkl"));

			while((line = br.readLine()) != null) {
				String[] values = line.split(",");

				for(String value: values) {
					System.out.println(value);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}