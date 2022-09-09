package com.allure.dto.support;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AskGeneralQuestionDTO {

	private MultipartFile file;
	
	private String question;
	
	private String email;
	
}
