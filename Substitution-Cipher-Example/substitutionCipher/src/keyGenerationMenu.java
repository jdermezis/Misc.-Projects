
/*
 * Manual Symmetric Key Generation Class with Menu
 * Utilizes Encryption.java, Decryption.java, and frequencyAnalysis.java for implementation
 * 
 * @a James Dermezis
 * CUS 1185
 */
import java.util.ArrayList;
import java.io.IOException;
import java.util.*;

public class keyGenerationMenu {
	static char[] lettersofAlphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
	static ArrayList<Character> plaintextKeyspace = new ArrayList<Character>();
	static ArrayList<Character> availableKeyspace = new ArrayList<Character>();
	static ArrayList<Character> ciphertextKey = new ArrayList<Character>();
	static Character selectedPlainLetter, selectedCipherLetter;
	static Scanner userInput;
	static String inputString, filePath;
	static int count = 0;

	public static void main(String[] args) throws IOException {
		System.out.println();
		for (int x = 0; x < lettersofAlphabet.length; x++) {
			plaintextKeyspace.add(lettersofAlphabet[x]);
			availableKeyspace.add(lettersofAlphabet[x]);
			ciphertextKey.add(' ');
		}
		System.out.println("Symmetric Key Generation");
		Scanner userInput = new Scanner(System.in).useDelimiter("\n");
		while (count < 25) {
			for (int x = 0; x < availableKeyspace.size(); x++) {
				System.out.println();
				printAvailableLetters();
				printCiphertextLetters();
				setCurrentPlaintextLetter(userInput);
				if (availableKeyspace.contains(selectedPlainLetter) == true) {
					setCurrentCiphertextLetter(userInput);
					if (ciphertextKey.get(plaintextKeyspace.indexOf(selectedCipherLetter)) == ' ') {
						ciphertextKey.set(plaintextKeyspace.indexOf(selectedCipherLetter), selectedPlainLetter);
						availableKeyspace.set(plaintextKeyspace.indexOf(selectedPlainLetter), ' ');
						count++;
					} else {
						System.out.println("Value already selected. Try again.");
					}
				}
			}
		}
		System.out.println("Successful Symmetric Key Generation.");
		do {
			int menuSelection = printMenu(userInput);
			switch (menuSelection) {
				case '1':
					printCiphertextLetters();
					if (performOtherMenuOption(userInput) == true)
						break;
					else
						System.exit(0);
				case '2':
					System.out.print("Enter a string to be symmetrically encrypted: ");
					inputString = userInput.next();
					System.out.println();
					System.out.println("Plaintext String: " + inputString);
					System.out.println("Symmetrically Encrypted String: " + Encryption.EncryptString(ciphertextKey, plaintextKeyspace, inputString));
					if (performOtherMenuOption(userInput) == true)
						break;
					else
						System.exit(0);
				case '3':
					Encryption.EncryptFile(ciphertextKey, plaintextKeyspace);
					System.out.println("Plaintext File inputFile.txt Successfully Encrypted.");
					if (performOtherMenuOption(userInput) == true)
						break;
					else
						System.exit(0);
				case '4':
					System.out.print("Enter a string to be symmetrically decrypted: ");
					inputString = userInput.next();
					System.out.println();
					System.out.println("Encrypted String: " + inputString);
					System.out.println("Decrypted String: "
							+ Decryption.DecryptString(ciphertextKey, plaintextKeyspace, inputString));
					if (performOtherMenuOption(userInput) == true)
						break;
					else
						System.exit(0);
				case '5':
					Decryption.DecryptFile(ciphertextKey, plaintextKeyspace);
					System.out.println("Encrypted File inputFile-Encrypted.enc Successfully Decrypted.");
					if (performOtherMenuOption(userInput) == true)
						break;
					else
						System.exit(0);
				case '6':
					frequencyAnalysis.performFrequencyAnalysis();
					if (performOtherMenuOption(userInput) == true)
						break;
					else
						System.exit(0);
				case '0':
					System.exit(0);
				default:
					System.out.println();
					System.out.println("Invalid Selection. Please try again.");
					printMenu(userInput);
					break;
			}
		} while (userInput.hasNextLine());
		userInput.close();
	}

	private static int printMenu(Scanner userInput) {
		System.out.println("---------------------------------------");
		System.out.println("Here are your options: ");
		System.out.println("1) Print Symmetric Key");
		System.out.println("2) Encrypt a Plaintext String");
		System.out.println("3) Encrypt a Plaintext File");
		System.out.println("4) Decrypt an Encrypted String");
		System.out.println("5) Decrypt an Encrypted File");
		System.out.println("6) Frequency Analysis on Encrypted File");
		System.out.println("0) Quit");
		System.out.println();
		System.out.print("Choose your option: ");
		return userInput.next().charAt(0);
	}

	private static Boolean performOtherMenuOption(Scanner userInput) {
		System.out.println("Task completed successfully.");
		System.out.println("---------------------------------------");
		System.out.println("1) Back to Menu");
		System.out.println("0) Quit");
		System.out.print("Choose your option: ");
		do {
			switch (userInput.next().charAt(0)) {
				case '1':
					return true;
				case '0':
					return false;
				default:
					System.out.println();
					System.out.println("Invalid Selection. Please try again.");
					backMenuOptions();
					break;
			}
		} while (userInput.hasNextLine());
		return false;
	}

	public static void backMenuOptions() {
		System.out.println("---------------------------------------");
		System.out.println("1) Back to Menu");
		System.out.println("0) Quit");
		System.out.print("Choose your option: ");
	}

	public static Character setCurrentPlaintextLetter(Scanner userInput) {
		System.out.print("Choose a plaintext letter: ");
		selectedPlainLetter = userInput.next().charAt(0);
		return selectedPlainLetter;
	}

	public static Character setCurrentCiphertextLetter(Scanner userInput) {
		System.out.print("Choose a ciphertext letter: ");
		selectedCipherLetter = userInput.next().charAt(0);
		return selectedCipherLetter;
	}

	public static void printAvailableLetters() {
		System.out.println("Available Letters: " + availableKeyspace.toString());
	}

	public static void printCiphertextLetters() {
		System.out.println("Ciphertext Key:    " + ciphertextKey.toString());
	}
}
