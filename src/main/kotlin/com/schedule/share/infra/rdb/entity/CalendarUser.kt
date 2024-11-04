package com.schedule.share.infra.rdb.entity

import com.schedule.share.common.utils.DateUtil
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@Entity
@Table(catalog = "schedule", name = "calendar_user")
@EntityListeners(AuditingEntityListener::class)
open class CalendarUser (
    id: Long? = null,
    userId: Long,
    calendarId: Long,
    createdAt: LocalDateTime? = DateUtil.now()
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open var id: Long? = id
        protected set

    open var userId: Long = userId
        protected set

    open var calendarId: Long = calendarId
        protected set

    @CreatedDate
    open var createdAt: LocalDateTime? = createdAt
        protected set
}