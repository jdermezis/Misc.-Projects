
/*
 * Frequency Analysis Class 
 * - Performs frequencyAnalysis() on an encrypted file
 * - Outputs a table listing each ciphertext character and its frequency
 * 
 * @a James Dermezis
 * CUS 1185
 */
import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class frequencyAnalysis {
    static char[] lettersofAlphabet = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
    static ArrayList<Character> plaintextLetters = new ArrayList<Character>();
    static ArrayList<Integer> frequencyList = new ArrayList<Integer>();
    static ArrayList<Character> ciphertextLetters = new ArrayList<Character>();
    static int totalCharacters, current;
    static Character currentCharacter;
    static DecimalFormat formatting = new DecimalFormat("#.#####");

    public static void performFrequencyAnalysis() {
        for (int x = 0; x < 26; x++) {
            frequencyList.add(0);
            plaintextLetters.add(lettersofAlphabet[x]);
        }
        File inputFile = new File("fileInput-Encrypted.enc");
        try (BufferedReader fileReader = new BufferedReader(new FileReader(inputFile))) {
            while ((current = fileReader.read()) != -1) {
                totalCharacters++;
                currentCharacter = (char) current;
                if (ciphertextLetters.contains(currentCharacter)) {
                    int index = ciphertextLetters.indexOf(currentCharacter);
                    int currentValue = frequencyList.get(index);
                    frequencyList.set(index, currentValue += 1);

                } else if (plaintextLetters.contains(currentCharacter)) {
                    ciphertextLetters.add(currentCharacter);
                    int index = ciphertextLetters.indexOf(currentCharacter);
                    frequencyList.set(index, 1);
                }
            }
        } catch (Exception FileNotFound) {
            System.err.println(FileNotFound);
        }
        printFrequencyTable();
    }

    public static void printFrequencyTable() {
        System.out.println("Cryptoanalysis Complete.");
        System.out.println();
        System.out.println("-----------------------------");
        System.out.println("Char : Frequency");
        for (int x = 0; x < plaintextLetters.size(); x++) {
            System.out.print("   " + ciphertextLetters.get(x) + " : ");
            System.out.println(formatting.format(((float) frequencyList.get(x) / totalCharacters)));
        }
    }
}