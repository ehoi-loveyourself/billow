package com.billow.controller.program;

import com.billow.domain.entity.program.Program;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@RestController
@RequestMapping("/userrecommend")
public class DjangoRecommandController {

    public final WebClient webClient;

    @GetMapping("/uesrRecomm/{userId}")
    public Flux<Program> userRecommend(@PathVariable("userId") Long userId) {
        return webClient.get()
                .uri("db/" + userId + "/")
                .retrieve()
                .bodyToFlux(Program.class);


    }
}
