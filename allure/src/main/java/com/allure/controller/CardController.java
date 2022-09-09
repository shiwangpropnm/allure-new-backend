package com.allure.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.allure.constants.AllureResponseStatus;
import com.allure.dto.AllureResponseDTO;
import com.allure.dto.LoggedInUserDTO;
import com.allure.dto.card.GetLikesDTO;
import com.allure.dto.card.GetResourceLeftResponseDTO;
import com.allure.dto.card.GetSwipingCardsResponseDTO;
import com.allure.dto.card.GetTopPicksResponseDTO;
import com.allure.dto.card.GetUserMatchesResponseDTO;
import com.allure.dto.card.LikeByUserResponseDTO;
import com.allure.dto.card.PostCardLikeDTO;
import com.allure.dto.card.UserLikeReceivedResponseDTO;
import com.allure.service.CardService;
import com.allure.service.LikeService;
import com.allure.service.MatchService;

@RestController
@RequestMapping("v1/card")
public class CardController {

	@Autowired
	CardService cardService;

	@Autowired
	LikeService likeService;
	

	@Autowired
	MatchService matchService;

	@Autowired
	MessageSource messageSource;

	@GetMapping("/getSwipingCards")
	public ResponseEntity<AllureResponseDTO<GetSwipingCardsResponseDTO>> getSwipingCards(
			LoggedInUserDTO loggedInUserDTO, @RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {

		GetSwipingCardsResponseDTO getSwipingCardsResponseDTO = cardService.getSwipingCards(loggedInUserDTO, page,
				pageSize);

		return new ResponseEntity<AllureResponseDTO<GetSwipingCardsResponseDTO>>(
				new AllureResponseDTO<GetSwipingCardsResponseDTO>(getSwipingCardsResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.swiping.cards.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@GetMapping("/topPicks")
	public ResponseEntity<AllureResponseDTO<GetTopPicksResponseDTO>> getTopPicks(LoggedInUserDTO loggedInUserDTO,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		GetTopPicksResponseDTO getTopPicksResponseDTO = cardService.getTopPicks(loggedInUserDTO, page, pageSize);

		return new ResponseEntity<AllureResponseDTO<GetTopPicksResponseDTO>>(
				new AllureResponseDTO<>(getTopPicksResponseDTO, AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.top.picks.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping("/like")
	public ResponseEntity<AllureResponseDTO<Object>> profileLike(LoggedInUserDTO loggedInUserDTO,
			@RequestBody PostCardLikeDTO postCardLikeDTO) {

		likeService.postProfileLike(loggedInUserDTO, postCardLikeDTO);

		return new ResponseEntity<AllureResponseDTO<Object>>(
				new AllureResponseDTO<>(null, AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("post.card.like.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping("/getLikesByUser")
	public ResponseEntity<AllureResponseDTO<LikeByUserResponseDTO>> getLikesByUser(LoggedInUserDTO loggedInUserDTO,
			@RequestBody GetLikesDTO getLikesDTO) {

		LikeByUserResponseDTO likeByUserResponseDTO = likeService.getLikesByUser(loggedInUserDTO, getLikesDTO);

		return new ResponseEntity<AllureResponseDTO<LikeByUserResponseDTO>>(
				new AllureResponseDTO<LikeByUserResponseDTO>(likeByUserResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.user.likes.success",
								new Object[] { likeByUserResponseDTO.getLikeByUser().size() }, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@PostMapping("/getUserLike")
	public ResponseEntity<AllureResponseDTO<UserLikeReceivedResponseDTO>> getUserLike(LoggedInUserDTO loggedInUserDTO,
			@RequestBody GetLikesDTO getLikesDTO) {

		UserLikeReceivedResponseDTO userLikeReceivedResponseDTO = likeService.getUserLikes(loggedInUserDTO, getLikesDTO);

		return new ResponseEntity<AllureResponseDTO<UserLikeReceivedResponseDTO>>(
				new AllureResponseDTO<UserLikeReceivedResponseDTO>(userLikeReceivedResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.like.recieved.by.user.success",
								new Object[] { userLikeReceivedResponseDTO.getUserLikeReceived().size() },
								Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@GetMapping("/getMatchesofUser")
	public ResponseEntity<AllureResponseDTO<GetUserMatchesResponseDTO>> getMatchesOfUser(
			LoggedInUserDTO loggedInUserDTO) {

		GetUserMatchesResponseDTO userMatchesDTO = matchService.getMatchesOfUser(loggedInUserDTO);

		return new ResponseEntity<AllureResponseDTO<GetUserMatchesResponseDTO>>(
				new AllureResponseDTO<GetUserMatchesResponseDTO>(userMatchesDTO, AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.user.matches.success",
								new Object[] { userMatchesDTO.getUserMatches().size() }, Locale.ENGLISH)),
				HttpStatus.OK);
	}

	@GetMapping("/getResourceLeft")
	public ResponseEntity<AllureResponseDTO<GetResourceLeftResponseDTO>> getResourceLeft(
			LoggedInUserDTO loggedInUserDTO) {

		GetResourceLeftResponseDTO getResourceLeftResponseDTO = likeService.getResourceLeft(loggedInUserDTO);

		return new ResponseEntity<AllureResponseDTO<GetResourceLeftResponseDTO>>(
				new AllureResponseDTO<GetResourceLeftResponseDTO>(getResourceLeftResponseDTO,
						AllureResponseStatus.SUCCESS.getCode(),
						messageSource.getMessage("get.resource.left.success", null, Locale.ENGLISH)),
				HttpStatus.OK);
	}
}
