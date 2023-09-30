/**
 * An example of AES Encryption and Decryption
 * CSS-1035
 * 
 * @author James Dermezis
 *
 */
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.util.Base64;
import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;

public class aesExample {
	public static void main(String[] args)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException,
			BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {

		// Plain text Message to be Encrypted
		String plaintextMessage = "Secret Message!";

		// Generate random IV & key
		byte[] iv = generateRandomIV();
		SecretKey aesKey = generateAESKey();

		String encryptedString = encryptString(plaintextMessage, aesKey, iv);
		String decryptedString = decryptString(encryptedString, aesKey, iv);

		System.out.println("Plaintext: " + plaintextMessage);
		System.out.println("Encrypted Ciphertext: " + encryptedString);
		System.out.println("Decrypted Ciphertext: " + decryptedString);
	}

	/**
	 * Generates random IV - should be safely stored
	 * 
	 * @return IV for encryption
	 */
	public static byte[] generateRandomIV() {
		byte[] iv = new byte[12];
		return iv;
	}

	/**
	 * Generates AES Key for Encryption and Decryption - should be safely stored
	 * 
	 * @return aesKey
	 * @throws NoSuchAlgorithmException
	 */
	public static SecretKey generateAESKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		return keyGenerator.generateKey();
	}

	/**
	 * Encrypts plaintext string using AES and generated key
	 * 
	 * @param stringToEncrypt
	 * @param aesKey
	 * @param iv
	 * @return encrypted string
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 * @throws UnsupportedEncodingException
	 */
	public static String encryptString(String stringToEncrypt, SecretKey aesKey, byte[] iv)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException,
			UnsupportedEncodingException {
		// Initialize cipher for Encryption - Better choice is "AES/GCM/PKCS5Padding",
		// however "AES/GCM/NoPadding" is used as per bug report
		// (https://bugs.openjdk.java.net/browse/JDK-8229043)
		GCMParameterSpec myParams = new GCMParameterSpec(128, iv);
		Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
		aesCipher.init(Cipher.ENCRYPT_MODE, aesKey, myParams);
		// Return Encrypted String
		return Base64.getEncoder().encodeToString(aesCipher.doFinal(stringToEncrypt.getBytes("UTF-8")));
	}

	/**
	 * Decrypts ciphertext string using AES and generated key
	 * 
	 * @param stringToDecrypt
	 * @param aesKey
	 * @param iv
	 * @return decrypted string
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 * @throws InvalidAlgorithmParameterException
	 * @throws IllegalBlockSizeException
	 * @throws BadPaddingException
	 */
	public static String decryptString(String stringToDecrypt, SecretKey aesKey, byte[] iv)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
			InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		// Initialize cipher for Decryption - Better choice is "AES/GCM/PKCS5Padding",
		// however "AES/GCM/NoPadding" is used as per bug report
		// (https://bugs.openjdk.java.net/browse/JDK-8229043)
		GCMParameterSpec myParams = new GCMParameterSpec(128, iv);
		Cipher aesCipher = Cipher.getInstance("AES/GCM/NoPadding");
		aesCipher.init(Cipher.DECRYPT_MODE, aesKey, myParams);
		// Return Decrypted String
		return new String(aesCipher.doFinal(Base64.getDecoder().decode(stringToDecrypt)));
	}
}
