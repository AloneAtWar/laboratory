package org.jeecg.modules.laboratory.controller;

import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.laboratory.entity.WeeklyReports;
import org.jeecg.modules.laboratory.service.IWeeklyReportsService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

/**
 * @Description: 周报表
 * @Author: jeecg-boot
 * @Date:   2022-12-24
 * @Version: V1.0
 */
@Api(tags="周报表")
@RestController
@RequestMapping("/laboratory/weeklyReports")
@Slf4j
public class WeeklyReportsController extends JeecgController<WeeklyReports, IWeeklyReportsService> {
	@Autowired
	private IWeeklyReportsService weeklyReportsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param weeklyReports
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	//@AutoLog(value = "周报表-分页列表查询")
	@ApiOperation(value="周报表-分页列表查询", notes="周报表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<IPage<WeeklyReports>> queryPageList(WeeklyReports weeklyReports,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<WeeklyReports> queryWrapper = QueryGenerator.initQueryWrapper(weeklyReports, req.getParameterMap());
		Page<WeeklyReports> page = new Page<WeeklyReports>(pageNo, pageSize);
		IPage<WeeklyReports> pageList = weeklyReportsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param weeklyReports
	 * @return
	 */
	@AutoLog(value = "周报表-添加")
	@ApiOperation(value="周报表-添加", notes="周报表-添加")
	//@RequiresPermissions("laboratory:weekly_reports:add")
	@PostMapping(value = "/add")
	public Result<String> add(@RequestBody WeeklyReports weeklyReports) {
		weeklyReportsService.save(weeklyReports);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param weeklyReports
	 * @return
	 */
	@AutoLog(value = "周报表-编辑")
	@ApiOperation(value="周报表-编辑", notes="周报表-编辑")
	//@RequiresPermissions("laboratory:weekly_reports:edit")
	@RequestMapping(value = "/edit", method = {RequestMethod.PUT,RequestMethod.POST})
	public Result<String> edit(@RequestBody WeeklyReports weeklyReports) {
		weeklyReportsService.updateById(weeklyReports);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "周报表-通过id删除")
	@ApiOperation(value="周报表-通过id删除", notes="周报表-通过id删除")
	//@RequiresPermissions("laboratory:weekly_reports:delete")
	@DeleteMapping(value = "/delete")
	public Result<String> delete(@RequestParam(name="id",required=true) String id) {
		weeklyReportsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "周报表-批量删除")
	@ApiOperation(value="周报表-批量删除", notes="周报表-批量删除")
	//@RequiresPermissions("laboratory:weekly_reports:deleteBatch")
	@DeleteMapping(value = "/deleteBatch")
	public Result<String> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.weeklyReportsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	//@AutoLog(value = "周报表-通过id查询")
	@ApiOperation(value="周报表-通过id查询", notes="周报表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<WeeklyReports> queryById(@RequestParam(name="id",required=true) String id) {
		WeeklyReports weeklyReports = weeklyReportsService.getById(id);
		if(weeklyReports==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(weeklyReports);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param weeklyReports
    */
    //@RequiresPermissions("laboratory:weekly_reports:exportXls")
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, WeeklyReports weeklyReports) {
        return super.exportXls(request, weeklyReports, WeeklyReports.class, "周报表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    //@RequiresPermissions("laboratory:weekly_reports:importExcel")
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, WeeklyReports.class);
    }

}
