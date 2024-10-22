package com.schedule.share.schedule.application.service.schedule

import com.schedule.share.schedule.application.port.inbound.ScheduleCommand
import com.schedule.share.schedule.application.port.outbound.ScheduleCommandPort
import com.schedule.share.schedule.application.service.schedule.vo.ScheduleVO
import com.schedule.share.schedule.domain.mapper.toDomain
import org.springframework.stereotype.Component

@Component
class ScheduleWriter(
    private val scheduleCommandPort: ScheduleCommandPort,
) : ScheduleCommand {
    override fun create(param: ScheduleVO.Save): Long {
        return scheduleCommandPort.create(param.toDomain())
    }

    override fun update(id: Long, param: ScheduleVO.Save) {
        scheduleCommandPort.update(param.toDomain(id))
    }

    override fun delete(id: Long) {
        scheduleCommandPort.delete(id)
    }
}