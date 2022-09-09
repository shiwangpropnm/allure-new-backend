package com.allure.service.impl;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.allure.dao.SupportQuestionRepository;
import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.support.AskGeneralQuestionDTO;
import com.allure.entity.SupportQuestion;
import com.allure.service.ObjectStoreService;
import com.allure.service.SupportService;

@Service
public class SupportServiceImpl implements SupportService {

	@Autowired
	ObjectStoreService objectStoreService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	SupportQuestionRepository supportQuestionRepository;

	@Value("${allure.ask.question.path}")
	private String askQuestionPath;

	@Override
	public void postAskGeneralQuestion(LoggedInUserDTO loggedInUserDTO, AskGeneralQuestionDTO askGeneralQuestionDTO) {
		MultipartFile multipartFile = askGeneralQuestionDTO.getFile();
		String fileName = getSupportQuestionFileName(loggedInUserDTO.getLoggedInUserId(), multipartFile.getName());
		objectStoreService.uploadFile(askQuestionPath, fileName, multipartFile, false);

		SupportQuestion supportQuestion = new SupportQuestion();
		supportQuestion.setEmail(askGeneralQuestionDTO.getEmail());
		supportQuestion.setQuestion(askGeneralQuestionDTO.getQuestion());
		supportQuestion.setAttachmentKey(askQuestionPath + fileName);
		supportQuestionRepository.save(supportQuestion);
	}

	private String getSupportQuestionFileName(int loggedInUserId, String name) {
		return String.valueOf(loggedInUserId) + "-" + System.currentTimeMillis() + "-" + new Random().nextInt(10) + "-"
				+ name;
	}

}
