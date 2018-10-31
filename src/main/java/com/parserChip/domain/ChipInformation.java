package com.parserChip.domain;

public class ChipInformation {
    private String imgSrc;
    private String information;
    private String idElement;

    public ChipInformation() {
    }

    public ChipInformation(String imgSrc, String information, String idElement) {
        this.imgSrc = imgSrc;
        this.information = information;
        this.idElement = idElement;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getIdElement() {
        return idElement;
    }

    public void setIdElement(String idElement) {
        this.idElement = idElement;
    }
}
