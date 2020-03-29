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
	Set<NFAState> statesVisited;
	Set<NFAState> startStateSet;
	
	Set<Character> abc;
	
	NFAState startState;
	
	/**
	 * Constructor for an NFA
	 */
	public NFA() {
		
		allStates = new LinkedHashSet<NFAState>();
		finalStates = new LinkedHashSet<NFAState>();
		statesVisited = new LinkedHashSet<NFAState>();
		startStateSet = new LinkedHashSet<NFAState>();
		abc = new LinkedHashSet<Character>();
	}

	@Override
	public void addStartState(String name) {
		
		NFAState nfaStartState = new NFAState(name);
		startStateSet.add(nfaStartState);
		startState = nfaStartState;
		allStates.add(nfaStartState);
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

		return startStateSet.iterator().next();
	}

	@Override
	public Set<Character> getABC() {
		
		return abc;
	}

	@Override
	public DFA getDFA() {
		
		DFA dfa = new DFA();
		Set<NFAState> nfaStartState = eClosure(startState);
		//System.out.println(nfaStartState.toString());
		statesVisited.clear();
		Set<NFAState> nfaFinalState = eClosure(finalStates.iterator().next());
		//System.out.println(nfaFinalState.toString());
		dfa.addStartState(nfaStartState.toString());
		dfa.addFinalState(nfaFinalState.toString());
		
		
		return dfa;
	}

	@Override
	public Set<NFAState> getToState(NFAState from, char onSymb) {
		
		return from.getTo(onSymb);
	}

	@Override
	public Set<NFAState> eClosure(NFAState s) {
		
		Set<NFAState> statesFrom_e = s.getTo('e');
		Set<NFAState> retVal = new LinkedHashSet<NFAState>();
		
		if(!statesFrom_e.isEmpty() && !statesVisited.contains(s)) {
			for(NFAState state : statesFrom_e) {
				if(!retVal.contains(state)) {
					retVal.add(state);
					statesVisited.add(state);
					if(!retVal.contains(s)) {
						retVal.add(s);
						statesVisited.add(s);
					}
					eClosure(state);
				}
			}
		} else {
			retVal.add(s);
		}
	
		return retVal;
	}
	
//    public Set<NFAState> eClosure(NFAState state)
//    {
//        Set<NFAState> temp = new LinkedHashSet<>();
//        Set<NFAState> statesVisited = new LinkedHashSet<>();
////        if(!state.getTo('e').equals(new LinkedHashSet<NFAState>())){
//        if(!state.getTo('e').isEmpty() && !statesVisited.contains(state)){
//            for(NFAState fromNFA : state.getTo('e')){
//                if(!this.eClosureStates.contains(fromNFA)) {
//                    this.eClosureStates.add(fromNFA);
//                    eClosure(fromNFA);
//                }
//            }
//        }
//        else{
//            this.eClosureStates.add(state);
//        }
////        what you can reach with "e"
////        set of states
//        return this.eClosureStates;
//    }
	
}
