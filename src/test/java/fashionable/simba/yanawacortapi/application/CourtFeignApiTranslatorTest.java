package fashionable.simba.yanawacortapi.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import fashionable.simba.yanawacortapi.domain.Court;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Rollback
@Transactional
class CourtFeignApiTranslatorTest {

    @Autowired
    CourtFeignApiTranslator courtFeignApiTranslator;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CourtFeignApi courtFeignApi;

    @BeforeEach
    void setUp() {
        courtFeignApiTranslator = new CourtFeignApiTranslator(courtFeignApi, objectMapper);
    }

    // TODO : CourtFeignApiTranslatorTest 작성하기

    @Test
    @DisplayName("API 헬스 체크를 합니다.")
    void test1() {
        assertThat(courtFeignApiTranslator.checkApi()).isTrue();
    }

    @Test
    @DisplayName("API에서 코트장 정보를 가져와 Court 엔티티로 가공합니다.")
    void test2() {
        List<Court> 코트_리스트 = courtFeignApiTranslator.findCourts();
        assertThat(코트_리스트.get(0)).isInstanceOf(Court.class);
    }

    @Test
    @DisplayName("변경 도중 실패가 발생하면 IOException이 발생합니다")
    void test3() {
        Map<String, Object> map = new HashMap<>();
        Assertions.assertThatThrownBy(
            () -> courtFeignApiTranslator.getCourts(ResponseEntity.ok(map))
        ).isInstanceOf(Exception.class);
    }

    @Test
    @DisplayName("API의 상태가 OK가 아니면 false를 리턴합니다.")
    void test4() {
        courtFeignApiTranslator.isStatusOk(ResponseEntity.badRequest().build());
    }
}
