package com.schedule.share.schedule.application.port.outbound.producer.schedule

import com.schedule.share.infra.kafka.event.CreateScheduleEvent

interface ScheduleEventPort {
    fun produceCreatedScheduleEvent(event: CreateScheduleEvent)
}