import java.util.HashMap;

/*

Scratch notes:
	- we use HashMap instead of Map because Map is an interface. Hash map is an implementation of Map
	- Java Classes must be used in <type, type> such as Integer, Double, etc. Not primitive types like
		int, double, etc.
*/


public class Maps {
	public static void main(String[] args) {

		// Mapping <Strings to Integers>
		// example - Employee : ID number / "Jake" : 2342
		HashMap<String, Integer> empIds = new HashMap<>();

		// adding a new mapping key/value
		empIds.put("John", 12345);
		empIds.put("Carl", 54321);
		empIds.put("Jerry", 897994);

		// printing map (there is no ordering)
		System.out.println(empIds);

		// printing a value using .get()
		System.out.println(empIds.get("Carl"));

		// check to see if key exists in map
		System.out.println(empIds.containsKey("George"));

		// see if map contains a value
		System.out.println(empIds.containsValue(12345));

		// if value exists, value will be overwritten
		empIds.put("John", 98765);
		System.out.println(empIds);

		// if value does not exist, new key/value pair created
		empIds.put("michael", 22222);
		System.out.println(empIds);
		
		// replace() replaces a value
		empIds.replace("John", 777);
		System.out.println(empIds);

		// replace() but if key does not exists, hashmap is not updated
		empIds.replace("Kramer", 2424242);
		System.out.println(empIds);

		// only puts if key is absent, otherwise value is not updated
		empIds.putIfAbsent("John", 222);
		System.out.println(empIds);

		// if key is absent, put()
		empIds.putIfAbsent("Steve", 34444);
		System.out.println(empIds);

		// removing key/value pair
		empIds.remove("Steve");
		System.out.println(empIds);
	}
}