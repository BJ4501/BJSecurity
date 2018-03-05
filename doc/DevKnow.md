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
- @ControllerAdvice 
    - 该注解作用是处理其他Controller抛出的异常
    - @ExceptionHandler(class)

### 常用的验证注解 (Hibernate Validator)
- @NotNull 值不能为空
- @Null 值必须为空
- @Pattern(regex=) 字符串必须匹配正则表达式
- @Size(min=,max=) 集合的元素数量必须在min，max之间
- @CreditCardNumber(ignoreNonDigitCharacters=) 字符串必须是信用卡号(美国标准)
- @Email 字符串必须是Email地址
- @Length(min=,max=) 检查字符串的长度
- @NotBlank 字符串必须有字符
- @NotEmpty 字符串不为null，集合有元素
- @Range(min=,max=) 数字必须大于等于min，小于等于max
- @SafeHtml 字符串是安全的Html
- @URL 字符串是合法的URL
- @AssertFalse 值必须是false
- @AssertTrue 值必须是True
- @DecimalMax(value=,inclusive=) 值必须小于等于(inclusive=true)/小于(inclusive=false)value属性指定的值。可以注解在字符串类型的属性上。
- @DecimalMin(value=,inclusive=) 值必须大于等于(inclusive=true)/大于(inclusive=false)value属性指定的值。可以注解在字符串类型的属性上。
- @Digits(integer=,fraction=) 数字格式检查。integer指定整数部分的最大长度，fraction指定小数部分的最大长度。
- @Future 值必须是未来的日期
- @Past 值必须是过去的日期
- @Max(value=) 值必须小于等于value指定的值。不能注解在字符串类型的属性上。
- @Min(value=) 值必须大于等于value指定的值。不能注解在字符串类型的属性上。
- 补充：以上所有注解都有一个共用的参数 message= 用来填写出错时的错误信息。

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
- 错误码信息页实现
> 在resources目录下，创建resources/error两级目录，在里面创建对应错误码的html，
当出现需要该错误码的页面时，就会读取该页面