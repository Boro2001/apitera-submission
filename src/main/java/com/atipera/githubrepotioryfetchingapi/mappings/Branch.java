package com.atipera.githubrepotioryfetchingapi.mappings;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Branch {

    @JsonProperty("name")
    private String name;

    @JsonProperty("commit")
    private Commit commit;

    @JsonProperty("protected")
    private boolean myprotected;
}
