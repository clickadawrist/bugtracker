package edu.ncsu.csc216.bug_tracker.command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.bug_tracker.command.Command.CommandValue;
import edu.ncsu.csc216.bug_tracker.command.Command.Resolution;

/**
 * Tests Command class.
 * @author Paul Hawkins
 */
public class CommandTest {

	Command c;
	/*Command c1;
	Command c2;
	Command c3;
	Command c4;
	*/
	//Private Command com?
	
	/**
	 * Sets up command object to test. 
	 */
	@Before
	public void setUp() throws Exception {
		c = new Command(CommandValue.CONFIRM, "devID", Resolution.DUPLICATE, "notes");
	/*	c1 = new Command(CommandValue.POSSESSION, "", Resolution.FIXED, "");
		c2 = new Command(CommandValue.REOPEN, "", Resolution.WONTFIX, "");
		c3 = new Command(CommandValue.RESOLVED, "", Resolution.WORKSFORME, "");
		c4 = new Command(CommandValue.VERIFIED, "", Resolution.DUPLICATE, "");
	*/
	}

	/**
	 * Tests Command method.
	 */
	@Test
	public void testCommand() 
	{
		try
		{
			c = new Command(null,null,null,null);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(e, e);
		}
		
		try
		{
			c = new Command(CommandValue.RESOLVED,null,null,null);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(e, e);
		}
		
		try
		{
			c = new Command(CommandValue.POSSESSION,null,null,null);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(e, e);
		}
		
		Command confirm = new Command(CommandValue.CONFIRM,null,null,null);
		assertEquals(confirm.getCommand(), CommandValue.CONFIRM);
		
		Command vote = new Command(CommandValue.VOTE, null,null,null);
		assertEquals(vote.getCommand(), CommandValue.VOTE);
		
		Command possession = new Command(CommandValue.POSSESSION,"Traemani",null,null);
		assertEquals(possession.getCommand(), CommandValue.POSSESSION);
		assertEquals(possession.getDeveloperId(), "Traemani");
		
		Command reopen = new Command(CommandValue.REOPEN,null,null,null);
		assertEquals(reopen.getCommand(), CommandValue.REOPEN);
		
		Command resolve = new Command(CommandValue.RESOLVED,null,Resolution.FIXED,null);
		assertEquals(resolve.getCommand(), CommandValue.RESOLVED);
		assertEquals(resolve.getResolution(), Resolution.FIXED);
		
		Command verify = new Command(CommandValue.VERIFIED,null,null,null);
		assertEquals(verify.getCommand(), CommandValue.VERIFIED);
	}

	/**
	 * Tests getCommand method.
	 */
	@Test
	public void testGetCommand() 
	{
		assertEquals(c.getCommand(), CommandValue.CONFIRM);
	}

	/**
	 * Tests getDeveloperId method.
	 */
	@Test
	public void testGetDeveloperId() 
	{
		assertEquals(c.getDeveloperId(), "devID");
	}

	/**
	 * Test getResolution method.
	 */
	@Test
	public void testGetResolution() 
	{
		assertEquals(c.getResolution(), Resolution.DUPLICATE);
	}

	/**
	 * Tests getNotes method.
	 */
	@Test
	public void testGetNote() 
	{
		assertEquals(c.getNote(), "notes");
	}
}