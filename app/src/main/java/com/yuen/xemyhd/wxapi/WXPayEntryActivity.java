package com.yuen.xemyhd.wxapi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yuen.xemyhd.Constants;
import com.yuen.xemyhd.activity.MainActivity;
import com.yuen.xemyhd.utils.ContactURL;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;

import java.util.HashMap;

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler{

    private IWXAPI api;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    	api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		Log.d("mafuhua","resp.errStr"+ resp.errStr);
		Log.d("mafuhua","resp.errCode"+ resp.errCode);

		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			if (resp.errCode == 0){

				postmoneytest();
			}else {
				Toast.makeText(this, "支付失败,请重试", Toast.LENGTH_SHORT).show();
				finish();
			}

		}
	}
	private void postmoneytest() {
		HashMap<String, String> paymap = new HashMap<>();
		paymap.put("order_id", "14643328368843683");
	/*	paymap.put("user_id",  "352");
		paymap.put("price","21540");*/
		XUtils.xUtilsPost(ContactURL.PAYMENT_DONE_URL, paymap,new Callback.CommonCallback<String>() {


			@Override
			public void onSuccess(String result) {
				Toast.makeText(WXPayEntryActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
				//Toast.makeText(WXPayEntryActivity.this, result, Toast.LENGTH_SHORT).show();
				Log.d("mafuhua","result"+ result);
				Intent intent = new Intent(WXPayEntryActivity.this, MainActivity.class);
				startActivity(intent);
            /*    Gson gson = new Gson();
                WXBean wxBean = gson.fromJson(result, WXBean.class);
                WXBean.DataBean data = wxBean.getData();
                wxapi = WXAPIFactory.createWXAPI(getActivity(), Constants.APP_ID);
                boolean registerApp = wxapi.registerApp(Constants.APP_ID);
                Log.d("mafuhua", "registerApp:" + registerApp);
                PayReq payReq = new PayReq();
                payReq.appId = data.getAppid();
                payReq.partnerId = data.getPartnerid();
                payReq.prepayId = data.getPrepayid();
                payReq.nonceStr = data.getNoncestr();
                payReq.packageValue = "Sign=WXPay";
                payReq.timeStamp = data.getTimestamp() + "";
                payReq.sign = data.getSign();



                wxapi.sendReq(payReq);*/
			}

			@Override
			public void onError(Throwable ex, boolean isOnCallback) {

			}

			@Override
			public void onCancelled(CancelledException cex) {

			}

			@Override
			public void onFinished() {
				finish();
			}
		});
	}
}