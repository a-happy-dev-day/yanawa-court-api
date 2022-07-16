package fashionable.simba.yanawacortapi.dto;

import java.util.UUID;

public class CourtResponse {
    private UUID id;
    private String name;

    public CourtResponse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
