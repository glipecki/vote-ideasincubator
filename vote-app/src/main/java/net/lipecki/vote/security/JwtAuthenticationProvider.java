package net.lipecki.vote.security;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import java.util.ArrayList;

/**
 * @author gregorry
 */
class JwtAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
		final RawJwtTokenAuthentication jwtAuthentication = (RawJwtTokenAuthentication) authentication;
		return new JwtTokenAuthentication("glipecki", (String) jwtAuthentication.getPrincipal(), new ArrayList<>());
	}

	@Override
	public boolean supports(final Class<?> authentication) {
		return RawJwtTokenAuthentication.class.isAssignableFrom(authentication);
	}

}
