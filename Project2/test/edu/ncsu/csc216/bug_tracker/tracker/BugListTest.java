/**
 * 
 */
package edu.ncsu.csc216.bug_tracker.tracker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.bug_tracker.bug.TrackedBug;
import edu.ncsu.csc216.bug_tracker.command.Command;
import edu.ncsu.csc216.bug_tracker.command.Command.CommandValue;
import edu.ncsu.csc216.bug_tracker.command.Command.Resolution;
import edu.ncsu.csc216.bug_tracker.xml.Bug;

/**
 * @author tmanthawk
 *
 */
public class BugListTest {

	BugList bugs;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		bugs = new BugList();
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugList#addBug(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddBug() {
		assertEquals(0, bugs.getBugs().size());
		bugs.addBug("This bug is killing everyone", "Paul");
		assertEquals(1, bugs.getBugs().size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugList#addXMLBug(java.util.List)}.
	 */
	@Test
	public void testAddXMLBug() {
//		List<Bug> temp = new ArrayList<Bug>();
//		Bug bug = new Bug();
//		Bug bug1 = new Bug();
//		Bug bug2 = new Bug();
//		temp.add(bug);
//		temp.add(bug1);
//		temp.add(bug2);
//		
//		bugs.addXMLBugs(temp);
//		assertEquals(3, bugs.getBugs().size());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugList#getBugs()}.
	 */
	@Test
	public void testGetBugs() {
		bugs.addBug("summary", "reporter");
		
		assertEquals("summary",bugs.getBugs().get(0).getSummary());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugList#getBugsByOwner(java.lang.String)}.
	 */
	@Test
	public void testGetBugsByOwner() {
		bugs.addBug("summary", "reporter");
		bugs.getBugs().get(0).update(new Command(CommandValue.CONFIRM,null,null,null));
		bugs.getBugs().get(0).update(new Command(CommandValue.POSSESSION,"Traemani",null,null));
		bugs.addBug("summar", "reporte");
		bugs.getBugs().get(1).update(new Command(CommandValue.CONFIRM,null,null,null));
		bugs.getBugs().get(1).update(new Command(CommandValue.POSSESSION,"Traemani",null,null));
		bugs.addBug("summa", "report");
		bugs.getBugs().get(2).update(new Command(CommandValue.CONFIRM,null,null,null));
		bugs.getBugs().get(2).update(new Command(CommandValue.POSSESSION,"Manaka",null,null));
		
		List<TrackedBug> list = new ArrayList<TrackedBug>();
		list.add(bugs.getBugs().get(0));
		list.add(bugs.getBugs().get(1));
		
		List<TrackedBug> list1 = new ArrayList<TrackedBug>();
		list1.add(bugs.getBugs().get(2));
		
		assertEquals(list1, bugs.getBugsByOwner("Manaka"));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugList#getBugById(int)}.
	 */
	@Test
	public void testGetBugById() {
		bugs.addBug("summary", "reporter");
		bugs.addBug("summar", "reporter");
		bugs.addBug("summa", "reporter");
		
		assertEquals(bugs.getBugs().get(1), bugs.getBugById(1));
		assertEquals(bugs.getBugs().get(0), bugs.getBugById(0));
		assertEquals(bugs.getBugs().get(2), bugs.getBugById(2));
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugList#executeCommand(int, edu.ncsu.csc216.bug_tracker.command.Command)}.
	 */
	@Test
	public void testExecuteCommand() {
		bugs.addBug("summary", "reporter");
		bugs.executeCommand(0, new Command(CommandValue.VOTE,null,null,null));
		assertEquals("Unconfirmed", bugs.getBugById(0).getState().getStateName());
		bugs.executeCommand(0, new Command(CommandValue.CONFIRM,null,null,null));
		assertEquals("New", bugs.getBugById(0).getState().getStateName());
		bugs.executeCommand(0, new Command(CommandValue.POSSESSION,"Traemani",null,null));
		assertEquals("Assigned", bugs.getBugById(0).getState().getStateName());
		bugs.executeCommand(0, new Command(CommandValue.RESOLVED,null,Resolution.FIXED,null));
		assertEquals("Resolved", bugs.getBugById(0).getState().getStateName());
		bugs.executeCommand(0, new Command(CommandValue.VERIFIED,null,null,null));
		assertEquals("Closed", bugs.getBugById(0).getState().getStateName());
		bugs.executeCommand(0, new Command(CommandValue.REOPEN,null,null,null));
		assertEquals("Reopen", bugs.getBugById(0).getState().getStateName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugList#deleteBugById(int)}.
	 */
	@Test
	public void testDeleteBugById() {
		bugs.deleteBugById(0);
		assertEquals(0, bugs.getBugs().size());
		bugs.addBug("summary", "reporter");
		assertEquals(1, bugs.getBugs().size());
		bugs.deleteBugById(0);
		assertEquals(0, bugs.getBugs().size());
	}

}
