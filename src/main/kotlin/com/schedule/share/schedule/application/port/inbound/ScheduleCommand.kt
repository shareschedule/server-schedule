package com.schedule.share.schedule.application.port.inbound

import com.schedule.share.schedule.application.service.DomainCommand
import com.schedule.share.schedule.application.service.schedule.vo.ScheduleVO

interface ScheduleCommand : DomainCommand<ScheduleVO.Save>
