package fashionable.simba.yanawacortapi.domain;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Court {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(columnDefinition = "BINARY(16)")
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
