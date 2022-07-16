package fashionable.simba.yanawacortapi.domain;

import java.util.List;

public interface CourtRepository {
    <S extends Court> List<S> saveAll(Iterable<S> entities);
}
