package com.movie.community.config;

import com.movie.community.config.jwt.JwtAuthenticationFilter;
import com.movie.community.config.jwt.JwtAuthorizationFilter;
import com.movie.community.config.jwt.JwtProperties;
import com.movie.community.repository.MemberRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.filter.CorsFilter;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final JwtProperties jwtProperties;
	private final MemberRepository memberRepository;
	private final CorsFilter corsFilter;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(jwtProperties, authenticationManager());
		jwtAuthenticationFilter.setFilterProcessesUrl("/login");
		return jwtAuthenticationFilter;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.addFilter(corsFilter)
				.csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.formLogin().disable()
				.httpBasic().disable()
				.addFilter(jwtAuthenticationFilter())
				.addFilter(new JwtAuthorizationFilter(authenticationManager(), memberRepository, jwtProperties))
				.authorizeRequests()
				// login api
				.antMatchers("/login").permitAll()
				.antMatchers("/members/sign-up").permitAll() // sign-up
				// movie api security setting
				.antMatchers(HttpMethod.POST, "/movies").hasAuthority("admin")
				.antMatchers(HttpMethod.PATCH, "/movies/**").hasAuthority("admin")
				.antMatchers(HttpMethod.DELETE, "/movies/**").hasAuthority("admin")
				// holiday api
				.antMatchers(HttpMethod.POST, "/holiday").hasAuthority("admin")
				.antMatchers(HttpMethod.PATCH, "/holiday/**").hasAuthority("admin")
				.antMatchers(HttpMethod.DELETE, "/holiday/**").hasAuthority("admin")
//				.antMatchers("/reviews/**", "/movies/**", "/holiday/**").permitAll() // for test
				.anyRequest().authenticated();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**", "/js/**", "/images/**", "/static/**");
		web.ignoring().antMatchers("/", "/sign-up"); // home, 회원가입 화면은 모두에게 열려 있어야 함
		web.ignoring().antMatchers("/view/**"); // 모든 화면을 제공받을 수 있게 함.
	}
}
