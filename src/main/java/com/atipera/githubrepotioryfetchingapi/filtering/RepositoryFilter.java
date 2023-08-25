package com.atipera.githubrepotioryfetchingapi.filtering;

import com.atipera.githubrepotioryfetchingapi.mappings.Repository;

public interface RepositoryFilter {
    boolean filter(Repository repository);
}
