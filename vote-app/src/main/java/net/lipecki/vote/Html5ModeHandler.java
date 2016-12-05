package net.lipecki.vote;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Supports SPA app using history.pushState via HTML5 history api.
 * <p>
 *     Use <base href="/"> in you app.
 * </p>
 */
@Component
@RequestMapping
public class Html5ModeHandler {

	@RequestMapping(value = "/{[path:[^\\.]*}")
	public String redirect() {
		return "forward:/";
	}

}