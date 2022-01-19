package com.example.meraphone.model;

public class Accessories {
    String itemId;

    String itemName;
    String itemPrice;
    public  Accessories(){};
    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemRating() {
        return itemRating;
    }

    public void setItemRating(String itemRating) {
        this.itemRating = itemRating;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getItemExtra() {
        return itemExtra;
    }

    public void setItemExtra(String itemExtra) {
        this.itemExtra = itemExtra;
    }

    String itemRating;
    String itemDesc;
    String itemStatus;
    String itemImage;
    String itemExtra;

    public Accessories(String itemId, String itemName, String itemPrice, String itemRating, String itemDesc, String itemStatus, String itemImage, String itemExtra) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemRating = itemRating;
        this.itemDesc = itemDesc;
        this.itemStatus = itemStatus;
        this.itemImage = itemImage;
        this.itemExtra = itemExtra;
    }


}


