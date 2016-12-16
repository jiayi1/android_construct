package com.android.app.model;

import com.android.app.core.http.BaseRes;

/**
 * Created by yulong.liu on 2016/12/16.
 */


public class TestModel extends BaseRes {


    /**
     * deliveryEmployeeName :
     * noScanOrderCount : 16
     * totalOrderCount : 72
     * noScanItemCount : 31
     * totalItemCount : 125
     * statusName : 未执行完成
     */

    private String deliveryEmployeeName;
    private int noScanOrderCount;
    private int totalOrderCount;
    private int noScanItemCount;
    private int totalItemCount;
    private String statusName;

    public String getDeliveryEmployeeName() {
        return deliveryEmployeeName;
    }

    public void setDeliveryEmployeeName(String deliveryEmployeeName) {
        this.deliveryEmployeeName = deliveryEmployeeName;
    }

    public int getNoScanOrderCount() {
        return noScanOrderCount;
    }

    public void setNoScanOrderCount(int noScanOrderCount) {
        this.noScanOrderCount = noScanOrderCount;
    }

    public int getTotalOrderCount() {
        return totalOrderCount;
    }

    public void setTotalOrderCount(int totalOrderCount) {
        this.totalOrderCount = totalOrderCount;
    }

    public int getNoScanItemCount() {
        return noScanItemCount;
    }

    public void setNoScanItemCount(int noScanItemCount) {
        this.noScanItemCount = noScanItemCount;
    }

    public int getTotalItemCount() {
        return totalItemCount;
    }

    public void setTotalItemCount(int totalItemCount) {
        this.totalItemCount = totalItemCount;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
