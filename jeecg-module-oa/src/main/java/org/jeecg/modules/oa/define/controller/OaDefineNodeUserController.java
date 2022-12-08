package org.jeecg.modules.oa.define.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.oa.define.entity.OaDefineNodeUser;
import org.jeecg.modules.oa.define.service.IOaDefineNodeUserService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 流程节点执行人
 * @Author: jeecg-boot
 * @Date:   2022-11-23
 * @Version: V1.0
 */
@Api(tags="流程节点执行人")
@RestController
@RequestMapping("/oa/define/oaDefineNodeUser")
@Slf4j
public class OaDefineNodeUserController extends JeecgController<OaDefineNodeUser, IOaDefineNodeUserService> {
	@Autowired
	private IOaDefineNodeUserService oaDefineNodeUserService;
	
	/**
	 * 分页列表查询
	 *
	 * @param oaDefineNodeUser
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "流程节点执行人-分页列表查询")
	@ApiOperation(value="流程节点执行人-分页列表查询", notes="流程节点执行人-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<OaDefineNodeUser>> queryPageList(OaDefineNodeUser oaDefineNodeUser,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<OaDefineNodeUser> queryWrapper = QueryGenerator.initQueryWrapper(oaDefineNodeUser, req.getParameterMap());
		Page<OaDefineNodeUser> page = new Page<OaDefineNodeUser>(pageNo, pageSize);
		IPage<OaDefineNodeUser> pageList = oaDefineNodeUserService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param oaDefineNodeUser
	 * @return
	 */
	@AutoLog(value = "流程节点执行人-添加")
	@ApiOperation(value="流程节点执行人-添加", notes="流程节点执行人-添加")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_node_user:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody OaDefineNodeUser oaDefineNodeUser) {
		oaDefineNodeUserService.save(oaDefineNodeUser);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param oaDefineNodeUser
	 * @return
	 */
	@AutoLog(value = "流程节点执行人-编辑")
	@ApiOperation(value="流程节点执行人-编辑", notes="流程节点执行人-编辑")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_node_user:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody OaDefineNodeUser oaDefineNodeUser) {
		oaDefineNodeUserService.updateById(oaDefineNodeUser);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "流程节点执行人-通过id删除")
	@ApiOperation(value="流程节点执行人-通过id删除", notes="流程节点执行人-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_node_user:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		oaDefineNodeUserService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "流程节点执行人-批量删除")
	@ApiOperation(value="流程节点执行人-批量删除", notes="流程节点执行人-批量删除")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_node_user:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.oaDefineNodeUserService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "流程节点执行人-通过id查询")
	@ApiOperation(value="流程节点执行人-通过id查询", notes="流程节点执行人-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<OaDefineNodeUser> queryById(@RequestParam(name="id",required=true) String id) {
		OaDefineNodeUser oaDefineNodeUser = oaDefineNodeUserService.getById(id);
		if(oaDefineNodeUser==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(oaDefineNodeUser);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param oaDefineNodeUser
    */
    //@RequiresPermissions("org.jeecg.modules.oa:oa_define_node_user:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, OaDefineNodeUser oaDefineNodeUser) {
        return super.exportXls(request, oaDefineNodeUser, OaDefineNodeUser.class, "流程节点执行人");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("oa_define_node_user:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, OaDefineNodeUser.class);
    }

}
