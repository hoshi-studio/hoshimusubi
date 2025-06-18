package com.hoshimusubi.suhwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoshimusubi.suhwa.dto.MessageDTO;
import com.hoshimusubi.suhwa.dto.UsersDTO;
import com.hoshimusubi.suhwa.mapper.MessageMapper;

@Service("MessageServiceImpl")
@Transactional
public class MessageServiceImpl implements MessageService{

	@Autowired
    private MessageMapper MessageMapper;
	
	@Override
    public UsersDTO getUserById(Long userId) {
        return MessageMapper.findUserById(userId);
    }
	
	@Override
    public void sendMessage(MessageDTO message) {
		MessageMapper.insertMessage(message);
    }
}
