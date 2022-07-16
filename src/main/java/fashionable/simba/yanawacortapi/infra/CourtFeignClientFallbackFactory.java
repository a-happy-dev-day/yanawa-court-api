package fashionable.simba.yanawacortapi.infra;

import feign.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class CourtFeignClientFallbackFactory implements FallbackFactory<CourtFeignClient> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public CourtFeignClient create(Throwable cause) {

        return new CourtFeignClient() {
            @Override
            public Response checkApi() {
                log.warn("Access failed using court feign client");
                throw new IllegalStateException("Access failed feign client");
            }

            @Override
            public Response findCourts(int minNum, int maxNum) {
                log.warn("Failed to get list using court feign client [{}][{}]", minNum, maxNum);
                throw new IllegalStateException("Access failed feign client");
            }
        };
    }
}
