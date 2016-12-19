package net.lipecki.vote.security;

import net.lipecki.vote.web.ApiConstants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author gregorry
 */
@RestController
@RequestMapping(LoginController.RESOURCE)
public class LoginController {

	public static final String RESOURCE = ApiConstants.API + "/login";

	private final TokenService tokenService;

	public LoginController(final TokenService tokenService) {
		this.tokenService = tokenService;
	}

	@PostMapping({"", "/"})
	public String login(final HttpServletResponse response) {
		final String xsrfToken = UUID.randomUUID().toString();
		final String refreshToken = UUID.randomUUID().toString();

		final Map<String, Object> claims = new HashMap<>();
		claims.put("xsrf", xsrfToken);
		claims.put("refresh", refreshToken);

		final String token = tokenService.createToken("glipecki", claims);

		final Cookie tokenCookie = new Cookie("token", token);
		tokenCookie.setHttpOnly(true);
		response.addCookie(tokenCookie);

		final Cookie xsrfCookie = new Cookie("xsrf", xsrfToken);
		xsrfCookie.setHttpOnly(false);
		response.addCookie(xsrfCookie);

		return "ok";
	}

}
