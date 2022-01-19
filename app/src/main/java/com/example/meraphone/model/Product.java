package com.example.meraphone.model;

import android.graphics.Bitmap;

public class Product extends Accessories {
    String itemId;
    String itemCategory;
    String itemName;
    String itemPrice;
    String itemRating;
    String itemModelNo;
    String itemFrontCamera;
    String itemBackCamera;
    String itemHybridSimSlot;
    String itemOtg;
    String itemMultiTouch;
    String itemStatus;
    String itemImage;
    String itemExtra;

    public Product() {
    }

    public Product(String id, String category, String itemName, String itemPrice, String itemRating, String itemModelNo, String itemFrontCamera, String itemBackCamera, String itemHybridSimSlot, String itemOtg, String itemMultiTouch, String itemStatus, String itemImage, String itemExtra) {
        this.itemId = id;
        this.itemCategory = category;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemRating = itemRating;
        this.itemModelNo = itemModelNo;
        this.itemFrontCamera = itemFrontCamera;
        this.itemBackCamera = itemBackCamera;
        this.itemHybridSimSlot = itemHybridSimSlot;
        this.itemOtg = itemOtg;
        this.itemMultiTouch = itemMultiTouch;
        this.itemStatus = itemStatus;
        this.itemImage = itemImage;
        this.itemExtra = itemExtra;
    }

    public String getId() {
        return itemId;
    }

    public void setId(String id) {
        this.itemId = id;
    }

    public String getCategory() {
        return itemCategory;
    }

    public void setCategory(String category) {
        this.itemCategory = category;
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

    public String getItemModelNo() {
        return itemModelNo;
    }

    public void setItemModelNo(String itemModelNo) {
        this.itemModelNo = itemModelNo;
    }

    public String getItemFrontCamera() {
        return itemFrontCamera;
    }

    public void setItemFrontCamera(String itemFrontCamera) {
        this.itemFrontCamera = itemFrontCamera;
    }

    public String getItemBackCamera() {
        return itemBackCamera;
    }

    public void setItemBackCamera(String itemBackCamera) {
        this.itemBackCamera = itemBackCamera;
    }

    public String getItemHybridSimSlot() {
        return itemHybridSimSlot;
    }

    public void setItemHybridSimSlot(String itemHybridSimSlot) {
        this.itemHybridSimSlot = itemHybridSimSlot;
    }

    public String getItemOtg() {
        return itemOtg;
    }

    public void setItemOtg(String itemOtg) {
        this.itemOtg = itemOtg;
    }

    public String getItemMultiTouch() {
        return itemMultiTouch;
    }

    public void setItemMultiTouch(String itemMultiTouch) {
        this.itemMultiTouch = itemMultiTouch;
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
}
