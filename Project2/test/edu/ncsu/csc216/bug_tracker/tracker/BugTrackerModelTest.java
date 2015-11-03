package edu.ncsu.csc216.bug_tracker.tracker;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.course_manager.courses.Course;
import edu.ncsu.csc216.course_manager.io.CourseRecordIO;

/**
 * Tests BugTrackerModel.
 * @author Paul Hakwins and Manaka Green
 */
public class BugTrackerModelTest {

	BugTrackerModel tracker;
	private static BugTrackerModel model;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		tracker = BugTrackerModel.getInstance();
	}

	/**
	 * Tests getInstance method.
	 */
	@Test
	public void testGetInstance() {
		try {
			
			model = new BugTrackerModel();
			
			} 
		catch (NullSomethingSomethingException e) 
		{
			fail("No ");
		}
		
		
	}

	/**
	 * Tests saveBugsToFile method.
	 */
	@Test
	public void testSaveBugsToFile() {
		fail("Not yet implemented");
	}

	/**
	 * Tests loadBugsFromFile method.
	 */
	@Test
	public void testLoadBugsFromFile() {
		fail("Not yet implemented");
	}

	/**
	 * Tests createNewBugList method.
	 */
	@Test
	public void testCreateNewBugList() {
		fail("Not yet implemented");
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
		fail("Not yet implemented");
	}

	/**
	 * Tests getBugById method.
	 */
	@Test
	public void testGetBugById() {
		fail("Not yet implemented");
	}

	/**
	 * Tests executeCommand method.
	 */
	@Test
	public void testExecuteCommand() {
		fail("Not yet implemented");
	}

	/**
	 * Tests deleteBugById method.
	 */
	@Test
	public void testDeleteBugById() {
		fail("Not yet implemented");
	}

	/**
	 * Tests addBugToList method.
	 */
	@Test
	public void testAddBugToList() {
		fail("Not yet implemented");
	}
}
