package interceptor;

import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;

/**
 * Created by A on 2016/12/23.
 */
public class TestInterceptor extends EmptyInterceptor {

    public static ThreadLocal<String> query = new ThreadLocal<String>();

    @Override
    public String onPrepareStatement(String sql) {
        System.out.println("sql==" + sql);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("select count(*) from (");
        stringBuffer.append(" select employee0_.id from employee employee0_");
        stringBuffer.append(") temp");
        query.set(sql);
        return super.onPrepareStatement(sql);
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        super.afterTransactionCompletion(tx);
        System.out.println("afterTransactionCompletion");
        query.remove();
    }
}
