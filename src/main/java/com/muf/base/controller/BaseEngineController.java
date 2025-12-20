package com.muf.base.controller;

import com.muf.core.engine.FileEngineService;
import com.muf.core.engine.TranslatorEngineService;
import com.muf.model.Response;
import com.muf.model.UploadFileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("")
public abstract class BaseEngineController extends BaseController {
    @Autowired
    private FileEngineService fileEngineService;

    @Autowired
    protected TranslatorEngineService translatorEngineService;

    @PostMapping(path = "/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<UploadFileModel> fileUpload(@RequestPart("file") MultipartFile file, @RequestPart("fileTypeCode") String fileTypeCode, @RequestPart(required = false) String croppedPoints) throws IOException {
        Response<UploadFileModel> response = fileEngineService.fileUpload(file, fileTypeCode, croppedPoints);
        return response;
    }
}