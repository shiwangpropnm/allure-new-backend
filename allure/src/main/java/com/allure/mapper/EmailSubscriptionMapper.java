package com.allure.mapper;

import org.mapstruct.Mapper;

import com.allure.dto.usersetting.GetEmailSubscriptionDTO;
import com.allure.entity.EmailSubscription;
import com.allure.entity.Users;

@Mapper(componentModel = "spring")
public interface EmailSubscriptionMapper {

	public GetEmailSubscriptionDTO emailSubscriptionAndUserToGetEmailSubscriptionDTO(
			EmailSubscription emailSubscription, Users user);

}
