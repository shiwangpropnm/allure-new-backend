package com.allure.service;

import org.springframework.web.multipart.MultipartFile;


public interface ObjectStoreService {

	public String uploadFile(String path, String fileName, MultipartFile file, boolean asDownloadable);

	public boolean deleteFile(String path, String fileName);

	public String getBlobUrl(String path, String fileName);

	public boolean isBlobExist(String path, String fileName);
}
