package com.billow.model.service.webClient;

import com.billow.domain.dto.program.ProgramResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class webClientService {

    private final WebClient webClient;

    public List<ProgramResponse> callDjangoApi(Long userId) {
        return webClient.get()
                .uri("db/" + userId + "/")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ProgramResponse.class)
                .toStream()
                .collect(Collectors.toList());
//                .toEntity(ProgramResponse.class)
//                .bodyToMono(ProgramResponse.class)
//                .flatMapIterable(ProgramResponse)
//                .block();

    }
}
