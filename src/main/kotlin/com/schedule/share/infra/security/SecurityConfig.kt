package com.schedule.share.infra.security

import com.netflix.discovery.EurekaClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.core.Authentication
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.RequestAuthorizationContext
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import org.springframework.security.web.util.matcher.IpAddressMatcher
import java.util.function.Supplier

@Profile("dev", "local")
@Configuration
class SecurityConfig(
    private val discoveryClient: EurekaClient,
) {
    @Bean
    fun filterChain(http: HttpSecurity) : SecurityFilterChain {
        permitSwagger(http)
        setHttpConfig(http)
        return http.build()
    }

    private fun permitSwagger(httpSecurity: HttpSecurity) {
        httpSecurity.authorizeHttpRequests { auth ->
            auth
                .requestMatchers(AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                .requestMatchers(AntPathRequestMatcher("/v3/api-docs/**")).permitAll()
        }
    }

    private fun setHttpConfig(httpSecurity: HttpSecurity) {
        val hasIpAddress = IpAddressMatcher(
            discoveryClient.getNextServerFromEureka("GATEWAY", false).ipAddr
        )

        httpSecurity
            .csrf { csrf: CsrfConfigurer<HttpSecurity> -> csrf.disable() }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers(AntPathRequestMatcher("/**"))
                    .access((AuthorizationManager { authentication: Supplier<Authentication?>?, context: RequestAuthorizationContext ->
                        AuthorizationDecision(
                            hasIpAddress.matches(context.request)
                        )}
                    ))
                    .anyRequest().denyAll()
            }
    }
}