package net.lipecki.vote;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import javax.servlet.http.Cookie;
import java.util.stream.Stream;

/**
 * <p>
 * Provides cookie handling between subsequent requestes.
 * </p>
 *
 * @author gregorry
 */
public class MockMvcSessionWrapper {

	private final MockMvc mockMvc;

	private Cookie[] cookies = new Cookie[0];

	public MockMvcSessionWrapper(final MockMvc mockMvc) {
		this.mockMvc = mockMvc;
	}

	public ResultActions withSession(final MockHttpServletRequestBuilder request) {
		try {
			if (cookies != null && cookies.length > 0) {
				request.cookie(cookies);
			}
			final ResultActions result = mockMvc.perform(request);
			final Cookie[] resultCookies = result.andReturn().getResponse().getCookies();
			cookies = concatCookieArrays(cookies, resultCookies);
			return result;
		} catch (final Exception ex) {
			throw new TestException("Can't execute mock mvc with session", ex);
		}
	}

	private Cookie[] concatCookieArrays(final Cookie[] left, final Cookie[] right) {
		return Stream.concat(java.util.Arrays.stream(left), java.util.Arrays.stream(right)).toArray(Cookie[]::new);
	}

}
