### Restful API
- 用URL描述资源
- 使用HTTP方法描述行为。使用Http状态码来标识不同的结果
- 使用json交互数据
- Restful只是一种风格，并非强制标准。

### 常用注解
- @RestController 标明此Controller提供RestAPI
- @RequestMapping 映射http请求url到java方法
- @RequestParam 映射请求参数到java方法的参数
    - required : 是否必填
    - name / value : 指定参数的名字
    - defaultValue : 默认参数
- @PageableDefault 指定分页参数默认值
    - direction
    - page
    - size
    - sort
    - value
    - Use Pageable (in Spring Data)
    ```
    param: "size" : "12",
           "page" : "2",
           "sort" : "age,desc"
    sort double : ?sort=firstname&sort=lastname,asc       
    ```
- @PathVariable
    - name / value : 指定变量名
    - required : 得到
- @JsonView 使用步骤
    - 使用接口来声明多个视图(不同场景)
        Sample in User.java
    - 在值对象的get方法上指定视图
    - 在Controller方法上指定视图
- @RequestBody 映射请求体到java方法的参数
- @Valid注解和BindingResult验证请求参数的合法性并处理请求
    - @NotBlank
    - 场景：传入参数的空判断，优化代码结构，不需要每次都进行判断，使用注解简化
    - 注：使用前需要在传入对象前加@Valid


#### 补充
- 通过反射对象打印toString
```
ReflectionToStringBuilder.toString(obj, ToStringStyle.MULTI_LINE_STYLE);
```
- MockMvc JsonPath 表达式
    - src: github.com/json-path/JsonPath
    - $ : 整个查询的根元素
- 日期类型参数的处理：使用时间戳
- StringUtils
```
    StringUtils.isBlank(obj);
    ...
```