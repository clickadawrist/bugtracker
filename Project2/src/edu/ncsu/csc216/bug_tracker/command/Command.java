package edu.ncsu.csc216.bug_tracker.command;

/**
 * @author Manaka Green and Paul Hawkins
 *
 */
public class Command {
	
	public static final String R_FIXED;
	public static final String R_DUPLICATE;
	public static final String R_WONTFIX;
	public static final String R_WORKSFORME;
	private String developerId;
	private String note;
	
	public Command(CommandValue a, String b, Resolution c, String d) {
		
	}
	
	/**
	 * Nested CommandValue Enumeration
	 */
	public static enum CommandValue { 
		VOTE, POSSESSION, RESOLVED, VERIFIED, REOPEN, CONFIRM	
	}
	
	public static enum Resolution { 
		FIXED, DUPLICATE, WONTFIX, WORKSFORME 
	}
		
	
	public CommandValue getCommand() {
		
	}
	
	public String getDeveloperId() {
		
	}
	
	public Resolution getResolution() {
		
	}
	
	public String getNote() {
		
	}
}
