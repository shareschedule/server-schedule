package com.schedule.share.infra.kafka.producer

import com.schedule.share.infra.kafka.event.CreateScheduleEvent
import com.schedule.share.schedule.application.port.outbound.producer.schedule.ScheduleEventPort
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Component
import java.util.function.BiConsumer

@Component
class ScheduleEventProducer (
    private val kafkaTemplate: KafkaTemplate<String, CreateScheduleEvent>,
) : ScheduleEventPort{
    private val TOPIC_CREATE_SCHDULE: String = "create-schedule"

    override fun produceCreatedScheduleEvent(event: CreateScheduleEvent) {
        val send = kafkaTemplate.send(TOPIC_CREATE_SCHDULE, event)
    }
}
