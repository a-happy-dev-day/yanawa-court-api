package fashionable.simba.yanawacortapi.ui;

import fashionable.simba.yanawacortapi.application.CourtApplicationService;
import fashionable.simba.yanawacortapi.domain.Court;
import fashionable.simba.yanawacortapi.dto.CourtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class CourtController {
    private static final Logger log = LoggerFactory.getLogger(CourtController.class);
    private final CourtApplicationService courtApplicationService;

    public CourtController(CourtApplicationService courtApplicationService) {
        this.courtApplicationService = courtApplicationService;
    }

    @PostMapping("/v1/api/courts")
    public ResponseEntity<Void> saveCourtList() {
        log.debug("Request to save list");
        courtApplicationService.saveCourts();
        return ResponseEntity.created(URI.create("/v1/api/courts")).build();
    }

    @GetMapping("/v1/api/courts")
    public ResponseEntity<List<CourtResponse>> getCourtsContainsParam(@RequestParam String param) {
        log.debug("Request to find list, Param is {}", param);
        return ResponseEntity.ok(courtApplicationService.findCourt(param).stream()
            .map(
                court -> new CourtResponse(court.getId(), court.getRegion() + court.getName())
            ).collect(Collectors.toList())
        );
    }

    @GetMapping("/v1/api/courts/list")
    public ResponseEntity<List<CourtResponse>> getCourts() {
        log.debug("Request to find list");
        return ResponseEntity.ok(courtApplicationService.findCourt().stream()
            .map(
                court -> new CourtResponse(court.getId(), court.getRegion() + court.getName())
            ).collect(Collectors.toList())
        );
    }

    @GetMapping("/v1/api/courts/{id}")
    public ResponseEntity<CourtResponse> getCourt(@PathVariable UUID id) {
        log.debug("Request to find list, Id is {}", id);
        Court court = courtApplicationService.findCourt(id);
        return ResponseEntity.ok(new CourtResponse(court.getId(), court.getRegion() + court.getName()));
    }
}
