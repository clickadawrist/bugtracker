package edu.ncsu.csc216.bug_tracker.bug;

import java.util.ArrayList;

import edu.ncsu.csc216.bug_tracker.command.Command;
import edu.ncsu.csc216.bug_tracker.command.Command.Resolution;
import edu.ncsu.csc216.bug_tracker.xml.Bug;

/**
 * The TrackedBug class creates a new TrackedBug from a Bug object.
 * When saving, the TrackedBug will generate a Bug object for writing to a file.
 * @author Manaka Green and Paul Hawkins
 */
public class TrackedBug 
{
	private int bugId;
	private BugState state;
	private String summary;
	private String reporter;
	private String owner;
	private int votes;
	private boolean confirmed;
	private ArrayList<String> notes;
	private final BugState unconfirmedState = new UnconfirmedState();
	private final BugState newState = new NewState(); 
	private final BugState assignedState = new AssignedState();
	private final BugState resolvedState = new ResolvedState();
	private final BugState reopenState = new ReopenState();
	private final BugState closedState = new ClosedState();
	public static final String UNCONFIRMED_NAME = "Unconfirmed";
	public static final String NEW_NAME = "New";
	public static final String ASSIGNED_NAME = "Assigned";
	public static final String RESOLVED_NAME = "Resolved";
	public static final String REOPEN_NAME = "Reopen";
	public static final String CLOSED_NAME = "Closed";
	public static final int VOTE_THRESHOLD = 3;
	private static int counter;
	private Resolution resolution;
	
	/**
	 * 
	 * @param summary
	 * @param reporter
	 */
	public TrackedBug(String summary, String reporter) 
	{
		this.summary = summary;
		this.reporter = reporter;
		this.bugId = counter;
		setState(UNCONFIRMED_NAME);
		this.owner = null;
		this.votes = 0;
		this.confirmed = false;
		this.notes = this.getNotes();
		setResolution(null);
	}
	
	/**
	 * 
	 * @param z
	 */
	public TrackedBug(Bug z) 
	{
		this.summary = z.getSummary();
		this.reporter = z.getReporter();
		this.bugId = z.getId();
		setState(z.getState());
		this.owner = z.getOwner();
		this.votes = z.getVotes();
		this.confirmed = z.confirmed;
		this.notes = (ArrayList<String>) z.getNoteList().note;
		setResolution(z.getResolution());
	}
	
	/**
	 * 
	 */
	public static void incrementCounter() 
	{
		TrackedBug.counter++;
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
	 * Gets the bug's state.
	 * @return state Current state of the bug
	 */
	public BugState getState() 
	{
		return this.state;
	}
	
	/**
	 * Helps with translating between string 
	 * @param e
	 */
	private void setState(String e) 
	{
	//Help with translating between the BugState and Resolution objects and their string equivalents
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
		return this.owner;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getSummary() 
	{
		return this.summary;
	}
	
	public int getVotes()
	{
		return votes;
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
		else if(this.state == newState)
		{
			newState.updateState(c);
		}
		else if(this.state == assignedState)
		{
			assignedState.updateState(c);
		}
		else if(this.state == reopenState)
		{
			reopenState.updateState(c);
		}
		else if(this.state == closedState)
		{
			closedState.updateState(c);
		}
		else if(this.state == resolvedState)
		{
			resolvedState.updateState(c);
		}
	}
	
	/**
	 * 
	 * @return bug
	 */
	public Bug getXMLBug() 
	{
		Bug bug = new Bug();
		bug.setConfirmed(this.confirmed);
		bug.setId(this.bugId);
		bug.setOwner( this.getOwner());
		bug.setReporter(this.getReporter());
		bug.setResolution(this.getResolutionString());
		bug.setState(this.getState().getStateName());
		bug.setSummary(this.getSummary());
		bug.setVotes(this.votes);
		bug.noteList.note = this.getNotes();
		
		return bug;
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
					votes ++;
					if(votes >= VOTE_THRESHOLD)
					{
						if(getOwner() == null)
						{
							setState(NEW_NAME);
						}
						else
						{
							setState(ASSIGNED_NAME);
						}
					}
					break;
				}
				case CONFIRM:
				{
					if(getOwner() != null)
					{
						setState(ASSIGNED_NAME);
					}
					else
					{
						setState(NEW_NAME);
					}
					confirmed = true;
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
		@Override
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
					owner = c.getDeveloperId();
					setState(ASSIGNED_NAME);
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
		@Override
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
					if(getResolution() != Resolution.FIXED)
					{
						setState(RESOLVED_NAME);
					}
					else
					{
						setState(CLOSED_NAME);
					}
					break;
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
		@Override
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
					if(isConfirmed())
					{
						setState(REOPEN_NAME);
					}
					else
					{
						setState(UNCONFIRMED_NAME);
					}
					
					setResolution(null);
					
					break;
				}
				case RESOLVED:
				{
					throw new UnsupportedOperationException();
				}
				default:
				{
					setState(CLOSED_NAME);
					break;
				}
			}
		}
		
		/**
		 * 
		 */
		@Override
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
					if(isConfirmed())
					{
						setState(REOPEN_NAME);
					}
					else
					{
						setState(UNCONFIRMED_NAME);
					}
					resolution = null;
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
		@Override
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
					owner = c.getDeveloperId();
					setState(ASSIGNED_NAME);
					break;
				}
				case REOPEN:
				{
					throw new UnsupportedOperationException();
				}
				case RESOLVED:
				{
					if(getResolution() != Resolution.FIXED)
					{
						setState(RESOLVED_NAME);
					}
					else
					{
						setState(CLOSED_NAME);
					}
					break;
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
		@Override
		public String getStateName()
		{
			return REOPEN_NAME;
		}
	}
}
