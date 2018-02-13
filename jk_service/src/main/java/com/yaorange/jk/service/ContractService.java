package com.yaorange.jk.service;

import com.yaorange.jk.entity.Contract;
import com.yaorange.jk.utils.Pagination;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
public interface ContractService {
    Pagination findByPage(Pagination page);

    List<Contract> findAll();

    void save(Contract model);

    Contract findById(String id);

    void update(Contract model);

    void deleteByIds(String[] ids);

    /**
     * 更新合同状态
     * @param id
     * @param state
     *
     */
    void updateState(String id, Long state);

    /**
     * 根据状态查询分页列表
     * @param page
     * @param state
     * @return
     */
    Pagination findByPage(Pagination page, int state);

    /**
     * 根据交货日期查询购销合同
     * @param now
     * @return
     */
    List<Contract> findListByDeliveryPeriod(String now);
}
