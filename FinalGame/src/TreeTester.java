/**
 * 
 */
import java.util.Scanner;
/**
 * @author Sage Hackbarth
 *
 */
public class TreeTester {
	
	/**
	 * @param inv
	 */
	public static void sort(String inv[])
	{
		int n = inv.length;
		for (int i = 0; i < n-1; i++)
			for (int j = 0; j < n-i-1; j++)
				if (inv[j].compareTo(inv[j+1]) < 0)
				{
					String temp = inv[j];
					inv[j] = inv[j+1];
					inv[j+1] = temp;
				}
	}
	
	/**
	 * @param in
	 * @param choice
	 * @param play
	 */
	public static void firRoom(Scanner in, int choice, Player play) {
		Room firstRoom = new Room("This room is lit with torches on the left, right, and back wall. The walls are all marble white and glisten with the shifting flames of the torches."
				+ " You see two doors straight ahead. If you want to go through the left(1) or right(2) door, type the corresponding number.");
		firstRoom.forward = "You run straight into a wall. You then make your way back to the enterance. (You might want to look where you are going)";
		
		System.out.println("What would you like to do? Go Forward(1), Look(2)");
		choice = in.nextInt();
		while((choice < 1) || (choice > 2)) {
			System.out.println("Please enter in 1 or 2");
			choice = in.nextInt();
		}
			
		if(choice == 1) {
			System.out.println(firstRoom.forward);
			firRoom(in, choice, play);
		} else if(choice == 2) {
			System.out.println(firstRoom.descript);
			firstNext(in, choice, firstRoom, play);
		}
	}
	
	/**
	 * @param in
	 * @param choice
	 * @param play
	 */
	public static void reRoom(Scanner in, int choice, Player play) {
		Room redRoom = new Room("This room is filled with light from a lamp set on a 3ft tall dresser. The walls are painted a deep red, and a royal bed with draping white silk cloth hanging from the post."
				+ " You see two doors straight ahead. If you want to go through the left(1) or right(2) door, type the corresponding number.");
		redRoom.forward = "You hit your shin on what feels like a bed frame. After five minutes of painful gasping you slowly make your way back to the enterance.";
		
		System.out.println("What would you like to do? Go Forward(1), Look(2)");
		choice = in.nextInt();
		while((choice < 1) || (choice > 2)) {
			System.out.println("Please enter in 1 or 2");
			choice = in.nextInt();
		}
			
		if(choice == 1) {
			System.out.println(redRoom.forward);
			reRoom(in, choice, play);
		} else if(choice == 2) {
			System.out.println(redRoom.descript);
			reNext(in, choice, redRoom, play);
		}
	}
	
	/**
	 * @param in
	 * @param choice
	 * @param play
	 */
	public static void natRoom(Scanner in, int choice, Player play) {
		Room natureRoom = new Room("This room has natural moon light because there's no ceiling. A big tree grows from the middle of the area up above the roof."
				+ " You see two doors straight ahead. If you want to go through the left(1) or right(2) door, type the corresponding number.");
		natureRoom.forward = "You hit your face really hard on a ruff and jagged surface. After rubbing the new bruises and scrapes on your face and hands, you make your way back to the entrance.";
		
		System.out.println("What would you like to do? Go Forward(1), Look(2)");
		choice = in.nextInt();
		while((choice < 1) || (choice > 2)) {
			System.out.println("Please enter in 1 or 2");
			choice = in.nextInt();
		}
			
		if(choice == 1) {
			System.out.println(natureRoom.forward);
			natRoom(in, choice, play);
		} else if(choice == 2) {
			System.out.println(natureRoom.descript);
			natNext(in, choice, natureRoom, play);
		}
	}
	
	/**
	 * @param in
	 * @param choice
	 * @param firstRoom
	 * @param play
	 */
	public static void firstNext(Scanner in, int choice, Room firstRoom, Player play) {
		System.out.println("Would you like to go through the left(1), right(2), or search(3) door?");
		choice = in.nextInt();
		while((choice < 1) || (choice > 3)) {
			System.out.println("Please enter in 1, 2, or 3");
			choice = in.nextInt();
		}
		if(choice == 1) {
			reRoom(in, choice, play);
		} else if(choice == 2) {
			natRoom(in, choice, play);
		} else if(play.inv[0] == "axe") {
			System.out.println("You already got the axe in this area. Maybe one of the next rooms has something?");
			firstNext(in, choice, firstRoom, play);
		} else if(choice == 3) {
			System.out.println("You look around and find something shiny.");
			System.out.println("You found an axe!");
			for(int i = 0; i < play.inv.length; i++) {
				if(play.inv[i] == "Empty") {
					play.inv[1] = "axe";
					sort(play.inv);
					break;
				}
			}
			firstNext(in, choice, firstRoom, play);
		}
	}
	
	/**
	 * @param in
	 * @param choice
	 * @param redRoom
	 * @param play
	 */
	public static void reNext(Scanner in, int choice, Room redRoom, Player play) {
		System.out.println("Would you like to go through the left door(1), right door(2), search(3), or go back(4)?");
		choice = in.nextInt();
		while((choice < 1) || (choice > 4)) {
			System.out.println("Please enter in 1, 2, 3, or 4");
			choice = in.nextInt();
		}
		if(choice == 1) {
			System.out.println("There is a sign that says'Buy the DLC to proceed'.");
			reNext(in, choice, redRoom, play);
		} else if(choice == 2) {
			System.out.println("There is a sign that says'Buy the DLC to proceed'.");
			reNext(in, choice, redRoom, play);
		} else if(choice == 3) {
			System.out.println("You find an off switch. I wonder what it does?");
			for(int i = 0; i < play.inv.length; i++) {
				if(play.inv[i] == "Empty") {
					play.inv[1] = "Switch";
					sort(play.inv);
				}
			}
			System.out.println("Press it? (y/n)");
			String choose = in.nextLine();
			choose = in.nextLine();
			choose = choose.toUpperCase();
			if(choose.equals("Y")) {
				System.out.println("The game has ended in the best way anyone could think of; eternal darkness.");
				System.out.println("Your character is still in that room, but you don't have to play any more. :)");
			} else {
				reNext(in, choice, redRoom, play);
			}
		} else if(choice == 4) {
			firRoom(in, choice, play);
		}
	}
	
	/**
	 * @param in
	 * @param choice
	 * @param natureRoom
	 * @param play
	 */
	public static void natNext(Scanner in, int choice, Room natureRoom, Player play) {
		boolean axes = false;
		for(int i = 0; i < play.inv.length; i++) {
			if(play.inv[i] == "axe") {
				axes= true;
			}
		}
		if(axes) {
			System.out.println("Would you like to go through the left door(1), right door(2), search(3), go back(4), or use axe(5)?");
			choice = in.nextInt();
			while((choice < 1) || (choice > 5)) {
				System.out.println("Please enter in 1, 2, 3, 4, or 5");
				choice = in.nextInt();
			}
			if(choice == 1) {
				System.out.println("The door is locked. Hmm, there must be somewhere else to go.");
				natNext(in, choice, natureRoom, play);
			} else if(choice == 2) {
				System.out.println("The door is locked. Hmm, there must be somewhere else to go.");
				natNext(in, choice, natureRoom, play);
			} else if(choice == 3) {
				System.out.println("There doesn't apear to be anything in this room of use.");
				natNext(in, choice, natureRoom, play);
			} else if(choice == 4) {
				firRoom(in, choice, play);
			} else if(choice == 5) {
				System.out.println("The tree in the middle of the room was felled by your mighty swing.");
				System.out.println("You can now walk into the room over.");
				natNext1(in, choice, natureRoom, play); 
			}
		} else {
			System.out.println("Would you like to go through the left door(1), right door(2), search(3), or go back(4)/");
			choice = in.nextInt();
			while((choice < 1) || (choice > 4)) {
				System.out.println("Please enter in 1, 2, 3, or 4");
				choice = in.nextInt();
			}
			if(choice == 1) {
				System.out.println("The door is locked. Hmm, there must be somewhere else to go.");
				natNext(in, choice, natureRoom, play);
			} else if(choice == 2) {
				System.out.println("The door is locked. Hmm, there must be somewhere else to go.");
				natNext(in, choice, natureRoom, play);
			} else if(choice == 3) {
				System.out.println("There doesn't apear to be anything in this room of use.");
				natNext(in, choice, natureRoom, play);
			} else if(choice == 4) {
				firRoom(in, choice, play);
			} 
		}
	}
	
	/**
	 * @param in
	 * @param choice
	 * @param natureRoom
	 * @param play
	 */
	public static void natNext1(Scanner in, int choice, Room natureRoom, Player play) {
		System.out.println("Would you like to go through the left door(1), right door(2), search(3), go back(4), or go to the next door room(5)?");
		choice = in.nextInt();
		while((choice < 1) || (choice > 5)) {
			System.out.println("Please enter in 1, 2, 3, 4, or 5");
			choice = in.nextInt();
		}
		if(choice == 1) {
			System.out.println("The door is locked. Hmm, there must be somewhere else to go.");
			natNext1(in, choice, natureRoom, play);
		} else if(choice == 2) {
			System.out.println("The door is locked. Hmm, there must be somewhere else to go.");
			natNext1(in, choice, natureRoom, play);
		} else if(choice == 3) {
			System.out.println("There doesn't apear to be anything in this room of use.");
			natNext1(in, choice, natureRoom, play);
		} else if(choice == 4) {
			firRoom(in, choice, play);
		} else if(choice == 5) {
			reRoom(in, choice, play);
		}
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Tree boyTree = new Tree();
		Player play = new Player();
		int choice = 0;
		
		play.inv[0] = "Empty"; 
		play.inv[1] = "Empty";
		play.inv[2] = "Book of magical monsters and where to find them";
		play.inv[3] = "Scrap of paper";
		play.inv[4] = "Pen";
		/* for(int i = 0; i < play.inv.length; i++) {
			System.out.println(play.inv[i]);
		} */
		sort(play.inv);
		/*for(int i = 0; i < play.inv.length; i++) {
			System.out.println(play.inv[i]);
		} */
		
		Room firstRoom = new Room("This room is lit with torches on the left, right, and back wall. The walls are all marble white and glisten with the shifting flames of the torches."
				+ " You see two doors straight ahead. If you want to go through the left(1) or right(2) door, type the corresponding number.");
		Room redRoom = new Room("This room is filled with light from a lamp set on a 3ft tall dresser. The walls are painted a deep red, and a royal bed with draping white silk cloth hanging from the post."
				+ " You see two doors straight ahead. If you want to go through the left(1) or right(2) door, type the corresponding number.");
		Room natureRoom = new Room("This room has natural moon light because there's no ceiling. A big tree grows from the middle of the area up above the roof."
				+ " You see two doors straight ahead. If you want to go through the left(1) or right(2) door, type the corresponding number.");
		
		firstRoom.forward = "You run straight into a wall. You then make your way back to the enterance. (You might want to look where you are going)";
		redRoom.forward = "You hit your shin on what feels like a ben frame. After five minutes of painful gasping you slowly make your way back to the enterance.";
		natureRoom.forward = "You hit your face really hard on a ruff and jagged surface. After rubbing the new bruises and scrapes on your face and hands, you make your way back to the entrance.";
		
		boyTree.insert(5, firstRoom);
		boyTree.insert(3, redRoom);
		boyTree.insert(8, natureRoom);
		
		firRoom(in, choice, play);
		
	}
}
//I couldn't figure out how to tansfer the tree itself into the functions so i improvised. 
//Credit to GeeksforGeeks for the BubbleSort
