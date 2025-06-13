// src/main/java/com/hoshimusubi/sangkyu/service/MemberService.java (실제 파일 경로와 패키지 일치)

// 1. 패키지 선언은 파일 최상단에 한 번만!
package com.hoshimusubi.sangkyu.service;

// 2. import 문은 패키지 선언 바로 아래, 클래스 선언 위에 모두 모아서!
//    MemberVO와 MemberMapper의 실제 패키지 경로에 맞게 수정해야 합니다.
//    만약 MemberVO가 com.hoshimusubi.domain.MemberVO 라면 그대로 두세요.
//    만약 MemberMapper가 com.hoshimusubi.mapper.MemberMapper 라면 그대로 두세요.
//    만약 이들이 com.hoshimusubi.sangkyu.domain.MemberVO 나 com.hoshimusubi.sangkyu.mapper.MemberMapper 라면
//    import com.hoshimusubi.sangkyu.domain.MemberVO;
//    import com.hoshimusubi.sangkyu.mapper.MemberMapper;
//    이렇게 수정해야 합니다. (아래 코드에는 일단 제가 이전 예시로 드렸던 경로로 유지하겠습니다.)

import com.hoshimusubi.sangkyu.domain.*;
import com.hoshimusubi.sangkyu.mapper.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID; // 고유한 파일명 생성을 위해

@Service
public class MemberService { // 클래스 선언은 한 번만, 그리고 중괄호는 클래스 끝에!

    @Autowired
    private MemberMapper memberMapper;

    // 프로필 이미지 업로드 및 DB 업데이트
    public String uploadProfileImage(int memberId, MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            return null; // 파일이 없는 경우
        }

        // 1. 서버에 이미지 파일 저장 경로 설정 (실제 배포 환경에 맞게 조정 필요)
        String uploadDir = "C:/hoshimusubi_uploads/profile/"; // 실제 서버의 파일 저장 경로로 변경!

        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs(); // 디렉토리가 없으면 생성
        }

        // 2. 파일명 중복 방지를 위한 고유한 파일명 생성 (UUID 사용)
        String originalFileName = file.getOriginalFilename();
        String fileExtension = "";
        if (originalFileName != null && originalFileName.contains(".")) {
            fileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        }
        String uuidFileName = UUID.randomUUID().toString() + fileExtension;

        File saveFile = new File(uploadPath, uuidFileName);

        // 3. 파일 저장
        file.transferTo(saveFile); // MultipartFile을 실제 파일로 저장

        // 4. DB에 이미지 경로 업데이트
        String dbPath = "/resources/upload/profile/" + uuidFileName;
        memberMapper.updateProfileImagePath(memberId, dbPath);

        return dbPath; // 새로 저장된 이미지 경로 반환
    }

    // 회원 정보 가져오기 (마이페이지 로딩 시 현재 프로필 사진 경로를 가져오기 위함)
    public MemberVO getMemberInfo(int memberId) {
        return memberMapper.getMemberById(memberId);
    }
}