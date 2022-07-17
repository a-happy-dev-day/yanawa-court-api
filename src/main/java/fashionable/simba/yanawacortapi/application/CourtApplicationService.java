package fashionable.simba.yanawacortapi.application;

import fashionable.simba.yanawacortapi.domain.Court;
import fashionable.simba.yanawacortapi.domain.CourtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public void saveCourts() {
        log.debug("Save Courts using CourtFeignClient");
        log.debug("Check api CourtFeignClient");
        if (!courtFeignApiTranslator.isStatusOk(courtFeignApi.checkApi())) {
            log.warn("Failed to check Api");
            throw new IllegalStateException();
        }
        List<Court> courts = courtFeignApiTranslator.getCourts(courtFeignApi.findCourts(MIN_SIZE, MAX_SIZE));
        courtService.saveCourts(courts);
    }

    public Court findCourt(UUID id) {
        log.debug("Find Courts Id, Id is {}", id);
        return courtService.findCourt(id);
    }

    public List<Court> findCourts(String params) {
        log.debug("Find Courts Id, Param is {}", params);
        return courtService.findCourts(params);
    }

    public List<Court> findCourts() {
        return courtService.findCourts();
    }
}
