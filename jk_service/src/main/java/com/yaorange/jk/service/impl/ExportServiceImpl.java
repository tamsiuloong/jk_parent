package com.yaorange.jk.service.impl;

import com.yaorange.jk.dao.BaseDao;
import com.yaorange.jk.entity.*;
import com.yaorange.jk.service.ContractService;
import com.yaorange.jk.service.ExportService;
import com.yaorange.jk.utils.Pagination;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * @author coach tam
 * @date 2017/12/20
 */
@Service("exportService")
@Transactional
public class ExportServiceImpl implements ExportService {

    @Autowired
    private BaseDao<Export,String> exportDao;
    @Autowired
    private BaseDao<ExportProduct,String> exportProductDao;

    @Autowired
    private ContractService contractService;
    @Override
    @Transactional(readOnly = true)
    public Pagination findByPage(Pagination page) {
        return exportDao.pageByHql("from Export order by inputDate desc",page.getPageNo(),page.getPageSize());
    }

    @Override
    public List<Export> findAll() {
        return exportDao.getListByHQL("from Export");
    }

    @Override
    public void save(Export model) {
        //记录合同编号
        String customerContractNo = "";

        //记录所有购销合同下的货物
        Set<ContractProduct> contractProductList = new HashSet<>();
        //1,修改购销合同状态 为 2 已保运
            //4028817a33812ffd013382048ff80024, 4028817a33812ffd0133821a8eb5002b -->数组
        String[] contractIds = model.getId().split(", ");
        for (String contractId:contractIds) {
            Contract contract = contractService.findById(contractId);
            customerContractNo+=" "+contract.getContractNo();

            //将当前合同货物全部加入到contractProductList
//            contractProductList.addAll(contract.getContractProducts());
            contract.setState(2l);
            contractService.update(contract);


            //拿到当前购销合同货物
            Set<ContractProduct> contractProducts = contract.getContractProducts();
            //2,拷贝报运货物
            for (ContractProduct cp:contractProducts)
            {
                try {
                //ContractProduct --> ExportProduct
                ExportProduct ep = new ExportProduct();

                    //将 cp 的数据拷贝到 ep
                    BeanUtils.copyProperties(ep,cp);
                    //手动将id值清空
                    ep.setId(null);
                //3,拷贝报运货物下附件
                //从cp中拷贝ep的附件
                    //当前合同货物下的附件
                Set<ExtCproduct>  extCproducts = cp.getExtCproducts();
                    //当前报运货物下的附件
                Set<ExtEproduct> extEproducts = new HashSet<>(extCproducts.size());
                for (ExtCproduct ec: extCproducts) {
                    //ExtCproduct  --> ExtEproduct
                    ExtEproduct ee = new ExtEproduct();
                    //将 ec 的数据拷贝到 ee
                    BeanUtils.copyProperties(ee,ec);
                    //手动将id值清空
                    ee.setId(null);
                    //将保运附件添加到extEproducts
                    extEproducts.add(ee);
                }

                    //设置保运单中的附件
                    ep.setExtEproducts(extEproducts);

                    //4,设置保运单对应货物
                    //将保运货物添加到报运单中
                   model.getExportProducts().add(ep);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }

            }
        }


        //设置报运单基本信息
        model.setContractIds(model.getId());//合同编号
        model.setInputDate(new Date());
        model.setCustomerContract(customerContractNo);//合同号
        model.setState(0);//初始化状态为草稿 0
        //5，新增报运单
        exportDao.save(model);
    }

    @Override
    @Transactional(readOnly = true)
    public Export findById(String id) {
        return exportDao.get(Export.class,id);
    }

    @Override
    public void update(Export model) {

        //1,更新报运单基本信息
        Export oldExport = exportDao.get(Export.class,model.getId());
        model.setExportProducts(oldExport.getExportProducts());
        model.setCreateTime(oldExport.getCreateTime());
        model.setUpdateTime(new Date());
        exportDao.evict(oldExport);
        exportDao.update(model);
    }

    public String toZero(String str)
    {
        return str.isEmpty()?"0":str;
    }
    @Override
    public void deleteByIds(String[] ids) {
        for (String id :ids)
        {
            exportDao.deleteById(Export.class,id);
        }
    }

    @Override
    public List<ExportProduct> findEPListByEId(String id) {
        return exportProductDao.getListByHQL("from ExportProduct where export.id=?",id);
    }

    @Override
    public void updateEP(ExportProduct ep) {
        ExportProduct old = exportProductDao.get(ExportProduct.class,ep.getId());
        ep.setExtEproducts(old.getExtEproducts());
        ep.setFactory(old.getFactory());
        ep.setBoxNum(old.getBoxNum());
        ep.setPackingUnit(old.getPackingUnit());
        ep.setOrderNo(old.getOrderNo());
        ep.setExport(old.getExport());
        ep.setProductNo(old.getProductNo());

        exportProductDao.evict(old);
        exportProductDao.update(ep);
    }
}
