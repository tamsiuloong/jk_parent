package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.Contract;
import com.yaorange.jk.service.ContractService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Service("contractService")
@Transactional
public class ContractServiceImpl implements ContractService {

    @Autowired
    private BaseDao<Contract,String> contractDao;
    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page) {
        return contractDao.pageByHql("from Contract",page.getPageNo(),page.getPageSize());
    }

    @Override
    public Pagination findByPage(Pagination page, int state) {
        return contractDao.pageByHql("from Contract where state = ?",page.getPageNo(),page.getPageSize(),new Long(state));
    }

    @Override
    public List<Contract> findAll() {
        return contractDao.getListByHQL("from Contract");
    }

    @Override
    public void save(Contract model) {
        model.setTotalAmount(0L);
        model.setState(0L);//草稿
        contractDao.save(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Contract findById(String id) {
        return contractDao.get(Contract.class,id);
    }

    @Override
    public void update(Contract model) {

        Contract old = contractDao.get(Contract.class,model.getId());
        model.setCreateTime(old.getCreateTime());
        model.setUpdateTime(new Date());
        contractDao.evict(old);
        contractDao.update(model);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id :ids)
        {
            contractDao.deleteById(Contract.class,id);
        }
    }

    @Override
    public void updateState(String id, Long state) {
        Contract contract = contractDao.get(Contract.class,id);
        contract.setState(state);

        contractDao.update(contract);
    }

    @Override
    public List<Contract> findListByDeliveryPeriod(String now) {
        return contractDao.getListByHQL("from Contract where state = 2 and to_char(deliveryPeriod,'yyyy-mm-dd')=?",now);
    }
}
