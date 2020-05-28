package com.oauth2.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.web.access.ExceptionTranslationFilter;

import java.util.Arrays;


@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private OAuth2ClientContextFilter oauth2ClientContextFilter;

    @Autowired
    private OAuth2ClientContext oauth2ClientContext;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http
                .authorizeRequests().antMatchers("/login").anonymous().and()
                .authorizeRequests().anyRequest().authenticated().and()
                .httpBasic().and()
                .addFilterAfter(oauth2ClientContextFilter, ExceptionTranslationFilter.class);
        // @formatter:on
    }

//  org.springframework.beans.factory.NoUniqueBeanDefinitionException: No qualifying bean of type [org.springframework.security.oauth2.client.OAuth2RestOperations] is defined: expected single matching bean but found 2: restTemplate,userInfoRestTemplate
//  @Bean
//  public OAuth2RestOperations restTemplate() {
//      return new OAuth2RestTemplate(bnetResource(), oauth2ClientContext);
//  }

    @Bean
    public OAuth2ProtectedResourceDetails bnetResource() {
        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
        resource.setId("bnet");
        resource.setClientId("acbccc4f-ef9b-4a4b-a7ba-dabd0c26a419");
        resource.setClientSecret("4*ZxHFnQvYgEktFremFOTffYeQrvM@");
        resource.setAccessTokenUri("https://acessocidadao.es.gov.br/is/connect/token");
        resource.setUserAuthorizationUri("https://acessocidadao.es.gov.br/is/connect/authorize");
        resource.setScope(Arrays.asList("api-sistemateste"));
        return resource;
    }

    @Bean
    public FilterRegistrationBean oauth2ClientFilterRegistration(
            OAuth2ClientContextFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(filter);
        registration.setOrder(-100);
        return registration;
    }
}
