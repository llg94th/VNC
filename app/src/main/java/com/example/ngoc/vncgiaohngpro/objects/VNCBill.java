package com.example.ngoc.vncgiaohngpro.objects;

/**
 * Created by HUNGNTPH04073@FPT.EDU.VN on 6/20/2016.
 */
public class VNCBill {
    private int orderNum;
    private String id;
    private String city;
    private String province;
    private String area;
    private String address;
    private String customerName;
    private String phoneNum1;
    private String phoneNum2;
    private String productName;
    private String promotion;
    private String price;
    private String inventoryDays;
    private String employeer;
    private int status;
    private int point;
    private String note;
    private String seasion;
    private long updateTime;

    /** Tình trạng đơn hàng */
    public final static int DEAL_CANCED = -3;
    public final static int DEAL_REJECTED = -2;
    public final static int DEAL_FAILED = -1;
    public final static int DEAL_NONE = 0;
    public final static int DEAL_DELIVERY = 1;
    public final static int DEAL_CHECKED = 2;
    public final static int DEAL_COMPLETED = 3;

    public VNCBill() {
    }

    public VNCBill(int orderNum, String id, String city, String province, String area, String address, String customerName, String phoneNum1, String phoneNum2, String productName, String promotion, String price, String inventoryDays, String employeer, int status, int point, String note, String seasion, long updateTime) {
        this.orderNum = orderNum;
        this.id = id;
        this.city = city;
        this.province = province;
        this.area = area;
        this.address = address;
        this.customerName = customerName;
        this.phoneNum1 = phoneNum1;
        this.phoneNum2 = phoneNum2;
        this.productName = productName;
        this.promotion = promotion;
        this.price = price;
        this.inventoryDays = inventoryDays;
        this.employeer = employeer;
        this.status = status;
        this.point = point;
        this.note = note;
        this.seasion = seasion;
        this.updateTime = updateTime;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhoneNum1() {
        return phoneNum1;
    }

    public void setPhoneNum1(String phoneNum1) {
        this.phoneNum1 = phoneNum1;
    }

    public String getPhoneNum2() {
        return phoneNum2;
    }

    public void setPhoneNum2(String phoneNum2) {
        this.phoneNum2 = phoneNum2;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPromotion() {
        return promotion;
    }

    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getInventoryDays() {
        return inventoryDays;
    }

    public void setInventoryDays(String inventoryDays) {
        this.inventoryDays = inventoryDays;
    }

    public String getEmployeer() {
        return employeer;
    }

    public void setEmployeer(String employeer) {
        this.employeer = employeer;
    }

    public int getStatus() {
        return status;
    }

    public String getStringStatus(){
        switch (status){
            case DEAL_CANCED:
                return "Huỷ";
            case DEAL_REJECTED:
                return "Từ chối";
            case DEAL_FAILED:
                return "Thất bại";
            case DEAL_NONE:
                return "Chưa triển khai";
            case DEAL_CHECKED:
                return "Đã lấy hàng";
            case DEAL_DELIVERY:
                return "Đang triển khai";
            case DEAL_COMPLETED:
                return "Đã giao";
            default:
                return "";
        }
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getSeasion() {
        return seasion;
    }

    public void setSeasion(String seasion) {
        this.seasion = seasion;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }
}
