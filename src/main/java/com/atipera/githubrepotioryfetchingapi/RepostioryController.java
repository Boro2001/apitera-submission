package com.atipera.githubrepotioryfetchingapi;

import com.atipera.githubrepotioryfetchingapi.data.Repo;
import com.atipera.githubrepotioryfetchingapi.exception.UserNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.io.IOException;

@RestController
@RequestMapping(("/api/v1/"))
@RequiredArgsConstructor
public class RepostioryController {

    private final RepositoryService repositoryService;
    @ExceptionHandler({UserNotFoundException.class})
    public void handleException(Exception e, HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_NOT_FOUND, e.getMessage());
    }

    @GetMapping("{username}/repositories")
    public Flux<Repo> getUserRepositories(@PathVariable String username) {
        return repositoryService.fetchUserRepositoriesByUsername(username);
    }

}
