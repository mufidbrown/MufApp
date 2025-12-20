package com.muf.modules.file.service;

import com.muf.modules.file.entity.domain.File;
import com.muf.modules.file.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    public File save(File file) {
        return fileRepository.save(file);
    }

    public File getByUploadedFileName(String uploadedFileName) {
        return fileRepository.findByUploadedFileName(uploadedFileName).orElse(null);
    }
}
