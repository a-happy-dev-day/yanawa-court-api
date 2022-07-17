package fashionable.simba.yanawacortapi.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawacortapi.domain.Court;
import fashionable.simba.yanawacortapi.infra.ApiResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CourtFeignApiTranslator {
    private static final String LIST_PUBLIC_RESERVATION_SPORT = "ListPublicReservationSport";
    private static final Logger log = LoggerFactory.getLogger(CourtFeignApiTranslator.class);
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 400;
    private static final String ROW = "row";
    private final CourtFeignApi courtFeignApi;
    private final ObjectMapper objectMapper;

    public CourtFeignApiTranslator(CourtFeignApi courtFeignApi, ObjectMapper objectMapper) {
        this.courtFeignApi = courtFeignApi;
        this.objectMapper = objectMapper;
    }

    public List<Court> findCourts() {
        ResponseEntity<Map<String, Object>> response = courtFeignApi.findCourts(MIN_SIZE, MAX_SIZE);
        return getCourts(response);
    }

    public boolean checkApi() {
        log.debug("Check api CourtFeignClient");
        ResponseEntity<Void> response = courtFeignApi.checkApi();
        return isStatusOk(response);
    }

    public boolean isStatusOk(ResponseEntity<Void> response) {
        return HttpStatus.OK == response.getStatusCode();
    }

    public List<Court> getCourts(ResponseEntity<Map<String, Object>> response) {
        log.debug("Find api using CourtFeignClient");
        try {
            JSONObject jsonObject = new JSONObject((Map<String, Object>) response.getBody().get(LIST_PUBLIC_RESERVATION_SPORT));
            List<ApiResponse> apiResponses = objectMapper.readValue(
                jsonObject.getJSONArray(ROW).toString(),
                new TypeReference<>() {
                }
            );
            return apiResponses.stream().map(apiResponse -> new Court(null, apiResponse.getAreaName(), apiResponse.getPlaceName(), apiResponse.getImagePath())).collect(Collectors.toList());
        } catch (Exception e) {
            log.warn("Failed to find list, message is {}", e.getMessage());
            throw new IllegalArgumentException();
        }
    }
}
