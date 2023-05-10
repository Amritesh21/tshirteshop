package com.tshirtShop.serverSide.publicAPIs.DTO;

import java.util.List;

public class PublicImageList {

    List<String> publicImageList;

    public PublicImageList(List<String> publicImageList) {
        this.publicImageList = publicImageList;
    }

    public List<String> getPublicImageList() {
        return publicImageList;
    }

    public void setPublicImageList(List<String> publicImageList) {
        this.publicImageList = publicImageList;
    }
}
