# DOM 模型

DOM 全称是 Document Object Model 文档对象模型

相当于把文档中的标签、属性和文本都转换成为对象来管理，构成一棵树——树的概念有助于理解伪类

Document 对象的理解：

```markdown
1. Document 对象管理了所有 HTML 文档内容
2. Document 是一种树结构的文档，有层级关系
3. 帮助我们把所有的标签都**对象化**
4. 可以通过 Document 访问所有的标签对象
```



## 对象方法

document 是所有 DOM 模型的**根**元素

- getElementById() - 返回指定 ID 的元素
- getElementsByTagName()
- getElementsByClassName()
- appendChild() - 向指定节点添加子节点
- removeChild() - 删除子节点
- replaceChild() - 替换子节点
- insertBefore() - 在指定的子节点前面插入新的子节点(兄弟)
- createAttribute() - 创建属性节点
- createElement() - 创建元素节点
- createTextNode() - 创建文本节点
- getAttribute() - 返回指定的属性值
- setAttribute() - 把指定属性设置或修改为指定的值



## 对象属性

- innerHTML - 用于获取或修改标签的所有子元素(包括文本和子标签等)
- nodeName - 规定节点的名称
  - 只读，始终包含 HTML 元素的大写字母标签名
  - 元素节点的 nodeName 与标签名相同
  - 属性节点的 nodeName 与属性名相同
  - 文本节点的 nodeName 始终是 #text
  - 文档节点的 nodeName 始终是 #document
- nodeValue - 规定节点的值
  - 元素节点的 nodeValue 是 undefined 或 null
  - 文本节点的 nodeValue 是文本本身
  - 属性节点的 nodeValue 是属性值
- nodeType - 返回结点的类型，只读
  - 元素 - 1、属性 - 2、文本 - 3、注释 - 8、文档 - 9

