package net.lipecki.vote.security;

import io.jsonwebtoken.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author gregorry
 */
public class TokenServiceTest {

	private String signKey = "abc";

	private TokenService service;

	@Before
	public void setUpService() {
		service = new TokenService(signKey);
	}

	/**
	 * By default tokens are signed with SignatureAlgorithm.HS512 (net.lipecki.vote.security.TokenService#ALGORITHM).
	 * Should throw exception when token signed with different algorithm than expected.
	 */
	@Test(expected = SignatureException.class)
	public void shouldExpectExactTokenAlgorithm() {
		// given
		final String token = Jwts.builder()
				.claim("claimKey", "claimValue")
				.signWith(SignatureAlgorithm.HS256, signKey.getBytes())
				.compact();

		// when & then throw
		service.parseToken(token);
	}

	@Test
	public void shouldParseCreatedToken() {
		final String subject = "glipecki";

		// given
		final String token = service.createToken(subject, new HashMap<>());

		// when
		final Jws<Claims> parsedToken = service.parseToken(token);

		// then
		assertThat(parsedToken.getBody().getSubject()).isEqualTo(subject);
	}

}
