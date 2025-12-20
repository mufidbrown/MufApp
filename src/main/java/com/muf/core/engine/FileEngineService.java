package com.muf.core.engine;

import com.muf.base.service.BaseEngineService;
import com.muf.common.constant.AppConstant;
import com.muf.common.constant.CoreConstant;
import com.muf.common.context.AppContextService;
import com.muf.common.helper.EncryptHelper;
import com.muf.model.Response;
import com.muf.model.ResponseStatusEnum;
import com.muf.model.UploadFileModel;
import com.muf.modules.file.entity.domain.File;
import com.muf.modules.file.entity.domain.FileType;
import com.muf.modules.file.repository.FileLocationRepository;
import com.muf.modules.file.repository.FileRepository;
import com.muf.modules.file.repository.FileTypeRepository;
import com.muf.modules.file.service.FileService;
import com.muf.modules.file.service.FileTypeService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FileEngineService extends BaseEngineService {


    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileTypeRepository fileTypeRepository;

    @Autowired
    private FileLocationRepository fileLocationRepository;

    @Autowired
    private FileTypeService fileTypeService;

    @Autowired
    private TranslatorEngineService translatorEngineService;

    @Autowired
    private FileService fileService;

    private static final String RAW_PREFIX = "RAW-";
    private static final String THUMBNAIL_SUFFIX = ".tmb";

    @Override
    protected String getModuleCode() {
        return AppConstant.MCODE_FILE_ENGINE;
    }


    public Response<UploadFileModel> fileUpload(
            MultipartFile file,
            String fileTypeCode,
            String croppedPoints
    ) throws IOException {

        UploadFileModel model = new UploadFileModel();

        /* =======================
         * 1. Ambil FileType (DATA-DRIVEN)
         * ======================= */
        FileType fileType = fileTypeService.getByCode(fileTypeCode);
        if (fileType == null) {
            return Response.error("FileType not found");
        }

        /* =======================
         * 2. Blacklist validation
         * ======================= */
        String originalName = file.getOriginalFilename();
        String[] blacklist = AppContextService
                .getParameterStringByCode(CoreConstant.ParameterEnum.BLACKLIST_FILE_EXTENSION)
                .split(",");

        for (String ext : blacklist) {
            if (originalName != null && originalName.toLowerCase().contains(ext.trim().toLowerCase())) {
                model.setFileName(originalName);
                model.setErrorMessage("Extension " + ext + " is not allowed");
                return Response.error(model.getErrorMessage(), model);
            }
        }

        /* =======================
         * 3. Extension validation
         * ======================= */
        String extension = getExtension(originalName);
        if (!fileType.getFileExtension().toLowerCase().contains(extension)) {
            return Response.error("File extension not allowed");
        }

        /* =======================
         * 4. Read bytes (+ crop)
         * ======================= */
        byte[] bytes = file.getBytes();

        if (croppedPoints != null && Boolean.TRUE.equals(fileType.getFileExtension())) {
            bytes = cropImage(bytes, croppedPoints);
        }

        /* =======================
         * 5. Generate filename
         * ======================= */
        String uploadedFileName = generateFileName(originalName, fileType);

        /* =======================
         * 6. Save physical file
         * ======================= */
        saveToDisk(
                bytes,
                fileType.getFileLocation().getPath(),
                uploadedFileName,
                fileType.getIsEncrypted()
        );

        /* =======================
         * 7. Save metadata DB
         * ======================= */
        File entity = new File();
        entity.setFileType(fileType);
        entity.setFileName(originalName);
        entity.setUploadedFileName(uploadedFileName);
        entity.setExtension(extension);
        entity.setSize((long) bytes.length);
        entity.setIsImage(fileType.getFileExtension().toLowerCase().contains(extension.toLowerCase()));

        fileService.save(entity);

        /* =======================
         * 8. Response
         * ======================= */
        model.setFileName(originalName);
        model.setUploadedFileName(uploadedFileName);
        model.setFileSize((long) bytes.length);
        model.setStatus(0);

        return Response.ok(model);
    }

    /* ========================================================= */

    private byte[] cropImage(byte[] source, String croppedPoints) throws IOException {
        String[] c = StringUtils.split(croppedPoints, ",");
        int x = (int) Double.parseDouble(c[0]);
        int y = (int) Double.parseDouble(c[1]);
        int w = (int) (Double.parseDouble(c[2]) - Double.parseDouble(c[0]));
        int h = (int) (Double.parseDouble(c[3]) - Double.parseDouble(c[1]));

        BufferedImage image = ImageIO.read(new ByteArrayInputStream(source));
        BufferedImage cropped = image.getSubimage(x, y, w, h);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(cropped, "png", baos);
        return baos.toByteArray();
    }

    private void saveToDisk(byte[] data, String path, String fileName, Boolean encrypted) throws IOException {
        if (Boolean.TRUE.equals(encrypted)) {
            data = EncryptHelper.binaryEncrypt(data);
        }

        Path target = Paths.get(path);
        if (!Files.exists(target)) {
            Files.createDirectories(target);
        }

        Files.write(target.resolve(fileName), data);
    }

    private String generateFileName(String original, FileType fileType) {
        String uuid = UUID.randomUUID().toString();
        return Boolean.TRUE.equals(fileType.getIsEncrypted())
                ? uuid + "_" + original
                : RAW_PREFIX + uuid + "_" + original;
    }

    private String getExtension(String name) {
        return name.substring(name.lastIndexOf('.') + 1).toLowerCase();
    }
}


/*

@Service
@Transactional
public class FileEngineService extends BaseEngineService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileTypeRepository fileTypeRepository;

    @Autowired
    private FileLocationRepository fileLocationRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private FileTypeService fileTypeService;


    @Override
    protected String getModuleCode() {
        return AppConstant.MCODE_FILE_ENGINE;
    }

    private static final String RAW_PREFIX = "RAW-";
    private static final String THUMBNAIL_SUFFIX = ".tmb";

    public Response<UploadFileModel> fileUpload(
            MultipartFile multipartFile,
            String fileTypeCode,
            String croppedPoints
    ) throws IOException {

        File file = upload(multipartFile, fileTypeCode);

        UploadFileModel model = mapToUploadFileModel(file);

        return Response.ok(model);
    }


    public File upload(MultipartFile multipartFile, String fileTypeCode) throws IOException {

        // 1. Ambil FileType
        FileType fileType = fileTypeService.getByCode(fileTypeCode);

        // 2. Validasi extension
        String extension = getExtension(multipartFile.getOriginalFilename());
        if (!fileType.getFileExtension().toLowerCase().contains(extension.toLowerCase())) {
            throw new RuntimeException("Invalid file extension");
        }

        // 3. Validasi size
        if (multipartFile.getSize() > fileType.getMaxSize()) {
            throw new RuntimeException("File size exceeded");
        }

        // 4. Generate uploaded file name
        String uploadedFileName = generateFileName(multipartFile.getOriginalFilename(), fileType);

        // 5. Simpan ke disk
        saveToDisk(
                multipartFile.getBytes(),
                fileType.getFileLocation().getPath(),
                uploadedFileName,
                fileType.getIsEncrypted()
        );

        // 6. Simpan metadata ke DB
        File file = new File();
        file.setFileType(fileType);
        file.setFileName(multipartFile.getOriginalFilename());
        file.setUploadedFileName(uploadedFileName);
        file.setExtension(extension);
        file.setSize(multipartFile.getSize());
        file.setIsImage(isImage(extension));

        return fileService.save(file);
    }




    /// /////////download/get file
    public byte[] download(String uploadedFileName) throws IOException {

        File file = fileService.getByUploadedFileName(uploadedFileName);
        if (file == null) {
            throw new FileNotFoundException();
        }

        FileType fileType = file.getFileType();
        Path path = Paths.get(fileType.getFileLocation().getPath(), uploadedFileName);

        byte[] data = Files.readAllBytes(path);

        if (Boolean.TRUE.equals(fileType.getIsEncrypted())) {
            data = EncryptHelper.binaryDecrypt(data);
        }

        return data;
    }



    private void saveToDisk(byte[] data, String path, String fileName, Boolean encrypted) throws IOException {

        if (Boolean.TRUE.equals(encrypted)) {
            data = EncryptHelper.binaryEncrypt(data);
        }

        Path target = Paths.get(path);
        if (!Files.exists(target)) {
            Files.createDirectories(target);
        }

        Files.write(target.resolve(fileName), data);
    }

    private String generateFileName(String originalName, FileType fileType) {
        if (Boolean.TRUE.equals(fileType.getIsEncrypted())) {
            return UUID.randomUUID().toString() + "_" + originalName;
        }
        return RAW_PREFIX + UUID.randomUUID() + "_" + originalName;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1);
    }

    private boolean isImage(String ext) {
        return List.of("jpg","jpeg","png","gif").contains(ext.toLowerCase());
    }
}
*/
