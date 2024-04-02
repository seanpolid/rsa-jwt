package app.util;

import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPublicKey;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JwtUtil {

	public static String generate(RSAKey privateKey) throws NoSuchAlgorithmException {
		Algorithm algorithm = Algorithm.RSA256(privateKey);
		return JWT.create()
				  .withIssuer("auth0")
				  .withClaim("user", "sean")
				  .withClaim("authorities", List.of("READ"))
				  .withExpiresAt(Date.valueOf(LocalDate.now()))
				  .withExpiresAt(Instant.now().plusNanos(1_800_000_000_000L))	// Expire in 30 minutes
				  .sign(algorithm);
	}
	
	public static DecodedJWT isValid(String token, RSAPublicKey publicKey) {
		Algorithm algorithm = Algorithm.RSA256(publicKey);
		JWTVerifier verifier = JWT.require(algorithm)
								  .build();
		return verifier.verify(token);
	}
	
}
