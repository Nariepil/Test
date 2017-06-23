package com.cy.modules.acc_organization.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.base.Cachable;
import com.cy.base.CacheClear;
import com.cy.base.CachePut;
import com.cy.base.CacheUpdate;
import com.cy.beans.CommonDto;
import com.cy.beans.Pagination;
import com.cy.modules.acc_organization.dao.AccOrganizationMapper;
import com.cy.modules.acc_organization.model.AccOrganization;
/**
 * autogenerate V1.0 by dongao
 */
@Cachable
@Service
public class AccOrganizationServiceImpl  implements AccOrganizationService {
	@Autowired
    private AccOrganizationMapper accOrganizationMapper;
    
    
//    @CachePut(entity = AccOrganization.class, isPojo=false)
//    public Pagination<AccOrganization> findByPage(AccOrganization accOrganization) {
//        List<AccOrganization> list = accOrganizationMapper.selectByPage(accOrganization);
//        return new Pagination<AccOrganization>(list, accOrganization.getPageParameter());
//    }
    @CacheClear(entitys = {AccOrganization.class})
    public Long save(AccOrganization accOrganization) {
        accOrganizationMapper.insert(accOrganization);
        return accOrganization.getId();
    }
    @CachePut(entity = AccOrganization.class)
    public AccOrganization load(Long id) {
        AccOrganization accOrganization = accOrganizationMapper.load(id);
        return accOrganization;
    }
    @CacheUpdate(entity = AccOrganization.class, id = "${#accOrganization.id}")
    public void update(AccOrganization accOrganization) {
        accOrganizationMapper.update(accOrganization);
    }

    @CacheClear(entitys = {AccOrganization.class})
    public void delete(Long id) {
        accOrganizationMapper.delete(id);
    }
    @CachePut(entity = AccOrganization.class)
	public List<AccOrganization> selectAll(){
		return accOrganizationMapper.selectAll();
	}
    @CachePut(entity = AccOrganization.class, isPojo=false)
	public int countAll(){
		return accOrganizationMapper.countAll();
	}
    @CachePut(entity = AccOrganization.class)
    public List<AccOrganization> commonSelectBySql(String sql){
    	return accOrganizationMapper.commonSelectBySql(new CommonDto(sql));
    }
    @CachePut(entity = AccOrganization.class, isPojo=false)
	public int commonCountBySql(String sql){
    	return accOrganizationMapper.commonCountBySql(new CommonDto(sql));
    }
    @CachePut(entity = AccOrganization.class, isPojo=false)
    public Pagination<AccOrganization> commonBySqlPage(CommonDto commonDto){
    	List<AccOrganization> list= accOrganizationMapper.commonBySqlPage(commonDto);
        return new Pagination<AccOrganization>(list, commonDto.getPageParameter());
    }
    @Override
	public List<AccOrganization> findChildren(Long parentId) {
		List<AccOrganization> list= accOrganizationMapper.findChildren(parentId);
		return list;
	}

	@Override
	public List<AccOrganization> selectByEntity(AccOrganization accOrganization) {
		List<AccOrganization> accOrganizations = accOrganizationMapper.selectByEntity(accOrganization);
		return accOrganizations;
	}
	@Override
	public String findParentIdByCode(Long id) {
		AccOrganization org = accOrganizationMapper.load(id);
		if(org != null && org.getParentId() != 0){
			return findParentIdByCode(org.getParentId())+"-"+org.getId();
		}
		return org.getId()+"";
	}

    @Override
	public AccOrganization findByOrgCode(String orgCode) {
		return accOrganizationMapper.findByOrgCode(orgCode);
	}
	@Override
	public List<AccOrganization> findOrganizaByOrgCode(String orgCode) {
		return accOrganizationMapper.findOrganizaByOrgCode(orgCode);
	}
	
	@Override
    public String getAllNodes(Long id){
    	return accOrganizationMapper.getAllNodes(id);
    }
	
	public int selectCountByCode(AccOrganization accOrganization){
    	return accOrganizationMapper.selectCountByCode(accOrganization);
    }
	
}
