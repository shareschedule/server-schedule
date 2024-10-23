package com.schedule.share.infra.kafka.event

import java.time.LocalDateTime

data class CreateScheduleEvent(
    val calendarId: Long,
    val userId: Long,
    val title: String,
    val isAllday: Boolean = false,
    val startDatetime: LocalDateTime,
    val endDatetime: LocalDateTime,
    val content: String? = null,
    val location: String? = null,
)
