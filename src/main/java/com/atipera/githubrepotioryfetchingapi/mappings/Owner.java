package com.atipera.githubrepotioryfetchingapi.mappings;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Owner(
        String login,
        int id, @JsonProperty("node_id") String nodeID,
        @JsonProperty("avatar_url") String avatarURL,
        @JsonProperty("gravatar_id") String gravatarID,
        String url,
        @JsonProperty("html_url") String htmlURL,
        @JsonProperty("followers_url") String followersURL,
        @JsonProperty("following_url") String followingURL,
        @JsonProperty("gists_url") String gistsURL,
        @JsonProperty("starred_url") String starredURL,
        @JsonProperty("subscriptions_url") String subscriptionsURL,
        @JsonProperty("organizations_url") String organizationsURL,
        @JsonProperty("repos_url") String reposURL,
        @JsonProperty("events_url") String eventsURL,
        @JsonProperty("received_events_url") String receivedEventsURL,
        String type,
        @JsonProperty("site_admin") boolean siteAdmin) {
}



