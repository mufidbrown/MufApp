package com.muf.base.controller;


import com.muf.core.engine.FileEngineService;
import com.muf.core.engine.TranslatorEngineService;
import com.muf.model.Response;
import com.muf.model.UploadFileModel;
import com.muf.model.ViewFileModel;
import com.muf.modules.module.repository.ModuleRepository;
import com.muf.modules.user.service.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
//import springfox.documentation.annotations.ApiIgnore;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.List;

@RestController
@RequestMapping("")
public abstract class BaseModuleController extends BaseController {

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private FileEngineService fileEngineService;

    @Autowired
    protected TranslatorEngineService translatorEngineService;

//    @Autowired
//    protected NumberGeneratorEngineBeanService numberGeneratorEngineBeanService;

    @Hidden
    @PostMapping(path = "/file/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<UploadFileModel> fileUpload(@RequestPart("file") MultipartFile file, @RequestPart("fileTypeCode") String fileTypeCode, @RequestPart(required = false) String croppedPoints) throws IOException {
        Response<UploadFileModel> response = fileEngineService.fileUpload(file, fileTypeCode, croppedPoints);
        return response;
    }

/*
    @ApiIgnore
    @PostMapping(path = "/file/upload-temp", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Response<UploadFileModel> fileUploadTemp(@RequestPart("file") MultipartFile file, @RequestPart("fileTypeCode") String fileTypeCode) throws IOException {
        Response<UploadFileModel> response = fileEngineService.fileUploadTemp(file, fileTypeCode);
        return response;
    }
*/

/*    @ApiIgnore
    @GetMapping("/file/view/{fileName}/{fileTypeCode}")
    public ResponseEntity<?> fileView(@PathVariable String fileName, @PathVariable String fileTypeCode, HttpServletRequest request) throws NoSuchFileException {

        ViewFileModel viewFileModel = new ViewFileModel();
        try {
            viewFileModel = fileEngineService.fileView(fileName, fileTypeCode, request);
        } catch (NoSuchFileException e) {
            logger.error("File << "+ fileName +" >> Not Found");
            return new ResponseEntity<>("app.msg.error.fileNotFound", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(viewFileModel.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + viewFileModel.getFileName())
                .body(viewFileModel.getData());
    }*/

/*    @ApiIgnore
    @PostMapping("/file/view-rar")
    public ResponseEntity<?> filesViewRAR(@RequestBody List<String> fileNameList, HttpServletRequest request) throws IOException {
        ViewFileModel viewFileModel = new ViewFileModel();
        try {
            viewFileModel = fileEngineService.fileViewRAR(fileNameList, request);
        } catch (NoSuchFileException e) {
            logger.error("File << "+ fileNameList.toString() +" >> Not Found");
            return new ResponseEntity<>("app.msg.error.fileNotFound", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(viewFileModel.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + viewFileModel.getFileName())
                .body(viewFileModel.getData());
    }*/

/*    @ApiIgnore
    @GetMapping("/thumbnail/view/{fileName}/{fileTypeCode}")
    public ResponseEntity<?> thumbnailView(@PathVariable String fileName, @PathVariable String fileTypeCode, HttpServletRequest request) throws NoSuchFileException {

        ViewFileModel viewFileModel = new ViewFileModel();
        try {
            viewFileModel = fileEngineService.thumbnailView(fileName, fileTypeCode, request);
        } catch (NoSuchFileException e) {
            logger.error("File << "+ fileName +" >> Not Found");
            return new ResponseEntity<>("app.msg.error.fileNotFound", HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(viewFileModel.getContentType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + viewFileModel.getFileName())
                .body(viewFileModel.getData());
    }*/

///*    @ApiIgnore
//    @GetMapping("/file/view-pm/{fileName}")
//    public Response<String> fileViewPM(@PathVariable String fileName, HttpServletRequest request) throws NoSuchFileException {
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        String strToken = CookieHelper.getCookies(AppConstant.COOKIES_KEY, request);
//        headers.set("Cookie", AppConstant.COOKIES_KEY + "=" + strToken);
//        HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
//        StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
//        String[] urlSplit = requestURL.toString().split("/api/");
//        String baseUrl = urlSplit[0];
//        String url = baseUrl + "/promisews/procurement/file/view/" + fileName;
//
//        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
//        logger.error("Response Download Doc from PM >>> ", response.getBody());
//
//        return Response.ok();
//    }*/

/*    @ApiIgnore
    @GetMapping("/template/view")
    public ResponseEntity<?> downloadTemplate(HttpServletRequest request) {
        *//* set graph *//*
        final EntityGraph<ModuleBean> moduleGraph = moduleBeanDAO.getEntityManager()
                .createEntityGraph(ModuleBean.class);
        final Subgraph<FileBean> fileGraph = moduleGraph.addSubgraph("file");
        fileGraph.addAttributeNodes("fileType");
        *//* end set graph *//*

        ModuleBean moduleBean = moduleBeanDAO.getByCode(getModuleCode(), moduleGraph);

        if (moduleBean.getFile() != null) {
            try {
                return fileView(moduleBean.getFile().getUploadedFileName(), moduleBean.getFile().getFileType().getCode(), request);
            } catch (NoSuchFileException e) {
                logger.error("File << "+ moduleBean.getFile().getUploadedFileName() +" >> Not Found");
                return new ResponseEntity<>("app.msg.error.fileNotFound", HttpStatus.NOT_FOUND);
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(null);
        }
    }*/

}
