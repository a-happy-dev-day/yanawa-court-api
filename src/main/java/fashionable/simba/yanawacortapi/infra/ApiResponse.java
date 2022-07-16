package fashionable.simba.yanawacortapi.infra;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiResponse {
    @JsonProperty("AREANM")
    private String areaName;
    @JsonProperty("PLACENM")
    private String placeName;
    @JsonProperty("IMGURL")
    private String imagePath;

    public ApiResponse() {
    }

    public String getAreaName() {
        return areaName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getImagePath() {
        return imagePath;
    }

    @Override
    public String toString() {
        return "Court{" +
            "areaName='" + areaName + '\'' +
            ", placeName='" + placeName + '\'' +
            ", imagePath='" + imagePath + '\'' +
            '}';
    }
}
