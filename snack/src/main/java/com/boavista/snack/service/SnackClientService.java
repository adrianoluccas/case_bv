package com.boavista.snack.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client")
@Service
public class SnackClientService {

	@RequestMapping(method = RequestMethod.GET)
    @ResponseBody
	public String getSnacks() {
		return "<html><body><h1>API IS ON!</h1><img src='https://upload.wikimedia.org/wikipedia/commons/thumb/8/8f/Stevie_Wonder_1994.jpg/220px-Stevie_Wonder_1994.jpg'></body></html>";	
	}
	
}
