package com.apitera.githubrepotioryfetchingapi.data;

import com.apitera.githubrepotioryfetchingapi.mappings.Repository;
import lombok.Getter;
import lombok.Setter;

import java.net.URI;
import java.net.URISyntaxException;

@Getter
@Setter
public class RepoWithBranchURIs {
    private String name;
    private String ownerLogin;
    private URI branchURI;

    public RepoWithBranchURIs(Repository repository) throws URISyntaxException {
        this.name = repository.getFullName();
        this.ownerLogin = repository.getOwner().getLogin();
        this.branchURI = new URI(repository.getFormattedBranchesUrl());
    }

}