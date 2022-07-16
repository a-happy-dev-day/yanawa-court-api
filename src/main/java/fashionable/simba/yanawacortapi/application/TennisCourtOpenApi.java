package fashionable.simba.yanawacortapi.application;

import fashionable.simba.yanawacortapi.domain.Court;

import java.util.List;

public interface TennisCourtOpenApi {
    List<Court> findCourts(int minNum, int maxNum);

    boolean checkApi();
}
