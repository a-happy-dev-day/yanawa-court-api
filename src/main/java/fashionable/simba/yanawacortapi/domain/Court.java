package fashionable.simba.yanawacortapi.domain;

import java.util.UUID;

public class Court {
    private UUID id;
    private String region;
    private String name;
    private String imagePath;

    protected Court() {/*no-op*/}

    public Court(UUID id, String region, String name, String imagePath) {
        this.id = id;
        this.region = region;
        this.name = name;
        this.imagePath = imagePath;
    }
}
