package fa.nfa;

/**
 * 
 * @author Kyle Tupper and Benjamin Clark
 * 
 * A class to represent a single state in the Non-deterministic Finite Automata
 *
 */
public class NFAState extends fa.State {
	
	//String name;
	boolean isFinal;
	
	/**
	 * Constructor for a non-final state
	 * @param name of NFAState
	 */
	public NFAState(String name) {
		this.name = name;
	}
	
	/**
	 * Constructor for a final-state
	 * @param name of final state
	 * @param isFinal is true if the state is a final state
	 */
	public NFAState(String name, boolean isFinal) {
		this.name = name;
		this.isFinal = isFinal;
	}
}
