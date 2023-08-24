package com.apitera.githubrepotioryfetchingapi.mappings;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Commit {

    @JsonProperty("sha")
    private String sha;

    @JsonProperty("url")
    private String url;

}
