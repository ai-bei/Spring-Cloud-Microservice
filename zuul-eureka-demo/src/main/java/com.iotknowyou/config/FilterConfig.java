package com.iotknowyou.config;

import com.iotknowyou.filter.DebugRequestFilter;
import com.iotknowyou.filter.ErrorFilter;
import com.iotknowyou.filter.IpFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class FilterConfig {

	@Bean
	public IpFilter ipFilter() {
		return new IpFilter();
	}

	@Bean
	public ErrorFilter errorFilter() {
		return new ErrorFilter();
	}
	
	@Bean
	public DebugRequestFilter debugRequestFilter() {
		return new DebugRequestFilter();
	}
}
