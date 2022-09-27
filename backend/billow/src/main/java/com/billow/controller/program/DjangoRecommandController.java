package com.billow.controller.program;

import com.billow.domain.dto.program.ProgramResponse;
import com.billow.domain.entity.program.Program;
import com.billow.model.service.webClient.webClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user-recommend")
public class DjangoRecommandController {

    public final WebClient webClient;

    public final webClientService webClientService;

    @GetMapping("/{userId}")
    public List<ProgramResponse> userRecommend(@PathVariable("userId") Long userId) {
        List<ProgramResponse> programRecommend =  webClientService.callDjangoApi(userId);
        System.out.println(programRecommend);
        return (programRecommend);
//        return webClient.get()
//                .uri("db/" + userId + "/")
//                .retrieve()
//                .bodyToFlux(ProgramResponse.class);

        }
}
