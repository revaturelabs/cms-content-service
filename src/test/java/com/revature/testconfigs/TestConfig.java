package com.revature.testconfigs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import com.google.gson.Gson;
import com.revature.entities.Content;

@Configuration
public class TestConfig {
	
	@Autowired
	DataSource datasource;
	
	@Bean
	public JdbcTemplate getJdbcTemplate() {
		
		return new JdbcTemplate(datasource);
	}
	
	@Bean
	public Gson getGson() {
		return new Gson();
	}

}
