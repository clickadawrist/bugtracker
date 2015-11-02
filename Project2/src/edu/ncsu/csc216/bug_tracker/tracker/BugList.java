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

	private ArrayList<TrackedBug> list;
	private BugReader reader = new BugReader("test");
	/**
	 * @throws BugIOException 
	 * 
	 */
	public BugList() throws BugIOException 
	{
		if(reader.getBugs() == null)
		{
			TrackedBug.setCounter(0);
		}
		else
		{
			addXMLBug(reader.getBugs());
		}
	}
	
	public int addBug(String summary, String reporter)
	{
		TrackedBug temp = new TrackedBug(summary, reporter);
		list.add(temp);
		return temp.getBugId();
	}
	
	public void addXMLBug(List<Bug> bugList)
	{
		int maxId = 0;
		for(int i = 0; i < reader.getBugs().size(); i++)
		{
			TrackedBug temp = new TrackedBug(reader.getBugs().get(i));
			maxId = Math.max(maxId, temp.getBugId());
			list.add(temp);
		}
		TrackedBug.setCounter(maxId + 1);
	}
	
	public List<TrackedBug> getBugs()
	{
		return list;
	}
	
	public List<TrackedBug> getBugsByOwner(String owner)
	{
		List<TrackedBug> temp = new ArrayList<TrackedBug>();
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getOwner() == owner)
			{
				temp.add(list.get(i));
			}
		}
		
		return temp;
	}
	
	public TrackedBug getBugByid(int id)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getBugId() == id)
			{
				return list.get(i);
			}
		}
		
		return null;
	}
	
	public void excecuteCommand(int id, Command c)
	{
		getBugByid(id).update(c);
	}
	
	public void deleteBugByid(int id)
	{
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i).getBugId() == id)
			{
				list.remove(i);
			}
		}
	}

}
