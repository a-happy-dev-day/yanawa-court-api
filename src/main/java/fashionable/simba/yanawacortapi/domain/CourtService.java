package fashionable.simba.yanawacortapi.domain;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CourtService {
    public boolean saveCourts(List<Court> process) {
        return true;
    }

    public Court findCourt(UUID id) {
        return null;
    }

    public List<Court> findCourt(String params) {
        return null;
    }
}
