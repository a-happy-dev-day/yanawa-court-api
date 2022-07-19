package fashionable.simba.yanawacortapi.domain;

import fashionable.simba.yanawacortapi.exception.NoCourtDataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static fashionable.simba.yanawacortapi.utils.LogConfig.KEY;

@Service
public class CourtService {

    private final CourtRepository courtRepository;
    private static final Logger log = LoggerFactory.getLogger(CourtService.class);

    public CourtService(CourtRepository courtRepository) {
        this.courtRepository = courtRepository;
    }

    @Transactional
    public List<Court> saveCourts(List<Court> courts) {
        if (courtRepository.count() > 0L) {
            log.debug("[{}] Delete all in Repository", MDC.get(KEY));
            courtRepository.deleteAllInBatch();
        }

        log.debug("[{}] Save court in Repository", MDC.get(KEY));
        try {
            return courtRepository.saveAll(courts);
        } catch (Exception e) {
            log.debug("[{}] Failed to save court in Repository, Message is {}", MDC.get(KEY), e.getMessage());
            throw new IllegalStateException("저장에 실패했습니다.");
        }
    }

    @Transactional(readOnly = true)
    public Court findCourt(UUID id) {
        log.debug("[{}] Find court by id in repository, Id is {}", MDC.get(KEY), id);
        return courtRepository.findById(id)
            .orElseThrow(() -> {
                log.debug("[{}] Failed to find court in Repository, Id is {}", MDC.get(KEY), id);
                throw new NoCourtDataException("코트장 정보가 존재하지 않습니다.");
            });
    }

    @Transactional(readOnly = true)
    public List<Court> findCourts(String params) {
        log.debug("[{}] Find courts by param in repository, Para is {}", MDC.get(KEY), params);
        return courtRepository.findCourtByAreaNameContainingOrPlaceNameContainingOrderByAreaNameAsc(params, params);
    }

    @Transactional(readOnly = true)
    public List<Court> findCourts() {
        log.debug("[{}] Find courts in repository", MDC.get(KEY));
        return courtRepository.findAll();
    }
}
