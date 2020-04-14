Project 2: NFA
Authors: Kyle Tupper & Benjamin Clark
Class: CS361 Section 2
Semester: Spring 2020
Overview:
This project is an implementation of a non-deterministic finite automaton.

Compiling and Using:
As stated in the assignment: To compile fa.dfa.NFADriver from the top directory of the files: $ javac fa/dfa/NFADriver.java To run fa.dfa.NFADriver: $ java fa.nfa.NFADriver ./tests/p2tc0.txt There is also multiple testing documents available to run with NFADriver. These tests follow this format. ./tests/p2tc0.txt, tc0-tc3

Discussion:
We were given the general framework for this project through the .zip file. We needed to implement the NFA class and NFAState class. One of the main things needed to be implemented was teh depth-first search (DFS) recursive algorithm. The e-closure methods Set<NFAState> eClosure(NFAState s), did provide some challenges but we were able to get through them.

Testing:
There was testing files given in order to test the project, we used the provided testing documents given in order to test the class. We then did some other testing on our own to see if there were test cases outside of the ones provided that needed to be checked.

Extra Credit
N/A

Sources used
N/A
