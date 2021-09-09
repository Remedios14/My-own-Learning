# jQuery 介绍
就是 JavaScript 和查询 (Query) ，是辅助 JavaScript 开发的 js 类库。
它的核心思想是 write less, do more ，所以实现了很多浏览器的兼容问题
现已成为最流行的 JavaScript 库

## 核心函数
$ 是 jQuery 的核心函数， $() 就是在调用这个函数
1. 传入参数为 \[function] 时：
    表示页面加载完成之后，相当于 window.onload = function(){}
2. 传入参数为 \[HTML字符串] 时:
    会为我们创建这个 HTML 对象，可用 appendTo 方法加入其他元素中
3. 传入参数为 \[选择器字符串] 时：
    \$("#id 属性值");     id 选择其，根据 id 查询标签对象
    \$(".class 属性值");  类型选择器，可以根据 class 属性查询标签对象
    \$("标签名name");     标签名选择器，根据制定的标签名查询标签对象
4. 传入参数为 \[DOM 对象] 时：
    会把这个 DOM 转换为jQuery 对象

## 对象区分
DOM 对象：
    - 通过 \*.get\*()方法获取的标签对象
        - 通过 createElement() 方法创建的对象
        - Alert 表现效果： \[object HTML*Element]

jQuery 对象：
    - 通过 jQuery 的 API 创建出来的对象
    - 通过 jQuery 包装的 DOM 对象
    - 通过 jQuery 的 API 查询到的对象
    - Alert 表现效果： \[object Object]

本质： jQuery 对象是 DOM 对象的数组 + jQuery 提供的一系列功能函数

区别： 两者没有父子关系，互相不能调用对方的方法

转换： \$(DOM 对象) 就获取了 jQuery 对象； jQuery 对象\[index] 就获取了其中的 DOM 对象