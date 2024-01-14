package com.learning.cloudgateway.controller;

import com.learning.cloudgateway.model.AuthenticationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("authenticate")
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    @GetMapping("login")
    public ResponseEntity<AuthenticationResponse> login(@AuthenticationPrincipal OidcUser oidcUser,
                                                        Model model,
                                                        @RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client) {
        logger.info("User login API triggered with user: {}", oidcUser.getEmail());

        return ResponseEntity
                .ok()
                .body(new AuthenticationResponse(
                        oidcUser.getEmail(),
                        client.getAccessToken().getTokenValue(),
                        Objects.requireNonNull(client.getRefreshToken()).getTokenValue(),
                        Objects.requireNonNull(client.getAccessToken().getExpiresAt()).getEpochSecond(),
                        oidcUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                );
    }
}
