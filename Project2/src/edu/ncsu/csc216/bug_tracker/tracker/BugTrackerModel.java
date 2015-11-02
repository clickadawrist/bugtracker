/**
 * 
 */
package edu.ncsu.csc216.bug_tracker.tracker;

import edu.ncsu.csc216.bug_tracker.bug.TrackedBug;
import edu.ncsu.csc216.bug_tracker.command.Command;
import edu.ncsu.csc216.bug_tracker.xml.BugIOException;
import edu.ncsu.csc216.bug_tracker.xml.BugReader;
import edu.ncsu.csc216.bug_tracker.xml.BugWriter;

/**
 * @author tmanthawk
 *
 */
public class BugTrackerModel {

	private static BugTrackerModel model = null;
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
		Object [][] listAsArray =  new Object[3][3];
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
		
		Object [][] listAsArray =  new Object[3][3];
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
	
	public TrackedBug getBugByid(int id)
	{
		return bugs.getBugByid(id);
	}
	
	public void excecuteCommand(int id,Command c)
	{
		bugs.getBugByid(id).update(c);
	}
	
	public void deleteBugByid(int id)
	{
		bugs.deleteBugByid(id);
	}
	
	public void addBugToList(String summary, String reporter)
	{
		bugs.addBug(summary, reporter);
	}
}
