package com.atipera.githubrepotioryfetchingapi.data;


import java.util.List;

public record Repo(String name, String description, List<BranchInfo> branchInfoList) {
}