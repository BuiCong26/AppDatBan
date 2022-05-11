package com.example.appdatban.Adapter;

public class MonAn {

    private  String tenMonAn;
    private  String motaMonAn;
    private String Photo;


    public MonAn(String tenMonAn, String motaMonAn,String Photo) {
        this.tenMonAn = tenMonAn;
        this.motaMonAn = motaMonAn;
        this.Photo = Photo;

    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }

    public String getMotaMonAn() {
        return motaMonAn;
    }

    public void setMotaMonAn(String motaMonAn) {
        this.motaMonAn = motaMonAn;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }
}
