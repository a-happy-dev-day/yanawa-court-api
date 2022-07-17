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
    private final CourtFeignApiTranslator courtFeignApiTranslator;
    private final CourtService courtService;

    public CourtApplicationService(CourtFeignApiTranslator courtFeignApiTranslator, CourtService courtService) {
        this.courtFeignApiTranslator = courtFeignApiTranslator;
        this.courtService = courtService;
    }

    public void saveCourts() {
        log.debug("Save Courts using CourtFeignClient");
        if (!courtFeignApiTranslator.checkApi()) {
            log.warn("Failed to check Api");
            throw new IllegalStateException();
        }
        courtService.saveCourts(courtFeignApiTranslator.findCourts());
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
