package com.schedule.share.schedule.domain

import java.time.LocalDateTime

class Schedule(
    val id: Long? = null,
    val calendarId: Long,
    val userId: Long,
    val title: String,
    val isAllday: Boolean = false,
    val startDatetime: LocalDateTime,
    val endDatetime: LocalDateTime,
    val content: String? = null,
    val location: String? = null,
    val createdAt: LocalDateTime? = null,
    val modifiedAt: LocalDateTime? = null,
    val isDeleted: Boolean = false
)