
package org.example;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class City {

    @JsonProperty("slug")
    private String slug;

    @JsonProperty("coords")
    private Coordinates coords;

    @Data
    @NoArgsConstructor
    public static class Coordinates {
        @JsonProperty("lat")
        private double lat;

        @JsonProperty("lon")
        private double lon;
    }
}
