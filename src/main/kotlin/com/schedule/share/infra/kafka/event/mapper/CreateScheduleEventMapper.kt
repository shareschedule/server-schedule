package com.schedule.share.infra.kafka.event.mapper

import com.schedule.share.infra.kafka.event.CreateScheduleEvent
import com.schedule.share.schedule.application.service.schedule.vo.ScheduleVO

fun ScheduleVO.Save.toEvent(
): CreateScheduleEvent = CreateScheduleEvent(
    calendarId = calendarId,
    userId = userId,
    title = title,
    isAllday = isAllday,
    startDatetime = startDatetime,
    endDatetime = endDatetime,
    content = content,
    location = location,
)