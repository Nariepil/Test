package com.cy.modules.g_right.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.base.Cachable;
import com.cy.base.CacheClear;
import com.cy.base.CachePut;
import com.cy.base.CacheUpdate;
import com.cy.beans.CommonDto;
import com.cy.beans.Pagination;
import com.cy.modules.g_right.dao.GRightMapper;
import com.cy.modules.g_right.model.GRight;
import com.cy.modules.g_user.model.GUser;
/**
 * autogenerate V1.0 by dongao
 */
@Cachable
@Service
public class GRightServiceImpl  implements GRightService {
	@Autowired
    private GRightMapper gRightMapper;
    
    
    @CachePut(entity = GRight.class, isPojo=false)
    public Pagination<GRight> findByPage(GRight gRight) {
        List<GRight> list = gRightMapper.selectByPage(gRight);
        return new Pagination<GRight>(list, gRight.getPageParameter());
    }
    @CacheClear(entitys = {GRight.class})
    public Long save(GRight gRight) {
        gRightMapper.insert(gRight);
        return gRight.getId();
    }
    @CachePut(entity = GRight.class)
    public GRight load(Long id) {
        GRight gRight = gRightMapper.load(id);
        return gRight;
    }
    @CacheUpdate(entity = GRight.class, id = "${#gRight.id}")
    public void update(GRight gRight) {
        gRightMapper.update(gRight);
    }

    @CacheClear(entitys = {GRight.class})
    public void delete(Long id) {
        gRightMapper.delete(id);
    }
    @CachePut(entity = GRight.class)
	public List<GRight> selectAll(){
		return gRightMapper.selectAll();
	}
    @CachePut(entity = GRight.class, isPojo=false)
	public int countAll(){
		return gRightMapper.countAll();
	}
    @CachePut(entity = GRight.class)
    public List<GRight> commonSelectBySql(String sql){
    	return gRightMapper.commonSelectBySql(new CommonDto(sql));
    }
    @CachePut(entity = GRight.class, isPojo=false)
	public int commonCountBySql(String sql){
    	return gRightMapper.commonCountBySql(new CommonDto(sql));
    }
    @CachePut(entity = GRight.class, isPojo=false)
    public Pagination<GRight> commonBySqlPage(CommonDto commonDto){
    	List<GRight> list= gRightMapper.commonBySqlPage(commonDto);
        return new Pagination<GRight>(list, commonDto.getPageParameter());
    }
	@Override
	public List<GRight> selectAllHead() {
		return gRightMapper.selectAllHead();
	}
	@Override
	public List<GRight> selectAllByProjectId(Integer projectId) {
		return gRightMapper.selectAllByProjectId(projectId);
	}
	@Override
	public List<GRight> selectUserMenuHead(Long id) {
		return gRightMapper.selectUserMenuHead(id);
	}
	@Override
	public List<GRight> selectUserMenuLeaf(Long userId, Long headId) {
		return gRightMapper.selectUserMenuLeaf(userId, headId);
	}
	@Override
	public List<GRight> selectUserMenuAll(GUser user) {
		return gRightMapper.selectUserMenuAll(user);
	}
	@Override
	public List<GRight> selectUserRightAll(GUser user) {
		return gRightMapper.selectUserRightAll(user);
	}
	@Override
	public List<GRight> selectUserDisabledButton(Long userId) {
		return gRightMapper.selectUserDisabledButton(userId);
	}
	@Override
	public List<GRight> selectUserDisabledRight(Long userId) {
		return gRightMapper.selectUserDisabledRight(userId);
	}
	@Override
	public List<GRight> findChildren(Long parentId, Integer type) {
		return gRightMapper.findChildren(parentId, type);
	}
	@Override
	public int countByCode(String code) {
		return gRightMapper.countByCode(code);
	}
	@Override
	public Integer getMaxSequence(Long pId) {
		return gRightMapper.getMaxSequence(pId);
	}
	@Override
	public void deleteByCode(String code) {
		gRightMapper.deleteByCode(code);
	}
	@Override
	public List<GRight> selectByEntity(GRight gRight) {
		return gRightMapper.selectByEntity(gRight);
	}
	@Override
    public String getAllNodes(Long id){
    	return gRightMapper.getAllNodes(id);
    }
}
