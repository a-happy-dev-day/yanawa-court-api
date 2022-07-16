package fashionable.simba.yanawacortapi.domain;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CourtRepository {
    <S extends Court> List<S> saveAll(Iterable<S> entities);

    Optional<Court> findById(UUID aLong);

    List<Court> findCourtByNameContainingOrRegionContaining(String name, String region);

}
