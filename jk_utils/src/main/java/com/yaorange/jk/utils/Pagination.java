package com.yaorange.jk.utils;

import com.fasterxml.jackson.annotation.JsonView;

import java.util.List;

/**
 * 列表分页。包含list属性。
 */
@SuppressWarnings("serial")
public class Pagination extends SimplePage implements java.io.Serializable {

    public Pagination() {
    }

    /**
     * 构造器
     *
     * @param pageNo     页码
     * @param pageSize   每页几条数据
     * @param totalCount 总共几条数据
     */
    public Pagination(Integer pageNo, Integer pageSize, Integer totalCount) {
        super(pageNo, pageSize, totalCount);


    }

    /**
     * 构造器
     *
     * @param pageNo     页码
     * @param pageSize   每页几条数据
     * @param totalCount 总共几条数据
     * @param list       分页内容
     */
    public Pagination(int pageNo, int pageSize, int totalCount, List<?> list) {
        super(pageNo, pageSize, totalCount);
        this.list = list;
    }

    /**
     * 第一条数据位置
     *
     * @return
     */
    @JsonView(ViewVo.Simple.class)
    public int getFirstResult() {
        return (pageNo - 1) * pageSize;
    }

    /**
     * 当前页的数据
     */
    private List<?> list;

    /**
     * 分页html
     */
    private String links;

    /**
     * 当前action  url
     */
    private String url;


    /**
     * 获得分页内容
     *
     * @return
     */
    @JsonView(ViewVo.Simple.class)
    public List<?> getList() {
        return list;
    }
//
//    /**
//     * 获得分页内容
//     *
//     * @return
//     */
//    public List<?> getResults() {
//        return list;
//    }


    /**
     * 设置分页内容
     *
     * @param list
     */
    public void setList(List list) {
        this.list = list;
    }


//    public void setUrl(String url) {
//        this.url = url;
//    }

//    public String getLinks() {
//        StringBuffer sBuf = new StringBuffer();
//        int curPageNo = this.pageNo;		//当前页
//
//
//        sBuf.append("<span class=\"noprint\" style=\"padding:5px;\">");
//
//        //利用js动态设置分页页码
//        sBuf.append("<script language=\"javascript\">");
//        sBuf.append("	function setPageNo( value ){");
//        sBuf.append("		document.getElementById(\"page.pageNo\").value = value;");
//        //sBuf.append("		alert(document.getElementById(\"pageNo\").value);");
//        sBuf.append("	}");
//        sBuf.append("</script>");
//        sBuf.append("<input type=\"hidden\" id=\"page.pageNo\" name=\"page.pageNo\" value=\"").append(curPageNo).append("\">");		//分页参数：当前页隐藏域
//
//
//        sBuf.append("&nbsp;第").append(curPageNo).append("页 / 共").append(getTotalPage()).append("页&nbsp;");
//        sBuf.append("&nbsp;总共").append(getTotalCount()).append("条记录 每页").append(getPageSize()).append("条记录&nbsp;");
//
//        sBuf.append("<a href=\"#").append("\" onclick=\"setPageNo(1);formSubmit('").append(url).append("','_self')");
//        sBuf.append("\">[首页]");
//        sBuf.append("</a>&nbsp;");
//
//        if(this.pageNo<=1){
//            curPageNo = 1;
//        }else{
//            curPageNo = this.pageNo - 1;
//        }
//        sBuf.append("<a href=\"#").append("\" onclick=\"setPageNo(").append(curPageNo).append(");formSubmit('").append(url).append("','_self')");
//        sBuf.append("\">[上一页]");
//        sBuf.append("</a>&nbsp;");
//
//
//        if(this.pageNo>=getTotalPage()){
//            curPageNo = getTotalPage();
//        }else{
//            curPageNo = this.pageNo + 1;
//        }
//        sBuf.append("<a href=\"#").append("\" onclick=\"setPageNo(").append(curPageNo).append(");formSubmit('").append(url).append("','_self')");
//        sBuf.append("\">[下一页]");
//        sBuf.append("</a>&nbsp;");
//
//        sBuf.append("<a href=\"#").append("\" onclick=\"setPageNo(").append(getTotalPage()).append(");formSubmit('").append(url).append("','_self')");
//        sBuf.append("\">[末页]");
//        sBuf.append("</a>&nbsp;");
//
//        sBuf.append("</span>");
//
//        return sBuf.toString();
//    }

}
