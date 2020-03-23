package fa.nfa;

import java.util.HashMap;

/**
 * 
 * @author Kyle Tupper and Benjamin Clark
 * 
 * A class to represent a single state in the Non-deterministic Finite Automata
 *
 */
public class NFAState extends fa.State {
	
	boolean isFinal;
	private HashMap<Character, NFAState> delta;
	
	/**
	 * Constructor for a non-final state
	 * @param name of NFAState
	 */
	public NFAState(String name) {
		
		this.name = name;
		delta = new HashMap<Character, NFAState>();
	}
	
	/**
	 * Constructor for a final-state
	 * @param name of final state
	 * @param isFinal is true if the state is a final state
	 */
	public NFAState(String name, boolean isFinal) {
		
		this.name = name;
		this.isFinal = isFinal;
		delta = new HashMap<Character, NFAState>();
	}
	
	/**
	 * adds a transition from that with a character
	 * @param onsymb character from the NFA alphabet
	 * @param name	Name of state
	 */
	public void addTransition(char onsymb, NFAState name) {
		delta.put(onsymb, name);
	}
	
	/**
	 * Gets to a certain state depending upon the alphabet symbol
	 * @param onsymb alphabet symbol used for transition
	 */
	public void getTo(char onsymb) {
		delta.get(onsymb);
	}
}
