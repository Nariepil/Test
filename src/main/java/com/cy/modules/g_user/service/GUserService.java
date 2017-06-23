package com.cy.modules.g_user.service;

import java.util.List;

import com.cy.beans.CommonDto;
import com.cy.beans.Pagination;
import com.cy.modules.g_user.model.GUser;
/**
 * autogenerate V1.0 by dongao
 */
public interface GUserService  {
	
	 /**
     * 分页查询所有GUser
     * 
     * @param gUser
     * @return
     */
    Pagination<GUser> findByPage(GUser gUser);
    /**
     * 增加GUser
     * 
     * @param gUser
     * @return 主键
     */
    Long save(GUser gUser);

    /**
     * 获取GUser
     * 
     * @param id
     * @return
     */
    GUser load(Long id);

    /**
     * 修改GUser
     * 
     * @param gUser
     */
    void update(GUser gUser);
    /**
     * 删除GUser
     * 
     * @param id
     * @return
     */
    void delete(Long id);
    /**
	 * GUser：查询全部
	 * 
	 */
	List<GUser> selectAll();
	/**
	 * GUser：统计全部
	 * 
	 */
	int countAll();
	/**
	 * GUser：自定义sql查询
	 * 
	 */
	List<GUser> commonSelectBySql(String sql);
	/**
	 * GUser：自定义sql统计
	 * 
	 */
	int commonCountBySql(String sql);
	/**
	 * GUser：自定义sql分页
	 * 
	 */
	Pagination<GUser> commonBySqlPage(CommonDto commonDto);
	/**
	 * 根据=地区编码获得用户
	 * @param orgCode
	 * @return
	 */
	List<GUser> findUserByOrgCode(String orgCode);
	/**
	 * 根据地区编码like获得下面及其的所有用户
	 * @param orgCode
	 * @return
	 */
	List<GUser> findUserLikeOrgCode(String orgCode);
	/**
	 * 查询地区编码like获得下面及其的所有用户的总数量
	 */
	int countUserByOrgCode(String orgCode);
	/**
	 * 查询用户名、登录用户编码（简称）；2个字段合在一起的唯一性
	 * @param name
	 * @param idStr
	 * @return
	 */
	int loginCodeUniqueValid(String name, String idStr);
	/**
	 * 根据登录名、密码进行校验。登录名可以为用户名、登录用户编码（简称）
	 * @param name
	 * @param password
	 * @return
	 */
	GUser login(String name, String password);
	/**
     * 启用GUser
     * 
     * @param id
     * @return
     */
    void enableUser(Long id);
    /**
     * 获取id
     * @param seqName
     * @return
     */
    long getCurrentSeqId();
}
