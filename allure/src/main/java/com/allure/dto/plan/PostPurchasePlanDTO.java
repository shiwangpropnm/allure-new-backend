package com.allure.dto.plan;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostPurchasePlanDTO {

	private Integer planId;
	
	private Integer paymentStructureId;
	
	private String transactionId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date transactionOn;
	
	private Boolean isSuccess;
}
