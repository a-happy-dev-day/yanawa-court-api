package fashionable.simba.yanawacortapi;

public class CourtResponse {
    private Long id;
    private String name;

    public CourtResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
