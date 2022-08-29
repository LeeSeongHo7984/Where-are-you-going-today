package com.varxyz.wgt.data;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc //스프링 MVC 설정에 대한 기본 구성 제공
@ComponentScan(basePackages = "com.varxyz.wgt")
public class WebMVCConfig implements WebMvcConfigurer{
	
	/*
	 * DispatcherServlet의 매핑 경로를 "/"로 주었을 때, JSP/HTML/CSS 등을 바르게 처리하도록 한다.
	 * <mvc: default-servlet-handler> 
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
	/*
	 * 컨트롤러의 처리 결과를 jsp로 표시하기 위한 설정
	 */
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.jsp("/WEB-INF/views/",".jsp");
	}
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

}
