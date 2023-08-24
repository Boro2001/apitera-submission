package com.apitera.githubrepotioryfetchingapi.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BranchInfo {
    private String branchName;
    private String lastCommitSHA;

}
