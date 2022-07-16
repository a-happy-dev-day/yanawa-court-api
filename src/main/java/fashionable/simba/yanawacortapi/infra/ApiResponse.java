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
    private String imageUrl;

    public ApiResponse() {
    }

    public String getAreaName() {
        return areaName;
    }

    public String getPlaceName() {
        return placeName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public String toString() {
        return "Court{" +
            "areaName='" + areaName + '\'' +
            ", placeName='" + placeName + '\'' +
            ", imageUrl='" + imageUrl + '\'' +
            '}';
    }
}
