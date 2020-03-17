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
	Set<NFAState> finalStates;
	Set<NFAState> startStates;
	Set<NFAState> eClosureStates;
	
	Set<Character> abc;
	
	NFAState startState;
	
	/**
	 * Constructor for an NFA
	 */
	public NFA() {
		
		allStates = new LinkedHashSet<NFAState>();
		finalStates = new LinkedHashSet<NFAState>();
		startStates = new LinkedHashSet<NFAState>();
		eClosureStates = new LinkedHashSet<NFAState>();
		abc = new LinkedHashSet<Character>();
	}

	@Override
	public void addStartState(String name) {
		
		NFAState nfaStartState = new NFAState(name);
		startState = nfaStartState;
		startStates.add(startState);
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
		finalStates.add(nfaFinalState);
		allStates.add(nfaFinalState);
	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		
		abc.add(onSymb);
	}

	@Override
	public Set<? extends State> getStates() {
		
		return allStates;
	}

	@Override
	public Set<? extends State> getFinalStates() {
	
		return finalStates;
	}

	@Override
	public State getStartState() {

		return startState;
	}

	@Override
	public Set<Character> getABC() {
		
		return abc;
	}

	@Override
	public DFA getDFA() {
		
		DFA dfa = new DFA();
		dfa.addFinalState(finalStates.toString());
		dfa.addStartState(startStates.toString());
		return dfa;
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
