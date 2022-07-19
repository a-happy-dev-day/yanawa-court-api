package fashionable.simba.yanawacortapi.ui;

import fashionable.simba.yanawacortapi.application.CourtApplicationService;
import fashionable.simba.yanawacortapi.domain.Court;
import fashionable.simba.yanawacortapi.dto.CourtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@RestController
public class CourtController {
    private static final Logger log = LoggerFactory.getLogger(CourtController.class);
    private final CourtApplicationService courtApplicationService;
    private static final Pattern pattern = Pattern.compile("^[가-힣\\s]*$");

    public CourtController(CourtApplicationService courtApplicationService) {
        this.courtApplicationService = courtApplicationService;
    }

    @PostMapping("/v1/api/courts")
    public ResponseEntity<Void> saveCourtList() {
        log.info("Request to save list");
        courtApplicationService.saveCourts();
        return ResponseEntity.created(URI.create("/v1/api/courts")).build();
    }

    @GetMapping("/v1/api/courts")
    public ResponseEntity<List<CourtResponse>> getCourtsContainsParam(@RequestParam(required = false, defaultValue = "") String param) {
        if (param == null || param.isBlank()) {
            log.info("Request to find list");
            return ResponseEntity.ok(courtApplicationService.findCourts().stream()
                .map(
                    this::getCourtResponse
                ).collect(Collectors.toList())
            );
        }

        if (!pattern.matcher(param).matches()) {
            log.info("Failed to find list, Cause is not allowed param, Param is {}", param);
            throw new IllegalArgumentException("입력 값은 한글만 가능합니다.");
        }

        log.info("Request to find list, Param is {}", param);

        return ResponseEntity.ok(courtApplicationService.findCourts(param).stream()
            .map(
                this::getCourtResponse
            ).collect(Collectors.toList())
        );
    }

    @GetMapping("/v1/api/courts/{id}")
    public ResponseEntity<CourtResponse> getCourt(@PathVariable UUID id) {
        log.info("Request to find list, Id is {}", id);
        return ResponseEntity.ok(
            getCourtResponse(courtApplicationService.findCourt(id))
        );
    }

    private CourtResponse getCourtResponse(Court court) {
        return new CourtResponse(court.getId(), String.format("%s %s", court.getAreaName(), court.getPlaceName()), court.getImagePath());
    }

}
