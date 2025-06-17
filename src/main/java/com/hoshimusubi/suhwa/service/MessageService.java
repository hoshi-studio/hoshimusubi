package com.hoshimusubi.suhwa.service;

import com.hoshimusubi.suhwa.dto.MessageDTO;
import com.hoshimusubi.suhwa.dto.UsersDTO;

public interface MessageService {

	void sendMessage(MessageDTO message);

	UsersDTO getUserById(Long userId);

}
