package fashionable.simba.yanawacortapi.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CourtServiceTest {

    @Mock
    private CourtRepository courtRepository;
    private CourtService courtService;

    @BeforeEach
    void setUp() {
        courtService = new CourtService(courtRepository);
    }

    @Test
    @DisplayName("코트장 리스트를 저장한다.")
    void test1() {
        // given
        List<Court> courts = Arrays.asList(
            new Court(null, "성동구", "응봉공원", null),
            new Court(null, "양천구", "목동운동장>다목적구장", null)
        );

        // when
        when(courtRepository.saveAll(any())).thenReturn(courts);
        courtService.saveCourts(courts);

        // then
        verify(courtRepository, atLeast(1)).saveAll(any());
    }

    @Test
    @DisplayName("Params으로 코트장을 조회한다.")
    void test2() {
        // given
        List<Court> courts = Arrays.asList(
            new Court(null, "성동구", "응봉공원", null),
            new Court(null, "양천구", "목동운동장>다목적구장", null)
        );
        String court = "court";

        // when
        when(courtRepository.findCourtByNameContainingOrRegionContaining(anyString(), anyString()))
            .thenReturn(courts);
        courtService.findCourt(court);

        // then
        verify(courtRepository, atLeast(1))
            .findCourtByNameContainingOrRegionContaining(court, court);
    }

    @Test
    @DisplayName("코트장 ID를 이용해 조회한다.")
    void test3() {
        // given
        Court 코트장 = new Court(null, "성동구", "응봉공원", null);
        UUID 코트장_ID = UUID.randomUUID();

        // when
        when(courtRepository.findById(any()))
            .thenReturn(Optional.of(코트장));
        courtService.findCourt(코트장_ID);

        // then
        verify(courtRepository, atLeast(1))
            .findById(코트장_ID);
    }
}
