package com.hoshimusubi.hanbeen.model;
// (푸터 정보)
public class FooterInfoVO {
    
    private String makerInfo = "星の結び・hoshiStudio";
    private String copyrightText;
    private String contactInfo;
    private String versionInfo;
    
    public FooterInfoVO() {}
    
    public FooterInfoVO(String makerInfo, String copyrightText, String contactInfo, String versionInfo) {
        this.makerInfo = makerInfo;
        this.copyrightText = copyrightText;
        this.contactInfo = contactInfo;
        this.versionInfo = versionInfo;
    }
    
    // Getters & Setters
    public String getMakerInfo() { return makerInfo; }
    public void setMakerInfo(String makerInfo) { this.makerInfo = makerInfo; }
    
    public String getCopyrightText() { return copyrightText; }
    public void setCopyrightText(String copyrightText) { this.copyrightText = copyrightText; }
    
    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }
    
    public String getVersionInfo() { return versionInfo; }
    public void setVersionInfo(String versionInfo) { this.versionInfo = versionInfo; }
}
