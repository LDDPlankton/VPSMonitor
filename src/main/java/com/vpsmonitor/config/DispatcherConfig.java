package com.vpsmonitor.config;

import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.vpsmonitor.controller"})
public class DispatcherConfig extends WebMvcConfigurerAdapter
{
	public DispatcherConfig()
	{

	}
	
	@Override
	public void extendMessageConverters(List<HttpMessageConverter<?>> converters)
	{
		converters.add(mappingJackson2HttpMessageConverter());
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter()
	{
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setObjectMapper(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));
		return converter;
	}
	
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer)
    {
        configurer.enable();
    }
    
    @Bean
    public ViewResolver getViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
        
    @Bean
    public HandlerInterceptor localeChangeInterceptor()
    {
	    LocaleChangeInterceptor interceptor = new
	    LocaleChangeInterceptor();
	    interceptor.setParamName("lang");
    return interceptor;
    }
    
    //STORE LANG SELECTION IN COOKIE + SET DEFAULT
    @Bean
    public LocaleResolver localeResolver()
    {
	    CookieLocaleResolver localeResolver = new CookieLocaleResolver();
	    localeResolver.setDefaultLocale(new Locale("en"));
	    return localeResolver;
    }
    
    @Bean
    public MessageSource messageSource()
    {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasename("classpath:/messages");
	    messageSource.setUseCodeAsDefaultMessage(true);
	    return messageSource;
    }
}
