package bean;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.usertype.UserType;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**自定义映射类型
 * Created by A on 2016/12/19.
 */
public class TestUseType implements UserType {

    public int[] sqlTypes() {
        return new int[]{Types.VARCHAR};
    }

    public Class returnedClass() {
        return List.class;
    }

    public boolean equals(Object x, Object y) throws HibernateException {
        if (x == y) {
            return true;
        }
        if (x != null && y != null) {
            List xList = (List) x;
            List yList = (List) y;
            if (xList.size() != yList.size()) {
                return false;
            }
            for (int i = 0; i < xList.size(); i++) {
                String str1 = (String) xList.get(i);
                String str2 = (String) yList.get(i);
                if (!str1.equals(str2)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode(Object x) throws HibernateException {
        return 0;
    }


    /**
     * 从result取出内容并处理
     *
     * @param rs
     * @param names
     * @param owner
     * @return
     * @throws HibernateException
     * @throws SQLException
     */
    public Object nullSafeGet(ResultSet rs, String[] names, Object owner) throws HibernateException, SQLException {
        String value = (String) Hibernate.STRING.nullSafeGet(rs, names[0]);
        if (value != null) {
            String[] strings = value.split(";");
            List list = new ArrayList();
            for (String string : strings
                    ) {
                list.add(string);
            }
            return list;
        } else {
            return null;
        }
    }

    /**
     * 处理list并放入相应字段
     *
     * @param st
     * @param value
     * @param index
     * @throws HibernateException
     * @throws SQLException
     */
    public void nullSafeSet(PreparedStatement st, Object value, int index) throws HibernateException, SQLException {
        if (value != null) {
            String str = assemble((List) value);
            Hibernate.STRING.nullSafeSet(st, str, index);
        } else {
            Hibernate.STRING.nullSafeSet(st, value, index);
        }
    }


    private String assemble(List phoneList) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Object phone : phoneList
                ) {
            stringBuffer.append(phone).append(";");
        }
        stringBuffer.substring(0, stringBuffer.length() - 1);
        return stringBuffer.toString();
    }

    /**
     * 创建新的list
     *
     * @param value
     * @return
     * @throws HibernateException
     */
    public Object deepCopy(Object value) throws HibernateException {
        List sourceList = (List) value;
        if (sourceList==null)
            return null;
        List targeList = new ArrayList();
        targeList.addAll(sourceList);
        return targeList;
    }

    /**
     * 本类型实例是否可变
     * @return
     */
    public boolean isMutable() {
        return false;
    }

    public Serializable disassemble(Object value) throws HibernateException {
        return (Serializable) value;
    }

    public Object assemble(Serializable cached, Object owner) throws HibernateException {
        return cached;
    }

    public Object replace(Object original, Object target, Object owner) throws HibernateException {
        return original;
    }
}
