package com.hoshimusubi.hanbeen.model;
//(오늘의 별자리 설명)
import java.time.LocalDate;

public class TodayHoroscopeVO {
    
    private String todayZodiacName;        // 오늘의 별자리 이름
    private String description;            // 별자리 설명
    private String horoscopeText;         // 운세 내용
    private LocalDate currentDate;        // 현재 날짜
    private String zodiacPeriodInfo;      // 별자리 기간 정보
    
    public TodayHoroscopeVO() {}
    
    public TodayHoroscopeVO(String todayZodiacName, String description, String horoscopeText, LocalDate currentDate, String zodiacPeriodInfo) {
        this.todayZodiacName = todayZodiacName;
        this.description = description;
        this.horoscopeText = horoscopeText;
        this.currentDate = currentDate;
        this.zodiacPeriodInfo = zodiacPeriodInfo;
    }
    
    // Getters & Setters
    public String getTodayZodiacName() { return todayZodiacName; }
    public void setTodayZodiacName(String todayZodiacName) { this.todayZodiacName = todayZodiacName; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getHoroscopeText() { return horoscopeText; }
    public void setHoroscopeText(String horoscopeText) { this.horoscopeText = horoscopeText; }
    
    public LocalDate getCurrentDate() { return currentDate; }
    public void setCurrentDate(LocalDate currentDate) { this.currentDate = currentDate; }
    
    public String getZodiacPeriodInfo() { return zodiacPeriodInfo; }
    public void setZodiacPeriodInfo(String zodiacPeriodInfo) { this.zodiacPeriodInfo = zodiacPeriodInfo; }
}
