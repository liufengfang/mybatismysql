package com.liuff.dao;

import com.liuff.entity.Resident;

import java.util.List;

/**
 * Created by liufengfang on 2019/10/14.
 */
public interface ResidentDao {
    /**
     * 新增用户
     * @param resident
     * @return
     */
    int save (Resident resident);

    /**
     * 更新用户信息
     * @param resident
     * @return
     */
    int update (Resident resident);

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById (long id);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Resident selectById (long id);

    /**
     * 查询所有用户信息
     * @return
     */
    List<Resident> selectAll ();
}
