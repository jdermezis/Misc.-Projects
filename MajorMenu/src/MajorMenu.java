import java.util.ArrayList;
import java.util.Scanner;

/**
 * Menu to show information for all related classes
 * 
 * @author James Dermezis
 *
 */
public class MajorMenu {
	public static char printOptions(Scanner keyboard) {
		System.out.println("Here are your options");
		System.out.println("1:Find a major by its code");
		System.out.println("2:List all majors sorted by total graduates");
		System.out.println("3:List all majors in a given category, sorted by total graduates");
		System.out.println(
				"4: List all majors that are above or below a threshold value. You can choose between number in college level jobs, number in non college level jobs and number in low wage jobs");
		System.out.println("0: exit");
		System.out.println("Enter your choice");
		char choice = keyboard.nextLine().charAt(0);
		return choice;
	}

	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("----------------Information on Majors------------");
		System.out.println();
		MajorList majorList = MajorLoader.load("recent-grads.csv");
		char choice = printOptions(keyboard);
		while (choice != '0') {
			switch (choice) {
				case '1':
					findMajor(majorList, keyboard);
					break;
				case '2':
					sortedMajorsGraduates(majorList);
					break;
				case '3':
					sortedMajorsCategory(majorList, keyboard);
					break;
				case '4':
					majorsThreshold(majorList, keyboard);
					break;
				default:
					System.out.println("Please try again");
			}
			choice = printOptions(keyboard);
		}
		System.out.println("Bye");
		keyboard.close();
	}

	private static void findMajor(MajorList majorList, Scanner keyboard) {
		System.out.println("Enter a code");
		String code = keyboard.nextLine();
		System.out.println();
		Major currentMajor = majorList.findByCode(code);
		if (currentMajor == null) {
			System.out.println("Major " + code + " was not found");
		} else {
			printMajor(currentMajor);
		}
	}

	private static void sortedMajorsGraduates(MajorList majorList) {
		ArrayList<Major> sortedMajorList = majorList.getListSortedByTotalMajors();
		System.out.println("Majors sorted by total number of majors");
		System.out.println();
		for (Major currentMajor : sortedMajorList) {
			printMajor(currentMajor);
		}
	}

	private static void sortedMajorsCategory(MajorList majorList, Scanner keyboard) {
		System.out.println("Enter the category");
		String category = keyboard.nextLine();
		ArrayList<Major> categoryArrayList = majorList.getListByCategory(category);
		MajorList categoryMajorList = new MajorList();
		for (Major currentMajor : categoryArrayList)
			categoryMajorList.add(currentMajor);
		ArrayList<Major> sortedMajorList = categoryMajorList.getListSortedByTotalMajors();
		if (sortedMajorList.size() == 0) {
			System.out.println("No majors exist for the category " + category);
		}
		for (Major currentMajor : sortedMajorList) {
			printMajor(currentMajor);
		}
	}

	private static void majorsThreshold(MajorList majorList, Scanner keyboard) {
		ArrayList<Major> percentThresholdList = new ArrayList<Major>();
		System.out.println("Enter the field name. Choices are COLLEGE, NONCOLLEGE, or LOWWAGE");
		FieldName currentFieldName = FieldName.valueOf(keyboard.nextLine());
		System.out.println("Enter the threshold value");
		double thresholdValue = Double.valueOf(keyboard.nextLine());
		System.out.println("Enter l for less than, or g for greater than");
		char userOperation = keyboard.nextLine().charAt(0);
		System.out.println();
		percentThresholdList = majorList.getListByPercentageThreshold(currentFieldName, thresholdValue, userOperation);
		if (percentThresholdList.size() == 0) {
			System.out.println("No majors exist for the specified criteria");
		}
		for (Major currentMajor : percentThresholdList) {
			printMajorThreshold(currentMajor, currentFieldName);
			System.out.println();
		}
	}

	private static void printMajorThreshold(Major currentMajor, FieldName fieldName) {
		System.out.println("Name: " + currentMajor.getMajorName());
		System.out.println("Category: " + currentMajor.getCategory());
		System.out.printf("Percentage in " + fieldName.toString() + " jobs: %.2f",
				currentMajor.getPercentageInField(fieldName));
		System.out.println();
	}

	private static void printMajor(Major major) {
		System.out.println("-----------------MAJOR-----------------");
		System.out.println("Code: " + major.getCode());
		System.out.println("Major name: " + major.getMajorName());
		System.out.println("Total graduates: " + major.getTotal());
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
		System.out.println();
	}
}