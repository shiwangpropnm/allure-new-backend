package com.allure.service.impl;

import java.io.IOException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.allure.service.ObjectStoreService;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;

@Service
public class AmazonObjectStoreServiceImpl implements ObjectStoreService {

	@Autowired
	private AmazonS3 amazonS3;

	@Value("${aws.s3.bucket.name}")
	private String bucket;

	@Value("${aws.s3.endpoint.url}")
	private String endpointUrl;

	@Autowired
	MessageSource messageSource;

	@Override
	public String uploadFile(String path, String fileName, MultipartFile file, boolean asDownloadable) {

		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType(file.getContentType());
		if (asDownloadable)
			objectMetadata.setContentDisposition("application");

		try {
			PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, path + fileName, file.getInputStream(),
					objectMetadata);
			AccessControlList acl = new AccessControlList();
			acl.grantPermission(GroupGrantee.AllUsers, Permission.Read); // all users or authenticated
			putObjectRequest.setAccessControlList(acl);
			PutObjectResult putObjectResult = amazonS3.putObject(putObjectRequest);
		} catch (AmazonServiceException e) {
			throw new RuntimeException(messageSource.getMessage("exception.cloud.file.upload", null, Locale.ENGLISH),
					e);
		} catch (IOException e) {
			throw new RuntimeException(
					messageSource.getMessage("exception.multipart.file.access", null, Locale.ENGLISH), e);
		}
		return getBlobUrl(path, fileName);
	}

	@Override
	public boolean deleteFile(String path, String fileName) {
		amazonS3.deleteObject(bucket, path + fileName);
		return true;
	}

	@Override
	public String getBlobUrl(String path, String fileName) {
		return endpointUrl + path + fileName;
	}

	@Override
	public boolean isBlobExist(String path, String fileName) {
		return amazonS3.doesObjectExist(bucket, path + fileName);
	}

}
