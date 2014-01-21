package com.jitong.oa.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jitong.common.action.JITActionBase;
import com.jitong.common.exception.JTException;
import com.jitong.common.util.DateUtil;
import com.jitong.common.util.StringUtil;
import com.jitong.oa.domain.Item;
import com.jitong.oa.service.ItemService;
import com.jitong.workflow.domain.BidMeetingRecord;
import com.jitong.workflow.domain.ItemBid;
import com.jitong.workflow.domain.ItemFinish;
import com.jitong.workflow.domain.RecommendBidder;
import com.opensymphony.xwork2.Preparable;

public class ExportWordAction extends JITActionBase implements Preparable {
	private static ItemService service;
	private Item item;
	private ItemFinish itemFinish;
	private ItemBid bid;
	private List<RecommendBidder> recommendBidderList;

	public void prepare() throws JTException {
		if (service == null) {
			service = new ItemService();
		}
		if (item != null && item.getId() != null) {
			item = service.findItem(item.getId());
			bid = (ItemBid) service.findBoById(ItemBid.class, item.getId());
		}
		if (bid != null && bid.getItemId() != null) {
			bid = (ItemBid) service.findBoById(ItemBid.class, bid.getItemId());
		}
		
		
	}

	/**
	 * 导出立项表
	 * 
	 * @return
	 */
	public String exportSetup() throws JTException{
		recommendBidderList = (List<RecommendBidder>) service.queryByHql("from RecommendBidder bidder where bidder.itemId='" + item.getId() + "'");
		
		Map dataMap = new HashMap();
		String applyDate="";
		try{
			applyDate=DateUtil.convertDateFormat(bid.getApplyDate(), "yyyy-MM-dd", "yyyy年MM月d日");
		}catch(Exception e){
			e.printStackTrace();
		}
		dataMap.put("hostDept", bid.getHostDept());
		dataMap.put("applyDate", applyDate);
		dataMap.put("planAmount", bid.getPlanAmount());
		dataMap.put("bidItemName", bid.getBidItemName());
		dataMap.put("refNumber", bid.getRefNumber());
		dataMap.put("moneySource", bid.getMoneySource());
		dataMap.put("itemSummary", bid.getItemSummary());
		dataMap.put("planBidDate", bid.getPlanBidDate());
		dataMap.put("agentPersonPhone", bid.getAgentPersonPhone());
		
		String recommendBidders="";
		if(recommendBidderList!=null){
			for(RecommendBidder rb:recommendBidderList){
				recommendBidders+=rb.getName()+"<br>";
			}
		}
		dataMap.put("recommendBidderList", recommendBidders);
		
		dataMap.put("hostDeptComments", bid.getHostDeptComments());
		dataMap.put("responsiblePerson", bid.getResponsiblePerson());
		dataMap.put("bidDate", bid.getBidDate());
		dataMap.put("bidType", bid.getBidType());
		dataMap.put("zhaobiaoDeptAgentPerson", bid.getZhaobiaoDeptAgentPerson());
		dataMap.put("zhaobiaoDeptComments", bid.getZhaobiaoDeptComments());
		dataMap.put("zhaobiaoDeptResponsiblePerson", bid.getZhaobiaoDeptResponsiblePerson());
		dataMap.put("jjwDeptComments", bid.getJjwDeptComments());
		dataMap.put("jjwDeptResponsiblePerson", bid.getJjwDeptResponsiblePerson());
		
		session.put("dataMap", dataMap);
		session.put("template", "file"+File.separator + "setup_template.mht");
		return "export";
	}
	/**
	 * 导出结项表
	 * 
	 * @return
	 */
	public String exportFinish() throws JTException{
		if (itemFinish != null && itemFinish.getId() != null) {
			itemFinish = (ItemFinish) service.findBoById(ItemFinish.class, itemFinish.getId());
		}
		
		Map dataMap = new HashMap();
		String	currentDate=DateUtil.getCurrentTime("yyyy年MM月d日");
		dataMap.put("currentDate", currentDate);
		
		dataMap.put("finishItemName", itemFinish.getFinishItemName());
		dataMap.put("investAmount", itemFinish.getInvestAmount());
		dataMap.put("biaodiAmount", itemFinish.getBiaodiAmount());
		dataMap.put("zhongbiaoPrice", itemFinish.getZhongbiaoPrice());
		dataMap.put("zhongbiaoCompany", itemFinish.getZhongbiaoCompany());
		dataMap.put("jingbiaoDate", itemFinish.getJingbiaoDate());
		dataMap.put("bidParticipants", itemFinish.getBidParticipants());
		dataMap.put("finishDate", itemFinish.getFinishDate());
		dataMap.put("contractSignDate", itemFinish.getContractSignDate());
		dataMap.put("sponsorDeptRespPers", itemFinish.getSponsorDeptRespPers());
		dataMap.put("jingbanPers", itemFinish.getJingbanPers());
		dataMap.put("finishSummary", itemFinish.getFinishSummary());
		dataMap.put("leadComments", itemFinish.getLeadComments());
		dataMap.put("lead", itemFinish.getLead());
		dataMap.put("jjwComments", itemFinish.getJjwComments());
		dataMap.put("jjw", itemFinish.getJjw());
		dataMap.put("finishNote", itemFinish.getFinishNote());
		
		session.put("dataMap", dataMap);
		session.put("template", "file"+File.separator + "finish_template.mht");
		return "export";
	}

	public String exportSupervision() throws JTException{
		recommendBidderList = (List<RecommendBidder>) service.queryByHql("from RecommendBidder bidder where bidder.itemId='" + item.getId() + "'");
		List<BidMeetingRecord> bidMeetingRecordList = (List<BidMeetingRecord>)service.queryByHql("from BidMeetingRecord record where record.itemId='"+item.getId()+"' order by record.date desc");
		Map dataMap = new HashMap();
		StringBuffer rows=new StringBuffer();
		for(RecommendBidder bidder:recommendBidderList ){
			String value=ROW;
			value=value.replaceAll("\\$name\\$", StringUtil.fillNull(bidder.getName()));
			value=value.replaceAll("\\$qiyezizhi\\$", StringUtil.fillNull(bidder.getQiyezizhi()));
			value=value.replaceAll("\\$yingyezhizhao\\$", StringUtil.fillNull(bidder.getYingyezhizhao()));
			value=value.replaceAll("\\$farendaibiaoName\\$", StringUtil.fillNull(bidder.getFarendaibiaoName()));
			value=value.replaceAll("\\$weituorenName\\$", StringUtil.fillNull(bidder.getWeituorenName()));
			value=value.replaceAll("\\$weituorenId\\$", StringUtil.fillNull(bidder.getWeituorenId()));
			value=value.replaceAll("\\$baojia1\\$", StringUtil.fillNull(bidder.getBaojia1()));
			value=value.replaceAll("\\$baojia2\\$", StringUtil.fillNull(bidder.getBaojia2()));
			value=value.replaceAll("\\$baojia3\\$", StringUtil.fillNull(bidder.getBaojia3()));
			value=value.replaceAll("\\$score\\$", StringUtil.fillNull(bidder.getScore()));
			rows.append(value);
		}
		dataMap.put("ROW", rows.toString());
		if(bidMeetingRecordList!=null&&!bidMeetingRecordList.isEmpty()){
			BidMeetingRecord record = bidMeetingRecordList.get(0);
			dataMap.put("meeting.date", record.getDate());
			dataMap.put("meeting.bidBase", record.getBidBase());
			dataMap.put("meeting.winAmount", record.getWinAmount());
			
			dataMap.put("meeting.bidType_place", record.getBidType()+record.getPlace());
			dataMap.put("meeting.judge_jjwJudge", record.getJudge()+record.getJjwJudge());
		}
		
		dataMap.put("bid.planAmount",bid.getPlanAmount());

		String itemType=item.getItemType();
		if("物资管理".equals(itemType)){
			dataMap.put("type", "物资（设备）采购");
		}else{
			dataMap.put("type", "建设工程项目");
		}
		
		String itemName = item.getItemName();
		dataMap.put("itemName", itemName);
		
		session.put("dataMap", dataMap);
		session.put("template", "file"+File.separator + "supervision_template.mht");
		return "export";
	}
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public ItemBid getBid() {
		return bid;
	}

	public void setBid(ItemBid bid) {
		this.bid = bid;
	}

	public ItemFinish getItemFinish() {
		return itemFinish;
	}

	public void setItemFinish(ItemFinish itemFinish) {
		this.itemFinish = itemFinish;
	}

	public List<RecommendBidder> getRecommendBidderList() {
		return recommendBidderList;
	}

	public void setRecommendBidderList(List<RecommendBidder> recommendBidderList) {
		this.recommendBidderList = recommendBidderList;
	}

	private String ROW="<tr style=3D'mso-yfti-irow:2;height:47.2pt'>  <td width=3D182 style=3D'width:109.3pt;border:solid black 1.0pt;border-to=p:none;  mso-border-top-alt:solid windowtext .5pt;mso-border-alt:solid black .75pt;  mso-border-top-alt:solid windowtext .5pt;padding:0cm 0cm 0cm 0cm;height:4=7.2pt'>  <p class=3DMsoNormal style=3D'mso-element:frame;mso-element-frame-hspace:=9.0pt;  mso-element-wrap:around;mso-element-anchor-vertical:paragraph;mso-element=-anchor-horizontal:  margin;mso-element-left:center;mso-element-top:7.6pt;mso-height-rule:exac=tly'><span  style=3D'font-size:9.0pt;font-family:\"Segoe UI\",\"sans-serif\";mso-font-ker=ning:  0pt;mso-ansi-language:ZH-CN'>$name$</span><span lang=3DEN-US style=3D'mso-=bidi-font-size:  10.5pt'><o:p></o:p></span></p>  </td>  <td width=3D110 style=3D'width:66.0pt;border-top:none;border-left:none;  border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;mso-border=-top-alt:  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-borde=r-top-alt:  windowtext .5pt;mso-border-left-alt:windowtext .5pt;mso-border-bottom-alt:  black .75pt;mso-border-right-alt:black .75pt;mso-border-style-alt:solid;  padding:0cm 0cm 0cm 0cm;height:47.2pt'>  <p class=3DMsoNormal align=3Dcenter style=3D'text-align:center;mso-elemen=t:frame;  mso-element-frame-hspace:9.0pt;mso-element-wrap:around;mso-element-anchor=-vertical:  paragraph;mso-element-anchor-horizontal:margin;mso-element-left:center;  mso-element-top:7.6pt;mso-height-rule:exactly'><span style=3D'font-size:9=.0pt;  font-family:\"Segoe UI\",\"sans-serif\";mso-font-kerning:0pt;mso-ansi-languag=e:  ZH-CN'>$qiyezizhi$</span><span lang=3DEN-US style=3D'mso-bidi-font-size:10.=5pt'><o:p></o:p></span></p>  </td>  <td width=3D140 colspan=3D2 style=3D'width:83.9pt;border-top:none;border-=left:none;  border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;mso-border=-top-alt:  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-borde=r-top-alt:  windowtext .5pt;mso-border-left-alt:windowtext .5pt;mso-border-bottom-alt:  black .75pt;mso-border-right-alt:black .75pt;mso-border-style-alt:solid;  padding:0cm 0cm 0cm 0cm;height:47.2pt'>  <p class=3DMsoNormal align=3Dcenter style=3D'text-align:center;mso-elemen=t:frame;  mso-element-frame-hspace:9.0pt;mso-element-wrap:around;mso-element-anchor=-vertical:  paragraph;mso-element-anchor-horizontal:margin;mso-element-left:center;  mso-element-top:7.6pt;mso-height-rule:exactly'><span lang=3DEN-US  style=3D'mso-bidi-font-size:10.5pt'>$yingyezhizhao$<o:p></o:p></span></p>  </td>  <td width=3D138 style=3D'width:82.8pt;border-top:none;border-left:none;  border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;mso-border=-top-alt:  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-borde=r-top-alt:  windowtext .5pt;mso-border-left-alt:windowtext .5pt;mso-border-bottom-alt:  black .75pt;mso-border-right-alt:black .75pt;mso-border-style-alt:solid;  padding:0cm 0cm 0cm 0cm;height:47.2pt'>  <p class=3DMsoNormal align=3Dcenter style=3D'text-align:center;mso-elemen=t:frame;  mso-element-frame-hspace:9.0pt;mso-element-wrap:around;mso-element-anchor=-vertical:  paragraph;mso-element-anchor-horizontal:margin;mso-element-left:center;  mso-element-top:7.6pt;mso-height-rule:exactly'><span lang=3DEN-US  style=3D'mso-bidi-font-size:10.5pt'>$farendaibiaoName$<o:p></o:p></span></p>  </td>  <td width=3D120 style=3D'width:72.0pt;border-top:none;border-left:none;  border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;mso-border=-top-alt:  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-borde=r-top-alt:  windowtext .5pt;mso-border-left-alt:windowtext .5pt;mso-border-bottom-alt:  black .75pt;mso-border-right-alt:black .75pt;mso-border-style-alt:solid;  padding:0cm 0cm 0cm 0cm;height:47.2pt'>  <p class=3DMsoNormal align=3Dcenter style=3D'text-align:center;mso-elemen=t:frame;  mso-element-frame-hspace:9.0pt;mso-element-wrap:around;mso-element-anchor=-vertical:  paragraph;mso-element-anchor-horizontal:margin;mso-element-left:center;  mso-element-top:7.6pt;mso-height-rule:exactly'><span lang=3DEN-US  style=3D'mso-bidi-font-size:10.5pt'>$weituorenName$<o:p></o:p></span></p>  </td>  <td width=3D161 style=3D'width:96.8pt;border-top:none;border-left:none;  border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;mso-border=-top-alt:  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-borde=r-top-alt:  windowtext .5pt;mso-border-left-alt:windowtext .5pt;mso-border-bottom-alt:  black .75pt;mso-border-right-alt:black .75pt;mso-border-style-alt:solid;  padding:0cm 0cm 0cm 0cm;height:47.2pt'>  <p class=3DMsoNormal align=3Dcenter style=3D'text-align:center;mso-elemen=t:frame;  mso-element-frame-hspace:9.0pt;mso-element-wrap:around;mso-element-anchor=-vertical:  paragraph;mso-element-anchor-horizontal:margin;mso-element-left:center;  mso-element-top:7.6pt;mso-height-rule:exactly'><span lang=3DEN-US  style=3D'mso-bidi-font-size:10.5pt'>$weituorenId$</span><span lang=3DEN-US><o:p></o:p></span></p>  </td>  <td width=3D99 style=3D'width:59.35pt;border-top:none;border-left:none;  border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;mso-border=-top-alt:  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-borde=r-top-alt:  windowtext .5pt;mso-border-left-alt:windowtext .5pt;mso-border-bottom-alt:  black .75pt;mso-border-right-alt:black .75pt;mso-border-style-alt:solid;  padding:0cm 0cm 0cm 0cm;height:47.2pt'>  <p class=3DMsoNormal align=3Dcenter style=3D'text-align:center;mso-elemen=t:frame;  mso-element-frame-hspace:9.0pt;mso-element-wrap:around;mso-element-anchor=-vertical:  paragraph;mso-element-anchor-horizontal:margin;mso-element-left:center;  mso-element-top:7.6pt;mso-height-rule:exactly'><span lang=3DEN-US>$baojia1$<o:p></o:p></span></p>  </td>  <td width=3D80 style=3D'width:48.2pt;border-top:none;border-left:none;bor=der-bottom:  solid black 1.0pt;border-right:solid black 1.0pt;mso-border-top-alt:solid= windowtext .5pt;  mso-border-left-alt:solid windowtext .5pt;mso-border-top-alt:windowtext .=5pt;  mso-border-left-alt:windowtext .5pt;mso-border-bottom-alt:black .75pt;  mso-border-right-alt:black .75pt;mso-border-style-alt:solid;padding:0cm 0=cm 0cm 0cm;  height:47.2pt'>  <p class=3DMsoNormal align=3Dcenter style=3D'text-align:center;mso-elemen=t:frame;  mso-element-frame-hspace:9.0pt;mso-element-wrap:around;mso-element-anchor=-vertical:  paragraph;mso-element-anchor-horizontal:margin;mso-element-left:center;  mso-element-top:7.6pt;mso-height-rule:exactly'><span lang=3DEN-US>$baojia2$<o:p></o:p></span></p>  </td>  <td width=3D76 style=3D'width:45.65pt;border-top:none;border-left:none;  border-bottom:solid black 1.0pt;border-right:solid black 1.0pt;mso-border=-top-alt:  solid windowtext .5pt;mso-border-left-alt:solid windowtext .5pt;mso-borde=r-top-alt:  windowtext .5pt;mso-border-left-alt:windowtext .5pt;mso-border-bottom-alt:  black .75pt;mso-border-right-alt:black .75pt;mso-border-style-alt:solid;  padding:0cm 0cm 0cm 0cm;height:47.2pt'>  <p class=3DMsoNormal align=3Dcenter style=3D'text-align:center;mso-elemen=t:frame;  mso-element-frame-hspace:9.0pt;mso-element-wrap:around;mso-element-anchor=-vertical:  paragraph;mso-element-anchor-horizontal:margin;mso-element-left:center;  mso-element-top:7.6pt;mso-height-rule:exactly'><span lang=3DEN-US>$baojia3$<o:p></o:p></span></p>  </td>  <td width=3D85 style=3D'width:51.1pt;border-top:none;border-left:none;bor=der-bottom:  solid black 1.0pt;border-right:solid black 1.0pt;mso-border-top-alt:solid= windowtext .5pt;  mso-border-left-alt:solid windowtext .5pt;mso-border-top-alt:windowtext .=5pt;  mso-border-left-alt:windowtext .5pt;mso-border-bottom-alt:black .75pt;  mso-border-right-alt:black .75pt;mso-border-style-alt:solid;padding:0cm 0=cm 0cm 0cm;  height:47.2pt'>  <p class=3DMsoNormal align=3Dcenter style=3D'text-align:center;mso-elemen=t:frame;  mso-element-frame-hspace:9.0pt;mso-element-wrap:around;mso-element-anchor=-vertical:  paragraph;mso-element-anchor-horizontal:margin;mso-element-left:center;  mso-element-top:7.6pt;mso-height-rule:exactly'><span lang=3DEN-US>$score$<o=:p></o:p></span></p>  </td> </tr> ";
	
	public static void main(String []args){
		System.out.println("$score$, Good".replaceAll("\\$score\\$", null));
	}
}