package com.schedule.share.schedule.application.service.schedule

import com.schedule.share.infra.kafka.event.CreateScheduleEvent
import com.schedule.share.infra.kafka.event.mapper.toEvent
import com.schedule.share.schedule.application.port.outbound.producer.schedule.ScheduleEventPort
import com.schedule.share.schedule.application.service.schedule.vo.ScheduleVO
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class ScheduleProducerService(
    private val scheduleEventPort: ScheduleEventPort,
    private val kafkaTemplate: KafkaTemplate<String, CreateScheduleEvent>,
){

    fun createSchedule(
        param: ScheduleVO.Save
    ) {
        scheduleEventPort.produceCreatedScheduleEvent(
            param.toEvent()
    )}
}