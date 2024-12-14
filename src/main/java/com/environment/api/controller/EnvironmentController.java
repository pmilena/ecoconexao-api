package com.environment.api.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class EnvironmentController {

    @GetMapping("/plants")
    public List<Map<String, String>> getPlants() {
        return List.of(
                Map.of("name", "Ipê Amarelo", "status", "Nativa"),
                Map.of("name", "Caviúna", "status", "Ameaçada"),
                Map.of("name", "Araucária", "status", "Vulnerável")
        );
    }

    @GetMapping("/tips")
    public List<String> getPreservationTips() {
        return List.of(
                "Reduza o uso de plásticos descartáveis.",
                "Economize água sempre que possível.",
                "Recicle materiais como papel, vidro e metal.",
                "Plante uma árvore em sua comunidade."
        );
    }

    @GetMapping("/impact")
    public List<Map<String, String>> getEnvironmentalImpact() {
        return List.of(
                Map.of("material", "Plástico", "decompositionTime", "450 anos"),
                Map.of("material", "Vidro", "decompositionTime", "indefinido"),
                Map.of("material", "Papel", "decompositionTime", "6 meses")
        );
    }
}
