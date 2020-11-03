package com.xgf.web.controller.system.dept;

import com.github.pagehelper.PageInfo;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.xgf.domain.msg.Msg;
import com.xgf.domain.system.dept.Dept;
import com.xgf.service.system.dept.IDeptService;
import com.xgf.web.controller.system.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/system/dept")
public class DeptController extends BaseController {
    @Autowired
    IDeptService deptService;

    //日志记录
    private static final Logger l = LoggerFactory.getLogger(DeptController.class);

    //查询部门信息，并返回部门列表页面，@RequestParam的defaultValue设置默认值
    @RequestMapping(path = "/toList", method = {RequestMethod.GET, RequestMethod.POST})
    public String toList(Model model, @RequestParam(defaultValue = "1") Integer curr,
                         @RequestParam(defaultValue = "5") Integer pageSize) {
        l.info("toList 当前页curr = " + curr);//当前第几页
        l.info("toList 每页数据量pageSize = " + pageSize);//每页记录数
        l.info("toList 部门所属公司companyId = " + super.getLoginCompanyId());//指定公司id
        //查询一个分页
        PageInfo<Dept> pi = deptService.findByPage(curr, pageSize, super.getLoginCompanyId());

        l.info("toList pi = " + pi);
        model.addAttribute("pi", pi);
        return "system/dept/dept-list";
    }


    //跳转增加部门页面
    @RequestMapping(path = "/toAdd", method = {RequestMethod.GET, RequestMethod.POST})
    public String toAdd(Model model) {
        //需要为下拉菜单查询出所有的部门，一个部门对应一个选项
        //根据companyId查询出部门，不做分页
        l.info("toAdd companyId=" + super.getLoginCompanyId());
        List<Dept> list = deptService.findAll(super.getLoginCompanyId());

        l.info("toAdd list=" + list);
        model.addAttribute("list", list);
        return "system/dept/dept-add";
    }

    //增加部门
    @RequestMapping(path = "/add", method = {RequestMethod.POST})
    public String add(Dept dept, String parentId) {
        l.info("add  dept=" + dept);
        l.info("add  parentId=" + parentId);
        //按照登录公司来进行部门管理，先默认公司id是1，对id=1的公司进行部门管理
        dept.setCompanyId(super.getLoginCompanyId());
        dept.setCompanyName(super.getLoginCompanyName());
        Dept parent = new Dept();
        parent.setDeptId(parentId);

        dept.setParent(parent);
        //保存到数据库
        deptService.saveDept(dept);
        return "redirect:/system/dept/toList";
    }


    // 更新回显，跳转更新页面。${path}/system/dept/toUpdate?deptId=${dept.deptId}
    @RequestMapping(path = "/toUpdate", method = {RequestMethod.GET, RequestMethod.POST})
    public String toUpdate(Model model, String deptId) {

        String companyId = super.getLoginCompanyId();
        l.info("toUpdate deptId=" + deptId);

        //查询部门获取部门信息dept
        Dept dept = deptService.findById(deptId);
        l.info("toUpdate dept=" + dept);

        //查询所有的部门
        List<Dept> list = deptService.findAll(companyId);

        //将数据传到前台jsp
        //当前更新的部门信息
        model.addAttribute("dept", dept);
        //所有部门信息，选择上级部门的时候需要遍历所有的部门
        model.addAttribute("list", list);

        return "system/dept/dept-update";
    }

    //更新部门操作，action="${path}/system/dept/update"
    @RequestMapping(path = "/update", method = {RequestMethod.GET, RequestMethod.POST})
    public String update(Dept dept, String parentId) {

        l.info("update 更新部门dept=" + dept);
        l.info("update parentId=" + parentId);

        //当前写死companyId与companyName以后再修改(登录的公司）
        dept.setCompanyName(super.getLoginCompanyName());
        dept.setCompanyId(super.getLoginCompanyId());
        //下拉菜单 - 所有部门信息（选择上级部门）
        Dept parent = new Dept();
        parent.setDeptId(parentId);
        dept.setParent(parent);

        l.info("update dept=" + dept);
        //2 保存到数据库
        deptService.updateDept(dept);
        //修改完成之后跳到列表页面
        return "redirect:/system/dept/toList";
    }


    // 删除部门（ajax异步提交），如果没有下级部门就删除成功，否则删除失败，location.href="${path}/system/dept/delete?depId="+deptId;
    @RequestMapping(path = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody   //加上@ResponseBody返回的才是字符串，不然就是返回的页面了
    public Object delete(String deptId) {
        l.info("delete deptId=" + deptId);

        boolean deleteResult = deptService.deleteDeptById(deptId);
        //ajax异步
        if(deleteResult == true){
            //删除成功
            return Msg.init(200,"部门编号deptId = " + deptId + "的部门删除成功。",null);
        }else {
            return Msg.init(-200,"deptId = " + deptId +"的部门存在下级部门，删除失败，请删除该部门的下级部门后再删除",null);
        }

        /*if(deleteResult == true){
            model.addAttribute("deleteResult","id = " + depId +"的部门删除成功");
        }else {
            model.addAttribute("deleteResult","id = " + depId +"的部门存在下级部门，删除失败，请删除该部门的下级部门后再删除");
        }*/

        //return "redirect:/system/dept/toList";//修改完成之后跳到列表页面
    }

    /*
    @Autowired
    HttpSession httpSession;
     */
    @RequestMapping(path = "/testRequest", method = {RequestMethod.GET,RequestMethod.POST})
    public String testRequest(HttpServletRequest request) {//springmvc会赋值
        l.info("sessionId" + session.getId());
        l.info("testRequest request=" + request.getParameter("age"));
        request.setAttribute("jack", "rose");

        return "result";
    }

}
