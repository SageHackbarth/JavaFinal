import java.util.Stack;

/**
 * 
 */

/**
 * @author Sage Hackbarth
 *
 */
public class Tree {
	private Node root;
	//String[] names = new String[] {"William", "Mason", "Alexander", "Liam", "Jacob", "James", "Michael", "Noah", "Ethan", "Benjamin"};
	//String[] namesg = new String[] {"Isabella", "Sophia", "Emily", "Olivia", "Ava", "Abigail", "Charlotte", "Emma", "Mia", "Harper"};
	//int[] places = new int[]{5, 3, 8, 2, 4, 7, 9, 1, 6, 10};
	
	/**
	 * 
	 */
	public Tree() {
		root = null;
	}
	
	/**
	 * @param place
	 * @return
	 */
	public Node find(int place) {
		Node current = root;
		while(current.place != place) {
			if(place < current.place) {
				current = current.leftChil;
			} else {
				current = current.rightChil;
			}
			if (current == null) {
				return null;
			}
		}
		return current;
	}
	
	/**
	 * @param lace
	 * @param ame
	 */
	public void insert(int lace, Room room) {
		//System.out.println("*Hacker voice* I'm in");
		Node newNode = new Node();
		newNode.place = lace;
		newNode.name = room;
		if(root == null) {
			root = newNode;
		} else {
			Node current = root;
			Node parent;
			while(true) {
				parent = current;
				if(lace < current.place) {
					current = current.leftChil;
					if(current == null) {
						parent.leftChil = newNode;
						return;
					} 
				}else {
					current = current.rightChil;
					if(current == null) {
						parent.rightChil = newNode;
						return;
					}
				}
			}
		}
	}
//End insert
	/**
	 * @param place
	 * @return
	 */
	public boolean delete(int place) {
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		
		while(current.place != place) {
			parent = current;
			if(place < current.place) {
				isLeftChild = true;
				current = current.leftChil;
			} else {
				isLeftChild = false;
				current = current.rightChil;
			}
			if(current == null) {
				return false;
			}
		}
		
		if((current.leftChil == null) && (current.rightChil == null)) {
			if(current == root) {
				root = null;
			} else if(isLeftChild) {
				parent.leftChil = null;
			} else {
				parent.rightChil = null;
			}
		} else if(current.rightChil == null) {
			if(current == root) {
				root = current.leftChil;
			} else if(isLeftChild) {
				parent.leftChil = current.leftChil;
			} else {
				parent.rightChil = current.leftChil;
			}
		} else if(current.leftChil == null) {
			if(current == root) {
				root = current.rightChil;
			} else if(isLeftChild) {
				parent.leftChil = current.rightChil;
			} else {
				parent.rightChil = current.rightChil;
			}
		} else {
			Node successor = getSuccessor(current);
			
			if(current == root) {
				root = successor;
			} else if(isLeftChild) {
				parent.leftChil = successor;
			} else {
				parent.rightChil = successor;
			}
			
			successor.leftChil = current.leftChil;
		}
		return true;
	}
	//End delete
	
	/**
	 * @param deNode
	 * @return
	 */
	private Node getSuccessor(Node deNode) {
		Node successorParent = deNode;
		Node successor = deNode;
		Node current = deNode.rightChil;
		while(current != null) {
			successorParent = successor;
			successor = current;
			current = current.leftChil;
		}
		
		if(successor != deNode.rightChil) {
			successorParent.leftChil = successor.rightChil;
			successor.rightChil = deNode.rightChil;
		}
		return successor;
	}
	//End getSucc
	
	/**
	 * @param traverseType
	 */
	public void traverse(int traverseType) {
		switch(traverseType) {
			case 1: System.out.print("\nPreorder traversal: ");
					preOrder(root);
					break;
			case 2: System.out.print("\nInorder traversal: ");
					inOrder(root);
					break;
			case 3: System.out.print("\nPostorder traverasal: ");
					postOrder(root);
					break;
		}
		System.out.println();
	}
	//End traverse
	
	/**
	 * @param localRoot
	 */
	private void preOrder(Node localRoot) {
		if(localRoot != null) {
			System.out.println(localRoot.place + " ");
			preOrder(localRoot.leftChil);
			preOrder(localRoot.rightChil);
		}
	}
	/**
	 * @param localRoot
	 */
	private void inOrder(Node localRoot) {
		if(localRoot != null) {
			preOrder(localRoot.leftChil);
			System.out.println(localRoot.place + " ");
			preOrder(localRoot.rightChil);
		}
	}
	/**
	 * @param localRoot
	 */
	private void postOrder(Node localRoot) {
		if(localRoot != null) {
			preOrder(localRoot.leftChil);
			preOrder(localRoot.rightChil);
			System.out.println(localRoot.place + " ");
		}
	}
	
	/**
	 * 
	 */
	public void displayTree() {
		Stack<Node> globalStack = new Stack<Node>();
		globalStack.push(root);
		int nBlanks = 0;
		boolean isRowEmpty = false;
		System.out.println("..............................................");
		
		while(isRowEmpty == false) {
			Stack<Node> localStack = new Stack<Node>();
			isRowEmpty = true;
			
			for(int j = 0; j < nBlanks; j++) {
				System.out.println(" ");
			}
			while(globalStack.isEmpty() == false) {
				Node temp = (Node)globalStack.pop();
				if(temp != null) {
					System.out.println("Place: " + temp.place + ", Name: " + temp.name);
					localStack.push(temp.leftChil);
					localStack.push(temp.rightChil);
					
					if((temp.leftChil != null) || (temp.rightChil != null)) {
						isRowEmpty = false;
					} 
				} else {
					System.out.println("--");
					localStack.push(null);
					localStack.push(null);
				}
				
				for(int j = 0; j < nBlanks*2-2; j++) {
					System.out.println(' ');
				}
			}
			
			System.out.println();
			nBlanks /= 2;
			while(localStack.isEmpty() == false) {
				globalStack.push(localStack.pop());
			}
		} //End while(row is empty)
		System.out.println("................................................");
	} //End display Tree
	
}
