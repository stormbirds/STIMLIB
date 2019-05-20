package cn.stormbirds.stimdemo;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.UUID;

import cn.stormbirds.stimlib.bean.GroupMessage;
import cn.stormbirds.stimlib.bean.SingleMessage;
import cn.stormbirds.stimlib.event.Events;
import cn.stormbirds.stimlib.event.IMEventCenter;
import cn.stormbirds.stimlib.event.IM_EventListener;
import cn.stormbirds.stimlib.im.IMClientBootstrap;
import cn.stormbirds.stimlib.im.MessageProcessor;
import cn.stormbirds.stimlib.im.MessageType;
import cn.stormbirds.stimlib.utils.CThreadPoolExecutor;
import cn.stormbirds.stimdemo.utils.PermissionHelper;
import cn.stormbirds.stidemo.utils.PermissionInterface;


public class MainActivity extends AppCompatActivity implements IM_EventListener, PermissionInterface {

    private EditText mEditText;
    private TextView mTextView;
    private Button login;

    String userId = "20025653445398528";
    String token = "Bearer " + userId;
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

        //初始化并发起权限申请
        mPermissionHelper = new PermissionHelper(this, this);
        mPermissionHelper.requestPermissions();

        mEditText = findViewById(R.id.et_content);
        mTextView = findViewById(R.id.tv_msg);
        login = findViewById(R.id.changeUser);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });



        IMEventCenter.registerEventListener(this, EVENTS);
    }

    public void sendMsg(View view) {
        GroupMessage message = new GroupMessage();
        message.setMsgId(UUID.randomUUID().toString());
        message.setMsgType(MessageType.GROUP_CHAT.getMsgType());
        message.setMsgContentType(MessageType.MessageContentType.TEXT.getMsgContentType());
        message.setFromId(userId);
        message.setToId("81778243076097");
        message.setTimestamp(System.currentTimeMillis());
        message.setContent(mEditText.getText().toString());

        MessageProcessor.getInstance().sendMsg(message);

    }

    public void login(){
        userId = UUID.randomUUID().toString();
        token = "Bearer " + userId;
        IMClientBootstrap.getInstance().login(userId, token);

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
