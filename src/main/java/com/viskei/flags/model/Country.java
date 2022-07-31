package com.viskei.flags.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;

@Data
public class Country {
    private String name;

    private Flags flags;

    @JsonIgnore
    private String flagUrl;


    @JsonProperty("name")
    private void unpackNameFromNestedObject(Map<String, ?> name) {
        this.name = (String) name.get("official");
    }

    @Data
    public class Flags {
        private String svg;
        private String png;
    }
}
