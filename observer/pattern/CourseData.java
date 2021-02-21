package observer.pattern;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JOptionPane;

import observer.CourseRecord;

/**
 * Represents a vector of CourseRecords.
 */
public class CourseData extends Observable {

	/**
	 * Constructs a CourseData object
	 */
	public CourseData() {
		this.courseData = new Vector<CourseRecord>();
		this.observerInterestedInUpdate = new HashMap<Object, Boolean>();
	}
	
	public void attach(Observer o, Boolean isInterestedInObjectUpdate) {
		super.attach(o);
		this.observerInterestedInUpdate.put(o, isInterestedInObjectUpdate);
	}

	public void detach(Observer o) {
		super.detach(o);
		this.observerInterestedInUpdate.remove(o);
	}
	
	/**
	 * Notify all Observers that Subject has changed
	 */
	public void notifyObservers(Object dataArg) {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = observers.elementAt(i);
			if(observerInterestedInUpdate.get(observer) != null && observerInterestedInUpdate.get(observer) == true) {
				observer.update(this, dataArg);
			}
		}
	}


	/**
	 * Add a new CourseRecord object
	 * 
	 * @param courseRecord
	 *            the CourseRecord to be added
	 */
	public void addCourseRecord(CourseRecord courseRecord) {
		boolean alreadyExists = false;
		for (int i = 0; i < courseData.size(); i++) {
			CourseRecord record = courseData.elementAt(i);
			if (record.getName().equals(courseRecord.getName())) {
				alreadyExists = true;
				JOptionPane
						.showMessageDialog(
								null,
								"Warning: Attempt to add new course with an already existing name",
								"alert", JOptionPane.ERROR_MESSAGE);
				i = courseData.size(); // exit the loop
			}
		}
		if (!alreadyExists)
			this.courseData.addElement(courseRecord);
		// this.notifyObservers(this.getUpdate());
		this.notifyObservers();
	}

	/**
	 * Update an existing CourseRecord object
	 * 
	 * @param subjectName
	 *            the name CourseRecord to be updated
	 * @param numOfStudents
	 *            the new number of students for this course
	 */
	public void changeCourseRecord(String subjectName, int numOfStudents) {
		for (int i = 0; i < courseData.size(); i++) {
			CourseRecord record = courseData.elementAt(i);
			if (record.getName().equals(subjectName)) {
				record.setNumOfStudents(numOfStudents);
				i = courseData.size();
				this.notifyObservers(record);
			}
		}
	}

	/**
	 * Return a copy of the vector of course data. Used by Observers to pull
	 * data.
	 * 
	 * @return vector of course data
	 */
	public Vector<CourseRecord> getUpdate() {
		return (Vector<CourseRecord>) courseData.clone();
	}

	private Vector<CourseRecord> courseData;
	private Map<Object, Boolean> observerInterestedInUpdate;
}