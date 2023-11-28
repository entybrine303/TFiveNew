package graduate.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import graduate.interceptor.AuthenticationInterceptor;


@Configuration
public class AuthenticationInterceptorConfig implements WebMvcConfigurer{
	@Autowired
	private AuthenticationInterceptor authenticationInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticationInterceptor)
		.addPathPatterns("/tfive/admin/**");
		
		registry.addInterceptor(authenticationInterceptor)
		.addPathPatterns("/tfive/driver/**");
		
		registry.addInterceptor(authenticationInterceptor)
		.addPathPatterns("/tfive/cart/**");
		registry.addInterceptor(authenticationInterceptor)
		.addPathPatterns("/tfive/wishlist/**");
		registry.addInterceptor(authenticationInterceptor)
		.addPathPatterns("/tfive/checkout/**");
		registry.addInterceptor(authenticationInterceptor)
		.addPathPatterns("/tfive/profile/**");
	}
}
