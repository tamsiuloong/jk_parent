package com.yaorange.jk.web.controller.cargo;

import com.yaorange.jk.entity.ContractProduct;
import com.yaorange.jk.service.ContractProductService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by coach-tam on 2018/2/24.
 */
@RequestMapping("/cargo/contractProduct")
@RestController
public class ContractProductCtrl {

    @Autowired
    private ContractProductService contractProductService;

    @GetMapping
    public Pagination list(Pagination page,String contractId)
    {
        Pagination result = contractProductService.findByPage(page,contractId);
        return result;
    }


    @GetMapping("getAll")
    public List<ContractProduct> getAll(Pagination page)
    {
        List<ContractProduct> contractProductList = contractProductService.findAll();
        return contractProductList;
    }

    @DeleteMapping
    public String delete(@RequestBody String[] ids)
    {
        contractProductService.deleteByIds(ids);
        return "1";
    }


    @PutMapping
    public String update(@RequestBody ContractProduct contractProduct)
    {
        contractProductService.update(contractProduct);
        return "1";
    }

    @PostMapping
    public ContractProduct add(@RequestBody ContractProduct contractProduct)
    {
        contractProductService.save(contractProduct);
        return contractProduct;
    }
}
