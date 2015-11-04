package edu.ncsu.csc216.bug_tracker.bug;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.bug_tracker.command.Command;
import edu.ncsu.csc216.bug_tracker.command.Command.CommandValue;
import edu.ncsu.csc216.bug_tracker.command.Command.Resolution;
import edu.ncsu.csc216.bug_tracker.xml.Bug;

/**
 * Tests TrackedBug class.
 * @author Paul Hawkins
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
	 * Sets up test. 
	 * @throws Exception 
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
	 * Tests the constructors for the Tracked Bug class
	 */
	@Test
	public void testTrackedBug() {
		//Test constructing a bug with a blank summary
		try
		{
			@SuppressWarnings("unused")
			TrackedBug b = new TrackedBug("", "adsfga");
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(e, e);
		}
		
		//Test constructing a bug with a blank reporter
		try
		{
			@SuppressWarnings("unused")
			TrackedBug b = new TrackedBug("adsfga", "");
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(e, e);
		}
		
		//Tests a valid constructor
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
	 * Tests the method for incrementing the counter
	 */
	@Test
	public void testIncrementCounter() {
		//Tests that each bug ID is one more than the last
		assertEquals(0, bug.getBugId());
		assertEquals(1, bug1.getBugId());
		assertEquals(2, bug2.getBugId());
		assertEquals(3, bug3.getBugId());
		assertEquals(4, bug4.getBugId());
		assertEquals(5, bug5.getBugId());
	}

	/**
	 * Tests the getter for BugID
	 */
	@Test
	public void testGetBugId() {
		assertEquals(0, bug.getBugId());
	}

	/**
	 * Tests the getter for BugState
	 */
	@Test
	public void testGetState() {
		assertEquals("Unconfirmed", bug.getState().getStateName());
	}

	/**
	 * Tests the getter for the resolution of the bug
	 */
	@Test
	public void testGetResolution() {
		//Tests that the beginning resolution is null
		assertEquals(null, bug.getResolution());
		
		//Updates the resolution to duplicate
		bug.update(new Command(CommandValue.CONFIRM, null, null, null));
		bug.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
		bug.update(new Command(CommandValue.RESOLVED, null, Resolution.DUPLICATE, null));
		
		//Tests the resolution is now duplicate
		assertEquals(Resolution.DUPLICATE, bug.getResolution());
	}

	/**
	 * Tests that the resolution returns the correct string
	 */
	@Test
	public void testGetResolutionString() {
		//Changes the resolution to Duplicate and checks that the string returned is "Duplicate"
		bug.update(new Command(CommandValue.CONFIRM, null, null, null));
		bug.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
		bug.update(new Command(CommandValue.RESOLVED, null, Resolution.DUPLICATE, null));
		assertEquals("Duplicate", bug.getResolutionString());
		
		//Changes the resolution to Fixed and checks that the string returned is "Fixed"
		bug1.update(new Command(CommandValue.CONFIRM, null, null, null));
		bug1.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
		bug1.update(new Command(CommandValue.RESOLVED, null, Resolution.FIXED, null));
		assertEquals("Fixed", bug1.getResolutionString());
		
		//Changes the resolution to WontFix and checks that the string returned is "WontFix"
		bug2.update(new Command(CommandValue.CONFIRM, null, null, null));
		bug2.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
		bug2.update(new Command(CommandValue.RESOLVED, null, Resolution.WONTFIX, null));
		assertEquals("WontFix", bug2.getResolutionString());
		
		//Changes the resolution to WorksForMe and checks that the string returned is "WorksForMe"
		bug3.update(new Command(CommandValue.CONFIRM, null, null, null));
		bug3.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
		bug3.update(new Command(CommandValue.RESOLVED, null, Resolution.WORKSFORME, null));
		assertEquals("WorksForMe", bug3.getResolutionString());
	}

	/**
	 * Tests the getter for the bug's owner
	 */
	@Test
	public void testGetOwner() {
		//Checks that the owner is null in the beginning
		assertEquals(null, bug.getOwner());
		
		//Assigns the owner to Traemani
		bug.update(new Command(CommandValue.CONFIRM, null, null, null));
		bug.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
		
		//Checks that the owner is Traemani
		assertEquals("Traemani", bug.getOwner());
	}

	/**
	 * Tests the getter for the bug summary
	 */
	@Test
	public void testGetSummary() {
		assertEquals("This bug is killing everyone", bug.getSummary());
	}

	/**
	 * Tests the getter for the bugs votes
	 */
	@Test
	public void testGetVotes() {
		assertEquals(1, bug.getVotes());
	}

	/**
	 * Tests the getter for the bugs reporter
	 */
	@Test
	public void testGetReporter() {
		assertEquals("Paul", bug.getReporter());
	}

	/**
	 * Tests the getter for the bug notes
	 */
	@Test
	public void testGetNotes() {
		//Creates a temp array with the same notes as the bug
		ArrayList<String> temp = new ArrayList<String>();
		
		//Adds note and tests that the notes arrays are equal
		assertEquals(temp, bug.getNotes());
		bug.update(new Command(CommandValue.VOTE, null, null, "note 1"));
		temp.add("note 1");
		assertEquals(temp, bug.getNotes());
		bug.update(new Command(CommandValue.VOTE, null, null, "note 2"));
		temp.add("note 2");
		assertEquals(temp, bug.getNotes());
		
		
	}

	/**
	 * Tests the getter for the bug notes string
	 */
	@Test
	public void testGetNotesString() {
		//Tests that the beginning notes string is empty
		assertEquals("", bug.getNotesString());
		
		//Adds 2 notes to the bug
		bug.update(new Command(CommandValue.VOTE, null, null, "note 1"));
		bug.update(new Command(CommandValue.VOTE, null, null, "note 2"));
		
		//Tests that the bug notes string is what it is supposed to be
		assertEquals("note 1\n------\nnote 2\n------\n", bug.getNotesString());
	}

	/**
	 * Tests the method to check if the bug is confirmed or not
	 */
	@Test
	public void testIsConfirmed() {
		//Tests that the bug is unconfirmed at the beginning
		assertFalse(bug.isConfirmed());
		
		//Confirms the bug
		bug.update(new Command(CommandValue.CONFIRM, null, null, null));
		
		//Tests that the bug is confirmed
		assertTrue(bug.isConfirmed());
	}

	/**
	 * Tests the method to update the bug's state and attributes
	 */
	@Test
	public void testUpdate() {
		//Checks that the bug state is unconfirmed at the beginning
		assertEquals("Unconfirmed", bug.getState().getStateName());
		
		//Votes on the bug and makes sure the bug has 2 votes and is
		//still unconfirmed
		bug.update(new Command(CommandValue.VOTE, null, null, null));
		assertEquals("Unconfirmed", bug.getState().getStateName());
		assertEquals(2, bug.getVotes());
		
		//Votes again and makes sure the bug has 3 votes and is in the 
		//new state
		bug.update(new Command(CommandValue.VOTE, null, null, null));
		assertEquals("New", bug.getState().getStateName());
		assertEquals(3, bug.getVotes());
		
		//Assigns the bug an owner of Traemani and checks that the state is 
		//Assigned and the owner is Traemani
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
		
		bug2.update(new Command(CommandValue.CONFIRM, null, null, null));
		
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
		
		bug2.update(new Command(CommandValue.POSSESSION, "Traemani", null, null));
		
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
		
		bug2.update(new Command(CommandValue.RESOLVED, null, Resolution.FIXED, null));
		
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
		
		bug2.update(new Command(CommandValue.VERIFIED, null, null, null));
		
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
		
		bug2.update(new Command(CommandValue.REOPEN, null, null, null));
		
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
	 * Tests the getter for changing the trackedbug to an XMLBug
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
		bug.update(new Command(CommandValue.RESOLVED, null, Resolution.DUPLICATE, null));
		
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

	
//	@Test
//	public void testAddXMLBug() {
//		BugReader bug = new BugReader("bug.xml");
//	}
}
