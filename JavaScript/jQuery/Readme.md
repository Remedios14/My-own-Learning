# jQuery 介绍
就是 JavaScript 和查询 (Query) ，是辅助 JavaScript 开发的 js 类库。
它的核心思想是 write less, do more ，所以实现了很多浏览器的兼容问题
现已成为最流行的 JavaScript 库

## 核心函数
\$ 是 jQuery 的核心函数， \$() 就是在调用这个函数
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



## 选择器

以下选择器在标准 JavaScript 中也都通用，这里只是可以 \$(“selector”) 传入字符串便捷实现

**基础选择器**(可以作为基本元素组成后续的复杂选择器)：

- #id - 通过 id 属性值搜索
- element - 根据指定的元素名匹配所有元素，一般得到对象列表
- .class - 根据类匹配元素，一般得到一个对象列表
- \* - 匹配所有元素，多用于结合上下文来搜索
- selector1, selector2, ... , selectorN - 将每一个选择其匹配到的元素合并为列表返回（列表内元素顺序按照 html 文档内元素出现顺序而非选择器顺序）

**层级选择器**：

- ancestor descendant - 用空格分隔，在给定祖先元素下匹配所有的后代元素
  - a d:nth-child( i / even / odd ) - 匹配父元素下的第 i 个子元素或奇偶元素(从 1 开始)
  - a d:first-child - 匹配第一个子元素(为每一个父元素匹配一个子元素)
  - a d:last-child - 匹配最后一个子元素
  - a d:only-child - 如果某个元素是父元素中唯一的子元素，那将会被匹配
- parent > child - 在给定的父元素下匹配所有的子元素
- prev + next - 匹配所有紧接在 prev 元素后的 next 元素(必须相邻，同辈)
- prev ~ siblings - 匹配 prev 元素之后的所有 siblings 元素(找同辈的)

**基本过滤选择器**：(as for any selector，是可加可不加的)

- as:first - 获取匹配的第一个元素
- as:last - 获取匹配的最后一个元素
- as1:not(as2) - 获取匹配 as1 中排除匹配 as2 的所有元素
- as:even - 获取列表中偶数索引的元素，从 0 开始计数
- as:odd - 获取奇数索引
- as:eq(index) - 获取给定索引的元素
- as:gt(index) - gt for greater than
- as:lt(index) - lt for less than
- :header - 匹配所有 h 标题元素
- :animated - 匹配所有正在执行动画效果的元素
- :hidden - 匹配所有不可见元素，或者 type 为 hidden 的元素
- :visible - 匹配所有的可见元素

**内容过滤选择器**：

- :contains(text) - 匹配包含给定文本的元素
- :empty - 匹配所有不包含子元素或者文本的空元素
- :parent - 匹配含有子元素或者文本的元素（与上面相对）
- :has(selector) - 匹配含有选择器所匹配的元素的元素（含有选择器匹配作为 child 的元素）

**属性过滤选择器**：(as for attribute selector)

- \[attribute] - 匹配包含给定属性的元素
- \[attribute=value] - 匹配给定属性是某个特定值的元素
- \[attribute!=value] - 匹配所有不含指定属性或属性值不等于特定值的元素
- \[attribute^=value] - 匹配给定的属性值以特定值开始的元素
- \[attribute\$=value] - 匹配给定的属性值以特定值结束的元素
- \[attribute\*=value] - 匹配给定的属性值以包含特定值的元素
- \[as1]\[as2]... - 复合属性选择器，给出多个属性过滤条件同时过滤

**表单过滤选择器**：

- :input - 匹配所有 input, textarea, select 和 button 元素
- :text - 匹配所有的单行文本框
- :password - 匹配所有密码框
- :radio - 匹配所有单选按钮
- :checkbox - 匹配所有复选框
- :submit - 匹配所有提交按钮
- :image - 匹配所有图像域
- :reset - 匹配所有重置按钮
- :button - 匹配所有按钮
- :file - 匹配所有文件域
- :enabled - 匹配所有可用元素
- :disabled - 匹配所有不可用元素
- :checked - 匹配所有选中的被选中元素（复选框、单选框等，不包括 select 中的 opt）
- :selected - 匹配所有选中的 option 元素



## 元素的筛选

一般都作为 DOM 对象或 jQuery 对象的方法来使用，未写参数的通常也可以用表达式过滤

- eq(i) - 获取给定索引的元素，和 :eq() 一样
- first() - 获取地一个元素，和 :first 一样
- last() - 获取最后一个元素，和 :last 一样
- filter(exp) - 筛选出与指定表达式匹配的元素集合，用逗号分隔多个表达式，例如 :even
- is(exp) - 判断是否匹配给定的选择器，只要有一个匹配就返回 true
- has(exp) - 保留包含特定后代的元素，和 :has 一样
- not(exp) - 删除**?**匹配选择器的元素，和 :not 一样
- children(exp) - 返回匹配给定选择器的资源素，和 parent > child 一样
- find(exp) - 返回匹配给定选择器的后代元素，和 a d 一样
- next() - 返回当前元素的下一个兄弟元素
- nextAll() - 返回当前元素后面所有的兄弟元素
- nextUntil(exp) - 返回当前元素到指定匹配的元素为止的后面元素
- parent() - 返回父元素
- prev(exp) - 返回当前元素的上一个兄弟元素
- prevAll() - 返回当前元素前面所有的兄弟元素
- prevUntil(exp) - 返回当前元素到指定匹配的元素为止的前面元素
- siblings(exp) - 返回所有兄弟元素
- add() - 把 add 匹配的选择器元素添加到当前 jQuery 对象中



## 属性操作

不传参数是获取，传递文本参数是设置：

- html() - 可以设置和获取起始标签和结束标签中的内容，和 DOM 的 innerHTML 一样
  - 若传入符合 HTML 标签格式的文本会转换成相应的标签
- text() - 可以设置和获取起始标签和结束标签中的文本，和 DOM 的 innerTEXT 一样
  - 只会当成整体字符串不会进行转换
- val() - 可以设置和获取<u>**表单项**</u>的 value 属性值，和 DOM 的 value 一样
  - 可用于批量操作 单选、复选和下拉选框中的选项，传入希望选中的 value 的值构成的 \[列表]

获取以及修改元素的属性（传一个“属性名”参数是获取，传入“属性名”和“修改值”两个参数或“键值对”是修改）：

- attr() - 设置或返回被选元素的属性值，对于一些元素自带属性未进行定义会 undefined  如
  - 不推荐操作 checked、readOnly、selected、disabled 等
  - 还可以操作非标准的——自定义属性
- prop() - 效果同上
  - 只推荐操作 checked、readOnly、selected、disabled 等



## 增删改操作

增操作：

- 向元素内添加子元素
  - a.append(b) - 向 a 元素中插入 b 作为最后一个子元素
  - a.prepend(b) - 向 a 元素中插入 b 作为第一个子元素
  - a.appendTo(b) - 把 a 元素插入成为 b 的最后一个子元素
  - a.prependTo(b) - 把 a 元素插入成为 b 的第一个子元素
- 添加兄弟元素
  - a.after(b) - 在 a 元素后方添加 b 兄弟元素
  - a.before(b) - 在 a 元素前方添加 b 兄弟元素
  - a.insertAfter(b) - 向 b 元素后方添加 a 作为兄弟元素
  - a.insertBefore(b) - 向 b 元素前方添加 a 作为兄弟元素

替换(改)操作：

- a.replaceWith(b) - 用 b 替换掉所有的 a
- a.replaceAll(b) - 用 a 替换掉所有的 b

删除操作：

- a.remove() - 删除 a 标签(连带标签本身)
- a.empty() - 清空 a 标签中的内容(子元素)

## CSS 样式操作

- addClass() - 添加样式
- removeClass() - 删除样式
- toggleClass() - 有就删除，没有就添加样式
- css() - 访问或设置匹配元素的样式属性，可以传入样式名查询或带上要设置的值
- offset() - 获取匹配元素在当前视口的相对偏移，返回对象包含两个属性：top 和 left
  - 只对可见元素有效
  - 可以传入键值对来进行设置，如 offset({top:100, left:50})



样式没有直接修改内容的操作，所以一般需要修改样式时先 remove 然后再添加目标样式 add



## 动画操作

基本动画：

- show() - 将**隐藏**的元素显示，仅对隐藏元素有效
- hide() - 将**可见**的元素隐藏，仅对可见元素有效
- toggle() - 可见就隐藏，不可见就显示

以上动画方法都可以添加参数：

	1. 第一个参数是动画执行的时长，以毫秒为单位
 	2. 第二个参数是动画的回调函数(——完成后自动调用的函数，对其他动作也都有回调函数)

淡入淡出动画：

- fadeIn() - 淡入，令隐藏元素淡入
- fadeOut() - 淡出，令可见元素淡出
- fadeTo() - 在指定时长内慢慢将透明度修改到指定的值，0 为完全透明
- fadeToggle() - 淡入/淡出切换



## 事件操作

### 页面加载事件的性质

```js
$(function () {})
window.onload = function () {}
```

触发的顺序：

	1. jQuery 页面加载完成之后先执行
 	2. 原生 js 页面加载完成之后后执行

触发时刻：

	1. jQuery 的页面加载完成之后是浏览器的内核解析完页面的标签创建好 DOM 对象之后就会马上执行
 	2. 原生 js 的页面加载完成之后，除了要等待浏览器内核解析完标签创建好 DOM 对象，还要等标签显示时需要的内容(如 iframe 连接和 image 爬取的非本地图片)加载完成(不论成功或失败)

执行的次数：

	1. 原生 js 的页面加载完成之后，只会执行最后一次的赋值函数(因为可以看成重复进行赋值，只取最后值)
 	2. jQuery 的页面加载完成之后是全部把注册的 function 全部顺序执行

### 其他事件的处理方法

可以传入函数对象绑定事件，也可以在其他位置获取对象执行方法但不传入参数以触发事件

click()、mouseover()、mouseout()、

- bind() - 可以一次性为对象绑定不少于一个事件
  - bind(“click”, function() {}) - 绑定一个事件
  - bind(“click mouseover ...”, function() {}) - 多个事件绑定同一个函数
  - bind({click: function1() {}, mouseover: function2() {}, ...})  - 分别绑定函数
- one() - 使用上和 bind() 一样，但是该方法绑定的事件只会响应一次
- unbind() - 跟 bind() 方法相反，解除事件的绑定，传入事件名即可，不传就全删
- live() - 也是用来绑定事件，可以用来绑定选择器匹配的所有元素的事件，哪怕这个元素是之后动态创建出来的也有效

### 事件冒泡

指父子元素同时监听同一个事件，当触发子元素的事件的时候，同一个事件也被传递到了父元素的事件中

——例如 父子元素都有 click 事件，单击子元素会触发子元素事件后触发父元素事件



如何阻止：在子元素事件函数体内，`return false;` 即可组织事件的冒泡传递

### 事件对象

是封装有触发的事件信息的一个 javascript 对象，在触发事件的时候都会创建



如何获取：在给元素绑定事件的时候，在事件的 `function(event)` 参数列表中添加一个参数(一般命名 event) 就能得到事件处理函数的事件对象，赋值给 event 对象，可以在函数体内调用



对于 bind 一次绑定多个同效事件，可以获取 event 对象然后检查 type 属性值来对应
