package com.eastern.jinxin.solr.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.beans.Field;

public class PopuInfo {

	/*
	 * 基础信息
	 */
	
	//统一人口编码
	@Field("popu_13_unif_popu_id")
	private String id;
	
	//姓名	
	@Field("popu_13_popu_nm")
	private String popu_nm;
	
	//证件类型	
	@Field("popu_13_cert_type_desc")
	private String cert_type_desc;
	
	//证件号码
	@Field("popu_13_cert_num")
	private String zjhm;
	
	//性别	
	@Field("popu_13_gender")
	private String gender;
	
	//民族	
	@Field("popu_13_nation")
	private String nation;
	
	//相片	
	
	//籍贯	
	@Field("popu_13_native_plc_desc")
	private String native_plc_desc;
	
	//出生日期
	@Field("popu_13_birth_dt")
	private String birth_dt;
	
	//出生地	
	@Field("popu_13_birth_add")
	private String birth_add;
	
	//户口类别	
	@Field("popu_13_hshld_prop")
	private String hshld_prop;
	
	//政治面貌	
	@Field("popu_13_poli_stat")
	private String poli_stat;
	
	//户籍住址	
	@Field("popu_13_regi_add_cd_desc")
	private String regi_add_cd_desc;
	
	//服务处所	
	@Field("popu_13_ser_space")
	private String ser_space;
	
	//死亡时间	
	@Field("popu_13_dead_dt")
	private String dead_dt;
	
	//文化程度	
	
	//实际居住地址	
	@Field("popu_13_natv_plc_urbn_nm")
	private String natv_plc_urbn_nm;
	
	//年龄	
	@Field("popu_13_age")
	private String age;
	
	//性别	
	//民族	
	
	//是否少数民族	
	@Field("popu_13_minority_flg")
	private String minority_flg;
	
	//学历(文化程度)	
	@Field("popu_13_Edu_Degr")
	private String Edu_Degr;
	
	//出生年代	
	@Field("popu_13_birth_years_cd")
	private String birth_years_cd;
	
	//是否有联系电话	
	@Field("popu_13_comm_tel_flg")
	private String comm_tel_flg;
	
	//宗教信仰	
	@Field("popu_13_religion")
	private String religion;
	
	/*
	 * 家庭特征
	 */
	
	//婚姻男方户籍地	
	//婚姻女方户籍地	
	
	//新领证件登记字号/撤销决定字号	
	@Field("popu_13_new_zj_cd")
	private String new_zj_cd;
	
	//婚姻登记日期	
	@Field("popu_13_dengji_dt")
	private String dengji_dt;
	
	//婚姻登记承办机关代码（办理机关编号）	   
	@Field("popu_13_undt_org")
	private String undt_org;
	
	//婚姻登记承办机关名称（办理机关名称）	
	@Field("popu_13_undt_org_nm")
	private String undt_org_nm;
	
	//计划生育类型	
	@Field("popu_13_family_plan_type")
	private String family_plan_type;
	
	//收养登记证号	
	@Field("popu_13_adopt_dj_no")
	private String adopt_dj_no;
	
	//婚姻状态	
	@Field("popu_13_marrg_stat")
	private String marrg_stat;
	
	//兄弟姐妹数量	
	@Field("popu_13_brothers_sisters")
	private String brothers_sisters;
	
	
	/*
	 * 流动状况
	 */
	
	//来源省份
	@Field("popu_13_src_prov_desc")
	private String src_prov_desc;
	
	//来琼常驻市县
	@Field("popu_13_hn_perm_resid_coty")
	private String hn_perm_resid_coty;
	
	//通常来琼居住房屋类型
	@Field("popu_13_norm_live_house_desc")
	private String norm_live_house_desc;
	
	//跨省流入标志
	@Field("popu_13_flw_in_flg")
	private String flw_in_flg;
	
	//跨省流出标志
	@Field("popu_13_flw_out_flg")
	private String flw_out_flg;
	
	//省内流动标志
	@Field("popu_13_inner_flw_flg")
	private String inner_flw_flg;
	
	//是否流动人口
	@Field("popu_13_flw_flg")
	private String flw_flg;
	
	//是否候鸟
	@Field("popu_13_migt_flg")
	private String migt_flg;

	
	/*
	 * 工作特征
	 */
	//参加工作时间	
	@Field("popu_13_join_work_dt")
	private String join_work_dt;
	
	//入编时间	
	@Field("popu_13_compile_time")
	private String compile_time;
	
	//入编文号	  
	@Field("popu_13_compile_no")
	private String compile_no;
	
	//在编状态 	
	@Field("popu_13_compile_status")
	private String compile_status;
	
	//聘任专业技术职务	
	@Field("popu_13_emp_pro_te")
	private String emp_pro_te;
	
	//岗位类别	
	@Field("popu_13_job_category")
	private String job_category;
	
	//公务员登记是否登记	
	@Field("popu_13_civil_regist")
	private String civil_regist;
	
	//控编单编号	       
	@Field("popu_13_cron_no")
	private String cron_no;
	
	//出编文号	
	@Field("popu_13_out_compile_no")
	private String out_compile_no;
	
	//状态变更	 
	@Field("popu_13_status_change")
	private String status_change;
	
	//职务	
	@Field("popu_13_post")
	private String post;
	
	//工作状态
	@Field("popu_13_work_stat")
	private String work_stat;
	
	//职业归属大类 	
	@Field("popu_13_profession_belong")
	private String profession_belong;
	
	//行业归属 	
	@Field("popu_13_inds_fst_class")
	private String inds_fst_class;
	
	//科研领域 	
	@Field("popu_13_science_area")
	private String science_area;
	
	//单位类型 	    
	@Field("popu_13_unit_type_nm")
	private String unit_type_nm;
	
	//参加工作年限 	  
	@Field("popu_13_join_work_age")
	private String join_work_age;
	
	//手机归属省份 	  
	@Field("popu_13_phone_belong_prov")
	private String phone_belong_prov;
	
	
	

	
	/*
	 * 资产状况
	 */
	//不动产	
	@Field("popu_13_estate")
	private String estate;
	
	//不动产产权证号	
	@Field("popu_13_estate_no")
	private String estate_no;
	
	//交通工具
	@Field("popu_13_vehicle")
	private String vehicle;
	
	//个人账户状态	
	@Field("popu_13_fund_personal_stat")
	private String fund_personal_stat;
	
	//营运状态	
	@Field("popu_13_car_oper_state")
	private String car_oper_state;
	
	//道路运输证号	
	@Field("popu_13_road_transport")
	private String road_transport;
	
	//机动车登记证号	     
	@Field("popu_13_car_dj_no")
	private String car_dj_no;
	
	//机动车行驶证号	
	@Field("popu_13_car_driving_no")
	private String car_driving_no;
	
	//驾驶证号码	   
	@Field("popu_13_driving_num")
	private String driving_num;
	
	//资产设备 	
	@Field("popu_13_asset_equipment")
	private String asset_equipment;
	
	//金融资产 	 
	@Field("popu_13_asset_financial")
	private String asset_financial;
	
	//无形资产	
	@Field("popu_13_asset_intangible")
	private String asset_intangible;

	
	/*
	 * 健康状况
	 */
	
	//残疾证号	
	@Field("popu_13_disability_no")
	private String disability_no;
	
	//死亡原因	
	@Field("popu_13_dead_reason")
	private String dead_reason;
	
	//死亡地点	
	@Field("popu_13_dead_location")
	private String dead_location;
	
	//病种 	
	@Field("popu_13_disease_type_desc")
	private String disease_type_desc;
	
	//住院次数 	
	@Field("popu_13_hospitalization_con")
	private String hospitalization_con;
	
	//健康状况 	
	@Field("popu_13_hlth_stat")
	private String hlth_stat;
	
	//残疾类别 	
	@Field("popu_13_disability_type_nm")
	private String disability_type_nm;
	
	//残疾等级	
	@Field("popu_13_disability_level")
	private String disability_level;
	
	//病情	
	@Field("popu_13_cond")
	private String cond;
	
	/*
	 * 专业领域
	 */
	
	//毕业学校（最高学历）	
	@Field("popu_13_grad_school")
	private String grad_school;
	
	//毕业时间	  
	@Field("popu_13_grad_time")
	private String grad_time;
	
	//选考语种	 
	@Field("popu_13_choose_lang")
	private String choose_lang;
	
	//学生学籍号	
	@Field("popu_13_stud_stat_id")
	private String stud_stat_id;
	
	//人才类型 	
	@Field("popu_13_talt_typ")
	private String talt_typ;
	
	//专业 	
	@Field("popu_13_major")
	private String major;
	
	//职业类型 	
	@Field("popu_13_voct_typ")
	private String voct_typ;
	
	//所属行业门类 	
	@Field("popu_13_domn")
	private String domn;
	
	//产业属性 	
	@Field("popu_13_industrial_attribute")
	private String industrial_attribute;
	
	//资质证书 	
	@Field("popu_13_qualification_certificate")
	private String qualification_certificate;

	
	/*
	 * 社会保障
	 */
	
	//社会保障号码	
	@Field("popu_13_social_secur_num")
	private String social_secur_num;
	
	//参保年限	
	@Field("popu_13_insured_years_desc")
	private String insured_years_desc;
	
	//首次参保日期	
	@Field("popu_13_first_ginseng_dt")
	private String first_ginseng_dt;
	
	//单位名称	
	@Field("popu_13_insure_corp_nm")
	private String insure_corp_nm;
	
	//失业原因	
	@Field("popu_13_unemp_reason")
	private String unemp_reason;
	
	//离退休类别	
	@Field("popu_13_retired_type")
	private String retired_type;
	
	//离退休日期	  
	@Field("popu_13_retired_dt")
	private String retired_dt;
	
	//低保家庭名称	
	@Field("popu_13_low_family")
	private String low_family;
	
	//低保家庭编码	
	@Field("popu_13_low_family_id")
	private String low_family_id;
	
	//低保家庭领取证号	
	@Field("popu_13_low_lq_cd")
	private String low_lq_cd;
	
	//低保家庭申请原因描述	 
	@Field("popu_13_ow_reason")
	private String ow_reason;
	
	//低保家庭开户银行名称	
	@Field("popu_13_ow_khyh_nm")
	private String ow_khyh_nm;
	
	//低保家庭银行帐号	
	@Field("popu_13_low_khyh")
	private String low_khyh;
	
	//低保人员劳动能力代码	
	@Field("popu_13_low_ability_cd")
	private String low_ability_cd;
	
	//低保人员类别代码	
	@Field("popu_13_low_per_cd")
	private String low_per_cd;
	
	//低保人员工作性质代码	
	@Field("popu_13_low_work_cd")
	private String low_work_cd;
	
	//低保人员月收入金额	
	@Field("popu_13_low_month")
	private String low_month;
	
	//低保人员工作单位名称	
	@Field("popu_13_low_work_units_nm")
	private String low_work_units_nm;
	
	//低保人员工作单位性质代码	
	@Field("popu_13_low_work_units_type")
	private String low_work_units_type;
	
	//低保金金额	       
	@Field("popu_13_low_amount")
	private String low_amount;
	
	//低保金发放日期	     
	@Field("popu_13_low_issue_dt")
	private String low_issue_dt;
	
	//低保资金记录类型	  
	@Field("popu_13_low_record_type")
	private String low_record_type;
	
	//低保资金记录日期	    
	@Field("popu_13_low_record_dt")
	private String low_record_dt;
	
	//低保资金记录金额	
	@Field("popu_13_low_record_amount")
	private String low_record_amount;
	
	//低保证号	
	@Field("popu_13_low_no")
	private String low_no;
	
	//发证机关名称	
	@Field("popu_13_low_fzjgnm")
	private String low_fzjgnm;
	
	//发证机关行政区划	
	@Field("popu_13_low_fzjgxz")
	private String low_fzjgxz;
	
	//救助类型	
	@Field("popu_13_rescue_type")
	private String rescue_type;
	
	//救助方式代码	
	@Field("popu_13_rescue_mode")
	private String rescue_mode;
	
	//救助金额	
	@Field("popu_13_rescue_amount")
	private String rescue_amount;
	
	//医疗救助方式	
	@Field("popu_13_rescue_medical_mode")
	private String rescue_medical_mode;
	
	//救助人员类别	
	@Field("popu_13_rescue_person_type")
	private String rescue_person_type;
	
	//救助时间	    
	@Field("popu_13_rescue_dt")
	private String rescue_dt;
	
	//救助地点	 
	@Field("popu_13_rescue_place")
	private String rescue_place;
	
	//参保险种 	
	@Field("popu_13_insured_type")
	private String insured_type;
	
	//参保缴费状态	
	@Field("popu_13_insured_payment_state")
	private String insured_payment_state;
	
	//参与新农合 	
	@Field("popu_13_xnh_flg")
	private String xnh_flg;
	
	//是否低保户 	
	@Field("popu_13_low_secu_family_flg")
	private String low_secu_family_flg;
	
	//致贫原因 	
	@Field("popu_13_povt_resn")
	private String povt_resn;
	
	//当前参保状态
	@Field("popu_13_cur_insure_stat")
	private String cur_insure_stat;
	
	//城镇社保标识 	
	@Field("popu_13_insure_ind")
	private String insure_ind;
	
	//农村社保标识 	
	@Field("popu_13_coop_ind")
	private String coop_ind;
	
	//优抚类型 	
	@Field("popu_13_preferential_type_desc")
	private String preferential_type_desc;
	
	//优抚月生活费标准 	
	@Field("popu_13_preferential_month_standar")
	private String preferential_month_standar;
	
	//住房公积金个人账户状态 	
//	@Field("popu_13_fund_personal_stat")
//	private String fund_personal_stat;
	
	//住房公积金个人缴存比例 	
	@Field("popu_13_fund_personal_proportion")
	private String fund_personal_proportion;
	
	//住房公积金个人月缴总额	
	@Field("popu_13_fund_personal_total")
	private String fund_personal_total;
	
	//低保类型
	@Field("popu_13_low_secu_typ")
	private String low_secu_typ;
	
	
	/*
	 * 信用评价
	 */
	
	//行政执法	
	@Field("popu_13_enforcement")
	private String enforcement;
	
	//司法	
	@Field("popu_13_judicial")
	private String judicial;
	
	//个人荣誉	
	@Field("popu_13_personal_honor")
	private String personal_honor;
	
	//“四史”情况	
	@Field("popu_13_shis_situation")
	private String shis_situation;
	
	//“三涉”情况	
	@Field("popu_13_sans_situation")
	private String sans_situation;
	
	//犯罪史标识	
	@Field("popu_13_crime_his_flg")
	private String crime_his_flg;
	
	//刑满释放标识	
	@Field("popu_13_full_release_flg")
	private String full_release_flg;
	
	//重点青少年标识	
	@Field("popu_13_teenager_flg")
	private String teenager_flg;
	
	//上访标识	
	@Field("popu_13_petition_flg")
	private String petition_flg;
	
	//住房公积金是否有贷款	
	@Field("popu_13_fund_loan_flg")
	private String fund_loan_flg;
	
	//住房公积金贷款合同状态	 
	@Field("popu_13_fund_loan_contract")
	private String fund_loan_contract;
	
	//住房公积金贷款担保类型	
	@Field("popu_13_fund_loan_guarantee")
	private String fund_loan_guarantee;
	
	//住房公积金贷款还款方式	
	@Field("popu_13_fund_loan_repayment")
	private String fund_loan_repayment;
	
	//不良信用类型	
	@Field("popu_13_bad_credit_type")
	private String bad_credit_type;

	
	/*
	 * 其它信息
	 */
	
	//护照号	
	@Field("popu_13_passport_no")
	private String passport_no;
	
	//护照有效期	
	@Field("popu_13_passport_validity")
	private String passport_validity;
	
	//护照签发机关	
	@Field("popu_13_passport_organ")
	private String passport_organ;
	
	//户号	
	@Field("popu_13_regi_num")
	private String regi_num;
	
	//户籍信息状态	
	@Field("popu_13_regi_infor_status")
	private String regi_infor_status;
	
	//出生证编号	
	@Field("popu_13_birth_cert_id")
	private String birth_cert_id;
	
	//头像
	@Field("popu_13_photo")
	private String head;
	
	//纬度
	@Field("popu_13_location_lat")
	private String location_lat; 
	//经度
	@Field("popu_13_location_lon")
	private String location_lon; 
	//经纬度
	@Field("popu_13_lat_lon")
	private String lat_lon; 
	
	
	private String defaultHead;
	
	private Map<String, Map<String, String>> classLabelMap = null;
	
	public void createMap(){
		classLabelMap = new LinkedMap();
		
		if("女".equals(gender)) {
			this.defaultHead = "w";
		}else {
			this.defaultHead = "m";
		}
		
		/*
		 * 基础信息
		 */
		Map<String, String> map01 = new LinkedMap();
		map01.put("姓名", popu_nm);
		map01.put("证件类型", cert_type_desc);
		map01.put("证件号码", zjhm);
		map01.put("性别", gender);
		map01.put("民族", nation);
		map01.put("籍贯", native_plc_desc);
		map01.put("出生日期", birth_dt);
		map01.put("出生地", birth_add);
		map01.put("户口类别", hshld_prop);
		map01.put("政治面貌", poli_stat);
		map01.put("户籍住址", regi_add_cd_desc);
		map01.put("服务处所", ser_space);
		map01.put("死亡时间", dead_dt);
		map01.put("婚姻状况", marrg_stat);
		map01.put("实际居住地址", natv_plc_urbn_nm);
		map01.put("年龄", age);
		map01.put("是否少数民族", minority_flg);
		map01.put("学历(文化程度)", Edu_Degr);
		map01.put("出生年代", birth_years_cd);
		map01.put("户口性质", hshld_prop);
		map01.put("是否有联系电话", comm_tel_flg);
		map01.put("宗教信仰", religion);
		map01.put("统一人口编码", id);
		classLabelMap.put("01", map01);

		
		/*
		 * 家庭特征
		 */
		Map<String, String> map02 = new LinkedMap();
		map02.put("新领证件登记字号/撤销决定字号", new_zj_cd);   
		map02.put("婚姻登记日期", dengji_dt);   
		map02.put("婚姻登记承办机关代码(办理机关编号)", undt_org);   
		map02.put("婚姻登记承办机关名称(办理机关名称)", undt_org_nm);   
		map02.put("计划生育类型", family_plan_type);   
		map02.put("收养登记证号", adopt_dj_no);   
		map02.put("婚姻状态", marrg_stat);   
		map02.put("兄弟姐妹数量", brothers_sisters);   
		classLabelMap.put("02", map02);
			
		
		/*
		 * 工作特征
		 */
		Map<String, String> map03 = new LinkedMap();
		map03.put("参加工作时间", join_work_dt);	
//		map03.put("专业技术职务", "");	
		map03.put("入编时间", compile_time);	
		map03.put("入编文号", compile_no);	
		map03.put("在编状态", compile_status);	
		map03.put("聘任专业技术职务", emp_pro_te);	
		map03.put("岗位类别", job_category);	
		map03.put("公务员登记是否登记", civil_regist);	
		map03.put("控编单编号", cron_no);	
		map03.put("出编文号", out_compile_no);	
		map03.put("状态变更", status_change);	
		map03.put("职务", post);	
		map03.put("工作状态", work_stat);	
		map03.put("职业归属大类", profession_belong);	
		map03.put("行业归属", inds_fst_class);	
		map03.put("科研领域", science_area);	
		map03.put("单位类型", unit_type_nm);	
		map03.put("参加工作年限", join_work_age);	
		map03.put("手机归属省份", phone_belong_prov);
		classLabelMap.put("03", map03);

		
		/*
		 * 流动状况
		 */
		Map<String, String> map04 = new LinkedMap();
		map04.put("来源省份", src_prov_desc);	
		map04.put("来琼常驻市县", hn_perm_resid_coty);	
		map04.put("通常来琼居住房屋类型", norm_live_house_desc);	
		map04.put("跨省流入标志", flw_in_flg);	
		map04.put("跨省流出标志", flw_out_flg);	
		map04.put("省内流动标志", inner_flw_flg);	
		map04.put("是否流动人口", flw_flg);	
		map04.put("是否候鸟", migt_flg);	
		classLabelMap.put("04", map04);
		
		
		/*
		 * 资产状况
		 */
		Map<String, String> map05 = new LinkedMap();
		map05.put("不动产产权证号", estate_no);
		map05.put("不动产", estate);
		map05.put("交通工具", vehicle);
		map05.put("个人账户状态", fund_personal_stat);
		map05.put("营运状态", car_oper_state);
		map05.put("道路运输证号", road_transport);
		map05.put("机动车登记证号", car_dj_no);
		map05.put("机动车行驶证号", car_driving_no);
		map05.put("驾驶证号码", driving_num);
		map05.put("资产设备", asset_equipment);
		map05.put("金融资产", asset_financial);
		map05.put("无形资产", asset_intangible);
		classLabelMap.put("05", map05);
			     
		
		/*
		 * 健康状况
		 */
		Map<String, String> map06 = new LinkedMap();
		map06.put("残疾证号", disability_no);
		map06.put("死亡原因", dead_reason);
		map06.put("死亡地点", dead_location);
		map06.put("病种", disease_type_desc);
		map06.put("住院次数", hospitalization_con);
		map06.put("健康状况", hlth_stat);
		map06.put("残疾类别", disability_type_nm);
		map06.put("残疾等级", disability_level);
		map06.put("病情", cond);
		classLabelMap.put("06", map06);
		
			
		/*
		 * 专业领域
		 */
		Map<String, String> map07 = new LinkedMap();
		map07.put("毕业学校（最高学历）", grad_school);
		map07.put("毕业时间", grad_time);
		map07.put("选考语种", choose_lang);
		map07.put("学生学籍号", stud_stat_id);
		map07.put("专业", major);
		map07.put("人才类型", talt_typ);
		map07.put("职业类型", voct_typ);
		map07.put("所属行业门类", domn);
		map07.put("产业属性", industrial_attribute);
		map07.put("资质证书", qualification_certificate);
		classLabelMap.put("07", map07);

		
		/*
		 * 社会保障
		 */
		Map<String, String> map08 = new LinkedMap();
		map08.put("参保年限", insured_years_desc);
		map08.put("社会保障号码", social_secur_num);
		map08.put("首次参保日期", first_ginseng_dt);
		map08.put("单位名称", insure_corp_nm);
		map08.put("失业原因", unemp_reason);
		map08.put("离退休类别", retired_type);
		map08.put("离退休日期", retired_dt);
		map08.put("低保家庭名称", low_family);
		map08.put("低保家庭编码", low_family_id);
		map08.put("低保家庭领取证号", low_lq_cd);
		map08.put("低保家庭申请原因描述", ow_reason);
		map08.put("低保家庭开户银行名称", ow_khyh_nm);
		map08.put("低保家庭银行帐号", low_khyh);
		map08.put("低保人员劳动能力代码", low_ability_cd);
		map08.put("低保人员类别代码", low_per_cd);
		map08.put("低保人员工作性质代码", low_work_cd);
		map08.put("低保人员月收入金额", low_month);
		map08.put("低保人员工作单位名称", low_work_units_nm);
		map08.put("低保人员工作单位性质代码", low_work_units_type);
		map08.put("低保金金额", low_amount);
		map08.put("低保金发放日期", low_issue_dt);
		map08.put("低保资金记录类型", low_record_type);
		map08.put("低保资金记录日期", low_record_dt);
		map08.put("低保资金记录金额", low_record_amount);
		map08.put("低保证号", low_no);
		map08.put("发证机关名称", low_fzjgnm);
		map08.put("发证机关行政区划", low_fzjgxz);
		map08.put("救助类型", rescue_type);
		map08.put("救助方式代码", rescue_mode);
		map08.put("救助金额", rescue_amount);
		map08.put("医疗救助方式", rescue_medical_mode);
		map08.put("救助人员类别", rescue_person_type);
		map08.put("救助时间", rescue_dt);
		map08.put("救助地点", rescue_place);
		map08.put("参保险种", insured_type);
		map08.put("参保缴费状态", insured_payment_state);
		map08.put("参与新农合", xnh_flg);
		map08.put("是否低保户", low_secu_family_flg);
		map08.put("致贫原因", povt_resn);
		map08.put("当前参保状态", cur_insure_stat);
		map08.put("城镇社保标识", insure_ind);
		map08.put("农村社保标识", coop_ind);
		map08.put("优抚类型", preferential_type_desc);
		map08.put("优抚月生活费标准", preferential_month_standar);
		map08.put("住房公积金个人账户状态", fund_personal_stat);
		map08.put("住房公积金个人缴存比例", fund_personal_proportion);
		map08.put("住房公积金个人月缴总额", fund_personal_total);
		map08.put("低保类型", low_secu_typ);
		classLabelMap.put("08", map08);

		
		/*
		 * 信用评价
		 */
		Map<String, String> map09 = new LinkedMap();
		map09.put("行政执法", enforcement);
		map09.put("司法", judicial);
		map09.put("个人荣誉", personal_honor);
		map09.put("“四史”情况", shis_situation);
		map09.put("“三涉”情况", sans_situation);
		map09.put("犯罪史标识", crime_his_flg);
		map09.put("刑满释放标识", full_release_flg);
		map09.put("重点青少年标识", teenager_flg);
		map09.put("上访标识", petition_flg);
		map09.put("住房公积金是否有贷款", fund_loan_flg);
		map09.put("住房公积金贷款合同状态", fund_loan_contract);
		map09.put("住房公积金贷款担保类型", fund_loan_guarantee);
		map09.put("住房公积金贷款还款方式", fund_loan_repayment);
		map09.put("不良信用类型", bad_credit_type);
		classLabelMap.put("09", map09);
			
			 
		/*
		 * 其它信息
		 */
		Map<String, String> map10 = new LinkedMap();
		map10.put("护照号", passport_no);
		map10.put("护照有效期", passport_validity);
		map10.put("护照签发机关", passport_organ);
		map10.put("户号", regi_num);
		map10.put("户籍信息状态", regi_infor_status);
		map10.put("出生证编号", birth_cert_id);
		map10.put("健康状况", hlth_stat);
		map10.put("纬度", location_lat);
		map10.put("经度", location_lon);
		map10.put("经纬度", lat_lon);
		
		classLabelMap.put("10", map10);
	}

	public String getPopu_nm() {
		return popu_nm;
	}

	public void setPopu_nm(String popu_nm) {
		this.popu_nm = popu_nm;
	}
	
	public String getCert_type_desc() {
		return cert_type_desc;
	}

	public void setCert_type_desc(String cert_type_desc) {
		this.cert_type_desc = cert_type_desc;
	}

	public String getBirth_add() {
		return birth_add;
	}

	public void setBirth_add(String birth_add) {
		this.birth_add = birth_add;
	}

	public String getRegi_add_cd_desc() {
		return regi_add_cd_desc;
	}

	public void setRegi_add_cd_desc(String regi_add_cd_desc) {
		this.regi_add_cd_desc = regi_add_cd_desc;
	}

	public String getSer_space() {
		return ser_space;
	}

	public void setSer_space(String ser_space) {
		this.ser_space = ser_space;
	}

	public String getDead_dt() {
		return dead_dt;
	}

	public void setDead_dt(String dead_dt) {
		this.dead_dt = dead_dt;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getMinority_flg() {
		return minority_flg;
	}

	public void setMinority_flg(String minority_flg) {
		this.minority_flg = minority_flg;
	}

	public String getBirth_years_cd() {
		return birth_years_cd;
	}

	public void setBirth_years_cd(String birth_years_cd) {
		this.birth_years_cd = birth_years_cd;
	}

	public String getComm_tel_flg() {
		return comm_tel_flg;
	}

	public void setComm_tel_flg(String comm_tel_flg) {
		this.comm_tel_flg = comm_tel_flg;
	}

	public String getReligion() {
		return religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

	public String getNew_zj_cd() {
		return new_zj_cd;
	}

	public void setNew_zj_cd(String new_zj_cd) {
		this.new_zj_cd = new_zj_cd;
	}

	public String getDengji_dt() {
		return dengji_dt;
	}

	public void setDengji_dt(String dengji_dt) {
		this.dengji_dt = dengji_dt;
	}

	public String getUndt_org() {
		return undt_org;
	}

	public void setUndt_org(String undt_org) {
		this.undt_org = undt_org;
	}

	public String getUndt_org_nm() {
		return undt_org_nm;
	}

	public void setUndt_org_nm(String undt_org_nm) {
		this.undt_org_nm = undt_org_nm;
	}

	public String getFamily_plan_type() {
		return family_plan_type;
	}

	public void setFamily_plan_type(String family_plan_type) {
		this.family_plan_type = family_plan_type;
	}

	public String getAdopt_dj_no() {
		return adopt_dj_no;
	}

	public void setAdopt_dj_no(String adopt_dj_no) {
		this.adopt_dj_no = adopt_dj_no;
	}

	public String getBrothers_sisters() {
		return brothers_sisters;
	}

	public void setBrothers_sisters(String brothers_sisters) {
		this.brothers_sisters = brothers_sisters;
	}

	public String getFlw_in_flg() {
		return flw_in_flg;
	}

	public void setFlw_in_flg(String flw_in_flg) {
		this.flw_in_flg = flw_in_flg;
	}

	public String getFlw_out_flg() {
		return flw_out_flg;
	}

	public void setFlw_out_flg(String flw_out_flg) {
		this.flw_out_flg = flw_out_flg;
	}

	public String getInner_flw_flg() {
		return inner_flw_flg;
	}

	public void setInner_flw_flg(String inner_flw_flg) {
		this.inner_flw_flg = inner_flw_flg;
	}

	public String getFlw_flg() {
		return flw_flg;
	}

	public void setFlw_flg(String flw_flg) {
		this.flw_flg = flw_flg;
	}

	public String getMigt_flg() {
		return migt_flg;
	}

	public void setMigt_flg(String migt_flg) {
		this.migt_flg = migt_flg;
	}

	public String getJoin_work_dt() {
		return join_work_dt;
	}

	public void setJoin_work_dt(String join_work_dt) {
		this.join_work_dt = join_work_dt;
	}

	public String getCompile_time() {
		return compile_time;
	}

	public void setCompile_time(String compile_time) {
		this.compile_time = compile_time;
	}

	public String getCompile_no() {
		return compile_no;
	}

	public void setCompile_no(String compile_no) {
		this.compile_no = compile_no;
	}

	public String getCompile_status() {
		return compile_status;
	}

	public void setCompile_status(String compile_status) {
		this.compile_status = compile_status;
	}

	public String getEmp_pro_te() {
		return emp_pro_te;
	}

	public void setEmp_pro_te(String emp_pro_te) {
		this.emp_pro_te = emp_pro_te;
	}

	public String getJob_category() {
		return job_category;
	}

	public void setJob_category(String job_category) {
		this.job_category = job_category;
	}

	public String getCivil_regist() {
		return civil_regist;
	}

	public void setCivil_regist(String civil_regist) {
		this.civil_regist = civil_regist;
	}

	public String getCron_no() {
		return cron_no;
	}

	public void setCron_no(String cron_no) {
		this.cron_no = cron_no;
	}

	public String getOut_compile_no() {
		return out_compile_no;
	}

	public void setOut_compile_no(String out_compile_no) {
		this.out_compile_no = out_compile_no;
	}

	public String getStatus_change() {
		return status_change;
	}

	public void setStatus_change(String status_change) {
		this.status_change = status_change;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getProfession_belong() {
		return profession_belong;
	}

	public void setProfession_belong(String profession_belong) {
		this.profession_belong = profession_belong;
	}

	public String getScience_area() {
		return science_area;
	}

	public void setScience_area(String science_area) {
		this.science_area = science_area;
	}

	public String getEstate_no() {
		return estate_no;
	}

	public void setEstate_no(String estate_no) {
		this.estate_no = estate_no;
	}

	public String getFund_personal_stat() {
		return fund_personal_stat;
	}

	public void setFund_personal_stat(String fund_personal_stat) {
		this.fund_personal_stat = fund_personal_stat;
	}

	public String getCar_oper_state() {
		return car_oper_state;
	}

	public void setCar_oper_state(String car_oper_state) {
		this.car_oper_state = car_oper_state;
	}

	public String getRoad_transport() {
		return road_transport;
	}

	public void setRoad_transport(String road_transport) {
		this.road_transport = road_transport;
	}

	public String getCar_dj_no() {
		return car_dj_no;
	}

	public void setCar_dj_no(String car_dj_no) {
		this.car_dj_no = car_dj_no;
	}

	public String getCar_driving_no() {
		return car_driving_no;
	}

	public void setCar_driving_no(String car_driving_no) {
		this.car_driving_no = car_driving_no;
	}

	public String getDriving_num() {
		return driving_num;
	}

	public void setDriving_num(String driving_num) {
		this.driving_num = driving_num;
	}

	public String getAsset_equipment() {
		return asset_equipment;
	}

	public void setAsset_equipment(String asset_equipment) {
		this.asset_equipment = asset_equipment;
	}

	public String getAsset_financial() {
		return asset_financial;
	}

	public void setAsset_financial(String asset_financial) {
		this.asset_financial = asset_financial;
	}

	public String getAsset_intangible() {
		return asset_intangible;
	}

	public void setAsset_intangible(String asset_intangible) {
		this.asset_intangible = asset_intangible;
	}

	public String getDisability_no() {
		return disability_no;
	}

	public void setDisability_no(String disability_no) {
		this.disability_no = disability_no;
	}

	public String getDead_reason() {
		return dead_reason;
	}

	public void setDead_reason(String dead_reason) {
		this.dead_reason = dead_reason;
	}

	public String getDead_location() {
		return dead_location;
	}

	public void setDead_location(String dead_location) {
		this.dead_location = dead_location;
	}

	public String getHospitalization_con() {
		return hospitalization_con;
	}

	public void setHospitalization_con(String hospitalization_con) {
		this.hospitalization_con = hospitalization_con;
	}

	public String getDisability_level() {
		return disability_level;
	}

	public void setDisability_level(String disability_level) {
		this.disability_level = disability_level;
	}

	public String getGrad_school() {
		return grad_school;
	}

	public void setGrad_school(String grad_school) {
		this.grad_school = grad_school;
	}

	public String getGrad_time() {
		return grad_time;
	}

	public void setGrad_time(String grad_time) {
		this.grad_time = grad_time;
	}

	public String getChoose_lang() {
		return choose_lang;
	}

	public void setChoose_lang(String choose_lang) {
		this.choose_lang = choose_lang;
	}

	public String getStud_stat_id() {
		return stud_stat_id;
	}

	public void setStud_stat_id(String stud_stat_id) {
		this.stud_stat_id = stud_stat_id;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getVoct_typ() {
		return voct_typ;
	}

	public void setVoct_typ(String voct_typ) {
		this.voct_typ = voct_typ;
	}

	public String getIndustrial_attribute() {
		return industrial_attribute;
	}

	public void setIndustrial_attribute(String industrial_attribute) {
		this.industrial_attribute = industrial_attribute;
	}

	public String getQualification_certificate() {
		return qualification_certificate;
	}

	public void setQualification_certificate(String qualification_certificate) {
		this.qualification_certificate = qualification_certificate;
	}

	public String getSocial_secur_num() {
		return social_secur_num;
	}

	public void setSocial_secur_num(String social_secur_num) {
		this.social_secur_num = social_secur_num;
	}

	public String getFirst_ginseng_dt() {
		return first_ginseng_dt;
	}

	public void setFirst_ginseng_dt(String first_ginseng_dt) {
		this.first_ginseng_dt = first_ginseng_dt;
	}

	public String getInsure_corp_nm() {
		return insure_corp_nm;
	}

	public void setInsure_corp_nm(String insure_corp_nm) {
		this.insure_corp_nm = insure_corp_nm;
	}

	public String getUnemp_reason() {
		return unemp_reason;
	}

	public void setUnemp_reason(String unemp_reason) {
		this.unemp_reason = unemp_reason;
	}

	public String getRetired_type() {
		return retired_type;
	}

	public void setRetired_type(String retired_type) {
		this.retired_type = retired_type;
	}

	public String getRetired_dt() {
		return retired_dt;
	}

	public void setRetired_dt(String retired_dt) {
		this.retired_dt = retired_dt;
	}

	public String getLow_family() {
		return low_family;
	}

	public void setLow_family(String low_family) {
		this.low_family = low_family;
	}

	public String getLow_family_id() {
		return low_family_id;
	}

	public void setLow_family_id(String low_family_id) {
		this.low_family_id = low_family_id;
	}

	public String getLow_lq_cd() {
		return low_lq_cd;
	}

	public void setLow_lq_cd(String low_lq_cd) {
		this.low_lq_cd = low_lq_cd;
	}

	public String getOw_reason() {
		return ow_reason;
	}

	public void setOw_reason(String ow_reason) {
		this.ow_reason = ow_reason;
	}

	public String getOw_khyh_nm() {
		return ow_khyh_nm;
	}

	public void setOw_khyh_nm(String ow_khyh_nm) {
		this.ow_khyh_nm = ow_khyh_nm;
	}

	public String getLow_khyh() {
		return low_khyh;
	}

	public void setLow_khyh(String low_khyh) {
		this.low_khyh = low_khyh;
	}

	public String getLow_ability_cd() {
		return low_ability_cd;
	}

	public void setLow_ability_cd(String low_ability_cd) {
		this.low_ability_cd = low_ability_cd;
	}

	public String getLow_per_cd() {
		return low_per_cd;
	}

	public void setLow_per_cd(String low_per_cd) {
		this.low_per_cd = low_per_cd;
	}

	public String getLow_work_cd() {
		return low_work_cd;
	}

	public void setLow_work_cd(String low_work_cd) {
		this.low_work_cd = low_work_cd;
	}

	public String getLow_month() {
		return low_month;
	}

	public void setLow_month(String low_month) {
		this.low_month = low_month;
	}

	public String getLow_work_units_nm() {
		return low_work_units_nm;
	}

	public void setLow_work_units_nm(String low_work_units_nm) {
		this.low_work_units_nm = low_work_units_nm;
	}

	public String getLow_work_units_type() {
		return low_work_units_type;
	}

	public void setLow_work_units_type(String low_work_units_type) {
		this.low_work_units_type = low_work_units_type;
	}

	public String getLow_amount() {
		return low_amount;
	}

	public void setLow_amount(String low_amount) {
		this.low_amount = low_amount;
	}

	public String getLow_issue_dt() {
		return low_issue_dt;
	}

	public void setLow_issue_dt(String low_issue_dt) {
		this.low_issue_dt = low_issue_dt;
	}

	public String getLow_record_type() {
		return low_record_type;
	}

	public void setLow_record_type(String low_record_type) {
		this.low_record_type = low_record_type;
	}

	public String getLow_record_dt() {
		return low_record_dt;
	}

	public void setLow_record_dt(String low_record_dt) {
		this.low_record_dt = low_record_dt;
	}

	public String getLow_record_amount() {
		return low_record_amount;
	}

	public void setLow_record_amount(String low_record_amount) {
		this.low_record_amount = low_record_amount;
	}

	public String getLow_no() {
		return low_no;
	}

	public void setLow_no(String low_no) {
		this.low_no = low_no;
	}

	public String getLow_fzjgnm() {
		return low_fzjgnm;
	}

	public void setLow_fzjgnm(String low_fzjgnm) {
		this.low_fzjgnm = low_fzjgnm;
	}

	public String getLow_fzjgxz() {
		return low_fzjgxz;
	}

	public void setLow_fzjgxz(String low_fzjgxz) {
		this.low_fzjgxz = low_fzjgxz;
	}

	public String getRescue_type() {
		return rescue_type;
	}

	public void setRescue_type(String rescue_type) {
		this.rescue_type = rescue_type;
	}

	public String getRescue_mode() {
		return rescue_mode;
	}

	public void setRescue_mode(String rescue_mode) {
		this.rescue_mode = rescue_mode;
	}

	public String getRescue_amount() {
		return rescue_amount;
	}

	public void setRescue_amount(String rescue_amount) {
		this.rescue_amount = rescue_amount;
	}

	public String getRescue_medical_mode() {
		return rescue_medical_mode;
	}

	public void setRescue_medical_mode(String rescue_medical_mode) {
		this.rescue_medical_mode = rescue_medical_mode;
	}

	public String getRescue_person_type() {
		return rescue_person_type;
	}

	public void setRescue_person_type(String rescue_person_type) {
		this.rescue_person_type = rescue_person_type;
	}

	public String getRescue_dt() {
		return rescue_dt;
	}

	public void setRescue_dt(String rescue_dt) {
		this.rescue_dt = rescue_dt;
	}

	public String getRescue_place() {
		return rescue_place;
	}

	public void setRescue_place(String rescue_place) {
		this.rescue_place = rescue_place;
	}

	public String getInsured_type() {
		return insured_type;
	}

	public void setInsured_type(String insured_type) {
		this.insured_type = insured_type;
	}

	public String getXnh_flg() {
		return xnh_flg;
	}

	public void setXnh_flg(String xnh_flg) {
		this.xnh_flg = xnh_flg;
	}

	public String getLow_secu_family_flg() {
		return low_secu_family_flg;
	}

	public void setLow_secu_family_flg(String low_secu_family_flg) {
		this.low_secu_family_flg = low_secu_family_flg;
	}

	public String getInsure_ind() {
		return insure_ind;
	}

	public void setInsure_ind(String insure_ind) {
		this.insure_ind = insure_ind;
	}

	public String getCoop_ind() {
		return coop_ind;
	}

	public void setCoop_ind(String coop_ind) {
		this.coop_ind = coop_ind;
	}

	public String getPreferential_month_standar() {
		return preferential_month_standar;
	}

	public void setPreferential_month_standar(String preferential_month_standar) {
		this.preferential_month_standar = preferential_month_standar;
	}

	public String getFund_personal_proportion() {
		return fund_personal_proportion;
	}

	public void setFund_personal_proportion(String fund_personal_proportion) {
		this.fund_personal_proportion = fund_personal_proportion;
	}

	public String getFund_personal_total() {
		return fund_personal_total;
	}

	public void setFund_personal_total(String fund_personal_total) {
		this.fund_personal_total = fund_personal_total;
	}

	public String getEnforcement() {
		return enforcement;
	}

	public void setEnforcement(String enforcement) {
		this.enforcement = enforcement;
	}

	public String getJudicial() {
		return judicial;
	}

	public void setJudicial(String judicial) {
		this.judicial = judicial;
	}

	public String getShis_situation() {
		return shis_situation;
	}

	public void setShis_situation(String shis_situation) {
		this.shis_situation = shis_situation;
	}

	public String getSans_situation() {
		return sans_situation;
	}

	public void setSans_situation(String sans_situation) {
		this.sans_situation = sans_situation;
	}

	public String getCrime_his_flg() {
		return crime_his_flg;
	}

	public void setCrime_his_flg(String crime_his_flg) {
		this.crime_his_flg = crime_his_flg;
	}

	public String getFull_release_flg() {
		return full_release_flg;
	}

	public void setFull_release_flg(String full_release_flg) {
		this.full_release_flg = full_release_flg;
	}

	public String getTeenager_flg() {
		return teenager_flg;
	}

	public void setTeenager_flg(String teenager_flg) {
		this.teenager_flg = teenager_flg;
	}

	public String getPetition_flg() {
		return petition_flg;
	}

	public void setPetition_flg(String petition_flg) {
		this.petition_flg = petition_flg;
	}

	public String getFund_loan_flg() {
		return fund_loan_flg;
	}

	public void setFund_loan_flg(String fund_loan_flg) {
		this.fund_loan_flg = fund_loan_flg;
	}

	public String getBad_credit_type() {
		return bad_credit_type;
	}

	public void setBad_credit_type(String bad_credit_type) {
		this.bad_credit_type = bad_credit_type;
	}

	public String getPassport_no() {
		return passport_no;
	}

	public void setPassport_no(String passport_no) {
		this.passport_no = passport_no;
	}

	public String getPassport_validity() {
		return passport_validity;
	}

	public void setPassport_validity(String passport_validity) {
		this.passport_validity = passport_validity;
	}

	public String getPassport_organ() {
		return passport_organ;
	}

	public void setPassport_organ(String passport_organ) {
		this.passport_organ = passport_organ;
	}

	public String getRegi_num() {
		return regi_num;
	}

	public void setRegi_num(String regi_num) {
		this.regi_num = regi_num;
	}

	public String getRegi_infor_status() {
		return regi_infor_status;
	}

	public void setRegi_infor_status(String regi_infor_status) {
		this.regi_infor_status = regi_infor_status;
	}

	public String getBirth_cert_id() {
		return birth_cert_id;
	}

	public void setBirth_cert_id(String birth_cert_id) {
		this.birth_cert_id = birth_cert_id;
	}

	public Map<String, Map<String, String>> getClassLabelMap() {
		return classLabelMap;
	}

	public void setClassLabelMap(Map<String, Map<String, String>> classLabelMap) {
		this.classLabelMap = classLabelMap;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getNative_plc_desc() {
		return native_plc_desc;
	}

	public void setNative_plc_desc(String native_plc_desc) {
		this.native_plc_desc = native_plc_desc;
	}

	public String getHshld_prop() {
		return hshld_prop;
	}

	public void setHshld_prop(String hshld_prop) {
		this.hshld_prop = hshld_prop;
	}

	public String getPoli_stat() {
		return poli_stat;
	}

	public void setPoli_stat(String poli_stat) {
		this.poli_stat = poli_stat;
	}

	public String getNatv_plc_urbn_nm() {
		return natv_plc_urbn_nm;
	}

	public void setNatv_plc_urbn_nm(String natv_plc_urbn_nm) {
		this.natv_plc_urbn_nm = natv_plc_urbn_nm;
	}

	public String getEdu_Degr() {
		return Edu_Degr;
	}

	public void setEdu_Degr(String edu_Degr) {
		Edu_Degr = edu_Degr;
	}

	public String getMarrg_stat() {
		return marrg_stat;
	}

	public void setMarrg_stat(String marrg_stat) {
		this.marrg_stat = marrg_stat;
	}

	public String getSrc_prov_desc() {
		return src_prov_desc;
	}

	public void setSrc_prov_desc(String src_prov_desc) {
		this.src_prov_desc = src_prov_desc;
	}

	public String getHn_perm_resid_coty() {
		return hn_perm_resid_coty;
	}

	public void setHn_perm_resid_coty(String hn_perm_resid_coty) {
		this.hn_perm_resid_coty = hn_perm_resid_coty;
	}

	public String getNorm_live_house_desc() {
		return norm_live_house_desc;
	}

	public void setNorm_live_house_desc(String norm_live_house_desc) {
		this.norm_live_house_desc = norm_live_house_desc;
	}

	public String getWork_stat() {
		return work_stat;
	}

	public void setWork_stat(String work_stat) {
		this.work_stat = work_stat;
	}

	public String getInds_fst_class() {
		return inds_fst_class;
	}

	public void setInds_fst_class(String inds_fst_class) {
		this.inds_fst_class = inds_fst_class;
	}

	public String getUnit_type_nm() {
		return unit_type_nm;
	}

	public void setUnit_type_nm(String unit_type_nm) {
		this.unit_type_nm = unit_type_nm;
	}

	public String getJoin_work_age() {
		return join_work_age;
	}

	public void setJoin_work_age(String join_work_age) {
		this.join_work_age = join_work_age;
	}

	public String getPhone_belong_prov() {
		return phone_belong_prov;
	}

	public void setPhone_belong_prov(String phone_belong_prov) {
		this.phone_belong_prov = phone_belong_prov;
	}

	public String getEstate() {
		return estate;
	}

	public void setEstate(String estate) {
		this.estate = estate;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}

	public String getDisease_type_desc() {
		return disease_type_desc;
	}

	public void setDisease_type_desc(String disease_type_desc) {
		this.disease_type_desc = disease_type_desc;
	}

	public String getHlth_stat() {
		return hlth_stat;
	}

	public void setHlth_stat(String hlth_stat) {
		this.hlth_stat = hlth_stat;
	}

	public String getDisability_type_nm() {
		return disability_type_nm;
	}

	public void setDisability_type_nm(String disability_type_nm) {
		this.disability_type_nm = disability_type_nm;
	}

	public String getCond() {
		return cond;
	}

	public void setCond(String cond) {
		this.cond = cond;
	}

	public String getTalt_typ() {
		return talt_typ;
	}

	public void setTalt_typ(String talt_typ) {
		this.talt_typ = talt_typ;
	}

	public String getDomn() {
		return domn;
	}

	public void setDomn(String domn) {
		this.domn = domn;
	}

	public String getInsured_years_desc() {
		return insured_years_desc;
	}

	public void setInsured_years_desc(String insured_years_desc) {
		this.insured_years_desc = insured_years_desc;
	}

	public String getInsured_payment_state() {
		return insured_payment_state;
	}

	public void setInsured_payment_state(String insured_payment_state) {
		this.insured_payment_state = insured_payment_state;
	}

	public String getPovt_resn() {
		return povt_resn;
	}

	public void setPovt_resn(String povt_resn) {
		this.povt_resn = povt_resn;
	}

	public String getCur_insure_stat() {
		return cur_insure_stat;
	}

	public void setCur_insure_stat(String cur_insure_stat) {
		this.cur_insure_stat = cur_insure_stat;
	}

	public String getPreferential_type_desc() {
		return preferential_type_desc;
	}

	public void setPreferential_type_desc(String preferential_type_desc) {
		this.preferential_type_desc = preferential_type_desc;
	}

	public String getLow_secu_typ() {
		return low_secu_typ;
	}

	public void setLow_secu_typ(String low_secu_typ) {
		this.low_secu_typ = low_secu_typ;
	}

	public String getPersonal_honor() {
		return personal_honor;
	}

	public void setPersonal_honor(String personal_honor) {
		this.personal_honor = personal_honor;
	}

	public String getFund_loan_contract() {
		return fund_loan_contract;
	}

	public void setFund_loan_contract(String fund_loan_contract) {
		this.fund_loan_contract = fund_loan_contract;
	}

	public String getFund_loan_guarantee() {
		return fund_loan_guarantee;
	}

	public void setFund_loan_guarantee(String fund_loan_guarantee) {
		this.fund_loan_guarantee = fund_loan_guarantee;
	}

	public String getFund_loan_repayment() {
		return fund_loan_repayment;
	}

	public void setFund_loan_repayment(String fund_loan_repayment) {
		this.fund_loan_repayment = fund_loan_repayment;
	}

	public String getZjhm() {
		return zjhm;
	}

	public void setZjhm(String zjhm) {
		this.zjhm = zjhm;
	}

	public String getBirth_dt() {
		return birth_dt;
	}

	public void setBirth_dt(String birth_dt) {
		this.birth_dt = birth_dt;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getDefaultHead() {
		return defaultHead;
	}

	public void setDefaultHead(String defaultHead) {
		this.defaultHead = defaultHead;
	}
	
	public String getLocation_lat() {
		return location_lat;
	}

	public void setLocation_lat(String location_lat) {
		this.location_lat = location_lat;
	}

	public String getLocation_lon() {
		return location_lon;
	}

	public void setLocation_lon(String location_lon) {
		this.location_lon = location_lon;
	}

	public String getLat_lon() {
		return lat_lon;
	}

	public void setLat_lon(String lat_lon) {
		this.lat_lon = lat_lon;
	}

	
}