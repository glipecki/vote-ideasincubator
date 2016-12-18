package net.lipecki.vote.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author gregorry
 */
public class JwtTokenAuthentication extends AbstractAuthenticationToken {

	private final String principal;

	private final String token;

	public JwtTokenAuthentication(final String principal, final String token, final Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.token = token;
		setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return token;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

}
