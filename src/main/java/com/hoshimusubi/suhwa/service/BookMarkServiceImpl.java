package com.hoshimusubi.suhwa.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.hoshimusubi.suhwa.mapper.BookMarkMapper;

public class BookMarkServiceImpl implements BookMarkService{
	@Autowired
    private BookMarkMapper bookmarkMapper;

    @Override
    public void addBookmark(Long postId, Long userId) {
        bookmarkMapper.insertBookmark(postId, userId);
    }

    @Override
    public void removeBookmark(Long postId, Long userId) {
        bookmarkMapper.deleteBookmark(postId, userId);
    }

    @Override
    public boolean isPostBookmarkedByUser(Long postId, Long userId) {
        return bookmarkMapper.countBookmark(postId, userId) > 0;
    }

}
