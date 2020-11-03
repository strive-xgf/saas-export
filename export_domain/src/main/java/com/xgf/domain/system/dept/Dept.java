package com.xgf.domain.system.dept;

//部门类
public class Dept {
    //类的成员变量是小驼峰，第二个单词起首字母大写（aaBbCc)
    //数据库是下划线，每个单词小写，使用_隔开（aa_bb_cc)
    //dept_id

    private String deptId;
    private String deptName;
    //private String parentId;      //数据库是上级部门的id
    //特殊（类中有一个类还是自己），上级部门也是Dept类
    private Dept parent;            //在类中，直接用上级部门的类对象（好处：可以直接获取上级部门信息，封装到类中）
    private Integer state;          //部门启用/通用状态
    private String companyId;       //企业id
    private String companyName;     //企业名

    public Dept() {
    }

    public Dept(String deptId, String deptName, Dept parent, Integer state, String companyId, String companyName) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.parent = parent;
        this.state = state;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "deptId='" + deptId + '\'' +
                ", deptName='" + deptName + '\'' +
                ", state=" + state +
                ", companyId='" + companyId + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Dept getParent() {
        return parent;
    }

    public void setParent(Dept parent) {
        this.parent = parent;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
