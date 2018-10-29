package com.mvc.dao;

import java.util.List;

import com.mvc.entityReport.AlarmLog;
import com.mvc.entityReport.EquipOper;
import com.mvc.entityReport.EquipPara;
import com.mvc.entityReport.Equipment;
import com.mvc.entityReport.Project;

public interface EquipRealInfoDao {

	// 查询设备信息总条数
	Integer countEqTotal(String searchKey);

	// 根据页数筛选全部设备信息列表
	List<Equipment> selectEquipmentByPage(String searchKey, Integer offset, Integer end);

	//根据设备id查找设备特征参数
	List<EquipPara> getEquipPara(String searchKey);

	
	//根据设备参数名字查找设备特征参数信息
	List<EquipPara> getEquipParaByName(String searchKey);

	//根据设备参数id查询设备参数实时数据
	List<EquipOper> getEquipRealData(String searchKey, String startDate);

	//获取设备报警信息
	List<AlarmLog> getWaringNews(String searchKey);
	
	//根据起始时间 向后查100条
	List<EquipOper> getEquipRealDataByTime(String equip_para_id, String startDate);

	//根据项目查询所属设备信息
	List<Equipment> getEquipmentListByProject(String searchKey);
	
	// 根据公司id获取所属项目信息
	List<Project> selectProjectByCompId(String searchKey);

	List<AlarmLog> getWaringNewsWithOut(String searchKey);

	//根据项目名称和设备名称获取告警信息
	//List<AlarmLog> getEquipAlarmByProAndEquip(String proName, String equipName);


}
