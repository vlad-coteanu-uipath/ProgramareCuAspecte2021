package abstractFactory.pattern;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JToolBar;

/** 
 * Implements Text editor specific components required by EditorFactory
 */
public class TextEditorFactory extends EditorFactory {
	
	protected TextEditorFactory() {
	}
	
	/**
	 * Creates a new JFrame and sets its default size
	 *
	 * @return a new JFrame object with a default size.
	 */
	public JFrame createFrame() {
		JFrame frame = new JFrame("Basic Text Editor");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.height = screenSize.height - (25 * screenSize.height / 768);
		frame.setSize(screenSize);
		return frame;
	}

	/**
	 * Creates a new JMenuBar with the appropriate command names
	 * and adds a listener to each of them.
	 *
	 * @param listener 
	 *					An ActionListener object for the menu
	 * 
	 * @return			The JMenuBar with the appropriate commands.
	 */
	public JMenuBar createMenuBar(ActionListener listener) {
		JMenuBar menuBar = new JMenuBar();
		JMenu fileMenu = new JMenu("File");
		String[] fileMenuItems = new String[] { "New", "Open", "Save", "Save As" };
		for (int i = 0; i < fileMenuItems.length; i++) {
			JMenuItem menuItem = new JMenuItem(fileMenuItems[i]);
			fileMenu.add(menuItem);
			menuItem.addActionListener(listener); //listens to button press
		}
		menuBar.add(fileMenu);

		//edit menu
		JMenu editMenu = new JMenu("Edit");
		String[] editMenuItems = new String[] { "Copy", "Cut", "Paste", "Select All" };
		for (int i = 0; i < editMenuItems.length; i++) {
			JMenuItem menuItem = new JMenuItem(editMenuItems[i]);
			editMenu.add(menuItem);
			menuItem.addActionListener(listener); //listens to button press
		}
		menuBar.add(editMenu);

		return menuBar;
	}

	/**
	 * Creates a new JToolBar with the appropriate buttons
	 * and adds a listener to each of them.
	 *
	 * @param listener 
	 *					An ActionListener object for the menu
	 * 
	 * @return			The JToolBar with the appropriate buttons.
	 */
	public JToolBar createToolBar(ActionListener listener) {
		JToolBar toolBar = new JToolBar();
		String[] buttons = new String[] { "New", "Open", "Save", "Copy", "Cut", "Paste" };
		for (int i = 0; i < buttons.length; i++) {
			JButton button = new JButton(buttons[i], new ImageIcon(buttons[i] + ".jpg"));
			// button.setPreferredSize(new Dimension(120, 30));
			toolBar.add(button);
			button.addActionListener(listener);
			if (i == 2)
				toolBar.addSeparator(new Dimension(10, toolBar.getHeight()));
		}
		return toolBar;
	}

	/**
	 * Creates and returns a new JDesktopPane object.
	 *
	 * @return a new JDesktopPane object
	 */
	public JDesktopPane createDesktopPane() {
		JDesktopPane pane = new JDesktopPane();
		return pane;
	}

	/**
	 * Creates and returns a new ActionListener object 
	 * attached to the given JDesktopPane.
	 * 
	 * @param pane
	 *				The JDesktopPane to which the ActionListener 
	 *				object will be attached
	 * @return a new ActionListener object attached to the given JDesktopPane
	 */
	public ActionListener createActionListener(JDesktopPane pane) {
		ButtonListener listener = new ButtonListener(pane);
		return listener;
	}

	/**
	 * Creates and returns a new WindowAdapter object 
	 * for the given JDesktopPane.
	 *
	 * @param pane
	 *				The JDesktopPane for the WindowAdapter 
	 *	
	 * @return a new WindowAdapter object for the given JDesktopPane
	 */
	public WindowAdapter createWindowAdapter(JDesktopPane pane) {
		WindowCloser windowAdapter = new WindowCloser(pane);
		return windowAdapter;
	}
}