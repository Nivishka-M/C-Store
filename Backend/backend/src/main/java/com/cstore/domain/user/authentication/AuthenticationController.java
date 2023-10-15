package com.cstore.domain.user.authentication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@Tag(
    name = "Sign in & Sign up",
    description = "Provides controller methods for signing in & signing up."
)

@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(
        method = "signUp",
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = JwtAuthenticationResponse.class)
                ),
                description = "Success",
                responseCode = "200"
            )
        },
        summary = "Signs up a user to the system."
    )
    @RequestMapping(
        method = RequestMethod.POST,
        path = "/signup"
    )
    public JwtAuthenticationResponse signUp(
        @RequestBody(required = true)
        SignUpRequest request
    ) {
        return authenticationService.signUp(request);
    }

    @Operation(
        method = "signIn",
        responses = {
            @ApiResponse(
                content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = JwtAuthenticationResponse.class)
                ),
                description = "Success",
                responseCode = "200"
            )
        },
        summary = "Signs in a user to the system."
    )
    @RequestMapping(
        method = RequestMethod.POST,
        path = "/signin"
    )
    public JwtAuthenticationResponse signIn(
        @RequestBody(required = true)
        SignInRequest request
    ) {
        return authenticationService.signIn(request);
    }
}
