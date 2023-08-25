package com.atipera.githubrepotioryfetchingapi.mappings;

import com.fasterxml.jackson.annotation.JsonProperty;


public record Commit(@JsonProperty("sha") String sha,
                     @JsonProperty("url") String url) {
}