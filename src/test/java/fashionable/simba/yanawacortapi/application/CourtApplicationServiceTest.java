package fashionable.simba.yanawacortapi.application;

import fashionable.simba.yanawacortapi.domain.Court;
import fashionable.simba.yanawacortapi.domain.CourtService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourtApplicationServiceTest {
    private CourtApplicationService courtApplicationService;
    @Mock
    TennisCourtOpenApi tennisCourtOpenApi;
    @Mock
    CourtService courtService;

    @BeforeEach
    void setUp() {
        courtApplicationService = new CourtApplicationService(tennisCourtOpenApi, courtService);
    }

    @Test
    @DisplayName("공공데이터 Open API에서 정보를 가져오고 도메인 서비스에게 데이터를 저장하도록 요청합니다.")
    void test1() {
        // given
        List<String> courts = Arrays.asList("courts1", "courts2");

        // when
        when(tennisCourtOpenApi.checkApi()).thenReturn(true);
        when(tennisCourtOpenApi.findCourts()).thenReturn(courts);
        when(courtService.saveCourts(any())).thenReturn(true);
        courtApplicationService.saveCourts();

        //then
        verify(tennisCourtOpenApi).checkApi();
        verify(tennisCourtOpenApi).findCourts();
        verify(courtService).saveCourts(any());
    }

    @Test
    @DisplayName("공공데이터 Open API의 상태가 false이면 예외가 발생합니다.")
    void test2() {
        // when
        when(tennisCourtOpenApi.checkApi()).thenReturn(false);

        // then
        Assertions.assertThatThrownBy(
            () -> courtApplicationService.saveCourts()
        ).isInstanceOf(IllegalStateException.class);

    }

    @Test
    @DisplayName("공공데이터 Open API에서 정보를 가져옵니다.")
    void test3() {
        // given
        List<String> courts = Arrays.asList("courts1", "courts2");

        // when
        when(tennisCourtOpenApi.checkApi()).thenReturn(true);
        when(tennisCourtOpenApi.findCourts()).thenReturn(courts);
        when(courtService.saveCourts(any())).thenReturn(true);
        courtApplicationService.saveCourts();

        //then
        verify(tennisCourtOpenApi).findCourts();
    }

    @Test
    @DisplayName("도메인 서비스에게 데이터를 저장하도록 요청합니다.")
    void test5() {
        // given
        List<String> courts = Arrays.asList("courts1", "courts2");

        // when
        when(tennisCourtOpenApi.checkApi()).thenReturn(true);
        when(tennisCourtOpenApi.findCourts()).thenReturn(courts);
        when(courtService.saveCourts(any())).thenReturn(true);
        courtApplicationService.saveCourts();

        //then
        verify(courtService).saveCourts(any());
    }

    @Test
    @DisplayName("ID 값을 가져와 데이터 스토어에서 데이터를 조회합니다.")
    void test6() {
        // given
        UUID 코트장_ID = UUID.randomUUID();
        Court 코트장 = new Court(코트장_ID, "성동구", "응봉공원", null);
        // when
        when(courtService.findCourt(코트장_ID)).thenReturn(코트장);
        courtApplicationService.findCourt(코트장_ID);

        // then
        verify(courtService).findCourt(코트장_ID);
    }

    @Test
    @DisplayName("입력 값을 가져와 데이터 스토어에서 데이터를 조회합니다.")
    void test7() {
        // given
        String 지역 = "응봉공원";
        Court court = new Court(UUID.randomUUID(), "성동구", "응봉공원", null);

        // when
        when(courtService.findCourt(지역)).thenReturn(court);
        courtApplicationService.findCourt(지역);

        // then
        verify(courtService).findCourt(지역);
    }

    @Test
    @DisplayName("입력 값은 지역과 이름으로 구성되어 있습니다.")
    void test8() {
        // given
        String 지역과이름 = "성동구 응봉공원";
        Court court = new Court(UUID.randomUUID(), "성동구", "응봉공원", null);

        // when
        when(courtService.findCourt(지역과이름)).thenReturn(court);
        courtApplicationService.findCourt(지역과이름);

        // then
        verify(courtService).findCourt(지역과이름);
    }
}
