/**
 * 
 */

/**
 * @author Sage Hackbarth
 *
 */
public class Node {
	Room name;
	Node leftChil;
	Node rightChil;
	int place;
	
	/**
	 * 
	 */
	public void disNode() {
		System.out.println("{ " + place + ", " + name + " }");
	}
	
}
