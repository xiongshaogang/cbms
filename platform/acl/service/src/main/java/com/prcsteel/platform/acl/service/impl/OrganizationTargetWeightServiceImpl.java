package com.prcsteel.platform.acl.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.prcsteel.platform.acl.model.dto.TargetWeightForUpdateDto;
import com.prcsteel.platform.acl.model.model.OrganizationTargetWeight;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.prcsteel.platform.acl.service.OrganizationService;
import com.prcsteel.platform.common.constants.Constant;
import com.prcsteel.platform.common.exception.BusinessException;
import com.prcsteel.platform.acl.persist.dao.OrganizationTargetWeightDao;
import com.prcsteel.platform.acl.service.OrganizationTargetWeightService;

/**
 * @author peanut
 * @version 1.0
 * @description 服务中心目标交易量service
 * @date 2016/4/8 11:22
 */
// Created by zhoucai@prcsteel.com 移植风控上线代码 on 2016/5/6.
@Service
public class OrganizationTargetWeightServiceImpl implements OrganizationTargetWeightService {
    @Resource
    OrganizationService organizationService;

    @Resource
	OrganizationTargetWeightDao organizationTargetWeightDao;

    /**
     * 根据年份查询在用服务中心目标交易量
     * @param years  年份
     */
    @Override
    public List<OrganizationTargetWeight> getWeightByYear(String years) {
        if(StringUtils.isBlank(years)){
            throw new BusinessException(Constant.ERROR_MISSING_REQUIRED_PARAM,"年份不能为空");
        }
        return organizationTargetWeightDao.getWeightByYears(years);
    }

    /**
     * 批量更新交易量
     * @param list
     * @return
     */
    @Override
    public int doBatchUpdateWeight(List<TargetWeightForUpdateDto> list) {
        if(list==null || list.isEmpty()){
            throw new BusinessException(Constant.ERROR_MISSING_REQUIRED_PARAM,"无数据更新!");
        }
		List<TargetWeightForUpdateDto> wList=list.stream().filter(e->e.getWeight()!=null).collect(Collectors.toList());
		if(wList ==null || wList.isEmpty()) return 0;
		
        return organizationTargetWeightDao.doBatchUpdateWeight(list);
    }

	@Override
	public BigDecimal selectByUserId(Integer userId) {
		// TODO Auto-generated method stub
		String  monthDb = new SimpleDateFormat ("yyyyMM").format(new Date());
		if(userId==null&&userId.equals("")){
			return null;
		}
		String  years = new SimpleDateFormat ("yyyy").format(new Date());
		OrganizationTargetWeight targetWeight= organizationTargetWeightDao.selectByUserId(years, userId);
		 String month=monthDb.substring(monthDb.length() - 2, monthDb.length());
		if(month.equals("01"))
			    return targetWeight.getTargetWeightOne();
			if(month.equals("02"))
				return targetWeight.getTargetWeightTwo();
			else if(month.equals("03"))
				return targetWeight.getTargetWeightThree();
			else if(month.equals("04"))
				return targetWeight.getTargetWeightFour();
			else if(month.equals("05"))
				return targetWeight.getTargetWeightFive();
			else if(month.equals("06"))
				return targetWeight.getTargetWeightSix();
			else if(month.equals("07"))
				return targetWeight.getTargetWeightSeven();
			else if(month.equals("08"))
				return targetWeight.getTargetWeightEight();
			else if(month.equals("09"))
				return targetWeight.getTargetWeightNine();
			else if(month.equals("10"))
				return targetWeight.getTargetWeightTen();
			else if(month.equals("11"))
				return targetWeight.getTargetWeightEleven();
			else if(month.equals("12"))
				return targetWeight.getTargetWeightTwelve();
			else								
				 return null;								
		
	}
}
