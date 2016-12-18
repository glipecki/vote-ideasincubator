package net.lipecki.vote.security;

import net.lipecki.vote.web.ApiConstants;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author gregorry
 */
@RestController
@RequestMapping(LoginController.RESOURCE)
public class LoginController {

	public static final String RESOURCE = ApiConstants.API + "/login";

	@PostMapping({"", "/"})
	public String login(final HttpServletResponse response) {
		final Cookie jwtCookie = new Cookie("token", "sampleToken");
		jwtCookie.setHttpOnly(true);
		response.addCookie(jwtCookie);

		return "ok";
	}

}
