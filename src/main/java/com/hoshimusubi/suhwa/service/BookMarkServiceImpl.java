package com.hoshimusubi.suhwa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hoshimusubi.suhwa.dto.UsersDTO;
import com.hoshimusubi.suhwa.mapper.BookMarkMapper;

@Service("BookMarkServiceImpl")
@Transactional
public class BookMarkServiceImpl implements BookMarkService{
	@Autowired
    private BookMarkMapper bookmarkMapper;

    @Override
    public void addBookmark(Long postId, UsersDTO userId) {
        bookmarkMapper.insertBookmark(postId, userId);
    }

    @Override
    public void removeBookmark(Long postId, UsersDTO userId) {
        bookmarkMapper.deleteBookmark(postId, userId);
    }

    @Override
    public boolean isPostBookmarkedByUser(Long postId, UsersDTO userId) {
        return bookmarkMapper.countBookmark(postId, userId);
    }

}
