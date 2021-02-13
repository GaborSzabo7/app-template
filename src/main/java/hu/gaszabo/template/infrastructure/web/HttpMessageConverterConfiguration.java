package hu.gaszabo.template.infrastructure.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class HttpMessageConverterConfiguration {

	@Autowired
	private ObjectMapper objectMapper;

	@Bean
	public HttpMessageConverters customConverters() {
		return new HttpMessageConverters((HttpMessageConverter<?>) new MappingJackson2HttpMessageConverter(objectMapper));
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
