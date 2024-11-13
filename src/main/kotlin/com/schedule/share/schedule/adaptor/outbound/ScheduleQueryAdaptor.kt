package com.schedule.share.schedule.adaptor.outbound

import com.schedule.share.schedule.application.port.outbound.ScheduleQueryPort
import com.schedule.share.schedule.domain.Schedule
import com.schedule.share.schedule.domain.mapper.toDomain
import com.schedule.share.common.exception.Common404Exception
import com.schedule.share.infra.rdb.repository.ScheduleRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class ScheduleQueryAdaptor(
    private val scheduleRepository: ScheduleRepository
) : ScheduleQueryPort {

    @Transactional(readOnly = true)
    override fun findAll(): List<Schedule> {
        return scheduleRepository.findAll()
            .map { it.toDomain() }
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): Schedule {
        return scheduleRepository.findById(id)
            .orElseThrow { throw Common404Exception() }
            .toDomain()
    }

    @Transactional(readOnly = true)
    override fun findAllByCalendarId(calendarId: Long): List<Schedule> {
        return scheduleRepository.findAllByCalendarId(calendarId)
            .map { it.toDomain() }
    }
}
