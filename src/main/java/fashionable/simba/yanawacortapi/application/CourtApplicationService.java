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
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private final CourtFeignApi tennisCourtOpenApi;
    private final CourtService courtService;

    public CourtApplicationService(CourtFeignApi tennisCourtOpenApi, CourtService courtService) {
        this.tennisCourtOpenApi = tennisCourtOpenApi;
        this.courtService = courtService;
    }

    public boolean saveCourts() {
        log.debug("Save Courts using CourtFeignClient");
        if (!tennisCourtOpenApi.checkApi()) {
            log.warn("Failed to check Api");
            throw new IllegalStateException();
        }
        return courtService.saveCourts(tennisCourtOpenApi.findCourts());
    }

    public Court findCourt(UUID id) {
        log.debug("Find Courts Id, Id is {}", id);
        return courtService.findCourt(id);
    }

    public List<Court> findCourt(String params) {
        log.debug("Find Courts Id, Param is {}", params);
        return courtService.findCourt(params);
    }
}
