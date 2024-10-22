package com.schedule.share.schedule.adaptor.outbound

import com.schedule.share.schedule.application.port.outbound.ScheduleQueryPort
import com.schedule.share.schedule.domain.Schedule
import com.schedule.share.schedule.domain.mapper.toDomain
import com.schedule.share.common.exception.Common404Exception
import com.schedule.share.infra.rdb.repository.ScheduleRepository
import org.springframework.stereotype.Component

@Component
class ScheduleQueryAdaptor(
    private val scheduleRepository: ScheduleRepository
) : ScheduleQueryPort {
    override fun findAll(): List<Schedule> {
        return scheduleRepository.findAll()
            .map { it.toDomain() }
    }

    override fun findById(id: Long): Schedule {
        return scheduleRepository.findById(id)
            .orElseThrow { throw Common404Exception() }
            .toDomain()
    }
}
