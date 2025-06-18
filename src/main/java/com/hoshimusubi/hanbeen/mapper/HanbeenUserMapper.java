package com.hoshimusubi.hanbeen.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.hoshimusubi.hanbeen.dto.UserProfileDTO; // ← ✅ 이거 꼭 필요!

@Mapper
public interface HanbeenUserMapper {

    @Select("SELECT " +
    		"u.id As user_Id, "+
            "u.nickname, " +
            "u.gender, " +
            "u.user_id AS email, " +
            "z.koname_ja AS zodiacNameJa, " +
            "u.profile_pic AS profilePic " +
            "FROM Users u " +
            "JOIN Zodiac z ON u.zodiac_id = z.id " +
            "WHERE u.id = #{userId, jdbcType=NUMERIC}")
    UserProfileDTO getUserProfileById(int userId);
}