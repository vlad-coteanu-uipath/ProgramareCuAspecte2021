package abstractFactory.pattern;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

/**
 * Listens to the buttons pressed on the tool bar
 */
public class ButtonListener implements ActionListener {

	/**
	 * Creates a ButtonListener object
	 * 
	 * @param pane 
	 *				The JDesktopPane to which this
	 *				ButtonListener is attached.
	 *
	 */
	public ButtonListener(JDesktopPane pane) {
		this.pane = pane; 
		newWindowX = 0;
		newWindowY = 0;
		docCount = 0;
	}

	/**
	 * Performs the appropriate action depending
	 * on the type of event triggered.
	 *
	 * @param event 
	 *				The ActionEvent corresponding to
	 *				the button clicked
	 *
	 */
	public void actionPerformed(ActionEvent event) {
		TextDocument document, current = null;
		if (event.getActionCommand().equals("New")) { //New button pressed
			document = new TextDocument("untitled" + docCount, newWindowX, newWindowY);
			this.addDocument(document);
			docCount++;
		} else if (event.getActionCommand().equals("Open")) { //Open button pressed
			document = new TextDocument("", newWindowX, newWindowY); //dummy document
			if (document.open()){
				this.addDocument(document);
			}
		} else if (event.getActionCommand().equals("Save")) { //Save button pressed
			current = this.getSelectedDocument();
			if (current != null){
				if (current.saved()){
					current.save();
				}else{
					current.saveAs();
				}
			}
		} else if (event.getActionCommand().equals("Save As")) {//Save As pressed
			current = this.getSelectedDocument();
			if (current != null){
				current.saveAs();
			}
		} else if (event.getActionCommand().equals("Copy")) {//Copy pressed
			current = this.getSelectedDocument();
			if (current != null){
				current.copy();
			}
		} else if (event.getActionCommand().equals("Paste")) {//Paste pressed
			current = this.getSelectedDocument();
			if (current != null){
				current.paste();
			}
		} else if (event.getActionCommand().equals("Cut")) {// cut pressed
			current = this.getSelectedDocument();
			if (current != null){
				current.cut();
			}
		} else if (event.getActionCommand().equals("Select All")) {// select allpressed
			current = this.getSelectedDocument();
			if (current != null){
				current.selectAll();
			}
		}
	}

	/**
	 * Adds a document to the desktop pane
	 * 
	 * @param document
	 *				The Document to be added to the
	 *				desktop pane
	 *
	 */
	protected void addDocument(Document document) {
		pane.add(document);
		pane.getDesktopManager().activateFrame(document);
		try {
			document.setSelected(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// the following line takes the task bar into acount
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		screenSize.height = screenSize.height - (25 * screenSize.height / 768); 
		// compute coords for posn of next new window
		newWindowX = (newWindowX + 20) % (int) (screenSize.width - 100);
		newWindowY = (newWindowY + 20) % (int) (screenSize.height - 100);
		document.grabFocus();
	}

	/**
	 * Accesses the currently selected document
	 * 
	 * @return
	 *			The TextDocument object of the
				currently selected document.
	 */
	protected TextDocument getSelectedDocument() {
		JInternalFrame[] frames = pane.getAllFrames();
		for (int i = 0; i < frames.length; i++) {
			if (frames[i].isSelected())
				return (TextDocument) frames[i];
		}
		return null;
	}

	protected JDesktopPane pane;
	protected int newWindowX, newWindowY, docCount;
}