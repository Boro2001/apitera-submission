package com.atipera.githubrepotioryfetchingapi.filtering;

import com.atipera.githubrepotioryfetchingapi.mappings.Repository;

public class IsNotForkFilter implements RepositoryFilter{
    @Override
    public boolean filter(Repository repository) {
        return !repository.fork();
    }
}
