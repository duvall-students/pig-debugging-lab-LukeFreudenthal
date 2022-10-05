
public abstract class Player {

	protected String myName;
	protected int myScore;
	private final int WIN_SCORE = 100;
	
	public Player(String myName){
		myScore = 0;
		/*
		 * Corrected from assigning the name "R2D2" in the
		 * computer class, to assignment in the player constructor
		 * which will be passed into the computer class
		 */
		this.myName = myName;
	}
	
	// Each player must provide logic for deciding to roll again
	public abstract boolean rollAgain(int totalSoFar);
	
	public String toString(){
		return myName+": "+myScore;
	}
	
	public boolean hasWon(){
		return myScore >= WIN_SCORE;
	}
	
	public void resetScore(){
		myScore = 0;
	}
	
	public void addToScore(int thisRound){
		myScore += thisRound;
	}
	
	public String getName(){
		return myName;
	}
}
