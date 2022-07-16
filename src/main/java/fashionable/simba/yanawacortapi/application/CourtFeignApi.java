package fashionable.simba.yanawacortapi.application;

import fashionable.simba.yanawacortapi.domain.Court;

import java.util.List;

public interface CourtFeignApi {
    List<Court> findCourts();

    boolean checkApi();
}
