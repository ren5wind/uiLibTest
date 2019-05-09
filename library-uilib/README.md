 ### UiLib 使用说明：
一.UiLibTitleBar:

为了减少title的代码量、时间，将title封装成控件，整合常用title样式。
titlebar控件分为4部分，left(TextView)、right(TextView)、title(TextView)、bottom(View)

### 使用：
1.在定义左右两边图片时，以TextView中DrawableLeft和DrawableRight的方式。
2.默认实现了back返回事件、back的text及drawable
3.控件style：
根据现有title样式定义了3个style分别为uiLib_titlebar_normal、uiLib_titlebar_noright、uiLib_titlebar_nobottom_nobarbackground。
如不满足可自行扩展
4.支持对子view的style设置
如：uilib:title_style="@style/uilib_comm_midd_textview"，需要API21
5.默认实现了left的文字、图片、点击事件。
文字中文为"返回"，英文为"Back"，已实现国际化，以后添加多语言时，需要同步添加。
图片为向左箭头
点击实现默认实现了activity.finish()。
6.代码中可使用builder的方式来创建控件。
7.属性title_maxWidthOff
title文字的maxwidth修改为动态计算：屏幕宽度 -（left或者right的最大值）*2 - 调整偏移量（文字距左右2边的距离-left或right的padding）
默认值：目前文字与左右view的距离为30dp，其中view的padding为15dp，偏移量title_maxWidthOff为15dp，已内置到模板中

### 局限性:
1.TextViewDrawable来设置图片:无法支持background+scr的方式
2.默认实现的back绑定点击事件只实现了activity.finish()；如使用此事件，需要activity的context
3.目前只配置了部分常用属性设置，详情请参照lib-uilib中attrs文件

二.UiLibDialog:
### 使用：
1.创建Builder时选择模板-》设置builder参数并create-》dialog show
2.API说明
（1）setXXXButton方法：
setXXXButton(text,UiLibDialogInterface.NormalOnClickListener listener, boolean aotuDismiss)
使用场景为需要监听button点击事件，参数aotuDismiss为点击后是否自动关闭dialog
setXXXButton(text);
使用场景为不需要监听button点击事件，默认实现点击后关闭dialog
setXXXButtonCountdown(int)
只有一个button生效，多次调用会覆盖之前的参数
点击事件listener
UiLibDialogInterface.NormalOnClickListener()
public void onClick(View v, boolean isChecked, String editText)
参数ischecked为是否选择了checkbox
参数editText为 点击button时EditText中输入文字回调
（2）setEditTextHit
调用该方法，打开EditText view，否则隐藏
（3）setXXXButtonTextColor(int colorsResId)
参数colorsResId为资源ID
（4）setItems(String[] items, final UiLibDialogInterface.ListOnItemClickListener listener)
参数items为列表项，默认点击关闭dialog
public void onItemClick(View v, int position,String itemText)
position为点击的items，从0开始
（5）setWidthPercentageForWindow(int percent)
设置dialog宽度（占窗口的百分比）
（6）setGravity(int gravity)

（7）setAnim(@StyleRes int animId)




### 局限性：
模板目前只实现了2个
1.normal：其布局由上自下为：title-》icon-》message-》tips-》CheckBox-》edittext-》3个button
2.list：其布局由上自下为：title-》icon-》message-》listview
