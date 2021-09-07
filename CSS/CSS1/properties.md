# 在这里按类别介绍一些基本属性

<u>所有属性都是 prop : val_1 [val_2  val_3]; 格式给出，仅在 CSS 中编写时不用双引号</u>

<u>数值类型的属性值一般可以给出带单位(如 px )的值，也可给出百分比的值<u/>


## 背景
- background-color
  - 颜色的定义方式：
      - 十六进制 - 如："#ff0000"
      - RGB - 如： "rgb(255,0,0)"  —— 也可使用 rgba ，其中的 a 值表示透明度
      - 颜色名称 - 如："red"
- background-image
  - 給出 url("dir_if/pic_name.gif");
  - 默认会延水平或垂直方向平铺，配合以下的 repeat 属性来设置
- background-repeat
  - repeat-x  水平平铺 、  repeat-y  垂直平铺  、  no-repeat  不平铺，配合 position
- background-attachment
  - 用于指定图像是否随页面变化，如 fixed 指定固定位置，即使滚动也保持 position
- background-position
  - 给出如： left[center\right] top[center\bottom] 、x% y% 、 xpos ypos 等

- 以上属性可直接在 background 中按固定顺序给出，无需每个单读写



## 文本

- color - 颜色，可传值类型同上，改变字体颜色
- text-align - 对齐方式， center、right 等，当设为 justify 时每一行宽度相等
- text-decoration - 装饰，看上去比较丑，设为 none 时取消超链接的下划线
- text-transform - 转换，指定字母大小写或首字母大写
- text-indent - 缩进，指定文本第一行的缩进
- letter-spacing - 行内各字符(每个字符都是)间距，设为负会重叠在一起
- line-height - 行高
- direction - 设置文本方向，如 rtl 表示从右到左
- word-spacing - 行内单词之间的间距（同一个单词在一块儿，可能是根据空格来识别的）
- unicode-bidi - 设置或返回文本是否被重写
- vertical-align - 设置元素的垂直对齐，用于元素交叉使用时
- white-space - 设置元素中空白的处理方式
- text-shadow - 设置文本阴影



## 字体

- font-family - 设置文本的字体系列，通常设置几个字体作为“后备”
- font-size - 设置文本字体大小
  - 可以是绝对大小和相对大小，普通文本大小 16px (像素)
  - 其他单位如 em = px/16 ，且 em 可以在所有浏览器中调整大小，而 px 不行
  - tip：给 body 标签设置百分比的大小，其内元素设置绝对大小实现<b>随时缩放</b>
- font-style - 指定斜体文字
- font-variant - 以小型大写字体或者正常字体显示文本
- font-weight - 指定字体的粗细
- 类同背景可以在 font 属性中一次性依序指定以上的元素



## 链接

链接可以有四种状态，分别设置不同的样式：

- a:link - 正常，未访问过的链接
- a:visited - 用户已访问过的链接
- a:hover - 当用户鼠标放在链接上时
- a:active - 链接被点击的那一刻

其中 a:active 必须在 a:hover 后面，而 后者又必须在前两者后面

具体样式内容按照 text 的设置即可



## 列表

可分别针对无序列表(ul)、有序列表(ol)来设定

- list-style-type - 指定无序表的形状或者有序表的序号类型
- list-style-position - 设置列表中列表项标志的位置
- list-style-image - 将图像设置为列表项标志
- 也可直接在 list-style 之中按顺序指定以上值



## 表格

表格由三种标签组成，都可以设置相应的属性和通用的属性

通用

- border: 1px solid black;   - 一次给出粗细，样式和颜色
- weight、height

table

- border-collapse - 设置表格的边框是否被折叠成一个单一的边框或隔开

th、td

- text-align - 水平对齐方式
- vertical-align - 垂直对齐方式
- paddding - 边框和表格内容之间的间距
- color / background-color - 分别设置内容(文字)、填充颜色



## 盒子模型

暂略



## 边框Border

几乎任何元素都可以设置边框属性

- border-width
- border-style (required)
- border-color
- 可以一次性按照以上顺序在 border 内给出

- border-[top \ bottom \ left \ right]- 可以分别给不同方向边框给出属性，用到再查吧



## 轮廓 outline

是绘制于元素周围的一条线，位于边框边缘的外围

按照 color 、style、width 顺序一次给出或分别写



## 外边距 margin

暂略



## 填充 padding

和 margin 是相对的，向内的边距



## 嵌套选择器<挺重要>

- ```p{}``` : 为所有 **p** 元素指定一个样式
- ```.marked{}``` : 为所有 **class="marked"** 的元素指定一个样式
- ```.marked p{}``` : 为所有 **class="marked"** 元素内的 **p** 元素指定一个样式。
- ```p.marked{}``` : 为所有 **class="marked"** 的 **p** 元素指定一个样式



## 尺寸 Dimension

没什么特别的，暂略



## 显示 Display 与 可见性 Visibility

暂略



## Position(定位)

五种定位： static、relative、fixed、absolute、sticky ，一般都要配合 top 、right 等属性给出值

简介：

- static - 是 HTML 元素的默认值，即没有定位，遵循正常的文档流对象
- relative - 相对其正常位置进行位移
- fixed - 相对于浏览器窗口是固定位置
- absolute - 相对于最近的已定位父元素，若无则相对于 \<html>
- sticky - (粘性定位)基于用户的滚动位置来定位



## 布局 Overflow

用于控制内容溢出元素框时显示的方式，只作用于**指定高度**的块元素上，实际会加上子滚动条



## Float (浮动)

会使元素向左或向右移动，其周围的元素也会重新排列（并非动态，而是显示位置相对编写位置变化）



## 组合选择符

- 后代选择器(以空格``` ```分隔)
- 子元素选择器(以大于```>```号分隔）
- 相邻兄弟选择器（以加号```+```分隔）
- 普通兄弟选择器（以波浪号```～```分隔）



## 伪类 (Pseudo-classes)

伪类的语法：

```javascript
selector:pseudo-class {property:value;}
```

CSS 类中的伪类

```css
selector.class:pseudo-class {property:value;}
```

有点复杂，以后补充

