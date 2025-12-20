package com.muf.modules.user.controller;

import com.muf.base.controller.BaseModuleController;
import com.muf.common.constant.AppConstant;
import com.muf.core.engine.FileEngineService;
import com.muf.model.Response;
import com.muf.model.UploadFileModel;
import com.muf.modules.user.dto.UserRequest;
import com.muf.modules.user.dto.UserResponse;
import com.muf.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
//@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController extends BaseModuleController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public UserResponse create(@RequestBody UserRequest userRequest) {
        return userService.createUser(userRequest);
    }

    @Override
    protected String getModuleCode() {
        return AppConstant.MCODE_USER;
    }

//    @PostMapping("/upload-image")
//    public Response<String> uploadImage(
//            @RequestPart MultipartFile file
//    ) throws IOException {
//
//        return fileEngineService.fileUpload(
//                file,
//                "PRODUCT_IMAGE"
//        );
//    }
}
