package com.atipera.githubrepotioryfetchingapi;

import com.atipera.githubrepotioryfetchingapi.data.Repo;
import com.atipera.githubrepotioryfetchingapi.exception.BadUserException;
import com.atipera.githubrepotioryfetchingapi.exception.UnexpectedResponseFormat;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;


@RestController
@RequestMapping(("/api/v1/"))
public class RepostioryController {

    private final RepositoryService repositoryService;

    public RepostioryController(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }


    @ExceptionHandler({URISyntaxException.class, IOException.class, InterruptedException.class, BadUserException.class, UnexpectedResponseFormat.class})
    public HttpEntity<ErrorResponse> handleException(Exception e) {
        return ResponseEntity
                .status(HttpServletResponse.SC_NOT_FOUND)
                .body(new ErrorResponse(HttpServletResponse.SC_NOT_FOUND, e.getMessage()));
    }

    @GetMapping("{username}/repositories")
    public ResponseEntity<List<Repo>> getUserRepositories(@PathVariable String username) throws URISyntaxException, IOException, InterruptedException, BadUserException, UnexpectedResponseFormat {
        return ResponseEntity.ok(repositoryService.fetchUserRepositoriesByUsername(username));
    }


}
