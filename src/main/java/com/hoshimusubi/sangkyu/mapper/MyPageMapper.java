package com.hoshimusubi.sangkyu.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.hoshimusubi.sangkyu.dto.*;

@Mapper
public interface MyPageMapper {
    // 내 프로필
    MyPageUserDto selectMyProfile(Long userId);

    // 내가 쓴 글
    List<MyPagePostDto> selectMyPosts(Long userId);

    // 내가 단 댓글
    List<MyPagePostDto> selectMyComments(Long userId);

    // 내가 좋아요한 글
    List<MyPagePostDto> selectMyLikes(Long userId);

    // 내가 스크랩한 글
    List<MyPagePostDto> selectMyScraps(Long userId);
}
