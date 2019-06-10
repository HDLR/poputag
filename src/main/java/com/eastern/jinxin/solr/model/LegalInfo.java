package com.eastern.jinxin.solr.model;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.beans.Field;

public class LegalInfo {

	/*
	 * 法人基本信息
	 */
	
	//统一法人编号
	@Field("legal_17_unif_corp_id")
	private String unif_corp_id;
	
	//法人编号
	@Field("legal_17_lpr_id")
	private String lpr_id;
	
	//法定代表人姓名	
	@Field("legal_17_lpr_nm")
	private String lpr_nm;
	
	//企业注册号
	@Field("legal_17_corp_rgst_num")
	private String corp_rgst_num;
	
	
	//统一社会信用代码	
	@Field("legal_17_uscc")
	private String uscc;
	
	//机构名称	
	@Field("legal_17_corp_nm")
	private String corp_nm;
	
	//机构类型	
	@Field("legal_17_corp_typ_cd")
	private String corp_typ_cd;
	
	//经营或业务范围	
	@Field("legal_17_busi_scop")
	private String busi_scop;
	
	//法定代表人或负责人	
	@Field("legal_17_leg_pers_popu_id")
	private String leg_pers_popu_id;
	
	//住所所在地	
	@Field("legal_17_rgst_addr")
	private String rgst_addr;
	
	//成立日期或设立日期	
	@Field("legal_17_estb_dt")
	private String estb_dt;
	
	//登记机关	
	@Field("legal_17_rgst_org_nm")
	private String rgst_org_nm;
	
	//注销或撤消日期	
	@Field("legal_17_remov_dt")
	private String remov_dt;
	
	//法定代表人/负责人公民身份证号码	
	@Field("legal_17_lpr_cert_num")
	private String lpr_cert_num;
	
	//联系电话	
	@Field("legal_17_cont_tel")
	private String cont_tel;
	
	//企业经营状态	
	@Field("legal_17_busi_opr_stat_desc")
	private String busi_opr_stat_desc;
	
	//法人类型	
	@Field("legal_17_lpr_type_desc")
	private String lpr_type_desc;
	
	//注册市县	
	@Field("legal_17_rgst_county_desc")
	private String rgst_county_desc;
	
	//行业类型	
	@Field("legal_17_industry_type_desc")
	private String industry_type_desc;
	
	//产业属性	
	@Field("legal_17_industrial_attribute_nm")
	private String industrial_attribute_nm;
	
	//注册资本	
	@Field("legal_17_rgst_capt")
	private String rgst_capt;
	
	//股东数量	
	@Field("legal_17_shareholder_cnt")
	private String shareholder_cnt;

	
	
	/*
	 * 生产经营
	 */
	
	//许可证号	
	@Field("legal_17_license_no")
	private String license_no;
	
	//许可事项	
	@Field("legal_17_license_item")
	private String license_item;
	
	//起始期	
	@Field("legal_17_license_begin_dt")
	private String license_begin_dt;
	
	//终止期(有效期)	
	@Field("legal_17_license_end_dt")
	private String license_end_dt;
	
	//发证日期	
	@Field("legal_17_license_fz_dt")
	private String license_fz_dt;   
	
	//许可证书状态	
	@Field("legal_17_license_certi_stat")
	private String license_certi_stat;
	
	//发证机关(机构)	
	@Field("legal_17_license_org")
	private String license_org;
	
	//主营业务成本	
	@Field("legal_17_product_main_bus_cost")
	private String product_main_bus_cost;
	
	//营业税金及附加	
	@Field("legal_17_product_tax_addition")
	private String product_tax_addition;
	
	//营业费用	
	@Field("legal_17_product_busi_cost")
	private String product_busi_cost;   
	
	//管理费用	
	@Field("legal_17_product_manage_cost")
	private String product_manage_cost; 
	
	//财务费用	
	@Field("legal_17_product_finance_cost")
	private String product_finance_cost;
	
	//实际利润额	
	@Field("legal_17_product_actual_profits")
	private String product_actual_profits;
	
	//经纳税调整后的生产经营所得	
	@Field("legal_17_product_after_adjust_income")
	private String product_after_adjust_income;
	
	//投资金额	
	@Field("legal_17_product_invest_amount")
	private String product_invest_amount;
	
	//筹资费用
	@Field("legal_17_product_raise_cost")
	private String product_raise_cost;   
	
	//利润总额
	@Field("legal_17_product_profits")
	private String product_profits;     
	
	//所得税费用
	@Field("legal_17_product_income_tax")
	private String product_income_tax;
	
	//一年内到期的非流动负债
	@Field("legal_17_product_oyear_no_flow_lia")
	private String product_oyear_no_flow_lia;
	
	//其他流动负债
	@Field("legal_17_product_other_flow_lia")
	private String product_other_flow_lia;
	
	//流动负债合计
	@Field("legal_17_product_flow_lia_total")
	private String product_flow_lia_total;
	
	//经营规模
	@Field("legal_17_busi_size_desc")
	private String busi_size_desc;
	
	//经营类型
	@Field("legal_17_busi_type_desc")
	private String busi_type_desc;
	
	//营业收入
	@Field("legal_17_busi_income")
	private String busi_income;      
	
	//资质许可-工程建设
	@Field("legal_17_build_qficat_lice")
	private String build_qficat_lice; 
	
	//资质许可-房屋管理
	@Field("legal_17_house_qficat_lice")
	private String house_qficat_lice; 
	
	//资质许可-工程设计
	@Field("legal_17_design_qficat_lice")
	private String design_qficat_lice; 
	
	//资质许可-城市规划
	@Field("legal_17_city_qficat_lice")
	private String city_qficat_lice;   
	
	//资质许可-旅游服务
	@Field("legal_17_tour_qficat_lice")
	private String tour_qficat_lice; 
	
	//资质许可-生态环境
	@Field("legal_17_environ_qficat_lice")
	private String environ_qficat_lice;
	
	//资质许可-未知
	@Field("legal_17_unknown_qficat_lice")
	private String unknown_qficat_lice;
	
	//企业变更
	@Field("legal_17_busi_change_desc")
	private String busi_change_desc;
	
	//员工数量
	@Field("legal_17_staff_cnt")
	private String staff_cnt;
	 
	//是否上市公司
	@Field("legal_17_is_listed_company")
	private String is_listed_company;
	
	//是否危险品经营企业
	@Field("legal_17_is_dangerous_busi")
	private String is_dangerous_busi;
	
	//是否排污企业
	@Field("legal_17_is_sewage_busi")
	private String is_sewage_busi;
	
	//主营业务收入
	
	@Field("legal_17_product_main_bus_income")
	private String product_main_bus_income;
	/*
	 * 资产状况
	 */
	
	//固定资产
	@Field("legal_17_product_fixed_assets")
	private String product_fixed_assets;
	
	//递延所得
	@Field("legal_17_product_deferred_income")
	private String product_deferred_income;
	
	//流动资产
	@Field("legal_17_product_flow_asset")
	private String product_flow_asset;
	
	//无形资产
	@Field("legal_17_product_int_asset")
	private String product_int_asset;
	
	//不动产-房产
	@Field("legal_17_house_estate")
	private String house_estate;   
	
	//不动产-地产
	@Field("legal_17_land_estate")
	private String land_estate;
	
	//不动产-矿产
	@Field("legal_17_mine_estate")
	private String mine_estate;
	
	//不动产-设备
	@Field("legal_17_equipment_estate")
	private String equipment_estate;
	
	//不动产-其它
	@Field("legal_17_other_estate")
	private String other_estate; 
	
	//金融资产-持有至到期投资
	@Field("legal_17_hold_matu_asset")
	private String hold_matu_asset;
	
	//金融资产-可供出售金融资产
	@Field("legal_17_avai_sale_asset")
	private String avai_sale_asset;
	
	//金融资产-贷款
	@Field("legal_17_loan_asset")
	private String loan_asset;
	
	//金融资产-应收款项
	@Field("legal_17_rece_asset")
	private String rece_asset;
	
	//金融资产-其它
	@Field("legal_17_other_asset")
	private String other_asset;
	
	//机器设备-生产工艺类设备	
	@Field("legal_17_prod_process_mac")
	private String prod_process_mac;
	
	//机器设备-辅助生产设备
	@Field("legal_17_aux_prod_mach")
	private String aux_prod_mach;
	
	//机器设备-服务设备
	@Field("legal_17_service_mach")
	private String service_mach;    
	
	//机器设备-其它
	@Field("legal_17_other_mach")
	private String other_mach;      
	
	//交通工具-汽车
	@Field("legal_17_car_vehicle")
	private String car_vehicle;     
	
	//交通工具-飞机
	@Field("legal_17_airplane_vehicle")
	private String airplane_vehicle;
	
	//交通工具-船
	@Field("legal_17_ship_vehicle")
	private String ship_vehicle;
	
	//交通工具-游轮
	@Field("legal_17_cruise_vehicle")
	private String cruise_vehicle;  
	
	//交通工具-游艇
	@Field("legal_17_yacht_vehicle")
	private String yacht_vehicle;   
	
	//交通工具-其它
	@Field("legal_17_other_vehicle")
	private String other_vehicle;  
	
	//无形资产-货币资金
	@Field("legal_17_funds_asset_int")
	private String funds_asset_int;
	
	//无形资产-应收帐款
	@Field("legal_17_account_recei_asset_int")
	private String account_recei_asset_int;
	
	//无形资产-金融资产
	@Field("legal_17_fina_asset_int")
	private String fina_asset_int;
	
	
	//无形资产-知识产权
	@Field("legal_17_knowledge_asset_int")
	private String knowledge_asset_int;
	
	//无形资产-其它
	@Field("legal_17_other_asset_int")
	private String other_asset_int    ;
	
	//负债-流动负债
	@Field("legal_17_flow_liabi")
	private String flow_liabi         ;
	
	//负债-长期负债
	@Field("legal_17_long_liabi")
	private String long_liabi         ;
	
	//负债-其它
	@Field("legal_17_other_liabi")
	private String other_liabi        ;
	
	//荣誉-著名驰名商标企业
	@Field("legal_17_fams_brand_honor")
	private String fams_brand_honor   ;
	
	//荣誉-技术创新企业
	@Field("legal_17_tech_inno_honor")
	private String tech_inno_honor    ;
	
	//荣誉-首重企业
	@Field("legal_17_importance_honor")
	private String importance_honor   ;
	
	//荣誉-其它
	@Field("legal_17_other_honor")
	private String other_honor        ;
	
	//督查检查-正常
	@Field("legal_17_normal_inspec")
	private String normal_inspec      ;
	
	//督查检查-质量检查异常
	@Field("legal_17_qua_abnormal_inspec")
	private String qua_abnormal_inspec;
	
	//督查检查-食品药品检查异常
	@Field("legal_17_")
	private String food_abnormal_inspec;
	
	//督查检查-环境检查异常
	@Field("legal_17_environ_abnormal_inspec")
	private String environ_abnormal_inspec;
	
	//督查检查-价格检查异常
	@Field("legal_17_price_abnormal_inspec")
	private String price_abnormal_inspec;
	
	//督查检查-安全生产检查异常
	@Field("legal_17_safe_prod_abnormal_inspec")
	private String safe_prod_abnormal_inspec;
	
	//督查检查-其它检查异常
	@Field("legal_17_other_abnormal_inspec")
	private String other_abnormal_inspec;
	
	//行政处罚-警告
	@Field("legal_17_notice_punish")
	private String notice_punish      ;
	
	//行政处罚-罚款
	@Field("legal_17_fine_punish")
	private String fine_punish        ;
	
	//行政处罚-没收违法所得
	@Field("legal_17_confi_income_punish")
	private String confi_income_punish;
	
	//行政处罚-责令停产停业
	@Field("legal_17_stop_busi_punish")
	private String stop_busi_punish   ;
	
	//行政处罚-暂扣或者吊销许可证
	@Field("legal_17_withhold_revoca_punish")
	private String withhold_revoca_punish;
	
	//行政处罚-行政拘留
	@Field("legal_17_detention_punish")
	private String detention_punish   ;
	
	//行政处罚-法律、行政法规规定的其它行政处罚
	@Field("legal_17_other_punish")
	private String other_punish       ;
	
	//行政处罚-未知
	@Field("legal_17_unkown_punish")
	private String unkown_punish      ;
	
	//行政处罚次数
	@Field("legal_17_punish_cnt")
	private String punish_cnt         ;
	
	//司法涉案标识-失信被执行人
	@Field("legal_17_discredit_judge")
	private String discredit_judge    ;
	
	//司法涉案标识-未履行生效裁判
	@Field("legal_17_unful_judge")
	private String unful_judge        ;
	
	//司法涉案标识-不涉及
	@Field("legal_17_no_judge")
	private String no_judge           ;
	
	//是否异常企业名录
	@Field("legal_17_abnormal_busi_list")
	private String abnormal_busi_list ;
	
	//无形资产-长期股权投资
	@Field("legal_17_long_equity_asset_int")
	private String long_equity_asset_int ;
	
	/*
	 * 纳税参保
	 */
	
	//是否税务非正常户
	@Field("legal_17_tax_unusual")
	private String tax_unusual        ;
	
	//是否黑名单企业
	@Field("legal_17_is_black_flg")
	private String is_black_flg       ;
		
	//纳税信用级别
	@Field("legal_17_tax_unusual")
	private String tax_credit_level_desc        ;
		
	//税务登记种类
	@Field("legal_17_is_black_flg")
	private String tax_rgst_type_desc       ;
	
	//纳税人状态代码
	@Field("legal_17_taxer_stat")
	private String taxer_stat;     
	
	//税务登记机关名称
	@Field("legal_17_tax_rgst_org")
	private String tax_rgst_org;    
	
	//税务登记日期
	@Field("legal_17_tax_rgst_dt")
	private String tax_rgst_dt;       
	
	//非正常户认定日期
	@Field("legal_17_unus_corp_start_dt")
	private String unus_corp_start_dt;
	
	//解除非正常户日期
	@Field("legal_17_unus_corp_stop_dt")
	private String unus_corp_stop_dt; 
	
	//登记注册类型代码
	@Field("legal_17_tax_rgst_typ_cd")
	private String tax_rgst_typ_cd;  
	
	//注销原因代码
	@Field("legal_17_tax_rgst_typ_desc")
	private String tax_rgst_typ_desc; 
	
	//注销税务机关(批准机关名称)
	@Field("legal_17_tax_revok_reson")
	private String tax_revok_reson;  
	
	//税控码验证日期
	@Field("legal_17_tax_valid_dt")
	private String tax_valid_dt;
	
	//所属税务机关代码
	@Field("legal_17_tax_belg_dept_cd")
	private String tax_belg_dept_cd;
	
	//参保单位名称
	@Field("legal_17_insure_corp_nm")
	private String insure_corp_nm;    
	
	//单位参保日期
	@Field("legal_17_insure_dt")
	private String insure_dt;          
	
	//单位缴存人数
	@Field("legal_17_insure_person_cnt")
	private String insure_person_cnt;  
	
	//单位账户状态
	@Field("legal_17_insure_account_stat")
	private String insure_account_stat;
	
	//单位缴存比例
	@Field("legal_17_insure_deposite")
	private String insure_deposite;
	
	//纳税金额
	@Field("legal_17_tax_amount")
	private String tax_amount;
	
	//纳税状态
	@Field("legal_17_tax_state_desc")
	private String tax_state_desc;
	
	//参保缴费状态
	@Field("legal_17_insured_payment_state_nm")
	private String insured_payment_state_nm;
	
	//是否缴存公积金
	@Field("legal_17_is_fund_flg")
	private String is_fund_flg;
	
	//单位住房公积金缴存人数
	@Field("legal_17_fund_persons")
	private String fund_persons;           
	
	//住房公积金单位账户状态
	@Field("legal_17_fund_company_stat")
	private String fund_company_stat;     
	
	//住房公积金单位缴存比例
	@Field("legal_17_fund_company_proportion")
	private String fund_company_proportion;
		
	//是否跨区域涉税
	@Field("legal_17_tax_cross_region")
	private String tax_cross_region;
	
	
	/*
	 * 其它信息
	 */
	
	//变更原因
	@Field("legal_17_remov_accept_resn")
	private String remov_accept_resn;
	
	//受理日期
	@Field("legal_17_remov_accept_dt")
	private String remov_accept_dt;
	
	//项目(变更内容)
	@Field("legal_17_remov_content")
	private String remov_content;
	
	//注销原因
	@Field("legal_17_remov_reas")
	private String remov_reas;
	
	//纬度
	@Field("legal_17_location_lat")
	private String location_lat;
	
	
	//经度
	@Field("legal_17_location_lon")
	private String location_lon;
	
	//经度纬度合并
	@Field("legal_17_lat_lon_rpt")
	private String lat_lon_rpt;
	
	
	private Map<String, Map<String, String>> classLabelMap = null;
	
	public void createMap(){
		classLabelMap = new LinkedMap();
		
		/*
		 * 法人基本信息
		 */
		Map<String, String> map01 = new LinkedMap();
		map01.put("机构名称", corp_nm);
		map01.put("统一社会信用代码", uscc);	
		map01.put("经营或业务范围", busi_scop);	
		map01.put("行业类型", industry_type_desc);	
		map01.put("法定代表人姓名", lpr_nm);
		map01.put("法定代表人/负责人公民身份证号码", lpr_cert_num);	
		map01.put("法人类型", lpr_type_desc);	
		
		map01.put("企业经营状态", busi_opr_stat_desc);	
		map01.put("注册市县", rgst_county_desc);	
		map01.put("产业属性", industrial_attribute_nm);	
		map01.put("注册资本", rgst_capt);	
		map01.put("股东数量", shareholder_cnt);
		map01.put("机构类型", corp_typ_cd);
//		map01.put("法定代表人或负责人", leg_pers_popu_id);
		map01.put("住所所在地", rgst_addr);	
		map01.put("成立日期或设立日期", estb_dt);
		map01.put("登记机关", rgst_org_nm);	
		map01.put("联系电话", cont_tel);	
		map01.put("企业注册号", corp_rgst_num);
		map01.put("注销或撤消日期", remov_dt);
		map01.put("统一法人编号", unif_corp_id);
		map01.put("法人编号", lpr_id);
		classLabelMap.put("01", map01);
		
		/*
		 * 生产经营
		 */
		Map<String, String> map02 = new LinkedMap();
		map02.put("许可证号", license_no);
		map02.put("许可事项", license_item);
		map02.put("起始期", license_begin_dt);
		map02.put("终止期(有效期)", license_end_dt);
		map02.put("发证日期", license_fz_dt);   
		map02.put("许可证书状态", license_certi_stat);
		map02.put("发证机关(机构)", license_org);
		map02.put("主营业务成本", product_main_bus_cost);
		map02.put("营业税金及附加", product_tax_addition);
		map02.put("营业费用", product_busi_cost);   
		map02.put("管理费用", product_manage_cost); 
		map02.put("财务费用", product_finance_cost);
		map02.put("实际利润额", product_actual_profits);
		map02.put("经纳税调整后的生产经营所得", product_after_adjust_income);
		map02.put("投资金额", product_invest_amount);
		map02.put("筹资费用", product_raise_cost);   
		map02.put("利润总额", product_profits);     
		map02.put("所得税费用", product_income_tax);
		map02.put("一年内到期的非流动负债", product_oyear_no_flow_lia);
		map02.put("其他流动负债", product_other_flow_lia);
		map02.put("流动负债合计", product_flow_lia_total);
		map02.put("经营规模", busi_size_desc);
		map02.put("经营类型", busi_type_desc);
		map02.put("营业收入", busi_income);      
		map02.put("资质许可-工程建设", build_qficat_lice); 
		map02.put("资质许可-房屋管理", house_qficat_lice); 
		map02.put("资质许可-工程设计", design_qficat_lice); 
		map02.put("资质许可-城市规划", city_qficat_lice);   
		map02.put("资质许可-旅游服务", tour_qficat_lice); 
		map02.put("资质许可-生态环境", environ_qficat_lice);
		map02.put("资质许可-未知", unknown_qficat_lice);
		map02.put("企业变更", busi_change_desc);
		map02.put("员工数量", staff_cnt);
		map02.put("是否上市公司", is_listed_company);
		map02.put("是否危险品经营企业", is_dangerous_busi);
		map02.put("是否排污企业", is_sewage_busi);
		map02.put("主营业务收入", product_main_bus_income);
		
		classLabelMap.put("02", map02);
		
		/*
		 * 资产状况
		 */
		Map<String, String> map03 = new LinkedMap();
		map03.put("固定资产", product_fixed_assets);//	
		map03.put("递延所得", product_deferred_income);	
		map03.put("流动资产", product_flow_asset);	
		map03.put("无形资产", product_int_asset);	
		map03.put("不动产-房产", house_estate);	
		map03.put("不动产-地产", land_estate);	
		map03.put("不动产-矿产", mine_estate);	
		map03.put("不动产-设备", equipment_estate);	
		map03.put("不动产-其它", other_estate);	
		map03.put("金融资产-持有至到期投资", hold_matu_asset);
		map03.put("金融资产-可供出售金融资产", avai_sale_asset);	
		map03.put("金融资产-贷款", loan_asset);	
		map03.put("金融资产-应收款项", rece_asset);	
		map03.put("金融资产-其它", other_asset);	
		map03.put("机器设备-生产工艺类设备", prod_process_mac);	
		map03.put("机器设备-辅助生产设备", aux_prod_mach);
		map03.put("机器设备-服务设备", service_mach);	
		map03.put("机器设备-其它", other_mach);	
		map03.put("交通工具-汽车", car_vehicle);	
		map03.put("交通工具-飞机", airplane_vehicle);	
		map03.put("交通工具-船", ship_vehicle);	
		map03.put("交通工具-游轮", cruise_vehicle);	
		map03.put("交通工具-游艇", yacht_vehicle);	
		map03.put("交通工具-其它", other_vehicle);	
		map03.put("无形资产-货币资金", funds_asset_int);	
		map03.put("无形资产-应收帐款", account_recei_asset_int);	
		map03.put("无形资产-金融资产", fina_asset_int);	
		
		map03.put("无形资产-知识产权", knowledge_asset_int);
		map03.put("无形资产-其它", other_asset_int);
		map03.put("负债-流动负债", flow_liabi);
		map03.put("负债-长期负债", long_liabi);
		map03.put("负债-其它", other_liabi);
		map03.put("荣誉-著名驰名商标企业", fams_brand_honor);
		map03.put("荣誉-技术创新企业", tech_inno_honor);
		map03.put("荣誉-首重企业", importance_honor);
		map03.put("荣誉-其它", other_honor);
		map03.put("督查检查-正常", normal_inspec);
		map03.put("督查检查-质量检查异常", qua_abnormal_inspec);
		map03.put("督查检查-食品药品检查异常", food_abnormal_inspec);
		map03.put("督查检查-环境检查异常", environ_abnormal_inspec);
		map03.put("督查检查-价格检查异常", price_abnormal_inspec);
		map03.put("督查检查-安全生产检查异常", safe_prod_abnormal_inspec);
		map03.put("督查检查-其它检查异常", other_abnormal_inspec);
		map03.put("行政处罚-警告", notice_punish);
		map03.put("行政处罚-罚款", fine_punish);
		map03.put("行政处罚-没收违法所得", confi_income_punish);
		map03.put("行政处罚-责令停产停业", stop_busi_punish);
		map03.put("行政处罚-暂扣或者吊销许可证", withhold_revoca_punish);
		map03.put("行政处罚-行政拘留", detention_punish);
		map03.put("行政处罚-法律、行政法规规定的其它行政处罚", other_punish);
		map03.put("行政处罚-未知", unkown_punish);
		map03.put("行政处罚次数", punish_cnt);
		map03.put("司法涉案标识-失信被执行人", discredit_judge);
		map03.put("司法涉案标识-未履行生效裁判", unful_judge);
		map03.put("司法涉案标识-不涉及", no_judge);
		map03.put("是否跨区域涉税", tax_cross_region);
		
		classLabelMap.put("03", map03);
		
		/*
		 * 纳税参保
		 */
		Map<String, String> map04 = new LinkedMap();
		map04.put("是否异常企业名录", abnormal_busi_list);
		map04.put("是否税务非正常户", tax_unusual);
		map04.put("是否黑名单企业", is_black_flg);
		
		map04.put("纳税信用级别", tax_credit_level_desc);
		map04.put("税务登记种类", tax_rgst_type_desc);
		
		map04.put("纳税人状态代码", taxer_stat);	
		map04.put("税务登记机关名称", tax_rgst_org);	
		map04.put("税务登记日期", tax_rgst_dt);	
		map04.put("非正常户认定日期", unus_corp_start_dt);	
		map04.put("解除非正常户日期", unus_corp_stop_dt);	
		map04.put("登记注册类型代码", tax_rgst_typ_cd);	
		map04.put("注销原因代码", tax_rgst_typ_desc);	
		map04.put("注销税务机关(批准机关名称)", tax_revok_reson);	
		map04.put("税控码验证日期", tax_valid_dt);	
		map04.put("所属税务机关代码", tax_belg_dept_cd);	
		map04.put("参保单位名称", insure_corp_nm);	
		map04.put("单位参保日期", insure_dt);	
		map04.put("单位缴存人数", insure_person_cnt);	
		map04.put("单位账户状态", insure_account_stat);	
		map04.put("单位缴存比例", insure_deposite);	
		map04.put("纳税金额", tax_amount);	
		map04.put("纳税状态", tax_state_desc);	
		
		map04.put("参保缴费状态", insured_payment_state_nm);	
		map04.put("是否缴存公积金", is_fund_flg);	

		map04.put("单位住房公积金缴存人数", fund_persons);	
		map04.put("住房公积金单位账户状态", fund_company_stat);	
		map04.put("住房公积金单位缴存比例", fund_company_proportion);	
		classLabelMap.put("04", map04);
		
		/*
		 * 其它信息
		 */
		Map<String, String> map05 = new LinkedMap();
		map05.put("变更原因", remov_accept_resn);
		map05.put("受理日期", remov_accept_dt);	
		map05.put("项目(变更内容)", remov_content);	
		map05.put("注销原因", remov_reas);	
		map05.put("经度", location_lon);	
		map05.put("纬度", location_lat);	
		map05.put("经纬度合并", lat_lon_rpt);	
		classLabelMap.put("05", map05);
	}
	

	public String getLpr_id() {
		return lpr_id;
	}


	public void setLpr_id(String lpr_id) {
		this.lpr_id = lpr_id;
	}


	public String getCorp_rgst_num() {
		return corp_rgst_num;
	}


	public void setCorp_rgst_num(String corp_rgst_num) {
		this.corp_rgst_num = corp_rgst_num;
	}


	public String getStaff_cnt() {
		return staff_cnt;
	}


	public void setStaff_cnt(String staff_cnt) {
		this.staff_cnt = staff_cnt;
	}


	public String getIs_listed_company() {
		return is_listed_company;
	}


	public void setIs_listed_company(String is_listed_company) {
		this.is_listed_company = is_listed_company;
	}


	public String getIs_dangerous_busi() {
		return is_dangerous_busi;
	}


	public void setIs_dangerous_busi(String is_dangerous_busi) {
		this.is_dangerous_busi = is_dangerous_busi;
	}


	public String getIs_sewage_busi() {
		return is_sewage_busi;
	}


	public void setIs_sewage_busi(String is_sewage_busi) {
		this.is_sewage_busi = is_sewage_busi;
	}


	public String getProduct_main_bus_income() {
		return product_main_bus_income;
	}


	public void setProduct_main_bus_income(String product_main_bus_income) {
		this.product_main_bus_income = product_main_bus_income;
	}


	public String getLong_equity_asset_int() {
		return long_equity_asset_int;
	}


	public void setLong_equity_asset_int(String long_equity_asset_int) {
		this.long_equity_asset_int = long_equity_asset_int;
	}


	public String getTax_cross_region() {
		return tax_cross_region;
	}


	public void setTax_cross_region(String tax_cross_region) {
		this.tax_cross_region = tax_cross_region;
	}


	public String getLpr_nm() {
		return lpr_nm;
	}

	public void setLpr_nm(String lpr_nm) {
		this.lpr_nm = lpr_nm;
	}

	public String getUscc() {
		return uscc;
	}

	public void setUscc(String uscc) {
		this.uscc = uscc;
	}

	public String getCorp_nm() {
		return corp_nm;
	}

	public void setCorp_nm(String corp_nm) {
		this.corp_nm = corp_nm;
	}

	public String getCorp_typ_cd() {
		return corp_typ_cd;
	}

	public void setCorp_typ_cd(String corp_typ_cd) {
		this.corp_typ_cd = corp_typ_cd;
	}

	public String getBusi_scop() {
		return busi_scop;
	}

	public void setBusi_scop(String busi_scop) {
		this.busi_scop = busi_scop;
	}

	public String getLeg_pers_popu_id() {
		return leg_pers_popu_id;
	}

	public void setLeg_pers_popu_id(String leg_pers_popu_id) {
		this.leg_pers_popu_id = leg_pers_popu_id;
	}

	public String getRgst_addr() {
		return rgst_addr;
	}

	public void setRgst_addr(String rgst_addr) {
		this.rgst_addr = rgst_addr;
	}

	public String getEstb_dt() {
		return estb_dt;
	}

	public void setEstb_dt(String estb_dt) {
		this.estb_dt = estb_dt;
	}

	public String getRgst_org_nm() {
		return rgst_org_nm;
	}

	public void setRgst_org_nm(String rgst_org_nm) {
		this.rgst_org_nm = rgst_org_nm;
	}

	public String getRemov_dt() {
		return remov_dt;
	}

	public void setRemov_dt(String remov_dt) {
		this.remov_dt = remov_dt;
	}

	public String getLpr_cert_num() {
		return lpr_cert_num;
	}

	public void setLpr_cert_num(String lpr_cert_num) {
		this.lpr_cert_num = lpr_cert_num;
	}

	public String getCont_tel() {
		return cont_tel;
	}

	public void setCont_tel(String cont_tel) {
		this.cont_tel = cont_tel;
	}
	public String getLpr_type_desc() {
		return lpr_type_desc;
	}

	public void setLpr_type_desc(String lpr_type_desc) {
		this.lpr_type_desc = lpr_type_desc;
	}

	public String getRgst_capt() {
		return rgst_capt;
	}

	public void setRgst_capt(String rgst_capt) {
		this.rgst_capt = rgst_capt;
	}

	public String getShareholder_cnt() {
		return shareholder_cnt;
	}

	public void setShareholder_cnt(String shareholder_cnt) {
		this.shareholder_cnt = shareholder_cnt;
	}

	public String getLicense_no() {
		return license_no;
	}

	public void setLicense_no(String license_no) {
		this.license_no = license_no;
	}

	public String getLicense_item() {
		return license_item;
	}

	public void setLicense_item(String license_item) {
		this.license_item = license_item;
	}

	public String getLicense_begin_dt() {
		return license_begin_dt;
	}

	public void setLicense_begin_dt(String license_begin_dt) {
		this.license_begin_dt = license_begin_dt;
	}

	public String getLicense_end_dt() {
		return license_end_dt;
	}

	public void setLicense_end_dt(String license_end_dt) {
		this.license_end_dt = license_end_dt;
	}

	public String getLicense_fz_dt() {
		return license_fz_dt;
	}

	public void setLicense_fz_dt(String license_fz_dt) {
		this.license_fz_dt = license_fz_dt;
	}

	public String getLicense_certi_stat() {
		return license_certi_stat;
	}

	public void setLicense_certi_stat(String license_certi_stat) {
		this.license_certi_stat = license_certi_stat;
	}

	public String getLicense_org() {
		return license_org;
	}

	public void setLicense_org(String license_org) {
		this.license_org = license_org;
	}

	public String getProduct_main_bus_cost() {
		return product_main_bus_cost;
	}

	public void setProduct_main_bus_cost(String product_main_bus_cost) {
		this.product_main_bus_cost = product_main_bus_cost;
	}

	public String getProduct_tax_addition() {
		return product_tax_addition;
	}

	public void setProduct_tax_addition(String product_tax_addition) {
		this.product_tax_addition = product_tax_addition;
	}

	public String getProduct_busi_cost() {
		return product_busi_cost;
	}

	public void setProduct_busi_cost(String product_busi_cost) {
		this.product_busi_cost = product_busi_cost;
	}

	public String getProduct_manage_cost() {
		return product_manage_cost;
	}

	public void setProduct_manage_cost(String product_manage_cost) {
		this.product_manage_cost = product_manage_cost;
	}

	public String getProduct_finance_cost() {
		return product_finance_cost;
	}

	public void setProduct_finance_cost(String product_finance_cost) {
		this.product_finance_cost = product_finance_cost;
	}

	public String getProduct_actual_profits() {
		return product_actual_profits;
	}

	public void setProduct_actual_profits(String product_actual_profits) {
		this.product_actual_profits = product_actual_profits;
	}

	public String getProduct_after_adjust_income() {
		return product_after_adjust_income;
	}

	public void setProduct_after_adjust_income(String product_after_adjust_income) {
		this.product_after_adjust_income = product_after_adjust_income;
	}

	public String getProduct_invest_amount() {
		return product_invest_amount;
	}

	public void setProduct_invest_amount(String product_invest_amount) {
		this.product_invest_amount = product_invest_amount;
	}

	public String getProduct_raise_cost() {
		return product_raise_cost;
	}

	public void setProduct_raise_cost(String product_raise_cost) {
		this.product_raise_cost = product_raise_cost;
	}

	public String getProduct_profits() {
		return product_profits;
	}

	public void setProduct_profits(String product_profits) {
		this.product_profits = product_profits;
	}

	public String getProduct_income_tax() {
		return product_income_tax;
	}

	public void setProduct_income_tax(String product_income_tax) {
		this.product_income_tax = product_income_tax;
	}

	public String getProduct_oyear_no_flow_lia() {
		return product_oyear_no_flow_lia;
	}

	public void setProduct_oyear_no_flow_lia(String product_oyear_no_flow_lia) {
		this.product_oyear_no_flow_lia = product_oyear_no_flow_lia;
	}

	public String getProduct_other_flow_lia() {
		return product_other_flow_lia;
	}

	public void setProduct_other_flow_lia(String product_other_flow_lia) {
		this.product_other_flow_lia = product_other_flow_lia;
	}

	public String getProduct_flow_lia_total() {
		return product_flow_lia_total;
	}

	public void setProduct_flow_lia_total(String product_flow_lia_total) {
		this.product_flow_lia_total = product_flow_lia_total;
	}

	public String getBusi_income() {
		return busi_income;
	}

	public void setBusi_income(String busi_income) {
		this.busi_income = busi_income;
	}

	public String getBuild_qficat_lice() {
		return build_qficat_lice;
	}

	public void setBuild_qficat_lice(String build_qficat_lice) {
		this.build_qficat_lice = build_qficat_lice;
	}

	public String getHouse_qficat_lice() {
		return house_qficat_lice;
	}

	public void setHouse_qficat_lice(String house_qficat_lice) {
		this.house_qficat_lice = house_qficat_lice;
	}

	public String getDesign_qficat_lice() {
		return design_qficat_lice;
	}

	public void setDesign_qficat_lice(String design_qficat_lice) {
		this.design_qficat_lice = design_qficat_lice;
	}

	public String getCity_qficat_lice() {
		return city_qficat_lice;
	}

	public void setCity_qficat_lice(String city_qficat_lice) {
		this.city_qficat_lice = city_qficat_lice;
	}

	public String getTour_qficat_lice() {
		return tour_qficat_lice;
	}

	public void setTour_qficat_lice(String tour_qficat_lice) {
		this.tour_qficat_lice = tour_qficat_lice;
	}

	public String getEnviron_qficat_lice() {
		return environ_qficat_lice;
	}

	public void setEnviron_qficat_lice(String environ_qficat_lice) {
		this.environ_qficat_lice = environ_qficat_lice;
	}

	public String getUnknown_qficat_lice() {
		return unknown_qficat_lice;
	}

	public void setUnknown_qficat_lice(String unknown_qficat_lice) {
		this.unknown_qficat_lice = unknown_qficat_lice;
	}

	public String getProduct_fixed_assets() {
		return product_fixed_assets;
	}

	public void setProduct_fixed_assets(String product_fixed_assets) {
		this.product_fixed_assets = product_fixed_assets;
	}

	public String getProduct_deferred_income() {
		return product_deferred_income;
	}

	public void setProduct_deferred_income(String product_deferred_income) {
		this.product_deferred_income = product_deferred_income;
	}

	public String getProduct_flow_asset() {
		return product_flow_asset;
	}

	public void setProduct_flow_asset(String product_flow_asset) {
		this.product_flow_asset = product_flow_asset;
	}

	public String getProduct_int_asset() {
		return product_int_asset;
	}

	public void setProduct_int_asset(String product_int_asset) {
		this.product_int_asset = product_int_asset;
	}

	public String getHouse_estate() {
		return house_estate;
	}

	public void setHouse_estate(String house_estate) {
		this.house_estate = house_estate;
	}

	public String getLand_estate() {
		return land_estate;
	}

	public void setLand_estate(String land_estate) {
		this.land_estate = land_estate;
	}

	public String getMine_estate() {
		return mine_estate;
	}

	public void setMine_estate(String mine_estate) {
		this.mine_estate = mine_estate;
	}

	public String getEquipment_estate() {
		return equipment_estate;
	}

	public void setEquipment_estate(String equipment_estate) {
		this.equipment_estate = equipment_estate;
	}

	public String getOther_estate() {
		return other_estate;
	}

	public void setOther_estate(String other_estate) {
		this.other_estate = other_estate;
	}

	public String getHold_matu_asset() {
		return hold_matu_asset;
	}

	public void setHold_matu_asset(String hold_matu_asset) {
		this.hold_matu_asset = hold_matu_asset;
	}

	public String getAvai_sale_asset() {
		return avai_sale_asset;
	}

	public void setAvai_sale_asset(String avai_sale_asset) {
		this.avai_sale_asset = avai_sale_asset;
	}

	public String getLoan_asset() {
		return loan_asset;
	}

	public void setLoan_asset(String loan_asset) {
		this.loan_asset = loan_asset;
	}

	public String getRece_asset() {
		return rece_asset;
	}

	public void setRece_asset(String rece_asset) {
		this.rece_asset = rece_asset;
	}

	public String getOther_asset() {
		return other_asset;
	}

	public void setOther_asset(String other_asset) {
		this.other_asset = other_asset;
	}

	public String getProd_process_mac() {
		return prod_process_mac;
	}

	public void setProd_process_mac(String prod_process_mac) {
		this.prod_process_mac = prod_process_mac;
	}

	public String getAux_prod_mach() {
		return aux_prod_mach;
	}

	public void setAux_prod_mach(String aux_prod_mach) {
		this.aux_prod_mach = aux_prod_mach;
	}

	public String getService_mach() {
		return service_mach;
	}

	public void setService_mach(String service_mach) {
		this.service_mach = service_mach;
	}

	public String getOther_mach() {
		return other_mach;
	}

	public void setOther_mach(String other_mach) {
		this.other_mach = other_mach;
	}

	public String getCar_vehicle() {
		return car_vehicle;
	}

	public void setCar_vehicle(String car_vehicle) {
		this.car_vehicle = car_vehicle;
	}

	public String getAirplane_vehicle() {
		return airplane_vehicle;
	}

	public void setAirplane_vehicle(String airplane_vehicle) {
		this.airplane_vehicle = airplane_vehicle;
	}

	public String getShip_vehicle() {
		return ship_vehicle;
	}

	public void setShip_vehicle(String ship_vehicle) {
		this.ship_vehicle = ship_vehicle;
	}

	public String getCruise_vehicle() {
		return cruise_vehicle;
	}

	public void setCruise_vehicle(String cruise_vehicle) {
		this.cruise_vehicle = cruise_vehicle;
	}

	public String getYacht_vehicle() {
		return yacht_vehicle;
	}

	public void setYacht_vehicle(String yacht_vehicle) {
		this.yacht_vehicle = yacht_vehicle;
	}

	public String getOther_vehicle() {
		return other_vehicle;
	}

	public void setOther_vehicle(String other_vehicle) {
		this.other_vehicle = other_vehicle;
	}

	public String getFunds_asset_int() {
		return funds_asset_int;
	}

	public void setFunds_asset_int(String funds_asset_int) {
		this.funds_asset_int = funds_asset_int;
	}

	public String getAccount_recei_asset_int() {
		return account_recei_asset_int;
	}

	public void setAccount_recei_asset_int(String account_recei_asset_int) {
		this.account_recei_asset_int = account_recei_asset_int;
	}

	public String getFina_asset_int() {
		return fina_asset_int;
	}

	public void setFina_asset_int(String fina_asset_int) {
		this.fina_asset_int = fina_asset_int;
	}

	public String getTaxer_stat() {
		return taxer_stat;
	}

	public void setTaxer_stat(String taxer_stat) {
		this.taxer_stat = taxer_stat;
	}

	public String getTax_rgst_org() {
		return tax_rgst_org;
	}

	public void setTax_rgst_org(String tax_rgst_org) {
		this.tax_rgst_org = tax_rgst_org;
	}

	public String getTax_rgst_dt() {
		return tax_rgst_dt;
	}

	public void setTax_rgst_dt(String tax_rgst_dt) {
		this.tax_rgst_dt = tax_rgst_dt;
	}

	public String getUnus_corp_start_dt() {
		return unus_corp_start_dt;
	}

	public void setUnus_corp_start_dt(String unus_corp_start_dt) {
		this.unus_corp_start_dt = unus_corp_start_dt;
	}

	public String getUnus_corp_stop_dt() {
		return unus_corp_stop_dt;
	}

	public void setUnus_corp_stop_dt(String unus_corp_stop_dt) {
		this.unus_corp_stop_dt = unus_corp_stop_dt;
	}

	public String getTax_rgst_typ_cd() {
		return tax_rgst_typ_cd;
	}

	public void setTax_rgst_typ_cd(String tax_rgst_typ_cd) {
		this.tax_rgst_typ_cd = tax_rgst_typ_cd;
	}

	public String getTax_rgst_typ_desc() {
		return tax_rgst_typ_desc;
	}

	public void setTax_rgst_typ_desc(String tax_rgst_typ_desc) {
		this.tax_rgst_typ_desc = tax_rgst_typ_desc;
	}

	public String getTax_revok_reson() {
		return tax_revok_reson;
	}

	public void setTax_revok_reson(String tax_revok_reson) {
		this.tax_revok_reson = tax_revok_reson;
	}

	public String getTax_valid_dt() {
		return tax_valid_dt;
	}

	public void setTax_valid_dt(String tax_valid_dt) {
		this.tax_valid_dt = tax_valid_dt;
	}

	public String getTax_belg_dept_cd() {
		return tax_belg_dept_cd;
	}

	public void setTax_belg_dept_cd(String tax_belg_dept_cd) {
		this.tax_belg_dept_cd = tax_belg_dept_cd;
	}

	public String getInsure_corp_nm() {
		return insure_corp_nm;
	}

	public void setInsure_corp_nm(String insure_corp_nm) {
		this.insure_corp_nm = insure_corp_nm;
	}

	public String getInsure_dt() {
		return insure_dt;
	}

	public void setInsure_dt(String insure_dt) {
		this.insure_dt = insure_dt;
	}

	public String getInsure_person_cnt() {
		return insure_person_cnt;
	}

	public void setInsure_person_cnt(String insure_person_cnt) {
		this.insure_person_cnt = insure_person_cnt;
	}

	public String getInsure_account_stat() {
		return insure_account_stat;
	}

	public void setInsure_account_stat(String insure_account_stat) {
		this.insure_account_stat = insure_account_stat;
	}

	public String getInsure_deposite() {
		return insure_deposite;
	}

	public void setInsure_deposite(String insure_deposite) {
		this.insure_deposite = insure_deposite;
	}

	public String getTax_amount() {
		return tax_amount;
	}

	public void setTax_amount(String tax_amount) {
		this.tax_amount = tax_amount;
	}

	public String getFund_persons() {
		return fund_persons;
	}

	public void setFund_persons(String fund_persons) {
		this.fund_persons = fund_persons;
	}

	public String getFund_company_stat() {
		return fund_company_stat;
	}

	public void setFund_company_stat(String fund_company_stat) {
		this.fund_company_stat = fund_company_stat;
	}

	public String getFund_company_proportion() {
		return fund_company_proportion;
	}

	public void setFund_company_proportion(String fund_company_proportion) {
		this.fund_company_proportion = fund_company_proportion;
	}

	public String getRemov_accept_resn() {
		return remov_accept_resn;
	}

	public void setRemov_accept_resn(String remov_accept_resn) {
		this.remov_accept_resn = remov_accept_resn;
	}

	public String getRemov_accept_dt() {
		return remov_accept_dt;
	}

	public void setRemov_accept_dt(String remov_accept_dt) {
		this.remov_accept_dt = remov_accept_dt;
	}

	public String getRemov_content() {
		return remov_content;
	}

	public void setRemov_content(String remov_content) {
		this.remov_content = remov_content;
	}

	public String getRemov_reas() {
		return remov_reas;
	}

	public void setRemov_reas(String remov_reas) {
		this.remov_reas = remov_reas;
	}


	public Map<String, Map<String, String>> getClassLabelMap() {
		return classLabelMap;
	}

	public void setClassLabelMap(Map<String, Map<String, String>> classLabelMap) {
		this.classLabelMap = classLabelMap;
	}


	public String getBusi_opr_stat_desc() {
		return busi_opr_stat_desc;
	}


	public void setBusi_opr_stat_desc(String busi_opr_stat_desc) {
		this.busi_opr_stat_desc = busi_opr_stat_desc;
	}


	public String getRgst_county_desc() {
		return rgst_county_desc;
	}


	public void setRgst_county_desc(String rgst_county_desc) {
		this.rgst_county_desc = rgst_county_desc;
	}


	public String getIndustry_type_desc() {
		return industry_type_desc;
	}


	public void setIndustry_type_desc(String industry_type_desc) {
		this.industry_type_desc = industry_type_desc;
	}


	public String getIndustrial_attribute_nm() {
		return industrial_attribute_nm;
	}


	public void setIndustrial_attribute_nm(String industrial_attribute_nm) {
		this.industrial_attribute_nm = industrial_attribute_nm;
	}


	public String getBusi_size_desc() {
		return busi_size_desc;
	}


	public void setBusi_size_desc(String busi_size_desc) {
		this.busi_size_desc = busi_size_desc;
	}


	public String getBusi_type_desc() {
		return busi_type_desc;
	}


	public void setBusi_type_desc(String busi_type_desc) {
		this.busi_type_desc = busi_type_desc;
	}


	public String getBusi_change_desc() {
		return busi_change_desc;
	}


	public void setBusi_change_desc(String busi_change_desc) {
		this.busi_change_desc = busi_change_desc;
	}


	public String getKnowledge_asset_int() {
		return knowledge_asset_int;
	}


	public void setKnowledge_asset_int(String knowledge_asset_int) {
		this.knowledge_asset_int = knowledge_asset_int;
	}


	public String getOther_asset_int() {
		return other_asset_int;
	}


	public void setOther_asset_int(String other_asset_int) {
		this.other_asset_int = other_asset_int;
	}


	public String getFlow_liabi() {
		return flow_liabi;
	}


	public void setFlow_liabi(String flow_liabi) {
		this.flow_liabi = flow_liabi;
	}


	public String getLong_liabi() {
		return long_liabi;
	}


	public void setLong_liabi(String long_liabi) {
		this.long_liabi = long_liabi;
	}


	public String getOther_liabi() {
		return other_liabi;
	}


	public void setOther_liabi(String other_liabi) {
		this.other_liabi = other_liabi;
	}


	public String getFams_brand_honor() {
		return fams_brand_honor;
	}


	public void setFams_brand_honor(String fams_brand_honor) {
		this.fams_brand_honor = fams_brand_honor;
	}


	public String getTech_inno_honor() {
		return tech_inno_honor;
	}


	public void setTech_inno_honor(String tech_inno_honor) {
		this.tech_inno_honor = tech_inno_honor;
	}


	public String getImportance_honor() {
		return importance_honor;
	}


	public void setImportance_honor(String importance_honor) {
		this.importance_honor = importance_honor;
	}


	public String getOther_honor() {
		return other_honor;
	}


	public void setOther_honor(String other_honor) {
		this.other_honor = other_honor;
	}


	public String getNormal_inspec() {
		return normal_inspec;
	}


	public void setNormal_inspec(String normal_inspec) {
		this.normal_inspec = normal_inspec;
	}


	public String getQua_abnormal_inspec() {
		return qua_abnormal_inspec;
	}


	public void setQua_abnormal_inspec(String qua_abnormal_inspec) {
		this.qua_abnormal_inspec = qua_abnormal_inspec;
	}


	public String getFood_abnormal_inspec() {
		return food_abnormal_inspec;
	}


	public void setFood_abnormal_inspec(String food_abnormal_inspec) {
		this.food_abnormal_inspec = food_abnormal_inspec;
	}


	public String getEnviron_abnormal_inspec() {
		return environ_abnormal_inspec;
	}


	public void setEnviron_abnormal_inspec(String environ_abnormal_inspec) {
		this.environ_abnormal_inspec = environ_abnormal_inspec;
	}


	public String getPrice_abnormal_inspec() {
		return price_abnormal_inspec;
	}


	public void setPrice_abnormal_inspec(String price_abnormal_inspec) {
		this.price_abnormal_inspec = price_abnormal_inspec;
	}


	public String getSafe_prod_abnormal_inspec() {
		return safe_prod_abnormal_inspec;
	}


	public void setSafe_prod_abnormal_inspec(String safe_prod_abnormal_inspec) {
		this.safe_prod_abnormal_inspec = safe_prod_abnormal_inspec;
	}


	public String getOther_abnormal_inspec() {
		return other_abnormal_inspec;
	}


	public void setOther_abnormal_inspec(String other_abnormal_inspec) {
		this.other_abnormal_inspec = other_abnormal_inspec;
	}


	public String getNotice_punish() {
		return notice_punish;
	}


	public void setNotice_punish(String notice_punish) {
		this.notice_punish = notice_punish;
	}


	public String getFine_punish() {
		return fine_punish;
	}


	public void setFine_punish(String fine_punish) {
		this.fine_punish = fine_punish;
	}


	public String getConfi_income_punish() {
		return confi_income_punish;
	}


	public void setConfi_income_punish(String confi_income_punish) {
		this.confi_income_punish = confi_income_punish;
	}


	public String getStop_busi_punish() {
		return stop_busi_punish;
	}


	public void setStop_busi_punish(String stop_busi_punish) {
		this.stop_busi_punish = stop_busi_punish;
	}


	public String getWithhold_revoca_punish() {
		return withhold_revoca_punish;
	}


	public void setWithhold_revoca_punish(String withhold_revoca_punish) {
		this.withhold_revoca_punish = withhold_revoca_punish;
	}


	public String getDetention_punish() {
		return detention_punish;
	}


	public void setDetention_punish(String detention_punish) {
		this.detention_punish = detention_punish;
	}


	public String getOther_punish() {
		return other_punish;
	}


	public void setOther_punish(String other_punish) {
		this.other_punish = other_punish;
	}


	public String getUnkown_punish() {
		return unkown_punish;
	}


	public void setUnkown_punish(String unkown_punish) {
		this.unkown_punish = unkown_punish;
	}


	public String getPunish_cnt() {
		return punish_cnt;
	}


	public void setPunish_cnt(String punish_cnt) {
		this.punish_cnt = punish_cnt;
	}


	public String getDiscredit_judge() {
		return discredit_judge;
	}


	public void setDiscredit_judge(String discredit_judge) {
		this.discredit_judge = discredit_judge;
	}


	public String getUnful_judge() {
		return unful_judge;
	}


	public void setUnful_judge(String unful_judge) {
		this.unful_judge = unful_judge;
	}


	public String getNo_judge() {
		return no_judge;
	}


	public void setNo_judge(String no_judge) {
		this.no_judge = no_judge;
	}


	public String getAbnormal_busi_list() {
		return abnormal_busi_list;
	}


	public void setAbnormal_busi_list(String abnormal_busi_list) {
		this.abnormal_busi_list = abnormal_busi_list;
	}


	public String getTax_unusual() {
		return tax_unusual;
	}


	public void setTax_unusual(String tax_unusual) {
		this.tax_unusual = tax_unusual;
	}


	public String getIs_black_flg() {
		return is_black_flg;
	}


	public void setIs_black_flg(String is_black_flg) {
		this.is_black_flg = is_black_flg;
	}


	public String getTax_credit_level_desc() {
		return tax_credit_level_desc;
	}


	public void setTax_credit_level_desc(String tax_credit_level_desc) {
		this.tax_credit_level_desc = tax_credit_level_desc;
	}


	public String getTax_rgst_type_desc() {
		return tax_rgst_type_desc;
	}


	public void setTax_rgst_type_desc(String tax_rgst_type_desc) {
		this.tax_rgst_type_desc = tax_rgst_type_desc;
	}


	public String getTax_state_desc() {
		return tax_state_desc;
	}


	public void setTax_state_desc(String tax_state_desc) {
		this.tax_state_desc = tax_state_desc;
	}


	public String getInsured_payment_state_nm() {
		return insured_payment_state_nm;
	}


	public void setInsured_payment_state_nm(String insured_payment_state_nm) {
		this.insured_payment_state_nm = insured_payment_state_nm;
	}


	public String getIs_fund_flg() {
		return is_fund_flg;
	}

	public void setIs_fund_flg(String is_fund_flg) {
		this.is_fund_flg = is_fund_flg;
	}


	public String getUnif_corp_id() {
		return unif_corp_id;
	}


	public void setUnif_corp_id(String unif_corp_id) {
		this.unif_corp_id = unif_corp_id;
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


	public String getLat_lon_rpt() {
		return lat_lon_rpt;
	}


	public void setLat_lon_rpt(String lat_lon_rpt) {
		this.lat_lon_rpt = lat_lon_rpt;
	}


}
