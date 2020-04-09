# VUE
不再操作dom

## 语法
1. v-cloak:指令设置样式，这些样式会在 Vue 实例编译结束时，从绑定的 HTML 元素上被移除。当网络较慢，网页还在加载 Vue.js ，而导致 Vue 来不及渲染，这时页面就会显示出 Vue 源代码。我们可以使用 v-cloak 指令来解决这一问题。

```css
    [v-cloak]{
        display:none
    }
```

2. v-text
    将data中的数据以字符串的形式输出

3. v-html
    将data中的数据转义为HTML输出

4. v-bind
    v-bind：是vue中，提供的用于绑定属性的按钮
    - 语法：v-bind:要绑定的属性，可以简写: :要绑定的属性
    - v-bind中可以写变量+字符串的拼接
  
5. v-on 
    事件绑定机制