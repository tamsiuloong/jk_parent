package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.Contract;
import com.yaorange.jk.entity.ContractProduct;
import com.yaorange.jk.entity.ExtCproduct;
import com.yaorange.jk.service.ContractProductService;
import com.yaorange.jk.service.ExtCProductService;
import com.yaorange.jk.service.ContractService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Service("extCproductService")
@Transactional
public class ExtCProductServiceImpl implements ExtCProductService {

    @Autowired
    private BaseDao<ExtCproduct,String> extCproductDao;

    @Autowired
    private ContractService contractService;

    @Autowired
    private ContractProductService contractProductService;
    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page, String cpId) {
        return extCproductDao.pageByHql("from ExtCproduct where contractProduct.id=?",page.getPageNo(),page.getPageSize(),cpId);
    }

    @Override
    public List<ExtCproduct> findAll() {
        return extCproductDao.getListByHQL("from ExtCproduct");
    }

    @Override
    public void save(ExtCproduct model) {

        //重新计算合同总金额
        //附件金额=单价*数量
        Long extCpAmount =  model.getCnumber() * model.getPrice();
        model.setAmount(extCpAmount);
        extCproductDao.save(model);

        ContractProduct cp = contractProductService.findById(model.getContractProduct().getId());
        //合同总金额 = 合同总金额 + 货物金额
        Contract contract = cp.getContract();
        Long totalAmount = contract.getTotalAmount() + extCpAmount;
        contract.setTotalAmount(totalAmount);
        contractService.update(contract);

    }

    @Override
    @Transactional(readOnly = true)
    public ExtCproduct findById(String id) {
        return extCproductDao.get(ExtCproduct.class,id);
    }

    @Override
    public void update(ExtCproduct model) {


        //附件金额=单价*数量
        Long extCpAmount =  model.getCnumber() * model.getPrice();



        //修改合同总金额

        //获取附件老金额
        ExtCproduct old = extCproductDao.get(ExtCproduct.class,model.getId());
        Contract contract = old.getContractProduct().getContract();
        //合同总金额 = 合同总金额-货物老金额+货物新金额
        Long totalAmount = contract.getTotalAmount() - old.getAmount() + extCpAmount;
        contract.setTotalAmount(totalAmount);

        contractService.update(contract);

        model.setAmount(extCpAmount);
        extCproductDao.evict(old);
        extCproductDao.update(model);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id :ids)
        {
            extCproductDao.deleteById(ExtCproduct.class,id);
            //TODO 维护合同总金额
        }
    }
}
