package fa.nfa;

import java.util.HashMap;
import java.util.LinkedHashSet;

/**
 * 
 * @author Kyle Tupper and Benjamin Clark
 * 
 * A class to represent a single state in the Non-deterministic Finite Automata
 *
 */
public class NFAState extends fa.State {
	
	boolean isFinal;
	private HashMap<Character, LinkedHashSet<NFAState>> delta;
	
	/**
	 * Constructor for a non-final state
	 * @param name of NFAState
	 */
	public NFAState(String name) {
		
		initDefault(name);
		isFinal = false;
	}
	
	/**
	 * Constructor for a final-state
	 * @param name of final state
	 * @param isFinal is true if the state is a final state
	 */
	public NFAState(String name, boolean isFinal) {
		
		initDefault(name);
		this.isFinal = isFinal;
	}
	
	private void initDefault(String name) {
		this.name = name;
		delta = new HashMap<Character, LinkedHashSet<NFAState>>();
	}
	
	/**
	 * adds a transition from that with a character
	 * @param onsymb character from the NFA alphabet
	 * @param name	Name of state
	 */
	public void addTransition(char onsymb, NFAState name) {
		LinkedHashSet<NFAState> states = delta.get(onsymb);
		
		if(states == null) {
			states = new LinkedHashSet<NFAState>();	
		}
			states.add(name);
			delta.put(onsymb, states);
		
	}
	
	/**
	 * Gets to a certain state depending upon the alphabet symbol
	 * @param onsymb alphabet symbol used for transition
	 */
	public LinkedHashSet<NFAState> getTo(char onsymb) {
		LinkedHashSet<NFAState> states = delta.get(onsymb);
		
		if(states == null) {
			return new LinkedHashSet<NFAState>();
		}
		
		
		return states;
	}
}
