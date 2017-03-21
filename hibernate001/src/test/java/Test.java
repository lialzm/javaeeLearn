import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

/**
 * Created by apple on 16/12/18.
 */

public class Test {

    @org.junit.Test
    public void queryDepartment() {
        new ManageEmployee().queryDepartment();
    }

    @org.junit.Test
    public void testQueryEmployee() {
        new ManageEmployee().queryEmp();
    }

    @org.junit.Test
    public void queryDepartment2() {
        new ManageEmployee().queryDepartment2();
    }

    @org.junit.Test
    public void queryDepartment3() {
        new ManageEmployee().queryDepartment3();
    }

    @org.junit.Test
    public void queryEmployee4() {
        new ManageEmployee().queryEmployee4();
    }

    @org.junit.Test
    public void testLoadEmp() {
        new ManageEmployee().loadEmp();
    }

    @org.junit.Test
    public void testSubSelect() {
        new ManageEmployee().subSelect();
    }

    @org.junit.Test
    public void testSqlQuery() {
        new ManageEmployee().sqlQuery();
    }

    @org.junit.Test
    public void testSqlQuery2() {
        new ManageEmployee().sqlQuery2();
    }

    @org.junit.Test
    public void testAddEntity() {
        new ManageEmployee().addEntity();
    }

    @org.junit.Test
    public void testAddEntity2() {
        new ManageEmployee().addEntity2();
    }

    @org.junit.Test
    public void testInsterTestUUid() {
        ManageEmployee manageEmployee = new ManageEmployee();
        manageEmployee.insterTestUUid("4028308259167d5b0159167d5c740001");
    }

    @org.junit.Test
    public void TestinsterMap() {
        ManageEmployee manageEmployee = new ManageEmployee();
        manageEmployee.insterMap();
    }

    @org.junit.Test
    public void testfilter() {
        ManageEmployee manageEmployee = new ManageEmployee();
        manageEmployee.filter();
    }

    @org.junit.Test
    public void testHaving() {
        ManageEmployee manageEmployee = new ManageEmployee();
        manageEmployee.having();
    }

    @org.junit.Test
    public void queryResultMap() {
        ManageEmployee manageEmployee = new ManageEmployee();
        manageEmployee.queryResultMap();
    }

    @org.junit.Test
    public void queryResultMap2() {
        ManageEmployee manageEmployee = new ManageEmployee();
        manageEmployee.queryResultMap2();
    }

    @org.junit.Test
    public void testCriteria() {
        CriteriaTest manageEmployee = new CriteriaTest();
        manageEmployee.testCriteria();
    }


    @org.junit.Test
    public void testInterceptor() {
        ManageEmployee manageEmployee = new ManageEmployee();
        manageEmployee.testInterceptor();
    }

    @org.junit.Test
    public void testaa() {
        ManageEmployee manageEmployee = new ManageEmployee();
        manageEmployee.aa();
    }

    @org.junit.Test
    public void transHqlToSql() {
        ManageEmployee manageEmployee = new ManageEmployee();
        String sql = manageEmployee.getCountFromHql("select e.id from Employee e where e.salary=:sa");
        System.out.println(sql);
    }

    @org.junit.Test
    public void getCountFromHql() {
        ManageEmployee manageEmployee = new ManageEmployee();
        Map<String, Object> map = new HashedMap();
        map.put("sa", 29);
        for (int i = 0; i < 11; i++) {
            Integer count = manageEmployee.getCountFromHql("select e.id from Employee e where e.salary=:sa", map);
            System.out.println(count);
            System.out.println("i==" + i);
        }

    }

    @org.junit.Test
    public void insterEmployee2() {
        ManageEmployee manageEmployee = new ManageEmployee();
        manageEmployee.insterEmployee2();
    }

    @org.junit.Test
    public void inseterAddressTest() {
        ManageEmployee manageEmployee = new ManageEmployee();
        manageEmployee.inseterAddress();
    }


    @org.junit.Test
    public void inseterRoleTest() {
        ManageEmployee manageEmployee = new ManageEmployee();
        manageEmployee.inseterRole();
    }

    @org.junit.Test
    public void criteria2Test() {
        CriteriaTest test = new CriteriaTest();
        test.criteria2();
    }
    @org.junit.Test
    public void criteria3Test() {
        CriteriaTest test = new CriteriaTest();
        test.criteria3();
    }

}
