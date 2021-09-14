# RegExp 正则

在 JavaScript 中可以用 new RegExp(“string”) 来创建正则表达式对象，而一种更简单的方式是直接 /string/ 给 var 赋值即可，斜杠内的串不需要加双引号

匹配邮箱的常用正则表达式：

`/\b[\w.%+-]+@[\w.-]+\.[a-zA-Z]{2,6}\b/g`

## 一般性规则

/正则表达式主体/修饰符(可选)

修饰符：

- i - 执行对大小写不敏感的匹配
- g - 执行全局匹配(查找所有匹配而非在找到第一个匹配后停止)
- m - 执行多行匹配
- s - 使特殊字符圆点`.`还包含换行符

模式：

- 范围匹配：
  - \[abc] - 查找方括号之间的任何字符，可以 \[a-z] 直接查找所有小写字母
  - \[0-9] - 查找所有从 0 到 9 的数字
  - [^adpg] - 查找给定集合以外的字符
  - (x|y) - 查找任何以 | 分隔的选项，例如'z|food' 能匹配 "z" 或 "food" ，而 '(z|f)ood' 能匹配 "zood" 或 "food"
- 元字符 Metacharacter（拥有特殊含义的字符）：
  - . - 查找除换行和行结束符以外的**单个字符**
  - \w - 查找数字、字母及下划线
  - \W - 查找非字母、数字、下划线
  - \d - 查找数字
  - \D - 查找非数字字符
  - \s - 查找空白字符，包括换行
  - \S - 查找非空白字符
  - \b - 匹配单词边界
  - \B - 匹配非单词边界
  - \0 - 查找 NULL 字符
  - \n - 查找换行符
  - \f - 查找换页符
  - \r - 查找回车符
  - \t - 查找制表符
  - \v - 查找垂直制表符
  - \xxx - 查找以八进制数 xxx 规定的字符
  - \xdd - 查找以十六进制数 dd 规定的字符
  - \uxxxx - 查找以十六进制数 xxxx 规定的 Unicode 字符
  - (pattern) - 匹配 pattern 并获取这一匹配
  - (?:pattern) - 匹配 pattern 但不获取匹配结果
  - (?=pattern) - 正向肯定预查（look ahead positive assert），在任何匹配pattern 的字符串开始处匹配查找字符串。例如，"Windows(?=95|98|NT|2000)"能匹配"Windows2000"中的"Windows"，但不能匹配 "Windows3.1"中的"Windows"。
  - (?!pattern) - 正向否定预查（negative assert），在任何不匹配 pattern 的字符串开始处匹配查找字符串
  - (?<=pattern) - 反向(look behind)肯定预查，与正向肯定预查类似，只是方向相反
  - (?<!pattern) - 反向否定预查
- 量词：（以下的 s 不仅表示但个字符，而表示一个匹配模式）
  - s+ - 匹配任何包含或至少一个 s 的字符串
  - s* - 匹配任何包含零个或多个 s 的字符串
  - s? - 匹配任何包含零个或一个 s 的字符串
  - s{n} - 匹配包含 n 个 s 的序列的字符串
  - s{n,} - 前面的模式 s 连续出现至少 n 次时匹配
  - s{n,m} - 前面的模式 s 连续出现至少 n 次，至多 m 次时匹配
  - s$ - 匹配任何结尾为 s 的字符串
  - ^s - 匹配任何开头为 s 的字符串
  - ?=s - 匹配任何其后紧接指定字符串 s 的字符串
  - ?!s - 匹配任何其后没有紧接指定字符串 s 的字符串
- 通过在 `*`、`+`或`?`限定符之后放置`?`使表达式从“贪婪”转换为“非贪婪”，最小匹配
- /^...$/ - 用于确保单行匹配，一行内完全符合匹配格式时才成立



## Java 中的正则表达式

主要由 java.util.regex 包负责功能，其主要包括以下三个类：

- Pattern 类：

  pattern 对象是一个正则表达式的编译表示。 Pattern 类没有公共构造方法，要创建一个 Pattern 对象必须先调用公共静态编译方法`Pattern.compile(regex)`，该方法接受一个正则表达式作为它的第一个参数，返回一个 Pattern 对象

- Matcher 类：

  Matcher 对象是对输入字符串进行解释和匹配操作的引擎。没有公共构造方法，需要调用 Pattern 对象的 matcher 方法`p.matcher(target)`传入<u>**目标字符串**</u>来获得一个 Matcher 对象

- PatternSyntaxException：

  PatternSyntaxException 是一个非强制异常类，表示一个正则表达式模式中的语法错误

**特别注意**：

```java
// 在 Java 中 \\ 表示：我要插入一个正则表达式的反斜线，所以其后的字符具有特殊的意义
System.out.print("\\");   // 输出为 \
System.out.print("\\\\");   // 输出为 \\
```

`\\\\` 匹配 `\\` ， `\\(` 匹配 `(`

### Matcher 类的方法

#### 索引方法

索引方法提供了有用的索引值，精确表明输入字符串中在哪能找到匹配

- public int start() - 返回以前匹配的初始索引
- public int start(int group) - 返回在以前的匹配操作期间，由给定组所捕获的子序列的初始索引
- public int end() - 返回最后匹配字符之后的索引(末匹配位索引 +1 )
- public int end(int group) - 返回在以前的匹配操作期间，由给定组所捕获子序列的最后字符之后的偏移量。

#### 查找方法

查找方法用来检查输入字符串并返回一个**布尔值**，表示是否找到该模式

- public boolean lookingAt() - 尝试将从区域开头开始的输入序列与该模式匹配
- public boolean find() - 尝试查找与该模式匹配的输入序列的下一个子序列
- public boolean find(int start) - 重置此匹配器，然后尝试查找匹配该模式、从指定索引开始的输入序列的下一个子序列
- public boolean matches() - 尝试将整个区域与模式匹配

#### 替换方法

替换方法是替换输入字符串里文本的方法

- public Matcher appendReplacement(StringBuffer sb, String replacement) - 实现非尾部添加和替换步骤
- public StringBuffer appendTail(StringBuffer sb) - 实现尾部添加和替换步骤
- public String replaceAll(String replacement) - 替换模式与给定替换字符串相匹配的输入序列的每个子序列
- public String replaceFirst(String replacement) - 替换模式与给定替换字符串相匹配的输入序列的第一个子序列
- public static String quoteReplacement(String s) - 返回指定字符串的字面替换字符串。这个方法返回一个字符串，就像传递给Matcher类的 appendReplacement 方法一个字面字符串一样工作。

### PatternSyntaxException 类的方法

是一个非强制异常类，指示一个正则表达式模式中的语法错误

- public String getDescription() - 获取错误的描述
- public int getIndex() - 获取错误的索引
- public String getPattern() - 获取错误的正则表达式模式
- public String getMessage() - 返回多行字符串，包含语法错误及其索引的描述】错误的正则表达式模式和模式中错误索引的可视化指示



## Python 中的正则表达式

主要借助 re 模块来实现匹配等功能



## 针对 JavaScript 中正则对象的方法

- RegExp 对象方法
  - re.exec(str) - 检索字符串中指定的值。返回找到的值，并确定其位置
  - re.test(str) - 检索字符串中指定的值。返回 true 或 false
  - re.toString() - 返回正则表达式的字符串
- RegExp 对象属性
  - cunstructor - 返回一个函数，该函数是一个创建 RegExp 对象的原型
  - global - 判断是否设置了 "g" 修饰符
  - ignoreCase - 判断是否设置了 "i" 修饰符
  - lastIndex - 用于规定下次匹配的起始位置
  - multiline - 判断是否设置了 "m" 修饰符
  - source - 返回正则表达式的匹配模式
- String 对象对正则表达式的方法
  - str.search(re) - 检索与正则表达式相匹配的值
  - str.match(re) - 找到一个或多个正则表达式的匹配
  - str.replace(re) - 替换与正则表达式匹配的子串
  - str.split(re) - 把字符串分隔为字符串数组
