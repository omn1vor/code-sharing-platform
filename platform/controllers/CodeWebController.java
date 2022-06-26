package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.entities.Code;
import platform.services.CodeService;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class CodeWebController {

    @Autowired
    CodeService codeService;

    @GetMapping(value = "/code/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public String getSnippet(HttpServletResponse res, Model model, @PathVariable UUID id) {
        res.setHeader("Content-Type", "text/html");
        Code snippet = codeService.getSnippet(id);
        model.addAttribute("codeSnippet", snippet.getCode());
        model.addAttribute("date", snippet.getDateAsString());
        model.addAttribute("timeRestraint", snippet.getTime() > 0);
        model.addAttribute("time", snippet.getRemainingSeconds());
        model.addAttribute("viewsRestraint", snippet.getViews() > 0);
        model.addAttribute("views", snippet.getRemainingViews());
        return "code";
    }

    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    public String publishSnippet(HttpServletResponse res) {
        res.setHeader("Content-Type", "text/html");
        return "addCode";
    }

    @GetMapping("/code/latest")
    public String getLatest(HttpServletResponse res, Model model) {
        res.setHeader("Content-Type", "text/html");
        model.addAttribute("title", "Latest");
        model.addAttribute("snippets", codeService.getLatest());
        return "snippets";
    }
}
