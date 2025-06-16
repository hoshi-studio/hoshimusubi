package com.hoshimusubi.hanbeen.model;
// (12개 별자리 아이콘)
public class ZodiacIconVO {
    
    private String zodiacName;      // 별자리 이름 (한글)
    private String zodiacNameJp;    // 별자리 이름 (일본어)
    private String iconPath;        // 아이콘 이미지 경로
    private String boardUrl;        // 게시판 URL
    private int zodiacIndex;        // 별자리 순서 (1-12)
    private String zodiacPeriod;    // 별자리 기간
    
    public ZodiacIconVO() {}
    
    public ZodiacIconVO(String zodiacName, String zodiacNameJp, String iconPath, String boardUrl, int zodiacIndex, String zodiacPeriod) {
        this.zodiacName = zodiacName;
        this.zodiacNameJp = zodiacNameJp;
        this.iconPath = iconPath;
        this.boardUrl = boardUrl;
        this.zodiacIndex = zodiacIndex;
        this.zodiacPeriod = zodiacPeriod;
    }
    
    // Getters & Setters
    public String getZodiacName() { return zodiacName; }
    public void setZodiacName(String zodiacName) { this.zodiacName = zodiacName; }
    
    public String getZodiacNameJp() { return zodiacNameJp; }
    public void setZodiacNameJp(String zodiacNameJp) { this.zodiacNameJp = zodiacNameJp; }
    
    public String getIconPath() { return iconPath; }
    public void setIconPath(String iconPath) { this.iconPath = iconPath; }
    
    public String getBoardUrl() { return boardUrl; }
    public void setBoardUrl(String boardUrl) { this.boardUrl = boardUrl; }
    
    public int getZodiacIndex() { return zodiacIndex; }
    public void setZodiacIndex(int zodiacIndex) { this.zodiacIndex = zodiacIndex; }
    
    public String getZodiacPeriod() { return zodiacPeriod; }
    public void setZodiacPeriod(String zodiacPeriod) { this.zodiacPeriod = zodiacPeriod; }
}
