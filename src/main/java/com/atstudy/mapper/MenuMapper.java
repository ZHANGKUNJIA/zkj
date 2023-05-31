package com.atstudy.mapper;

import com.atstudy.bean.po.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 访问数据库中 menu数据表 数据访问层 接口
 * */
@Repository
public interface MenuMapper {

    // 根据员工编号查询菜单列表
    List<Menu> getListByAdminId(@Param("admin_id") Integer admin_id);

}
