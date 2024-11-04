package com.schedule.share.infra.openfeign

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "CALENDARROLEMANAGER")
interface CalendarRoleFeignClient {

    @GetMapping("/calendar-user")
    fun getUserRoleInCalendar(
        @RequestParam userId:Long,
        @RequestParam calendarId: Long,
    ): Long
}