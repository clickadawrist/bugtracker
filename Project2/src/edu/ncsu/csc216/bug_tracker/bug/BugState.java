package edu.ncsu.csc216.bug_tracker.bug;

import edu.ncsu.csc216.bug_tracker.command.Command;

/**
 * Interface for states in the Bug State Pattern.  All 
 * concrete bug states must implement the BugState interface.
 * 
 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu) 
 */
public interface BugState {
	
		
		/**
		 * Update the {@link TrackedBug} based on the given {@link Command}.
		 * An {@link UnsupportedOperationException} is throw if the {@link CommandValue}
		 * is not a valid action for the given state.  
		 * @param c {@link Command} describing the action that will update the {@link TrackedBug}'s
		 * state.
		 * @throws UnsupportedOperationException if the {@link CommandValue} is not a valid action
		 * for the given state.
		 */
		void updateState(Command c);
		
		/**
		 * Returns the name of the current state as a String.
		 * @return the name of the current state as a String.
		 */
		String getStateName();

	}

