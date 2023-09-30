import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class reads information about various majors from a data file. Each
 * major from the data file is read through a Scanner object and the major is
 * added to the MajorList
 *
 * @author James Dermezis CUS 1116
 *
 */
public class MajorLoader {
	public static MajorList load(String filePath) {
		MajorList majors = new MajorList();
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(new File(filePath));
			fileIn.nextLine();
			while (fileIn.hasNextLine()) {
				String line = fileIn.nextLine();
				Major currentMajor = processMajorInputLine(line);
				majors.add(currentMajor);
			}
		} catch (FileNotFoundException e) {
			System.err.println("There is no file at location " + filePath);
		} finally {
			if (fileIn != null)
				fileIn.close();
		}
		return majors;
	}

	public static Major processMajorInputLine(String line) {
		Scanner lineScanner = new Scanner(line);
		lineScanner.useDelimiter(",");
		String code = lineScanner.next();
		String majorName = lineScanner.next();
		int total = lineScanner.nextInt();
		int totalMen = lineScanner.nextInt();
		int totalWomen = lineScanner.nextInt();
		String category = lineScanner.next();
		double shareWomen = lineScanner.nextDouble();
		int employed = lineScanner.nextInt();
		int unemployed = lineScanner.nextInt();
		double unemployedRate = lineScanner.nextDouble();
		int medianIncome = lineScanner.nextInt();
		int numInCollegeJobs = lineScanner.nextInt();
		int numInNonCollegeJobs = lineScanner.nextInt();
		int numInLowWageJobs = lineScanner.nextInt();
		Major currentMajor = new Major(code, majorName, category, total, totalMen, totalWomen, shareWomen, employed,
				unemployed, unemployedRate, medianIncome, numInCollegeJobs, numInNonCollegeJobs, numInLowWageJobs);
		lineScanner.close();
		return currentMajor;
	}
}