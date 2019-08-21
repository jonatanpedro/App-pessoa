package br.com.zeroquo.appPessoa.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/source")
    public String findById() {
        return "https://github.com/jonatanpedro/App-pessoa";
    }
}
