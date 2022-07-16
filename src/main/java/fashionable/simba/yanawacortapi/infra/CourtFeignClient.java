package fashionable.simba.yanawacortapi.infra;

import fashionable.simba.yanawacortapi.application.TennisCourtOpenApi;
import fashionable.simba.yanawacortapi.domain.Court;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(url = "http://openapi.seoul.go.kr:8088")
public interface CourtFeignClient extends TennisCourtOpenApi {

    @GetMapping("/6663504c7867656f3635516a6b4770/json/ListPublicReservationSport/1/1/%ED%85%8C%EB%8B%88%EC%8A%A4%EC%9E%A5")
    boolean checkApi();

    @GetMapping("/6663504c7867656f3635516a6b4770/json/ListPublicReservationSport/{minNum}/{maxNum}/%ED%85%8C%EB%8B%88%EC%8A%A4%EC%9E%A5")
    List<Court> findCourts(@PathVariable int minNum, @PathVariable int maxNum);
}
