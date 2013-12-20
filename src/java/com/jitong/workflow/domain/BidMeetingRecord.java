package com.jitong.workflow.domain;

import java.sql.Blob;

public class BidMeetingRecord {
	private String id;
	private String itemId;
	private String title;
	private Blob meetingNote;
	private String meetingNoteExt;
	private String date;
	private String place;
	private String bidType;
	private String host;
	private String judge;//评委
	private String jjwJudge;//纪委
	private Blob judgeSummary;//评标办法
	private String judgeSummaryExt;
	private String bidBase;//标底
	private Blob judgeChengnuoshu;//评委廉政承诺书
	private String judgeChengnuoshuExt;
	private String winner;//中标单位
	private String winAmount;//中标金额
	/*private String bidderId;
	private String bidderName;
	private String bidderBiaoshuNo;
	private String bidderFarendaibiaoName;
	private String bidderWeituorenName;
	private String bidderWeituorenId;
	private String bidderBaojia;
	private String bidTimes;//报价次数
	private String isWin;*/
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Blob getMeetingNote() {
		return meetingNote;
	}
	public void setMeetingNote(Blob meetingNote) {
		this.meetingNote = meetingNote;
	}
	public String getMeetingNoteExt() {
		return meetingNoteExt;
	}
	public void setMeetingNoteExt(String meetingNoteExt) {
		this.meetingNoteExt = meetingNoteExt;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getBidType() {
		return bidType;
	}
	public void setBidType(String bidType) {
		this.bidType = bidType;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getJudge() {
		return judge;
	}
	public void setJudge(String judge) {
		this.judge = judge;
	}
	public String getJjwJudge() {
		return jjwJudge;
	}
	public void setJjwJudge(String jjwJudge) {
		this.jjwJudge = jjwJudge;
	}
	
	public String getBidBase() {
		return bidBase;
	}
	public void setBidBase(String bidBase) {
		this.bidBase = bidBase;
	}
	
	/*public String getBidderId() {
		return bidderId;
	}
	public void setBidderId(String bidderId) {
		this.bidderId = bidderId;
	}
	public String getBidderName() {
		return bidderName;
	}
	public void setBidderName(String bidderName) {
		this.bidderName = bidderName;
	}
	public String getBidderBiaoshuNo() {
		return bidderBiaoshuNo;
	}
	public void setBidderBiaoshuNo(String bidderBiaoshuNo) {
		this.bidderBiaoshuNo = bidderBiaoshuNo;
	}
	public String getBidderFarendaibiaoName() {
		return bidderFarendaibiaoName;
	}
	public void setBidderFarendaibiaoName(String bidderFarendaibiaoName) {
		this.bidderFarendaibiaoName = bidderFarendaibiaoName;
	}
	public String getBidderWeituorenName() {
		return bidderWeituorenName;
	}
	public void setBidderWeituorenName(String bidderWeituorenName) {
		this.bidderWeituorenName = bidderWeituorenName;
	}
	public String getBidderWeituorenId() {
		return bidderWeituorenId;
	}
	public void setBidderWeituorenId(String bidderWeituorenId) {
		this.bidderWeituorenId = bidderWeituorenId;
	}
	public String getBidderBaojia() {
		return bidderBaojia;
	}
	public void setBidderBaojia(String bidderBaojia) {
		this.bidderBaojia = bidderBaojia;
	}

	public String getBidTimes() {
		return bidTimes;
	}
	public void setBidTimes(String bidTimes) {
		this.bidTimes = bidTimes;
	}
	public String getIsWin() {
		return isWin;
	}
	public void setIsWin(String isWin) {
		this.isWin = isWin;
	}*/
	public Blob getJudgeSummary() {
		return judgeSummary;
	}
	public void setJudgeSummary(Blob judgeSummary) {
		this.judgeSummary = judgeSummary;
	}
	
	public Blob getJudgeChengnuoshu() {
		return judgeChengnuoshu;
	}
	public void setJudgeChengnuoshu(Blob judgeChengnuoshu) {
		this.judgeChengnuoshu = judgeChengnuoshu;
	}
	public String getJudgeSummaryExt() {
		return judgeSummaryExt;
	}
	public void setJudgeSummaryExt(String judgeSummaryExt) {
		this.judgeSummaryExt = judgeSummaryExt;
	}
	public String getJudgeChengnuoshuExt() {
		return judgeChengnuoshuExt;
	}
	public void setJudgeChengnuoshuExt(String judgeChengnuoshuExt) {
		this.judgeChengnuoshuExt = judgeChengnuoshuExt;
	}
	public String getWinner() {
		return winner;
	}
	public void setWinner(String winner) {
		this.winner = winner;
	}
	public String getWinAmount() {
		return winAmount;
	}
	public void setWinAmount(String winAmount) {
		this.winAmount = winAmount;
	}
	
}
