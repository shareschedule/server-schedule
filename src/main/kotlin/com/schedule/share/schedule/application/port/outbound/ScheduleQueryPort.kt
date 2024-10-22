package com.schedule.share.schedule.application.port.outbound

import com.schedule.share.schedule.domain.Schedule

interface ScheduleQueryPort {
    fun findAll(): List<Schedule>

    fun findById(id: Long): Schedule
}
