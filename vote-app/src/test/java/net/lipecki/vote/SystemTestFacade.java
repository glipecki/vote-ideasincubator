package net.lipecki.vote;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.lipecki.vote.items.ItemDto;
import net.lipecki.vote.items.ItemsController;
import net.lipecki.vote.security.LoginController;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author gregorry
 */
public class SystemTestFacade {

	private final MockMvc mockMvc;

	private final MockMvcSessionWrapper session;

	private ObjectMapper objectMapper;

	public SystemTestFacade(final MockMvc mockMvc, final ObjectMapper objectMapper) {
		this.mockMvc = mockMvc;
		this.objectMapper = objectMapper;
		this.session = new MockMvcSessionWrapper(mockMvc);
	}

	public ResultActions performPost(final String resource) {
		return session.withSession(
				post(resource)
		);
	}

	public ResultActions performPost(final String resource, final String body) {
		return session.withSession(
				post(resource)
						.contentType(MediaType.APPLICATION_JSON)
						.content(body)
		);
	}

	public ResultActions performGet(final String resource) {
		return session.withSession(
				get(resource)
		);
	}

	public void login() {
		try {
			performPost(LoginController.RESOURCE)
					.andExpect(status().isOk())
					.andExpect(cookie().exists("token"));
		} catch (final Exception ex) {
			throw new TestException("Login failed", ex);
		}
	}

	public ItemDto addItem(final ItemDto itemToAdd) {
		try {
			return fromJson(
					performPost(ItemsController.RESOURCE, toJson(itemToAdd))
							.andExpect(status().isOk())
							.andReturn()
							.getResponse()
							.getContentAsString(),
					ItemDto.class);
		} catch (final Exception ex) {
			throw new TestException("Item add failed", ex);
		}
	}

	public ItemDto getItem(final Integer itemId) {
		try {
			return fromJson(
					performGet(ItemsController.RESOURCE + "/" + itemId)
							.andExpect(status().isOk())
							.andReturn()
							.getResponse()
							.getContentAsString(),
					ItemDto.class
			);
		} catch (final Exception ex) {
			throw new TestException("Item get failed", ex);
		}
	}

	public ItemDto[] getItems() {
		try {
			return fromJson(
					performGet(ItemsController.RESOURCE)
							.andExpect(status().isOk())
							.andReturn()
							.getResponse()
							.getContentAsString(),
					ItemDto[].class
			);
		} catch (final Exception ex) {
			throw new TestException("Items get failed", ex);
		}
	}

	public String toJson(final Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			throw new TestException("Can't writa object as JSON", e);
		}
	}

	public <T> T fromJson(final String json, final Class<T> aClass) {
		try {
			return objectMapper.readValue(json, aClass);
		} catch (IOException e) {
			throw new TestException("Can't parse JSON response", e);
		}
	}

}
