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
    ```

#### 补充
- 通过反射对象打印toString
```
ReflectionToStringBuilder.toString(obj, ToStringStyle.MULTI_LINE_STYLE);
```
- MockMvc JsonPath 表达式
    - src: github.com/json-path/JsonPath
    - $ : 整个查询的根元素
    