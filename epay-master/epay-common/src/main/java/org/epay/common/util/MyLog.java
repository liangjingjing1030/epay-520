package org.epay.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 名称: MyLog
 * 作者: HappyDan
 * 版本: V1.0
 */
public class MyLog extends MyLogFace {

    private static final Map<String,MyLog> _pool = new HashMap<String,MyLog>();
    //----------
    public static synchronized Set<String> getLoggers() 	{
        return _pool.keySet();
    }
    public static synchronized void clearLoggers() 	{
        _pool.clear();
    }
    //----------
    public static synchronized MyLog getLog(String clz) 	{
        MyLog log = _pool.get(clz);
        if (log==null) {
            log = new MyLog();
            log.setName(clz);
            _pool.put(clz, log);
        }
        return log;
    }
    //----------
    public static MyLog getLog(Class<?> clz){
        return getLog(clz.getName());
    }

}
