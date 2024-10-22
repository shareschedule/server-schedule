package com.schedule.share.schedule.application.port.outbound

import com.schedule.share.schedule.domain.Schedule

interface ScheduleCommandPort {
    fun create(param: Schedule): Long

    fun update(param: Schedule)

    fun delete(id: Long)
}
