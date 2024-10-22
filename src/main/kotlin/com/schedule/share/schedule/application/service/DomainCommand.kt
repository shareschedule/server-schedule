package com.schedule.share.schedule.application.service

interface DomainCommand<R> {

    fun create(param: R): Long

    fun update(id: Long, param: R)

    fun delete(id: Long)
}