package com.mvc.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.base.constants.SessionKeyConstants;
import com.mvc.entityReport.EquipManu;
import com.mvc.entityReport.EquipRoom;
import com.mvc.entityReport.EquipType;
import com.mvc.entityReport.Equipment;
import com.mvc.entityReport.User;
import com.mvc.entityReport.Project;
import com.mvc.entityReport.EquipPara;
import com.mvc.entityReport.EquipMain;

import com.utils.Pager;
import com.mvc.service.EquipmentService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/equipEquipment")
public class EquipmentController {
	
	@Autowired
	EquipmentService equipmentService;

	//删除设备信息
	@RequestMapping(value = "/deleteEquipmentInfo.do")
	public @ResponseBody String deleteEquipmentInfo(HttpServletRequest request, HttpSession session) {
		Integer equipmentid = Integer.valueOf(request.getParameter("equipmentId"));	
		boolean result = equipmentService.deleteIsdelete(equipmentid);
		return JSON.toJSONString(result);
	}
	
	/*//根据限制条件type、state筛选设备信息
		@RequestMapping("/getEquipmentListByTS.do")
		public @ResponseBody String getEquipmentByPrarm(HttpServletRequest request, HttpSession session) {
			String eqType = null;
			String eqState = null;
			JSONObject jsonObject = new JSONObject();
			if(request.getParameter("eqType") != null){
				eqType = JSONObject.fromObject(request.getParameter("eqType")).getString("equip_type");
			}
			if(request.getParameter("eqState") != null){
				eqState = JSONObject.fromObject(request.getParameter("eqState")).getString("equip_state");
			}
			Integer totalRow = equipmentService.countTotal(eqType,eqState);
			Pager pager = new Pager();
			pager.setPage(Integer.valueOf(request.getParameter("page")));
			if(totalRow != 0){
				pager.setTotalRow(Integer.parseInt(totalRow.toString()));
			}
			List<Equipment> list = equipmentService.findEquipmentByPage(eqType,eqState,pager.getOffset(), pager.getLimit());
			jsonObject.put("totalPage", pager.getTotalPage());
			jsonObject.put("list", list);
			return jsonObject.toString();
		}*/
		
		//根据页数显示设备信息列表
		@RequestMapping(value = "/getEquipmentListByPage.do")
		public @ResponseBody String getEquipmentsByPrarm(HttpServletRequest request, HttpSession session) {
			JSONObject jsonObject = new JSONObject();
			String searchKey = request.getParameter("searchKey");
			Integer totalRow = equipmentService.countEqTotal(searchKey);
			Pager pager = new Pager();
			pager.setPage(Integer.valueOf(request.getParameter("page")));
			pager.setTotalRow(Integer.parseInt(totalRow.toString()));
			List<Equipment> list = equipmentService.selectEquipmentByPage(searchKey, pager.getOffset(), pager.getLimit());
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i).getEquip_ndate());
			}
			jsonObject.put("list", list);
			jsonObject.put("totalPage", pager.getTotalPage());
			System.out.println("totalPage:" + pager.getTotalPage());
			return jsonObject.toString();
		}		
		//添加设备信息
		@RequestMapping(value = "/addEquipment.do")
		public @ResponseBody String addEquipment(HttpServletRequest request, HttpSession session) throws ParseException {
			JSONObject jsonObject = new JSONObject();
			jsonObject = JSONObject.fromObject(request.getParameter("equipment"));
			Equipment equipment = new Equipment();
			if (jsonObject.containsKey("equip_no")) {
			    equipment.setEquip_no(jsonObject.getString("equip_no"));
			}	
			if (jsonObject.containsKey("equip_name")) {
			    equipment.setEquip_name(jsonObject.getString("equip_name"));
			}
			if (jsonObject.containsKey("equip_num")) {
				equipment.setEquip_num(jsonObject.getString("equip_num"));
			}		
//			if (jsonObject.containsKey("equip_pic")) {
//				equipment.setEquip_pic(jsonObject.getString("equip_pic"));}
//			if (jsonObject.containsKey("equip_qrcode")) {
//				equipment.setEquip_qrcode(jsonObject.getString("equip_qrcode"));}
			if (jsonObject.containsKey("equip_type")) {
				EquipType et = new EquipType();
				et.setEquip_type_id(Integer.valueOf(jsonObject.getString("equip_type")));
				equipment.setEquip_type(et);	
			}
			if (jsonObject.containsKey("equip_manu")) {
				equipment.setEquip_manu(jsonObject.getString("equip_manu"));
				}
			if (jsonObject.containsKey("equip_pdate")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(jsonObject.getString("equip_pdate"));
				equipment.setEquip_pdate(date);
			}
			if (jsonObject.containsKey("equip_udate")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(jsonObject.getString("equip_udate"));
				equipment.setEquip_udate(date);
			}
			if (jsonObject.containsKey("equip_bfee")) {
				equipment.setEquip_bfee(Float.parseFloat(jsonObject.getString("equip_bfee")));
			}
			if (jsonObject.containsKey("equip_snum")) {
				equipment.setEquip_snum(Integer.parseInt(jsonObject.getString("equip_snum")));
				}
			if (jsonObject.containsKey("equip_mdate")) {
				equipment.setEquip_mdate(Integer.parseInt(jsonObject.getString("equip_mdate")));
				}
			if (jsonObject.containsKey("equip_ndate")) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = sdf.parse(jsonObject.getString("equip_ndate"));
				equipment.setEquip_ndate(date);
			}
			if (jsonObject.containsKey("equip_atime")) {
				equipment.setEquip_atime(Integer.parseInt(jsonObject.getString("equip_atime")));
				}
			if (jsonObject.containsKey("equip_life")) {
				equipment.setEquip_life(Integer.parseInt(jsonObject.getString("equip_life")));
				}	

			if (jsonObject.containsKey("equip_room")) {
				EquipRoom er = new EquipRoom();
				er.setEquip_room_id(Integer.valueOf(jsonObject.getString("equip_room")));
				equipment.setEquip_room(er);	
			}
			if (jsonObject.containsKey("user")) {
				User eu = new User();
				eu.setUser_id(Integer.valueOf(jsonObject.getString("user")));
				equipment.setUser(eu);	
			}	
			if (jsonObject.containsKey("equip_memo")) {
				equipment.setEquip_memo(jsonObject.getString("equip_memo"));
			}
			equipment.setEquip_isdeleted(0);
			boolean result;
			if (jsonObject.containsKey("equip_id")) {
				equipment.setEquip_id(Integer.valueOf(jsonObject.getString("equip_id")));
				result = equipmentService.save(equipment);// 添加信息
			} else {
				result = equipmentService.save(equipment); 
			}
			return JSON.toJSONString(result);
		}	
        
		//修改设备信息
		@RequestMapping("/updateEquipmentById.do")
		public @ResponseBody Integer updateEquipmentById(HttpServletRequest request, HttpSession session) throws ParseException {
			User user = (User) session.getAttribute(SessionKeyConstants.LOGIN);
			JSONObject jsonObject = JSONObject.fromObject(request.getParameter("equipment"));
			Integer equip_id = null;
			if (jsonObject.containsKey("equip_id")) {
				equip_id = Integer.parseInt(jsonObject.getString("equip_id"));
			}
			Boolean flag = equipmentService.updateEquipmentBase(equip_id, jsonObject, user);
			if (flag == true)
				return 1;
			else
				return 0;
		}
		
 		//根据id获取设备信息
		@RequestMapping("/selectEquipmentById.do")
		public @ResponseBody String selectEquipmentById(HttpServletRequest request, HttpSession session) {
			int equip_id = Integer.parseInt(request.getParameter("equip_id"));
			session.setAttribute("equip_id", equip_id);
			Equipment equipment = equipmentService.selectEquipmentById(equip_id);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("equipment", equipment);
			return jsonObject.toString();
		}

		//根据项目编号筛选设备信息
		@RequestMapping("/selectBaseInfoByProj.do")
		public @ResponseBody String selectBaseInfoByProj(HttpServletRequest request, HttpSession session) {
			Pager pager = new Pager();
			String proj_id = null;
			List<EquipRoom> room = new ArrayList<EquipRoom>();
			if(request.getParameter("page") != null){
				pager.setPage(Integer.valueOf(request.getParameter("page")));
			};
			if(request.getParameter("proj_id") != null){
				proj_id = request.getParameter("proj_id");
			};
			
			room = equipmentService.selectEquipRoomByPage(proj_id);
			List<Equipment> list = equipmentService.selectEquipByRoom(room, pager.getOffset(), pager.getLimit());
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("equipment", list);
			jsonObject.put("room", room);
			return jsonObject.toString();
		}
		
		//添加设备安装位置信息
		@RequestMapping(value = "/addEquipRoom.do")
		public @ResponseBody String addEquipRoom(HttpServletRequest request, HttpSession session) throws ParseException {
			JSONObject jsonObject = new JSONObject();
			jsonObject = JSONObject.fromObject(request.getParameter("equip_room"));
			EquipRoom equip_room = new EquipRoom();
			if (jsonObject.containsKey("equip_room_name")) {
				equip_room.setEquip_room_name(jsonObject.getString("equip_room_name"));}
			if (jsonObject.containsKey("project")) {
				Project erp = new Project();
				erp.setProj_id(Integer.valueOf(jsonObject.getString("project")));
				equip_room.setProject(erp);	
			}
			equip_room.setEquip_room_isdeleted(0);		
			boolean result;
			if (jsonObject.containsKey("equip_room_id")) {
				equip_room.setEquip_room_id(Integer.valueOf(jsonObject.getString("equip_room_id")));
				result = equipmentService.save(equip_room);// 修改信息
			} else {
				result = equipmentService.save(equip_room);// 添加信息
			}
			return JSON.toJSONString(result);
		}
				
		/*//添加设备分类信息
				@RequestMapping(value = "/addEquipType.do")
				public @ResponseBody String addEquipType(HttpServletRequest request, HttpSession session) throws ParseException {
					JSONObject jsonObject = new JSONObject();
					jsonObject = JSONObject.fromObject(request.getParameter("equip_type"));
					EquipType equip_type = new EquipType();
					if (jsonObject.containsKey("equip_type_name")) {
						equip_type.setEquip_type_name(jsonObject.getString("equip_type_name"));}
					if (jsonObject.containsKey("equip_type_memo")) {
						equip_type.setEquip_type_name(jsonObject.getString("equip_type_memo"));}
	
					equip_type.setEquip_type_isdeleted(0);
					
					boolean result;
					if (jsonObject.containsKey("equip_type_id")) {
						equip_type.setEquip_type_id(Integer.valueOf(jsonObject.getString("equip_type_id")));
						result = equipmentService.save(equip_type);// 修改信息
					} else {
						result = equipmentService.save(equip_type);// 添加信息
					}
					return JSON.toJSONString(result);
				}*/

				/*//添加设备制造商信息
				@RequestMapping(value = "/addEquipManu.do")
				public @ResponseBody String addEquipManu(HttpServletRequest request, HttpSession session) throws ParseException {
					JSONObject jsonObject = new JSONObject();
					jsonObject = JSONObject.fromObject(request.getParameter("equip_manu"));
					EquipManu equip_manu = new EquipManu();
					if (jsonObject.containsKey("equip_manu_name")) {
						equip_manu.setEquip_manu_name(jsonObject.getString("equip_manu_name"));
						}
					if (jsonObject.containsKey("equip_manu_tel")) {
						equip_manu.setEquip_manu_tel(Integer.parseInt(jsonObject.getString("equip_manu_tel")));
						}
					if (jsonObject.containsKey("equip_manu_addr")) {
						equip_manu.setEquip_manu_addr(jsonObject.getString("equip_manu_addr"));
						}	
					if (jsonObject.containsKey("equip_manu_memo")) {
						equip_manu.setEquip_manu_memo(jsonObject.getString("equip_manu_memo"));}

					equip_manu.setEquip_manu_isdeleted(0);
					
					boolean result;
					if (jsonObject.containsKey("equip_manu_id")) {
						equip_manu.setEquip_manu_id(Integer.valueOf(jsonObject.getString("equip_manu_id")));
						result = equipmentService.save(equip_manu);// 修改信息
					} else {
						result = equipmentService.save(equip_manu);// 添加信息
					}
					return JSON.toJSONString(result);
				}*/

				//查找安装位置信息
				@RequestMapping("/getEquipRoomInfo.do")
				public @ResponseBody String getEquipRoomInfo(HttpServletRequest request) {
					JSONObject jsonObject = new JSONObject();		
					List<EquipRoom> result = equipmentService.getEquipRoomInfo();		
					jsonObject.put("result", result);
					return jsonObject.toString();
				}

				//查找安装分类信息
				@RequestMapping("/getEquipTypeInfo.do")
				public @ResponseBody String getEquipTypeInfo(HttpServletRequest request) {
					JSONObject jsonObject = new JSONObject();		
					List<EquipType> result = equipmentService.getEquipTypeInfo();		
					jsonObject.put("result", result);
					return jsonObject.toString();
				}

				/*//查找设备制造商信息
				@RequestMapping("/getEquipManuInfo.do")
				public @ResponseBody String getEquipManuInfo(HttpServletRequest request) {
					JSONObject jsonObject = new JSONObject();		
					List<EquipManu> result = equipmentService.getEquipManuInfo();		
					jsonObject.put("result", result);
					return jsonObject.toString();
				}*/

				//添加设备特征参数信息
				@RequestMapping(value = "/addEquipPara.do")
				public @ResponseBody String addEquipPara(HttpServletRequest request, HttpSession session) throws ParseException {
					JSONObject jsonObject = new JSONObject();
					jsonObject = JSONObject.fromObject(request.getParameter("equip_para"));
					EquipPara equip_para = new EquipPara();
					if (jsonObject.containsKey("equipment")) {
						Equipment ee = new Equipment();
						ee.setEquip_id(Integer.valueOf(jsonObject.getString("equipment")));
						equip_para.setEquipment(ee);	
					}
					if (jsonObject.containsKey("equip_para_name")) {
						equip_para.setEquip_para_name(jsonObject.getString("equip_para_name"));
					}
					if (jsonObject.containsKey("equip_para_unit")) {
						equip_para.setEquip_para_unit(jsonObject.getString("equip_para_unit"));
					}
					if (jsonObject.containsKey("equip_para_rate")) {
						equip_para.setEquip_para_rate(jsonObject.getString("equip_para_rate"));
					}
					if (jsonObject.containsKey("equip_para_max")) {
						equip_para.setEquip_para_max(Float.parseFloat(jsonObject.getString("equip_para_max")));
					}
					if (jsonObject.containsKey("equip_para_min")) {
						equip_para.setEquip_para_min(Float.parseFloat(jsonObject.getString("equip_para_min")));
					}
					if (jsonObject.containsKey("equip_para_memo")) {
						equip_para.setEquip_para_memo(jsonObject.getString("equip_para_memo"));}

					equip_para.setEquip_para_isdeleted(0);
					
					boolean result;
					if (jsonObject.containsKey("equip_para_id")) {
						equip_para.setEquip_para_id(Integer.valueOf(jsonObject.getString("equip_para_id")));
						result = equipmentService.save(equip_para);// 修改信息
					} else {
						result = equipmentService.save(equip_para);// 添加信息
					}
					return JSON.toJSONString(result);
				}

				//根据id获取设备安装位置信息
				@RequestMapping("/selectEquipRoomById.do")
				public @ResponseBody String selectEquipRoomById(HttpServletRequest request, HttpSession session) {
					int equip_room_id = Integer.parseInt(request.getParameter("equip_room_id"));
					session.setAttribute("equip_room_id", equip_room_id);
					EquipRoom equiproom = equipmentService.selectEquipRoomById(equip_room_id);
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("equip_room", equiproom);
					return jsonObject.toString();
				}

				//根据id获取用户信息
				@RequestMapping("/selectUserById.do")
				public @ResponseBody String selectUserById(HttpServletRequest request, HttpSession session) {
					int user_id = Integer.parseInt(request.getParameter("user_id"));
					session.setAttribute("user_id", user_id);
					User user = equipmentService.selectUserById(user_id);
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("user", user);
					return jsonObject.toString();
				}
				
				//根据id获取设备特征信息
				@RequestMapping("/selectEquipParaById.do")
				public @ResponseBody String selectEquipParaById(HttpServletRequest request, HttpSession session) {
					int equip_para_id = Integer.parseInt(request.getParameter("equip_para_id"));
					session.setAttribute("equip_para_id", equip_para_id);
					EquipPara equippara = equipmentService.selectEquipParaById(equip_para_id);
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("equip_para", equippara);
					return jsonObject.toString();
				}
				
				//根据id获取设备信息
				@RequestMapping("/selectProjectById.do")
				public @ResponseBody String selectProjectById(HttpServletRequest request, HttpSession session) {
					int proj_id = Integer.parseInt(request.getParameter("proj_id"));
					session.setAttribute("proj_id", proj_id);
					Project project = equipmentService.selectProjectById(proj_id);
					JSONObject jsonObject = new JSONObject();
					jsonObject.put("project", project);
					return jsonObject.toString();
				}

				//根据页数显示设备信息列表
				@RequestMapping(value = "/getEquipMainListByPage.do")
				public @ResponseBody String getEquipMainsByPrarm(HttpServletRequest request, HttpSession session) {
					JSONObject jsonObject = new JSONObject();
					String searchKey = request.getParameter("searchKey");
					Integer totalRow = equipmentService.countEmTotal(searchKey);
					Pager pager = new Pager();
					pager.setPage(Integer.valueOf(request.getParameter("page")));
					pager.setTotalRow(Integer.parseInt(totalRow.toString()));
					List<EquipMain> list = equipmentService.selectEquipMainByPage(searchKey, pager.getOffset(), pager.getLimit());
					jsonObject.put("list", list);
					jsonObject.put("totalPage", pager.getTotalPage());
					System.out.println("totalPage:" + pager.getTotalPage());
					return jsonObject.toString();
				}

}















