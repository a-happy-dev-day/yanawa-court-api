package fashionable.simba.yanawacortapi.infra;

import fashionable.simba.yanawacortapi.domain.Court;
import fashionable.simba.yanawacortapi.domain.CourtRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JpaCourtRepository extends JpaRepository<Court, Long>, CourtRepository {
    @Override
    <S extends Court> List<S> saveAll(Iterable<S> entities);

    @Override
    Optional<Court> findById(Long aLong);

}
