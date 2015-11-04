package edu.ncsu.csc216.bug_tracker.tracker;

import edu.ncsu.csc216.bug_tracker.bug.TrackedBug;
import edu.ncsu.csc216.bug_tracker.command.Command;
import edu.ncsu.csc216.bug_tracker.xml.BugIOException;
import edu.ncsu.csc216.bug_tracker.xml.BugReader;
import edu.ncsu.csc216.bug_tracker.xml.BugWriter;

/**
 * Concrete class that maintains the BugList and handles Commands from the GUI.
 * @author Paul Hawkins and Manaka Green
 */
public class BugTrackerModel {

	/** . */
	private static BugTrackerModel model = null;
	
	/** . */
	private BugList bugs;
	
	/**
	 * 
	 */
	private BugTrackerModel() {
		bugs = new BugList();
	}
	
	/**
	 * returns the singleton
	 * @return model the singleton
	 */
	public static BugTrackerModel getInstance()
	{
		if(model == null)
		{
			model = new BugTrackerModel();
		}
		return model;
	}
	
	/**
	 * Saves the bugs to an XML file
	 * @param file The string for the file to be saved to
	 */
	public void saveBugsToFile(String file)
	{
		BugWriter writer = new BugWriter(file);
		for(int i = 0; i < bugs.getBugs().size(); i++)
		{
			writer.addItem(bugs.getBugs().get(i).getXMLBug());
		}
	}
	
	/**
	 * Loads the bugs from an XML file
	 * @param file The string for the file to load from
	 */
	public void loadBugsFromFile(String file)
	{
		try 
		{
			BugReader reader = new BugReader(file);
			bugs.addXMLBugs(reader.getBugs());
		} 
		catch (BugIOException e) 
		{
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Creates a new bug list
	 */
	public void createNewBugList()
	{
		bugs = new BugList();
	}
	
	/**
	 * Converts the bug list into a 2D array for the gui to read
	 * @return listAsArray The converted bug list in 2D array form
	 */
	public Object[][] getBugListAsArray()
	{
		Object [][] listAsArray =  new Object[bugs.getBugs().size()][3];
		for(int i = 0; i < bugs.getBugs().size(); i++)
		{
			listAsArray[i][0] = bugs.getBugs().get(i).getBugId();
			listAsArray[i][1] = bugs.getBugs().get(i).getState().getStateName();
			listAsArray[i][2] = bugs.getBugs().get(i).getSummary();
		}
		
		return listAsArray;
	}
	
	/**
	 * Converts the bug list into a 2D array by owner for the gui to read
	 * @param owner the owner to filter the list by
	 * @return listAsArray the converted bug list
	 */
	public Object[][] getBugListByOwnerAsArray(String owner)
	{
		if(owner == null)
		{
			throw new IllegalArgumentException();
		}
		
		int rows = 0;
		
		for(int i = 0; i < bugs.getBugs().size(); i++)
		{
			if(bugs.getBugs().get(i).getOwner() == owner)
			{
				rows++;
			}
		}
		
		Object [][] listAsArray =  new Object[rows][3];
		for(int i = 0; i < bugs.getBugs().size(); i++)
		{
			if(bugs.getBugs().get(i).getOwner() == owner)
			{
				listAsArray[i][0] = bugs.getBugs().get(i).getBugId();
				listAsArray[i][1] = bugs.getBugs().get(i).getState().getStateName();
				listAsArray[i][2] = bugs.getBugs().get(i).getSummary();
			}
		}
		
		return listAsArray;
	}
	
	/**
	 * Returns the bug that has the same ID as the parameter passed in
	 * @param id the id of the bug the user wants to get
	 * @return bug.getBugById(id) the bug with the parameter as its id
	 */
	public TrackedBug getBugById(int id)
	{
		return bugs.getBugById(id);
	}
	
	/**
	 * Runs the passed in command to the bug of the passed in id
	 * @param id the bug the user wants to run the command on
	 * @param c the command that the user wants to run on the bug
	 */
	public void executeCommand(int id, Command c)
	{
		bugs.executeCommand(id, c);
	}
	
	/**
	 * Deletes the bug of the passed in id
	 * @param id the bug the user wants to delete
	 */
	public void deleteBugById(int id)
	{
		bugs.deleteBugById(id);
	}
	
	/**
	 * adds a new bug to the bug list
	 * @param summary Brief description of bug
	 * @param reporter Name of person reporting the bug
	 */
	public void addBugToList(String summary, String reporter)
	{
		bugs.addBug(summary, reporter);
	}
}
