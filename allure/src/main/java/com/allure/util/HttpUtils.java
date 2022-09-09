package com.allure.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;

public class HttpUtils {

	  public static HttpHeaders getResponseHeaders(final HttpServletRequest httpRequest) {
	    final HttpHeaders responseHeaders = new HttpHeaders();
	    responseHeaders.add(com.google.common.net.HttpHeaders.CONTENT_TYPE,
	        httpRequest.getContentType());
	    return responseHeaders;
	  }

}
