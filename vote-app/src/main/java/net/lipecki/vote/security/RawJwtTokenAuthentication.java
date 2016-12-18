package net.lipecki.vote.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.ArrayList;

/**
 * @author gregorry
 */
public class RawJwtTokenAuthentication extends AbstractAuthenticationToken {

	private String rawToken;

	public RawJwtTokenAuthentication(final String rawToken) {
		super(new ArrayList<>());
		this.rawToken = rawToken;
		setAuthenticated(false);
	}

	@Override
	public Object getCredentials() {
		return rawToken;
	}

	@Override
	public Object getPrincipal() {
		return "N/A";
	}

	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
		if (isAuthenticated) {
			throw new IllegalArgumentException(
					"Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
		}

		super.setAuthenticated(false);
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		this.rawToken = null;
	}

}
