package com.learning.cloudgateway.model;

import java.util.List;

public record AuthenticationResponse(String userId,
                                     String accessToken,
                                     String refreshToken,
                                     Long expiresAt,
                                     List<String> authorityList) {
}
