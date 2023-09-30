
/*
 * Encryption Class 
 * - Performs EncryptString() to encrypt a plaintext string
 * - Performs EncryptFile() to encrypt a plaintext file and outputs the encrypted file
 * 
 * @a James Dermezis
 * CUS 1185
 */
import java.util.*;
import java.io.*;

public class Encryption {
    static String encryptedString = "";
    static Character currentLetter = ' ';
    static Character encryptedLetter = ' ';
    static int arrayIndex;
    static Scanner textInput;
    static String currentLine = "";
    static String encryptedLine = "";
    static String encryptedFile = "";
    static FileWriter outputFile;

    public static String EncryptString(ArrayList<Character> ciphertextKey, ArrayList<Character> plaintextKeyspace,
            String inputString) {
        encryptedString = "";
        currentLetter = ' ';
        encryptedLetter = ' ';
        for (int x = 0; x < inputString.length(); x++) {
            currentLetter = inputString.charAt(x);
            if (plaintextKeyspace.contains(currentLetter)) {
                arrayIndex = plaintextKeyspace.indexOf(currentLetter);
                encryptedString = encryptedString + ciphertextKey.get(arrayIndex);
            } else
                encryptedString = encryptedString + currentLetter;
        }
        return encryptedString;
    }

    public static void EncryptFile(ArrayList<Character> ciphertextKey, ArrayList<Character> plaintextKeyspace)
            throws IOException {
        File inputFile = new File("fileInput.txt");
        outputFile = new FileWriter("fileInput-Encrypted.enc");
        try (BufferedReader fileReader = new BufferedReader(new FileReader(inputFile))) {
            while ((currentLine = fileReader.readLine()) != null) {
                encryptedLine = EncryptString(ciphertextKey, plaintextKeyspace, currentLine);
                outputFile.write(encryptedLine.toString());
            }
        }

        outputFile.close();
    }
}