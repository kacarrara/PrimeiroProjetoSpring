package br.com.primeiroprojetospring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.primeiroprojetospring.service.CurrentUserDetailsService;

@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String ADMIN = "ADMIN";
	private static final String USER = "USER";

	private static final String INDEX_PATH_STRING = "/principal";
	private static final String[] PUBLIC_MATCHERS = { INDEX_PATH_STRING, "/carro/**" };
	private static final String[] PUBLIC_MATCHERS_GET = { "/chave/**", "/login" };

	private static final String[] ADMIN_MATCHERS = { "/h2-console/**", "/carro/**" };
	private static final String[] USER_MATCHERS = { "/fabricante/**" };
	private static final String LOGOUT_PATH_STRING = "/logout";

	@Autowired
	private CurrentUserDetailsService userDetailsService;

//	protected void configure(HttpSecurity http) throws Exception {
//
//		http.authorizeRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
//				.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
//				.antMatchers(ADMIN_MATCHERS).permitAll()
//				.antMatchers(ADMIN_MATCHERS).hasRole(ADMIN)
//				.antMatchers(USER_MATCHERS).permitAll()
//				.antMatchers(USER_MATCHERS).hasRole(USER)
//				.antMatchers(USER_MATCHERS).hasAnyAuthority(Permissao.INSERT)
//				.anyRequest().authenticated().and().formLogin().defaultSuccessUrl(INDEX_PATH_STRING).permitAll().and().logout()
//				.logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT_PATH_STRING));
//		
//		http.httpBasic();
//		http.csrf().disable();
//		http.headers().frameOptions().disable();
//
//	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeHttpRequests().antMatchers(PUBLIC_MATCHERS).permitAll()
				.antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll().antMatchers(ADMIN_MATCHERS).hasRole(ADMIN)
				.and().addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), userDetailsService));
	}

	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/*", "/js/*");
	}

	public void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());

	}
}