package com.apitera.githubrepotioryfetchingapi;

import com.apitera.githubrepotioryfetchingapi.data.Repo;
import com.apitera.githubrepotioryfetchingapi.data.RepoWithBranchURIs;
import com.apitera.githubrepotioryfetchingapi.exception.BadUserException;
import com.apitera.githubrepotioryfetchingapi.exception.UnexpectedResponseFormat;
import com.apitera.githubrepotioryfetchingapi.mappings.Repository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class RepositoryService {

    @Value("${backapi.endpoint.uri}")
    private String endpoint;
    private static final HttpClient httpClient = HttpClient.newBuilder().build();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Repo> fetchUserRepositoriesByUsername(String username) throws URISyntaxException, IOException, InterruptedException, BadUserException, UnexpectedResponseFormat {
        HttpResponse<String> response = sendSyncGet(String.format("%s/%s/repos", endpoint, username));
        List<Repository> repositories = objectMapper.readValue(response.body(), new TypeReference<>() {
        });
        List<RepoWithBranchURIs> notForkedRepoWithLinks = repositories.stream()
                .filter(repository -> !repository.isFork())
                .map(repository -> {
                    try {
                        return new RepoWithBranchURIs(repository);
                    } catch (URISyntaxException e) {
                        throw new UnexpectedResponseFormat(String.format("Error while parsing URI: %s returned by api: %s", repository.getBranchesUrl(), e.getMessage()));
                    }
                }).toList();
        List<CompletableFuture<Repo>> repoList = notForkedRepoWithLinks.stream().
                map(repo -> httpClient.sendAsync(
                                HttpRequest.newBuilder(repo.getBranchURI()).GET().build(),
                                HttpResponse.BodyHandlers.ofString())
                        .thenApply(branches -> {
                            try {
                                return new Repo(repo, objectMapper.readValue(branches.body(), new TypeReference<>() {
                                }));
                            } catch (JsonProcessingException e) {
                                throw new UnexpectedResponseFormat(String.format("Error while parsing JSON: %s returned by api: %s", branches.body(), e.getMessage()));
                            }
                        }))
                .toList();
        return repoList.stream()
                .map(CompletableFuture::join)
                .toList();
    }

    private static HttpResponse<String> sendSyncGet(String uri) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(uri))
                .GET()
                .build();
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == HttpServletResponse.SC_OK) {
            return response;
        } else {
            throw new BadUserException(String.format("Error while fetching data from %s. Status code: %d", uri, response.statusCode()));
        }
    }

}



