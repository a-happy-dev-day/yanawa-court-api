package fashionable.simba.yanawacortapi.ui;

import fashionable.simba.yanawacortapi.application.CourtApplicationService;
import fashionable.simba.yanawacortapi.domain.Court;
import fashionable.simba.yanawacortapi.dto.CourtResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourtControllerTest {

    @Mock
    private CourtApplicationService courtApplicationService;
    private CourtController courtController;

    @BeforeEach
    void setUp() {
        courtController = new CourtController(courtApplicationService);
    }

    @Test
    @DisplayName("코트장을 저장한다.")
    void test1() {
        Assertions.assertDoesNotThrow(
            () -> courtController.saveCourtList()
        );
    }

    @Test
    @DisplayName("ID를 입력해 코트장을 조회한다.")
    void test2() {
        // given
        UUID 응봉공원_ID = UUID.randomUUID();
        Court 응봉공원 = new Court(응봉공원_ID, "성동구", "응봉공원", null);

        // when
        when(courtApplicationService.findCourt(응봉공원_ID)).thenReturn(응봉공원);
        ResponseEntity<CourtResponse> 응답 = courtController.getCourt(응봉공원_ID);

        // then
        Assertions.assertAll(
            () -> assertThat(Objects.requireNonNull(응답.getBody()).getName())
                .isEqualTo(응봉공원.getRegion() + " " + 응봉공원.getName()),
            () -> assertThat(Objects.requireNonNull(응답.getBody().getId()))
                .isEqualTo(응봉공원.getId())
        );
    }

    @Test
    @DisplayName("한글 파라미터 명을 입력해 데이터를 조회한다.")
    void test3() {
        String 입력값 = "응봉공원";
        UUID 응봉공원_ID = UUID.randomUUID();
        Court 응봉공원 = new Court(응봉공원_ID, "성동구", "응봉공원", null);
        List<Court> 코트장_리스트 = List.of(응봉공원);

        when(courtApplicationService.findCourts(입력값)).thenReturn(코트장_리스트);
        ResponseEntity<List<CourtResponse>> 응답 = courtController.getCourtsContainsParam(입력값);

        assertThat(Objects.requireNonNull(응답.getBody()).size())
            .isEqualTo(코트장_리스트.size());
    }

    @Test
    @DisplayName("한글 파라미터 명이 아니면 예외가 발생한다.")
    void test5() {
        String 영어_파라미터 = "eungbong";
        assertThatThrownBy(
            () -> courtController.getCourtsContainsParam(영어_파라미터)
        ).isInstanceOf(IllegalArgumentException.class);
    }


    @DisplayName("파라미터의 값이 null이거나 비어있으면 전체를 조회한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void test7(String 입력값) {
        UUID 응봉공원_ID = UUID.randomUUID();
        Court 응봉공원 = new Court(응봉공원_ID, "성동구", "응봉공원", null);
        List<Court> 코트장_리스트 = List.of(응봉공원);

        when(courtApplicationService.findCourts()).thenReturn(코트장_리스트);
        ResponseEntity<List<CourtResponse>> 응답 = courtController.getCourtsContainsParam(입력값);

        assertThat(Objects.requireNonNull(응답.getBody()).size())
            .isEqualTo(코트장_리스트.size());
    }

}
