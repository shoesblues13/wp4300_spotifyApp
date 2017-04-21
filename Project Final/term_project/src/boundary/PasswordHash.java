package boundary;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
/**
 * 
 * @author Lokesh Gupta
 * http://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
 * Source for the code for this hashing
 *
 */

public class PasswordHash {
	
	
	public static String hashPass(String pass) throws NoSuchAlgorithmException {
		String passwordToHash = pass;
		byte[] salt = getSalt();
		String securePassword = get_SHA_512_SecurePassword(passwordToHash,salt);
		return securePassword;
	}
	
	private static String get_SHA_512_SecurePassword(String passwordToHash, byte[] salt){
		String generatedPassword = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt);
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i <bytes.length ; ++i){
				sb.append(Integer.toString((bytes[i] & 0xff)+ 0x100, 16).substring(1));
			}
			generatedPassword=sb.toString();
		}
		catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		return generatedPassword;
	}
	
	private static byte[] getSalt() throws NoSuchAlgorithmException {
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		byte[] salt = new byte[16];
		sr.nextBytes(salt);
		return salt;
	}
}
