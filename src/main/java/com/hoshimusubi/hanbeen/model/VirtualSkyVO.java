package com.hoshimusubi.hanbeen.model;
//(별자리 지도)
import java.time.LocalDateTime;

public class VirtualSkyVO {
    
    private String virtualSkyHtml;         // VirtualSky HTML 코드
    private String skyConfigJson;          // VirtualSky 설정 JSON
    private String exampleType;            // Example 5,7,8 중 선택
    private LocalDateTime currentTime;     // 현재 시각
    private String skyDescription;         // 하단 별자리 설명 텍스트
    
    public VirtualSkyVO() {}
    
    public VirtualSkyVO(String virtualSkyHtml, String skyConfigJson, String exampleType, LocalDateTime currentTime, String skyDescription) {
        this.virtualSkyHtml = virtualSkyHtml;
        this.skyConfigJson = skyConfigJson;
        this.exampleType = exampleType;
        this.currentTime = currentTime;
        this.skyDescription = skyDescription;
    }
    
    // Getters & Setters
    public String getVirtualSkyHtml() { return virtualSkyHtml; }
    public void setVirtualSkyHtml(String virtualSkyHtml) { this.virtualSkyHtml = virtualSkyHtml; }
    
    public String getSkyConfigJson() { return skyConfigJson; }
    public void setSkyConfigJson(String skyConfigJson) { this.skyConfigJson = skyConfigJson; }
    
    public String getExampleType() { return exampleType; }
    public void setExampleType(String exampleType) { this.exampleType = exampleType; }
    
    public LocalDateTime getCurrentTime() { return currentTime; }
    public void setCurrentTime(LocalDateTime currentTime) { this.currentTime = currentTime; }
    
    public String getSkyDescription() { return skyDescription; }
    public void setSkyDescription(String skyDescription) { this.skyDescription = skyDescription; }
}
