import java.util.ArrayList;

public class MajorLoaderTest {
	public static void main(String[] args) {
		MajorList list = MajorLoader.load("recent-grads.csv");
		System.out.println("list len is " + list.numMajors());
		Major major = list.findByCode("2419");
		if (major == null) {
			System.out.println("2419 not found");
		} else
			System.out.println("major name is " + major.getMajorName() + " total is " + major.getTotal()
					+ " category is " + major.getCategory() + " unemployed = " + major.getUnemployed()
					+ " median income is " + major.getMedianIncome() + " number in college level jobs is "
					+ major.getNumInCollegeJobs() + " number in non college jobs is " + major.getNumInNonCollegeJobs()
					+ " number in low wage jobs is " + major.getNumInLowWageJobs());
		ArrayList<Major> loadTest = new ArrayList<Major>();
		loadTest = list.getListSortedByTotalMajors();
		for (Major currentMajor : loadTest) {
			printMajor(currentMajor);
		}
		ArrayList<Major> categoryTest = new ArrayList<Major>();
		categoryTest = list.getListByCategory("Computers & Mathematics");
		for (Major currentMajor : categoryTest) {
			printMajor(currentMajor);
			System.out.println();
		}
		ArrayList<Major> sortedMajors = list.getListSortedByTotalMajors();
		for (Major currentMajor : sortedMajors) {
			printMajor(currentMajor);
			System.out.println();
		}
		Major percentTest = list.findByCode("2102");
		System.out.println(percentTest.getPercentageInField(FieldName.NONCOLLEGE));
		ArrayList<Major> percentThresholdListTest = list.getListByPercentageThreshold(FieldName.COLLEGE, 50.0, 'g');
		for (Major currentMajor : percentThresholdListTest) {
			System.out.println("Name: " + currentMajor.getMajorName());
			System.out.println("Category: " + currentMajor.getCategory());
			System.out.printf("Percentage in " + FieldName.COLLEGE.toString() + " jobs: %.2f",
					currentMajor.getPercentageInField(FieldName.COLLEGE));
			System.out.println();
			System.out.println();
		}
	}

	private static void printMajor(Major major) {
		System.out.println("Code: " + major.getCode());
		System.out.println("Major name: " + major.getMajorName());
		System.out.println("Total graduates " + major.getTotal());
		System.out.println("Total male graduates: " + major.getTotalMen());
		System.out.println("Total female graduates: " + major.getTotalWomen());
		System.out.println("Percentage female graduates: " + major.getShareWomen());
		System.out.println("Major category: " + major.getCategory());
		System.out.println("Total employed: " + major.getEmployed());
		System.out.println("Total unemployed: " + major.getUnemployed());
		System.out.println("Unemployment rate: " + major.getUnemployedRate());
		System.out.println("Median income: " + major.getMedianIncome());
		System.out.println("Total in jobs requiring a college degree: " + major.getNumInCollegeJobs());
		System.out.println("Total in jobs not requiring a college degree: " + major.getNumInNonCollegeJobs());
		System.out.println("Total in low wage jobs: " + major.getNumInLowWageJobs());
	}
}