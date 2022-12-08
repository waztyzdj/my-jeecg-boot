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
import org.jeecg.modules.oa.define.entity.OaDefineFlowNode;
import org.jeecg.modules.oa.define.service.IOaDefineFlowNodeService;

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
 * @Description: 流程节点
 * @Author: jeecg-boot
 * @Date:   2022-11-23
 * @Version: V1.0
 */
@Api(tags="流程节点")
@RestController
@RequestMapping("/oa/define/oaDefineFlowNode")
@Slf4j
public class OaDefineFlowNodeController extends JeecgController<OaDefineFlowNode, IOaDefineFlowNodeService> {
	@Autowired
	private IOaDefineFlowNodeService oaDefineFlowNodeService;
	
	/**
	 * 分页列表查询
	 *
	 * @param oaDefineFlowNode
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "流程节点-分页列表查询")
	@ApiOperation(value="流程节点-分页列表查询", notes="流程节点-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<OaDefineFlowNode>> queryPageList(OaDefineFlowNode oaDefineFlowNode,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<OaDefineFlowNode> queryWrapper = QueryGenerator.initQueryWrapper(oaDefineFlowNode, req.getParameterMap());
		Page<OaDefineFlowNode> page = new Page<OaDefineFlowNode>(pageNo, pageSize);
		IPage<OaDefineFlowNode> pageList = oaDefineFlowNodeService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param oaDefineFlowNode
	 * @return
	 */
	@AutoLog(value = "流程节点-添加")
	@ApiOperation(value="流程节点-添加", notes="流程节点-添加")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_flow_node:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody OaDefineFlowNode oaDefineFlowNode) {
		oaDefineFlowNodeService.save(oaDefineFlowNode);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param oaDefineFlowNode
	 * @return
	 */
	@AutoLog(value = "流程节点-编辑")
	@ApiOperation(value="流程节点-编辑", notes="流程节点-编辑")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_flow_node:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody OaDefineFlowNode oaDefineFlowNode) {
		oaDefineFlowNodeService.updateById(oaDefineFlowNode);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "流程节点-通过id删除")
	@ApiOperation(value="流程节点-通过id删除", notes="流程节点-通过id删除")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_flow_node:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		oaDefineFlowNodeService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "流程节点-批量删除")
	@ApiOperation(value="流程节点-批量删除", notes="流程节点-批量删除")
	//@RequiresPermissions("org.jeecg.modules.oa:oa_define_flow_node:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.oaDefineFlowNodeService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "流程节点-通过id查询")
	@ApiOperation(value="流程节点-通过id查询", notes="流程节点-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<OaDefineFlowNode> queryById(@RequestParam(name="id",required=true) String id) {
		OaDefineFlowNode oaDefineFlowNode = oaDefineFlowNodeService.getById(id);
		if(oaDefineFlowNode==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(oaDefineFlowNode);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param oaDefineFlowNode
    */
    //@RequiresPermissions("org.jeecg.modules.oa:oa_define_flow_node:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, OaDefineFlowNode oaDefineFlowNode) {
        return super.exportXls(request, oaDefineFlowNode, OaDefineFlowNode.class, "流程节点");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("oa_define_flow_node:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, OaDefineFlowNode.class);
    }

}
