# API列表

======

## 1、通用接口

+ 1、登录
  - [接口地址]： /api/login
  - [请求方式]：POST
  - [返回示例]：
  
  ```json
   {
   	"code":"200",
   	"date":[
   		{
   		    "succ":"true"
   		}
   	],
   	"message":"login success"
   }
  ```
  - [参数列表]：

    |  参数名  |       含义       |  类型  |            取值范围            |
    | :------: | :--------------: | :----: | :----------------------------: |
    |    id    | 学号或者教师编号 | String |               -                |
    | password |       密码       | String |               -                |
    | usertype |     用户类型     | String | student\|teacher\|administator |


- 1、通过用户id获取用户信息

  - [请求地址]：/api/user/{id}

  - [请求方式]：get

  - [返回示例]：

  ```json
  {
      "code":"200",
      "date":{
          "college":"dxxy",
          "id":20150201007,
          "password":"123456",
          "userName":"zhh"
      },
      "message":"query successful"
  }
  ```
- 2、通过用户的ID获取用户的菜单
  - [请求地址]：/api/menu/{id}
  - [请求方式]：get
  - [返回方式]:
  
