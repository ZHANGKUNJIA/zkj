package com.atstudy.bean.bo;

//分页业务模型类
public class PageBo {
    //考量点1，需要和mysql要进行交互。。。。mysql的limit子句需要的参数是什么？
    //起始记录索引
    private Integer startIndex = 0 ;   //l代表java中要把0这个整数常量，定义成long类型
    // 查询记录数
    private Integer pageSize = 10;      //每页显示10条

    //考量点2，需要和/role/admin.html中的分页的html控件进行交互。。。。页面设计图中有什么需求？
    //需要知道有多少页
    private Integer pageCount;              //需要计算出来的值：符合条件的记录/每页显示条记录（向上取整）
                                         //eg: 总计21条记录，每页显示10条。。。总计需要有3页
                                            // 总计20条记录，每页显示10条。。。。总计需要2页
    //需要知道有多少条符合条件的记录
    private Integer resultCount;           //需要从getCount()访问层接口返回，再对它赋值

    //需要知道当前处于第几页
    private Integer page= 1;                  //需要由用户点击、跳转，就会更新这个值

    //访问器


    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {

        this.pageSize = pageSize;

        //逻辑2：当前是第几页发生改变时，级联修改起始记录索引的
        //假设处于第一页，每页显示10条。startIndex=0
        //假设处于第二页，每页显示10条，startIndex=10
        //归纳规则： (当前处于第几页-1)*每页显示多少条
        Integer curStartIndex = 0;
        curStartIndex = (this.getPage() -1 )* this.getPageSize();

        //bug:设置起始startindex的时候，考虑不要小于0的场景，边界值的控制
        if(curStartIndex<0)
            curStartIndex = 0;

        this.setStartIndex(curStartIndex);
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;

        //需要控制当前的页码，不能超过总页数，必须大于0
        if(this.getPage()<1){
            this.setPage(1);
        }
        if(this.getPage()>this.getPageCount()){
            this.setPage(this.getPageCount());
        }
    }

    public Integer getResultCount() {
        return resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;

        //逻辑1：当你对resultCount属性赋值时，需要修改pageSize
        Integer totalPageCount = 0;
        if(this.getResultCount() % this.getPageSize() == 0){
            //如果 记录数 取模 页数 等于0，直接除即可
            totalPageCount = this.getResultCount() / this.getPageSize();
        }
        else {
            //如果 记录数 取模 页数 大于0，除后+1
            totalPageCount = this.getResultCount() / this.getPageSize() +1;
        }
        this.setPageCount(totalPageCount);
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;

        //逻辑3：每页显示的记录数发生改变时，级联修改起始记录索引的
        //假设处于第一页，每页显示10条。startIndex=0
        //假设处于第二页，每页显示10条，startIndex=10
        //归纳规则： (当前处于第几页-1)*每页显示多少条
        Integer curStartIndex = 0;
        curStartIndex = (this.getPage() -1 )* this.getPageSize();

        if(curStartIndex<0)
            curStartIndex = 0;

        this.setStartIndex(curStartIndex);
    }

    //toString方法重写

    @Override
    public String toString() {
        return "PageBo{" +
                "startIndex=" + startIndex +
                ", pageSize=" + pageSize +
                ", pageCount=" + pageCount +
                ", resultCount=" + resultCount +
                ", page=" + page +
                '}';
    }
}
