package edu.ncsu.csc216.bug_tracker.tracker;

import edu.ncsu.csc216.bug_tracker.bug.TrackedBug;
import edu.ncsu.csc216.bug_tracker.command.Command;
import edu.ncsu.csc216.bug_tracker.xml.BugIOException;
import edu.ncsu.csc216.bug_tracker.xml.BugReader;
import edu.ncsu.csc216.bug_tracker.xml.BugWriter;

/**
 * 
 * @author Paul Hawkins and Manaka Green
 */
public class BugTrackerModel {

	/** */
	private static BugTrackerModel model = null;
	/** */
	
	private BugList bugs;
	
	/**
	 * 
	 */
	private BugTrackerModel() {
		
	}
	
	public static BugTrackerModel getInstance()
	{
		if(model == null)
		{
			model = new BugTrackerModel();
		}
		return model;
	}
	
	public void saveBugsToFile(String file)
	{
		BugWriter writer = new BugWriter(file);
		for(int i = 0; i < bugs.getBugs().size(); i++)
		{
			writer.addItem(bugs.getBugs().get(i).getXMLBug());
		}
	}
	
	public void loadBugsFromFile(String file)
	{
		try 
		{
			BugReader reader = new BugReader(file);
			bugs.addXMLBug(reader.getBugs());
		} 
		catch (BugIOException e) 
		{
			throw new IllegalArgumentException();
		}
	}
	
	public void createNewBugList()
	{
		bugs = new BugList();
	}
	
	public Object[][] getBugListAsArray()
	{
		Object [][] listAsArray =  new Object[bugs.getBugs().size()+1][3];
		for(int i = 0; i < bugs.getBugs().size(); i++)
		{
			listAsArray[i][0] = bugs.getBugs().get(i).getBugId();
			listAsArray[i][1] = bugs.getBugs().get(i).getState().getStateName();
			listAsArray[i][2] = bugs.getBugs().get(i).getSummary();
		}
		
		return listAsArray;
	}
	
	public Object[][] getBugListByOwnerAsArray(String owner)
	{
		if(owner == null)
		{
			throw new IllegalArgumentException();
		}
		
		Object [][] listAsArray =  new Object[bugs.getBugs().size()][3];
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
	
	public TrackedBug getBugById(int id)
	{
		return bugs.getBugById(id);
	}
	
	public void executeCommand(int id,Command c)
	{
		bugs.executeCommand(id, c);
	}
	
	public void deleteBugById(int id)
	{
		bugs.deleteBugById(id);
	}
	
	public void addBugToList(String summary, String reporter)
	{
		bugs.addBug(summary, reporter);
	}
}
