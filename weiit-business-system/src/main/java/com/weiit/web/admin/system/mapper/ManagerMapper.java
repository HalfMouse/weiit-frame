package com.weiit.web.admin.system.mapper;



import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.core.mapper.BaseMapper;

/**
 * 管理员mapper接口
 * @author 半个鼠标
 * @date：2017年2月14日 上午2:34:56
 * @version 1.0
 * @company http://www.wei-it.com
 */
public interface ManagerMapper extends BaseMapper{
	
    /**
     * 向管理员与角色中间表插入关系数据
     * @return
     */
    void insertManagerAndRole(FormMap param);
    
    /**
     * 删除管理员与角色关系数据
     * @return
     */
    void removeManagerAndRole(FormMap param);
    
    /**
     * 管理员登录查询
     * @return 返回管理员对象
     */
    E login(FormMap formMap);
    
    /**
     * 修改管理员密码
     * @return 
     */
    void editManagerPassword(FormMap formMap);
    
}