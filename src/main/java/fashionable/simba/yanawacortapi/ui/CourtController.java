package fashionable.simba.yanawacortapi.ui;

import fashionable.simba.yanawacortapi.application.CourtApplicationService;
import fashionable.simba.yanawacortapi.dto.CourtResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

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
        return ResponseEntity.created(URI.create("/v1/api/courts")).build();
    }

    @GetMapping("/v1/api/courts")
    public ResponseEntity<List<CourtResponse>> getListContainsName(@RequestParam String param) {
        log.debug("Request to find list param is {}", param);

        List<CourtResponse> courtResponses = new ArrayList<>();
        courtResponses.add(new CourtResponse(null, "성동구"));
        courtResponses.add(new CourtResponse(null, "응봉공원"));
        courtResponses.add(new CourtResponse(null, "성동구 응봉공원"));
        return ResponseEntity.ok(courtResponses);
    }
}
