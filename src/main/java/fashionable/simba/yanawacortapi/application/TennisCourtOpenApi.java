package fashionable.simba.yanawacortapi.application;

import java.util.List;

public interface TennisCourtOpenApi {
    List<String> findCourts();

    boolean checkApi();
}
