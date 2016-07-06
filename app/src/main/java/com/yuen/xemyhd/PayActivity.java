package com.yuen.xemyhd;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yuen.xemyhd.bean.WXBean;
import com.yuen.xemyhd.utils.XUtils;

import org.xutils.common.Callback;

import java.util.HashMap;

public class PayActivity extends Activity {

	private IWXAPI wxapi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pay);

		//wxapi = WXAPIFactory.createWXAPI(this, "wxb4ba3c02aa476ea1");
		wxapi = WXAPIFactory.createWXAPI(this, "wxcab1c0202ebea846");

		Button appayBtn = (Button) findViewById(R.id.button);
		appayBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {

				Toast.makeText(PayActivity.this, "获取订单中...", Toast.LENGTH_SHORT).show();
				HashMap<String, String> paymap = new HashMap<>();
				paymap.put("uid", "10000000");
				paymap.put("discount", "1");
				XUtils.xUtilsPost("http://172.27.35.9/yuenkeji/UPA/20160607/Home/OrderRmb/dopay", paymap, new Callback.CommonCallback<String>() {


					@Override
					public void onSuccess(String result) {
						System.out.println(result);
						Toast.makeText(PayActivity.this, result, Toast.LENGTH_SHORT).show();
						Log.d("mafuhua","result"+ result);
						Gson gson = new Gson();
						WXBean wxBean = gson.fromJson(result, WXBean.class);
						WXBean.DataBean data = wxBean.getData();
						wxapi = WXAPIFactory.createWXAPI(PayActivity.this, Constants.APP_ID,false);
						boolean registerApp = wxapi.registerApp(Constants.APP_ID);
						Log.d("mafuhua", "registerApp:" + registerApp);
						PayReq payReq = new PayReq();
						/*payReq.appId = data.getAppId();
						payReq.partnerId = data.getPartnerid();
						payReq.prepayId = data.getPrepayid();
						payReq.nonceStr = data.getNonceStr();
						payReq.packageValue = "Sign=WXPay";
						payReq.timeStamp = data.getTimeStamp() + "";
						payReq.sign = data.getSign();*/
                      /*  payReq.appId = "wxcab1c0202ebea846";
                        payReq.partnerId = "1361691202";
                        payReq.prepayId = "wx201607061116290138e80f690642205793";
                        payReq.nonceStr = "8iwlm6eqdbqq66h09w3h7h163ai16gwx";
                        payReq.packageValue = "Sign=WXPay";
                        payReq.timeStamp = "1467774991";
                        payReq.sign = "D51ABFC07C22C72836A55B7443C710D4";*/
						wxapi.sendReq(payReq);

					}

					@Override
					public void onError(Throwable ex, boolean isOnCallback) {

					}

					@Override
					public void onCancelled(CancelledException cex) {

					}

					@Override
					public void onFinished() {

					}
				});

			}
		});		
		Button checkPayBtn = (Button) findViewById(R.id.button2);
		checkPayBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				boolean isPaySupported = wxapi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
				Toast.makeText(PayActivity.this, String.valueOf(isPaySupported), Toast.LENGTH_SHORT).show();
			}
		});
	}
	
}
