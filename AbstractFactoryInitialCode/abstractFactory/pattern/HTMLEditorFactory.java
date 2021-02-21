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
import javax.swing.JTextField;
import javax.swing.JToolBar;

/** 
 * Implements Text editor specific components required by EditorFactory
 */
public class HTMLEditorFactory extends TextEditorFactory {
	
	protected HTMLEditorFactory() {
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
		String[] buttons = new String[] { "New", "Open", "Save", "Copy", "Cut", "Paste"};
		for (int i = 0; i < buttons.length; i++) {
			JButton button = new JButton(buttons[i], new ImageIcon(buttons[i] + ".jpg"));
			// button.setPreferredSize(new Dimension(120, 30));
			toolBar.add(button);
			button.addActionListener(listener);
			if (i == 2)
				toolBar.addSeparator(new Dimension(10, toolBar.getHeight()));
		}
		
		toolBar.addSeparator(new Dimension(10, toolBar.getHeight()));
		JButton button = new JButton("Go to URL", new ImageIcon("New.jpg"));
		toolBar.add(button);
		button.addActionListener(listener);
		
		JTextField jTextField = new JTextField("Type URL");
		jTextField.setName("URLFilter");
		if(listener instanceof HTMLButtonListener) {
			((HTMLButtonListener) listener).setTextField(jTextField);
		}
		toolBar.add(jTextField);
		return toolBar;
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
		ButtonListener listener = new HTMLButtonListener(pane);
		return listener;
	}

}