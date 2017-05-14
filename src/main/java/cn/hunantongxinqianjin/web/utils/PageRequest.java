/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hunantongxinqianjin.web.utils;

/**
 * 分页查询对象
 *
 * @author shixiaoqi
 */
public class PageRequest{

    public final int MIN_SIZE = 1;
    public final int DEFAULT_SIZE = 10;

    public final int MIN_NUMBER = 1;
    public final int DEFAULT_NUMBER = 1;

    public final boolean DEFAULT_NEED_PAGE = true;
    public final int DEFAULT_INDEX = 0;

    private int pageSize;
    private int pageNo;
    private int index;
    private boolean needPage;

    public PageRequest() {
        pageSize = DEFAULT_SIZE;
        pageNo = DEFAULT_NUMBER;
        index = DEFAULT_INDEX;
        needPage = DEFAULT_NEED_PAGE;
    }

    public PageRequest(int pageSize, int pageNo, int index, boolean needPage) {
        setPageSize(pageSize);
        setPageNo(pageNo);
        setIndex(index);
        this.needPage = needPage;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
        if (pageSize < MIN_SIZE) {
            pageSize = MIN_SIZE;
        }
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
        if (pageSize < MIN_SIZE) {
            this.pageSize = MIN_SIZE;
        }
        this.pageSize = pageSize;
    }

    /**
     * @return the pageNo
     */
    public int getPageNo() {
        if (pageNo < MIN_NUMBER) {
            pageNo = MIN_NUMBER;
        }
        return pageNo;
    }

    /**
     * @param pageNo the pageNo to set
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
        if (pageNo < MIN_NUMBER) {
            this.pageNo = MIN_NUMBER;
        }
//        this.pageNo = pageNo;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the needPage
     */
    public boolean isNeedPage() {
        return needPage;
    }

    /**
     * @param needPage the needPage to set
     */
    public void setNeedPage(boolean needPage) {
        this.needPage = needPage;
    }

    /**
     * @return the offset
     */
    public int getOffset() {
        return pageSize * (pageNo - 1);
    }

}
