package com.app.controllers;

import com.app.services.FileServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class FileController {
    @Autowired
    private FileServices fileServices;

    @GetMapping("/file-upload")
    public String getFileUploadPage() {
        return "fileUpload";
    }

    @PostMapping("/file-upload")
    public String storeNewFile(@RequestParam("file")MultipartFile file) throws IOException {
        fileServices.storeNewFile(file);
        return "redirect:/";
    }

    @GetMapping("/all-files")
    public String getAllFilesPage(Model model) {
        model.addAttribute("files", fileServices.getAllFiles());
        return "allFiles";
    }

    @PostMapping("/avatar-upload")
    public String storeUserAvatar(@RequestParam("avatar") MultipartFile avatar) throws IOException {
        fileServices.storeUserAvatar(avatar);
        return"redirect:/";
    }
}
