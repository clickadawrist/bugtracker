package edu.ncsu.csc216.bug_tracker.tracker;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.bug_tracker.bug.TrackedBug;
import edu.ncsu.csc216.bug_tracker.command.Command;
import edu.ncsu.csc216.bug_tracker.xml.Bug;
import edu.ncsu.csc216.bug_tracker.xml.BugIOException;
import edu.ncsu.csc216.bug_tracker.xml.BugReader;

/**
 * @author tmanthawk
 *
 */
public class BugList {

	ArrayList<TrackedBug> list;
	/**
	 * @throws BugIOException 
	 * 
	 */
	public BugList() throws BugIOException 
	{
		BugReader reader = new BugReader("test");
		if(reader.getBugs() == null)
		{
			TrackedBug.setCounter(0);
		}
		else
		{
			for(int i = 0; i < reader.getBugs().size(); i++)
			{
				TrackedBug temp = new TrackedBug(reader.getBugs().get(i));
				list.add(temp);
			}
		}
	}
	
	public int addBug(String summary, String reporter)
	{
		
	}
	
	public void addXMLBug(List<Bug> bugList)
	{
		
	}
	
	public List<TrackedBug> getBugs()
	{
		
	}
	
	public List<TrackedBug> getBugsByOwner(String owner)
	{
		
	}
	
	public TrackedBug getBugByid(int id)
	{
		
	}
	
	public void excecuteCommand(int index, Command c)
	{
		
	}
	
	public void deleteBugByid(int id)
	{
		
	}

}
