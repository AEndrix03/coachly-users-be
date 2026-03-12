package it.aredegalli.coachly.user.controller;

import it.aredegalli.coachly.user.service.InternalUserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.UUID;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class InternalUserControllerTest {

    @Mock
    private InternalUserService internalUserService;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(new InternalUserController(internalUserService)).build();
    }

    @Test
    void resolveUserReturnsInternalUserIdForExternalId() throws Exception {
        String externalId = "jwt-sub";
        UUID userId = UUID.randomUUID();

        when(internalUserService.resolveUserId(externalId)).thenReturn(userId);

        mockMvc.perform(get("/internal/users/resolve")
                        .queryParam("externalId", externalId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(userId.toString()));

        verify(internalUserService).resolveUserId(externalId);
    }

    @Test
    void resolveUserRejectsMissingExternalId() throws Exception {
        mockMvc.perform(get("/internal/users/resolve").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
