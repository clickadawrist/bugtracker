package edu.ncsu.csc216.bug_tracker.bug;

import java.util.ArrayList;

import edu.ncsu.csc216.bug_tracker.command.Command;
import edu.ncsu.csc216.bug_tracker.command.Command.Resolution;
import edu.ncsu.csc216.bug_tracker.tracker.BugList;
import edu.ncsu.csc216.bug_tracker.xml.Bug;

/**
 * @author Manaka Green and Paul Hawkins
 *
 */
public class TrackedBug 
{
	private int bugId;
	private BugState state;
	private String summary;
	private String reporter;
	private int votes;
	private boolean confirmed;
	private ArrayList<String> notes;
	private final BugState unconfirmedState = new UnconfirmedState();
	private final BugState newState = new NewState(); 
	private final BugState assignedState = new AssignedState();
	private final BugState resolvedState = new ResolvedState();
	private final BugState reopenState = new ReopenState();
	private final BugState closedState = new ClosedState();
	public static final String UNCONFIRMED_NAME = "UnconfirmedState";
	public static final String NEW_NAME = "NewState";
	public static final String ASSIGNED_NAME = "AssignedState";
	public static final String RESOLVED_NAME = "ResolvedState";
	public static final String REOPEN_NAME = "ReopenState";
	public static final String CLOSED_NAME = "ClosedState";
	public static final int VOTE_THRESHOLD = 3;
	private static int counter;
	private Resolution resolution;
	private BugList bugs;
	
	/**
	 * 
	 * @param summary
	 * @param reporter
	 */
	public TrackedBug(String summary, String reporter) 
	{
		this.summary = summary;
		this.reporter = reporter;
	}
	
	/**
	 * 
	 * @param z
	 */
	public TrackedBug(Bug z) 
	{
		this.summary = z.getSummary();
		this.reporter = z.getReporter();
	}
	
	/**
	 * 
	 */
	public static void incrementCounter() 
	{
		counter++;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getBugId() 
	{
		return this.bugId;
	}
	
	/**
	 * 
	 * @return
	 */
	public BugState getState() 
	{
		return this.state;
	}
	
	/**
	 * 
	 * @param d
	 */
	private void setSate(String e) 
	{
		if(e == UNCONFIRMED_NAME)
		{
			this.state = unconfirmedState;
		}
		else if(e == NEW_NAME )
		{
			this.state = newState;
		}
		else if(e == ASSIGNED_NAME)
		{
			this.state = assignedState;
		}
		else if(e == RESOLVED_NAME)
		{
			this.state = resolvedState;
		}
		else if(e == REOPEN_NAME)
		{
			this.state = reopenState;
		}
		else if(e == CLOSED_NAME)
		{
			this.state = closedState;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Resolution getResolution() 
	{
		return this.resolution;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getResolutionString() 
	{
		return this.resolution.toString();
		
	}
	
	/**
	 * 
	 * @param e
	 */
	private void setResolution(String e) 
	{	
		if(e == Command.R_DUPLICATE)
		{
			this.resolution = Resolution.DUPLICATE;
		}
		else if(e == Command.R_FIXED)
		{
			this.resolution = Resolution.FIXED;
		}
		else if(e == Command.R_WONTFIX)
		{
			this.resolution = Resolution.WONTFIX;
		}
		else if(e == Command.R_WORKSFORME)
		{
			this.resolution = Resolution.WORKSFORME;
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public String getOwner() 
	{
//////////////?????
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSummary() 
	{
		return this.summary;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getReporter() 
	{
		return this.reporter;
	}
	
	/**
	 * 
	 * @return
	 */
	public ArrayList<String> getNotes() 
	{
		return this.notes;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getNotesString() 
	{
		return this.notes.toString();
	}
	
	/**
	 * 
	 * @return
	 */
	public boolean isConfirmed() 
	{
		return this.confirmed;
	}
	
	/**
	 * 
	 * @param f
	 */
	public void update(Command c) 
	{
		if(this.state == unconfirmedState)
		{
			unconfirmedState.updateState(c);
		}
		if(this.state == newState)
		{
			newState.updateState(c);
		}
		if(this.state == assignedState)
		{
			assignedState.updateState(c);
		}
		if(this.state == reopenState)
		{
			reopenState.updateState(c);
		}
		if(this.state == closedState)
		{
			closedState.updateState(c);
		}
		if(this.state == resolvedState)
		{
			resolvedState.updateState(c);
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public Bug getXMLBug() 
	{
//////////////?????
	}
	
	/**
	 * 
	 * @param g
	 */
	public static void setCounter(int g) 
	{
		counter = g;
	}
	
	/**
	 * 
	 */
	private class UnconfirmedState implements BugState
	{
		/**
		 * 
		 */
		@Override
		public void updateState(Command c)
		{
			switch(c.getCommand())
			{
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
		
		/**
		 * 
		 */
		public String getStateName()
		{
			return UNCONFIRMED_NAME;
		}
	}
	
	/**
	 * 
	 * 
	 *
	 */
	private class NewState implements BugState
	{	
		/**
		 * 
		 */
		@Override
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
		
		/**
		 * 
		 */
		public String getStateName()
		{
			return NEW_NAME;
		}
	}
	
	/**
	 * 
	 * 
	 *
	 */
	private class AssignedState implements BugState
	{	
		/**
		 * 
		 */
		@Override
		public void updateState(Command c)
		{
			switch(c.getCommand()) 
			{
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
		
		/**
		 * 
		 */
		public String getStateName()
		{
			return ASSIGNED_NAME;
		}
	}
	
	/**
	 * 
	 * 
	 *
	 */
	private class ResolvedState implements BugState
	{	
		/**
		 * 
		 */
		@Override
		public void updateState(Command c)
		{
			switch(c.getCommand())
			{
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
		
		/**
		 * 
		 */
		public String getStateName()
		{
			return RESOLVED_NAME;
		}
	}
	
	/**
	 * 
	 *  
	 *
	 */
	private class ClosedState implements BugState
	{
		/**
		 * 
		 */
		@Override
		public void updateState(Command c)
		{
			switch(c.getCommand())
			{
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
		
		/**
		 * 
		 */
		public String getStateName()
		{
			return CLOSED_NAME;
		}
	}
	
	/**
	 * 
	 * 
	 *
	 */
	private class ReopenState implements BugState
	{
		
		/**
		 * 
		 */
		@Override
		public void updateState(Command c)
		{
			switch(c.getCommand())
			{
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
		
		/**
		 * 
		 */
		public String getStateName()
		{
			return REOPEN_NAME;
		}
	}
}
