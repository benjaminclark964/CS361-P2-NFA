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
	Set<NFAState> eClosureStates;
	
	Set<Character> abc;
	
	NFAState startState;
	
	/**
	 * Constructor for an NFA
	 */
	public NFA() {
		
		allStates = new LinkedHashSet<NFAState>();
		finalStates = new LinkedHashSet<NFAState>();
		eClosureStates = new LinkedHashSet<NFAState>();
		abc = new LinkedHashSet<Character>();
	}

	@Override
	public void addStartState(String name) {
		
		NFAState nfaStartState = new NFAState(name);
		startState = nfaStartState;
		
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
	
	/**
	 * Returns the state if it is a valid state
	 * @param name of NFAState
	 * @return NFAState
	 */
	private NFAState getState(String name) {
		
		NFAState retVal= null;
		
		for(NFAState state: allStates) {
			if(state.getName().equals(name)) {
				retVal = state;
				break;
			}
		}
		return retVal;
	}

	@Override
	public void addTransition(String fromState, char onSymb, String toState) {
		
		getState(fromState).addTransition(onSymb, getState(toState));
		
		if(!abc.contains(onSymb) && onSymb != 'e') {
			abc.add(onSymb);
		}
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
		dfa.addStartState(startState.toString());
		for(NFAState state: allStates) {
			eClosure(state);
		}
		System.out.println(eClosureStates.toString());
		
		
		return dfa;
	}

	@Override
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		
		return from.getTo(onSymb);
	}

	@Override
	public Set<NFAState> eClosure(NFAState s) {
		
		Set<NFAState> statesFrom_e = s.getTo('e');
		
		if(!statesFrom_e.isEmpty()) {
			for(NFAState state : statesFrom_e) {
				if(!eClosureStates.contains(state)) {
					eClosureStates.add(state);
					eClosure(state);
				}
			}
		}
		
		return eClosureStates;
	}
}
