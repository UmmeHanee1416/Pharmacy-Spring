package com.finalProject.FinalProject.service;


import com.finalProject.FinalProject.dto.FileInfoDTO;
import com.finalProject.FinalProject.entity.FileData;
import com.finalProject.FinalProject.entity.ImageData;
import com.finalProject.FinalProject.repository.FileDataRepository;
import com.finalProject.FinalProject.repository.StorageRepository;
import com.finalProject.FinalProject.util.ImageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StorageService {

    @Autowired
    private StorageRepository repository;

    @Autowired
    private FileDataRepository fileDataRepository;

    @Value("${file.path}")
    private String FOLDER_PATH;

    public FileData uploadImageToFileSystem(MultipartFile file) throws IOException {
        String filePath = FOLDER_PATH + file.getOriginalFilename();
        FileData fileData=fileDataRepository.save(
                FileData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .filePath(filePath).build()
        );
        file.transferTo(new File(filePath));

        if (fileData != null) {
            return fileData;
        }
        return null;
    }

    public byte[] downloadImageFromFileSystem(String fileName) throws IOException {
//        Optional<FileData> fileData = fileDataRepository.findByName(fileName);
        FileData fileData = fileDataRepository.findAllSortedByNameUsingNative(fileName);
        String filePath=fileData.getFilePath();
//        String filePath=fileData.get().getFilePath();
        byte[] images = Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    public List<FileInfoDTO> getAllImage() throws IOException {
        List<FileInfoDTO> fileInfoDTOS = new ArrayList<>();
        List<FileData> fileDataList = fileDataRepository.findAll();
        fileDataList.forEach(fileData -> {
            try {
                String filePath=fileData.getFilePath();
                byte[] images = Files.readAllBytes(new File(filePath).toPath());
                FileInfoDTO fileInfoDTO = new FileInfoDTO(fileData.getName(), fileData.getFilePath(), images);
                fileInfoDTOS.add(fileInfoDTO);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        return fileInfoDTOS;
    }

    public FileData downloadImageByID(Long fileName) {
        FileData fileData = fileDataRepository.findById(fileName).get();
        return fileData;
    }
}
