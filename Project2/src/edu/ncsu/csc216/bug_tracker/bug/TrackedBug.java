package edu.ncsu.csc216.bug_tracker.bug;

import java.util.ArrayList;

import edu.ncsu.csc216.bug_tracker.command.Command;
import edu.ncsu.csc216.bug_tracker.command.Command.Resolution;
import edu.ncsu.csc216.bug_tracker.xml.Bug;
import edu.ncsu.csc216.bug_tracker.xml.NoteList;

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
	
	/**Name of unconfirmed bug state- "unconfirmed". */
	public static final String UNCONFIRMED_NAME = "Unconfirmed";
	/**Name of new bug state - "new". */
	public static final String NEW_NAME = "New";
	/**Name of assigned bug state - "assigned". */
	public static final String ASSIGNED_NAME = "Assigned";
	/**Name of resolved bug state - "resolved". */
	public static final String RESOLVED_NAME = "Resolved";
	/**Name of reopened bug state - "reopen". */
	public static final String REOPEN_NAME = "Reopen";
	/**Name of closed bug state - "closed". */
	public static final String CLOSED_NAME = "Closed";
	/**Voting threshold constant. */
	public static final int VOTE_THRESHOLD = 3;
	private static int counter;
	private Resolution resolution;
	
	/**
	 * Identifies a problem in the software by creating a bug that
	 * is added into the system and summarized by reporter. 
	 * @param summary Brief description of bug
	 * @param reporter Name of person reporting the bug
	 */
	public TrackedBug(String summary, String reporter) 
	{
		if(summary == null || summary.equals("") || reporter.equals("") || reporter == null)
		{
			throw new IllegalArgumentException();
		}
		
		this.summary = summary;
		this.reporter = reporter;
		this.bugId = counter;
		setState(UNCONFIRMED_NAME);
		this.owner = null;
		this.votes = 1;
		this.confirmed = false;
		this.notes = new ArrayList<String>();
		resolution = null;
		TrackedBug.incrementCounter();
	}
	
	/**
	 * Bug that is being tracked within the system that has 
	 * more detailed descriptive note.
	 * @param z Bug object 
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
		setResolution(z.getResolution());
		this.notes = new ArrayList<String>();
		
		for(int i = 0; i < z.getNoteList().getNote().size(); i++)
		{
			this.notes.add(z.getNoteList().getNote().get(i));
		}
		TrackedBug.incrementCounter();
	}
	
	/**
	 * Sums up the amount of tracked bugs.
	 */
	public static void incrementCounter() 
	{
		TrackedBug.counter++;
	}
	
	/**
	 * Gets bug's id.
	 * @return bugId Current bug's id
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
	 * Helps with translating between name of bug state and state of bug.
	 * @param e Name of bug's states
	 */
	private void setState(String e) 
	{
		//Help with translating between the BugState and Resolution objects and their string equivalents
		if(e != null)
		{
			if(e.equals(UNCONFIRMED_NAME))
			{
				this.state = unconfirmedState;
			}
			else if(e.equals(NEW_NAME) )
			{
				this.state = newState;
			}
			else if(e.equals(ASSIGNED_NAME))
			{
				this.state = assignedState;
			}
			else if(e.equals(RESOLVED_NAME))
			{
				this.state = resolvedState;
			}
			else if(e.equals(REOPEN_NAME))
			{
				this.state = reopenState;
			}
			else if(e.equals(CLOSED_NAME))
			{
				this.state = closedState;
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}
	}
	
	/**
	 * Gets bug's resolution.
	 * @return resolution Current bug's resolution
	 */
	public Resolution getResolution() 
	{
		return this.resolution;
	}
	
	/**
	 * Gets bug's resolution in a string.
	 * @return res Name of current bug's resolution
	 */
	public String getResolutionString() 
	{
		String res = null;
		
		if(resolution == Resolution.DUPLICATE)
		{
			res = Command.R_DUPLICATE;
		}
		else if(resolution == Resolution.FIXED)
		{
			res = Command.R_FIXED;
		}
		else if(resolution == Resolution.WONTFIX)
		{
			res = Command.R_WONTFIX;
		}
		else if(resolution == Resolution.WORKSFORME)
		{
			res = Command.R_WORKSFORME;
		}
		else
		{
			throw new IllegalArgumentException();
		}
		return res;
	}
	
	/**
	 * Helps with translating between name of bug's resolution and resolution of bug.
	 * @param e Name of bug's resolution
	 */
	private void setResolution(String e) 
	{	
		if(e != null)
		{
			if(e.equals(Command.R_DUPLICATE))
			{
				this.resolution = Resolution.DUPLICATE;
			}
			else if(e.equals(Command.R_FIXED))
			{
				this.resolution = Resolution.FIXED;
			}
			else if(e.equals(Command.R_WONTFIX))
			{
				this.resolution = Resolution.WONTFIX;
			}
			else if(e.equals(Command.R_WORKSFORME))
			{
				this.resolution = Resolution.WORKSFORME;
			}
		}
	}
	
	/**
	 * Gets bug's assigned owner.
	 * @return owner Owner assigned to fix bug
	 */
	public String getOwner() 
	{
		return this.owner;
	}
	
	/**
	 * Gets bug's summary.
	 * @return summary Details of the bug
	 */
	public String getSummary() 
	{
		return this.summary;
	}
	
	/**
	 * Gets bug's number of votes.
	 * @return votes Number of people interested in fixing bug
	 */
	public int getVotes()
	{
		return votes;
	}
	
	/**
	 * Gets bug's reporter.
	 * @return reporter Person who reported current bug
	 */
	public String getReporter() 
	{
		return this.reporter;
	}
	
	/**
	 * Gets bug's notes as a list.
	 * @return notes Additional detail list of current bug
	 */
	public ArrayList<String> getNotes() 
	{
		return this.notes;
	}
	
	/**
	 * Gets bug's notes as a string. 
	 * @return note Additional detail of current bug
	 */
	public String getNotesString() 
	{
		String note = "";
		for(int i = 0; i < this.notes.size(); i++)
		{
			note += this.notes.get(i);
			note += "\n------\n";
		}
		return note;
	}
	
	/**
	 * Confirms bug into the tracking system.
	 * @return confirmed Boolean true if bug is confirmed
	 */
	public boolean isConfirmed() 
	{
		return this.confirmed;
	}
	
	/**
	 * Updates state of bug.
	 * @param c Command used to change state of bug
	 */
	public void update(Command c) 
	{
		this.getState().updateState(c);
		if(c.getNote() != null && c.getNote().equals(""))
		{
			notes.add(c.getNote());
		}
	}
	
	/**
	 * Gets bug and its information from XML file.
	 * @return bug Bug from XML file
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
		bug.setNoteList(new NoteList());
		bug.noteList.note = this.getNotes();
		
		return bug;
	}
	
	/**
	 * Sets counter for bug id number.
	 * @param g Number of bug's id
	 */
	public static void setCounter(int g) 
	{
		TrackedBug.counter = g;
	}
	
	/**
	 * Unconfirmed State that a bug can transition into.
	 */
	private class UnconfirmedState implements BugState
	{
		/**
		 * Updates the state of the bug with a command.
		 */
		@Override
		public void updateState(Command c)
		{
			//FSM State Pattern
			switch(c.getCommand())
			{
				case VOTE:
				{
					votes++;
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
		 * Returns Unconfirmed State's name as string constant.
		 */
		@Override
		public String getStateName()
		{
			return UNCONFIRMED_NAME;
		}
	}
	
	/**
	 * New State that a bug can transition into.
	 */
	private class NewState implements BugState
	{	
		/**
		 * Updates the state of the bug with a command.
		 */
		@Override
		public void updateState(Command c)
		{
			//FSM State Pattern
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
					throw new UnsupportedOperationException();
				}
				default:
				{
					throw new UnsupportedOperationException();
				}
			}
		}
		
		/**
		 * Returns New State's name as string constant.
		 */
		@Override
		public String getStateName()
		{
			return NEW_NAME;
		}
	}
	
	/**
	 * Assigned State that a bug can transition into.
	 */
	private class AssignedState implements BugState
	{	
		/**
		 * Updates the state of the bug with a command.
		 */
		@Override
		public void updateState(Command c)
		{
			//FSM State Pattern
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
					resolution = c.getResolution();
					if(getResolution() == Resolution.FIXED)
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
		 * Returns Assigned State's name as string constant.
		 */
		@Override
		public String getStateName()
		{
			return ASSIGNED_NAME;
		}
	}
	
	/**
	 * Resolved State that a bug can transition into.
	 */
	private class ResolvedState implements BugState
	{	
		/**
		 * Updates the state of the bug with a command.
		 */
		@Override
		public void updateState(Command c)
		{
			//FSM State Pattern
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
					setState(CLOSED_NAME);
					break;
				}
			}
		}
		
		/**
		 * Returns Resolved State's name as string constant.
		 */
		@Override
		public String getStateName()
		{
			return RESOLVED_NAME;
		}
	}
	
	/**
	 * Closed State that a bug can transition into.
	 */
	private class ClosedState implements BugState
	{
		/**
		 * Updates the state of the bug with a command.
		 */
		@Override
		public void updateState(Command c)
		{
			//FSM State Pattern
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
		 * Returns Closed State's name as string constant.
		 */
		@Override
		public String getStateName()
		{
			return CLOSED_NAME;
		}
	}
	
	/**
	 * Reopen State that a bug can transition into.
	 */
	private class ReopenState implements BugState
	{
		
		/**
		 * Updates the state of the bug with a command.
		 */
		@Override
		public void updateState(Command c)
		{
			//FSM State Pattern 
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
					resolution = null;
					break;
				}
				case REOPEN:
				{
					throw new UnsupportedOperationException();
				}
				case RESOLVED:
				{
					resolution = c.getResolution();
					if(getResolution() == Resolution.FIXED)
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
		 * Returns Reopen State's name as string constant.
		 */
		@Override
		public String getStateName()
		{
			return REOPEN_NAME;
		}
	}
}