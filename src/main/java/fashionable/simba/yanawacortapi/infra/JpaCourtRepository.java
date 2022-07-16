package fashionable.simba.yanawacortapi.infra;

import fashionable.simba.yanawacortapi.domain.Court;
import fashionable.simba.yanawacortapi.domain.CourtRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface JpaCourtRepository extends JpaRepository<Court, Long>, CourtRepository {
    @Override
    <S extends Court> List<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Court> findById(UUID aLong);

    List<Court> findCourtByNameContainingOrRegionContaining(String name, String region);
}
