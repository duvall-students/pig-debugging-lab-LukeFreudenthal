import java.util.Random;

public class Game {
	// NullPointerException was thrown because GUIPlayer and ComputerPlayer
	// were assigned to the incorrect player1 and player2 Player objects
	Player player1 = new GUIPlayer();
	Player player2 = new ComputerPlayer();
	private Random die;
	private Spinner spinner;
//	Changed from "GRUNT" back to "grunt",
//	deemed unnecessary of a change
	private final String LOSER_SPIN = "grunt";
	private final int LOSER_ROLL = 1;
	
	public Game(){
//		Player player1 = new GUIPlayer();
//		Player player2 = new ComputerPlayer();
		die = new Random();
		spinner = new Spinner();
	}
	
	/*
	 * Method will play one game between the players.
	 */
	public void playGame(){
		printStartGameMessage();
		Player whoseTurn = player1;
		while(!winner()){
			int roundScore = takeATurn(whoseTurn);
			whoseTurn.addToScore(roundScore);
			// Switch whose turn it is.
			if(whoseTurn == player1){
				whoseTurn = player2;
			}
			else{
				whoseTurn = player1;
			}
		}
		printEndGameMessage();
	}
	
	/*
	 * One player's turn.  Ends because
	 * - roll a 1
	 * - spin a "GRUNT"
	 * - or Player decides to stop
	 * 
	 * Return the number of points to add to the player
	 */
	public int takeATurn(Player whoseTurn){
		int roundScore = 0;
		boolean keepGoing = true;
		printStartRoundMessage(whoseTurn);
		while(keepGoing){
//			int roll = die.nextInt(7);
//			The issue was that it was 0(inclusive) to 7(exclusive),
//			the change below turns any 0 into a 1 and any 5 into a 6,
//			thus creating the correct range of 1-6 (inclusive)
			int roll = die.nextInt(6) + 1;
			String spin = spinner.spin();
			System.out.println(roll+ " "+ spin);
			
			if(roll == LOSER_ROLL){
//			The issue was that loser roll check did not
//			include a check for loser spin.
//			Added a check within the loser roll check
//			to see if the loser spin is also true.
//			Also added .toLowerCase() to match loser spins	
//			and changed from == to .equals()
				if(spin.toLowerCase().equals(LOSER_SPIN)){
					System.out.println("Too bad!  Lose all your points and lose a turn.");
					whoseTurn.resetScore();
					return 0;
				} else {
					System.out.println("Lose a turn.");
					return 0;
				}
			}
//			Changed spin to lower case and changed from mathematical comparison
//			to string comparison with .equals()
			else if(spin.toLowerCase().equals(LOSER_SPIN)){
				System.out.println("Too bad!  Lose all your points.");
				whoseTurn.resetScore();
				return 0;
			}
			else{
				roundScore = roundScore + roll;
				System.out.println("Round total is currently: "+roundScore);
				keepGoing = whoseTurn.rollAgain(roundScore);
			}
		}
		return roundScore;
	}
	
	// True if one of the players has won the game.
	public boolean winner(){
//		return player1.hasWon() && player2.hasWon();
//		Issue was that it required both players to win,
//		changing 'and' to 'or' solves this 
		return player1.hasWon() || player2.hasWon();
	}
	
	/* 
	 * These methods are for printing messages to the console to follow the game.
	 */
	public void printStartRoundMessage(Player whoseTurn){
		System.out.println("New Round!  "+ whoseTurn.getName()+" 's turn."); 
		System.out.println(player1);
		System.out.println(player2);
	}
	
	public void printEndGameMessage(){
		if(player1.hasWon()){
			System.out.println("Winner is "+player1.getName());
		}
		else{
			System.out.println("Winner is "+player2.getName());
		}
	}
	
	public void printStartGameMessage(){
		System.out.println("Let's Play Pig!");
	}
}
