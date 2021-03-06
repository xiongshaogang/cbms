package com.prcsteel.platform.order.service.invoice;

import com.prcsteel.platform.order.model.dto.PoolInDetailDto;
import com.prcsteel.platform.order.model.model.PoolInDetail;
import com.prcsteel.platform.order.model.query.InvoiceDetailQuery;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by lcw on 2015/8/1.
 */
public interface PoolInDetailService {
    int deleteByPrimaryKey(Long id);

    int insert(PoolInDetail record);

    int insertSelective(PoolInDetail record);

    PoolInDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PoolInDetail record);

    int updateByPrimaryKey(PoolInDetail record);

    /**
     * 查询
     *
     * @param paramMap  查询参数
     * @return
     */
    List<PoolInDetailDto> query(InvoiceDetailQuery invoiceDetailQuery);

    /**
     * 查询总数
     *
     * @param paramMap  查询参数
     * @return
     */
    int queryTotal(InvoiceDetailQuery invoiceDetailQuery);

    /**
     * 查询应收总吨位
     *
     * @param paramMap  查询参数
     * @return
     */
    BigDecimal queryShouldTotalWeight(Map<String, Object> paramMap);

    /**
     * 查询应收总金额
     *
     * @param paramMap  查询参数
     * @return
     */
    BigDecimal queryShouldTotalAmount(Map<String, Object> paramMap);

    /**
     * 按大类，品名查询
     *
     * @param paramMap  查询参数
     * @return
     */
    List<PoolInDetail> queryByNsort(Map<String, Object> paramMap);

    /**
     * 按大类，品名查询总数
     *
     * @param paramMap  查询参数
     * @return
     */
    int queryTotalByNsort(Map<String, Object> paramMap);
    
    int modifyPoolinDetailReceivedAmount(PoolInDetail modifier);
    
    /**
     * 查询进项票详情应收进项票合计
     * @param invoiceDetailQuery
     * @return
     */
    PoolInDetailDto queryCombined(InvoiceDetailQuery invoiceDetailQuery);
    
    /**
     * 根据品名,材质,规格查询详情
     *
     * @param paramMap  查询参数
     * @return
     */
    List<PoolInDetailDto> queryDetailByNsort(Map<String, Object> paramMap);
    
    /**
     * 根据品名,材质,规格查询详情总数
     * @param paramMap
     * @return
     */
    int queryTotalDetailByNsort(Map<String, Object> paramMap);
    
    /**
     * 根据品名,材质,规格统计详情,总量和数量
     * @param paramMap
     * @return
     */
    PoolInDetailDto queryStatisDetailByNsort(Map<String, Object> paramMap);
} 
