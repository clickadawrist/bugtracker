package edu.ncsu.csc216.bug_tracker.tracker;

import java.util.ArrayList;
import java.util.List;

import edu.ncsu.csc216.bug_tracker.bug.TrackedBug;
import edu.ncsu.csc216.bug_tracker.command.Command;
import edu.ncsu.csc216.bug_tracker.xml.Bug;

/**
 * Handles lists of bugs between Bug Tracker's XML list, scanned in XML list,
 * and user generated inputs bug lists.
 * @author Paul Hawkins and Manaka Green
 */
public class BugList {

	/** List of tracked bugs.  */
	private ArrayList<TrackedBug> list;
	
	/**
	 * Constructs a new bug list and sets first bug count to 0.
	 */
	public BugList()  
	{
		TrackedBug.setCounter(0);
	}
	
	/**
	 * Adds bug to Bug Tracker's XML list of bugs from user input.
	 * @param summary Description of the bug being added
	 * @param reporter Name of the person adding the bug
	 * @return temp ID of the newly added bug
	 */
	public int addBug(String summary, String reporter)
	{
		TrackedBug temp = new TrackedBug(summary, reporter);
		list.add(temp);
		return temp.getBugId();
	}
	
	/**
	 * Adds bug to Bug Tracker's XML list of bugs from a different XML.
	 * Sets id of newly added bugs.
	 * @param bugList List of bugs from non Bug-Tracker XML
	 */
	public void addXMLBug(List<Bug> bugList) 
	{
		int maxId = 0;
		for(int i = 0; i < bugList.size(); i++)
		{
			TrackedBug temp = new TrackedBug(bugList.get(i));
			maxId = Math.max(maxId, temp.getBugId());
			list.add(temp);
		}
		TrackedBug.setCounter(maxId + 1);
	}
	
	/**
	 * Gets bugs from Bug Tracker's list.
	 * @return list List of bugs from Bug Tracker
	 */
	public List<TrackedBug> getBugs()
	{
		return list;
	}
	
	/**
	 * Gets bugs from Bug Tracker's list according to owner.
	 * @param owner Name of person who's tracking the bug
	 * @return temp List of bugs from Bug Tracker by owner 
	 */
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
	
	/**
	 * Gets bugs from Bug Tracker's list according bug's id.
	 * @param id Integer value of bug's id
	 * @return list List of bugs from Bug Tracker by id
	 */
	public TrackedBug getBugById(int id)
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
	
	/**
	 * Updates the state bug with its unique id.
	 * @param id Integer value of bug's id
	 * @param c Command that updates state of bug
	 */
	public void executeCommand(int id, Command c)
	{
		getBugById(id).update(c);
	}
	
	/**
	 * Deletes bugs from Bug Tracker's list according to bug's id.
	 * @param id Integer value of bug's id
	 */
	public void deleteBugById(int id)
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
