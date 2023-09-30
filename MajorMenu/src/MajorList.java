import java.util.ArrayList;
import java.util.Collections;

/**
 * This class manages an ArrayList of majors for a user.
 *
 * @author James Dermezis CUS1116
 */
public class MajorList {
	private ArrayList<Major> majorList = new ArrayList<Major>();

	/**
	 * This method adds a given Major to the ArrayList of majors. The method tests
	 * if the given major's code is equal to another major in the list's code. If
	 * the given major already exists, false is returned and the major is not added.
	 * If the given major does not already exist, the major is added to the
	 * ArrayList of Majors and true is returned.
	 *
	 * @param major - given major
	 * @return true - if adding major was successful.
	 * @return false - if adding major was unsuccessful.
	 */
	public Boolean add(Major major) {
		for (Major currentMajor : majorList) {
			if (currentMajor.getCode().equals(major.getCode()))
				return false;
		}
		majorList.add(major);
		return true;
	}

	/**
	 * This method searches through the ArrayList of Majors to find a match to the
	 * given major code. If a match is found, the major is returned. If a match is
	 * not found, null is returned.
	 *
	 * @param code - given code
	 * @return major that matches given code
	 */
	public Major findByCode(String code) {
		for (Major currentMajor : majorList) {
			if (currentMajor.getCode().equals(code))
				return currentMajor;
		}
		return null;
	}

	/**
	 * This method returns the number of Majors on the ArrayList of Majors.
	 *
	 * @return number of Majors on ArrayList of majors
	 */
	public int numMajors() {
		return majorList.size();
	}

	/**
	 * This method returns an ArrayList of Majors with all elements sorted by total
	 * number of graduates.
	 *
	 * @return sortedMajorList - sorted ArrayList of Majors
	 */
	public ArrayList<Major> getListSortedByTotalMajors() {
		ArrayList<Major> sortedMajorList = new ArrayList<Major>();
		for (Major currentMajor : majorList) {
			sortedMajorList.add(currentMajor);
		}
		Collections.sort(sortedMajorList);
		return sortedMajorList;
	}

	/**
	 * This method iterates through the ArrayList of Majors and finds majors
	 * matching the given category. If the current major matches the given category,
	 * the current major is added to the new ArrayList.
	 *
	 * @param category - category to sort ArrayList of Majors
	 * @return categoryMajorList - ArrayList of Majors
	 */
	public ArrayList<Major> getListByCategory(String category) {
		ArrayList<Major> categoryMajorList = new ArrayList<Major>();
		for (Major currentMajor : majorList) {
			if (currentMajor.getCategory().equals(category))
				categoryMajorList.add(currentMajor);
		}
		return categoryMajorList;
	}

	/**
	 * This method iterates through the ArrayList of majors and finds majors
	 * matching the given criteria of field name, threshold, and operator. If the
	 * current major matches the given criteria, it is added to the new ArrayList.
	 *
	 * @param fieldName
	 * @param threshold
	 * @param op        - 'l' for less than / 'g' for greater than
	 * @return percentThreshold - ArrayList of Majors that pass given criteria.
	 */
	public ArrayList<Major> getListByPercentageThreshold(FieldName fieldName, double threshold, char op) {
		ArrayList<Major> percentThreshold = new ArrayList<Major>();
		if (op == 'l') {
			for (Major currentMajor : majorList) {
				if (currentMajor.getPercentageInField(fieldName) < threshold)
					percentThreshold.add(currentMajor);
			}
		}
		if (op == 'g') {
			for (Major currentMajor : majorList) {
				if (currentMajor.getPercentageInField(fieldName) > threshold)
					percentThreshold.add(currentMajor);
			}
		}
		return percentThreshold;
	}
}