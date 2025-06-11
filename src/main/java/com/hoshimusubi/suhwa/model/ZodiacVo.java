package com.hoshimusubi.suhwa.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ZodiacVo {
	String codename;     // 영문 별자리 이름 (예: aries)
    String konameJa;     // 일문 별자리 이름 (예: おひつじ座)
    String symbol;
}
