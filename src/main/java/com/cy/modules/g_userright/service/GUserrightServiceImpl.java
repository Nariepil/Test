package com.cy.modules.g_userright.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cy.base.Cachable;
import com.cy.base.CacheClear;
import com.cy.base.CachePut;
import com.cy.base.CacheUpdate;
import com.cy.beans.CommonDto;
import com.cy.beans.Pagination;
import com.cy.modules.g_userright.dao.GUserrightMapper;
import com.cy.modules.g_userright.model.GUserright;
/**
 * autogenerate V1.0 by dongao
 */
@Cachable
@Service
public class GUserrightServiceImpl  implements GUserrightService {
	@Autowired
    private GUserrightMapper gUserrightMapper;
    
    
    @CachePut(entity = GUserright.class, isPojo=false)
    public Pagination<GUserright> findByPage(GUserright gUserright) {
        List<GUserright> list = gUserrightMapper.selectByPage(gUserright);
        return new Pagination<GUserright>(list, gUserright.getPageParameter());
    }
    @CacheClear(entitys = {GUserright.class})
    public Long save(GUserright gUserright) {
        gUserrightMapper.insert(gUserright);
        return gUserright.getId();
    }
    @CachePut(entity = GUserright.class)
    public GUserright load(Long id) {
        GUserright gUserright = gUserrightMapper.load(id);
        return gUserright;
    }
    @CacheUpdate(entity = GUserright.class, id = "${#gUserright.id}")
    public void update(GUserright gUserright) {
        gUserrightMapper.update(gUserright);
    }

    @CacheClear(entitys = {GUserright.class})
    public void delete(Long id) {
        gUserrightMapper.delete(id);
    }
    @CachePut(entity = GUserright.class)
	public List<GUserright> selectAll(){
		return gUserrightMapper.selectAll();
	}
    @CachePut(entity = GUserright.class, isPojo=false)
	public int countAll(){
		return gUserrightMapper.countAll();
	}
    @CachePut(entity = GUserright.class)
    public List<GUserright> commonSelectBySql(String sql){
    	return gUserrightMapper.commonSelectBySql(new CommonDto(sql));
    }
    @CachePut(entity = GUserright.class, isPojo=false)
	public int commonCountBySql(String sql){
    	return gUserrightMapper.commonCountBySql(new CommonDto(sql));
    }
    @CachePut(entity = GUserright.class, isPojo=false)
    public Pagination<GUserright> commonBySqlPage(CommonDto commonDto){
    	List<GUserright> list= gUserrightMapper.commonBySqlPage(commonDto);
        return new Pagination<GUserright>(list, commonDto.getPageParameter());
    }
	@Override
	public List<Long> findByUserId(Long valueOf) {
		return gUserrightMapper.findByUserId(valueOf);
	}
	@Override
	public void delByRightIds(Long userId, List<Long> needDelIds) {
		gUserrightMapper.delByRightIds(userId, needDelIds);		
	}
	@Override
	public void addByRightIds(Long userId, List<Long> needAddIds) {
		gUserrightMapper.addByRightIds(userId, needAddIds);			
	}
	@Override
	public List<Long> findByUserIds(String[] checkedUserIds) {
		return gUserrightMapper.findByUserIds(checkedUserIds);
	}
	@Override
	public List<Long> findByUserIdAndProjectId(Long userId, Long projectId) {
		// TODO Auto-generated method stub
		return gUserrightMapper.findByUserIdAndProjectId(userId, projectId);
	}
    
}
