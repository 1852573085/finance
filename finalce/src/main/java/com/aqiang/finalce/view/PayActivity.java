package com.aqiang.finalce.view;

import android.annotation.SuppressLint;
import android.databinding.BindingAdapter;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.aqiang.common.BaseApplication;
import com.aqiang.common.router.RouterPath;
import com.aqiang.core.view.BaseActivity;
import com.aqiang.finalce.R;
import com.aqiang.finalce.databinding.ActivityPayBinding;
import com.aqiang.finalce.pay.AuthResult;
import com.aqiang.finalce.pay.OrderInfoUtil2_0;
import com.aqiang.finalce.pay.PayResult;
import com.aqiang.finalce.viewmodel.FinalceViewModel;
import com.bumptech.glide.Glide;

import java.util.Map;

@Route(path = RouterPath.FINALCE_PAY)
public class PayActivity extends BaseActivity<ActivityPayBinding, FinalceViewModel> {
    @Autowired(name = "status")
    int status;
    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2016102400751965";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "";

    /**
     *  pkcs8 格式的商户私钥。
     *
     * 	如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 	使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * 	RSA2_PRIVATE。
     *
     * 	建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 	工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCP+uFcwFL5TY4ecY0XKcVpsvbA39bL/FzVmFhnJcbIayFFwMZP2FIMC3kQYRypcqCpLF/35hvVRlTTuEFNMVbOnoFKkJapX5VhMZ+l+xWrWci9d04xOC5y9yDOPEnUf9ByNvEAHqnO7EMAx+KGJc2cJnL20GaiCWPyq/FXXDqe/xcZgdWcFretZtTob6F7JcpIVX4k/88kYjuJilLLcN9J274q3F0DOj+7YwqfQD/sUOr0DF6JhSoFb9p12EOQGsIHLG0/qGSVvuX8u0uMJctEy1t5bJ4E+XG3UVc40j2gQ1s5w3cnVqnxgPbNwEZYFnbdyYvLr5Ob73xrpM5yqvgDAgMBAAECggEADqa7+iFEEER5GtX5QosooJnqjDP6+qUCmWW4UT7B3LEyIJP+Wi+sIbelKSqIrSGSAcUXOvW6jVhr12OfmYhUyg/NHlZaToAAhnRzR4Omb9piGA2k8lNCPsaxGut9o9ch0g2PNrDoHLtkEhdFENuV8bbj6gPI/eIwvogKthYXjQhFxiUAxFYfRBuJXBwD+saaw1EeXLXSB+HvzTKn1JTbo83C2Tfw28XQ4gOA0qcqeuXgmfEbrvCv8DtzLXhnrYBSGxup+L/yPuRtz6GA7ELjjrONELuL3FPWATxBRBe6xdmUXRTgtFvXx14xuKlznWV3u7r1Dtx1F6UYnQEqiPyhyQKBgQD7MXz72Iy3Cd7pQIg5WixB2LA/T0jaQTsVxWk62Bksy1POPi6z4UcDpUwjHypPg9+iH/R4O6p1ch0D3ckcWn9Vq9zmQhCyEtIT8YwF3UTkWEtgNdYNLu090KlktvsiaHphP8YQ2xnja+9x6aHSkUXFr/hG0qehypEJs6KiIEpN7wKBgQCSvDCnMhi9L03S3yqKm6LCNf5QiaAgZASSx1/klIoqxbkCMoL4y7VA7fXvv1jhUdGxPzP5ftwXigTl1iVzN+kOVN7cE+X3uoV09DeoWHpDaayiT7uzgQh3a5QoshejqO9kqnGQyFvJWrERxnpWzPQx19frHeaWpVUSZyzC8d4LLQKBgD3av9YcSvo/mf6B/4lv5PoJ68yLL3tfGLeRtwngcMKRBwAAW+u7i4sgRxFW+Uo/RPBdQgvhVWY6RV7QXFd5CWYFPg4NMqNG8ygqELrY87/u5hC48Q6qAY15Nl0wmcKtS4Vq+NDpwwQ3bez1BP4e+7lkTZTTBqhWg0SKUX/ux51NAoGBAIWvuXAK/2kVuQS/WCXyiEYDJVHBIceA+H218hLyljND8zwiQxtjlR6z7tbgcZzPi4m+OJ8+Sk2T7MRt567iqI3rGyRNaL7cDA0zF6Zj/fh/Rc6XcD6p98m9lL3DDDCKkJkZhOBQ+vcnjp3QQ9PjZTQoH8tRca2csjZTFAAElrV9AoGBAMuHPUb1IADjcw8ZCUpAW8EAqSEi1BiBF9MP0b1oGHsVJrOYjqf02ZiP5aMdiT7H8gUSHpwuIkKW10Y1VTw2waQ9D5tAlFA6XLoG5xFLGyKKew7Yv9LYuZ+b2gAh0CgE/UbLZIm0C84ptubHDOGUyKMYUUF4HVkZahjGXQdrmvmv";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        showToast(getString(R.string.pay_success)+payResult);
                        //showAlert(PayDemoActivity.this, getString(R.string.pay_success) + payResult);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showToast(getString(R.string.pay_failed) + payResult);
                        //showAlert(PayDemoActivity.this, getString(R.string.pay_failed) + payResult);
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        showToast(getString(R.string.auth_success) + authResult);
                        //showAlert(PayDemoActivity.this, getString(R.string.auth_success) + authResult);
                    } else {
                        // 其他状态值则为授权失败
                        showToast(getString(R.string.auth_failed) + authResult);
                        //showAlert(PayDemoActivity.this, getString(R.string.auth_failed) + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    @Override
    protected int bindLayout() {
        return R.layout.activity_pay;
    }

    @Override
    protected FinalceViewModel createVM() {
        return null;
    }

    @Override
    protected void setBinding() {
        ARouter.getInstance().inject(this);
        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        binding.setImgPath("http://hbimg.b0.upaiyun.com/0cdfedffcedb13445e4def3f2d6891bb32cb03de828b-m2zK4U_fw658");
        binding.setActivity(this);
    }

    @BindingAdapter("imgsPic")
    public static void imgPic(ImageView imageView,String path){
        Glide.with(BaseApplication.getContext())
                .load(path)
                .into(imageView);
    }

    public void payClick(View view){
        showToast(""+status);
        if(status == 1){
            payV2();
        }else if(status == 2){

        }else if(status == 3){

        }

    }

    public void payV2() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            showToast(getString(R.string.error_missing_appid_rsa_private));
            //showAlert(this, getString(R.string.error_missing_appid_rsa_private));
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }
}
