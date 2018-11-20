package com.reomor.controller.mvc;

import com.reomor.core.exception.StorageFileNotFoundException;
import com.reomor.impl.repository.FileSystemStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private final FileSystemStorage fileSystemStorage;

    @Autowired
    public FileUploadController(FileSystemStorage fileSystemStorage) {
        this.fileSystemStorage = fileSystemStorage;
    }

    @GetMapping("/files")
    public String listUploadedFiles(HttpServletRequest request, Model model) throws IOException {

        model.addAttribute("files", fileSystemStorage.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "serveFile", "test", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/files/{thread:.+}/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String thread, @PathVariable String filename) {

        Resource file = fileSystemStorage.loadAsResource(thread, filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @PostMapping("/upload")
    public String handleFileUpload(
            @RequestParam("file") MultipartFile file,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {

        fileSystemStorage.store("/", file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        String referrer = request.getHeader("Referer");

        return "redirect:" + referrer;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
