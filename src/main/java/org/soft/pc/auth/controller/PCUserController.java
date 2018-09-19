package org.soft.pc.auth.controller;

import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.soft.pc.auth.model.MbgUser;
import org.soft.pc.auth.model.UserAndRole;
import org.soft.pc.auth.model.UserUpdateFormModel;
import org.soft.pc.auth.service.PCUserService;
import org.soft.pc.mgt.common.SoftJsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;

@Controller
public class PCUserController {

	@Autowired
	private PCUserService userService;

	@RequestMapping(path = "/user/add", method = RequestMethod.POST)
	public ResponseEntity<Map> addUser(@RequestParam(required = true) String username,
			@RequestParam(required = true) String passwd, @RequestParam(required = true) String repasswd)
			throws NoSuchAlgorithmException {
		Map<String, String> result = new HashMap<String, String>();
		if (!passwd.equals(repasswd)) {
			result.put("msg", "password not sync");
			return new ResponseEntity<Map>(result, HttpStatus.BAD_REQUEST);
		}

		userService.addUser(username, passwd);
		result.put("msg", "success");
		return new ResponseEntity<Map>(result, HttpStatus.OK);
	}

	@RequestMapping(path = "/user/getAll", method = RequestMethod.GET)
	public ResponseEntity<String> getAllUsers() {
		List<UserAndRole> uList = userService.getAllUsers();
		String users_json = SoftJsonUtil.objectToJson(uList);
		String result = "get_users(" + users_json + ")";
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@RequestMapping(path = "/user/{uid}/update", method = RequestMethod.GET)
	public ResponseEntity<String> getUserUpdateForm(@PathVariable String uid,
			@RequestParam(required = false) String callback) {
		UserUpdateFormModel userUpdateFormModel = userService.getUserUpdateForm(uid);
		String result = SoftJsonUtil.objectToJson(userUpdateFormModel);
		if (!StringUtils.isEmpty(callback)) {
			result = callback + "(" + result + ")";
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@RequestMapping(path = "/user/update", method = RequestMethod.POST)
	public ResponseEntity<String> updateUser(@RequestBody MbgUser mbgUser) {
		userService.updateUser(mbgUser);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(path = "/user/login", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> login(@RequestParam String username, @RequestParam String passwd) {
		String userJson = null;
		try {
			userJson = userService.login(username, passwd);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		if (userJson != null)
			return new ResponseEntity<String>(userJson, HttpStatus.OK);
		else
			return new ResponseEntity<String>("Login Failed", HttpStatus.BAD_REQUEST);
	}
	
	@RequestMapping(path="/{token}/token", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<String> getUserToken(@PathVariable String token, @RequestParam(required=false) String callback) {
		String result = userService.getUserByToken(token);
		if(!StringUtils.isEmpty(callback)) {
			if(!StringUtils.isEmpty(result)) {
				result = callback + "(" + result + ")";
			}
		} else if(StringUtils.isEmpty(result)) {
			return new ResponseEntity<String>("Non-login", HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@RequestMapping(path="/{token}/logout", method=RequestMethod.GET)
	public ResponseEntity<String> logout(@PathVariable String token) {
		userService.logout(token);
		return new ResponseEntity<String>("Logout Success", HttpStatus.OK);
	}

}
