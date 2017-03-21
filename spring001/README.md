# springmvc 参数绑定

##  @RequestParam 绑定单个请求
```java
RequestMapping(value = "/form2")
    @ResponseBody
    public String form2(@RequestParam String name){
        logger.info("name=" + name );
        return "";
    }
```
`@RequestParam` 有required(是否必填),defaultValue(默认参数)参数
默认required=true,defaultValue为空
如果name参数不存在则返回400

使用下面这种写法则required默认为false
```java
 @RequestMapping(value = "/form")
    @ResponseBody
    public String form(String name, Integer age) {
        logger.info("name=" + name + "," + "age=" + age);
        return "";
    }
```
`@RequestParam`还支持数组形式
```java
@RequestMapping(value = "/names")
    @ResponseBody
    public String names(@RequestParam String[] name) {
        logger.info("name=" + Arrays.asList(name));
        return Arrays.asList(name).toString();
    }
```
对应请求/names.do?name=张三,李四
或者/names.do?name=张三&name=李四


## @PathVariable 绑定uri变量值

```java
@RequestMapping("/user/{id}")
    @ResponseBody
    public String getUser(@PathVariable Long id) {
        return String.valueOf(id);
    }
```
请求/user/1返回id为1

## @CookieValue 绑定cookie的值

```java
 @RequestMapping("/getCookie")
    @ResponseBody
    public String getCookie(@CookieValue("JSESSIONID") String sessionId) {
        return sessionId;
    }
```

## @ModelAttribute绑定参数到命令对象

1. 绑定请求参数到对象

先定义两个对象
Role
```java
public class Role implements Serializable {

    private String id;
}
```
User
```java
public class User implements Serializable {

    private String id;

    private Role role;
}
```

```java
 @RequestMapping("/getUserByModel")
    @ResponseBody
    public String getUserByModel(@ModelAttribute("user") User user) {
        logger.info(user.toString());
        return "";
    }
```
对应请求/getUserByModel.do?id=123&role.id=1

2. 暴露表单引用对象为模型数据

```java
 @ModelAttribute("user")
    public User getUser(String userId) {
        User user = new User();
        user.setId("11");
        Role role=new Role();
        role.setId("123");
        user.setRole(role);
        return user;
    }
    
     @RequestMapping("/getUserModel")
    @ResponseBody
    public String getUserModel(@ModelAttribute User user) {
        return user.toString();
    }
```
请求/getUserModel?id=1
返回 User{id='1', role=Role{id='123'}}
使用`@ModelAttribute`注释的方法会在所有Controller方法前运行
因为先运行了`@ModelAttribute`注解的方法,生成了一个user对象,之后user对象的id属性被修改为1

## @SessionAttributes绑定命令对象到session

springmvc提供了`@SessionAttributes`用来对会话数据的存取
添加session
```java
 @RequestMapping("/setSession")
    @ResponseBody
    public String setSession(
            HttpSession session) {
        City city = new City();
        city.setCityName("shanghai");
        session.setAttribute("city", city);
        return city.toString();
    }
```
获取session数据
```java
//在类头部添加需要的session属性
@Controller
@SessionAttributes(value = {"city"})
public class TestController {
 @RequestMapping("/getSession")
    @ResponseBody
    public String getSession(@ModelAttribute City city) {
        return city.toString();
    }
}
```
清除本次会话session
```java
 @RequestMapping("/clearSession")
    @ResponseBody
    public String clearSession(SessionStatus status) {
        status.setComplete();
        return "success";
    }
```


## @RequestBody绑定请求的内容区数据并能进行自动类型转换等
使用RequestBody可以将json转化为对象
```java
 @RequestMapping("/getUserByJson")
    @ResponseBody
    public String getUserByJson(@RequestBody User user){
        return user.toString();
    }
```
发送json格式报文
```json
{
	"id":"123"
}
```
返回结果User{id='123', role=null}

## @RequestPart绑定“multipart/data”数据

```java
 @RequestMapping("/getFile")
    @ResponseBody
    public String getFile(@RequestPart("image") MultipartFile file){
        System.out.println(file.getName()+","+file.getSize());
        return "success";
    }
```
