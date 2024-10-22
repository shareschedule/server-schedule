package com.schedule.share.infra.rdb.repository

import com.schedule.share.infra.rdb.entity.ScheduleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ScheduleRepository : JpaRepository<ScheduleEntity, Long> {
    fun findAllByCalendarId(calendarId: Long): List<ScheduleEntity>
}