package com.schedule.share.schedule.application.service.schedule

import com.schedule.share.schedule.application.port.inbound.ScheduleQuery
import com.schedule.share.schedule.application.port.outbound.ScheduleQueryPort
import com.schedule.share.schedule.application.service.schedule.vo.ScheduleVO
import com.schedule.share.schedule.domain.mapper.toVO
import org.springframework.stereotype.Component

@Component
class ScheduleReader(
    private val scheduleQueryPort: ScheduleQueryPort,
) : ScheduleQuery {

    override fun get(
        id: Long
    ): ScheduleVO.Schedule = scheduleQueryPort.findById(id).toVO()

    override fun list(
        calendarId: Long
    ): List<ScheduleVO.Schedule> = scheduleQueryPort.findAllByCalendarId(calendarId)
        .map { it.toVO() }
}