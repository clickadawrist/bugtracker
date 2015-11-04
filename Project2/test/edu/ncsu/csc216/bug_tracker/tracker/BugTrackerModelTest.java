package edu.ncsu.csc216.bug_tracker.tracker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.bug_tracker.bug.TrackedBug;
import edu.ncsu.csc216.bug_tracker.xml.Bug;
import edu.ncsu.csc216.bug_tracker.xml.BugReader;
import edu.ncsu.csc216.bug_tracker.xml.BugWriter;

/**
 * Tests BugTrackerModel.
 * @author Paul Hakwins and Manaka Green
 */
public class BugTrackerModelTest {

	BugTrackerModel tracker;
	BugList bugList;
	//do I need the following: private static BugTrackerModel model; ?
	
	Bug b;
	BugList one;
	BugList two;
	BugTrackerModel a;
	
	/**
	 * Set up bugs here to help testing with bug tracker model?
	 * 
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		tracker = BugTrackerModel.getInstance();
//		bugList = new BugList();
//		bugList.addBug("This bug is killing everyone", "Paul");
//		bugList.addBug("This bug is eating everyone", "Manaka");
//		bugList.addBug("This bug is paying everyone", "Trae");
//		bugList.addBug("This bug is failing everyone", "Stevie");
//		bugList.addBug("This bug is catching everyone", "James");
//		bugList.addBug("This bug is loving everyone", "Jason");
		b = new Bug();
		b.summary = "This bug is loving everyone";
		b.reporter = "Jason";
	}

	/**
	 * Tests getInstance method.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(tracker);
	//assertEquals(double expected, double actual)
	//How do you test this method??
		try{
		} catch(IndexOutOfBoundsException e) {
			//assertEquals(model, null);
		}		
	}

	/**
	 * Tests saveBugsToFile method.
	 */
	@Test
	public void testSaveBugsToFile() {
		BugWriter writer = new BugWriter("save_bugs.xml");
		writer.addItem(b);
//		List<TrackedBug> bList = bugList.getBugs();
//		for (int i = 0; i < bList.size(); i++) {
//			writer.addItem(b);
//		}
		/*
		BugWriter writer = new BugWriter(one);
		for(int i = 0; i < bugs.getBugs().size(); i++)
		{
			assertEquals(one, two)
			writer.addItem(bugs.getBugs().get(i).getXMLBug());
		}
		*/
		//Test this method.
		//BugList hugs = new BugList();
		
		
		
	}

	/**
	 * Tests loadBugsFromFile method.
	 */
	@Test
	public void testLoadBugsFromFile() {
		//fail("Not yet implemented");
		BugReader bugReader = new BugReader("save_bugs.xml");
	}

	/**
	 * Tests createNewBugList method.
	 */
	@Test
	public void testCreateNewBugList() {
		//fail("Not yet implemented");
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

	/**
	 * Tests getBugListByOwnerAsArray method.
	 */
	@Test
	public void testGetBugListByOwnerAsArray() {
		//fail("Not yet implemented");
	}

	/**
	 * Tests getBugById method.
	 */
	@Test
	public void testGetBugById() {
		//fail("Not yet implemented");
	}

	/**
	 * Tests executeCommand method.
	 */
	@Test
	public void testExecuteCommand() {
		//fail("Not yet implemented");
	}

	/**
	 * Tests deleteBugById method.
	 */
	@Test
	public void testDeleteBugById() {
		//fail("Not yet implemented");
	}

	/**
	 * Tests addBugToList method.
	 */
	@Test
	public void testAddBugToList() {
		//fail("Not yet implemented");
	}
}
