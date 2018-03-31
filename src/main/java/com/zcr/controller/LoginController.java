package com.zcr.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zcr.service.CoreService;
import com.zcr.service.LoginService;

@RestController
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@GetMapping(value = "/wx")
	public String login(@RequestParam(name = "signature", required = false) String signature,
			@RequestParam(name = "nonce", required = false) String nonce,
			@RequestParam(name = "timestamp", required = false) String timestamp,
			@RequestParam(name = "echostr", required = false) String echostr) {
		logger.info("signature is {} , nonce is {} , timestamp is {} , echostr is {}", signature, nonce, timestamp,
				echostr);
		if (LoginService.checkSignature(signature, timestamp, nonce)) {
			return echostr;
		}
		return "";

	}

	@PostMapping(value = "/wx")
	public void dealPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		logger.info("signature is {} , nonce is {} , timestamp is {}", signature, nonce, timestamp);
		PrintWriter out = response.getWriter();
		if (LoginService.checkSignature(signature, timestamp, nonce)) {
			String respxml = CoreService.processRequest(request);
			logger.info("respxml is {}", respxml);
			out.write(respxml);
		}
		out.close();
		out = null;
	}
}
