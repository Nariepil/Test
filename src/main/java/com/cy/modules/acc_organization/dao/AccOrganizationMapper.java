package com.cy.modules.acc_organization.dao;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cy.beans.CommonDto;
import com.cy.modules.acc_organization.model.AccOrganization;

/**
 * autogenerate V1.0 by dongao
 */
public interface AccOrganizationMapper {
	/**
	 * 根据主键删除AccOrganization
	 * 
	 * @param id
	 * @return
	 */
	int delete(Long id);

	/**
	 * 新增AccOrganization
	 * 
	 * @param entity
	 * @return
	 */
	int insert(AccOrganization entity);

	/**
	 * 根据主键查询AccOrganization
	 * 
	 * @param id
	 * @return
	 */
	AccOrganization load(Long id);

	/**
	 * 根据条件查询AccOrganization（带分页）
	 * 
	 * @param entity
	 * @return
	 */
	List<AccOrganization> selectByPage(AccOrganization entity);
	/**
	 * 更新AccOrganization
	 * 
	 * @param entity
	 * @return
	 */
	int update(AccOrganization entity);
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
	List<AccOrganization> commonSelectBySql(CommonDto commonDto);
	/**
	 * AccOrganization：自定义sql统计
	 * 
	 */
	int commonCountBySql(CommonDto commonDto);
	/**
	 * AccOrganization：自定义sql分页
	 * 
	 */
	List<AccOrganization> commonBySqlPage(CommonDto commonDto);
	
	List<AccOrganization> findChildren(@Param(value = "parentId")Long parentId);

	List<AccOrganization> selectByEntity(AccOrganization accOrganization);
	
	AccOrganization findByOrgCode(String orgCode);
	
	List<AccOrganization> findOrganizaByOrgCode(@Param(value = "orgCode")String orgCode);
	
	String getAllNodes(Long id);
	
	int selectCountByCode(AccOrganization accOrganization);
	
}
