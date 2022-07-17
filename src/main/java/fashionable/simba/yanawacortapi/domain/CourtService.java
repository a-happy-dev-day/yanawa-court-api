package fashionable.simba.yanawacortapi.domain;

import fashionable.simba.yanawacortapi.exception.NoCourtDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourtService {

    private final CourtRepository courtRepository;
    private static final Logger log = LoggerFactory.getLogger(CourtService.class);

    public CourtService(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    public void saveCourts(List<Court> courts) {
        log.debug("Save court in Repository");
        try {
            courtRepository.saveAll(courts);
        } catch (Exception e) {
            log.debug("Failed to save court in Repository, Message is {}", e.getMessage());
            throw new IllegalStateException("저장에 실패했습니다.");
        }
    }

    public Court findCourt(UUID id) {
        return courtRepository.findById(id)
            .orElseThrow(() -> new NoCourtDataException("코트장 정보가 존재하지 않습니다."));
    }

    public List<Court> findCourts(String params) {
        return courtRepository.findCourtByNameContainingOrRegionContaining(params, params);
    }

    public List<Court> findCourts() {
        return courtRepository.findAll();
    }
}
