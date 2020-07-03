package com.aqiang.common.router;

import android.app.Application;
import android.os.Bundle;
import android.os.Parcelable;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.launcher.ARouter;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

public class RouterManager {
    private RouterManager(){

    }

    private static RouterManager routerManager = new RouterManager();

    public static RouterManager getInstance(){
        return routerManager;
    }

    public void init(Application application,boolean isDebug){
        if(isDebug){
            ARouter.openDebug();
            ARouter.openLog();
        }
        ARouter.init(application);
    }

    public void router(String path){
        ARouter.getInstance().build(path).navigation();
    }

    public void route(String path, Map<String,Object> params){
        Postcard build = ARouter.getInstance().build(path);
        if (params!=null&&params.size()>0){
            Set<Map.Entry<String, Object>> entries = params.entrySet();
            for (Map.Entry<String, Object> set:entries){
                Object value = set.getValue();
                if (value instanceof Boolean){
                    build.withBoolean(set.getKey(), (Boolean) value);
                }else if (value instanceof Bundle){
                    build.withBundle(set.getKey(), (Bundle) value);
                }else if (value instanceof Integer){
                    build.withInt(set.getKey(), (Integer) value);
                }else if (value instanceof Double){
                    build.withDouble(set.getKey(), (Double) value);
                }else if (value instanceof Float){
                    build.withString(set.getKey(), (String) value);
                }else if (value instanceof Parcelable){
                    build.withParcelable(set.getKey(), (Parcelable) value);
                }else if (value instanceof Serializable){
                    build.withSerializable(set.getKey(), (Serializable) value);
                }
            }
        }
        build.navigation();
    }
}
