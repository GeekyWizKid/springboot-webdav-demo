package com.redstream.webdav.controller;


import com.redstream.webdav.service.WebDavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/webdav")
public class WebDavController {

    @Autowired
    private WebDavService webDavService;


    @GetMapping("/query")
    public String query(@RequestParam String uri) throws IOException {
        return webDavService.query(uri);
    }

    @PostMapping("/upload")
    public String upload(@RequestParam String uri, @RequestParam MultipartFile file) throws IOException {
        return webDavService.uploadFile(uri, file);
    }

    @GetMapping("/download")
    public byte[] download(@RequestParam String uri) throws IOException {
        return webDavService.downloadFile(uri);
    }
}
