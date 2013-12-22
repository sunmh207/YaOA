package com.jitong.workflow.domain;

import java.sql.Blob;

import com.jitong.common.form.JTField;

public class RecommendBidder {
	private String id;
	private String itemId;
	private String name;
	private Blob doc;
	private String docExt;
	private String biaoshubianhao;//标书编号	
	private String qiyezizhi;//企业资质、
	private String yingyezhizhao;//营业执照
	private String farendaibiaoName;//法人代表
	private String weituorenName;//委托人
	private String weituorenId;//委托人身份证
	private String baojia1;//报价1
	private String baojia2;//报价2
	private String baojia3;//报价3
	private String score;//得分
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	@JTField(chineseName="参加投标单位",order=5)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Blob getDoc() {
		return doc;
	}
	public void setDoc(Blob doc) {
		this.doc = doc;
	}
	public String getDocExt() {
		return docExt;
	}
	public void setDocExt(String docExt) {
		this.docExt = docExt;
	}
	
	public String getBiaoshubianhao() {
		return biaoshubianhao;
	}
	public void setBiaoshubianhao(String biaoshubianhao) {
		this.biaoshubianhao = biaoshubianhao;
	}
	@JTField(chineseName="企业资质等级和证号",order=10)
	public String getQiyezizhi() {
		return qiyezizhi;
	}
	public void setQiyezizhi(String qiyezizhi) {
		this.qiyezizhi = qiyezizhi;
	}
	
	@JTField(chineseName="营业执照/注册号",order=15)
	public String getYingyezhizhao() {
		return yingyezhizhao;
	}
	public void setYingyezhizhao(String yingyezhizhao) {
		this.yingyezhizhao = yingyezhizhao;
	}
	
	@JTField(chineseName="法人代表",order=20)
	public String getFarendaibiaoName() {
		return farendaibiaoName;
	}
	public void setFarendaibiaoName(String farendaibiaoName) {
		this.farendaibiaoName = farendaibiaoName;
	}
	
	@JTField(chineseName="委托人姓名",order=25)
	public String getWeituorenName() {
		return weituorenName;
	}
	public void setWeituorenName(String weituorenName) {
		this.weituorenName = weituorenName;
	}
	
	@JTField(chineseName="委托人身份证",order=30)
	public String getWeituorenId() {
		return weituorenId;
	}
	public void setWeituorenId(String weituorenId) {
		this.weituorenId = weituorenId;
	}
	
	@JTField(chineseName="第一次报价",order=35)
	public String getBaojia1() {
		return baojia1;
	}
	public void setBaojia1(String baojia1) {
		this.baojia1 = baojia1;
	}
	@JTField(chineseName="第二次报价",order=40)
	public String getBaojia2() {
		return baojia2;
	}
	public void setBaojia2(String baojia2) {
		this.baojia2 = baojia2;
	}
	
	@JTField(chineseName="第三次报价",order=45)
	public String getBaojia3() {
		return baojia3;
	}
	
	public void setBaojia3(String baojia3) {
		this.baojia3 = baojia3;
	}
	
	@JTField(chineseName="得分",order=50)
	public String getScore() {
		return score;
	}
	
	public void setScore(String score) {
		this.score = score;
	}
	
}
