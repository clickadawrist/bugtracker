package edu.ncsu.csc216.bug_tracker.tracker;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.bug_tracker.xml.Bug;


/**
 * Tests BugTrackerModel.
 * @author Paul Hakwins and Manaka Green
 */
public class BugTrackerModelTest {

	BugTrackerModel tracker;
	BugList bugList;
	
	
	Bug b;
	BugList one;
	BugList two;
	BugTrackerModel a;
	
	/**
	 * Set up bugs here to help testing with bug tracker model
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {
		tracker = BugTrackerModel.getInstance();
	}

	/**
	 * Tests getInstance method.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(tracker);
	}

	/**
	 * Tests createNewBugList method.
	 */
	@Test
	public void testCreateNewBugList() {
		tracker.createNewBugList();
		Object[][] o = tracker.getBugListAsArray();
		assertEquals(0, o.length);
	}

	/**
	 * Tests getBugListAsArray method.
	 */
	@Test
	public void testGetBugListAsArray() {
		tracker.addBugToList("summ", "repo");
		tracker.addBugToList("jasd", "reoa");
		Object[][] trackerArray = tracker.getBugListAsArray();
		assertEquals(2, trackerArray.length);
	}
}