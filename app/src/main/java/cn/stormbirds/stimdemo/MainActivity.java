package cn.stormbirds.stimdemo;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

import cn.stormbirds.stimdemo.bean.GroupMessage;
import cn.stormbirds.stimdemo.bean.SingleMessage;
import cn.stormbirds.stimdemo.event.Events;
import cn.stormbirds.stimdemo.event.IMEventCenter;
import cn.stormbirds.stimdemo.event.IM_EventListener;
import cn.stormbirds.stimdemo.im.IMSClientBootstrap;
import cn.stormbirds.stimdemo.im.MessageProcessor;
import cn.stormbirds.stimdemo.im.MessageType;
import cn.stormbirds.stimdemo.utils.CThreadPoolExecutor;
import cn.stormbirds.stimdemo.utils.PermissionHelper;
import cn.stormbirds.stimdemo.utils.PermissionInterface;


public class MainActivity extends AppCompatActivity implements IM_EventListener, PermissionInterface {

    private EditText mEditText;
    private TextView mTextView;

    String userId = "100001";
    String token = "token_" + userId;
    String hosts = "[{\"host\":\"192.168.6.198\", \"port\":8855}]";
    private PermissionHelper mPermissionHelper;
    private static final String[] EVENTS = {
            Events.CHAT_SINGLE_MESSAGE,
            Events.CHAT_GROUP_MESSAGE
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
//        TextView tv = (TextView) findViewById(R.id.sample_text);
//        tv.setText(stringFromJNI());

        //初始化并发起权限申请
        mPermissionHelper = new PermissionHelper(this, this);
        mPermissionHelper.requestPermissions();

        mEditText = findViewById(R.id.et_content);
        mTextView = findViewById(R.id.tv_msg);

        IMSClientBootstrap.getInstance().init(userId, token, hosts, 1);

        IMEventCenter.registerEventListener(this, EVENTS);
    }

    public void sendMsg(View view) {
        GroupMessage message = new GroupMessage();
        message.setMsgId(UUID.randomUUID().toString());
        message.setMsgType(MessageType.GROUP_CHAT.getMsgType());
        message.setMsgContentType(MessageType.MessageContentType.TEXT.getMsgContentType());
        message.setFromId(userId);
        message.setToId("100002");
        message.setTimestamp(System.currentTimeMillis());
        message.setContent(mEditText.getText().toString());

        MessageProcessor.getInstance().sendMsg(message);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        IMEventCenter.unregisterEventListener(this, EVENTS);
    }

    @Override
    public void onEvent(String topic, int msgCode, int resultCode, Object obj) {
        switch (topic) {
            case Events.CHAT_SINGLE_MESSAGE: {
                final SingleMessage message = (SingleMessage) obj;
                CThreadPoolExecutor.runOnMainThread(new Runnable() {

                    @Override
                    public void run() {
                        mTextView.setText("单聊消息："+message.getContent());
                    }
                });
                break;
            }
            case Events.CHAT_GROUP_MESSAGE:{
                final GroupMessage message = (GroupMessage) obj;
                CThreadPoolExecutor.runOnMainThread(new Runnable() {

                    @Override
                    public void run() {
                        mTextView.setText("群聊消息："+message.getContent());
                    }
                });
                break;
            }

            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(mPermissionHelper.requestPermissionsResult(requestCode, permissions, grantResults)){
            //权限请求结果，并已经处理了该回调
            return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public int getPermissionsRequestCode() {
        //设置权限请求requestCode，只有不跟onRequestPermissionsResult方法中的其他请求码冲突即可。
        return 10000;
    }

    @Override
    public String[] getPermissions() {
        //设置该界面所需的全部权限
        return new String[]{
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.INTERNET
        };
    }

    @Override
    public void requestPermissionsSuccess() {
        //权限请求用户已经全部允许
        initViews();
    }

    @Override
    public void requestPermissionsFail() {
        //权限请求不被用户允许。可以提示并退出或者提示权限的用途并重新发起权限申请。
        finish();
    }

    private void initViews(){
        //已经拥有所需权限，可以放心操作任何东西了

    }
}