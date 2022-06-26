package platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import platform.entities.Code;
import platform.repositories.CodeRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CodeService {
    @Autowired
    CodeRepository codeRepo;

    public Code getSnippet(UUID id) {
        Code snippet = codeRepo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        if (!snippet.isAvailable()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        snippet.registerView();

        codeRepo.save(snippet);
        return snippet;
    }

    public UUID addSnippet(Code toAdd) {
        Code snippet = new Code();
        snippet.setCode(toAdd.getCode());
        snippet.setDate(LocalDateTime.now());
        snippet.setTime(Math.max(0, toAdd.getTime()));
        snippet.setViews(Math.max(0, toAdd.getViews()));
        snippet.setRestricted(snippet.getTime() > 0 || snippet.getViews() > 0);

        codeRepo.save(snippet);
        return snippet.getId();
    }

    public List<Code> getLatest() {
        return codeRepo.findTop10ByRestrictedFalseOrderByDateDesc();
    }
}
