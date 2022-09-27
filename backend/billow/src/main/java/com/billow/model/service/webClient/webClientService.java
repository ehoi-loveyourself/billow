package com.billow.model.service.webClient;

import com.billow.domain.dto.program.ProgramResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Service
public class webClientService {

    private final WebClient webClient;

    public ResponseEntity<ProgramResponse> callDjangoApi(Long userId) {
        return webClient.get()
                .uri("db/" + userId + "/")
                .retrieve()
//                .bodyToFlux(ProgramResponse.class);
                .toEntity(ProgramResponse.class)
                .block();
    }
}
