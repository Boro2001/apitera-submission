package com.atipera.githubrepotioryfetchingapi;

import com.atipera.githubrepotioryfetchingapi.mappings.Repository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.*;

@RequiredArgsConstructor
class RepositoryServiceTest {

    private final RepositoryService repositoryService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void fetchUserRepositoriesByUsername() {
    }

    @Test
    void convertBranchToBranchInfo() {
    }

    @Test
    void processRepositoryToRepo() {
    }

    @Test
    void fetchRepositoryFlux() {
    }

    @Test
    void fetchBranchMono() {
    }

    @Test
    void getRepositoryFilter() {
    }

    @Test
    void setRepositoryFilter() {
    }
}