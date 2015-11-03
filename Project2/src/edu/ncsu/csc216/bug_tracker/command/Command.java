package edu.ncsu.csc216.bug_tracker.command;

/**
 * For the user this class creates specific commands to use and resolution types to change for the bug.
 * @author Manaka Green and Paul Hawkins
 */
public class Command {
	
	//Translates resolution to it's string constant name equivalent.
	public static final String R_FIXED = Resolution.FIXED.toString();
	public static final String R_DUPLICATE = Resolution.DUPLICATE.toString();
	public static final String R_WONTFIX = Resolution.WONTFIX.toString();
	public static final String R_WORKSFORME = Resolution.WORKSFORME.toString();
	private String developerId;
	private String note;
	
	//Command values used by user to change bug's state,
	//Resolution reflects user's status with the bug.
	private CommandValue c;
	private Resolution resolution;
	
	/**
	 * Encapsulates user actions, user information and bug information into a command object.
	 * @param c Command value for the bug 
	 * @param developer's id Id of the developer
	 * @param resolution Resolution of the bug
	 * @param note Note for the bug
	 * @throws IllegalArgumentException if command value is null, if command
	 * value is resolved and resolution is null, or if command value is possession and: 
	 * developer ID is null or empty String
	 */
	public Command(CommandValue c, String developerId, Resolution resolution, String note) 
	{
		if(c == null)
		{
			throw new IllegalArgumentException();
		}
		
		if(c == CommandValue.RESOLVED && resolution == null)
		{
			throw new IllegalArgumentException();
		}
		
		if(c == CommandValue.POSSESSION && developerId == null || developerId == "")
		{
			throw new IllegalArgumentException();
		}
		
		this.c = c;
		this.developerId = developerId;
		this.note = note;
		this.resolution = resolution;
	}
	
	/**
	 * Enumeration.
	 * Has fixed commands for the bug.
	 */
	public static enum CommandValue 
	{ 
		VOTE, POSSESSION, RESOLVED, VERIFIED, REOPEN, CONFIRM	
	}
	
	/**
	 * Enumeration.
	 * Has fixed resolution outcomes for the bug.
	 */
	public static enum Resolution 
	{ 
		FIXED, DUPLICATE, WONTFIX, WORKSFORME 
	}
		
	/**
	 * Gets command from the bug.
	 * @return c Command value for the bug
	 */
	public CommandValue getCommand() 
	{
		return this.c; 
	}
	
	/**
	 * Gets developer's id from the bug.
	 * @return developer's id Id of the developer
	 */
	public String getDeveloperId() 
	{
		return this.developerId;
	}
	
	/**
	 * Gets resolution from the bug.
	 * @return resolution Resolution of the bug
	 */
	public Resolution getResolution() 
	{
		return this.resolution;
	}
	
	/**
	 * Gets note from the bug.
	 * @return note Note for the bug
	 */
	public String getNote() 
	{
		return this.note;
	}
}