package com.schedule.share.schedule.application.service

interface DomainQuery<T> {

    fun get(id: Long): T

    fun list(calendarId: Long): List<T>
}
