package it.aredegalli.coachly.user.controller;

import it.aredegalli.coachly.user.dto.InternalUserResolveResponse;
import it.aredegalli.coachly.user.service.InternalUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/internal/users")
public class InternalUserController {

    private final InternalUserService internalUserService;

    public InternalUserController(InternalUserService internalUserService) {
        this.internalUserService = internalUserService;
    }

    @GetMapping("/resolve")
    public InternalUserResolveResponse resolveUser(@RequestParam("externalId") String externalId) {
        return new InternalUserResolveResponse(internalUserService.resolveUserId(externalId));
    }
}
