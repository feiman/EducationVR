package com.dqxxhl.educationvr.vrplayer;


import com.dqxxhl.educationvr.R;
import com.dqxxhl.educationvr.presenter.ServiceManager;
import com.dqxxhl.educationvr.presenter.ViewAction;
import com.dqxxhl.educationvr.utils.Utils;
import com.sd.vr.ctrl.netty.protobuf.MessageProto;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class EducationVRMainActivity extends AppCompatActivity implements ViewAction {

    ServiceManager serviceManager;
    Button sendConnectButton;
    Button sendRegisterButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_education_vrmain);
        serviceManager = ServiceManager.getInstance();
        serviceManager.bindAction(this);
        sendConnectButton = (Button) findViewById(R.id.connect_send);
        sendConnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //向服务端发送数据
                MessageProto.ReConnectRequest reConnectRequest = MessageProto.ReConnectRequest.newBuilder().setEventId("REGISTER").setEquipmentId(Utils.getDeviceId(EducationVRMainActivity.this)).build();
                MessageProto.MessageRequest request = MessageProto.MessageRequest.newBuilder().setType(MessageProto.Types.RECONNECT).setReConnectRequest(reConnectRequest).build();
                System.out.println("发送数据："+request.toString());
                serviceManager.sendRequest(request);
            }
        });

        sendRegisterButton = (Button) findViewById(R.id.register_send);
        sendRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //向服务端发送数据
                MessageProto.RegisterRequest registerRequest = MessageProto.RegisterRequest.newBuilder().setEventId("RECONNECT").setEquipmentId(Utils.getDeviceId(EducationVRMainActivity.this)).build();
                MessageProto.MessageRequest request = MessageProto.MessageRequest.newBuilder().setType(MessageProto.Types.REGISTER).setRegisterRequest(registerRequest).build();
                System.out.println("发送数据："+request.toString());
                serviceManager.sendRequest(request);
            }
        });

    }

    @Override
    public void stop() {
        System.out.println("收到服务端指令----->暂停");
    }

    @Override
    public void showToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}
