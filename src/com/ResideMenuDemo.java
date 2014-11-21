package com;

import com.examp.ResideMenu.ResideMenu;
import com.examp.ResideMenu.ResideMenuInfo;
import com.examp.ResideMenu.ResideMenuItem;
import com.examp.ResideMenu.ResideMenuSetting;
import com.example.residemenu.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

public class ResideMenuDemo extends Activity implements OnClickListener {
	//1.菜单
	private ResideMenu resideMenu;

	//2.菜单子项（中部）
	private ResideMenuItem MenuItem1;
	private ResideMenuItem MenuItem2;
	private ResideMenuItem MenuItem3;
	private ResideMenuItem MenuItem4;
	private ResideMenuItem MenuItem5;
	//3.菜单子项（头部）
	private ResideMenuInfo MenuTitleItem;
	//4.菜单子项（底部）
	private ResideMenuSetting MenuBottomItem;
	
	//时间（判断返回键退出）
	private long mExitTime;
	//判断菜单是否展开
	private boolean is_closed = false;
	
	private ImageButton imageButton1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		//设置菜单
		setUpMenu();
		//设置监听
		setListener();
	}	
	private void setUpMenu() {		
		//实例化（不解释）
		resideMenu = new ResideMenu(this);
		//设置菜单背景（测试看效果哈）
		resideMenu.setBackground(R.drawable.residebackground1);
		//关联的Activity（这里是当前的Activity）
		resideMenu.attachToActivity(this);
		//设置监听
		resideMenu.setMenuListener(menuListener);
		//设置比例（0.0f-1.0f自己测试看效果）
		resideMenu.setScaleValue(0.6f);
		//禁止使用右侧菜单
		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
		// resideMenu.setDirectionDisable(ResideMenu.DIRECTION_RIGHT);

		//创建菜单子项
		MenuItem1 = new ResideMenuItem(this, R.drawable.residebutton1, "选项1");
		MenuItem2 = new ResideMenuItem(this, R.drawable.residebutton5, "选项2");
		MenuItem3 = new ResideMenuItem(this, R.drawable.residebutton3,"选项3");
		MenuItem4 = new ResideMenuItem(this, R.drawable.residebutton4, "选项4");
		MenuItem5 = new ResideMenuItem(this, R.drawable.residebutton2, "选项5");

		imageButton1=(ImageButton) this.findViewById(R.id.imageButton1);
		MenuTitleItem = new ResideMenuInfo(this, R.drawable.icon_profile, "英雄hero", "32 级");
		MenuBottomItem = new ResideMenuSetting(this, "设置", "");

		resideMenu.addMenuItem(MenuItem1, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(MenuItem2, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(MenuItem3, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(MenuItem4, ResideMenu.DIRECTION_LEFT);
		resideMenu.addMenuItem(MenuItem5, ResideMenu.DIRECTION_LEFT);
		// resideMenu.addMenuItem(itemFile, ResideMenu.DIRECTION_LEFT);

		resideMenu.addMenuInfo(MenuTitleItem);
		resideMenu.addMenuSetting(MenuBottomItem);

	}
	private void setListener() {
		MenuItem1.setOnClickListener(this);
		MenuItem2.setOnClickListener(this);
		MenuItem3.setOnClickListener(this);
		MenuItem4.setOnClickListener(this);
		MenuItem5.setOnClickListener(this);

		MenuTitleItem.setOnClickListener(this);
		MenuBottomItem.setOnClickListener(this);

		imageButton1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
			}
		});
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return resideMenu.dispatchTouchEvent(ev);
	}

	@Override
	public void onClick(View view) {
		if (view == MenuItem1) {
			Toast.makeText(ResideMenuDemo.this, "MenuItem1", Toast.LENGTH_LONG).show();
		} else if (view == MenuItem2) {
			Toast.makeText(ResideMenuDemo.this, "MenuItem2", Toast.LENGTH_LONG).show();
		} else if (view == MenuItem3) {
			Toast.makeText(ResideMenuDemo.this, "MenuItem3", Toast.LENGTH_LONG).show();
		} else if (view == MenuItem4) {
			Toast.makeText(ResideMenuDemo.this, "MenuItem4", Toast.LENGTH_LONG).show();
		} else if (view == MenuItem5) {
			Toast.makeText(ResideMenuDemo.this, "MenuItem5", Toast.LENGTH_LONG).show();
		} else if (view == MenuTitleItem) {
			Toast.makeText(ResideMenuDemo.this, "MenuTitleItem", Toast.LENGTH_LONG).show();
		} else if (view == MenuBottomItem) {
			Toast.makeText(ResideMenuDemo.this, "MenuBottomItem", Toast.LENGTH_LONG).show();
		}
	}

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
		@Override
		public void openMenu() {
			is_closed = false;
			// leftMenu.setVisibility(View.GONE);
		}
		@Override
		public void closeMenu() {
			is_closed = true;
			// leftMenu.setVisibility(View.VISIBLE);
		}
	};

	// What good method is to access resideMenu？
	public ResideMenu getResideMenu() {
		return resideMenu;
	}

	// 监听手机上的BACK键
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// 判断菜单是否关闭
			if (is_closed) {
				 // 判断两次点击的时间间隔（默认设置为2秒）
				 if ((System.currentTimeMillis() - mExitTime) > 2000) {
				 Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				
				 mExitTime = System.currentTimeMillis();
				 } else {
				 finish();
				 System.exit(0);
				 super.onBackPressed();
				 }
				
//				// 模拟HOME键效果（可替换上面退出代码哦）
//				Intent i = new Intent(Intent.ACTION_MAIN);
//				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//				i.addCategory(Intent.CATEGORY_HOME);
//				startActivity(i);
			} else {
				resideMenu.closeMenu();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
