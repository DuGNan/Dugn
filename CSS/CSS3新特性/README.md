# CSS3新特性
## transition（过渡）
一种样式变化为另一种样式的的效果、过程。通常使用在hover上，Internet Explorer 9 以及更早版本的浏览器不支持 transition 属性

1. 语法
    transition： CSS属性，花费时间，效果曲线(默认ease)，延迟时间(默认0)

2. 属性
    - 可以简写

    - transition-property ：规定设置过渡效果的 CSS 属性的名称
        - all:默认值，所有属性获得过渡效果
        - none:没有属性获得过渡效果
        - property:定义应用过渡效果的 CSS 属性名称列表，列表以逗号分隔

    - transition-duration : 规定完成过渡效果需要多少秒或毫秒。必须填
        - 0：默认值
        - time: 规定完成过渡效果需要花费的时间（以秒或毫秒计）

    - transition-timing-function ：规定速度效果的速度曲线规,该属性允许过渡效果随着时间来改变其速度
        - linear : 规定以相同速度开始至结束的过渡效果（等于 cubic-bezier(0,0,1,1)）
        - ease : 规定慢速开始，然后变快，然后慢速结束的过渡效果（cubic-bezier(0.25,0.1,0.25,1)）
        - ease-in : 规定以慢速开始的过渡效果（等于 cubic-bezier(0.42,0,1,1)）
        - ease-out : 规定以慢速结束的过渡效果（等于 cubic-bezier(0,0,0.58,1)）
        - ease-in-out : 规定以慢速开始和结束的过渡效果（等于 cubic-bezier(0.42,0,0.58,1)
        - cubic-bezier(n,n,n,n)	: 在 cubic-bezier 函数中定义自己的值。可能的值是 0 至 1 之间的数值

    - transition-delay : 过渡效果何时开始
        - 0 : 默认值
        - time : 规定在过渡效果开始之前需要等待的时间，以秒或毫秒计

## animation (动画)
使用CSS3创建一个动画

1. 语法
    animation：动画名称，一个周期花费时间，运动曲线（默认ease），动画延迟（默认0），播放次数（默认1），是否反向播放动画（默认normal），是否暂停动画（默认running）

2. @keyframes 规则
    @keyframes 规则用于创建动画。在 @keyframes 中规定某项 CSS 样式，就能创建由当前样式逐渐改为新样式的动画效果

    @keyframes 中创建动画时，需要将动画名称捆绑到某个选择器，否则不会产生动画效果。

    通过规定至少以下两项 CSS3 动画属性，即可将动画绑定到选择器：
    - 规定动画的名称
    - 规定动画的时长

    请用百分比来规定变化发生的时间，或用关键词 "from" 和 "to"，等同于 0% 和 100%。

    案例
    ```css
    @keyframes my-keyframes
    {
    from {background:red;}
    to {background:yellow;}
    }
    ```

3. 属性
    - 可以简写

    - animation-name ： 规定 @keyframes 动画的名称

    - animation-duration : 规定动画完成一个周期所花费的秒或毫秒。默认是 0

    - animation-timing-function	: 规定动画的速度曲线。默认是 "ease"
        - linear : 动画从头到尾的速度是相同的
        - ease : 默认。动画以低速开始，然后加快，在结束前变慢
        - ease-in : 动画以低速开始
        - ease-out : 动画以低速结束
        - ease-in-out : 动画以低速开始和结束
        - cubic-bezier(n,n,n,n)	: 在 cubic-bezier 函数中自己的值。可能的值是从 0 到 1 的数值

    - animation-delay : 规定动画何时开始。默认是 0

    - animation-iteration-count	: 规定动画被播放的次数。默认是 1
        - infinite : 无限次播放
        - n ：播放次数  

    - animation-direction : 规定动画是否在下一周期逆向地播放。默认是 "normal"
        - normal ：默认值。动画应该正常播放
        - alternate ： 动画应该轮流反向播放	

    - animation-play-state : 规定动画是否正在运行或暂停。默认是 "running"

    - animation-fill-mode ：规定对象动画时间之外的状态
        - none：不改变默认行为    
        - forwards ：当动画完成后，保持最后一个属性值（在最后一个关键帧中定义）
        - backwards：在 animation-delay 所指定的一段时间内，在动画显示之前，应用开始属性值（在第一个关键帧中定义） 
        - both：向前和向后填充模式都被应用


    

## transform (形态转换)
Transform属性应用于元素的2D或3D转换。这个属性允许你将元素旋转，缩放，移动，倾斜等

1. 语法
   transform: none|transform-functions

2. 属性值
    |值|描述|
    |---|---|
    |none|定义不进行转换||
    |matrix(n,n,n,n,n,n)|定义 2D 转换，使用六个值的矩阵||
    |matrix3d(n,n,n,n,n,n,n,n,n,n,n,n,n,n,n,n)|定义 3D 转换，使用 16 个值的 4x4 矩阵||
    |translate(x,y)|定义 2D 转换||
    |translate3d(x,y,z)|定义 3D 转换||
    |translateX(x)|定义转换，只是用 X 轴的值||
    |translateY(y)|定义转换，只是用 Y 轴的值||
    |translateZ(z)|定义 3D 转换，只是用 Z 轴的值||
    |scale(x,y)|定义 2D 缩放转换||
    |scale3d(x,y,z)|定义 3D 缩放转换||
    |scaleX(x)|通过设置 X 轴的值来定义缩放转换||
    |scaleY(y)|通过设置 Y 轴的值来定义缩放转换||
    |scaleZ(z)|通过设置 Z 轴的值来定义 3D 缩放转换||
    |rotate(angle)|定义 2D 旋转，在参数中规定角度||
    |rotate3d(x,y,z,angle)|定义 3D 旋转||
    |rotateX(angle)|定义沿着 X 轴的 3D 旋转||
    |rotateY(angle)|定义沿着 Y 轴的 3D 旋转||
    |rotateZ(angle)|定义沿着 Z 轴的 3D 旋转||
    |skew(x-angle,y-angle)|定义沿着 X 和 Y 轴的 2D 倾斜转换||
    |skewX(angle)|定义沿着 X 轴的 2D 倾斜转换||
    |skewY(angle)|定义沿着 Y 轴的 2D 倾斜转换||
    |perspective(n)|为 3D 转换元素定义透视视图|	

    
## 选择器
CSS3提供了些新的选择器，这里就列几个常用的，其余的可以[w3c](https://www.w3school.com.cn/cssref/css_selectors.asp)查询

1. css3常用选择器
    |        选择器     |      例子      |                  说明                        |
    |-------------------|----------------|----------------------------------------------|
    |[attribute^=value]	|a[src^="https"] |选择其 src 属性值以 "https" 开头的每个 <a> 元素 |
    |[attribute$=value]	|a[src$=".pdf"]	 |选择其 src 属性以 ".pdf" 结尾的所有 <a> 元素    |
    |[attribute*=value]	|a[src*="abc"]	 |选择其 src 属性中包含 "abc" 子串的每个 <a> 元素  |
    |:first-of-type	    |p:first-of-type |选择属于其父元素的首个 <p> 元素的每个 <p> 元素   |
    |:last-of-type	    |p:last-of-type	 |选择属于其父元素的最后 <p> 元素的每个 <p> 元素	|


## box-shadow（边框阴影）
向框添加一个或者多个阴影

1. 语法
    box-shadow: 水平阴影的位置 垂直阴影的位置 模糊距离 阴影的尺寸 阴影的颜色 阴影开始方向;

2. 属性
   - h-shadow ：必需。水平阴影的位置。允许负值
   - v-shadow ：必需。垂直阴影的位置。允许负值
   - blur : 可选。模糊距离
   - spread	: 可选。阴影的尺寸
   - color : 可选。阴影的颜色
   - inset ：可选。将默认值外部阴影 (outset) 改为内部阴影

3. 区分模糊距离和阴影尺寸
   具体看这篇文章：https://blog.csdn.net/qq_40574071/article/details/94326686?depth_1-utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-4&utm_source=distribute.pc_relevant.none-task-blog-BlogCommendFromBaidu-4 ，作者讲的超级细了


## text-shadow（文本阴影）
向文本设置阴影

1. 语法
   text-shadow: 水平阴影的位置 垂直阴影的位置 模糊距离 阴影的颜色;

2. 属性
   - h-shadow ：必需。水平阴影的位置。允许负值
   - v-shadow ：必需。垂直阴影的位置。允许负值
   - blur : 可选。模糊距离
   - color : 可选。阴影的颜色

## 新的边框属性
border-radius为边框创建圆角,border-image使用图片创建图片


## 背景
1. background-clip,规定背景的绘制区域
   - border-box	: 背景被裁剪到边框盒
   - padding-box : 背景被裁剪到内边距框
   - content-box : 背景被裁剪到内容框
  
2. background-origin，规定 background-position 属性相对于什么位置来定位
   - border-box	: 背景图像相对于边框盒来定位
   - padding-box : 背景图像相对于内边距框来定位
   - content-box : 背景图像相对于内容框来定位
  
3. background-size， 背景图像的尺寸
   - length	：设置背景图片高度和宽度。第一个值设置宽度，第二个值设置的高度。如果只给出一个值，第二个是设置为 auto(自动)
   - percentage	： 将计算相对于背景定位区域的百分比。第一个值设置宽度，第二个值设置的高度。如果只给出一个值，第二个是设置为"auto(自动)"
   - cover	： 此时会保持图像的纵横比并将图像缩放成将完全覆盖背景定位区域的最小大小
   - contain ： 此时会保持图像的纵横比并将图像缩放成将适合背景定位区域的最大大小


   