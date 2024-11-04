package com.schedule.share.schedule.adaptor.inbound.api

import com.schedule.share.schedule.adaptor.inbound.api.dto.ScheduleRequestDTO
import com.schedule.share.schedule.adaptor.inbound.api.dto.ScheduleResponseDTO
import com.schedule.share.schedule.adaptor.inbound.api.mapper.toResponse
import com.schedule.share.schedule.adaptor.inbound.api.mapper.toVO
import com.schedule.share.schedule.application.port.inbound.ScheduleCommand
import com.schedule.share.schedule.application.port.inbound.ScheduleQuery
import com.schedule.share.schedule.application.service.schedule.ScheduleService
import com.schedule.share.common.model.ResponseModel
import com.schedule.share.schedule.application.service.schedule.ScheduleProducerService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/schedules")
class ScheduleApi(
    private val service: ScheduleProducerService, //TODO: 인터페이스로 교체하기
    private val scheduleQuery: ScheduleQuery,
    private val scheduleCommand: ScheduleCommand,
    private val scheduleService: ScheduleService,
) {
    @Operation(summary = "스케줄 단건 조회 API", description = "스케줄 단건 조회 API")
    @GetMapping("/{id}")
    fun get(
        @PathVariable id: Long,
    ): ResponseModel<ScheduleResponseDTO.Response> = ResponseModel.of(
        data = scheduleQuery.get(id = id).toResponse()
    )

    @Operation(summary = "스케쥴 조회 API", description = "스토어 조회 API")
    @GetMapping
    fun getList(
        @RequestParam(defaultValue = "0") limit: Int,
        @RequestParam(defaultValue = "0") offset: Int,
        @RequestParam calendarId: Long,
    ): ResponseModel<List<ScheduleResponseDTO.Response>> {
        val response = scheduleQuery.list(calendarId).map { it.toResponse() }

        return ResponseModel.of(
            data = response,
            total = response.size.toLong(),
            offset = offset,
            limit = limit,
        )
    }

    @Operation(summary = "스케쥴 등록 API", description = "스토어 등록 API")
    @PostMapping
    fun post(
        @RequestHeader("X-UserId") userId: Long,
        @RequestParam calendarId: Long,
        @RequestBody body: ScheduleRequestDTO.Schedule,
    ): ResponseModel<Long>  = ResponseModel(
            data = scheduleCommand.create(
                param = body.toVO(
                    userId = userId,
                    calendarId = calendarId,
                )
            )
        )

    //TODO FATCH로 변경하기
    @Operation(summary = "스케쥴 수정 API", description = "스토어 수정 API")
    @PutMapping("/{calendarId}/{id}")
    fun put(
        @RequestHeader("X-UserId") userId: Long,
        @PathVariable calendarId: Long,
        @PathVariable id: Long,
        @RequestBody body: ScheduleRequestDTO.Schedule,
    ) {
        scheduleCommand.update(
            id = id,
            param = body.toVO(
                userId = userId,
                calendarId = calendarId,
            )
        )
    }

    @Operation(summary = "스케쥴 삭제 API", description = "스토어 삭제 API")
    @DeleteMapping("/{id}")
    fun put(
        @PathVariable id: Long,
    ) {
        scheduleCommand.delete(id = id)
    }
}
