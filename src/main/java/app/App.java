package app;

import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.Map.Entry;

import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;

import app.util.JwtUtil;
import app.util.RSAUtil;

public class App  {
	
    public static void main( String[] args ) throws NoSuchAlgorithmException {
        RSAUtil.generate();
        /*
        PrivateKey privateKey = RSAUtil.getPrivateKey();
        PublicKey publicKey = RSAUtil.getPublicKey();
        
        String jwt = JwtUtil.generate((RSAKey) privateKey);
        System.out.println(jwt);
        
        DecodedJWT decodedJwt = JwtUtil.isValid(jwt, (RSAPublicKey) publicKey);
        for (Entry<String, Claim> entry : decodedJwt.getClaims().entrySet()) {
        	List<String> authorities = entry.getValue().asList(String.class);
        	
        	if (authorities != null) {
        		for (String authority : authorities) {
        			System.out.println(entry.getKey() + " " + authority);
        		}
        	} else {
        		System.out.println(entry.getKey() + " " + entry.getValue().asString());
        	}
        }
        */
    }
    
}
