package com.aqiang.storage.sp;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtlis {

    public static SharedPreferences.Editor getEdit(Context context){
        SharedPreferences sp = context.getSharedPreferences("sdata", Context.MODE_PRIVATE);
        return sp.edit();
    }

    public static void put(Context context,String key,Object value){
        SharedPreferences.Editor edit = getEdit(context);
        if(value instanceof String){
            edit.putString(key, (String) value);
        }else if(value instanceof Boolean){
            edit.putBoolean(key, (Boolean) value);
        }else if(value instanceof Integer){
            edit.putInt(key, (Integer) value);
        }
        edit.commit();
    }

    public static Object get(Context context,String key,Object defaultValue){
        SharedPreferences sp = context.getSharedPreferences("sdata", Context.MODE_PRIVATE);
        if(defaultValue instanceof String){
            return sp.getString(key, (String) defaultValue);
        }else if(defaultValue instanceof Boolean){
            return sp.getBoolean(key, (Boolean) defaultValue);
        }else if(defaultValue instanceof Integer){
            return sp.getInt(key, (Integer) defaultValue);
        }else {
            return sp.getString(key, (String) defaultValue);
        }
    }

    public static void remove(Context context,String key){
        SharedPreferences.Editor editor = getEdit(context);
        editor.remove(key);
        editor.commit();
    }

    /**
     * 判断释放包含key
     * @param context
     * @param key
     * @return
     */
    public static boolean contains(Context context,String key){
        SharedPreferences sp = context.getSharedPreferences("sdata",
                Context.MODE_PRIVATE);
        return  sp.contains(key);
    }

    /**
     * 清空数据
     * @param context
     */
    public static void clear(Context context){
        SharedPreferences.Editor editor  = getEdit(context);
        editor.clear();
        editor.commit();

    }

}
