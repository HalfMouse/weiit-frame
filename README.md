# JAVA极速开发框架[Weiit]

Weiit是基于Java语言的极速web开发框架，其核心设计目标是开发迅速、代码量少、学习简单、功能强大、轻量级、易扩展、Restful。Weiit适用于快速开发，轻应用型的应用开发，大大降低开发人员投入与维护成本支出，是一款永久免费开源的Web开发框架。weiit-saas是完全开源电商SaaS系统，属于weiit开源的社区版，旧版框架已不在维护和更新，仅供学习和参考，新版saas从底层架构到前后端UI设计已经全面优化和升级，访问地址：https://www.wei-it.com/

## 框架应用案例-最新下载
-  gitee开源地址：[https://gitee.com/wei-it/weiit-saas](https://gitee.com/wei-it/weiit-saas)
-  github开源地址：[https://github.com/HalfMouse/weiit-saas](https://github.com/HalfMouse/weiit-saas)

## 一、框架主要特点
 

 -  基于**Spring+SpringMVC+Mybstis**主流开源框架，遵循MVC架构，设计轻巧，使用简单，拥有传统 SSH 框架的绝大部分核心功能，适用于快速开发；
 -  框架去实体化，请求参数进行统一拦截与处理，所有取值从**FormMap** 中获取；
 -  框架定义 **Mapper/Service/Controller** 方法标准，常规方法统一省略不写，开发人员只关注特殊业务的编写的原则；
 -  框架统一封装结果集 **E** 对象，即所有取值对象皆转化成E对象，从E对象中Get；
 -  框架统一封装**第三方资源** 支持包，支持阿里云、腾讯云、七牛云、容联云、快递鸟、百度等厂商。统一约定 **工具类** 的使用与标准，避免技术成员随意引用第三方工具类导致升级与维护成本增加；
 - 支持 **模块化开发** ，可对常用功能进行模块成组件，方便热插式使用，如系统管理模块功能，可作为热插模块；
 -  支持快速开发 **定时任务**，可视化管理定时任务。
 - 支持**微信小程序**、**微信公众号**、**微信开放平台**、**微信支付**快速集成与运用。
 - 支持**全栈式开发**、**前后端分离**两种开发方式，分别采用Shiro与JWT进行校验框架。
 
## 二、客户案例

截止2020年，Weiit团队基于自身框架已经成功应用为300多家企业提供技术支持。Weiit团队还自身开源了一个产品项目，蜗店SAAS电商系统。

《蜗店》线上环境[https://www.wstore.me](https://www.wstore.me)。 

《蜗店》目前已下线，weiit新版saas升级版本环境演示：
https://www.wei-it.com/





## 三、Maven仓库

必须依赖，框架核心支持：
```maven
 <dependency>
    <groupId>com.weiit</groupId>
    <artifactId>weiit-support-core</artifactId>
    <version>1.0.0</version>
</dependency>
```

需第三方支持与工具类，则引入，非必须依赖：
```maven
 <dependency>
    <groupId>com.weiit</groupId>
    <artifactId>weiit-resource-common</artifactId>
    <version>1.0.0</version>
</dependency>
```

如需定时任务是，则引入，非必须依赖：
```maven
 <dependency>
    <groupId>com.weiit</groupId>
    <artifactId>weiit-suppor-task</artifactId>
    <version>1.0.0</version>
</dependency>
```

如需系统管理模块，则引入，非必须依赖：
```maven
 <dependency>
    <groupId>com.weiit</groupId>
    <artifactId>weiit-business-system</artifactId>
    <version>1.0.0</version>
</dependency>
```


## 四、代码示例
Mapper层：
```java
package com.weiit.web.admin.user.mapper;

import java.util.List;

import com.weiit.core.entity.FormMap;
import com.weiit.core.mapper.BaseMapper;

/**
 * 用户中心 mapper
 * @author 半个鼠标
 * @date 2017年8月15日 下午3:20:16
 * @version 1.0
 * @company http://www.wei-it.com
 */

public interface UserMapper extends BaseMapper {
    
    /**
     * 查询所有的会员积分信息
     * @param param 
     * @return 返回所有会员的积分信息
     */
    List selectUserIntergralList(FormMap formMap);

    /**
     * 查询所有的会员余额信息
     * @param param 
     * @return 返回所有会员的余额信息
     */
    List selectbalanceList(FormMap forMap);

    /**
     * 查询所有的会员地址信息
     * @param param 
     * @return 返回所有会员的地址信息
     */
    List selectAdressList(FormMap formMap);
	
   /**
     * 删除会员地址库
     * @param param 
     * @return 
     */
    int removeAddress(FormMap formMap);
	
   /**
     * 删除会员余额记录
     * @param param 
     * @return 
     */
    int removeBalanceLog(FormMap formMap);
	
   /**
     * 删除会员积分记录
     * @param param 
     * @return 
     */
    int removeIntegralLog(FormMap formMap);

}
```
Service层：
```java
package com.weiit.web.admin.user.service;

import java.util.List;

import com.weiit.core.entity.FormMap;
import com.weiit.core.service.BaseService;

/**
 * 用户模块接口
 * @author 半个鼠标
 * @date 2017年8月15日 上午11:55:25
 * @version 1.0
 * @company http://www.wei-it.com 
 */
public interface UserService extends BaseService{

	List selectUserIntergralList(FormMap formMap);

	List selectbalanceList(FormMap forMap);
	
	List selectAdressList(FormMap formMap);
	
	void removeTrans(FormMap formMap);
}

```

Service实现层：
```java
package com.weiit.web.admin.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.weiit.core.entity.FormMap;
import com.weiit.core.mapper.BaseMapper;
import com.weiit.core.service.impl.AbstractService;
import com.weiit.web.admin.user.mapper.UserMapper;
import com.weiit.web.admin.user.service.UserService;

/**
 * 会员模块接口实现接口
 * @author 半个鼠标
 * @date 2017年8月15日 下午3:23:04
 * @version 1.0
 * @company http://www.wei-it.com
 */
@Service
@Transactional(readOnly=false)
public class UserServiceImpl extends AbstractService implements UserService {

	@Resource
	private UserMapper userMapper;

	@Override
	public BaseMapper setMapper() {
		return userMapper;
	}

	/**
	 * 查询会员积分
	 */
	@Override
	public List selectUserIntergralList(FormMap formMap) {
		return userMapper.selectUserIntergralList(formMap);
	}

	/**
	 * 查询会员余额
	 */
	@Override
	public List selectbalanceList(FormMap forMap) {
		return userMapper.selectbalanceList(forMap);
	}

	/**
	 * 查询会员地址
	 */
	@Override
	public List selectAdressList(FormMap formMap) {
		return userMapper.selectAdressList(formMap);
	}

	/**
	 * 会员删除 (事务管理) 1、删除积分记录。2、删除余额记录。3、删除收货地址。4、删除会员信息
	 */
	@Override
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED,rollbackFor=Exception.class)//事务可写，记住方法中不能捕获异常，捕获异常不会回滚事务，trycatch也不能用
	public void removeTrans(FormMap formMap) {
		//1、先删除积分记录
		userMapper.removeIntegralLog(formMap);
		//2、删除余额记录
		userMapper.removeBalanceLog(formMap);
		//3、删除会员收货地址
		userMapper.removeAddress(formMap);
		//4、删除会员
		userMapper.remove(formMap);
	}
	
	
}

```

控制层（全栈式开发）：
```java
package com.weiit.web.admin.user.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.support.common.utils.WeiitUtil;
import com.weiit.web.admin.user.service.UserService;
import com.weiit.web.base.AdminController;
import com.weiit.web.base.Message;

/**
 * 后台管理 》会员模块 
 * @author 半个鼠标
 * @date：2017年8月15日 下午3:20:56
 * @version 1.0
 * @company http://www.wei-it.com
 */

@Controller
@RequestMapping("/user")
public class UserController extends AdminController {
	
	public static Logger logger = Logger.getLogger(UserController.class);
	
	@Resource
	private UserService userService;
	
	private String page_list = "/center/user/userList";
	
	private String page_intergral = "/center/user/userIntergral";
	
	private String redirect_list = "redirect:userList";
	
	private String page_balance = "/center/user/userBalance";
	
	private String page_adress = "/center/user/userAdress";

	/**
	 * 会员管理》查询用户列表
	 */
	@RequestMapping("/userList")
	public ModelAndView userList() {
		logger.info("进入UserController-user_list,会员列表展示");
		
		ModelAndView result = UIView(page_list);
		FormMap formMap = getFormMap();
		// 开启分页
		PageHelper.startPage(formMap.getPage(), formMap.getRows());
		List userslist = userService.selectList(formMap);
		result.addObject("pageInfo", new PageInfo<E>(userslist));
		result.addObject("queryParam", formMap);
		return result;

	}

	/**
	 * 会员管理》刪除会员
	 */
	@RequestMapping("/remove")
	public ModelAndView remove() {
		logger.info("进入UserController-remove,会员删除");
		
		ModelAndView result = UIView(redirect_list, Message.DANGER);
		FormMap formMap = getFormMap();
		//用户删除【事务】
		userService.removeTrans(formMap);
		return result;
	}

	/**
	 * 会员管理》添加会员
	 */
	@RequestMapping("/save")
	public ModelAndView save() {
		logger.info("进入UserController-save,添加会员");
		
		ModelAndView result = UIView(redirect_list, Message.INFO);
		FormMap formMap = getFormMap();
		if (StringUtils.isEmpty(formMap.getStr("user_id"))) {
			userService.insert(formMap);
		} else {
			userService.edit(formMap);
		}
		return result;
	}

	/**
	 * 会员管理》根据id查询会员
	 */

	@ResponseBody
	@RequestMapping("/view")
	public String view() {
		logger.info("进入UserController-view,根据id查询会员");
		
		FormMap formMap = getFormMap();
		E e = userService.selectOne(formMap);
		return toJsonAPI(e);
	}

	/**
	 * 会员管理》查询用户积分
	 */
	@RequestMapping("/userIntergral")
	public ModelAndView userIntergral() {
		logger.info("进入UserController-user_intergralList,会员积分查询");
		
		ModelAndView result = UIView(page_intergral);
		FormMap formMap = getFormMap();
		// 开启分页
		PageHelper.startPage(formMap.getPage(), formMap.getRows());
		List intergrallist = userService.selectUserIntergralList(formMap);
		result.addObject("pageInfo", new PageInfo<E>(intergrallist));
		result.addObject("queryParam", formMap);
		return result;
	}

	/**
	 * 会员管理》查询用户余额
	 */
	@RequestMapping("/userBalance")
	public ModelAndView userBalance() {
		logger.info("进入UserController-user_balanceList,会员余额展示");
		
		FormMap forMap = getFormMap();
		List balancelist = userService.selectbalanceList(forMap);
		ModelAndView result = UIView(page_balance);
		result.addObject("pageInfo", new PageInfo<E>(balancelist));
		result.addObject("queryParam", forMap);
		return result;
	}

	/**
	 *会员管理》查询会员地址
	 */
	@RequestMapping("/userAddress")
	public ModelAndView userAddress() {
		logger.info("进入UserController-useradressList,会员地址展示");
		
		ModelAndView result = UIView(page_adress);
		FormMap formMap = getFormMap();
		// 开启分页
		PageHelper.startPage(formMap.getPage(), formMap.getRows());
		List adresslist = userService.selectAdressList(formMap);
		result.addObject("pageInfo", new PageInfo<E>(adresslist));
		result.addObject("queryParam", formMap);
		return result;
	}

	/**
	 * 会员管理》批量删除会员
	 * */
	@RequestMapping(value="/removeBatch")
	public ModelAndView removeBatch(){
		logger.info("进入UserController-deleteBatch,批量删除会员");
		
		ModelAndView result = UIView(redirect_list, Message.INFO);
		FormMap formMap=getFormMap();
		String[] validate_id=formMap.getAttrNames("validate_id");//
		String validate_ids=WeiitUtil.arrayToString(validate_id);
		formMap.set("validate_ids", validate_ids);
		userService.removeBatch(formMap);
		return result;
	} 

}
```

控制层（前后端分离开发）：
```java
package com.weiit.web.admin.user.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.weiit.core.entity.E;
import com.weiit.core.entity.FormMap;
import com.weiit.support.common.utils.WeiitUtil;
import com.weiit.web.admin.user.service.UserService;
import com.weiit.web.base.AdminController;
import com.weiit.web.base.Message;

/**
 * 会员相关接口 
 * @author 半个鼠标
 * @date：2017年8月15日 下午3:20:56
 * @version 1.0
 * @company http://www.wei-it.com
 */

@Controller
@RequestMapping("/user")
public class UserController extends AdminController {
	
	public static Logger logger = Logger.getLogger(UserController.class);
	
	

	/**
	 * 查询用户接口
	 */
	@RequestMapping("/getUserInfo")
	public String getUserList() {
	    logger.info("进入UserController-getUserInfo,查询用户信息");
		
	      //判断用户token真实的，重新查询用户信息出来
               if (formMap.get("user_id") != null) {
                    E userInfo = userService.userInfo(formMap);
                    return toJsonAPI(userInfo);
                } else {
                    return toJsonAPI(ApiResponseCode.TOKEN_INVALID);
               }

	}

	

}
```


## 更多支持

weiit开源支持


![avatar](https://cdn.tikcos.cn/saas/weiit5613741909255/237-237_1653999214682.jpeg)

