package com.project.bime.controller;

import com.project.bime.exception.NotFoundException;
import com.project.bime.model.extra.Attachment;
import com.project.bime.payload.media.MediaInfo;
import com.project.bime.repository.MediaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class MediaController {

    private MediaRepository mediaRepository;

    @Autowired
    public MediaController(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    @PostMapping("/upload")
    public MediaInfo fileUpload(@RequestParam MultipartFile file) {
        String originalName = file.getOriginalFilename().replaceAll(" ", "_");
        int a = originalName.lastIndexOf(".");
        String file_type = originalName.substring(a + 1);
        String name = UUID.randomUUID() + "." + file_type;
        String path = "/public/images/" + name;
        File outFile = new File(System.getProperty("user.dir") + path);
        try {
            OutputStream out = new FileOutputStream((outFile));
            FileCopyUtils.copy(file.getInputStream(), out);
            Attachment attachment = new Attachment(name);
            return new MediaInfo(mediaRepository.save(attachment));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
