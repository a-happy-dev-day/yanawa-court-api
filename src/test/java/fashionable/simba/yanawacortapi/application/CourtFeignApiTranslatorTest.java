package fashionable.simba.yanawacortapi.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawacortapi.infra.CourtFeignClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class CourtFeignApiTranslatorTest {
    CourtFeignApiTranslator courtFeignApiTranslator;
    private ObjectMapper objectMapper = new ObjectMapper();
    @Mock
    private CourtFeignClient courtFeignClient;

    @BeforeEach
    void setUp() {
        courtFeignApiTranslator = new CourtFeignApiTranslator(courtFeignClient, objectMapper);
    }

    // TODO : CourtFeignApiTranslatorTest 작성하기

}
