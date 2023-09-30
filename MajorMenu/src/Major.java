/**
 * This class represents one major. Each task has an code, name, category, total
 * number of graduates, total number of male graduates, total number of female
 * graduates, percentage of total female graduates, total number of employed
 * graduates, total number of unemployed graduates, unemployment rate, median
 * income for all graduates, total number of graduates in jobs requiring a
 * college degree, total number of graduates in jobs not requiring a college
 * degree, and total number of graduates in low wage jobs. The class also
 * implements the Comparable class in order to compare and sort majors on an
 * ArrayList.
 *
 * @author James Dermezis CUS1116
 *
 */
public class Major implements Comparable<Major> {
	private String code;
	private String majorName;
	private String category;
	private int total;
	private int totalMen;
	private int totalWomen;
	private double shareWomen;
	private int employed;
	private int unemployed;
	private double unemployedRate;
	private int medianIncome;
	private int numInCollegeJobs;
	private int numInNonCollegeJobs;
	private int numInLowWageJobs;

	/**
	 * Major constructor that creates Major objects. Each task has an code, name,
	 * category, total number of graduates, total number of male graduates, total
	 * number of female graduates, percentage of total female graduates, total
	 * number of employed graduates, total number of unemployed graduates,
	 * unemployment rate, median income for all graduates, total number of graduates
	 * in jobs requiring a college degree, total number of graduates in jobs not
	 * requiring a college degree, and total number of graduates in low wage jobs.
	 *
	 * @param code
	 * @param name
	 * @param category
	 * @param total
	 * @param totalMen
	 * @param totalWomen
	 * @param shareWomen
	 * @param employed
	 * @param unemployed
	 * @param unemployedRate
	 * @param medianIncome
	 * @param numInCollegeJobs
	 * @param numInNonCollegeJobs
	 * @param numInLowWageJobs
	 */
	public Major(String code, String name, String category, int total, int totalMen, int totalWomen, double shareWomen,
			int employed, int unemployed, double unemployedRate, int medianIncome, int numInCollegeJobs,
			int numInNonCollegeJobs, int numInLowWageJobs) {
		this.code = code;
		majorName = name;
		this.category = category;
		this.total = total;
		this.totalMen = totalMen;
		this.totalWomen = totalWomen;
		this.shareWomen = shareWomen;
		this.employed = employed;
		this.unemployed = unemployed;
		this.unemployedRate = unemployedRate;
		this.medianIncome = medianIncome;
		this.numInCollegeJobs = numInCollegeJobs;
		this.numInNonCollegeJobs = numInNonCollegeJobs;
		this.numInLowWageJobs = numInLowWageJobs;
	}

	/**
	 * This method compares the number of total graduates of two Majors.
	 */
	public int compareTo(Major otherMajor) {
		int currMajorTotal = this.getTotal();
		int otherMajorTotal = otherMajor.getTotal();
		return (otherMajorTotal - currMajorTotal);
	}

	/**
	 * This method returns the percentage of graduates that have jobs in a given
	 * field. The given field contain the total number of graduates in jobs
	 * requiring a college degree, jobs not requiring a college degree, and low wage
	 * jobs.
	 *
	 * @param fieldName - FieldName value to get percentage: COLLEGE, NONCOLLEGE,
	 *                  LOWWAGE
	 * @return percentInField - percentage of graduates that are employed in the
	 *         given field
	 */
	public double getPercentageInField(FieldName fieldName) {
		double percentInField = 1;
		if (fieldName.equals(FieldName.COLLEGE)) {
			percentInField = getPercentageInCollegeJobs();
		}
		if (fieldName.equals(FieldName.NONCOLLEGE)) {
			percentInField = getPercentageInNonCollegeJobs();
		}
		if (fieldName.equals(FieldName.LOWWAGE)) {
			percentInField = getPercentageInLowWageJobs();
		}
		return percentInField;
	}

	/**
	 * This method calculates the percentage of graduates that are in jobs requiring
	 * college degrees
	 *
	 * @return percentage
	 */
	private double getPercentageInCollegeJobs() {
		return (this.getNumInCollegeJobs() / (double) this.getEmployed()) * 100;
	}

	/**
	 * This method calculates the percentage of graduates that are in jobs not
	 * requiring college degrees
	 *
	 * @return percentage
	 */
	private double getPercentageInNonCollegeJobs() {
		return (this.getNumInNonCollegeJobs() / (double) this.getEmployed()) * 100;
	}

	/**
	 * This method calculates the percentage of graduates that are in low wage jobs
	 *
	 * @return percentage
	 */
	private double getPercentageInLowWageJobs() {
		return (this.getNumInLowWageJobs() / (double) this.getEmployed()) * 100;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((category == null) ? 0 : category.hashCode());
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + employed;
		result = prime * result + medianIncome;
		result = prime * result + ((majorName == null) ? 0 : majorName.hashCode());
		result = prime * result + numInCollegeJobs;
		result = prime * result + numInLowWageJobs;
		result = prime * result + numInNonCollegeJobs;
		long temp;
		temp = Double.doubleToLongBits(shareWomen);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + total;
		result = prime * result + totalMen;
		result = prime * result + totalWomen;
		result = prime * result + unemployed;
		temp = Double.doubleToLongBits(unemployedRate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Major other = (Major) obj;
		if (category == null) {
			if (other.category != null)
				return false;
		} else if (!category.equals(other.category))
			return false;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (employed != other.employed)
			return false;
		if (medianIncome != other.medianIncome)
			return false;
		if (majorName == null) {
			if (other.majorName != null)
				return false;
		} else if (!majorName.equals(other.majorName))
			return false;
		if (numInCollegeJobs != other.numInCollegeJobs)
			return false;
		if (numInLowWageJobs != other.numInLowWageJobs)
			return false;
		if (numInNonCollegeJobs != other.numInNonCollegeJobs)
			return false;
		if (Double.doubleToLongBits(shareWomen) != Double.doubleToLongBits(other.shareWomen))
			return false;
		if (total != other.total)
			return false;
		if (totalMen != other.totalMen)
			return false;
		if (totalWomen != other.totalWomen)
			return false;
		if (unemployed != other.unemployed)
			return false;
		if (Double.doubleToLongBits(unemployedRate) != Double.doubleToLongBits(other.unemployedRate))
			return false;
		return true;
	}

	/**
	 * getters and setters
	 *
	 */
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMajorName() {
		return majorName;
	}

	public void setMajorName(String name) {
		this.majorName = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getTotalMen() {
		return totalMen;
	}

	public void setTotalMen(int totalMen) {
		this.totalMen = totalMen;
	}

	public int getTotalWomen() {
		return totalWomen;
	}

	public void setTotalWomen(int totalWomen) {
		this.totalWomen = totalWomen;
	}

	public double getShareWomen() {
		return shareWomen;
	}

	public void setShareWomen(double shareWomen) {
		this.shareWomen = shareWomen;
	}

	public int getEmployed() {
		return employed;
	}

	public void setEmployed(int employed) {
		this.employed = employed;
	}

	public int getUnemployed() {
		return unemployed;
	}

	public void setUnemployed(int unemployed) {
		this.unemployed = unemployed;
	}

	public double getUnemployedRate() {
		return unemployedRate;
	}

	public void setUnemployedRate(double unemployedRate) {
		this.unemployedRate = unemployedRate;
	}

	public int getMedianIncome() {
		return medianIncome;
	}

	public void setMedianIncome(int medianIncome) {
		this.medianIncome = medianIncome;
	}

	public int getNumInCollegeJobs() {
		return numInCollegeJobs;
	}

	public void setNumInCollegeJobs(int numInCollegeJobs) {
		this.numInCollegeJobs = numInCollegeJobs;
	}

	public int getNumInNonCollegeJobs() {
		return numInNonCollegeJobs;
	}

	public void setNumInNonCollegeJobs(int numInNonCollegeJobs) {
		this.numInNonCollegeJobs = numInNonCollegeJobs;
	}

	public int getNumInLowWageJobs() {
		return numInLowWageJobs;
	}

	public void setNumInLowWageJobs(int numInLowWageJobs) {
		this.numInLowWageJobs = numInLowWageJobs;
	}
}