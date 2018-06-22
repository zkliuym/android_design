package com.lhj.android_design;import android.content.Intent;import android.os.Bundle;import android.support.v7.app.AppCompatActivity;import android.widget.Button;import android.widget.TextView;import com.lhj.android_design.banner.BannerTestActivity;import com.lhj.android_design.msgctrl.MsgDef;import com.lhj.android_design.msgctrl.MsgDispatcher;import com.lhj.android_design.msgctrl.RxBus;import com.lhj.android_design.refresh.SmartRefreshTestActivity;import com.lhj.android_design.service.response.UserInfoResult;import butterknife.BindView;import butterknife.ButterKnife;import butterknife.OnClick;import cn.sharesdk.onekeyshare.OnekeyShare;import rx.Subscription;public class MainActivity extends BaseActivity {    @BindView(R.id.tvContent)    TextView tvContent;    @BindView(R.id.btnGetUserInfo)    Button btnGetUserInfo;    Subscription userInfoDataSubscription;    @Override    public int initLayout() {        return R.layout.activity_main;    }    @Override    public void initView() {    }    @Override    public void initData() {        userInfoDataSubscription = RxBus.getInstance().toObservable(UserInfoResult.class)                .subscribe(                        result -> tvContent.setText(result.respData.getUserName()));    }    @OnClick(R.id.btnGetUserInfo)    public void onViewClicked() {        MsgDispatcher.dispatchMessage(MsgDef.MSG_DEF_GET_USER_INFO);    }    @Override    protected void onDestroy() {        super.onDestroy();        RxBus.getInstance().unsubscribe(userInfoDataSubscription);    }    @OnClick(R.id.btnShare)    public void onShareSdkViewClicked() {        OnekeyShare onekeyShare = new OnekeyShare();        //关闭sso授权        onekeyShare.disableSSOWhenAuthorize();        //title        onekeyShare.setTitle("androidDesign");        //TitleRrl QQ和QQ空间的跳转连接        onekeyShare.setTitleUrl("http://baidu.com");        onekeyShare.setText("分享文本信息");        onekeyShare.setImageUrl("http://wx.qlogo.cn/mmopen/0NCoSFBsJrS6ictTRINKR2DJQ4pk6Lrlxy6XF5UdDXVAiaDaM3IKdiclcoZGay7PIEENeWs9VQWkyNYKGC6RFrDaONBmSEpXGtS/0");        // onekeyShare.setImagePath()        onekeyShare.setUrl("http://sharesdk.cn");        onekeyShare.show(this);        // onekeyShare.    }    @OnClick(R.id.refreshTest)    public void onRefreshClicked() {        Intent intent = new Intent(this, SmartRefreshTestActivity.class);        startActivity(intent);    }    @OnClick(R.id.banner)    public void onBananerClicked(){        Intent intent = new Intent(this, BannerTestActivity.class);        startActivity(intent);    }}