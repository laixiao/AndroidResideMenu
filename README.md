AndroidResideMenu:类似QQ淡入淡出菜单
<img src="https://github.com/laixiao/AndroidResideMenu/blob/master/doc/menu2.png"></img>
#1.简介
  1.编码：gbk
  
  2.可自定义resideMenu
  
  3.demo有详细注释
  
  
#2.demo使用注意事项
  ```java
  //1.如果你开启了手势滑动，你需要替换被依附的 Activity 里  dispatchTouchEvent()  代码
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }
  //2.如果您需要使用手势滑动开启/关闭菜单，请复写activity的dispatchTouchEvent()，代码如下
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }
  //3.在某些场景下，手势滑动开启/关闭菜单可能与您的某些控件产生冲突，例如viewpager，这时您可以把viewpager添加到ignored view.
     // add gesture operation's ignored views（这样子在ignored_view操作的区域就不允许用手势滑动操作菜单.）
      FrameLayout ignored_view = (FrameLayout) findViewById(R.id.ignored_view);
       resideMenu.addIgnoredView(ignored_view);

  
  ```
#3.demo演示
<img src="https://github.com/laixiao/AndroidResideMenu/blob/master/doc/menu1.png"></img>

Android开发部落交流群：430049289

