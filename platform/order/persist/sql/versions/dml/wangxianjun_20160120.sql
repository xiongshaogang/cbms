-- 根据买家更新提成表开单时间为订单主表第一笔订单开单时间
DELIMITER &&  
DROP PROCEDURE IF EXISTS modify_report_new_user_reward_buy_data;
CREATE PROCEDURE modify_report_new_user_reward_buy_data()

BEGIN
	-- 修改数据
		-- 按买家分组并按时候排序
	DECLARE accountId int;
	DECLARE openCreated datetime;
	DECLARE buyerOrgId int;
	DECLARE buyerOrgName VARCHAR(45);
	DECLARE ownerId int;
  DECLARE ownerName VARCHAR(45);
	
	
	-- 遍历数据结束标志
	DECLARE done int default false;
	
	-- 游标
	DECLARE cur CURSOR FOR select distinct b.account_id from busi_consign_order b where b.status > 6  ;
	
	-- 将结束标志绑定到游标
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
	-- 打开游标
	OPEN cur;

	-- 游标 开始循环
	read_loop: LOOP
	
		-- 提取游标里的数据，这里只有一个，多个的话也一样；
		FETCH cur into accountId; 
		
		-- 声明结束的时候
		IF done THEN
			LEAVE read_loop;
		END IF;	
			BEGIN

				select o.created,o.buyer_org_id,o.buyer_org_name,o.owner_id,o.owner_name
				into openCreated,buyerOrgId,buyerOrgName,ownerId,ownerName
				from busi_consign_order o
				where o.created = (select min(b.created)  from busi_consign_order b where b.account_id = accountId and b.status > 6 )
				and o.account_id = accountId and o.status > 6;

			END;

			  UPDATE report_new_user_reward r
			  SET r.open_order_date = openCreated ,
			  	  r.manager_id = ownerId,
			  	  r.manager_name = ownerName,
			  	  r.org_id = buyerOrgId,
			  	  r.org_name = buyerOrgName
			  WHERE r.add_new_buyer = accountId
			  and is_deleted = '0';
	END LOOP;
	
		-- 遍历数据结束标志
		-- 关闭游标
	CLOSE cur;
END; -- &&
-- DELIMITER ;  


-- 执行存储过程
CALL modify_report_new_user_reward_buy_data();