package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.Contract;
import com.yaorange.jk.entity.ContractProduct;
import com.yaorange.jk.service.ContractProductService;
import com.yaorange.jk.service.ContractService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Service("contractProductService")
@Transactional
public class ContractProductServiceImpl implements ContractProductService {

    @Autowired
    private BaseDao<ContractProduct,String> contractProductDao;

    @Autowired
    private ContractService contractService;
    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page, String contractId) {
        return contractProductDao.pageByHql("from ContractProduct where contract.id=?",page.getPageNo(),page.getPageSize(),contractId);
    }

    @Override
    public List<ContractProduct> findAll() {
        return contractProductDao.getListByHQL("from ContractProduct");
    }

    @Override
    public void save(ContractProduct model) {

        //重新计算合同总金额
        //货物金额=单价*数量
        Long productAmount =  model.getCnumber() * model.getPrice();
        model.setAmount(productAmount);
        contractProductDao.save(model);

        //合同总金额 = 合同总金额 + 货物金额
        Contract contract = contractService.findById(model.getContract().getId());
        Long totalAmount = contract.getTotalAmount() + productAmount;
        contract.setTotalAmount(totalAmount);
        contractService.update(contract);

    }

    @Override
    @Transactional(readOnly = true)
    public ContractProduct findById(String id) {
        return contractProductDao.get(ContractProduct.class,id);
    }

    @Override
    public void update(ContractProduct model) {


        //货物金额=单价*数量
        Long productAmount =  model.getCnumber() * model.getPrice();



        //修改合同总金额

        //获取货物老金额
        ContractProduct old = contractProductDao.get(ContractProduct.class,model.getId());
        Contract contract = old.getContract();
        //合同总金额 = 合同总金额-货物老金额+货物新金额
        Long totalAmount = contract.getTotalAmount() - old.getAmount() + productAmount;
        contract.setTotalAmount(totalAmount);

        contractService.update(contract);

        model.setAmount(productAmount);
        contractProductDao.evict(old);
        contractProductDao.update(model);
    }

    @Override
    public void deleteByIds(String[] ids) {
        for (String id :ids)
        {
            contractProductDao.deleteById(ContractProduct.class,id);
            //TODO 维护合同总金额
        }
    }

    @Override
    public List<ContractProduct> findOutProductList(String inputDate) {
        return contractProductDao.getListByHQL("from ContractProduct where contract.state = 1 and to_char(contract.shipTime,'yyyy-mm') = ?",inputDate);
    }
}
