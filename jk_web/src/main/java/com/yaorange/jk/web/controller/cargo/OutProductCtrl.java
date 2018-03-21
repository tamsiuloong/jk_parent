package com.yaorange.jk.web.controller.cargo;

import com.yaorange.jk.entity.Contract;
import com.yaorange.jk.entity.ContractProduct;
import com.yaorange.jk.entity.Factory;
import com.yaorange.jk.service.ContractProductService;
import com.yaorange.jk.service.FactoryService;
import com.yaorange.jk.utils.DateUtils;
import com.yaorange.jk.utils.Pagination;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by coach-tam on 2018/2/24.
 */
@RequestMapping("/cargo/outProduct")
@Controller
public class OutProductCtrl {

    @Autowired
    private ContractProductService contractProductService;

    /**
     * 手动打印excel
     * @return
     */
    @RequestMapping("/print")
    public void print(String inputDate,HttpServletResponse response) throws IOException {
        //创建工作表
//        Workbook wb = new HSSFWorkbook(); // HSSFWorkbook 只支持2003格式，行不超过65536，列不超过256，支持模板
//        Workbook wb = new XSSFWorkbook();//XSSFWorkbook 支持2003，2007/所有版本格式，行支持百万级别，支持模板
        Workbook wb = new SXSSFWorkbook(500);//SXSSFWorkbook 只支持2007/所有版本格式，行支持百万级别，不支持模板
        //创建工作簿
        Sheet sheet = wb.createSheet("出货表");
        //设置列宽
        sheet.setColumnWidth(1,26*256);
        sheet.setColumnWidth(2,11*256);
        sheet.setColumnWidth(3,26*256);
        sheet.setColumnWidth(4,11*256);
        sheet.setColumnWidth(5,15*256);
        sheet.setColumnWidth(6,10*256);
        sheet.setColumnWidth(7,9*256);
        sheet.setColumnWidth(8,8*256);

        //初始化行数
        int rowNo=0;
        //初始化列数
        int cellNo = 1;

        //1.打印大标题
        Row row = sheet.createRow(rowNo++);
        Cell cell = row.createCell(1);
        cell.setCellStyle(bigTitle(wb));
        //横向合并
        sheet.addMergedRegion(new CellRangeAddress(0,0,1,8));

        //设置大标题内容  --》2012年8月份出货表
        //2015-07  --> 2015年7月
        String bigTitle = inputDate.replaceAll("-0","年").replaceAll("-","年")+"月份出货表";
        cell.setCellValue(bigTitle);


        //2.打印小标题
        row  = sheet.createRow(rowNo++);
        String[] titles  = new String[]{"客户","订单号","货号","数量","工厂","工厂交期","船期","贸易条款"};

        for (String title:titles) {
            cell = row.createCell(cellNo++);
            cell.setCellValue(title);
            cell.setCellStyle(title(wb));
        }


        //3.打印出货表数据
        List<ContractProduct> cpList = contractProductService.findOutProductList(inputDate);
//        CellStyle cellStyle  = text(wb);
            for (ContractProduct cp:cpList)
            {
                row  = sheet.createRow(rowNo++);
                cellNo=1;//重置cellNo
                Contract c = cp.getContract();
                titles  = new String[]{c.getCustomName(),c.getContractNo(),cp.getProductNo(),cp.getCnumber().toString(),cp.getFactoryName(), DateUtils.format.format(c.getDeliveryPeriod()),DateUtils.format.format(c.getShipTime()),c.getTradeTerms()};
                for (String title:titles) {
                    cell = row.createCell(cellNo++);
                    cell.setCellValue(title);
                    cell.setCellStyle(text(wb));
                }
            }
        //4.下载
        response.setHeader("Content-Disposition", "attachment;filename="+new String((inputDate+"出货表").getBytes("gbk"), "iso8859-1")+".xlsx");
        wb.write(response.getOutputStream());
        wb.close();
    }

    /**
     * 使用模板打印excel
     * @return
     * @throws IOException
     */
//    public String print() throws IOException {
//        //创建工作表
//        //获取完整的模板路径
//        String realPath = ServletActionContext.getServletContext().getRealPath("/make/xlsprint/tOUTPRODUCT.xls");
//        Workbook wb = new HSSFWorkbook(new FileInputStream(realPath));
//        //获取工作簿
//        Sheet sheet = wb.getSheetAt(0);
//
//
//        //初始化行数
//        int rowNo=0;
//        //初始化列数
//        int cellNo = 1;
//
//        //----------------1.打印大标题-----------------------//
//        Row row = sheet.getRow(rowNo++);
//        Cell cell = row.getCell(1);
//
//
//        //设置大标题内容  --》2012年8月份出货表
//        //2015-07  --> 2015年7月
//        String bigTitle = inputDate.replaceAll("-0","年").replaceAll("-","年")+"月份出货表";
//        cell.setCellValue(bigTitle);
//
//
//        //----------------2.打印小标题-----------------------//
//        rowNo++;
//
//
//        //----------------3.打印出货表数据-------------------//
//
//        //获取第三行
//        row = sheet.getRow(rowNo++);
//        //获取模板每一列的数据样式
//        CellStyle style1 = row.getCell(cellNo++).getCellStyle();
//        CellStyle style2 = row.getCell(cellNo++).getCellStyle();
//        CellStyle style3 = row.getCell(cellNo++).getCellStyle();
//        CellStyle style4 = row.getCell(cellNo++).getCellStyle();
//        CellStyle style5 = row.getCell(cellNo++).getCellStyle();
//        CellStyle style6 = row.getCell(cellNo++).getCellStyle();
//        CellStyle style7 = row.getCell(cellNo++).getCellStyle();
//        CellStyle style8 = row.getCell(cellNo++).getCellStyle();
//        CellStyle[] styles = new CellStyle[]{style1,style2,style3,style4,style5,style6,style7,style8};
//
//        List<ContractProduct> cpList = contractProductService.findOutProductList(inputDate);
//        CellStyle cellStyle  = text(wb);
//        String[] titles;
//        for(int i = 0 ; i < 10000;i++)
//        {
//            for (ContractProduct cp:cpList)
//            {
//                row  = sheet.createRow(rowNo++);
//                cellNo=1;//重置cellNo
//                Contract c = cp.getContract();
//                titles  = new String[]{c.getCustomName(),c.getContractNo(),cp.getProductNo(),cp.getCnumber().toString(),cp.getFactoryName(),DateUtils.format.format(c.getDeliveryPeriod()),DateUtils.format.format(c.getShipTime()),c.getTradeTerms()};
//                for (String title:titles) {
//                    cell = row.createCell(cellNo++);
//                    cell.setCellValue(title);
//                    cell.setCellStyle(styles[cellNo-2]);
//                }
//            }
//        }
//
//
//        //4.下载
//        HttpServletResponse response = ServletActionContext.getResponse();
//        response.setHeader("Content-Disposition", "attachment;filename="+new String((inputDate+"出货表").getBytes("gbk"), "iso8859-1")+".xls");
//        wb.write(response.getOutputStream());
//        wb.close();
//        return null;
//    }





    //大标题的样式
    public CellStyle bigTitle(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short)16);
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);					//字体加粗

        style.setFont(font);

        style.setAlignment(CellStyle.ALIGN_CENTER);					//横向居中
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);		//纵向居中

        return style;
    }
    //小标题的样式
    public CellStyle title(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("黑体");
        font.setFontHeightInPoints((short)12);

        style.setFont(font);

        style.setAlignment(CellStyle.ALIGN_CENTER);					//横向居中
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);		//纵向居中

        style.setBorderTop(CellStyle.BORDER_THIN);					//上细线
        style.setBorderBottom(CellStyle.BORDER_THIN);				//下细线
        style.setBorderLeft(CellStyle.BORDER_THIN);					//左细线
        style.setBorderRight(CellStyle.BORDER_THIN);				//右细线

        return style;
    }

    //文字样式
    public CellStyle text(Workbook wb){
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontName("Times New Roman");
        font.setFontHeightInPoints((short)10);

        style.setFont(font);

        style.setAlignment(CellStyle.ALIGN_LEFT);					//横向居左
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);		//纵向居中

        style.setBorderTop(CellStyle.BORDER_THIN);					//上细线
        style.setBorderBottom(CellStyle.BORDER_THIN);				//下细线
        style.setBorderLeft(CellStyle.BORDER_THIN);					//左细线
        style.setBorderRight(CellStyle.BORDER_THIN);				//右细线

        return style;
    }
}
