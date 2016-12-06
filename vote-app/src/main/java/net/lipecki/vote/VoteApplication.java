package net.lipecki.vote;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.Collections;

@SpringBootApplication
@Slf4j
public class VoteApplication extends WebMvcConfigurerAdapter {

	 // 1

//	@Controller
//	static class SpaController {
//		@RequestMapping("resourceNotFound")
//		public String handle() {
//			return "forward:/index.html";
//		}
//	}
//
//	@Bean
//	public EmbeddedServletContainerCustomizer containerCustomizer() {
//		return container -> container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/resourceNotFound"));
//	}

	// 2

//	@Autowired
//	private RequestMappingHandlerMapping requestMappingHandlerMapping;
//
//	static class SpaWithHistoryPushStateHandler {
//
//	}
//
//	static class SpaWithHistoryPushStateHandlerAdapter implements HandlerAdapter {
//
//		@Override
//		public boolean supports(final Object handler) {
//			return handler instanceof SpaWithHistoryPushStateHandler;
//		}
//
//		@Override
//		public ModelAndView handle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) throws Exception {
//			response.getOutputStream().println("default index.html");
//			return null;
//		}
//
//		@Override
//		public long getLastModified(final HttpServletRequest request, final Object handler) {
//			return -1;
//		}
//	}
//
//	@Bean
//	public SpaWithHistoryPushStateHandlerAdapter spaWithHistoryPushStateHandlerAdapter() {
//		return new SpaWithHistoryPushStateHandlerAdapter();
//	}
//
//	@PostConstruct
//	public void setupDefaultHandler() {
//		requestMappingHandlerMapping.setDefaultHandler(new SpaWithHistoryPushStateHandler());
//	}

	// 3

//	@Autowired
//	private ResourceProperties resourceProperties;
//
//	@Override
//	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
//		registry.addResourceHandler("/**")
//				.addResourceLocations(resourceProperties.getStaticLocations())
//				.setCachePeriod(resourceProperties.getCachePeriod())
//				.resourceChain(resourceProperties.getChain().isCache())
//				.addResolver(new PathResourceResolver() {
//					@Override
//					public Resource resolveResource(final HttpServletRequest request, final String requestPath, final List<? extends Resource> locations, final ResourceResolverChain chain) {
//						final Resource resource = super.resolveResource(request, requestPath, locations, chain);
//						if (resource != null) {
//							return resource;
//						} else {
//							return super.resolveResource(request, "/index.html", locations, chain);
//						}
//					}
//				});
//	}

	// 4
	@Bean
	public ErrorViewResolver customErrorViewResolver() {
		final ModelAndView redirectToIndexHtml = new ModelAndView("forward:/index.html", Collections.emptyMap(), HttpStatus.OK);
		return (request, status, model) -> status == HttpStatus.NOT_FOUND ? redirectToIndexHtml : null;
	}

	public static void main(String[] args) {
		SpringApplication.run(VoteApplication.class, args);
	}

}
