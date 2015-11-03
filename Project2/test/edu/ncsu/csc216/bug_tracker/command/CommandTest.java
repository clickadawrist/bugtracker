/**
 * 
 */
package edu.ncsu.csc216.bug_tracker.command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.bug_tracker.command.Command.CommandValue;
import edu.ncsu.csc216.bug_tracker.command.Command.Resolution;

/**
 * @author tmanthawk
 *
 */
public class CommandTest {

	private Command c;
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		c = new Command(CommandValue.CONFIRM, "devID", Resolution.DUPLICATE, "notes");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.command.Command#Command(edu.ncsu.csc216.bug_tracker.command.Command.CommandValue, java.lang.String, edu.ncsu.csc216.bug_tracker.command.Command.Resolution, java.lang.String)}.
	 */
	@Test
	public void testCommand() 
	{
		try
		{
			Command com = new Command(null,null,null,null);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(e, e);
		}
		
		try
		{
			Command com = new Command(CommandValue.RESOLVED,null,null,null);
			fail();
		}
		catch(IllegalArgumentException e)
		{
			assertEquals(e, e);
		}
		
		try
		{
			Command com = new Command(CommandValue.POSSESSION,null,null,null);
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
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.command.Command#getCommand()}.
	 */
	@Test
	public void testGetCommand() {
		assertEquals(c.getCommand(), CommandValue.CONFIRM);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.command.Command#getDeveloperId()}.
	 */
	@Test
	public void testGetDeveloperId() {
		assertEquals(c.getDeveloperId(), "devID");
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.command.Command#getResolution()}.
	 */
	@Test
	public void testGetResolution() {
		assertEquals(c.getResolution(), Resolution.DUPLICATE);
	}

	/**
	 * Test method for {@link edu.ncsu.csc216.bug_tracker.command.Command#getNote()}.
	 */
	@Test
	public void testGetNote() {
		assertEquals(c.getNote(), "notes");
	}

}
