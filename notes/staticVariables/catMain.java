public class catMain {

	public static void main(String[] args) {
		System.out.println("number of cats: " + Cat.getCatCount()); // use Cat class to access static variable
												// cat count is not associated with an instance of Cat,
												// but with the class itself.
		Cat myCat = new Cat("Samwise Gamgee");

		System.out.println("number of cats: " + Cat.getCatCount());

		Cat yourCat = new Cat("Alexander the Great");

		System.out.println("number of cats: " + Cat.getCatCount());
	}
}