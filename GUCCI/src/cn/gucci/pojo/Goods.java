package cn.gucci.pojo;

import java.io.Serializable;

/**
 * 商品信息类（商品编号，商品名，商品数量，商品单价，图片路径，月销量
 * 商品描述，详细信息，商品分类(FK)）
 * @author Administrator
 *
 */
public class Goods implements Serializable{
	private int goodsId;
	private String goodsName;
	private int goodsCount;
	private int price;
	private String picPath;
	private int monthSale;
	private String goodsDesc;
	private String goodsInfo;
	private int goodsClassId;
	private int type;
	private int status;
	
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public int getGoodsCount() {
		return goodsCount;
	}
	public void setGoodsCount(int goodsCount) {
		this.goodsCount = goodsCount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPicPath() {
		return picPath;
	}
	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}
	public int getMonthSale() {
		return monthSale;
	}
	public void setMonthSale(int monthSale) {
		this.monthSale = monthSale;
	}
	public String getGoodsDesc() {
		return goodsDesc;
	}
	public void setGoodsDesc(String goodsDesc) {
		this.goodsDesc = goodsDesc;
	}
	public String getGoodsInfo() {
		return goodsInfo;
	}
	public void setGoodsInfo(String goodsInfo) {
		this.goodsInfo = goodsInfo;
	}
	public int getGoodsClassId() {
		return goodsClassId;
	}
	public void setGoodsClassId(int goodsClassId) {
		this.goodsClassId = goodsClassId;
	}
	
}
