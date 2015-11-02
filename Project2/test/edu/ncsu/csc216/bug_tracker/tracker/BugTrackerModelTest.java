/**
 * 
 */
package edu.ncsu.csc216.bug_tracker.tracker;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * @author tmanthawk
 *
 */
public class BugTrackerModelTest {

	BugTrackerModel tracker;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tracker = BugTrackerModel.getInstance();
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel#saveBugsToFile(java.lang.String)}.
	 */
	@Test
	public void testSaveBugsToFile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel#loadBugsFromFile(java.lang.String)}.
	 */
	@Test
	public void testLoadBugsFromFile() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel#createNewBugList()}.
	 */
	@Test
	public void testCreateNewBugList() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel#getBugListAsArray()}.
	 */
	@Test
	public void testGetBugListAsArray() {
		tracker.addBugToList("summ", "repo");
		tracker.addBugToList("jasd", "reoa");
		Object[][] trackerArray = tracker.getBugListAsArray();
		assertEquals(2, trackerArray.length);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel#getBugListByOwnerAsArray(java.lang.String)}.
	 */
	@Test
	public void testGetBugListByOwnerAsArray() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel#getBugById(int)}.
	 */
	@Test
	public void testGetBugById() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel#executeCommand(int, edu.ncsu.csc216.bug_tracker.command.Command)}.
	 */
	@Test
	public void testExecuteCommand() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel#deleteBugById(int)}.
	 */
	@Test
	public void testDeleteBugById() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel#addBugToList(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddBugToList() {
		fail("Not yet implemented");
	}

}
