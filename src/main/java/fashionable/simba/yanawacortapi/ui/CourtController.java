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
        log.debug("Request to save list");
        courtApplicationService.saveCourts();
        return ResponseEntity.created(URI.create("/v1/api/courts")).build();
    }

    @GetMapping("/v1/api/courts")
    public ResponseEntity<List<CourtResponse>> getCourtsContainsParam(@RequestParam(required = false) String param) {
        if (param == null || param.isBlank()) {
            throw new IllegalStateException("공백이 들어올 수는 없습니다.");
        }

        if (!pattern.matcher(param).matches()) {
            throw new IllegalArgumentException("입력 값은 한글만 가능합니다.");
        }

        log.debug("Request to find list, Param is {}", param);

        return ResponseEntity.ok(courtApplicationService.findCourt(param).stream()
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
