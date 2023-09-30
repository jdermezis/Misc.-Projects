
/*
 * Decryption Class 
 * - Performs DecryptString() to decrypt a plaintext string
 * - Performs DecryptFile() to decrypt an encrypted file and outputs the decrypted file
 * 
 * @a James Dermezis
 * CUS 1185
 */
import java.io.*;
import java.util.*;

public class Decryption {
    static String plaintextString = "";
    static Character currentLetter = ' ';
    static Character plaintextLetter = ' ';
    static int arrayIndex;
    static Scanner textInput;
    static String currentLine = "";
    static String decryptedLine = "";
    static String decryptedFile = "";
    static FileWriter outputFile;

    public static String DecryptString(ArrayList<Character> ciphertextKey, ArrayList<Character> plaintextKeyspace,
            String inputString) {
        plaintextString = "";
        currentLetter = ' ';
        plaintextLetter = ' ';

        for (int x = 0; x < inputString.length(); x++) {
            currentLetter = inputString.charAt(x);
            if (ciphertextKey.contains(currentLetter)) {
                arrayIndex = ciphertextKey.indexOf(currentLetter);
                plaintextString = plaintextString + plaintextKeyspace.get(arrayIndex);
            } else
                plaintextString = plaintextString + currentLetter;
        }
        return plaintextString;
    }

    public static void DecryptFile(ArrayList<Character> ciphertextKey, ArrayList<Character> plaintextKeyspace)
            throws IOException {
        File inputFile = new File("fileInput-Encrypted.enc");
        outputFile = new FileWriter(
                "fileInput-Decrypted.dec");
        try (BufferedReader fileReader = new BufferedReader(new FileReader(inputFile))) {
            while ((currentLine = fileReader.readLine()) != null) {
                decryptedLine = DecryptString(ciphertextKey, plaintextKeyspace, currentLine);
                outputFile.write(decryptedLine.toString());
            }
        }
        outputFile.close();
    }
}
