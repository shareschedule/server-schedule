package com.schedule.share

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaAuditing
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity

@SpringBootApplication
@EnableJpaAuditing
@EnableWebSecurity
@EnableFeignClients
@EnableDiscoveryClient
@PropertySource("classpath:/env.yml")
class ShareScheduleApplication

fun main(args: Array<String>) {
    runApplication<ShareScheduleApplication>(*args)
}
