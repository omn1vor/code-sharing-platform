package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import platform.entities.Code;
import platform.services.CodeService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class CodeAPIController {

    @Autowired
    CodeService codeService;

    @GetMapping("/code/{id}")
    public Code getSnippet(HttpServletResponse res, @PathVariable UUID id) {
        res.setHeader("Content-Type", "application/json");
        return codeService.getSnippet(id);
    }

    @PostMapping("/code/new")
    public Map<String, String> publishSnippet(@RequestBody Code code) {
        return Map.of("id", String.valueOf(codeService.addSnippet(code)));
    }

    @GetMapping("/code/latest")
    public List<Code> getLatest() {
        return codeService.getLatest();
    }
}
