package com.muf.modules.master.user.controller;

import com.muf.base.controller.BaseModuleController;
import com.muf.common.constant.AppConstant;
import com.muf.modules.master.user.dto.CreateUserRequest;
import com.muf.modules.master.user.dto.UpdatePersonalInfoRequest;
import com.muf.modules.master.user.dto.UpdateUserRequest;
import com.muf.modules.master.user.dto.UserResponse;
import com.muf.modules.user.dto.*;
import com.muf.modules.master.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseModuleController {

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected String getModuleCode() {
        return AppConstant.MCODE_USER;
    }

    // Admin Endpoints
    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(userService.createUser(createUserRequest));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Integer userId, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.updateUser(userId, updateUserRequest));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted.");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UserResponse>> getAllUsers(Pageable pageable){
        return ResponseEntity.ok(userService.getAllUsers(pageable));
    }

    //User Self-Service Endpoints
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(Authentication authentication){
        return ResponseEntity.ok(userService.getCurrentUser(authentication.getName()));
    }

    @PutMapping("/me")
    public ResponseEntity<UserResponse> updatePersonalInfo(Authentication authentication, @Valid @RequestBody UpdatePersonalInfoRequest updatePersonalInfoRequest){
        return ResponseEntity.ok(userService.updatePersonalInfo(authentication.getName(), updatePersonalInfoRequest));
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
