
public enum Cereals {

	CAPTAIN_CRUNCH(50, 2.90),
	FROOT_LOOPS(60, 3.40),
	REESES_PUFFS(100, 4.00),
	COCOA_PEBBLES(75, 2.10);

	// use final if you don't want value changed
	final int levelOfDeliciousness;
	final double price;

	// contstruct that adds values to enums
	Cereals(int levelOfDeliciousness, double price) {
		this.levelOfDeliciousness = levelOfDeliciousness;
		this.price = price;
	}

}