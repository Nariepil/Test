package com.cy.modules.acc_organization.service;

import java.util.List;

import com.cy.beans.CommonDto;
import com.cy.beans.Pagination;
import com.cy.modules.acc_organization.model.AccOrganization;
/**
 * autogenerate V1.0 by dongao
 */
public interface AccOrganizationService  {
	
	 /**
     * 分页查询所有AccOrganization
     * 
     * @param accOrganization
     * @return
     */
//    Pagination<AccOrganization> findByPage(AccOrganization accOrganization);
    /**
     * 增加AccOrganization
     * 
     * @param accOrganization
     * @return 主键
     */
    Long save(AccOrganization accOrganization);

    /**
     * 获取AccOrganization
     * 
     * @param id
     * @return
     */
    AccOrganization load(Long id);

    /**
     * 修改AccOrganization
     * 
     * @param accOrganization
     */
    void update(AccOrganization accOrganization);
    /**
     * 删除AccOrganization
     * 
     * @param id
     * @return
     */
    void delete(Long id);
    /**
	 * AccOrganization：查询全部
	 * 
	 */
	List<AccOrganization> selectAll();
	/**
	 * AccOrganization：统计全部
	 * 
	 */
	int countAll();
	/**
	 * AccOrganization：自定义sql查询
	 * 
	 */
	List<AccOrganization> commonSelectBySql(String sql);
	/**
	 * AccOrganization：自定义sql统计
	 * 
	 */
	int commonCountBySql(String sql);
	/**
	 * AccOrganization：自定义sql分页
	 * 
	 */
	Pagination<AccOrganization> commonBySqlPage(CommonDto commonDto);
	
	/**
	 * AccOrganization：查询组织子树
	 * 
	 */
	List<AccOrganization> findChildren(Long parentId);

	/**
	 * 条件查询
	 * @param setOrgCode
	 */
	List<AccOrganization> selectByEntity(AccOrganization accOrganization);
	/**
	 * 根据code查找所有父节点
	 * @param orgCode
	 * @return
	 */
	String findParentIdByCode(Long orgCode);

	
	/**
	 * 根据code查询组织
	 * @param orgCode
	 * @return
	 */
	AccOrganization findByOrgCode(String orgCode);
	
	/**
	 * 根据orgcode查询组织
	 * @param orgCode
	 * @return
	 */
	List<AccOrganization> findOrganizaByOrgCode(String orgCode);
	
	/**
	 * 获取下面所有节点
	 * @param id
	 * @return
	 */
	String getAllNodes(Long id); 

	int selectCountByCode(AccOrganization accOrganization);
	
}
