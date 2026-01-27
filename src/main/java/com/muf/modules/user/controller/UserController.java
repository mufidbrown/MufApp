package com.muf.modules.user.controller;

import com.muf.base.controller.BaseModuleController;
import com.muf.common.constant.AppConstant;
import com.muf.core.engine.FileEngineService;
import com.muf.model.Response;
import com.muf.model.UploadFileModel;
import com.muf.modules.user.ChangePasswordRequest;
import com.muf.modules.user.CreateUserRequest;
import com.muf.modules.user.UpdatePersonalInfoRequest;
import com.muf.modules.user.UpdateUserRequest;
import com.muf.modules.user.dto.UserRequest;
import com.muf.modules.user.dto.UserResponse;
import com.muf.modules.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
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
//
//    // ADMIN ENDPOINTS
//    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {
//        return ResponseEntity.ok(userService.createUser(request));
//    }
//
//    @PutMapping("/{userId}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer userId, @RequestBody UpdateUserRequest request) {
//        return ResponseEntity.ok(userService.updateUser(userId, request));
//    }
//
//    @DeleteMapping("/{userId}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
//        userService.deleteUser(userId);
//        return ResponseEntity.ok("User deleted successfully");
//    }
//
//    @GetMapping("/{userId}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer userId) {
//        return ResponseEntity.ok(userService.getUserById(userId));
//    }
//
//    @GetMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<Page<UserResponse>> getAllUsers(Pageable pageable) {
//        return ResponseEntity.ok(userService.getAllUsers(pageable));
//    }
//
//    // USER SELF-SERVICE ENDPOINTS
//    @GetMapping("/me")
//    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication) {
//        return ResponseEntity.ok(userService.getCurrentUser(authentication.getName()));
//    }
//
//    @PutMapping("/me")
//    public ResponseEntity<UserResponse> updatePersonalInfo(Authentication authentication, @RequestBody UpdatePersonalInfoRequest request) {
//        return ResponseEntity.ok(userService.updatePersonalInfo(authentication.getName(), request));
//    }
//
//    @PostMapping("/me/change-password")
//    public ResponseEntity<String> changePassword(Authentication authentication, @RequestBody ChangePasswordRequest request) {
//        userService.changePassword(authentication.getName(), request);
//        return ResponseEntity.ok("Password changed successfully");
//    }


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
