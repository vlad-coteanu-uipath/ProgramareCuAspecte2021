package abstractFactory.pattern;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;

public class EditorApplication {

	/**
	 * Creates a new EditorApplication object filling it with the appropriate
	 * components and placing them in the frame
	 */
	public EditorApplication(EditorFactory factory) {
		//call Factory Methods to create products
		frame = factory.createFrame();
		pane = factory.createDesktopPane();
		listener = factory.createActionListener(pane);
		menuBar = factory.createMenuBar(listener);
		toolBar = factory.createToolBar(listener);
		windowAdapter = factory.createWindowAdapter(pane);

		//add components to frame
		frame.setJMenuBar(menuBar);
		frame.addWindowListener(windowAdapter);
		frame.getContentPane().add(toolBar, "North");
		frame.getContentPane().add(pane);
	}

	public static void main(String[] args) {
		EditorFactory ef = EditorFactory.createEditorFactoryInstance(HTMLEditorFactory.class);
		EditorApplication editor = new EditorApplication(ef);
		editor.frame.setVisible(true);
	}
	
	private JFrame frame;
	private JMenuBar menuBar;
	private JToolBar toolBar;
	private WindowAdapter windowAdapter;
	private JDesktopPane pane;
	private ActionListener listener;
}