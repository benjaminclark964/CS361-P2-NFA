package fa.nfa;

import java.util.LinkedHashSet;
import java.util.Set;

import fa.State;
import fa.dfa.DFA;

/**
 * 
 * @author Benjamin Clark and Kyle Tupper 
 * 
 * This class creates a NFA and makes a corresponding DFA
 *
 */
public class NFA implements NFAInterface {
	
	Set<NFAState> allStates;
	Set<Character> abc;
	
	/**
	 * Constructor for an NFA
	 */
	public NFA() {
		allStates = new LinkedHashSet<NFAState>();
		abc = new LinkedHashSet<Character>();
	}

	@Override
	public void addStartState(String name) {
		NFAState startState = new NFAState(name);
		allStates.add(startState);
		
	}

	@Override
	public void addState(String name) {
		NFAState state = new NFAState(name);
		allStates.add(state);
		
	}

	@Override
	public void addFinalState(String name) {
		NFAState nfaFinalState = new NFAState(name, true);
		allStates.add(nfaFinalState);
		//System.out.println(allStates.toString());
	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<? extends State> getStates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<? extends State> getFinalStates() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State getStartState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Character> getABC() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DFA getDFA() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<NFAState> eClosure(NFAState s) {
		// TODO Auto-generated method stub
		return null;
	}

}
