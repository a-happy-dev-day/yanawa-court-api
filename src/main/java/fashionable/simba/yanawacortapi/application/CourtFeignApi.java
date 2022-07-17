package fashionable.simba.yanawacortapi.application;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface CourtFeignApi {
    ResponseEntity<Void> checkApi();

    ResponseEntity<Map<String, Object>> findCourts(int minNum, int maxNum);
}
