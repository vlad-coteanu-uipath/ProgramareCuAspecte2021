package observer.pattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.JPanel;
import javax.swing.JSlider;

import observer.CourseRecord;
import observer.LayoutConstants;

/**
 * This class represents a bar chart view of a vector of data. Uses the Observer
 * pattern.
 */
@SuppressWarnings("serial")
public class PieChartObserver extends JPanel implements Observer {
	/**
	 * Creates a PieChartObserver object
	 * 
	 * @param data
	 *            a CourseData object to observe
	 */
	public PieChartObserver(CourseData data) {
		data.attach(this, true);
		this.courseData = data.getUpdate();
		this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
				+ (LayoutConstants.barSpacing + LayoutConstants.barWidth)
				* this.courseData.size(), LayoutConstants.graphHeight + 2
				* LayoutConstants.yOffset));
		this.setBackground(Color.white);
	}

	/**
	 * Paint method
	 * 
	 * @param g
	 *            a Graphics object on which to paint
	 */
	public void paint(Graphics g) {
		super.paint(g);
		int radius = 100;
		
		double total = 0.0;
		for (int i = 0; i < this.courseData.size(); i++) {
			CourseRecord record = (CourseRecord) courseData.elementAt(i);
			total += record.getNumOfStudents();
		}
		
		if (total != 0) {
			double startAngle = 0.0;
			for (int i = 0; i < this.courseData.size(); i++) {
				CourseRecord record = (CourseRecord) courseData.elementAt(i);
				double ratio = (record.getNumOfStudents() / total) * 360.0;
				//draw the arc
				g.setColor(LayoutConstants.courseColours[i]);
				g.fillArc(LayoutConstants.xOffset, LayoutConstants.yOffset, 2 * radius, 2 * radius, (int) startAngle, (int) ratio);
				
				DecimalFormat df = new DecimalFormat("####0.00");
				String percent = df.format((record.getNumOfStudents() / total) * 100) + "%";
				
				g.drawString(record.getName() + " " + percent, LayoutConstants.xOffset - 100, LayoutConstants.yOffset + i * 25);
				startAngle += ratio;
			}
		}
	}

	/**
	 * Informs this observer that the observed CourseData object has changed
	 * 
	 * @param o
	 *            the observed CourseData object that has changed
	 */
	public void update(Observable o) {
		CourseData data = (CourseData) o;
		this.courseData = data.getUpdate();

		this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
				+ (LayoutConstants.barSpacing + LayoutConstants.barWidth)
				* this.courseData.size(), LayoutConstants.graphHeight + 2
				* LayoutConstants.yOffset));
		this.revalidate();
		this.repaint();
	}
	
	public void update(Observable o, Object dataArg) {
		// Vector<CourseRecord> data = (Vector<CourseRecord>) dataArg;
		CourseRecord changedCourseRecord = (CourseRecord) dataArg;
		for(int i = 0; i < this.courseData.size(); i++) {
			if(courseData.get(i).getName().equals(changedCourseRecord.getName())) {
				courseData.get(i).setNumOfStudents(changedCourseRecord.getNumOfStudents());
				break;
			}
		}

		this.setPreferredSize(new Dimension(2 * LayoutConstants.xOffset
				+ (LayoutConstants.barSpacing + LayoutConstants.barWidth)
				* this.courseData.size(), LayoutConstants.graphHeight + 2
				* LayoutConstants.yOffset));
		this.revalidate();
		this.repaint();
	}

	private Vector<CourseRecord> courseData;
}