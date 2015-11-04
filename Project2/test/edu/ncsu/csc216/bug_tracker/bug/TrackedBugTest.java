/**
 * 
 */
package edu.ncsu.csc216.bug_tracker.bug;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.bug_tracker.command.Command;
import edu.ncsu.csc216.bug_tracker.command.Command.CommandValue;
import edu.ncsu.csc216.bug_tracker.command.Command.Resolution;

/**
 * @author tmanthawk
 *
 */
public class TrackedBugTest {

	TrackedBug bug;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TrackedBug.setCounter(0);
		bug = new TrackedBug("This bug is killing everyone", "Paul");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#TrackedBug(edu.ncsu.csc216.bug_tracker.xml.Bug)}.
	 */
	@Test
	public void testTrackedBug() {
		try
		{
			TrackedBug b = new TrackedBug("","adsfga");
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(e,e);
		}
		
		try
		{
			TrackedBug b = new TrackedBug("adsfga","");
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(e,e);
		}
		
		TrackedBug.setCounter(0);
		TrackedBug b = new TrackedBug("me", "you");
		assertEquals(b.getSummary(), "me");
		assertEquals(b.getReporter(), "you");
		assertEquals(b.getBugId(), 0);
		assertEquals(b.getNotesString(), "");
		assertEquals(b.getOwner(), null);
		assertEquals(b.getResolution(), null);
		assertEquals(b.getState().getStateName(), "Unconfirmed");
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#incrementCounter()}.
	 */
	@Test
	public void testIncrementCounter() {
		assertEquals(0, bug.getBugId());
		TrackedBug b = new TrackedBug("I love this bug", "Traemani");
		assertEquals(1, b.getBugId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getBugId()}.
	 */
	@Test
	public void testGetBugId() {
		assertEquals(0, bug.getBugId());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getState()}.
	 */
	@Test
	public void testGetState() {
		assertEquals("Unconfirmed", bug.getState().getStateName());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getResolution()}.
	 */
	@Test
	public void testGetResolution() {
		assertEquals(null, bug.getResolution());
		bug.update(new Command(CommandValue.CONFIRM, null, null, null));
		bug.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
		bug.update(new Command(CommandValue.RESOLVED,null,Resolution.DUPLICATE,null));
		assertEquals(Resolution.DUPLICATE, bug.getResolution());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getResolutionString()}.
	 */
	@Test
	public void testGetResolutionString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getOwner()}.
	 */
	@Test
	public void testGetOwner() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getSummary()}.
	 */
	@Test
	public void testGetSummary() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getVotes()}.
	 */
	@Test
	public void testGetVotes() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getReporter()}.
	 */
	@Test
	public void testGetReporter() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getNotes()}.
	 */
	@Test
	public void testGetNotes() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getNotesString()}.
	 */
	@Test
	public void testGetNotesString() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#isConfirmed()}.
	 */
	@Test
	public void testIsConfirmed() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#update(edu.ncsu.csc216.bug_tracker.command.Command)}.
	 */
	@Test
	public void testUpdate() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getXMLBug()}.
	 */
	@Test
	public void testGetXMLBug() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#setCounter(int)}.
	 */
	@Test
	public void testSetCounter() {
		fail("Not yet implemented");
	}

}
