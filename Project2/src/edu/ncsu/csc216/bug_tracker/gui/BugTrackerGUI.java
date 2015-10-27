package edu.ncsu.csc216.bug_tracker.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.bug_tracker.bug.TrackedBug;
import edu.ncsu.csc216.bug_tracker.command.Command;
import edu.ncsu.csc216.bug_tracker.command.Command.Resolution;
import edu.ncsu.csc216.bug_tracker.tracker.BugTrackerModel;

/**
 * Container for the BugTracker that has the menu options for new bug 
 * tracking files, loading existing files, saving files and quitting.
 * Depending on user actions, other {@link JPanel}s are loaded for the
 * different ways users interact with the UI.
 * 
 * @author Dr. Sarah Heckman (heckman@csc.ncsu.edu)
 */
public class BugTrackerGUI extends JFrame implements ActionListener {
	
	/** ID number used for object serialization. */
	private static final long serialVersionUID = 1L;
	/** Title for top of GUI. */
	private static final String APP_TITLE = "Bug Tracker";
	/** Text for the File Menu. */
	private static final String FILE_MENU_TITLE = "File";
	/** Text for the New Bug XML menu item. */
	private static final String NEW_XML_TITLE = "New";
	/** Text for the Load Bug XML menu item. */
	private static final String LOAD_XML_TITLE = "Load";
	/** Text for the Save menu item. */
	private static final String SAVE_XML_TITLE = "Save";
	/** Text for the Quit menu item. */
	private static final String QUIT_TITLE = "Quit";
	/** Menu bar for the GUI that contains Menus. */
	private JMenuBar menuBar;
	/** Menu for the GUI. */
	private JMenu menu;
	/** Menu item for creating a new file containing {@link TrackedBug}s. */
	private JMenuItem itemNewBugXML;
	/** Menu item for loading a file containing {@link TrackedBug}s. */
	private JMenuItem itemLoadBugXML;
	/** Menu item for saving the bug list. */
	private JMenuItem itemSaveBugXML;
	/** Menu item for quitting the program. */
	private JMenuItem itemQuit;
	/** Panel that will contain different views for the application. */
	private JPanel panel;
	/** Constant to identify BugListPanel for {@link CardLayout}. */
	private static final String BUG_LIST_PANEL = "BugListPanel";
	/** Constant to identify UnconfirmedPanel for {@link CardLayout}. */
	private static final String UNCONFIRMED_PANEL = "UnconfirmedPanel";
	/** Constant to identify NewPanel for {@link CardLayout}. */
	private static final String NEW_PANEL = "NewPanel";
	/** Constant to identify AssignedPanel for {@link CardLayout}. */
	private static final String ASSIGNED_PANEL = "AssignedPanel";
	/** Constant to identify ResolvedPanel for {@link CardLayout}. */
	private static final String RESOLVED_PANEL = "ResolvedPanel";
	/** Constant to identify ReopenPanel for {@link CardLayout}. */
	private static final String REOPEN_PANEL = "ReopenPanel";
	/** Constant to identify ClosedPanel for {@link CardLayout}. */
	private static final String CLOSED_PANEL = "ClosedPanel";
	/** Constant to identify CreateBugPanel for {@link CardLayout}. */
	private static final String CREATE_BUG_PANEL = "CreateBugPanel";
	/** Bug List panel - we only need one instance, so it's final. */
	private final BugListPanel pnlBugList = new BugListPanel();
	/** Unconfirmed panel - we only need one instance, so it's final. */
	private final UnconfirmedPanel pnlUnconfirmed = new UnconfirmedPanel();
	/** New panel - we only need one instance, so it's final. */
	private final NewPanel pnlNew = new NewPanel();
	/** Assigned panel - we only need one instance, so it's final. */
	private final AssignedPanel pnlAssigned = new AssignedPanel();
	/** Resolved panel - we only need one instance, so it's final. */
	private final ResolvedPanel pnlResolved = new ResolvedPanel();
	/** Reopen panel - we only need one instance, so it's final. */
	private final ReopenPanel pnlReopen = new ReopenPanel();
	/** Closed panel - we only need one instance, so it's final. */
	private final ClosedPanel pnlClosed = new ClosedPanel();
	/** Add Bug panel - we only need one instance, so it's final. */
	private final CreateBugPanel pnlCreateBug = new CreateBugPanel();
	/** Reference to {@link CardLayout} for panel.  Stacks all of the panels. */
	private CardLayout cardLayout;
	
	
	/**
	 * Constructs a {@link BugTrackerGUI} object that will contain a {@link JMenuBar} and a
	 * {@link JPanel} that will hold different possible views of the data in
	 * the {@link BugTrackerModel}.
	 */
	public BugTrackerGUI() {
		super();
		
		//Set up general GUI info
		setSize(500, 700);
		setLocation(50, 50);
		setTitle(APP_TITLE);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setUpMenuBar();
		
		//Create JPanel that will hold rest of GUI information.
		//The JPanel utilizes a CardLayout, which stack several different
		//JPanels.  User actions lead to switching which "Card" is visible.
		panel = new JPanel();
		cardLayout = new CardLayout();
		panel.setLayout(cardLayout);
		panel.add(pnlBugList, BUG_LIST_PANEL);
		panel.add(pnlUnconfirmed, UNCONFIRMED_PANEL);
		panel.add(pnlNew, NEW_PANEL);
		panel.add(pnlAssigned, ASSIGNED_PANEL);
		panel.add(pnlResolved, RESOLVED_PANEL);
		panel.add(pnlReopen, REOPEN_PANEL);
		panel.add(pnlClosed, CLOSED_PANEL);
		panel.add(pnlCreateBug, CREATE_BUG_PANEL);
		cardLayout.show(panel, BUG_LIST_PANEL);
		
		//Add panel to the container
		Container c = getContentPane();
		c.add(panel, BorderLayout.CENTER);
		
		//Set the GUI visible
		setVisible(true);
	}
	
	/**
	 * Makes the GUI Menu bar that contains options for loading a file
	 * containing bugs or for quitting the application.
	 */
	private void setUpMenuBar() {
		//Construct Menu items
		menuBar = new JMenuBar();
		menu = new JMenu(FILE_MENU_TITLE);
		itemNewBugXML = new JMenuItem(NEW_XML_TITLE);
		itemLoadBugXML = new JMenuItem(LOAD_XML_TITLE);
		itemSaveBugXML = new JMenuItem(SAVE_XML_TITLE);
		itemQuit = new JMenuItem(QUIT_TITLE);
		itemNewBugXML.addActionListener(this);
		itemLoadBugXML.addActionListener(this);
		itemSaveBugXML.addActionListener(this);
		itemQuit.addActionListener(this);
		
		//Start with save button disabled
		itemSaveBugXML.setEnabled(false);
		
		//Build Menu and add to GUI
		menu.add(itemNewBugXML);
		menu.add(itemLoadBugXML);
		menu.add(itemSaveBugXML);
		menu.add(itemQuit);
		menuBar.add(menu);
		this.setJMenuBar(menuBar);
	}
	
	/**
	 * Performs an action based on the given {@link ActionEvent}.
	 * @param e user event that triggers an action.
	 */
	public void actionPerformed(ActionEvent e) {
		//Use BugTrackerModel's singleton to create/get the sole instance.
		BugTrackerModel model = BugTrackerModel.getInstance();
		if (e.getSource() == itemNewBugXML) {
			//Create a new bug list
			model.createNewBugList();
			itemSaveBugXML.setEnabled(true);
			pnlBugList.updateTable(null);
			cardLayout.show(panel, BUG_LIST_PANEL);
			validate();
			repaint();			
		} else if (e.getSource() == itemLoadBugXML) {
			//Load an existing bug list
			try {
				model.loadBugsFromFile(getFileName());
				itemSaveBugXML.setEnabled(true);
				pnlBugList.updateTable(null);
				cardLayout.show(panel, BUG_LIST_PANEL);
				validate();
				repaint();
			} catch (IllegalArgumentException exp) {
				JOptionPane.showMessageDialog(this, "Unable to load bug file.");
			} catch (IllegalStateException exp) {
				//Don't do anything - user canceled (or error)
			}
		} else if (e.getSource() == itemSaveBugXML) {
			//Save current bug list
			try {
				model.saveBugsToFile(getFileName());
			} catch (IllegalArgumentException exp) {
				JOptionPane.showMessageDialog(this, "Unable to save bug file.");
			} catch (IllegalStateException exp) {
				//Don't do anything - user canceled (or error)
			}
		} else if (e.getSource() == itemQuit) {
			//Quit the program
			try {
				model.saveBugsToFile(getFileName());
				System.exit(0);  //Ignore FindBugs warning here - this is the only place to quit the program!
			} catch (IllegalArgumentException exp) {
				JOptionPane.showMessageDialog(this, "Unable to save bug file.");
			} catch (IllegalStateException exp) {
				//Don't do anything - user canceled (or error)
			}
		}
	}
	
	/**
	 * Returns a file name generated through interactions with a {@link JFileChooser}
	 * object.
	 * @return the file name selected through {@link JFileChooser}
	 */
	private String getFileName() {
		JFileChooser fc = new JFileChooser("./");  //Open JFileChoose to current working directory
		int returnVal = fc.showOpenDialog(this);
		if (returnVal != JFileChooser.APPROVE_OPTION) {
			//Error or user canceled, either way no file name.
			throw new IllegalStateException();
		}
		File gameFile = fc.getSelectedFile();
		return gameFile.getAbsolutePath();
	}

	/**
	 * Starts the GUI for the BugTracker application.
	 * @param args command line arguments
	 */
	public static void main(String [] args) {
		new BugTrackerGUI();
	}
	
	/**
	 * Inner class that creates the look and behavior for the {@link JPanel} that 
	 * shows the list of bugs.
	 * 
	 * @author Dr. Sarah Heckman (heckman@csc.ncsu.edu)
	 */
	private class BugListPanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Button for creating a new Bug */
		private JButton btnAddNewBug;
		/** Button for deleting the selected bug in the list */
		private JButton btnDeleteBug;
		/** Button for editing the selected bug in the list */
		private JButton btnEditBug;
		/** Text field for a user to enter an owner name to filter bug list */
		private JTextField txtFilterByOwner;
		/** Button for starting filter of list by owner */
		private JButton btnFilterByOwner;
		/** Button that will show all bugs that are currently tracked */
		private JButton btnShowAllBugs;
		/** JTable for displaying the list of bugs */
		private JTable table;
		/** TableModel for Bugs */
		private BugTableModel bugTableModel;
		
		/**
		 * Creates the bug list.
		 */
		public BugListPanel() {
			super(new BorderLayout());
			
			//Set up the JPanel that will hold action buttons		
			btnAddNewBug = new JButton("Add New Bug");
			btnAddNewBug.addActionListener(this);
			btnDeleteBug = new JButton("Delete Selected Bug");
			btnDeleteBug.addActionListener(this);
			btnEditBug = new JButton("Edit Selected Bug");
			btnEditBug.addActionListener(this);
			txtFilterByOwner = new JTextField(10);
			btnFilterByOwner = new JButton("Filter List by Owner");
			btnFilterByOwner.addActionListener(this);
			btnShowAllBugs = new JButton("Show All Bugs");
			btnShowAllBugs.addActionListener(this);
			
			JPanel pnlActions = new JPanel();
			pnlActions.setLayout(new GridLayout(2, 3));
			pnlActions.add(btnAddNewBug);
			pnlActions.add(btnDeleteBug);
			pnlActions.add(btnEditBug);
			pnlActions.add(txtFilterByOwner);
			pnlActions.add(btnFilterByOwner);
			pnlActions.add(btnShowAllBugs);
						
			//Set up table
			bugTableModel = new BugTableModel();
			table = new JTable(bugTableModel);
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setPreferredScrollableViewportSize(new Dimension(500, 500));
			table.setFillsViewportHeight(true);
			
			JScrollPane listScrollPane = new JScrollPane(table);
			
			add(pnlActions, BorderLayout.NORTH);
			add(listScrollPane, BorderLayout.CENTER);
		}

		/**
		 * Performs an action based on the given {@link ActionEvent}.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnAddNewBug) {
				//If the add button is pressed switch to the createBugPanel
				cardLayout.show(panel,  CREATE_BUG_PANEL);
			} else if (e.getSource() == btnDeleteBug) {
				//If the delete button is pressed, delete the bug
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "No bug selected");
				} else {
					try {
						int bugId = Integer.parseInt(bugTableModel.getValueAt(row, 0).toString());
						BugTrackerModel.getInstance().deleteBugById(bugId);
					} catch (NumberFormatException nfe ) {
						JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid bug id");
					}
				}
				updateTable(null);
			} else if (e.getSource() == btnEditBug) {
				//If the edit button is pressed, switch panel based on state
				int row = table.getSelectedRow();
				if (row == -1) {
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "No bug selected");
				} else {
					try {
						int bugId = Integer.parseInt(bugTableModel.getValueAt(row, 0).toString());
						String stateName = BugTrackerModel.getInstance().getBugById(bugId).getState().getStateName();
						if (stateName.equals(TrackedBug.UNCONFIRMED_NAME)) {
							cardLayout.show(panel, UNCONFIRMED_PANEL);
							pnlUnconfirmed.setBugInfo(bugId);
						} 
						if (stateName.equals(TrackedBug.NEW_NAME)) {
							cardLayout.show(panel, NEW_PANEL);
							pnlNew.setBugInfo(bugId);
						} 
						if (stateName.equals(TrackedBug.ASSIGNED_NAME)) {
							cardLayout.show(panel, ASSIGNED_PANEL);
							pnlAssigned.setBugInfo(bugId);
						} 
						if (stateName.equals(TrackedBug.RESOLVED_NAME)) {
							cardLayout.show(panel, RESOLVED_PANEL);
							pnlResolved.setBugInfo(bugId);
						} 
						if (stateName.equals(TrackedBug.REOPEN_NAME)) {
							cardLayout.show(panel, REOPEN_PANEL);
							pnlReopen.setBugInfo(bugId);
						} 
						if (stateName.equals(TrackedBug.CLOSED_NAME)) {
							cardLayout.show(panel, CLOSED_PANEL);
							pnlClosed.setBugInfo(bugId);
						} 
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid bug id");
					} catch (NullPointerException npe) {
						JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid bug id");
					}
				}
			} else if (e.getSource() == btnFilterByOwner) {
				String owner = txtFilterByOwner.getText();
				txtFilterByOwner.setText("");
				updateTable(owner);
			} else if (e.getSource() == btnShowAllBugs) {
				updateTable(null);
			}
			BugTrackerGUI.this.repaint();
			BugTrackerGUI.this.validate();
		}
		
		public void updateTable(String owner) {
			if (owner == null) {
				bugTableModel.updateBugData();
			} else {
				bugTableModel.updateBugDataWithOwner(owner);
			}
		}
		
		/**
		 * {@link BugTableModel} is the object underlying the {@link JTable} object that displays
		 * the list of {@link TrackedBug}s to the user.
		 * @author Dr. Sarah Heckman (heckman@csc.ncsu.edu)
		 */
		private class BugTableModel extends AbstractTableModel {
			
			/** ID number used for object serialization. */
			private static final long serialVersionUID = 1L;
			/** Column names for the table */
			private String [] columnNames = {"Bug ID", "Bug State", "Bug Summary"};
			/** Data stored in the table */
			private Object [][] data;
			
			/**
			 * Constructs the {@link BugTableModel} by requesting the latest information
			 * from the {@link BugTrackedModel}.
			 */
			public BugTableModel() {
				updateBugData();
			}

			/**
			 * Returns the number of columns in the table.
			 * @return the number of columns in the table.
			 */
			public int getColumnCount() {
				return columnNames.length;
			}

			/**
			 * Returns the number of rows in the table.
			 * @return the number of rows in the table.
			 */
			public int getRowCount() {
				if (data == null) 
					return 0;
				return data.length;
			}
			
			/**
			 * Returns the column name at the given index.
			 * @return the column name at the given column.
			 */
			public String getColumnName(int col) {
				return columnNames[col];
			}

			/**
			 * Returns the data at the given {row, col} index.
			 * @return the data at the given location.
			 */
			public Object getValueAt(int row, int col) {
				if (data == null)
					return null;
				return data[row][col];
			}
			
			/**
			 * Sets the given value to the given {row, col} location.
			 * @param value Object to modify in the data.
			 * @param row location to modify the data.
			 * @param column location to modify the data.
			 */
			public void setValueAt(Object value, int row, int col) {
				data[row][col] = value;
				fireTableCellUpdated(row, col);
			}
			
			/**
			 * Updates the given model with {@link TrackedBug} information from the {@link BugTrackerModel}.
			 */
			private void updateBugData() {
				BugTrackerModel m = BugTrackerModel.getInstance();
				data = m.getBugListAsArray();
			}
			
			/**
			 * Updates the given model with {@link TrackedBug} information for the 
			 * given owner from the {@link BugTrackerModel}.
			 * @param owner developer id to search for.
			 */
			private void updateBugDataWithOwner(String owner) {
				try {
					BugTrackerModel m = BugTrackerModel.getInstance();
					data = m.getBugListByOwnerAsArray(owner);
				} catch (IllegalArgumentException e) {
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid owner");
				}
			}
		}
	}
	
	/**
	 * Inner class that creates the look and behavior for the {@link JPanel} that 
	 * interacts with an unconfirmed bug.
	 * 
	 * @author Dr. Sarah Heckman (heckman@csc.ncsu.edu)
	 */
	private class UnconfirmedPanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** {@link BugInfoPanel} that presents the {@link TrackedBug}'s information to the user */
		private BugInfoPanel pnlBugInfo;
		/** Note label for the state update */
		private JLabel lblNote;
		/** Note for the state update */
		private JTextArea txtNote;
		/** Vote action */
		private JButton btnVote;
		/** Confirm action */
		private JButton btnConfirm;
		/** Cancel action */
		private JButton btnCancel;
		/** Current {@link TrackedBug}'s id */
		private int bugId;
		
		/**
		 * Constructs the JPanel for editing a {@link TrackedBug} in the UnconfirmedState.
		 */
		public UnconfirmedPanel() {
			pnlBugInfo = new BugInfoPanel();
			lblNote = new JLabel("Note");
			txtNote = new JTextArea(30, 5);
			btnVote = new JButton("Vote");
			btnConfirm = new JButton("Confirm");
			btnCancel = new JButton("Cancel");
			
			btnVote.addActionListener(this);
			btnConfirm.addActionListener(this);
			btnCancel.addActionListener(this);
			
			setLayout(new GridLayout(2, 1));
			add(pnlBugInfo);
			JPanel pnlUnconfInfo = new JPanel();
			pnlUnconfInfo.setLayout(new GridLayout(3, 1));
			pnlUnconfInfo.add(lblNote);
			pnlUnconfInfo.add(txtNote);
			JPanel pnlBtnRow = new JPanel();
			pnlBtnRow.setLayout(new GridLayout(1, 3));
			pnlBtnRow.add(btnVote);
			pnlBtnRow.add(btnConfirm);
			pnlBtnRow.add(btnCancel);
			pnlUnconfInfo.add(pnlBtnRow);
			add(pnlUnconfInfo);
		}
		
		/**
		 * Set the {@link BugInfoPanel} with the given bug data.
		 * @param bugId id of the bug
		 */
		public void setBugInfo(int bugId) {
			this.bugId = bugId;
			pnlBugInfo.setBugInfo(this.bugId);
		}

		/**
		 * Performs an action based on the given {@link ActionEvent}.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			//Take care of note
			String note = txtNote.getText();
			if (note.equals("")) {
				note = null;
			}
			if (e.getSource() == btnVote) {
				//Try a command.  If command fails, go back to bug list.
				try {
					Command c = new Command(Command.CommandValue.VOTE, null, null, note);
					BugTrackerModel.getInstance().executeCommand(bugId, c);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid command");
				} catch (UnsupportedOperationException uoe) {
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid state transition");
				}
			} else if (e.getSource() == btnConfirm) {
				//Try a command.  If command fails, go back to bug list.
				try {
					Command c = new Command(Command.CommandValue.CONFIRM, null, null, note);
					BugTrackerModel.getInstance().executeCommand(bugId, c);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid command");
				} catch (UnsupportedOperationException uoe) {
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid state transition");
				}
			} 
			//Add buttons lead to back bug list
			cardLayout.show(panel, BUG_LIST_PANEL);
			pnlBugList.updateTable(null);
			BugTrackerGUI.this.repaint();
			BugTrackerGUI.this.validate();
			//Reset note
			txtNote.setText("");
		}
		
	}
	
	/**
	 * Inner class that creates the look and behavior for the {@link JPanel} that 
	 * interacts with an new bug.
	 * 
	 * @author Dr. Sarah Heckman (heckman@csc.ncsu.edu)
	 */
	private class NewPanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** {@link BugInfoPanel} that presents the {@TrackedBug}'s information to the user */
		private BugInfoPanel pnlBugInfo;
		/** Note label for the state update */
		private JLabel lblNote;
		/** Note for the state update */
		private JTextArea txtNote;
		/** Label for developer id field */
		private JLabel lblDeveloperId;
		/** Text field for developer id */
		private JTextField txtDeveloperId;
		/** Assign action */
		private JButton btnAssign;
		/** Cancel action */
		private JButton btnCancel;
		/** Current {@link TrackedBug}'s id */
		private int bugId;
		
		/**
		 * Constructs the JPanel for editing a {@link TrackedBug} in the NewState.
		 */
		public NewPanel() {
			pnlBugInfo = new BugInfoPanel();
			lblNote = new JLabel("Note");
			txtNote = new JTextArea(30, 5);
			lblDeveloperId = new JLabel("Developer Id");
			txtDeveloperId = new JTextField(15);
			btnAssign = new JButton("Assign");
			btnCancel = new JButton("Cancel");
			
			btnAssign.addActionListener(this);
			btnCancel.addActionListener(this);
			
			setLayout(new GridLayout(2, 1));
			add(pnlBugInfo);
			JPanel pnlNewInfo = new JPanel();
			pnlNewInfo.setLayout(new GridLayout(4, 1));
			JPanel pnlDev = new JPanel();
			pnlDev.setLayout(new GridLayout(1, 2));
			pnlDev.add(lblDeveloperId);
			pnlDev.add(txtDeveloperId);
			pnlNewInfo.add(pnlDev);
			pnlNewInfo.add(lblNote);
			pnlNewInfo.add(txtNote);
			JPanel pnlBtnRow = new JPanel();
			pnlBtnRow.setLayout(new GridLayout(1, 3));
			pnlBtnRow.add(btnAssign);
			pnlBtnRow.add(btnCancel);
			pnlNewInfo.add(pnlBtnRow);
			add(pnlNewInfo);
		}
		
		/**
		 * Set the {@link BugInfoPanel} with the given bug data.
		 * @param bugId id of the bug
		 */
		public void setBugInfo(int bugId) {
			this.bugId = bugId;
			pnlBugInfo.setBugInfo(this.bugId);
		}

		/**
		 * Performs an action based on the given {@link ActionEvent}.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			boolean reset = true;
			if (e.getSource() == btnAssign) {
				//Take care of note.
				String note = txtNote.getText();
				if (note.equals("")) {
					note = null;
				}
				String developerId = txtDeveloperId.getText();
				if (developerId == null || developerId.equals("")) {
					//If developer id is invalid, show an error message
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid developer id");
					reset = false;
				} else {
					//Otherwise, try a Command.  If command fails, go back to bug list
					try {
						Command c = new Command(Command.CommandValue.POSSESSION, developerId, null, note);
						BugTrackerModel.getInstance().executeCommand(bugId, c);
					} catch (IllegalArgumentException iae) {
						JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid command");
					} catch (UnsupportedOperationException uoe) {
						JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid state transition");
					}
					
				}
			}
			if (reset) {
				//All buttons lead to back bug list if valid info for owner
				cardLayout.show(panel, BUG_LIST_PANEL);
				pnlBugList.updateTable(null);
				BugTrackerGUI.this.repaint();
				BugTrackerGUI.this.validate();
				//Reset fields
				txtDeveloperId.setText("");
				txtNote.setText("");
			}
			//Otherwise, do not refresh the GUI panel and wait for correct developer input.
		}
		
	}
	
	/**
	 * Inner class that creates the look and behavior for the {@link JPanel} that 
	 * interacts with an assigned bug.
	 * 
	 * @author Dr. Sarah Heckman (heckman@csc.ncsu.edu)
	 */
	private class AssignedPanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** {@link BugInfoPanel} that presents the {@TrackedBug}'s information to the user */
		private BugInfoPanel pnlBugInfo;
		/** Note label for the state update */
		private JLabel lblNote;
		/** Note for the state update */
		private JTextArea txtNote;
		/** Label for selecting a resolution */
		private JLabel lblResolved;
		/** ComboBox for resolutions */
		private JComboBox<String> comboResolved;
		/** Resolve action */
		private JButton btnResolve;
		/** Cancel action */
		private JButton btnCancel;
		/** Current bug's id */
		private int bugId;
		/** Possible resolutions to list in the combo box */
		private String [] resolutions = {"Fixed", "Duplicate", "WontFix", "WorksForMe"}; 

		/**
		 * Constructs a JPanel for editing a {@link TrackedBug} in the AssignedState.
		 */
		public AssignedPanel() {
			pnlBugInfo = new BugInfoPanel();
			lblNote = new JLabel("Note");
			txtNote = new JTextArea(30, 5);
			lblResolved = new JLabel("Resolution");
			comboResolved = new JComboBox<String>(resolutions);
			comboResolved.setSelectedIndex(0);
			
			btnResolve = new JButton("Resolve");
			btnCancel = new JButton("Cancel");
			
			btnResolve.addActionListener(this);
			btnCancel.addActionListener(this);
			
			setLayout(new GridLayout(2, 1));
			add(pnlBugInfo);
			JPanel pnlAssignedInfo = new JPanel();
			pnlAssignedInfo.setLayout(new GridLayout(4, 1));
			JPanel pnlResolvedLocal = new JPanel();
			pnlResolvedLocal.setLayout(new GridLayout(1, 2));
			pnlResolvedLocal.add(lblResolved);
			pnlResolvedLocal.add(comboResolved);
			pnlAssignedInfo.add(pnlResolvedLocal);
			pnlAssignedInfo.add(lblNote);
			pnlAssignedInfo.add(txtNote);
			JPanel pnlBtnRow = new JPanel();
			pnlBtnRow.setLayout(new GridLayout(1, 3));
			pnlBtnRow.add(btnResolve);
			pnlBtnRow.add(btnCancel);
			pnlAssignedInfo.add(pnlBtnRow);
			add(pnlAssignedInfo);
		}
		
		/**
		 * Set the {@link BugInfoPanel} with the given bug data.
		 * @param bugId id of the bug
		 */
		public void setBugInfo(int bugId) {
			this.bugId = bugId;
			pnlBugInfo.setBugInfo(this.bugId);
		}

		/**
		 * Performs an action based on the given {@link ActionEvent}.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			boolean reset = true;
			if (e.getSource() == btnResolve) {
				//Handle note
				String note = txtNote.getText();
				if (note.equals("")) {
					note = null;
				}
				//Get resolution
				int idx = comboResolved.getSelectedIndex();
				if (idx < 0 || idx >= resolutions.length) {
					//If problem, show error and remain on page.
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid resolution");
					reset = false;
				} else {
					Resolution r = null;
					switch (idx) {
					case 0: 
						r = Resolution.FIXED;
						break;
					case 1:
						r = Resolution.DUPLICATE;
						break;
					case 2:
						r = Resolution.WONTFIX;
						break;
					case 3:
						r = Resolution.WORKSFORME;
						break;
					default:
						r = null;
					}
					//Try a command.  If problem, go back to bug list.
					try {
						Command c = new Command(Command.CommandValue.RESOLVED, null, r, note);
						BugTrackerModel.getInstance().executeCommand(bugId, c);
					} catch (IllegalArgumentException iae) {
						JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid command");
					} catch (UnsupportedOperationException uoe) {
						JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid state transition");
					}
				}
			} 
			if (reset) {
				//All buttons lead to back bug list
				cardLayout.show(panel, BUG_LIST_PANEL);
				pnlBugList.updateTable(null);
				BugTrackerGUI.this.repaint();
				BugTrackerGUI.this.validate();
				//Reset fields
				comboResolved.setSelectedIndex(0);
				txtNote.setText("");
			}
			//Otherwise, stay on panel
		}
		
	}
	
	/**
	 * Inner class that creates the look and behavior for the {@link JPanel} that 
	 * interacts with a resolved bug.
	 * 
	 * @author Dr. Sarah Heckman (heckman@csc.ncsu.edu)
	 */
	private class ResolvedPanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** {@link BugInfoPanel} that presents the {@TrackedBug}'s information to the user */
		private BugInfoPanel pnlBugInfo;
		/** Note label for the state update */
		private JLabel lblNote;
		/** Note for the state update */
		private JTextArea txtNote;
		/** Verify action */
		private JButton btnVerified;
		/** Reopen action */
		private JButton btnReopen;
		/** Cancel action */
		private JButton btnCancel;
		/** Current bug's id */
		private int bugId;

		/**
		 * Constructs a JFrame for editing a {@link TrackedBug} in the ResolvedState.
		 */
		public ResolvedPanel() {
			pnlBugInfo = new BugInfoPanel();
			lblNote = new JLabel("Note");
			txtNote = new JTextArea(30, 5);
			
			btnVerified = new JButton("Verify");
			btnReopen = new JButton("Reopen");
			btnCancel = new JButton("Cancel");
			
			btnVerified.addActionListener(this);
			btnReopen.addActionListener(this);
			btnCancel.addActionListener(this);
			
			setLayout(new GridLayout(2, 1));
			add(pnlBugInfo);
			JPanel pnlResolvedInfo = new JPanel();
			pnlResolvedInfo.setLayout(new GridLayout(4, 1));
			pnlResolvedInfo.add(lblNote);
			pnlResolvedInfo.add(txtNote);
			JPanel pnlBtnRow = new JPanel();
			pnlBtnRow.setLayout(new GridLayout(1, 3));
			pnlBtnRow.add(btnVerified);
			pnlBtnRow.add(btnReopen);
			pnlBtnRow.add(btnCancel);
			pnlResolvedInfo.add(pnlBtnRow);
			add(pnlResolvedInfo);
		}
		
		/**
		 * Set the {@link BugInfoPanel} with the given bug data.
		 * @param bugId id of the bug
		 */
		public void setBugInfo(int bugId) {
			this.bugId = bugId;
			pnlBugInfo.setBugInfo(this.bugId);
		}

		/**
		 * Performs an action based on the given {@link ActionEvent}.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			//Handle note
			String note = txtNote.getText();
			if (note.equals("")) {
				note = null;
			}
			if (e.getSource() == btnVerified) {
				//Try command.  If problem, go to bug list.
				try {
					Command c = new Command(Command.CommandValue.VERIFIED, null, null, note);
					BugTrackerModel.getInstance().executeCommand(bugId, c);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid command");
				} catch (UnsupportedOperationException uoe) {
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid state transition");
				}
			} else if (e.getSource() == btnReopen) {
				//Try command.  If problem, go to bug list.
				try {
					Command c = new Command(Command.CommandValue.REOPEN, null, null, note);
					BugTrackerModel.getInstance().executeCommand(bugId, c);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid command");
				} catch (UnsupportedOperationException uoe) {
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid state transition");
				}
			}
			//All buttons lead to back bug list
			cardLayout.show(panel, BUG_LIST_PANEL);
			pnlBugList.updateTable(null);
			BugTrackerGUI.this.repaint();
			BugTrackerGUI.this.validate();
			//Reset note
			txtNote.setText("");
		}
	}
	
	/**
	 * Inner class that creates the look and behavior for the {@link JPanel} that 
	 * interacts with a reopened bug.
	 * 
	 * @author Dr. Sarah Heckman (heckman@csc.ncsu.edu)
	 */
	private class ReopenPanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** {@link BugInfoPanel} that presents the {@TrackedBug}'s information to the user */
		private BugInfoPanel pnlBugInfo;
		/** Note label for the state update */
		private JLabel lblNote;
		/** Note for the state update */
		private JTextArea txtNote;
		/** Label for developer id field */
		private JLabel lblDeveloperId;
		/** Text field for developer id */
		private JTextField txtDeveloperId;
		/** Label for selecting a resolution */
		private JLabel lblResolved;
		/** ComboBox for resolutions */
		private JComboBox<String> comboResolved;
		/** Assign action */
		private JButton btnAssign;
		/** Resolve action */
		private JButton btnResolve;
		/** Cancel action */
		private JButton btnCancel;
		/** Current bug's id */
		private int bugId;
		/** List of resolution for combo box */
		private String [] resolutions = {"Fixed", "Duplicate", "WontFix", "WorksForMe"}; 

		/**
		 * Constructs a JPanel for editing a {@link TrackedBug} in the ReopenState.
		 */
		public ReopenPanel() {
			pnlBugInfo = new BugInfoPanel();
			lblNote = new JLabel("Note");
			txtNote = new JTextArea(30, 5);
			lblDeveloperId = new JLabel("Developer Id");
			txtDeveloperId = new JTextField(15);
			lblResolved = new JLabel("Resolution");
			comboResolved = new JComboBox<String>(resolutions);
			comboResolved.setSelectedIndex(0);
			
			btnAssign = new JButton("Reassign");
			btnResolve = new JButton("Resolve");
			btnCancel = new JButton("Cancel");
			
			btnAssign.addActionListener(this);
			btnResolve.addActionListener(this);
			btnCancel.addActionListener(this);
			
			setLayout(new GridLayout(2, 1));
			add(pnlBugInfo);
			JPanel pnlReopenInfo = new JPanel();
			pnlReopenInfo.setLayout(new GridLayout(5, 1));
			JPanel pnlDev = new JPanel();
			pnlDev.setLayout(new GridLayout(1, 2));
			pnlDev.add(lblDeveloperId);
			pnlDev.add(txtDeveloperId);
			pnlReopenInfo.add(pnlDev);
			JPanel pnlResolvedLocal = new JPanel(); 
			pnlResolvedLocal.setLayout(new GridLayout(1, 2));
			pnlResolvedLocal.add(lblResolved);
			pnlResolvedLocal.add(comboResolved);
			pnlReopenInfo.add(pnlResolvedLocal);
			pnlReopenInfo.add(lblNote);
			pnlReopenInfo.add(txtNote);
			JPanel pnlBtnRow = new JPanel();
			pnlBtnRow.setLayout(new GridLayout(1, 3));
			pnlBtnRow.add(btnAssign);
			pnlBtnRow.add(btnResolve);
			pnlBtnRow.add(btnCancel);
			pnlReopenInfo.add(pnlBtnRow);
			add(pnlReopenInfo);
		}
		
		/**
		 * Set the {@link BugInfoPanel} with the given bug data.
		 * @param bugId id of the bug
		 */
		public void setBugInfo(int bugId) {
			this.bugId = bugId;
			pnlBugInfo.setBugInfo(this.bugId);
		}

		/**
		 * Performs an action based on the given {@link ActionEvent}.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			boolean reset = true;
			//Handle note
			String note = txtNote.getText();
			if (note.equals("")) {
				note = null;
			}
			if (e.getSource() == btnAssign) {
				//Get the developer id
				String developerId = txtDeveloperId.getText();
				if (developerId == null || developerId.equals("")) {
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid developer id");
					reset = false;
				} else {
					//Try command.  If problem, return to bug list.
					try {
						Command c = new Command(Command.CommandValue.POSSESSION, developerId, null, note);
						BugTrackerModel.getInstance().executeCommand(bugId, c);
					} catch (IllegalArgumentException iae) {
						JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid command");
					} catch (UnsupportedOperationException uoe) {
						JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid state transition");
					}
				}
			} else if (e.getSource() == btnResolve) {
				//Get resolution
				int idx = comboResolved.getSelectedIndex();
				if (idx < 0 || idx >= resolutions.length) {
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid resolution");
					reset = false;
				} else {
					Resolution r = null;
					switch (idx) {
					case 0: 
						r = Resolution.FIXED;
						break;
					case 1:
						r = Resolution.DUPLICATE;
						break;
					case 2:
						r = Resolution.WONTFIX;
						break;
					case 3:
						r = Resolution.WORKSFORME;
						break;
					default:
						r = null;
					}
					//Try command.  If problem, return to bug list
					try {
						Command c = new Command(Command.CommandValue.RESOLVED, null, r, note);
						BugTrackerModel.getInstance().executeCommand(bugId, c);
					}  catch (IllegalArgumentException iae) {
						JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid command");
					} catch (UnsupportedOperationException uoe) {
						JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid state transition");
					}
				}
			}
			if (reset) {
				//All buttons lead to back bug list
				cardLayout.show(panel, BUG_LIST_PANEL);
				pnlBugList.updateTable(null);
				BugTrackerGUI.this.repaint();
				BugTrackerGUI.this.validate();
				//Reset fields
				txtDeveloperId.setText("");
				comboResolved.setSelectedIndex(0);
				txtNote.setText("");
			} 
			//Otherwise, stay on panel so user can fix
		}
		
	}
	
	/**
	 * Inner class that creates the look and behavior for the {@link JPanel} that 
	 * interacts with a closed bug.
	 * 
	 * @author Dr. Sarah Heckman (heckman@csc.ncsu.edu)
	 */
	private class ClosedPanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** {@link BugInfoPanel} that presents the {@TrackedBug}'s information to the user */
		private BugInfoPanel pnlBugInfo;
		/** Note label for the state update */
		private JLabel lblNote;
		/** Note for the state update */
		private JTextArea txtNote;
		/** Reopen action */
		private JButton btnReopen;
		/** Cancel action */
		private JButton btnCancel;
		/** Current bug's id */
		private int bugId;

		/**
		 * Constructs a JPanel for editing a {@link TrackedBug} in the ClosedState.
		 */
		public ClosedPanel() {
			pnlBugInfo = new BugInfoPanel();
			lblNote = new JLabel("Note");
			txtNote = new JTextArea(30, 5);
			
			btnReopen = new JButton("Reopen");
			btnCancel = new JButton("Cancel");
			
			btnReopen.addActionListener(this);
			btnCancel.addActionListener(this);
			
			setLayout(new GridLayout(2, 1));
			add(pnlBugInfo);
			JPanel pnlClosedInfo = new JPanel();
			pnlClosedInfo.setLayout(new GridLayout(4, 1));
			pnlClosedInfo.add(lblNote);
			pnlClosedInfo.add(txtNote);
			JPanel pnlBtnRow = new JPanel();
			pnlBtnRow.setLayout(new GridLayout(1, 3));
			pnlBtnRow.add(btnReopen);
			pnlBtnRow.add(btnCancel);
			pnlClosedInfo.add(pnlBtnRow);
			add(pnlClosedInfo);
		}
		
		/**
		 * Set the {@link BugInfoPanel} with the given bug data.
		 * @param bugId id of the bug
		 */
		public void setBugInfo(int bugId) {
			this.bugId = bugId;
			pnlBugInfo.setBugInfo(this.bugId);
		}

		/**
		 * Performs an action based on the given {@link ActionEvent}.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnReopen) {
				String note = txtNote.getText();
				if (note.equals("")) {
					note = null;
				}
				//Try command.  If problem, go back to bug list
				try {
					Command c = new Command(Command.CommandValue.REOPEN, null, null, note);
					BugTrackerModel.getInstance().executeCommand(bugId, c);
				} catch (IllegalArgumentException iae) {
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid command");
				} catch (UnsupportedOperationException uoe) {
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid state transition");
				}
			}
			//All buttons lead to back bug list
			cardLayout.show(panel, BUG_LIST_PANEL);
			pnlBugList.updateTable(null);
			BugTrackerGUI.this.repaint();
			BugTrackerGUI.this.validate();
		}
	}
	
	/**
	 * Inner class that creates the look and behavior for the {@link JPanel} that 
	 * shows information about the bug.
	 * 
	 * @author Dr. Sarah Heckman (heckman@csc.ncsu.edu)
	 */
	private class BugInfoPanel extends JPanel {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Label for title */
		private JLabel lblTitle;
		/** Label for id */
		private JLabel lblId;
		/** Field for id */
		private JTextField txtId;
		/** Label for state */
		private JLabel lblState;
		/** Field for state */
		private JTextField txtState;
		/** Label for summary */
		private JLabel lblSummary;
		/** Field for summary */
		private JTextArea txtSummary;
		/** Label for reporter */
		private JLabel lblReporter;
		/** Field for reporter */
		private JTextField txtReporter;
		/** Label for owner */
		private JLabel lblOwner;
		/** Field for owner */
		private JTextField txtOwner;
		/** Label for votes */
		private JLabel lblVotes;
		/** Field for votes */
		private JTextField txtVotes;
		/** Label for confirmed */
		private JLabel lblConfirmed;
		/** Field for confirmed */
		private JTextField txtConfirmed;
		/** Label for resolution */
		private JLabel lblResolution;
		/** Field for resolution */
		private JTextField txtResolution;
		/** Label for notes */
		private JLabel lblNotes;
		/** Field for notes */
		private JTextArea txtNotes;
		
		/** 
		 * Construct the panel for the bug information.
		 */
		public BugInfoPanel() {
			super(new GridLayout(9, 1));
			
			lblTitle = new JLabel("Bug Info");
			lblId = new JLabel("Bug Id");
			lblState = new JLabel("Bug State");
			lblSummary = new JLabel("Bug Summary");
			lblReporter = new JLabel("Reporter");
			lblOwner = new JLabel("Owner");
			lblVotes = new JLabel("Votes");
			lblConfirmed = new JLabel("Confirmed");
			lblResolution = new JLabel("Resolution");
			lblNotes = new JLabel("Notes");
			
			txtId = new JTextField(15);
			txtState = new JTextField(15);
			txtSummary = new JTextArea(15, 3);
			txtReporter = new JTextField(15);
			txtOwner = new JTextField(15);
			txtVotes = new JTextField(15);
			txtConfirmed = new JTextField(15);
			txtResolution = new JTextField(15);
			txtNotes = new JTextArea(30, 5);
			
			txtId.setEditable(false);
			txtState.setEditable(false);
			txtSummary.setEditable(false);
			txtReporter.setEditable(false);
			txtOwner.setEditable(false);
			txtVotes.setEditable(false);
			txtConfirmed.setEditable(false);
			txtResolution.setEditable(false);
			txtNotes.setEditable(false);
			
			JScrollPane summaryScrollPane = new JScrollPane(txtSummary);
			JScrollPane notesScrollPane = new JScrollPane(txtNotes);
			
			//Row 1 - Title
			add(lblTitle);
			//Row 2 - ID and State
			JPanel row2 = new JPanel();
			row2.setLayout(new GridLayout(1, 4));
			row2.add(lblId);
			row2.add(txtId);
			row2.add(lblState);
			row2.add(txtState);
			add(row2);
			//Row 3 - Summary title
			add(lblSummary);
			//Row 4 - Summary text area
			add(summaryScrollPane);
			//Row 5 - Reporter & Owner
			JPanel row5 = new JPanel();
			row5.setLayout(new GridLayout(1, 4));
			row5.add(lblReporter);
			row5.add(txtReporter);
			row5.add(lblOwner);
			row5.add(txtOwner);
			add(row5);
			//Row 6 - Votes & Confirmed
			JPanel row6 = new JPanel();
			row6.setLayout(new GridLayout(1, 4));
			row6.add(lblVotes);
			row6.add(txtVotes);
			row6.add(lblConfirmed);
			row6.add(txtConfirmed);
			add(row6);
			//Row 7 - Resolution
			JPanel row7 = new JPanel();
			row7.setLayout(new GridLayout(1, 2));
			row7.add(lblResolution);
			row7.add(txtResolution);
			add(row7);
			//Row 8 - Notes title
			add(lblNotes);
			//Row 9 - Notes text area
			add(notesScrollPane);
		}
		
		/**
		 * Adds information about the bug to the display.  
		 * @param bugId the id for the bug to display information about.
		 */
		public void setBugInfo(int bugId) {
			//Get the bug from the model
			TrackedBug b = BugTrackerModel.getInstance().getBugById(bugId);
			if (b == null) {
				//If the bug doesn't exist for the given id, show an error message
				JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid bug id");
				cardLayout.show(panel, BUG_LIST_PANEL);
				BugTrackerGUI.this.repaint();
				BugTrackerGUI.this.validate();
			} else {
				//Otherwise, set all of the fields with the information
				txtId.setText("" + b.getBugId());
				txtState.setText(b.getState().getStateName());
				txtSummary.setText(b.getSummary());
				txtReporter.setText(b.getReporter());
				txtOwner.setText(b.getOwner());
				txtVotes.setText("" + b.getVotes());
				txtConfirmed.setText("" + b.isConfirmed());
				String resolutionString = b.getResolutionString();
				if (resolutionString == null) {
					txtResolution.setText("");
				} else {
					txtResolution.setText("" + b.getResolutionString());
				}
				txtNotes.setText(b.getNotesString());
			}
		}
	}
	
	/**
	 * Inner class that creates the look and behavior for the {@link JPanel} that 
	 * allows for creation of a new bug.
	 * 
	 * @author Dr. Sarah Heckman (heckman@csc.ncsu.edu)
	 */
	private class CreateBugPanel extends JPanel implements ActionListener {
		/** ID number used for object serialization. */
		private static final long serialVersionUID = 1L;
		/** Label for identifying summary text field */
		private JLabel lblSummary;
		/** Text field for entering summary information */
		private JTextArea txtSummary;
		/** Label for identifying reporter text field */
		private JLabel lblReporter;
		/** Text field for entering reporter id */
		private JTextField txtReporter;
		/** Button to add a bug */
		private JButton btnAdd;
		/** Button for canceling add action */
		private JButton btnCancel;
		
		/**
		 * Creates the {@link JPanel} for adding new bugs to the 
		 * tracker.
		 */
		public CreateBugPanel() {
			super(new GridLayout(3, 1));  //Creates 3 row, 1 col grid
			
			//Construct widgets
			lblSummary = new JLabel("Bug Summary");
			txtSummary = new JTextArea(5, 30);
			lblReporter = new JLabel("Bug Reporter");
			txtReporter = new JTextField(30);
			btnAdd = new JButton("Add Bug to List");
			btnCancel = new JButton("Cancel");
			
			//Adds action listeners
			btnAdd.addActionListener(this);
			btnCancel.addActionListener(this);
			
			//Builds summary panel, which is a 2 row, 1 col grid
			JPanel pnlSummary = new JPanel();
			pnlSummary.setLayout(new GridLayout(2, 1));
			pnlSummary.add(lblSummary);
			pnlSummary.add(txtSummary);
			
			//Builds reporter panel, which is a 1 row, 2 col grid
			JPanel pnlReporter = new JPanel();
			pnlReporter.setLayout(new GridLayout(1, 2));
			pnlReporter.add(lblReporter);
			pnlReporter.add(txtReporter);
			
			//Build button panel, which is a 1 row, 2 col grid
			JPanel pnlButtons = new JPanel();
			pnlButtons.setLayout(new GridLayout(1, 2));
			pnlButtons.add(btnAdd);
			pnlButtons.add(btnCancel);
			
			//Adds all panels to main panel
			add(pnlSummary);
			add(pnlReporter);
			add(pnlButtons);
		}

		/**
		 * Performs an action based on the given {@link ActionEvent}.
		 * @param e user event that triggers an action.
		 */
		public void actionPerformed(ActionEvent e) {
			boolean done = true; //Assume done unless error
			if (e.getSource() == btnAdd) {
				//Add bug to the list
				String summary = txtSummary.getText();
				String reporter = txtReporter.getText();
				//Get instance of model and add bug
				try {
					BugTrackerModel.getInstance().addBugToList(summary, reporter);
				} catch (IllegalArgumentException exp) {
					done = false;
					JOptionPane.showMessageDialog(BugTrackerGUI.this, "Invalid bug information.");
				}
			} 
			if (done) {
				//All buttons lead to back bug list
				cardLayout.show(panel, BUG_LIST_PANEL);
				pnlBugList.updateTable(null);
				BugTrackerGUI.this.repaint();
				BugTrackerGUI.this.validate();
				//Reset fields
				txtSummary.setText("");
				txtReporter.setText("");
			}
		}
	}
}
