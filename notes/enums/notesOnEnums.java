
public class EnumTutorial {

	public static void main(String[] args) {

		// initiating day with THURSDAY enum
		DaysOfTheWeek day = DaysOfTheWeek.THURSDAY;

		if (day == DaysOfTheWeek.THURSDAY) {
			System.out.println("Yay it's almost Friday!");
		}

		// print every value in DaysOfTheWeek enum
		for (DaysOfTheWeek myDay: DaysOfTheWeek.values()) {
			System.out.println(myDay);
		}

		// accessing values within enum values
		System.out.println(Cereals.FROOT_LOOPS.levelOfDeliciousness);
		System.out.println(Cereals.FROOT_LOOPS.price);

	}
}