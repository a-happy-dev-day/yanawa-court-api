package fashionable.simba.yanawacortapi.application;

import fashionable.simba.yanawacortapi.domain.Court;
import fashionable.simba.yanawacortapi.domain.CourtService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourtApplicationService {
    private final CourtFeignApi tennisCourtOpenApi;
    private final CourtService courtService;

    public CourtApplicationService(CourtFeignApi tennisCourtOpenApi, CourtService courtService) {
        this.tennisCourtOpenApi = tennisCourtOpenApi;
        this.courtService = courtService;
    }

    public boolean saveCourts() {
        if (!tennisCourtOpenApi.checkApi()) {
            throw new IllegalStateException();
        }
        List<Court> courts = tennisCourtOpenApi.findCourts();
        return courtService.saveCourts(courts);
    }

    public Court findCourt(UUID id) {
        return courtService.findCourt(id);
    }

    public Court findCourt(String params) {
        return courtService.findCourt(params);
    }
}
