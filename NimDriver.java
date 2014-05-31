//-----------------------------------------------------------------------------------
//	Class: NimDriver.java	programmer: Nadya Pena
//
//	Description: Simulates a game of Nim
//-----------------------------------------------------------------------------------
import java.util.Scanner;
import java.lang.Math;
import java.util.Random;

public class NimDriver{

	public static void main(String[] argsv){

		int stones_to_take = 0, maxrange = 0;
		Random randomobj = new Random();
		String player1, player2 = "";
		String activePlayer = "";
		String inactivePlayer = "";
		Scanner scan = new Scanner(System.in);
		System.out.println("Hello, enter the number of stones to use.");
		int totalStones = scan.nextInt();

		//Check that the number of stones is > 1 
		while(totalStones == 1){

			if(totalStones == 1){
				System.out.println("Can't play with just one stone! Pick a different number.");
				totalStones = scan.nextInt();
			}
			else{
				System.out.println("So you want to play with " + totalStones + " stones...OK!");
				System.out.println("Let's begin.\n");
			}
		}

		Nim nimgame = new Nim();

		//------------------------------
		//set the number of stones
		//------------------------------
		nimgame.setStones(totalStones);

		//--------------------------------------
		//Semi-randomly choose who goes first
		//--------------------------------------
		activePlayer = nimgame.assignPlayers();

		if(activePlayer.equals("AI")){
			System.out.println("I will go first.");
		}
		else{
			System.out.println("You go first");
		}
		//-------------------------------------------------------------
		// While there are still more than 1 stone left, play the game
		// 1. Play the game
		// 2. Calculate the maximum range of stones to take
		// 3. If the maximum stones that we can take is 1, take 1.
		// 4. If the max range is greater than 1, 
		//-------------------------------------------------------------
		while(totalStones > 1){

			if(activePlayer.equals("AI")){
				
				nimgame.playGame(activePlayer, totalStones);
				/*maxrange = (int)(totalStones/2); //max stones to take
				if(maxrange == 1)
					stones_to_take = 1;

				if(maxrange > 1)
					stones_to_take = randomobj.nextInt(maxrange);*/
				
				//System.out.println("the number of stones I'm taking is: " + stones);
				if (totalStones > 1){
					stones_to_take = nimgame.aiTakeStones(totalStones);
					System.out.println("I will take " + stones_to_take);
					totalStones = totalStones - stones_to_take;
					System.out.println("There are now " + totalStones + " stones remaining\n");
					activePlayer = nimgame.switchPlayers(activePlayer);
				}
			}
			else{
				System.out.println("How many stones will you take?");
				stones_to_take = scan.nextInt();

				if(stones_to_take > (totalStones/2)){
					System.out.println("Can't take more than half the stones! Try again.");
					stones_to_take = scan.nextInt();
				}

				totalStones = nimgame.humanTakeStones(totalStones);
				System.out.println("There are now " + totalStones + " stones remaining.");
				activePlayer = nimgame.switchPlayers(activePlayer);
			}
		}
	}	
}
