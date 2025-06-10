package com.hoshimusubi.hanbeen.model;
//(메인 컨테이너)
import java.time.LocalDate;
import java.util.List;

public class MainPageVO {
    
    // 헤더 정보
    private String appTitle = "星結び";
    private String catLoginIconPath;
    
    // 12개 별자리 아이콘 리스트
    private List<ZodiacIconVO> zodiacIconList;
    
    // 오늘의 별자리 설명
    private TodayHoroscopeVO todayHoroscope;
    
    // VirtualSky 별자리 지도
    private VirtualSkyVO virtualSky;
    
    // 사용자 상태
    private UserStatusVO userStatus;
    
    // 푸터 정보
    private FooterInfoVO footerInfo;
    
    public MainPageVO() {}
    
    // Getters & Setters
    public String getAppTitle() { return appTitle; }
    public void setAppTitle(String appTitle) { this.appTitle = appTitle; }
    
    public String getCatLoginIconPath() { return catLoginIconPath; }
    public void setCatLoginIconPath(String catLoginIconPath) { this.catLoginIconPath = catLoginIconPath; }
    
    public List<ZodiacIconVO> getZodiacIconList() { return zodiacIconList; }
    public void setZodiacIconList(List<ZodiacIconVO> zodiacIconList) { this.zodiacIconList = zodiacIconList; }
    
    public TodayHoroscopeVO getTodayHoroscope() { return todayHoroscope; }
    public void setTodayHoroscope(TodayHoroscopeVO todayHoroscope) { this.todayHoroscope = todayHoroscope; }
    
    public VirtualSkyVO getVirtualSky() { return virtualSky; }
    public void setVirtualSky(VirtualSkyVO virtualSky) { this.virtualSky = virtualSky; }
    
    public UserStatusVO getUserStatus() { return userStatus; }
    public void setUserStatus(UserStatusVO userStatus) { this.userStatus = userStatus; }
    
    public FooterInfoVO getFooterInfo() { return footerInfo; }
    public void setFooterInfo(FooterInfoVO footerInfo) { this.footerInfo = footerInfo; }
}
