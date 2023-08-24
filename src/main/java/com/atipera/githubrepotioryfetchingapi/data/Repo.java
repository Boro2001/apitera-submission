package com.atipera.githubrepotioryfetchingapi.data;

import com.atipera.githubrepotioryfetchingapi.mappings.Branch;
import lombok.Data;

import java.util.List;

@Data
public class Repo {
    private String name;
    private String ownerLogin;
    private List<BranchInfo> branchInfoList;

    public Repo(RepoWithBranchURIs repoWithBranchURIs, List<Branch> branches) {
        this.name = repoWithBranchURIs.getName();
        this.ownerLogin = repoWithBranchURIs.getOwnerLogin();
        this.branchInfoList = List.copyOf(branches).stream()
                .map(branch -> BranchInfo.builder()
                        .branchName(branch.getName())
                        .lastCommitSHA(branch.getCommit().getSha())
                        .build())
                .toList();
    }
}
