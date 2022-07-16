package fashionable.simba.yanawacortapi.application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawacortapi.domain.Court;
import fashionable.simba.yanawacortapi.infra.CourtFeignClient;
import feign.Response;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class CourtFeignApiTranslator implements CourtFeignApi {
    private static final String LIST_PUBLIC_RESERVATION_SPORT = "ListPublicReservationSport";
    private static final Logger log = LoggerFactory.getLogger(CourtFeignApiTranslator.class);
    private static final int MIN_SIZE = 1;
    private static final int MAX_SIZE = 400;
    private final CourtFeignClient courtFeignClient;
    private final ObjectMapper objectMapper;

    public CourtFeignApiTranslator(CourtFeignClient courtFeignClient, ObjectMapper objectMapper) {
        this.courtFeignClient = courtFeignClient;
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Court> findCourts() {
        log.debug("Find api using CourtFeignClient");
        try (Response response = courtFeignClient.findCourts(MIN_SIZE, MAX_SIZE)) {
            JSONObject jsonObject = new JSONObject(response.body().toString())
                .getJSONObject(LIST_PUBLIC_RESERVATION_SPORT);
            return objectMapper.readValue(
                jsonObject.getJSONArray("row").toString(), new TypeReference<>() {}
            );
        } catch (IOException e) {
            log.warn("Failed to find list, message is {}", e.getMessage());
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean checkApi() {
        log.debug("Check api CourtFeignClient");
        return courtFeignClient.checkApi().status() == HttpStatus.OK.value();
    }
}
