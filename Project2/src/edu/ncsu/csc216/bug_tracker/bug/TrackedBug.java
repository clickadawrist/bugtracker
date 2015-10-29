package edu.ncsu.csc216.bug_tracker.bug;

import java.util.ArrayList;

import edu.ncsu.csc216.bug_tracker.command.Command;
import edu.ncsu.csc216.bug_tracker.command.Command.Resolution;
import edu.ncsu.csc216.bug_tracker.xml.Bug;

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
	
	private class UnconfirmedState implements BugState
	{
		private UnconfirmedState()
		{
			
		}
		
		public void updateState(Command c)
		{
			switch(c.getCommand()){
				case VOTE:
				{
					break;
				}
				case CONFIRM:
				{
					break;
				}
				case POSSESSION:
				{
					throw new UnsupportedOperationException();
				}
				case REOPEN:
				{
					throw new UnsupportedOperationException();
				}
				case RESOLVED:
				{
					throw new UnsupportedOperationException();
				}
				default:
				{
					throw new UnsupportedOperationException();
				}
			}
		}
		
		public String getStateName()
		{
			
		}
	}
	
	private class NewState implements BugState
	{
		private NewState()
		{
			
		}
		
		public void updateState(Command c)
		{
			switch(c.getCommand()){
			case VOTE:
			{
				throw new UnsupportedOperationException();
			}
			case CONFIRM:
			{
				throw new UnsupportedOperationException();
			}
			case POSSESSION:
			{
				break;
			}
			case REOPEN:
			{
				throw new UnsupportedOperationException();
			}
			case RESOLVED:
			{
				throw new UnsupportedOperationException();
			}
			default:
			{
				throw new UnsupportedOperationException();
			}
		}
		}
		
		public String getStateName()
		{
			
		}
	}
	
	private class AssignedState implements BugState
	{
		private AssignedState()
		{
			
		}
		
		public void updateState(Command c)
		{
			switch(c.getCommand()){
			case VOTE:
			{
				throw new UnsupportedOperationException();
			}
			case CONFIRM:
			{
				throw new UnsupportedOperationException();
			}
			case POSSESSION:
			{
				throw new UnsupportedOperationException();
			}
			case REOPEN:
			{
				throw new UnsupportedOperationException();
			}
			case RESOLVED:
			{
				break;
			}
			default:
			{
				break;
			}
		}
		}
		
		public String getStateName()
		{
			
		}
	}
	
	private class ResolvedState implements BugState
	{
		private ResolvedState()
		{
			
		}
		
		public void updateState(Command c)
		{
			switch(c.getCommand()){
			case VOTE:
			{
				throw new UnsupportedOperationException();
			}
			case CONFIRM:
			{
				throw new UnsupportedOperationException();
			}
			case POSSESSION:
			{
				throw new UnsupportedOperationException();
			}
			case REOPEN:
			{
				break;
			}
			case RESOLVED:
			{
				throw new UnsupportedOperationException();
			}
			default:
			{
				break;
			}
		}
		}
		
		public String getStateName()
		{
			
		}
	}
	
	private class ClosedState implements BugState
	{
		private ClosedState()
		{
			
		}
		
		public void updateState(Command c)
		{
			switch(c.getCommand()){
			case VOTE:
			{
				throw new UnsupportedOperationException();
			}
			case CONFIRM:
			{
				throw new UnsupportedOperationException();
			}
			case POSSESSION:
			{
				throw new UnsupportedOperationException();
			}
			case REOPEN:
			{
				break;
			}
			case RESOLVED:
			{
				throw new UnsupportedOperationException();
			}
			default:
			{
				throw new UnsupportedOperationException();
			}
		}
		}
		
		public String getStateName()
		{
			
		}
	}
	
	private class ReopenState implements BugState
	{
		private ReopenState()
		{
			
		}
		
		public void updateState(Command c)
		{
			switch(c.getCommand()){
			case VOTE:
			{
				throw new UnsupportedOperationException();
			}
			case CONFIRM:
			{
				throw new UnsupportedOperationException();
			}
			case POSSESSION:
			{
				break;
			}
			case REOPEN:
			{
				throw new UnsupportedOperationException();
			}
			case RESOLVED:
			{
				break;
			}
			default:
			{
				break;
			}
		}
		}
		
		public String getStateName()
		{
			
		}
	}
	
}
