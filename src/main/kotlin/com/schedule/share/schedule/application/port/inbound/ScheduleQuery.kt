package com.schedule.share.schedule.application.port.inbound

import com.schedule.share.schedule.application.service.DomainQuery
import com.schedule.share.schedule.application.service.schedule.vo.ScheduleVO

interface ScheduleQuery : DomainQuery<ScheduleVO.Schedule>
