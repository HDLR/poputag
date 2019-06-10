package com.eastern.jinxin.business.userGroup.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;

import com.eastern.jinxin.business.userGroup.service.MicroscopicPictureService;
import com.eastern.jinxin.sys.common.common.utils.PageInfo;
import com.eastern.jinxin.hbase.entity.PersonEnum;
import com.eastern.jinxin.hbase.entity.TUserInfo;
import com.eastern.jinxin.hbase.service.impl.HbaseServiceImpl;
import net.sf.json.JSONArray;

@Service("microscopicPictureService")
public class MicroscopicPictureServiceImpl implements MicroscopicPictureService{

	@Autowired
	private HbaseServiceImpl hbaseService;
	
	public List<TUserInfo> listByPage(PageInfo pageInfo) {
		int nowPage = pageInfo.getNowpage();
		int limit = pageInfo.getSize();
//		List<TUserInfo> userInfos = hbaseService.createPersonListFromHbase(pageInfo, (String)pageInfo.getCondition().get("campIdP"), nowPage, limit);
		String usersJson = "[{\"birthYears\":\"70年代\",\"userName\":\"赵**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"70年代\",\"userName\":\"郭**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"40年代\",\"userName\":\"潘**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"50年代\",\"userName\":\"孙**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"60年代\",\"userName\":\"范**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"60年代\",\"userName\":\"陈**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"60年代\",\"userName\":\"林**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"60年代\",\"userName\":\"刘**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"60年代\",\"userName\":\"谭**\",\"userSex\":\"女\"},\r\n" + 
				"{\"birthYears\":\"60年代\",\"userName\":\"曾**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"60年代\",\"userName\":\"苏**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"70年代\",\"userName\":\"吴**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"70年代\",\"userName\":\"朱**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"70年代\",\"userName\":\"霍**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"80年代\",\"userName\":\"潘**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"80年代\",\"userName\":\"吴**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"80年代\",\"userName\":\"范**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"80年代\",\"userName\":\"蒲**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"30年代\",\"userName\":\"张**\",\"userSex\":\"男\"},\r\n" + 
				"{\"birthYears\":\"30年代\",\"userName\":\"文**\",\"userSex\":\"男\"}]";
		
		JSONArray array = JSONArray.fromObject(usersJson);
		List<TUserInfo> userInfos = JSONArray.toList(array, TUserInfo.class);
//		if(null != userInfos) {
//			for(TUserInfo u : userInfos) {
//				//性别
//				String userSex = u.getUserSex();
//				/*if (PersonEnum.PersonSex.SEX_MR.toString().equals(userSex))
//			         userSex = PersonEnum.PersonSex.SEX_MR.showVal;
//			       else if (PersonEnum.PersonSex.SEX_MS.toString().equals(userSex))
//			         userSex = PersonEnum.PersonSex.SEX_MS.showVal;
//			       else
//			         userSex = PersonEnum.PersonSex.NULL.toString();*/				
//				u.setUserSex(userSex);
//				u.setBirthYears(u.getBirthYears());
//				//出生年代
//				/*if("99".equals(u.getBirthYears())||StringUtil.isBlank(u.getBirthYears())) {
//					u.setBirthYears("未知年代");
//				}else {
//					u.setBirthYears(Integer.parseInt(u.getBirthYears()) + "0年代");
//				}*/
//
//			}
//		}
		pageInfo.setRows(userInfos);
		return userInfos;
		
	}
	
	public Map<String, Object> queryUserDetail(String userGuid) {
		
		String reStr = "{\"信用评价\":[{\"tag_ctgy_nm\":\"不良信用类型\",\"tag_nm\":\"其它\"},{\"tag_ctgy_nm\":\"黑名单\"},{\"tag_ctgy_nm\":\"犯罪史标识\"},{\"tag_ctgy_nm\":\"行政执法\",\"tag_nm\":\"其它\"},{\"tag_ctgy_nm\":\"刑满释放标识\"},{\"tag_ctgy_nm\":\"住房公积金贷款合同状态\",\"tag_nm\":\"其它\"},{\"tag_ctgy_nm\":\"住房公积金是否有贷款\"},{\"tag_ctgy_nm\":\"住房公积金贷款担保类型\",\"tag_nm\":\"其它\"},{\"tag_ctgy_nm\":\"住房公积金贷款还款方式\",\"tag_nm\":\"其它\"},{\"tag_ctgy_nm\":\"司法\",\"tag_nm\":\"其它\"},{\"tag_ctgy_nm\":\"个人荣誉\"},{\"tag_ctgy_nm\":\"上访标识\"},{\"tag_ctgy_nm\":\"“三涉”情况\"},{\"tag_ctgy_nm\":\"“四史”情况\"},{\"tag_ctgy_nm\":\"重点青少年标识\"}],\"专业领域\":[{\"tag_ctgy_nm\":\"所属行业门类\",\"tag_nm\":\"其它\"},{\"tag_ctgy_nm\":\"产业属性\",\"tag_nm\":\"其他\"},{\"tag_ctgy_nm\":\"专业\"},{\"tag_ctgy_nm\":\"发表论文个数\"},{\"tag_ctgy_nm\":\"资质证书\",\"tag_nm\":\"其它资质\"},{\"tag_ctgy_nm\":\"人才类型\"},{\"tag_ctgy_nm\":\"职业类型\"}],\"基本属性\":[{\"tag_ctgy_nm\":\"年龄\",\"tag_nm\":\"47\"},{\"tag_ctgy_nm\":\"出生日期\",\"tag_nm\":\"1971-05-12\"},{\"tag_ctgy_nm\":\"出生年代\",\"tag_nm\":\"70年代\"},{\"tag_ctgy_nm\":\"证件号码\",\"tag_nm\":\"11000019710512****\"},{\"tag_ctgy_nm\":\"证件类型\",\"tag_nm\":\"身份证\"},{\"tag_ctgy_nm\":\"联系电话\",\"tag_nm\":\"\"},{\"tag_ctgy_nm\":\"是否有联系电话\",\"tag_nm\":\"否\"},{\"tag_ctgy_nm\":\"学历\",\"tag_nm\":\"初中\"},{\"tag_ctgy_nm\":\"性别\",\"tag_nm\":\"男\"},{\"tag_ctgy_nm\":\"户口性质\"},{\"tag_ctgy_nm\":\"是否少数民族\",\"tag_nm\":\"否\"},{\"tag_ctgy_nm\":\"民族\",\"tag_nm\":\"汉\"},{\"tag_ctgy_nm\":\"籍贯\",\"tag_nm\":\"\"},{\"tag_ctgy_nm\":\"居住地\"},{\"tag_ctgy_nm\":\"政治面貌\"},{\"tag_ctgy_nm\":\"姓名\",\"tag_nm\":\"***\"},{\"tag_ctgy_nm\":\"宗教信仰\"},{\"tag_ctgy_nm\":\"现役军人标识\"}],\"流动状况\":[{\"tag_ctgy_nm\":\"来琼年平均停留时间\"},{\"tag_ctgy_nm\":\"是否流动人口\",\"tag_nm\":\"否\"},{\"tag_ctgy_nm\":\"跨省流入标志\",\"tag_nm\":\"否\"},{\"tag_ctgy_nm\":\"跨省流出标志\",\"tag_nm\":\"否\"},{\"tag_ctgy_nm\":\"来琼常驻市县\"},{\"tag_ctgy_nm\":\"省内流动标志\",\"tag_nm\":\"否\"},{\"tag_ctgy_nm\":\"是否候鸟\",\"tag_nm\":\"否\"},{\"tag_ctgy_nm\":\"通常来琼月份\"},{\"tag_ctgy_nm\":\"通常离琼月份\"},{\"tag_ctgy_nm\":\"通常来琼居住房屋类型\",\"tag_nm\":\"其它\"},{\"tag_ctgy_nm\":\"手机归属省份\"},{\"tag_ctgy_nm\":\"来源省份\"}],\"工作特征\":[{\"tag_ctgy_nm\":\"行业归属\"},{\"tag_ctgy_nm\":\"职称\"},{\"tag_ctgy_nm\":\"参加工作年限\",\"tag_nm\":\"其他\"},{\"tag_ctgy_nm\":\"职务\"},{\"tag_ctgy_nm\":\"职业归属大类\",\"tag_nm\":\"不便分类的其它从业人员\"},{\"tag_ctgy_nm\":\"科研领域\",\"tag_nm\":\"其它技术领域\"},{\"tag_ctgy_nm\":\"单位类型\",\"tag_nm\":\"其他\"},{\"tag_ctgy_nm\":\"工作状态\"}],\"社会保障\":[{\"tag_ctgy_nm\":\"农村社保标识\",\"tag_nm\":\"否\"},{\"tag_ctgy_nm\":\"当前参保状态\"},{\"tag_ctgy_nm\":\"低保家庭人口数\"},{\"tag_ctgy_nm\":\"住房公积金个人缴存比例\",\"tag_nm\":\"其它\"},{\"tag_ctgy_nm\":\"住房公积金个人账户状态\",\"tag_nm\":\"其它\"},{\"tag_ctgy_nm\":\"住房公积金个人月缴总额\"},{\"tag_ctgy_nm\":\"参保费用\"},{\"tag_ctgy_nm\":\"是否参保\"},{\"tag_ctgy_nm\":\"参保缴费状态\"},{\"tag_ctgy_nm\":\"参保险种\",\"tag_nm\":\"未知\"},{\"tag_ctgy_nm\":\"参保年限\",\"tag_nm\":\"未知\"},{\"tag_ctgy_nm\":\"城镇社保标识\",\"tag_nm\":\"否\"},{\"tag_ctgy_nm\":\"保障金额\"},{\"tag_ctgy_nm\":\"是否低保户\",\"tag_nm\":\"否\"},{\"tag_ctgy_nm\":\"低保保障人口数\"},{\"tag_ctgy_nm\":\"低保类型\",\"tag_nm\":\"未知\"},{\"tag_ctgy_nm\":\"参与新农合年限\"},{\"tag_ctgy_nm\":\"是否有退休金\"},{\"tag_ctgy_nm\":\"致贫原因\"},{\"tag_ctgy_nm\":\"优抚月生活费标准\",\"tag_nm\":\"0\"},{\"tag_ctgy_nm\":\"是否优抚对象\",\"tag_nm\":\"否\"},{\"tag_ctgy_nm\":\"优抚类型\",\"tag_nm\":\"其它\"},{\"tag_ctgy_nm\":\"是否救助对象\"},{\"tag_ctgy_nm\":\"新农合缴费金额\"},{\"tag_ctgy_nm\":\"是否参与新农合\",\"tag_nm\":\"否\"}],\"健康状况\":[{\"tag_ctgy_nm\":\"是否患有艾滋病\"},{\"tag_ctgy_nm\":\"献血标识\"},{\"tag_ctgy_nm\":\"病情\"},{\"tag_ctgy_nm\":\"残疾等级\",\"tag_nm\":\"其它\"},{\"tag_ctgy_nm\":\"残疾类别\",\"tag_nm\":\"未知\"},{\"tag_ctgy_nm\":\"病种\"},{\"tag_ctgy_nm\":\"是否有吸毒史\"},{\"tag_ctgy_nm\":\"健康状况\"},{\"tag_ctgy_nm\":\"住院次数\",\"tag_nm\":\"0次\"},{\"tag_ctgy_nm\":\"住院费用\",\"tag_nm\":\"0\"},{\"tag_ctgy_nm\":\"是否有精神病史\"},{\"tag_ctgy_nm\":\"新农合是否有大病补偿\"}],\"家庭特征\":[{\"tag_ctgy_nm\":\"收养儿童数\"},{\"tag_ctgy_nm\":\"是否收养家庭\",\"tag_nm\":\"否\"},{\"tag_ctgy_nm\":\"兄弟姐妹数量\",\"tag_nm\":\"0个\"},{\"tag_ctgy_nm\":\"子女人数\",\"tag_nm\":\"0个\"},{\"tag_ctgy_nm\":\"是否就业困难人员\"},{\"tag_ctgy_nm\":\"婚姻次数\",\"tag_nm\":\"0次\"},{\"tag_ctgy_nm\":\"婚姻状态\"},{\"tag_ctgy_nm\":\"独生子女标识\",\"tag_nm\":\"否\"},{\"tag_ctgy_nm\":\"是否贫困户\"}],\"资产状况\":[{\"tag_ctgy_nm\":\"资产设备\"},{\"tag_ctgy_nm\":\"金融资产\"},{\"tag_ctgy_nm\":\"无形资产\"},{\"tag_ctgy_nm\":\"不动产\"},{\"tag_ctgy_nm\":\"交通工具\"}]}";
		
		Map<String, Object> m = (Map<String, Object>)JSON.parse(reStr);
		
		return m;
		
//		return hbaseService.getUserDetail(userGuid);
	}
	
	public List<List<Map<String, Object>>> downLoadExcelMsg(String campId){
		Map<String, Object> ms = hbaseService.queryDetailAll(campId);
		List<List<Map<String, Object>>> list = new ArrayList<List<Map<String, Object>>>(); 
		int i = 0;
		if(null != ms && null != ms.get("users")) {
			List<Integer> userList = (List<Integer>)ms.get("users");
			for(Integer u : userList) {
				List<Map<String, Object>> tags = hbaseService.getUserAllTag(u.toString());
				list.add(tags);
			}
		}
		return list;
	}
}
