package net.lipecki.vote.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * @author gregorry
 */
@Component
public class TokenService {

	private static final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS512;

	private final SecretKey key;

	private final String appName = "voteservice";

	public TokenService(@Value("${app.signKey:}") final String signKey) {
		if (StringUtils.isNotBlank(signKey)) {
			key = new SecretKeySpec(signKey.getBytes(), ALGORITHM.getValue());
		} else {
			key = MacProvider.generateKey(ALGORITHM);
		}
	}

	public String createToken(final String subject, final Map<String, Object> claims) {
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.signWith(ALGORITHM, key)
				.setId(UUID.randomUUID().toString())
				.setIssuedAt(new Date())
				.setIssuer(appName)
				.compact();
	}

	public Jws<Claims> parseToken(final String token) {
		final Jws<Claims> tokenCandidate = Jwts.parser()
				.setSigningKey(key)
				.parseClaimsJws(token);

		final String alg = tokenCandidate.getHeader().getAlgorithm();
		if (!Objects.equals(alg, ALGORITHM.getValue())) {
			throw new SignatureException("Token contains unexpected signature ALGORITHM");
		}

		return tokenCandidate;
	}

}
