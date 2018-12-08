package nl.jtosti.hermes.controllers;

import nl.jtosti.hermes.entities.dto.FileDTO;
import nl.jtosti.hermes.exceptions.FileStoreException;
import nl.jtosti.hermes.services.StorageServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
    private final StorageServiceInterface storageService;

    @Autowired
    public FileController(StorageServiceInterface storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/images/upload")
    @ResponseStatus(HttpStatus.OK)
    public FileDTO uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String path = storageService.store(file);
            return new FileDTO(path);
        } catch (Exception e) {
            throw new FileStoreException("uploadFile");
        }
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
