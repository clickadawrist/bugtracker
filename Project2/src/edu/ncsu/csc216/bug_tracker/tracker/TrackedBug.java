package edu.ncsu.csc216.bug_tracker.tracker;

import java.util.ArrayList;

import edu.ncsu.csc216.bug_tracker.bug.BugState;

/**
 * @author Manaka Green and Paul Hawkins
 *
 */
public class TrackedBug {
	private int bugId;
	private BugState state;
	private String summary;
	private String reporter;
	private int votes;
	private boolean confirmed;
	private ArrayList<String> notes;
	private final BugState unconfirmedState;
	private final BugState newState; //did they mean to have an underscore or space between new and State?
	private final BugState assignedState;
	private final BugState resolvedState;
	private final BugState reopenState;
	private final BugState closedState;
	public static final String UNCONFIRMED_NAME;
	public static final String NEW_NAME;
	public static final String ASSIGNED_NAME;
	public static final String RESOLVED_NAME;
	public static final String REOPEN_NAME;
	public static final String CLOSED_NAME;
	public static final int VOTE_THRESHOLD;
	private static int counter;
	
	public TrackedBug(String a, String b) {
		
	}
	
	public TrackedBug(Bug c) {
		
	}
	
	//have to make nested classes one day...
	
	public static void incrementCounter() {
		
	}
	
	public int getBugId() {
		
	}
	
	public BugState getState() {
		
	}
	
	private void setSate(String d) {
		
	}
	
	public Resolution getResolution() {
		
	}
	
	public String getResolutionString() {
		
	}
	
	private void setResolution(String e) {
		
	}
	
	public String getOwner() {
		
	}
	
	public String getSummary() {
		
	}
	
	public String getReporter() {
		
	}
	
	public ArrayList<String> getNotes() {
		
	}
	
	public String getNotesString() {
		
	}
	
	public boolean isConfirmed() {
		
	}
	
	public void update(Command f) {
		
	}
	
	public Bug getXMLBug() {
		
	}
	
	public static void setCounter(int g) {
		
	}
	
}
