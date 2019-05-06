package tacos.security;

import javax.naming.ldap.LdapContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/design", "/orders")
					.access("hasRole('ROLE_USER')")
				.antMatchers("/", "/**").access("permitAll")
				
				.and()
					.formLogin()
						.loginPage("/login")
//						.loginProcessingUrl("/authenticate")
//						.usernameParameter("user")
//						.passwordParameter("pwd")
//						.defaultSuccessUrl("/design")
				
				.and()
					.logout()
						.logoutSuccessUrl("/")
						
				// Make H2-Console non-secured; for debug purposes
				.and()
					.csrf()
						.ignoringAntMatchers("/h2-console/**")
						
				// Allow pages to be loaded in frames from the same origin; needed for H2-Console
				.and()  
					.headers()
						.frameOptions()
					    	.sameOrigin()
				;
				
	}
	
//	protected void configure(HttpSecurity http) throws Exception {
//		http
//			.authorizeRequests()
//				.antMatchers("/design", "/orders")
//					.access("hasRole('ROLE_USER') && " +
//							"T(java.util.Calendar).getInstance().get(" +
//							"T(java.util.Calendar).DAY_OF_WEEK == " +
//							"T(java.util.Calendar).TUESDAY")
//				.antMatchers("/", "/**").access("permitAll");
//				
//	}

	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder encoder() {
		return new StandardPasswordEncoder("53cr3t");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(encoder());
	}
	
	
//	// embadded LDAP server
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.ldapAuthentication()
//			.userSearchBase("ou=people")
//			.userSearchFilter("(uid={0})")
//			.groupSearchBase("ou=groups")
//			.groupSearchFilter("member={0}")
//			.passwordCompare()
//			.passwordEncoder(new BCryptPasswordEncoder())
//			.passwordAttribute("passcode")
//			.contextSource()
//				.root("dc=tacocloud,dc=com")
//				.ldif("classpath:users.ldif");
//	}
	
//	// remote LDAP server
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.ldapAuthentication()
//			.userSearchBase("ou=people")
//			.userSearchFilter("(uid={0})")
//			.groupSearchBase("ou=groups")
//			.groupSearchFilter("member={0}")
//			.passwordCompare()
//			.passwordEncoder(new BCryptPasswordEncoder())
//			.passwordAttribute("passcode")
//			.contextSource()
//				.url("ldap://tacocloud.com:389/dc=tacocloud,dc=com");
//	}
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//			.ldapAuthentication()
//				.userSearchBase("ou=people")
//				.userSearchFilter("(uid={0}")
//				.groupSearchBase("ou=groups")
//				.groupSearchFilter("member={0}")
//				.passwordCompare()
//				.passwordEncoder(new BCryptPasswordEncoder())
//				.passwordAttribute("passcode");
//		
//	}

//	@Autowired
//	DataSource dataSource;
//	
//	@SuppressWarnings("deprecation")
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
//					"where username=?")
//			.passwordEncoder(new StandardPasswordEncoder("53cr3t"));
//		
//	}
	
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
