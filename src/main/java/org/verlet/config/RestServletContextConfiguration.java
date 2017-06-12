package org.verlet.config;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;
import org.verlet.config.annotation.RestEndPoint;
import org.verlet.config.annotation.RestEndPointAdvice;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
@ComponentScan(
		basePackages = "org.verlet.site",
		useDefaultFilters = false,
		includeFilters = @ComponentScan.Filter({RestEndPoint.class, RestEndPointAdvice.class})
)
public class RestServletContextConfiguration extends WebMvcConfigurerAdapter{
	@Inject
	ObjectMapper objectMapper;
	@Inject
	Marshaller marshaller;
	@Inject
	Unmarshaller unmarshaller;
	@Inject
	SpringValidatorAdapter validator;
	
	@Override
	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorPathExtension(false).favorParameter(false).ignoreAcceptHeader(false).defaultContentType(MediaType.APPLICATION_JSON);
	}
	
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new SourceHttpMessageConverter<>());
		
		MarshallingHttpMessageConverter xmlConverter = new MarshallingHttpMessageConverter();
		xmlConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("application","xml"),new MediaType("text","xml")));
		xmlConverter.setMarshaller(this.marshaller);
		xmlConverter.setUnmarshaller(this.unmarshaller);
		converters.add(xmlConverter);
		
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		jsonConverter.setSupportedMediaTypes(Arrays.asList(new MediaType("application","json"),new MediaType("text","json")));
		jsonConverter.setObjectMapper(this.objectMapper);
		converters.add(jsonConverter);
		
	}

	@Override
	public Validator getValidator() {
		return this.validator;
	}
	
	@Bean
	public LocaleResolver localeResolver(){
		return new AcceptHeaderLocaleResolver();
	}
}