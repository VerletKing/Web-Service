package org.verlet.config;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * 服务器启动入口类 类似web.xml 实现WebApplicationInitializer
 * 
 * @author Try
 */
public class Bootstrap implements WebApplicationInitializer {
	public void onStartup(ServletContext servletContext) throws ServletException {
		servletContext.getServletRegistration("default").addMapping("/resources/*");

		// 注册spring容器
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(RootContextConfiguration.class);
		servletContext.addListener(new ContextLoaderListener(rootContext));

		// 注册springmvc
		AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
		webContext.register(WebServletContextConfiguration.class);
		Dynamic dispatcher = servletContext.addServlet("springWebDispatcher", new DispatcherServlet(webContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.setMultipartConfig(new MultipartConfigElement(null, 20_971_520L, 41_943_040L, 512_000));
		dispatcher.addMapping("/");

		// 注册restFul
		AnnotationConfigWebApplicationContext restContext = new AnnotationConfigWebApplicationContext();
		restContext.register(RestServletContextConfiguration.class);
		DispatcherServlet servlet = new DispatcherServlet(restContext);
		servlet.setDispatchOptionsRequest(true);
		dispatcher = servletContext.addServlet("springRestDispatcher", servlet);
		dispatcher.setLoadOnStartup(2);
		dispatcher.addMapping("/services/Rest/*");
	}
}