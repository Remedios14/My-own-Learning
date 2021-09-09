// onload 事件动态注册，window对象是固定写法
<<<<<<< HEAD
window.onload = function() {
    document.getElementById("pre01").onload = function() {
        alert("动态注册的 onload 事件");
    }
=======
// window 对象可能在没有 iframe 元素时不存在，所以去掉之后才生效
document.getElementById("pre1").onload = function () {
    alert("动态注册的 onload 事件");
>>>>>>> 941aae295d8034c742b91f360ffb8e6bdd7b8d19
}


onclick_func = function () {
    alert("静态注册 onclick 事件");
}
<<<<<<< HEAD
document.getElementById("btn01").onclick = function () {
    alert("动态注册 onclick 事件");
}
window.onload = function() {
    // document 是 JavaScript 语言提供的一个对象（文档）
    var btnObj = document.getElementById("btn01");

    btnObj.onclick = function() {
        alert("动态注册 onclick 事件");
    }
=======


// document 是 JavaScript 语言提供的一个对象（文档）
var btnObj = document.getElementById("btn01");
//alert(btnObj);
btnObj.onclick = function () {
    alert("动态注册 onclick 事件");
>>>>>>> 941aae295d8034c742b91f360ffb8e6bdd7b8d19
}

// 静态注册失去焦点事件
function onblurfun() {
    // console 是控制台对象，是 JavaScript 语言提供，专门用来向浏览器的控制台打印输出，用于测试使用
    // log() 是打印方法
    // 浏览器内按 F12 可以找到控制台
    console.log("静态注册失去焦点事件！");
}

// 动态注册 onblur 事件
<<<<<<< HEAD
window.onload = function() {
    // 1 获取标签对象
    var input = document.getElementById("dynamic_input");
    // alert(input);
    // 2 通过标签对象.事件名 = function(){};
    input.onblur = function() {
        console.log("动态注册失去焦点事件！");
    }
}

=======
// 1 获取标签对象
var input = document.getElementById("dynamic_input");
// alert(input);
// 2 通过标签对象.事件名 = function(){};
input.onblur = function () {
    console.log("动态注册失去焦点事件！");
}


>>>>>>> 941aae295d8034c742b91f360ffb8e6bdd7b8d19
function onchangefun() {
    console.log("静态注册 onchange 改变事件");
}

<<<<<<< HEAD
window.onload = function() {
    var select_obj = document.getElementById("select01");
    select_obj.onchange = function() {
        console.log("动态注册 onchange 改变事件");
    }
}

=======

var select_obj = document.getElementById("select01");
select_obj.onchange = function () {
    console.log("动态注册 onchange 改变事件");
}


>>>>>>> 941aae295d8034c742b91f360ffb8e6bdd7b8d19
function onsubmitfun() {
    alert("静态注册表单提交事件……发现不合法");
    // 注意这里是返回了 false 值，但没有阻止 submit 操作，需要在标签内再加 return 后调用函数
    return false;
}

<<<<<<< HEAD
window.onload = function() {
    var submitObj = document.getElementById("submit01");
    submitObj.onsubmit = function() {
        // 这里就不用再去加 return 来阻止提交
        alert("动态注册表单提交事件……发现不合法");
        return false;
    }
}
=======

var submitObj = document.getElementById("submit01");
submitObj.onsubmit = function () {
    // 这里就不用再去加 return 来阻止提交
    alert("动态注册表单提交事件……发现不合法");
    return false;
}
>>>>>>> 941aae295d8034c742b91f360ffb8e6bdd7b8d19
