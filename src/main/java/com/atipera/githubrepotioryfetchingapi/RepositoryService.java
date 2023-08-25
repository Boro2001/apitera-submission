package com.atipera.githubrepotioryfetchingapi;

import com.atipera.githubrepotioryfetchingapi.data.BranchInfo;
import com.atipera.githubrepotioryfetchingapi.data.Repo;
import com.atipera.githubrepotioryfetchingapi.filtering.IsNotForkFilter;
import com.atipera.githubrepotioryfetchingapi.filtering.RepositoryFilter;
import com.atipera.githubrepotioryfetchingapi.mappings.Branch;
import com.atipera.githubrepotioryfetchingapi.mappings.Repository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;


@Service
@Getter
@Setter
@RequiredArgsConstructor
public class RepositoryService {

    private RepositoryFilter repositoryFilter = new IsNotForkFilter();
    @Value("${backapi.endpoint.uri}")
    private String endpoint;

    public Flux<Repo> fetchUserRepositoriesByUsername(String username) {
        return this.fetchRepositoryFlux(endpoint + "/users/"+ username + "/repos")
                .filter(repositoryFilter::filter)
                .flatMap(this::processRepositoryToRepo);
    }

    public BranchInfo convertBranchToBranchInfo(Branch branch) {
        return new BranchInfo(branch.name(), branch.commit().sha());
    }

    public Mono<Repo> processRepositoryToRepo(Repository repository) {

        String branchesUrl = repository.branchesUrl().replace("{/branch}", "");
        List<BranchInfo> branchInfoList = WebClient.create().get()
                .uri(branchesUrl)
                .retrieve()
                .bodyToFlux(Branch.class)
                .map(this::convertBranchToBranchInfo)
                .collectList()
                .block();
        return Mono.just(new Repo(repository.name(), repository.description(), branchInfoList));
    }

    public Flux<Repository> fetchRepositoryFlux(String repositoryUrl) {
        return WebClient.create()
                .get()
                .uri(repositoryUrl)
                .retrieve()
                .bodyToFlux(Repository.class);
    }

    public Flux<Branch> fetchBranchFlux(String branchUrl) {
        return WebClient.create()
                .get()
                .uri(branchUrl)
                .retrieve()
                .bodyToFlux(Branch.class);
    }

}



