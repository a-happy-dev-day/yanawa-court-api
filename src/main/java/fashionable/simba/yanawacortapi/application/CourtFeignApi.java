package fashionable.simba.yanawacortapi.application;

import com.fasterxml.jackson.core.JsonProcessingException;
import fashionable.simba.yanawacortapi.domain.Court;

import java.io.IOException;
import java.util.List;

public interface CourtFeignApi {
    List<Court> findCourts();
    boolean checkApi();
}
