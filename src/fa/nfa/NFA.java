package fa.nfa;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Queue;
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
	Set<NFAState> nonFinalNonStartStates;
	Set<NFAState> finalStates;
	Set<NFAState> statesVisited;
	Set<NFAState> startStateSet;
	Set<NFAState> eClosureStates;
	
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
		nonFinalNonStartStates = new LinkedHashSet<NFAState>();
		eClosureStates = new LinkedHashSet<NFAState>();
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
		nonFinalNonStartStates.add(state);
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
		
		boolean isFinished = false;
		boolean trapState = false;
		boolean addedTransitions = false;
		
		Queue<Set<NFAState>> q = new LinkedList<Set<NFAState>>();
		LinkedList<Set<NFAState>> qContained = new LinkedList<Set<NFAState>>();
		
		Set<NFAState> dfaStartState = new HashSet<NFAState>();
		Set<NFAState> closureState = eClosure(startState);
		dfaStartState.addAll(closureState);
		
		for(NFAState state: dfaStartState) {
			if(state.isFinal()) {
				isFinished = true;
			}
		}
		
		if(isFinished == true) {
			dfa.addFinalState(dfaStartState.toString());
			isFinished = false;
		}
		
		q.add(dfaStartState);
		qContained.add(dfaStartState);
		dfa.addStartState(dfaStartState.toString());
		
		while(!q.isEmpty()) {
			
			Set<NFAState> queueState = q.remove();
			Set<NFAState> newStateSet = new LinkedHashSet<NFAState>(); 
			Set<NFAState> currentState = queueState;

			if(queueState.isEmpty() && trapState == true) {
				for(char symb: abc) {
					dfa.addTransition(queueState.toString(), symb, queueState.toString());
				}
			}
			
			boolean bigSetFlag = false;
			
			for(char symb: abc) {
			int sizeOfQueueState = queueState.size();
			for(NFAState states: currentState) {
				
				Set<NFAState> individualState = new LinkedHashSet<NFAState>();
				individualState.add(states);
				
					if(!states.getTo(symb).isEmpty()) {
						Set<NFAState> transState = states.getTo(symb);
						closureState = transState;
						
						//gets closure states from each state
						for(NFAState s: transState) {
							if(s.isFinal()) {
								isFinished = true;
							}
							closureState.addAll(eClosure(s));
						}
						
						//checks size of state and adds accordingly
						if(sizeOfQueueState > 1) {
							newStateSet.addAll(closureState);
							bigSetFlag = true;
							addedTransitions = true;
							sizeOfQueueState--;
							continue;
						} else if(sizeOfQueueState == 1 && bigSetFlag == false){
							newStateSet = closureState;
						} else if(sizeOfQueueState == 1 && bigSetFlag == true) {
							newStateSet.addAll(closureState);
						}
						
						addedTransitions = true;
						
						//adds trap state
					} else if(states.getTo(symb).isEmpty() && qContained.contains(individualState)
							&& addedTransitions == false) {
						
						newStateSet = new LinkedHashSet<NFAState>();
						trapState = true;
						
						//if a certain state has no transition, but does not need a trap state
					} else {
						if(sizeOfQueueState > 1) {
							sizeOfQueueState--;
						}
						if(addedTransitions == false) {
							continue;
						}
						
					}
					
					//adds new element to queue
					if(!qContained.contains(newStateSet)) {
						q.add(newStateSet);
						qContained.add(newStateSet);
						
						//checks if newStateSet is a final state
						if(isFinished == true) {
							dfa.addFinalState(newStateSet.toString());
						} else {
							dfa.addState(newStateSet.toString());
						}
					}
						
						dfa.addTransition(queueState.toString(), symb, newStateSet.toString());
						isFinished = false;
						newStateSet = new LinkedHashSet<NFAState>();
				}
				addedTransitions = false;
			}
			
		}
		
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
		
		statesVisited.clear();
		return retVal;
	}
}
