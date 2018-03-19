package com.yaorange.jk.web.controller.cargo;

import com.yaorange.jk.entity.Contract;
import com.yaorange.jk.service.ContractService;
import com.yaorange.jk.utils.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by coach-tam on 2018/2/24.
 */
@RequestMapping("/cargo/contract")
@RestController
public class ContractCtrl {

    @Autowired
    private ContractService contractService;

    @GetMapping
    public Pagination list(Pagination page)
    {
        Pagination result = contractService.findByPage(page);
        return result;
    }


    @GetMapping("getAll")
    public List<Contract> getAll(Pagination page)
    {
        List<Contract> contractList = contractService.findAll();
        return contractList;
    }

    @DeleteMapping
    public String delete(String[] ids)
    {
        contractService.deleteByIds(ids);
        return "1";
    }


    @PutMapping
    public String update(@RequestBody Contract contract)
    {
        contractService.update(contract);
        return "1";
    }

    @PostMapping
    public Contract add(@RequestBody Contract contract)
    {
        contractService.save(contract);
        return contract;
    }
}
