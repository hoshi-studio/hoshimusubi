package com.hoshimusubi.hanbeen.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hoshimusubi.hanbeen.dto.UserProfileDTO;

@Mapper
public interface HanbeenUserMapper {

    @Select("""
        SELECT 
            u.nickname,
            u.gender,
            u.user_id AS email,
            z.koname_ja AS zodiacNameJa,
            u.profile_pic AS profilePic
        FROM Users u
        JOIN Zodiac z ON u.zodiac_id = z.id
        WHERE u.id = #{userId}
    """)
    UserProfileDTO getUserProfileById(Long userId);
}
