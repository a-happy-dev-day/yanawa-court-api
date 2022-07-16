package fashionable.simba.yanawacortapi.infra;

import fashionable.simba.yanawacortapi.application.TennisCourtOpenApi;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FakeTennisCourtOpenApi implements TennisCourtOpenApi {
    @Override
    public List<String> findCourts() {
        return null;
    }

    @Override
    public boolean checkApi() {
        return false;
    }
}
