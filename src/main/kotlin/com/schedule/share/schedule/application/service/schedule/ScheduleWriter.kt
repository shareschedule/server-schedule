package com.schedule.share.schedule.application.service.schedule

import com.schedule.share.infra.openfeign.CalendarRoleFeignClient
import com.schedule.share.schedule.application.port.inbound.ScheduleCommand
import com.schedule.share.schedule.application.port.outbound.ScheduleCommandPort
import com.schedule.share.schedule.application.service.schedule.vo.ScheduleVO
import com.schedule.share.schedule.domain.mapper.toDomain
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException

@Component
class ScheduleWriter(
    private val scheduleCommandPort: ScheduleCommandPort,
    private val calendarRoleFeignClient: CalendarRoleFeignClient,
) : ScheduleCommand {
    override fun create(param: ScheduleVO.Save): Long {
        val userRoleLevelInCalendar = calendarRoleFeignClient.getUserRoleInCalendar(
            userId = param.userId,
            calendarId = param.calendarId,
        )

        //TODO: userRoleLevel에 따라 분기처리 고민 후 적용하기
        return scheduleCommandPort.create(param.toDomain())
    }

    override fun update(id: Long, param: ScheduleVO.Save) {
        scheduleCommandPort.update(param.toDomain(id))
    }

    override fun delete(id: Long) {
        scheduleCommandPort.delete(id)
    }
}