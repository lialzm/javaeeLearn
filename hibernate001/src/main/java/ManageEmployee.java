import bean.*;
import interceptor.TestInterceptor;
import org.apache.commons.collections.map.HashedMap;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.hql.ast.QueryTranslatorImpl;
import org.hibernate.impl.SessionFactoryImpl;
import org.hibernate.transform.Transformers;

import java.io.File;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by A on 2016/12/2.
 */
public class ManageEmployee {
    private static SessionFactory sessionFactory;

    static {
        File file = new File("/Users/apple/develop/IdeaProjects/javaeeLearn/twoday/src/main/resources/hibernate.cfg.xml");
//        File file = new File("E:\\devtool\\ideaSource\\javaeeLearn\\javaeeLearn\\twoday\\src\\main\\resources\\hibernate.cfg.xml");
        sessionFactory = new Configuration().configure(file).buildSessionFactory();
    }

    /**
     * 普通查询
     */
    public void queryDepartment() {
        String sql = "select t.id from Department t";
        Session session = sessionFactory.openSession();
        Query qq = session.createQuery(sql);
        List<Department> departments = qq.list();
        System.out.println(departments.size());
        session.close();
    }

    /**
     * 占位符
     */
    public void queryDepartment2() {
        String sql = "from Department t where t.name=:name";
        Session session = sessionFactory.openSession();
        //获取事务
        Query qq = session.createQuery(sql);
        List<Department> departments = qq.setParameter("name", "技术部").list();
        System.out.println(departments.size());
        session.close();
    }

    /**
     * 占位符
     */
    public void queryDepartment3() {
        String sql = "from Department t where t.name=?";
        Session session = sessionFactory.openSession();
        //获取事务
        Query qq = session.createQuery(sql);
        List<Department> departments = qq.setParameter(0, "技术部").list();
        System.out.println(departments.size());
        session.close();
    }


    //根据主键获取实体。如果没有相应的实体，抛出异常。
    public void loadEmp() {
        Session session = sessionFactory.openSession();
        //获取事务
        Transaction ts = session.beginTransaction();
        Employee employee = (Employee) session.load(Employee.class, 1);
        System.out.println(employee.getFirstName());
        ts.commit();
        session.close();
    }

    //根据主键获取实体。如果没有相应的实体，返回 null
    public void getEmp() {
        Session session = sessionFactory.openSession();
        Employee employee = (Employee) session.get(Employee.class, 1);
    }

    public void queryEmp() {
        Session session = sessionFactory.openSession();
        //获取事务
        List<Object> employees = session.createQuery("select e.cardEntity.cardNo from Employee  e").list();
        System.out.println(employees);
        session.close();
    }

    /**
     * 关联查询
     */
    public void queryEmployee2() {
        String sql = "select t,(select count(*) from t.employees es where es.departmentId=t.id) as cc  from Department t left join fetch t.employees es2 where es2.salary>10";
        Session session = sessionFactory.openSession();
        //获取事务
        Transaction ts = session.beginTransaction();
       /* Query<Object[]> qq = session.createQuery(sql);
        List<Object[]> dds = qq.getResultList();
        ts.commit();
        Department dddd = (Department) dds.get(0)[0];
        String dddd2 = String.valueOf(dds.get(0)[1]);
        String dddd3 = String.valueOf(dds.get(1)[1]);
        System.out.println(dddd3);*/
    }

    /**
     * group by的使用
     */
    public void queryEmployee3() {
        String sql = "select t,(select count(*) from t.employees es where es.departmentId=t.id and es.salary>10) as cc  from Department t  group by t";
        Session session = sessionFactory.openSession();
        //获取事务
        Transaction ts = session.beginTransaction();
        Query qq = session.createQuery(sql);
        List<Object[]> dds = qq.list();
        ts.commit();
        for (Object[] objects : dds
                ) {
            for (Object ob : objects
                    ) {
                System.out.println(ob);
            }
        }
        session.close();
    }

    public void queryEmployee4() {
        String sql = "select t from Employee t  ";
        Session session = sessionFactory.openSession();
        Query qq = session.createQuery(sql);
        List<Employee> dds = qq.list();
        System.out.println(dds.get(0).getAddressEntities());
        session.close();
    }

    public Integer insterDepartment() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        Department department = new Department();
        department.setName("技术部");
        Integer departmentId = (Integer) session.save(department);
        ts.commit();
        session.close();
        return departmentId;
    }


    public Integer insterEmployee(String fname, String lname, int salary, int depId) {
        Session session = sessionFactory.openSession();
        //获取事务
        Transaction ts = session.beginTransaction();
        Employee employee = new Employee(fname, lname, salary, depId);
        Integer employeeID = (Integer) session.save(employee);
        ts.commit();
        return employeeID;
    }

    public void insterEmployee2() {
        Session session = sessionFactory.openSession();
        //获取事务
        Transaction ts = session.beginTransaction();
        Employee employee = new Employee();
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNo("123");
        employee.setFirstName("张");
        employee.setLastName("三");
        //相互关联
        cardEntity.setEmployee(employee);
        employee.setCardEntity(cardEntity);
        session.save(employee);
        ts.commit();
        session.close();
    }

    /**
     * 设置主键模式后,主键由hibernate指定
     *
     * @param uuid
     */
    public void insterTestUUid(String uuid) {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        TestUuidEntity uuidEntity = new TestUuidEntity();
        uuidEntity.setName("test");
        if (uuid != null) {
            uuidEntity.setId(uuid);
        }
        String uuid2 = (String) session.save(uuidEntity);
        System.out.println(uuid2);
        ts.commit();
    }

    /**
     * 动态模型
     */
    public void insterMap() {
        Session session = sessionFactory.openSession().getSession(EntityMode.MAP);
        Map<String, Object> map = new HashedMap();
        map.put("name", "i am map");
        Transaction transaction = session.beginTransaction();
        session.save("TestMap", map);
        transaction.commit();
    }

    /**
     * 查询结果返回map
     */
    public void queryResultMap() {
        Session session = sessionFactory.openSession().getSession(EntityMode.MAP);
        Transaction transaction = session.beginTransaction();
        String sql = "select new Map(t,(select count(*) from t.employees es where es.departmentId=t.id and es.salary>=10) as cc)  from Department t  group by t";
        List<Map<String, Object>> map = session.createQuery(sql).list();
        System.out.println(map.size());
        for (Map<String, Object> m : map
                ) {
            Department department = (Department) m.get("0");
            System.out.println(department);
            Integer count = (Integer) m.get("cc");
            System.out.println(count);
        }
        transaction.commit();
        session.close();
    }

    /**
     * 原生sql,返回map
     */
    public void queryResultMap2() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT t.name,t.id,(SELECT count(*) FROM employee es WHERE es.department_id=t.id AND es.salary>=10) AS cc  FROM department t  GROUP BY t.id";
        List<Map<String, Object>> map = session.createSQLQuery(sql).setResultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP).list();
        for (Map<String, Object> m : map
                ) {
            System.out.println(m.get("name"));
            System.out.println(m.get("id"));
            BigInteger count = (BigInteger) m.get("cc");
            System.out.println(count);
        }
        transaction.commit();
        session.close();
    }



    public void subSelect() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        String sql = "select count(*) from EmployeeCount ec where ec.salary=10";
        Long count = (Long) session.createQuery(sql).uniqueResult();
        System.out.println(count);
        ts.commit();
        session.close();
    }

    public void sqlQuery() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        String sql = "SELECT t.id,t.name  FROM department t ";
        List<Department> departments = session.createSQLQuery(sql).addScalar("id").addScalar("name").setResultTransformer(Transformers.aliasToBean(Department.class)).list();
        for (Department d : departments
                ) {
            System.out.println(d);
        }
        ts.commit();
        session.close();
    }

    public void sqlQuery2() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        String sql = "SELECT t.id,t.name,count(emp.id) AS count  FROM department t LEFT JOIN EMPLOYEE emp ON t.id=emp.department_id GROUP BY t.id,t.name";
        List<Department> departments = session.createSQLQuery(sql).addScalar("id").addScalar("name").addScalar("count").setResultTransformer(Transformers.aliasToBean(Department.class)).list();
        for (Department d : departments
                ) {
            System.out.println(d);
        }
        ts.commit();
        session.close();
    }

    public void addEntity() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        String sql = "SELECT t.id,t.name  FROM department t ";
        List<Department> departments = session.createSQLQuery(sql).addEntity(Department.class).list();
        for (Department d : departments
                ) {
            System.out.println(d);
        }
        ts.commit();
        session.close();
    }

    public void addEntity2() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        String sql = "SELECT t.id,t.name,count(emp.id) AS count  FROM department t LEFT JOIN EMPLOYEE emp ON t.id=emp.department_id GROUP BY t.id,t.name";
        List<Department> departments = session.createSQLQuery(sql).addEntity(Department.class).list();
        for (Department d : departments
                ) {
            System.out.println(d);
        }
        ts.commit();
        session.close();
    }

    public void filter() {
        Session session = sessionFactory.openSession();
        String sql = "SELECT t.id,t.name  FROM department t ";
        List<Department> departments = session.createSQLQuery(sql).addScalar("id").addScalar("name").setResultTransformer(Transformers.aliasToBean(Department.class)).list();
        Object aa = session.createFilter(departments, "select  this.name").uniqueResult();
        System.out.println(aa);
    }

    public void having() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select e.id from Employee e having count(e)");
        Long aa = (Long) session.createQuery(stringBuffer.toString()).uniqueResult();
        System.out.println(aa);
        ts.commit();
        session.close();
    }

    public void testInterceptor() {
        Session session = sessionFactory.openSession(new TestInterceptor());
        Transaction ts = session.beginTransaction();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(" select e.id from Employee e");
        List<Object[]> id = session.createQuery(stringBuffer.toString()).list();
        System.out.println(id);

        ts.commit();
        session.close();
    }

    public void aa() {
        Session session = sessionFactory.openSession(new TestInterceptor());
        Transaction ts = session.beginTransaction();
        StringBuffer stringBuffer = new StringBuffer();
        HashMap<String, Object> map = new HashMap();
        stringBuffer.append(" select e.id from Employee e where e.salary=:sa");
        Query query = session.createQuery(stringBuffer.toString());
        session.createQuery("select emp from Employee emp").list();
        String sql = TestInterceptor.query.get();
        List<Object[]> id = query.setParameter("sa", 29).list();
        System.out.println(id);
        Object count = session.createSQLQuery("select count(*) from (" + sql + ") tt")
                .setParameter(0, 29).uniqueResult();
        System.out.println(count);
        ts.commit();
        session.close();
    }


    public String getCountFromHql(String hql) {
        // 当hql为null或空时,直接返回null
        if (hql == null || hql.equals("")) {
            return "";
        }
        // 获取当前session
        // 得到session工厂实现类
        SessionFactoryImpl sfi = (SessionFactoryImpl) sessionFactory;
        // 得到Query转换器实现类
        QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(hql, hql, Collections.EMPTY_MAP, sfi);
        queryTranslator.compile(Collections.EMPTY_MAP, false);
        // 得到sql
        String sql = queryTranslator.getSQLString();
        return sql;
    }

    public Integer getCountFromHql(String hql, Map<String, Object> map) {
        // 当hql为null或空时,直接返回null
        if (hql == null || hql.equals("")) {
            return -1;
        }
        // 获取当前session
        // 得到session工厂实现类
        SessionFactoryImpl sfi = (SessionFactoryImpl) sessionFactory;
        // 得到Query转换器实现类
        QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(hql, hql, Collections.EMPTY_MAP, sfi);
        queryTranslator.compile(Collections.EMPTY_MAP, false);
        // 得到sql
        String sql = queryTranslator.getSQLString();
        Session session = sessionFactory.openSession(new TestInterceptor());
        Query query = session.createSQLQuery("SELECT count(*) from (" + sql + ") t");
        Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Object> entry = iterator.next();
            String key = entry.getKey();
            Object value = entry.getValue();
            int[] positions = queryTranslator.getNamedParameterLocs(key);
            for (int position : positions
                    ) {
                query.setParameter(position, value);
            }
        }
        Integer count = Integer.valueOf(query.uniqueResult().toString());
        session.close();
        return count;
    }


    /**
     * 将hql语句转换为sql语句,不需要格式化参数的情况
     *
     * @param hql         要转换的hql语句
     * @param paramValues hql参数值列表,注意与参数的顺序一致
     * @return 可执行的sql语句, 当返回null, 可以通过getResultMsg()方法查看处理结果信息
     */
    public String getCountFromHql(String hql, List paramValues) {
        // 要返回的sql语句
        String sql = getCountFromHql(hql);
        // 当为null或空时,返回null
        if (sql == null || sql.equals("")) {
            return null;
        }
        // 赋参数值
        if (paramValues != null && paramValues.size() > 0) {
            for (int i = 0; i < paramValues.size(); i++) {
                sql = sql.replaceFirst("\\?", "\\'" + paramValues.get(i).toString() + "\\'");
            }
        }
        return sql;
    }

    public void inseterAddress() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        Employee employee = new Employee();
        employee.setFirstName("技术");
        employee.setLastName("1");
        CardEntity cardEntity = new CardEntity();
        cardEntity.setCardNo("111");
        cardEntity.setEmployee(employee);
        employee.setCardEntity(cardEntity);
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddress("火星");
        addressEntity.setEmployee(employee);
        addressEntity.setType(0);
        session.save(employee);
        ts.commit();
        session.close();
    }

    public void inseterRole() {
        Session session = sessionFactory.openSession();
        Transaction ts = session.beginTransaction();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("test");
//新建角色
        RoleEntity roleEntity2 = new RoleEntity();
        roleEntity2.setName("test2");
//新建权限
        PermissionEntity permissionEntity = new PermissionEntity();
        permissionEntity.setName("新建用户");
//权限2
        PermissionEntity permissionEntity2 = new PermissionEntity();
        permissionEntity2.setName("删除用户");

        Set set = new HashSet<RoleEntity>();
        set.add(roleEntity);
        permissionEntity.setRoleEntities(set);

        Set set2 = new HashSet<RoleEntity>();
        set2.add(roleEntity2);
        permissionEntity2.setRoleEntities(set2);

        Set set3 = new HashSet<PermissionEntity>();
        set3.add(permissionEntity);
        set3.add(permissionEntity2);
        roleEntity.setPermissionEntities(set3);

        Set set4 = new HashSet<PermissionEntity>();
        set4.add(permissionEntity2);
        roleEntity2.setPermissionEntities(set4);


        session.save(roleEntity);
        session.save(roleEntity2);
        ts.commit();
        session.close();
    }


}
