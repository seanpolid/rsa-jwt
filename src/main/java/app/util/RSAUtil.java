package app.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.EncodedKeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAUtil {
	
	private static String publicKeyPath = "/keys/cosc411/authorization-server/test/public.key";
	private static String privateKeyPath = "/keys/cosc411/authorization-server/test/private.key";
	
	public static void generate() throws NoSuchAlgorithmException {
		KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
		generator.initialize(2048);
		
		KeyPair keyPair = generator.generateKeyPair();
		write(publicKeyPath, keyPair.getPublic());
		write(privateKeyPath, keyPair.getPrivate());
	}
	
	private static void write(String path, Key key) {
		String fullPath = System.getProperty("user.home") + path;
		
		try (FileOutputStream outputStream = new FileOutputStream(fullPath)) {
			outputStream.write(key.getEncoded());
		} catch (Exception e) {
		}
	}
	
	public static PrivateKey getPrivateKey() {
		String fullPrivateKeyPath = System.getProperty("user.home") + privateKeyPath;
		
		try (FileInputStream inputStream = new FileInputStream(fullPrivateKeyPath)) {
			byte[] bytes = inputStream.readAllBytes();
			PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePrivate(keySpec);
		} catch (Exception ex) {
			System.out.println("An exception occurred while reading private key. Exception: " + ex);
		}
		
		return null;
	}
	
	public static PublicKey getPublicKey() {
		String fullPublicKeyPath = System.getProperty("user.home") + publicKeyPath;
		
		try (FileInputStream inputStream = new FileInputStream(fullPublicKeyPath)) {
			byte[] bytes = inputStream.readAllBytes();
			EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			return keyFactory.generatePublic(keySpec);
		} catch (Exception ex) {
		}
		
		return null;
	}

}
