package fashionable.simba.yanawacortapi.application;

import fashionable.simba.yanawacortapi.domain.Court;
import fashionable.simba.yanawacortapi.domain.CourtService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
class SpringCourtApplicationServiceTest {
    private static final Court 코트장 = new Court(UUID.randomUUID(), "성동구", "응봉공원", null);

    @Autowired
    private CourtApplicationService courtApplicationService;
    @Autowired
    private CourtFeignApiTranslator courtFeignApiTranslator;
    @Autowired
    private CourtService courtService;
    @Autowired
    private CourtFeignApi courtFeignApi;

    @BeforeEach
    void setUp() {
        courtApplicationService = new CourtApplicationService(courtFeignApiTranslator, courtService, courtFeignApi);
    }

    @Test
    @DisplayName("공공데이터 Open API에서 정보를 가져오고 도메인 서비스에게 데이터를 저장하도록 요청합니다.")
    void test1() {
        List<Court> courts = courtApplicationService.saveCourts();
        for (Court court : courts) {
            System.out.println(court.getPlaceName());
        }
        assertThat(courts).isNotEmpty();
    }

}
