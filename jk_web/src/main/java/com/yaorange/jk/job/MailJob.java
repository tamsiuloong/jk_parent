package com.yaorange.jk.job;

import com.yaorange.jk.entity.Contract;
import com.yaorange.jk.entity.User;
import com.yaorange.jk.service.ContractService;
import com.yaorange.jk.service.UserService;
import com.yaorange.jk.utils.DateUtils;
import com.yaorange.jk.utils.JavaMailUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * @author coach tam
 * @date 2018/1/3
 */
public class MailJob {

    @Autowired
    private ContractService contractService;

    @Autowired
    private UserService userService;

    @Autowired
    private JavaMailUtils javaMailUtils;

    public void sendMail(){
        System.out.println("sending mail..."+new Date());
        //查询当天的购销合同,向相关人员发送一封邮件
        String now = DateUtils.format.format(new Date());
        List<Contract> contractList = contractService.findListByDeliveryPeriod(now);
        for (Contract contract:contractList) {
            String mail = "yaorange_201703@sina.com";
            if(contract.getCreateBy()!=null)
            {
                User user = userService.findById(contract.getCreateBy());
                mail= user.getUserInfo().getEmail();
            }

            javaMailUtils.sendMail(mail,"购销合同交货日期已到!","你好,购销合同号为["+contract.getContractNo()+"]购销合同交货日期已到!请及时处理!");
        }

    }
}
