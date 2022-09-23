import java.util.Scanner;

public class GUIPlayer extends Player {
	private Scanner scanner;
	
	public GUIPlayer(){
		super("Human");
		System.out.println("Hello Player!  Please enter your name.");
		scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		myName = name;
	}

	@Override
	public boolean rollAgain(int totalSoFar) {
		System.out.println("Score is "+myScore+ " Round score is "+totalSoFar);
		System.out.println("Do you want to roll again? Y/N");
//		I do not think this was listed on the docs, but it
//		bothered me so I fixed it. There was an error thrown
//		whenever no input was given when prompted for rolling again
		try {
			String answer = scanner.nextLine();
			return answer.toUpperCase().charAt(0) == 'Y';
		} catch(Exception e ) {
			System.out.println("Please enter Y or N\n");
			return rollAgain(totalSoFar);
		}
	}
//		return answer.toUpperCase().charAt(0) == 'Y';
}
