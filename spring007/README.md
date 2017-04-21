# spring DI的基本使用

## setter注入
```java
public class Person {
    private String name;
	public void setName(String name) {
        this.name = name;
    }
}
```
这样就可以在xml中注册
```xml
 <bean id="person" class="com.catfish.Person">
        <property name="name" value="小美"/>
    </bean>
```
使用的时候添加`@Autowired`注解就可以直接获取到注入的值了

## 构造器注入
```java
 public Person(Integer age, Integer sex) {
        this.age = age;
        this.sex = sex;
    }
```
注入
```xml
<bean id="person" class="com.catfish.Person">
        <constructor-arg name="age" value="10"/>
        <constructor-arg name="sex" value="0"/>
    </bean>
```
## 集合注入
> spring可以注入List,Set,Map,数组

### List
```java
private List<Book> mathBooks;
public void setMathBooks(List<Book> mathBooks) {
        this.mathBooks = mathBooks;
    }
```
对应注入
```xml
<property name="mathBooks">
            <list>
                <bean class="com.catfish.Book">
                    <property name="name" value="数学1"></property>
                </bean>
                <bean class="com.catfish.Book">
                    <property name="name" value="数学2"></property>
                </bean>
            </list>
        </property>
```
### set
只需要把list换成set就行
### map
对应注入
```xml
<property name="map">
            <map>
                <entry key="key1" value="1"/>
                <entry key="key2" value="2"/>
            </map>
</property>
```

### 数组
数组注入方法和list相同或者也可以使用`array`进行注入

## bean互相依赖
```xml
<bean id="book" class="com.catfish.Book">
      <property name="name" value="其他书"/>
</bean>
<property name="book">
      <ref bean="book"/>
</property>    
```