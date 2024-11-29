package com.example.jenkinslab;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<Map<String, Object>> getProducts() {
        return List.of(
                Map.of("id", 1, "name", "Laptop", "price", 1200),
                Map.of("id", 2, "name", "Phone", "price", 800)
        );
    }

    @GetMapping("/{id}")
    public Map<String, Object> getProductById(@PathVariable int id) {
        return Map.of("id", id, "name", id == 1 ? "Laptop" : "Unknown", "price", id == 1 ? 1200 : 0);
    }
}

