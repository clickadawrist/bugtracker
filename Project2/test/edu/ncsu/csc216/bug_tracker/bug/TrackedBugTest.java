/**
 * 
 */
package edu.ncsu.csc216.bug_tracker.bug;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.bug_tracker.command.Command;
import edu.ncsu.csc216.bug_tracker.command.Command.CommandValue;
import edu.ncsu.csc216.bug_tracker.command.Command.Resolution;
import edu.ncsu.csc216.bug_tracker.xml.Bug;
import edu.ncsu.csc216.bug_tracker.xml.NoteList;

/**
 * @author tmanthawk
 *
 */
public class TrackedBugTest {

	TrackedBug bug;
	TrackedBug bug1;
	TrackedBug bug2;
	TrackedBug bug3;
	TrackedBug bug4;
	TrackedBug bug5;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		TrackedBug.setCounter(0);
		bug = new TrackedBug("This bug is killing everyone", "Paul");
		bug1 = new TrackedBug("This bug is loving everyone", "Jason");
		bug2 = new TrackedBug("This bug is eating everyone", "Manaka");
		bug3 = new TrackedBug("This bug is paying everyone", "Trae");
		bug4 = new TrackedBug("This bug is failing everyone", "Stevie");
		bug5 = new TrackedBug("This bug is catching everyone", "James");
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
		assertEquals(1, bug1.getBugId());
		assertEquals(2, bug2.getBugId());
		assertEquals(3, bug3.getBugId());
		assertEquals(4, bug4.getBugId());
		assertEquals(5, bug5.getBugId());
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
		bug.update(new Command(CommandValue.CONFIRM, null, null, null));
		bug.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
		bug.update(new Command(CommandValue.RESOLVED,null,Resolution.DUPLICATE,null));
		assertEquals("Duplicate", bug.getResolutionString());
		
		bug1.update(new Command(CommandValue.CONFIRM, null, null, null));
		bug1.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
		bug1.update(new Command(CommandValue.RESOLVED,null,Resolution.FIXED,null));
		assertEquals("Fixed", bug1.getResolutionString());
		
		bug2.update(new Command(CommandValue.CONFIRM, null, null, null));
		bug2.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
		bug2.update(new Command(CommandValue.RESOLVED,null,Resolution.WONTFIX,null));
		assertEquals("WontFix", bug2.getResolutionString());
		
		bug3.update(new Command(CommandValue.CONFIRM, null, null, null));
		bug3.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
		bug3.update(new Command(CommandValue.RESOLVED,null,Resolution.WORKSFORME,null));
		assertEquals("WorksForMe", bug3.getResolutionString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getOwner()}.
	 */
	@Test
	public void testGetOwner() {
		assertEquals(null, bug.getOwner());
		bug.update(new Command(CommandValue.CONFIRM, null, null, null));
		bug.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
		assertEquals("Traemani", bug.getOwner());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getSummary()}.
	 */
	@Test
	public void testGetSummary() {
		assertEquals("This bug is killing everyone", bug.getSummary());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getVotes()}.
	 */
	@Test
	public void testGetVotes() {
		assertEquals(1, bug.getVotes());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getReporter()}.
	 */
	@Test
	public void testGetReporter() {
		assertEquals("Paul", bug.getReporter());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getNotes()}.
	 */
	@Test
	public void testGetNotes() {
		ArrayList<String> temp = new ArrayList<String>();
		assertEquals(temp, bug.getNotes());
		bug.update(new Command(CommandValue.VOTE, null, null, "note 1"));
		temp.add("note 1");
		assertEquals(temp, bug.getNotes());
		bug.update(new Command(CommandValue.VOTE, null, null, "note 2"));
		temp.add("note 2");
		assertEquals(temp, bug.getNotes());
		
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getNotesString()}.
	 */
	@Test
	public void testGetNotesString() {
		assertEquals("", bug.getNotesString());
		bug.update(new Command(CommandValue.VOTE, null, null, "note 1"));
		bug.update(new Command(CommandValue.VOTE, null, null, "note 2"));
		assertEquals("note 1\n------\nnote 2\n------\n", bug.getNotesString());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#isConfirmed()}.
	 */
	@Test
	public void testIsConfirmed() {
		assertFalse(bug.isConfirmed());
		bug.update(new Command(CommandValue.CONFIRM, null, null, null));
		assertTrue(bug.isConfirmed());
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#update(edu.ncsu.csc216.bug_tracker.command.Command)}.
	 */
	@Test
	public void testUpdate() {
		assertEquals("Unconfirmed", bug.getState().getStateName());
		bug.update(new Command(CommandValue.VOTE, null, null, null));
		assertEquals("Unconfirmed", bug.getState().getStateName());
		assertEquals(2, bug.getVotes());
		bug.update(new Command(CommandValue.VOTE, null, null, null));
		assertEquals("New", bug.getState().getStateName());
		assertEquals(3, bug.getVotes());
		bug.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
		assertEquals("Assigned", bug.getState().getStateName());
		assertEquals("Traemani", bug.getOwner());
		bug.update(new Command(CommandValue.RESOLVED, null, Resolution.FIXED, null));
		assertEquals("Resolved", bug.getState().getStateName());
		assertEquals(Resolution.FIXED, bug.getResolution());
		bug.update(new Command(CommandValue.VERIFIED, null, null, null));
		assertEquals("Closed", bug.getState().getStateName());
		bug.update(new Command(CommandValue.REOPEN, null, null, null));
		assertEquals("Unconfirmed", bug.getState().getStateName());
		bug.update(new Command(CommandValue.VOTE, null, null, null));
		assertEquals("Assigned", bug.getState().getStateName());
		bug.update(new Command(CommandValue.RESOLVED, null, Resolution.WONTFIX, null));
		assertEquals("Closed", bug.getState().getStateName());
		assertEquals(Resolution.WONTFIX, bug.getResolution());
		
		bug1.update(new Command(CommandValue.CONFIRM, null, null, null));
		assertEquals("New", bug1.getState().getStateName());
		bug1.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
		assertEquals("Assigned", bug1.getState().getStateName());
		bug1.update(new Command(CommandValue.RESOLVED, null, Resolution.FIXED, null));
		assertEquals("Resolved", bug1.getState().getStateName());
		bug1.update(new Command(CommandValue.REOPEN, null, null, null));
		assertEquals("Reopen", bug1.getState().getStateName());
		bug1.update(new Command(CommandValue.POSSESSION, "Manaka", null, null));
		assertEquals("Assigned", bug1.getState().getStateName());
		
		try
		{
			bug2.update(new Command(CommandValue.POSSESSION, "Manaka", null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Unconfirmed", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.REOPEN, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Unconfirmed", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.RESOLVED, null, Resolution.DUPLICATE, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Unconfirmed", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.VERIFIED, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Unconfirmed", bug2.getState().getStateName());
		}
		
		bug2.update(new Command(CommandValue.CONFIRM,null,null,null));
		
		try
		{
			bug2.update(new Command(CommandValue.CONFIRM, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("New", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.REOPEN, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("New", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.RESOLVED, null, Resolution.DUPLICATE, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("New", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.VERIFIED, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("New", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.VOTE, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("New", bug2.getState().getStateName());
		}
		
		bug2.update(new Command(CommandValue.POSSESSION, "Traemani", null,null));
		
		try
		{
			bug2.update(new Command(CommandValue.CONFIRM, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Assigned", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Assigned", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.REOPEN, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Assigned", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.VERIFIED, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Assigned", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.VOTE, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Assigned", bug2.getState().getStateName());
		}
		
		bug2.update(new Command(CommandValue.RESOLVED,null,Resolution.FIXED,null));
		
		try
		{
			bug2.update(new Command(CommandValue.CONFIRM, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Resolved", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Resolved", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.RESOLVED, null, Resolution.DUPLICATE, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Resolved", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.VOTE, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Resolved", bug2.getState().getStateName());
		}
		
		bug2.update(new Command(CommandValue.VERIFIED,null,null,null));
		
		try
		{
			bug2.update(new Command(CommandValue.CONFIRM, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Closed", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Closed", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.RESOLVED, null, Resolution.DUPLICATE, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Closed", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.VERIFIED, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Closed", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.VOTE, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Closed", bug2.getState().getStateName());
		}
		
		bug2.update(new Command(CommandValue.REOPEN,null,null,null));
		
		try
		{
			bug2.update(new Command(CommandValue.CONFIRM, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Reopen", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.REOPEN, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Reopen", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.VERIFIED, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Reopen", bug2.getState().getStateName());
		}
		
		try
		{
			bug2.update(new Command(CommandValue.VOTE, null, null, null));
			fail();
		}
		catch(UnsupportedOperationException e)
		{
			assertEquals("Reopen", bug2.getState().getStateName());
		}
		
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.bug.TrackedBug#getXMLBug()}.
	 */
	@Test
	public void testGetXMLBug() {
		Bug buggy = new Bug();
		buggy = bug.getXMLBug();
		assertEquals(0, buggy.getId());
		assertEquals(null, buggy.getOwner());
		assertEquals("Paul", buggy.getReporter());
		assertEquals(null, buggy.getResolution());
		assertEquals("Unconfirmed", buggy.getState());
		assertEquals("This bug is killing everyone", buggy.getSummary());
		assertEquals(1, buggy.getVotes());
		
		bug.update(new Command(CommandValue.CONFIRM, null, null, "note 1"));
		bug.update(new Command(CommandValue.POSSESSION, "Traemani", null, "note 2"));
		bug.update(new Command(CommandValue.RESOLVED,null,Resolution.DUPLICATE,null));
		
		buggy = bug.getXMLBug();
		assertEquals(0, buggy.getId());
		assertEquals("note 1", buggy.getNoteList().getNote().get(0));
		assertEquals("note 2", buggy.getNoteList().getNote().get(1));
		assertEquals("Traemani", buggy.getOwner());
		assertEquals("Paul", buggy.getReporter());
		assertEquals("Duplicate", buggy.getResolution());
		assertEquals("Closed", buggy.getState());
		assertEquals("This bug is killing everyone", buggy.getSummary());
		assertEquals(1, buggy.getVotes());
		
	}

}
