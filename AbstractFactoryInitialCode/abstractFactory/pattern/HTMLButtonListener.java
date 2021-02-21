package abstractFactory.pattern;

import java.awt.event.ActionEvent;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class HTMLButtonListener extends ButtonListener {
	
	public HTMLButtonListener(JDesktopPane pane) {
		super(pane);
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
		TextDocument document;
		if (event.getActionCommand().equals("Go to URL")) { //New button pressed
			
			try {
				String url = textField.getText();
				
				URL page = new URL(url);
				HttpURLConnection.setFollowRedirects(true);
				HttpURLConnection con = (HttpURLConnection) page.openConnection();
				
				document = new TextDocument("untitled" + docCount, newWindowX, newWindowY);
				
				document.textArea.setText("");
				document.textArea.append(page.getHost());
				document.textArea.read(new InputStreamReader(con.getInputStream()), null);
				
				this.addDocument(document);
				docCount++;
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "You need to type in a valid url.");
			}
		} else {
			super.actionPerformed(event);
		}
	}
	
	public void setTextField(JTextField jTextField) {
		this.textField = jTextField;
	}
	
	JTextField textField;
}
