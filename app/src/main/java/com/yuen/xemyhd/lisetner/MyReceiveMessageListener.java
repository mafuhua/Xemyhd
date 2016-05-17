package com.yuen.xemyhd.lisetner;

import android.app.Activity;
import android.util.Log;

import com.yuen.xemyhd.activity.MainActivity;

import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Message;
import io.rong.message.TextMessage;

public class MyReceiveMessageListener extends Activity implements RongIMClient.OnReceiveMessageListener {

    public static String targetId;

    /**
     * 收到消息的处理。
     *
     * @param message 收到的消息实体。
     * @param left    剩余未拉取消息数目。
     * @return 收到消息是否处理完成，true 表示走自已的处理方式，false 走融云默认处理方式。
     */
    @Override
    public boolean onReceived(Message message, int left) {
        Log.d("mafuhua", "------------南方就是罚款");
        MainActivity.initNotify();
        TextMessage content = (TextMessage) message.getContent();
        content.getContent();
        targetId = message.getTargetId();
        Log.d("mafuhua", "message:" + content.getContent());
        Log.d("mafuhua", "message**:" + message.getTargetId());
        return true;
    }


}