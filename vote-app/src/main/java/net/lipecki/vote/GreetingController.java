package net.lipecki.vote;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gregorry
 */
@RestController
public class GreetingController {

	@RequestMapping(value = "/greeting", method = RequestMethod.GET)
	public String greetingGet() {
		return "Welcome!";
	}

}
