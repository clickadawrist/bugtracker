package edu.ncsu.csc216.bug_tracker.tracker;

import static org.junit.Assert.*;


import org.junit.Before;
import org.junit.Test;

//import edu.ncsu.csc216.bug_tracker.bug.TrackedBug;
//import edu.ncsu.csc216.bug_tracker.command.Command;
//import edu.ncsu.csc216.bug_tracker.command.Command.CommandValue;
//import edu.ncsu.csc216.bug_tracker.command.Command.Resolution;
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
	//assertEquals(double expected, double actual)
		//try{
		//} catch(IndexOutOfBoundsException e) {
			//assertEquals(model, null);
		//}		
	}

//	/**
//	 * Tests saveBugsToFile method.
//	 */
//	@Test
//	public void testSaveBugsToFile() {
//		tracker.saveBugsToFile("save_bugs.xml");
//		assertTrue(true);
		//assertNotNull(new File("save_bugs.xml"));
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
		//what the method looks like

		
		
		
//	}

//	/**
//	 * Tests loadBugsFromFile method.
//	 */
//	@Test
//	public void testLoadBugsFromFile() {
//		assertTrue(true);
//		tracker.loadBugsFromFile("bug1.xml");
//		Object[][] o = tracker.getBugListAsArray();
//		assertEquals(6, o.length);
//	}

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

//	/**
//	 * Tests getBugListByOwnerAsArray method.
//	 */
//	@Test
//	public void testGetBugListByOwnerAsArray() {
//		assertTrue(true);
		//fail("Not yet implemented");
//		tracker.loadBugsFromFile("lib/test-files/bug8.xml");
//		Object[][] o = tracker.getBugListByOwnerAsArray("owner");
//		assertEquals(1, o.length);
//	}

//	/**
//	 * Tests getBugById method.
//	 */
//	@Test
//	public void testGetBugById() {
//		assertTrue(true);
		//fail("Not yet implemented");
//		tracker.loadBugsFromFile("lib/test-files/bug8.xml");
//		TrackedBug trackedBug = tracker.getBugById(6);
//		assertEquals("owner", trackedBug.getOwner());
//	}

//	/**
//	 * Tests executeCommand method.
//	 */
//	@Test
//	public void testExecuteCommand() {
//		assertTrue(true);
//		//fail("Not yet implemented");
//		tracker.loadBugsFromFile("lib/test-files/bug8.xml");
//		tracker.executeCommand(6, new Command(CommandValue.REOPEN, "Potato", Resolution.WONTFIX, "RIP"));
//		Object[][] o = tracker.getBugListAsArray();
//		assertEquals("Open", o[0][1]);
//	}

//	/**
//	 * Tests deleteBugById method.
//	 */
//	@Test
//	public void testDeleteBugById() {
//		//fail("Not yet implemented");
//		assertTrue(true);
//	}

//	/**
//	 * Tests addBugToList method.
//	 */
//	@Test
//	public void testAddBugToList() {
//		//fail("Not yet implemented");
//		assertTrue(true);
//	}
}
