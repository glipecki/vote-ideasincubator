package net.lipecki.vote.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author gregorry
 */
public class JwtCookieFilter extends GenericFilterBean {

	public JwtCookieFilter(final AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;

		final Optional<Cookie> tokenCookie = Optional.ofNullable(WebUtils.getCookie(request, "token"));
		if (tokenCookie.isPresent()) {
			try {
				final Authentication authentication = authenticationManager.authenticate(
						new RawJwtTokenAuthentication(tokenCookie.get().getValue())
				);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			} catch (final Exception ex) {
				response.addCookie(deleteTokenCookie());
				SecurityContextHolder.clearContext();
				return;
			}
		}
		chain.doFilter(request, response);
	}

	private Cookie deleteTokenCookie() {
		final Cookie removeCookie = new Cookie("token", null);
		removeCookie.setMaxAge(0);
		return removeCookie;
	}

	private AuthenticationManager authenticationManager;

}
