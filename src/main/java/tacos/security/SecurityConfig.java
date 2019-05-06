package tacos.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// IllegalArgumentException: There is no PasswordEncoder mapped for the id "null":
		// https://www.baeldung.com/spring-security-5-default-password-encoder
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		auth
			.inMemoryAuthentication()
				.withUser("buzz")
					.password(encoder.encode("infinity"))
					.authorities("ROLE_USER")
				.and()
				.withUser("woody")
					.password(encoder.encode("bullseye"))
					.authorities("ROLE_USER");
	}

}
