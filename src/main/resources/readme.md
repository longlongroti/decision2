一、基础数据管理CRUD
    ~~1.供应商~~
    ~~2.14885编码~~
    ~~3.功能位置~~
    
二、大型设备CRUD
    ~~1.压风12      lg~~
    ~~2.瓦抽泵10    lg~~
    ~~3.制氮机13    lg~~
    ~~4.供电           wyy~~
    ~~5.强排14          ry~~
    ~~6.主排水16        ry~~
    7.采区排水17      ry
    ~~8.强力皮带18      rcy~~
    9.架空乘人装置21   wyy
    10.主通风机22     wyy
    ~~11.提升机24       ry~~
    ~~12.井下变电所30    ry~~
    
    
20200615-20200620   
wyy ~~设备领用查看详情~~    完成
wyy ~~设备档案查看详情~~  完成
wyy ~~租赁计划 查看详情~~  完成  
ry  ~~设备回退 增删改查~~   
wyy ~~设备移交 增删改查   增 删 查 改 详细页面 完成~~
ry  ~~设备维修 增删改查~~   
ry  ~~设备报废 增删改查~~  lg  
hjs 租赁合同(拉计划) 增删改查   
hjs 租赁出租出库(拉合同) 增删改查     
hjs 租赁退租申请(拉合同/拉设备) 增删改查     
ry  租赁退租入库(拉退租申请) 增删改查   lg
wyy ~~设备调动其他 完成~~
wyy 流程图bpmn   
cl  各个业务流程审批    
cl/wyy 首页   
wyy 报表  
wyy 测试   
cl 业务扭转历史表   
cl 自动生成编码     
cl/wyy 各个模块优化    

20200622-20200627   
~~ry 查看详情附表信息~~   
~~ry 各模块删除（+附表）~~
hjs 各模块 搜索条件补齐   
cl 帆软10集成   
cl 系统部署          
wyy/rcy 测试      
rcy 用户权限分配   
wyy 数据导入        
lwx 业务模块导入导出功能   

支卉


0620wyy   
trigger   
流程图   
测试  
首页  
报表   

文件上传    

计划 领用的detail界面
人员的company_id数据
删除功能
拉取设备的窗口增加查询条件


delimiter ||
drop trigger if exists update_archive_status||
create trigger update_archive_status after update on device_archives for each row
begin
if NEW.status=2 then
insert into device_archives_status(uuid,device_id,device_name,flow_type,flow_type_code,location_name,location_id
,equipment_status,current_using_dept,current_using_dept_name,current_using_org, current_using_org_name)
select UUID(), Old.uuid,material_name,'equipmentDoc','1',location_number_name,location_number,biz_status,lease_org,
       lease_org_name,using_dept,using_dept_name from device_archives where uuid = OLD.uuid;
end if;
end||
delimiter ;     

 

delimiter ||
drop trigger if exists update_archive_status_lease_plan||
create trigger update_archive_status_lease_plan after update on device_lease_plan for each row
begin
if NEW.status=2 then
insert into device_archives_status(uuid,device_id,device_name,flow_type,flow_type_code,location_name,location_id
,equipment_status,current_using_dept,current_using_dept_name,current_using_org, current_using_org_name)
select UUID(), a.device_id,b.material_name,'leasingPlanning','11',b.location_number_name,b.location_number,
       b.biz_status,b.lease_org,
       b.lease_org_name,b.using_dept,b.using_dept_name from device_lease_plan_equipment a
                     left join device_archives b on a.device_id = b.uuid
where device_lease_id = OLD.UUID;
end if;
end||
delimiter ;      

设备领用
delimiter ||
DROP TRIGGER
IF EXISTS update_archive_status_receive||

CREATE TRIGGER update_archive_status_receive AFTER UPDATE ON device_use FOR EACH ROW
BEGIN

IF NEW. STATUS = 2 THEN
	INSERT INTO device_archives_status (
		uuid,
		device_id,
		device_name,
		flow_type,
		flow_type_code,
		location_id,
		location_name,
		equipment_status,
		equipment_status_name,
		current_using_dept,
		current_using_dept_name,current_using_org, current_using_org_name
	) SELECT
		UUID(),
		a.device_id,
		b.material_name,
		'deviceRecieve',
		'12',
		a.location_uuid,
		a.location_name,
		a.equ_status_code,
		a.equ_status_name,
		c.apply_org,
		c.apply_org_name,b.using_dept,b.using_dept_name
	FROM
		device_use_equipment a
	LEFT JOIN device_archives b ON a.device_id = b.uuid
	LEFT JOIN device_use c ON c.uuid = a.device_use_id
	WHERE
		a.device_use_id = OLD.UUID ;
	END
	IF ;
	END||
delimiter ;      


回退
delimiter ||
DROP TRIGGER
IF EXISTS update_archive_status_rollback||

CREATE TRIGGER update_archive_status_rollback AFTER UPDATE ON device_rollback FOR EACH ROW
BEGIN

IF NEW. STATUS = 2 THEN
	INSERT INTO device_archives_status (
		uuid,
		device_id,
		device_name,
		flow_type,
		flow_type_code,
		location_id,
		location_name,
		equipment_status,
		equipment_status_name,
		current_using_dept,
		current_using_dept_name,current_using_org, current_using_org_name
	) SELECT
		UUID(),
		a.device_id,
		b.material_name,
		'devicerollback',
		'13',
		'编码',
		a.func_position_target,
		'编码',
		a.device_status_target,
		c.fold_department_id,
		c.fold_department,b.using_dept,b.using_dept_name
	FROM
		device_rollback_entry a
	LEFT JOIN device_archives b ON a.device_id = b.uuid
	LEFT JOIN device_rollback c ON c.uuid = a.rollback_id
	WHERE
		a.rollback_id = OLD.UUID ;
	END
	IF ;
	END||
delimiter ;   

移交
delimiter ||
DROP TRIGGER
IF EXISTS update_archive_status_handover||

CREATE TRIGGER update_archive_status_handover AFTER UPDATE ON device_handover FOR EACH ROW
BEGIN

IF NEW. STATUS = 2 THEN
	INSERT INTO device_archives_status (
		uuid,
		device_id,
		device_name,
		flow_type,
		flow_type_code,
		location_id,
		location_name,
		equipment_status,
		equipment_status_name,
		current_using_dept,
		current_using_dept_name,current_using_org, current_using_org_name
	) SELECT
		UUID(),
		a.device_uuid,
		b.material_name,
		'devicehandover',
		'14',
		'编码',
		a.func_position_target,
		'编码',
		a.device_status_target,
		c.into_org,
		c.into_org_name,b.using_dept,b.using_dept_name
	FROM
		device_handover_details a
	LEFT JOIN device_archives b ON a.device_uuid = b.uuid
	LEFT JOIN device_handover c ON c.uuid = a.parent
	WHERE
		a.parent = OLD.UUID ;
	END
	IF ;
	END||
delimiter ;        


维修
delimiter ||
DROP TRIGGER
IF EXISTS update_archive_status_maintain||

CREATE TRIGGER update_archive_status_maintain AFTER UPDATE ON device_maintain FOR EACH ROW
BEGIN

IF NEW. STATUS = 2 THEN
	INSERT INTO device_archives_status (
		uuid,
		device_id,
		device_name,
		flow_type,
		flow_type_code,
		location_id,
		location_name,
		equipment_status,
		equipment_status_name,current_using_org, current_using_org_name
	) SELECT
		UUID(),
		a.device_id,
		b.material_name,
		'deviceMaintain',
		'15',
		'a.编码',
		'a.名称',
		'a.编码',
		'a.名称',b.using_dept,b.using_dept_name
	FROM
		device_maintain_equipment a
	LEFT JOIN device_archives b ON a.device_id = b.uuid
	WHERE
		a.device_maintain_id = OLD.UUID ;
	END
	IF ;
	END||
delimiter ;       


报废
delimiter ||
DROP TRIGGER
IF EXISTS update_archive_status_scarp||

CREATE TRIGGER update_archive_status_scarp AFTER UPDATE ON device_scarp FOR EACH ROW
BEGIN

IF NEW. STATUS = 2 THEN
	INSERT INTO device_archives_status (
		uuid,
		device_id,
		device_name,
		flow_type,
		flow_type_code,
		location_id,
		location_name,
		equipment_status,
		equipment_status_name,current_using_org, current_using_org_name
	) SELECT
		UUID(),
		a.device_id,
		b.material_name,
		'devicescarp',
		'16',
		'a.编码',
		'a.名称',
		'已报废的编码',
		'已报废',b.using_dept,b.using_dept_name
	FROM
		device_scarp_entry a
	LEFT JOIN device_archives b ON a.device_id = b.uuid
	WHERE
		a.device_scarp_id = OLD.UUID ;
	END
	IF ;
	END||
delimiter ;    


租赁合同
delimiter ||
DROP TRIGGER
IF EXISTS update_archive_lease_contract||

CREATE TRIGGER update_archive_lease_contract AFTER UPDATE ON device_lease_contract FOR EACH ROW
BEGIN

IF NEW. STATUS = 2 THEN
	INSERT INTO device_archives_status (
		uuid,
		device_id,
		device_name,
		flow_type,
		flow_type_code,
		location_id,
		location_name,
		equipment_status,
		equipment_status_name,
		current_using_org,
		current_using_org_name
	) SELECT
		UUID(),
		a.material_number,
		b.material_name,
		'devicecontract',
		'17',
		b.location_number_name,
		b.location_number,
		b.biz_status,
		b.lease_org,
		b.using_dept,
		b.using_dept_name
	FROM
		device_lease_contract_details a
	LEFT JOIN device_archives b ON a.material_number = b.uuid
	WHERE
		a.lease_contract_id = OLD.UUID ;
	END
	IF ;
	END||
delimiter ;    

出租出库
delimiter ||
DROP TRIGGER
IF EXISTS update_archive_status_rentout||

CREATE TRIGGER update_archive_status_rentout AFTER UPDATE ON device_rent_out FOR EACH ROW
BEGIN

IF NEW. STATUS = 2 THEN
	INSERT INTO device_archives_status (
		uuid,
		device_id,
		device_name,
		flow_type,
		flow_type_code,
		location_name,
		location_id,
		equipment_status,
		current_using_dept,
		current_using_dept_name,
		current_using_org,
		current_using_org_name
	) SELECT
		UUID(),
		a.material_number,
		b.material_name,
		'deviceLeaseRentOut',
		'17',
		b.location_number_name,
		b.location_number,
		b.biz_status,
		b.lease_org,
		b.lease_org_name,
		b.using_dept,
		b.using_dept_name
	FROM
		device_rent_out_details a
	LEFT JOIN device_archives b ON a.material_number = b.uuid
	WHERE
		a.rent_out_id = OLD.UUID ;
	END
	IF ;
	END||
delimiter ;      








