package com.cy.modules.g_user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.base.Cachable;
import com.cy.base.CacheClear;
import com.cy.base.CachePut;
import com.cy.base.CacheUpdate;
import com.cy.beans.CommonDto;
import com.cy.beans.Pagination;
import com.cy.modules.g_user.dao.GUserMapper;
import com.cy.modules.g_user.model.GUser;
/**
 * autogenerate V1.0 by dongao
 */
@Cachable
@Service
public class GUserServiceImpl  implements GUserService {
	@Autowired
    private GUserMapper gUserMapper;
    
    
    @CachePut(entity = GUser.class, isPojo=false)
    public Pagination<GUser> findByPage(GUser gUser) {
        List<GUser> list = gUserMapper.selectByPage(gUser);
        return new Pagination<GUser>(list, gUser.getPageParameter());
    }
    @CacheClear(entitys = {GUser.class})
    public Long save(GUser gUser) {
        gUserMapper.insert(gUser);
        return gUser.getId();
    }
    @CachePut(entity = GUser.class)
    public GUser load(Long id) {
        GUser gUser = gUserMapper.load(id);
        return gUser;
    }
    @CacheUpdate(entity = GUser.class, id = "${#gUser.id}")
    public void update(GUser gUser) {
        gUserMapper.update(gUser);
    }

    @CacheClear(entitys = {GUser.class})
    public void delete(Long id) {
        gUserMapper.delete(id);
    }
    @CachePut(entity = GUser.class)
	public List<GUser> selectAll(){
		return gUserMapper.selectAll();
	}
    @CachePut(entity = GUser.class, isPojo=false)
	public int countAll(){
		return gUserMapper.countAll();
	}
    @CachePut(entity = GUser.class)
    public List<GUser> commonSelectBySql(String sql){
    	return gUserMapper.commonSelectBySql(new CommonDto(sql));
    }
    @CachePut(entity = GUser.class, isPojo=false)
	public int commonCountBySql(String sql){
    	return gUserMapper.commonCountBySql(new CommonDto(sql));
    }
    @CachePut(entity = GUser.class, isPojo=false)
    public Pagination<GUser> commonBySqlPage(CommonDto commonDto){
    	List<GUser> list= gUserMapper.commonBySqlPage(commonDto);
        return new Pagination<GUser>(list, commonDto.getPageParameter());
    }
    @Override
	public List<GUser> findUserByOrgCode(String orgCode) {
		return gUserMapper.findUserByOrgCode(orgCode);
	}
	@Override
	public int countUserByOrgCode(String orgCode) {
		return gUserMapper.countUserByOrgCode(orgCode);
	}
	@Override
	public int loginCodeUniqueValid(String name, String idStr) {
		return gUserMapper.loginCodeUniqueValid(name,idStr);
	}
	@Override
	public GUser login(String name, String password) {
		return gUserMapper.login(name, password);
	}
	@Override
	public List<GUser> findUserLikeOrgCode(String orgCode) {
		return gUserMapper.findUserLikeOrgCode(orgCode);
	}
	 @CacheUpdate(entity = GUser.class, id = "${#gUser.id}")
	public void enableUser(Long id) {
		 gUserMapper.enableUser(id);
	}
	@Override
	public long getCurrentSeqId() {
		// TODO Auto-generated method stub
		return gUserMapper.getCurrentSeqId();
	}
}
