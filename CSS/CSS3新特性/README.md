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

## 动画
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
        - linear : 动画从头到尾的速度是相同的
        - ease : 默认。动画以低速开始，然后加快，在结束前变慢
        - ease-in : 动画以低速开始
        - ease-out : 动画以低速结束
        - ease-in-out : 动画以低速开始和结束
        - cubic-bezier(n,n,n,n)	: 在 cubic-bezier 函数中自己的值。可能的值是从 0 到 1 的数值

    - animation-timing-function	: 规定动画的速度曲线。默认是 "ease"

    - animation-delay : 规定动画何时开始。默认是 0

    - animation-iteration-count	: 规定动画被播放的次数。默认是 1

    - animation-direction : 规定动画是否在下一周期逆向地播放。默认是 "normal"

    - animation-play-state : 规定动画是否正在运行或暂停。默认是 "running"

    - animation-fill-mod : 规定对象动画时间之外的状态

    - animation-fill-mode
        - none：不改变默认行为    
        - forwards ：当动画完成后，保持最后一个属性值（在最后一个关键帧中定义）
        - backwards：在 animation-delay 所指定的一段时间内，在动画显示之前，应用开始属性值（在第一个关键帧中定义） 
        - both：向前和向后填充模式都被应用


    
