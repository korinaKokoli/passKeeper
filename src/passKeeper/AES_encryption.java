package passKeeper;

import java.security.*;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.*;

public class AES_encryption {
	private static final String ALGO = "AES";
	/*private static byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's',
			't', 'S', 'e', 'c', 'r', 'e', 't', 'K', 'e', 'y' };*/

	public static String encrypt(byte[] keyValue, String Data) throws Exception {
		Key key = generateKey(keyValue);
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(Data.getBytes());
//		String encryptedValue = new BASE64Encoder().encode(encVal);
		byte[] encryptedValue = new Base64().encode(encVal);
		return new String(encryptedValue);
	}

	public static String decrypt(byte[] keyValue, String encryptedData) throws Exception {
		Key key = generateKey(keyValue);
		Cipher c = Cipher.getInstance(ALGO);
		c.init(Cipher.DECRYPT_MODE, key);
//		byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
		byte[] decordedValue = new Base64().decode(encryptedData.getBytes());
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private static Key generateKey(byte[] keyValue) throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGO);
		return key;
	}

}
