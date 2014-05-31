//-----------------------------------------------------------------------------------
// Class: nim.java	programmer: Nadya Pena
//
//	Description: Defines a game of nim
//-----------------------------------------------------------------------------------
import java.lang.Math;
import java.util.Random;
import java.util.Scanner;

public class Nim{

	private int stones, stones_taken, stones_to_take;
	private String activePlayer;

	//-------------------------------------------------------
	//set the number of stones
	//-------------------------------------------------------
	public void setStones (int total){
		stones = total;
	}
	//-------------------------------------------------------
	//Take stones (for the human player)
	//-------------------------------------------------------
	public int takeStones(int totalstones,int stones_taken){
		stones = totalstones - stones_taken;
		return stones;
	}
	//-------------------------------------------------------
	//win State
	//-------------------------------------------------------
	public void checkWinState(){

	}
	//-------------------------------------------------------
	//lose
	//-------------------------------------------------------
	public void checkLoseState(){

	}
	//-------------------------------------------------------
	//Set Players - chooses who is first
	//-------------------------------------------------------
	public String assignPlayers(){

		String inactivePlayer = "";
		String activePlayer = "";

		double randomNumber = Math.random();
		if (randomNumber >= 0.5){
			activePlayer = "AI";
			inactivePlayer = "Human";
		} else{
			activePlayer = "Human";
		}
		return activePlayer;
	}
	//--------------------------------------------
	//Switch players
	//--------------------------------------------
	public String switchPlayers(String player){
		if(player.equals("AI")){
			player = "Human";
		}
		else{
			player = "AI";
		}
		return player;
	}
	//---------------------------------------------------------------
	//play the game
	//the AI will want to leave the human with 1 + some power of 2.
	// powers of 2 benefit the activePlayer. 
	//---------------------------------------------------------------
	public void playGame(String player, int s){ //this method looks like hell

		int max_to_take = 0;
		player = activePlayer;
		s = stones; // total number of stones to choose from 
		int remaining_stones = 0;

		if(activePlayer.equals("AI")){

			aiTakeStones(s); // execute the algorithm for the AI.
		}
	}
	//------------------------------------------------------------
	// Calculate the number of stones the human player may take
	// Input: Total stones in game 	Output: Stones to take	
	//------------------------------------------------------------
	public int humanTakeStones(int stones){

		int max_to_take = stones/2;
		Scanner humanScanner = new Scanner(System.in);
		stones_to_take = humanScanner.nextInt();

		while(stones_to_take == 0 || stones_to_take > max_to_take){

			System.out.println("You can't choose to take " + stones_to_take + ". Try again.");
			stones_to_take = humanScanner.nextInt();
		}
		return stones_to_take;
	}
	//-----------------------------------------------------------------------------
	//calculate the best number of stones to take
	// At every number, calc. how many stones should take to get power of 2 - 1
	// default maximum stones to take (max_to_take) is half. Go from there.
	// Goals of the AI:
	//		1) Leave player with 3 items
	//		2) Leave the player with a power of 2 - 1 so it can have winning strat.
	//			AVOID LEAVING 4 AT ALL COSTS. 4 is automatic loss.
	//-----------------------------------------------------------------------------
	public int aiTakeStones(int t){

		int remaining_stones;
		int max_to_take = (int)stones/2; //default start point for choosing how many to take
		int max_power = 0;
		int product = 0;
		//boolean isPowerTwo;

		for(int i = max_to_take; i >= 1; i--){ //start optimizing how many to take

			remaining_stones = stones - i;

			// Check a few classic cases 
			// Check if the remaining stones is 3
			if(remaining_stones == 3){
				stones_to_take = i;
			} else if(remaining_stones == 5){
				stones_to_take = i - 2; //make it so we leave 7 instead of 5.
			} else if(remaining_stones == 4){
				stones_to_take = i - 3; //make it so we don't leave 4, or 5. Leave 7.
			} else if(remaining_stones == 2){
				stones_to_take = i - 1; //make it so we leave 3.
			}
			//-------------------------------------------------------------------
			// If none of the previous cases applies  and the number of stones
			// is multiple of 2, check if power of 2, check if it's a power of 2
			// Important to know if power of 2 because of advantage of power 2 -1
			//-------------------------------------------------------------------
			else if(remaining_stones % 2 == 0 && isPowerTwo(remaining_stones)){
				//If power of two, then take one stone. 
				stones_to_take = 1;
			}
		}
		return stones_to_take;
	}
	//------------------------------------------------------------------
	// Check if the remaining stones are power two
	// Input: int, Total stones 	output: Boolean, isPowerTwo
	//------------------------------------------------------------------
	private boolean isPowerTwo(int stones){

		boolean isPowerTwo = false;
		int product = 1;
		int half_of_stones = (int)stones/2;

		for(int i = 1; i < half_of_stones; i++){

			product = 2 * product;

			if(product == stones){
				isPowerTwo = true;
				break;
			}
		} 
		return isPowerTwo;
	}
}