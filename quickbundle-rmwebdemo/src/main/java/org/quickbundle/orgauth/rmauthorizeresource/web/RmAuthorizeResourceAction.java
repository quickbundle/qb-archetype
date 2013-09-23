//代码生成时,文件路径: e:/download/QbRmWebDemo/src/main/java/orgauth/rmauthorizeresource/web/RmAuthorizeResourceAction.java
//代码生成时,系统时间: 2010-11-27 22:08:42
//代码生成时,操作系统用户: Administrator

/*
 * 系统名称:单表模板 --> QbRmWebDemo
 * 
 * 文件名称: org.quickbundle.orgauth.rmauthorizeresource.web --> RmAuthorizeResourceAction.java
 * 
 * 功能描述:
 * 
 * 版本历史: 2010-11-27 22:08:42 创建1.0.0版 (白小勇)
 *  
 */

package org.quickbundle.orgauth.rmauthorizeresource.web;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.quickbundle.base.beans.factory.RmBeanFactory;
import org.quickbundle.base.cache.RmSqlCountCache;
import org.quickbundle.base.web.page.RmPageVo;
import org.quickbundle.orgauth.cache.RmAuthorizeCache;
import org.quickbundle.orgauth.rmauthorize.vo.RmAuthorizeVo;
import org.quickbundle.orgauth.rmauthorizeresource.service.IRmAuthorizeResourceService;
import org.quickbundle.orgauth.rmauthorizeresource.util.IRmAuthorizeResourceConstants;
import org.quickbundle.orgauth.rmauthorizeresource.vo.RmAuthorizeResourceVo;
import org.quickbundle.project.IGlobalConstants;
import org.quickbundle.project.RmProjectHelper;
import org.quickbundle.third.struts.RmActionHelper;
import org.quickbundle.third.struts.actions.RmDispatchAction;
import org.quickbundle.tools.helper.RmJspHelper;
import org.quickbundle.tools.helper.RmPopulateHelper;
import org.quickbundle.tools.helper.RmSqlHelper;
import org.quickbundle.tools.helper.RmVoHelper;
import org.springframework.jdbc.core.RowMapper;

/**
 * 功能、用途、现存BUG:
 * 
 * @author 白小勇
 * @version 1.0.0
 * @see 需要参见的其它类
 * @since 1.0.0
 */

public class RmAuthorizeResourceAction extends RmDispatchAction implements IRmAuthorizeResourceConstants {

    /**
     * 得到Service对象
     * 
     * @return Service对象
     */
    public IRmAuthorizeResourceService getService() {
        return (IRmAuthorizeResourceService) RmBeanFactory.getBean(SERVICE_KEY);  //得到Service对象,受事务控制
    }

    /**
     * 从页面表单获取信息注入vo，并插入单条记录
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response 
     * @return
     * @throws Exception
     */
    public ActionForward insert(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RmAuthorizeResourceVo vo = new RmAuthorizeResourceVo();
        RmPopulateHelper.populate(vo, request);  //从request中注值进去vo
        RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
        String id = getService().insert(vo);  //插入单条记录
        request.setAttribute(IGlobalConstants.INSERT_FORM_ID, id);  //新增记录的id放入request属性
        return mapping.findForward(FORWARD_TO_QUERY_ALL);
    }

    /**
     * 从页面的表单获取单条记录id，并删除单条记录
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response 
     * @return
     * @throws Exception
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int deleteCount = getService().delete(request.getParameter(REQUEST_ID));  //删除单条记录
        request.setAttribute(EXECUTE_ROW_COUNT, deleteCount);  //sql影响的行数放入request属性
        return mapping.findForward(FORWARD_TO_QUERY_ALL);
    }

    /**
     * 从页面的表单获取多条记录id，并删除多条记录
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response 
     * @return
     * @throws Exception
     */
    public ActionForward deleteMulti(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] id = RmJspHelper.getArrayFromRequest(request, REQUEST_IDS);  //从request获取多条记录id
        int deleteCount = 0;  //定义成功删除的记录数
        if (id != null && id.length != 0) {
            deleteCount = getService().delete(id);  //删除多条记录
        }
        request.setAttribute(EXECUTE_ROW_COUNT, deleteCount);  //sql影响的行数放入request属性
        return mapping.findForward(FORWARD_TO_QUERY_ALL);
    }

    /**
     * 从页面的表单获取单条记录id，查出这条记录的值，并跳转到修改页面
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response 
     * @return
     * @throws Exception
     */
    public ActionForward find(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        detail(mapping, form, request, response);
        return mapping.findForward(FORWARD_UPDATE_PAGE);
    }

    /**
     * 从页面表单获取信息注入vo，并修改单条记录
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response 
     * @return
     * @throws Exception
     */
    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RmAuthorizeResourceVo vo = new RmAuthorizeResourceVo();
        RmPopulateHelper.populate(vo, request);  //从request中注值进去vo
        RmVoHelper.markModifyStamp(request,vo);  //打修改时间,IP戳
        int count = getService().update(vo);  //更新单条记录
        request.setAttribute(EXECUTE_ROW_COUNT, count);  //sql影响的行数放入request属性
        return mapping.findForward(FORWARD_TO_QUERY_ALL);
    }
    
    /**
     * 批量保存，没有主键的insert，有主键的update
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response 
     * @return
     * @throws Exception
     */
    public ActionForward insertUpdateBatch(ActionMapping mapping, ActionForm form, final HttpServletRequest request, HttpServletResponse response) throws Exception {
    	List<RmAuthorizeResourceVo> lvo = RmPopulateHelper.populateAjax(RmAuthorizeResourceVo.class, request);
    	for(RmAuthorizeResourceVo vo : lvo) {
    		if(vo.getId() != null && vo.getId().trim().length() > 0) {
    			RmVoHelper.markModifyStamp(request, vo);
    		} else {
    			RmVoHelper.markCreateStamp(request, vo);
    		}
    	}
    	int[] sum_insert_update = getService().insertUpdateBatch(lvo.toArray(new RmAuthorizeResourceVo[0]));
    	request.setAttribute(REQUEST_OUTPUT_OBJECT, sum_insert_update);
        return mapping.findForward(FORWARD_TO_QUERY_ALL);
    }

    /**
     * 简单查询，分页显示，支持表单回写
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response 
     * @return
     * @throws Exception
     */
    public ActionForward simpleQuery(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IRmAuthorizeResourceService service = getService();
        String queryCondition = getQueryCondition(request);  //从request中获得查询条件
        RmPageVo pageVo = RmJspHelper.transctPageVo(request, getCount(queryCondition));
        String orderStr = RmJspHelper.getOrderStr(request);  //得到排序信息
        List<RmAuthorizeResourceVo> beans = service.queryByCondition(queryCondition, orderStr, pageVo.getStartIndex(), pageVo.getPageSize());  //按条件查询全部,带排序
        //如果采用下边这句，就是不带翻页的，同时需要删掉list页面的page.jsp相关语句
        //beans = service.queryByCondition(queryCondition, orderStr);  //查询全部,带排序
        RmJspHelper.saveOrderStr(orderStr, request);  //保存排序信息
        request.setAttribute(REQUEST_QUERY_CONDITION, queryCondition);
        request.setAttribute(REQUEST_BEANS, beans);  //把结果集放入request
        request.setAttribute(REQUEST_WRITE_BACK_FORM_VALUES, RmVoHelper.getMapFromRequest((HttpServletRequest) request));  //回写表单
        return mapping.findForward(FORWARD_LIST_PAGE);
    }

    /**
     * 查询全部记录，分页显示，支持页面上触发的后台排序
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response 
     * @return
     * @throws Exception
     */
    public ActionForward queryAll(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute(REQUEST_QUERY_CONDITION, "");
        simpleQuery(mapping, form, request, response);
        return mapping.findForward(FORWARD_LIST_PAGE);
    }

    /**
     * 从页面的表单获取单条记录id，并察看这条记录的详细信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response 
     * @return
     * @throws Exception
     */
    public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        RmAuthorizeResourceVo bean = getService().find(request.getParameter(REQUEST_ID));  //通过id获取vo
        request.setAttribute(REQUEST_BEAN, bean);  //把vo放入request
        if(RM_YES.equals(request.getParameter(REQUEST_IS_READ_ONLY))) {
            request.setAttribute(REQUEST_IS_READ_ONLY, request.getParameter(REQUEST_IS_READ_ONLY));
        }
        return mapping.findForward(FORWARD_DETAIL_PAGE);
    }
	
    
    /**
     * 功能: 从request中获得查询条件
     *
     * @param request
     * @return
     */
    protected String getQueryCondition(HttpServletRequest request) {
        String queryCondition = null;
        if(request.getAttribute(REQUEST_QUERY_CONDITION) != null) {  //如果request.getAttribute中有，就不取request.getParameter
            queryCondition = request.getAttribute(REQUEST_QUERY_CONDITION).toString();
        } else {
			List<String> lQuery = new ArrayList<String>();
			
				lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".authorize_id", request.getParameter("authorize_id"), RmSqlHelper.TYPE_CUSTOM, "='", "'"));
				
				lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".old_resource_id", request.getParameter("old_resource_id"), RmSqlHelper.TYPE_CHAR_LIKE));
				
				lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".default_access", request.getParameter("default_access"), RmSqlHelper.TYPE_CUSTOM, "='", "'"));
				
				lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".default_is_affix_data", request.getParameter("default_is_affix_data"), RmSqlHelper.TYPE_CUSTOM, "='", "'"));
				
				lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".default_is_recursive", request.getParameter("default_is_recursive"), RmSqlHelper.TYPE_CUSTOM, "='", "'"));
				
				lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".default_access_type", request.getParameter("default_access_type"), RmSqlHelper.TYPE_CHAR_LIKE));
				
				lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".total_code", request.getParameter("total_code"), RmSqlHelper.TYPE_CHAR_LIKE));
				
				lQuery.add(RmSqlHelper.buildQueryStr(TABLE_NAME + ".name", request.getParameter("name"), RmSqlHelper.TYPE_CHAR_LIKE));
				
			queryCondition = RmSqlHelper.appendQueryStr(lQuery.toArray(new String[0]));
        }
        return queryCondition;
    }
    
    /**
     * 得到缓存中查询条件对应的count(*)记录数，如返回-1则执行查询
     * 
     * @param queryCondition
     * @return
     */
    protected int getCount(String queryCondition) {
    	int count = RmSqlCountCache.getCount(TABLE_NAME, queryCondition);
    	if(count < 0) {
    		count = getService().getRecordCount(queryCondition);
    		RmSqlCountCache.putCount(TABLE_NAME, queryCondition, count);
    	}
    	return count;
    }

    /**
     * 功能: 插入中间表RM_AUTHORIZE_RESOURCE_RECORD数据
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward insertRm_authorize_resource_record(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String bs_keyword = request.getParameter("bs_keyword");
		String old_resource_id = request.getParameter("old_resource_id");

    	String[] party_ids = request.getParameter("party_ids").split(",");
    	int count = getService().insertRm_authorize_resource_record(bs_keyword, old_resource_id, party_ids).length;
    	return RmActionHelper.getForwardInstanceWithAlert("/orgauth/rmauthorizeresource/middle/listRm_authorize_resource_record.jsp?bs_keyword=" + bs_keyword + "&old_resource_id=" + old_resource_id, "插入了" + count + "条记录!");
    }
    
    /**
     * 功能: 删除中间表RM_AUTHORIZE_RESOURCE_RECORD数据
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteRm_authorize_resource_record(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String bs_keyword = request.getParameter("bs_keyword");
    	RmAuthorizeVo authorize = RmAuthorizeCache.getAuthorizeByBs_keyword(bs_keyword);
    	String old_resource_id = request.getParameter("old_resource_id");
    	String authorize_resource_id = RmProjectHelper.getCommonServiceInstance().queryForObject("select id from rm_authorize_resource  where authorize_id=" + authorize.getId() + " and old_resource_id='" + old_resource_id + "'", new RowMapper() {
			public Object mapRow(ResultSet resultset, int i) throws SQLException {
				return resultset.getString("id");
			}
		}).toString();
    	String[] party_ids = request.getParameter("party_ids").split(",");
    	int count = getService().deleteRm_authorize_resource_record(authorize_resource_id, party_ids);
    	return RmActionHelper.getForwardInstanceWithAlert("/orgauth/rmauthorizeresource/middle/listRm_authorize_resource_record.jsp?bs_keyword=" + bs_keyword + "&old_resource_id=" + old_resource_id, "删除了" + count + "条记录!");
    }

    /**
     * 授权资源保存或更新
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward updateDefaultAccess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String bs_keyword = request.getParameter("bs_keyword");
        String old_resource_id = request.getParameter("old_resource_id");
        RmAuthorizeResourceVo vo = new RmAuthorizeResourceVo();
        RmPopulateHelper.populate(vo, request);  //从request中注值进去vo
        RmVoHelper.markCreateStamp(request,vo);  //打创建时间,IP戳
        vo.setAuthorize_id(RmAuthorizeCache.getAuthorizeByBs_keyword(bs_keyword).getId());
        vo.setOld_resource_id(old_resource_id);
        vo.setDefault_is_affix_data("0");
        vo.setDefault_access_type("11");
        vo.setDefault_is_recursive("1");
        
        List lResource = getService().queryByCondition("AUTHORIZE_ID='" + RmAuthorizeCache.getAuthorizeByBs_keyword(bs_keyword).getId() + "' AND OLD_RESOURCE_ID='" + old_resource_id + "'", null);
        if(lResource.size() > 0) {
        	RmAuthorizeResourceVo resourceVo = (RmAuthorizeResourceVo)lResource.get(0);
            vo.setId(resourceVo.getId());
            getService().update(vo);  //更新
        } else {
            //String id = 
            getService().insert(vo);  //插入单条记录
        }
        return RmActionHelper.getForwardInstance("","/RmAuthorizeResourceAction.do?cmd=findDefaultAccess&bs_keyword=" + bs_keyword + "&old_resource_id=" + old_resource_id,true);
    }
    
    public ActionForward findDefaultAccess(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
    	String bs_keyword = request.getParameter("bs_keyword");
    	String old_resource_id = request.getParameter("old_resource_id");
    	request.setAttribute("bs_keyword", bs_keyword);
    	request.setAttribute("old_resource_id", old_resource_id);
        return RmActionHelper.getForwardInstance("", "/orgauth/rmauthorizeresource/initRmResource.jsp", false);
    }
}