package com.atipera.githubrepotioryfetchingapi.mappings;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Branch(String name,
                     Commit commit,
                     @JsonProperty("protected") String protectedField) {
}
