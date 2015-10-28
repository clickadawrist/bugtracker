package edu.ncsu.csc216.bug_tracker.command;

import java.util.ArrayList;

/**
 * @author Manaka Green and Paul Hawkins
 *
 */
public class Command {
	
	public static final String R_FIXED = Resolution.FIXED.toString();
	public static final String R_DUPLICATE = Resolution.DUPLICATE.toString();
	public static final String R_WONTFIX = Resolution.WONTFIX.toString();
	public static final String R_WORKSFORME = Resolution.WORKSFORME.toString();
	private String developerId;
	private String note;
	
	private CommandValue c;
	private Resolution resolution;
	
	/**
	 * Throws an Illegal Argument Exception if the command value is null,
	 * if the command value is resolved and the resolution is null,
	 * or if the command value is possession and the developer ID is null or an empty
	 * String.
	 * 
	 * @param c
	 * @param developerId
	 * @param resolution
	 * @param note
	 */
	public Command(CommandValue c, String developerId, Resolution resolution, String note) {
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
	 * Has fixed commands.
	 */
	public static enum CommandValue { 
		VOTE, POSSESSION, RESOLVED, VERIFIED, REOPEN, CONFIRM	
	}
	
	/**
	 * Enumeration.
	 * Has fixed resolution outcomes.
	 *
	 */
	public static enum Resolution { 
		FIXED, DUPLICATE, WONTFIX, WORKSFORME 
	}
		
	/**
	 * 
	 * @return command value
	 */
	public CommandValue getCommand() {
		return this.c; 
	}
	
	/**
	 * 
	 * @return developer's id
	 */
	public String getDeveloperId() {
		return this.developerId;
	}
	
	/**
	 * 
	 * @return resolution
	 */
	public Resolution getResolution() {
		return this.resolution;
	}
	
	/**
	 * 
	 * @return note for the bug
	 */
	public String getNote() {
		return this.note;
	}
}
