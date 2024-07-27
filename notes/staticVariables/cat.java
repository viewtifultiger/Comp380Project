public class Cat {

	private static int catCount = 0;	// private so that users cannot manipulate value
										// static value is associated with the class itself

	// these attributes only apply to the class object, not the class itself
	String name;
	int age;
	int livesRemaining;

	public Cat(String name) {
		this.name = name;
		catCount++;
	}

	public static int getCatCount() {
		return catCount;
	}

	public void meow() {
		System.out.print("Meow");
	}
}