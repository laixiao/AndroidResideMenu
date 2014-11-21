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
	//1.�˵�
	private ResideMenu resideMenu;

	//2.�˵�����в���
	private ResideMenuItem MenuItem1;
	private ResideMenuItem MenuItem2;
	private ResideMenuItem MenuItem3;
	private ResideMenuItem MenuItem4;
	private ResideMenuItem MenuItem5;
	//3.�˵����ͷ����
	private ResideMenuInfo MenuTitleItem;
	//4.�˵�����ײ���
	private ResideMenuSetting MenuBottomItem;
	
	//ʱ�䣨�жϷ��ؼ��˳���
	private long mExitTime;
	//�жϲ˵��Ƿ�չ��
	private boolean is_closed = false;
	
	private ImageButton imageButton1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		//���ò˵�
		setUpMenu();
		//���ü���
		setListener();
	}	
	private void setUpMenu() {		
		//ʵ�����������ͣ�
		resideMenu = new ResideMenu(this);
		//���ò˵����������Կ�Ч������
		resideMenu.setBackground(R.drawable.residebackground1);
		//������Activity�������ǵ�ǰ��Activity��
		resideMenu.attachToActivity(this);
		//���ü���
		resideMenu.setMenuListener(menuListener);
		//���ñ�����0.0f-1.0f�Լ����Կ�Ч����
		resideMenu.setScaleValue(0.6f);
		//��ֹʹ���Ҳ�˵�
		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
		// resideMenu.setDirectionDisable(ResideMenu.DIRECTION_RIGHT);

		//�����˵�����
		MenuItem1 = new ResideMenuItem(this, R.drawable.residebutton1, "ѡ��1");
		MenuItem2 = new ResideMenuItem(this, R.drawable.residebutton5, "ѡ��2");
		MenuItem3 = new ResideMenuItem(this, R.drawable.residebutton3,"ѡ��3");
		MenuItem4 = new ResideMenuItem(this, R.drawable.residebutton4, "ѡ��4");
		MenuItem5 = new ResideMenuItem(this, R.drawable.residebutton2, "ѡ��5");

		imageButton1=(ImageButton) this.findViewById(R.id.imageButton1);
		MenuTitleItem = new ResideMenuInfo(this, R.drawable.icon_profile, "Ӣ��hero", "32 ��");
		MenuBottomItem = new ResideMenuSetting(this, "����", "");

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

	// What good method is to access resideMenu��
	public ResideMenu getResideMenu() {
		return resideMenu;
	}

	// �����ֻ��ϵ�BACK��
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			// �жϲ˵��Ƿ�ر�
			if (is_closed) {
				 // �ж����ε����ʱ������Ĭ������Ϊ2�룩
				 if ((System.currentTimeMillis() - mExitTime) > 2000) {
				 Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();
				
				 mExitTime = System.currentTimeMillis();
				 } else {
				 finish();
				 System.exit(0);
				 super.onBackPressed();
				 }
				
//				// ģ��HOME��Ч�������滻�����˳�����Ŷ��
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
