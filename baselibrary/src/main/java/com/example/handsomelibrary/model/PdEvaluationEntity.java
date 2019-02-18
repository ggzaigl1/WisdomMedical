package com.example.handsomelibrary.model;

/**
 * Created by Stefan on 2018/12/19 10:44
 */
public class PdEvaluationEntity {

    public PdEvaluationEntity(Long orderId, Long goodsId, Long cusId, String evaContent, Integer evaStar) {
        this.orderId = orderId;
        this.goodsId = goodsId;
        this.cusId = cusId;
        this.evaContent = evaContent;
        this.evaStar = evaStar;
    }

    /**
     * 订单ID
     */
    private Long orderId;
    /**
     * 商品ID
     */
    private Long goodsId;
    /**
     * 用户ID
     */
    private Long cusId;
    /**
     * 评价内容
     */
    private String evaContent;
    /**
     * 打分
     */
    private Integer evaStar;


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public long getCusId() {
        return cusId;
    }

    public void setCusId(long cusId) {
        this.cusId = cusId;
    }

    public String getEvaContent() {
        return evaContent;
    }

    public void setEvaContent(String evaContent) {
        this.evaContent = evaContent;
    }

    public Integer getEvaStar() {
        return evaStar;
    }

    public void setEvaStar(Integer evaStar) {
        this.evaStar = evaStar;
    }
}
