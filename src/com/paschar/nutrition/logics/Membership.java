package com.paschar.nutrition.logics;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

public class Membership extends Application{
	private static String Active = "Active";
	
	public static boolean CheckIsMemberActive(Context context)
	{
		SharedPreferences appSettings = context.getSharedPreferences(Active, android.content.Context.MODE_PRIVATE);
		return appSettings.getBoolean(Active, false);
	}
	
	public static void MemberActivation(Context context)
	{
		SharedPreferences appSettings = context.getSharedPreferences("Active", android.content.Context.MODE_PRIVATE);
		SharedPreferences.Editor prefEditor = appSettings.edit();  
		prefEditor.putBoolean(Active, true);
		prefEditor.commit();		
	}
}
