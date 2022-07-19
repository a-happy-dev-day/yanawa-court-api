package fashionable.simba.yanawacortapi.application;

import fashionable.simba.yanawacortapi.domain.Court;
import fashionable.simba.yanawacortapi.domain.CourtService;
import fashionable.simba.yanawacortapi.utils.LogConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static fashionable.simba.yanawacortapi.utils.LogConfig.*;

@Service
public class CourtApplicationService {
    private static final Logger log = LoggerFactory.getLogger(CourtApplicationService.class);
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 400;
    private final CourtFeignApiTranslator courtFeignApiTranslator;
    private final CourtService courtService;
    private final CourtFeignApi courtFeignApi;

    public CourtApplicationService(CourtFeignApiTranslator courtFeignApiTranslator, CourtService courtService, CourtFeignApi courtFeignApi) {
        this.courtFeignApiTranslator = courtFeignApiTranslator;
        this.courtService = courtService;
        this.courtFeignApi = courtFeignApi;
    }

    public List<Court> saveCourts() {
        log.debug("[{}] Check api CourtFeignClient", MDC.get(KEY));
        if (!courtFeignApiTranslator.isStatusOk(courtFeignApi.checkApi())) {
            log.warn("[{}] Failed to check Api", MDC.get(KEY));
            throw new IllegalStateException();
        }
        log.debug("[{}] Save Courts using CourtFeignClient", MDC.get(KEY));
        List<Court> courts = courtFeignApiTranslator.getCourts(courtFeignApi.findCourts(MIN_SIZE, MAX_SIZE));
        return courtService.saveCourts(courts);
    }

    public Court findCourt(UUID id) {
        log.debug("[{}] Find courts by id, Id is {}", MDC.get(KEY), id);
        return courtService.findCourt(id);
    }

    public List<Court> findCourts(String params) {
        log.debug("[{}] Find courts by param, Param is {}", MDC.get(KEY), params);
        return courtService.findCourts(params);
    }

    public List<Court> findCourts() {
        log.debug("[{}] Find Courts", MDC.get(KEY));
        return courtService.findCourts();
    }
}
