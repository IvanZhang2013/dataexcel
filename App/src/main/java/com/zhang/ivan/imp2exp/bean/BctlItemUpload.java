/*
package com.zhang.ivan.imp2exp.bean;


import com.huateng.ebank.business.common.GlobalInfo;
import com.huateng.ebank.business.common.service.CommonService;
import com.huateng.ebank.business.management.IDService;
import com.huateng.ebank.entity.dao.mng.BctlDAO;
import com.huateng.ebank.entity.dao.mng.ROOTDAO;
import com.huateng.ebank.entity.dao.mng.ROOTDAOUtils;
import com.huateng.ebank.entity.data.mng.Bctl;
import com.huateng.ebank.framework.exceptions.CommonException;
import com.huateng.ebank.framework.util.DateUtil;
import com.huateng.uniform.excel.batchimport.BaseUpload;
import com.huateng.uniform.excel.batchimport.BatchImportInfoVO;
import com.huateng.uniform.excel.batchimport.TableFieldInfoVO;
import com.huateng.uniform.utills.DataMyUtil;
import com.nantian.ofpiwap.secure.Account;
import com.nantian.ofpiwap.secure.SecurityContext;
import com.nantian.ofpiwap.srv.FormatedJDBCTableDao;

*//**
 * author: kin wong
 *
 * class desc:可信域名批量上传类
 *//*
public class BctlItemUpload extends BaseUpload  {
	public BctlItemUpload() throws Exception{
		GlobalInfo globalInfo = GlobalInfo.getCurrentInstance();
		设置总体信息
		batchVO = new BatchImportInfoVO();
		TableFieldInfoVO[] fields = new TableFieldInfoVO[19];
		batchVO.setFieldInfo(fields);
		batchVO.setTableName("SYS_BCTL");
		batchVO.setOperDesc("批量导入机构组织");
		batchVO.setOtherFieldLength(8);

		fields[0] = new TableFieldInfoVO();
		fields[0].setFieldsName("BRNO");
		fields[0].setFieldsDesc("机构代码");
		fields[0].setRegex(".{1,20}");
		fields[0].setLimitDesc("机构代码”要求长度小于20个字符");
		fields[0].setUnique(true);
		
		fields[1] = new TableFieldInfoVO();
		fields[1].setFieldsName("BRNAME");
		fields[1].setFieldsDesc("机构名称");
		fields[1].setRegex(".{1,60}");
		fields[1].setLimitDesc("机构名称”要求长度小于60个字符");
		
		
		fields[2] = new TableFieldInfoVO();
		fields[2].setFieldsName("ADDRESS");
		fields[2].setFieldsDesc("机构地址");
		fields[2].setRegex(".{1,60}");
		fields[2].setLimitDesc("机构地址”要求长度小于60个字符");
		
		
		fields[3] = new TableFieldInfoVO();
		fields[3].setFieldsName("POSTNO");
		fields[3].setFieldsDesc("邮政编码");
		fields[3].setRegex("[0-9]{6}");
		fields[3].setLimitDesc("邮政编码”要求长度6个数字");
		
		fields[4] = new TableFieldInfoVO();
		fields[4].setFieldsName("TELENO");
		fields[4].setFieldsDesc("联系电话");
		fields[4].setRegex(".{1,20}");
		fields[4].setLimitDesc("联系电话”要求长度少于20字符");
		
		fields[5] = new TableFieldInfoVO();
		fields[5].setFieldsName("BRCLASS");
		fields[5].setFieldsDesc("机构级别");
		fields[5].setRegex("[2-7]{1}");
		fields[5].setLimitDesc("机构级别”要求填写正确的类型编码");
		
		fields[6] = new TableFieldInfoVO();
		fields[6].setFieldsName("BLN_UP_BRCODE");
		fields[6].setFieldsDesc("上级机构");
		fields[6].setRegex("[0-9]{6}");
		fields[6].setLimitDesc("”上级机构”要求长度为6个字符");
		fields[6].setTableValue(true);
		fields[6].setIsTableSql("select count(*) from  SYS_BCTL  where LP_BRCODE='"+globalInfo.getLpBrcode()+"' and BRCODE=?");
		
		
		fields[7] = new TableFieldInfoVO();
		fields[7].setFieldsName("BRANCH_BRCODE");
		fields[7].setFieldsDesc("所属分行");
		fields[7].setRegex("[0-9]{0,6}");
		fields[7].setLimitDesc("“所属分行”要求长度为6个字符");
		fields[7].setTableValue(true);
		fields[7].setIsTableSql("select count(*) from  SYS_BCTL  where LP_BRCODE='"+globalInfo.getLpBrcode()+"'  and BRCLASS='3' and BRCODE=?");
		
		fields[8] = new TableFieldInfoVO();
		fields[8].setFieldsName("BRATTR");
		fields[8].setFieldsDesc("机构属性");
		fields[8].setRegex("[0-1]{0,1}");
		fields[8].setLimitDesc("机构属性”要求填写正确的类型编码");
		
		
		fields[9] = new TableFieldInfoVO();
		fields[9].setFieldsName("CREDIT_BRCODE");
		fields[9].setFieldsDesc("所属信用卡中心");
		fields[9].setRegex("[0-9]{0,6}");
		fields[9].setLimitDesc("”所属信用卡中心”要求长度为6个字符");
		fields[9].setTableValue(true);
		fields[9].setIsTableSql("select count(*) from  SYS_BCTL  where LP_BRCODE='"+globalInfo.getLpBrcode()+"' and BRCLASS='5'  and BRCODE=?");
		
		fields[10] = new TableFieldInfoVO();
		fields[10].setFieldsName("ADMIN_FLAG");
		fields[10].setFieldsDesc("是否行政机构");
		fields[10].setRegex("[0-1]{1}");
		fields[10].setLimitDesc("“是否行政机构”要求填写正确的类型编码");
		
		
		设置非文件字段信息
		*//**
		 * 机构主键
		 * *//*
		fields[11] = new TableFieldInfoVO();
		fields[11].setFieldsName("BRCODE");
		fields[11].setFieldValue("");
		
		*//**
		 * 法人机构
		 * *//*
		fields[12] = new TableFieldInfoVO();
		fields[12].setFieldsName("LP_BRCODE");
		fields[12].setFieldValue(globalInfo.getLpBrcode());
		*//**
		 * 锁   状态   删除
		 * *//*
		
		fields[13] = new TableFieldInfoVO();
		fields[13].setFieldsName("ST");
		fields[13].setFieldValue("4");
		
		fields[14] = new TableFieldInfoVO();
		fields[14].setFieldsName("IS_LOCK");
		fields[14].setFieldValue("F");
		
		fields[15] = new TableFieldInfoVO();
		fields[15].setFieldsName("IS_DEL");
		fields[15].setFieldValue("F");
		
		
		
		*//**
		 * 操作人   操作时间
		 * *//*
		fields[16] = new TableFieldInfoVO();
		fields[16].setFieldsName("LAST_UPD_TLR");
		fields[16].setFieldValue(globalInfo.getTlrno());
		
		fields[17] = new TableFieldInfoVO();
		fields[17].setFieldsName("LAST_UPD_DATE");
		fields[17].setFieldValue(DataMyUtil.getDate());
		
		fields[18] = new TableFieldInfoVO();
		fields[18].setFieldsName("STATUS");
		fields[18].setFieldValue("1");
		
	}
}
*/