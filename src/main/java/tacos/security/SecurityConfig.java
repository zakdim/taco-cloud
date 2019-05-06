package tacos.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery(
					"select username, password, enabled from Users " +
					"where username=?")
			.authoritiesByUsernameQuery(
					"select username, authority from UserAuthorities " +
					"where username=?")
			.passwordEncoder(new StandardPasswordEncoder("53cr3t"));
		
	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.jdbcAuthentication()
//			.dataSource(dataSource)
//			.usersByUsernameQuery(
//					"select username, password, enabled from Users " +
//					"where username=?")
//			.authoritiesByUsernameQuery(
//					"select username, authority from UserAuthorities " +
//					"where username=?");
//		
//	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.jdbcAuthentication()
//			.dataSource(dataSource);
//	}
	
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		
//		// IllegalArgumentException: There is no PasswordEncoder mapped for the id "null":
//		// https://www.baeldung.com/spring-security-5-default-password-encoder
//		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
//		
//		auth
//			.inMemoryAuthentication()
//				.withUser("buzz")
//					.password(encoder.encode("infinity"))
//					.authorities("ROLE_USER")
//				.and()
//				.withUser("woody")
//					.password(encoder.encode("bullseye"))
//					.authorities("ROLE_USER");
//	}

}
