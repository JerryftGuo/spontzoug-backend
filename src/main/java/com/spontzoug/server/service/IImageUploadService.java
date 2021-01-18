package com.spontzoug.server.service;

import org.springframework.web.multipart.MultipartFile;

public interface IImageUploadService {
    void save(String province, String city, String industry, MultipartFile filePart);
}