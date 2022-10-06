package edu.deakin.sit218.examgeneration.config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration //– tells spring that this is a configuration file
@EnableWebMvc //– tells that the this app is a Spring MVC web app
@ComponentScan(basePackages = "edu.deakin.sit218.examgeneration")
//this tells where to find the controller classes
public class ExamGenSpringMVCConfig {

	// Define a bean for the View Resolver 
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/view/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	
	@Bean
	public DataSource securityDataSource() {
		//create a connection pool
		ComboPooledDataSource securityDataSource = 
				new ComboPooledDataSource();
		
		//set the jdbc driver class
		try {
			securityDataSource.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			throw new RuntimeException(e);
		}
				
		//set database connection props
		securityDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/questionanswerschema?useSSL=false");
		securityDataSource.setUser("lecturerdbadmin");
		securityDataSource.setPassword("lecturerdbadmin");
		
		//set connection pool props
		securityDataSource.setInitialPoolSize(5);
		securityDataSource.setMinPoolSize(5);
		securityDataSource.setMaxPoolSize(20);
		securityDataSource.setMaxIdleTime(3000);

		return securityDataSource;
	}
}