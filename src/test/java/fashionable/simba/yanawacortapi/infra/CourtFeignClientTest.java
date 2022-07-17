package fashionable.simba.yanawacortapi.infra;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourtFeignClientTest {
    // TODO : CourtFeignClientTest 작성

    @Autowired
    private CourtFeignClient courtFeignClient;

    @Test
    @DisplayName("findCourts 테스트")
    void test1() {
        Assertions.assertDoesNotThrow(
            () -> courtFeignClient.findCourts(1, 10)
        );
    }

    @Test
    @DisplayName("checkApi 테스트")
    void test2() {
        Assertions.assertDoesNotThrow(
            () -> courtFeignClient.checkApi()
        );
    }
}
