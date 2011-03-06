package com.paschar.nutrition;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.*;

import com.paschar.nutrition.logics.Membership;
import com.paypal.android.MEP.*;

import java.math.BigDecimal;

public class Payment extends Activity implements View.OnClickListener {

    private TextView title;
	private TextView info;
	private TextView extra;

    private ScrollView scroller;
    private Button exitApp;
    private TextView labelSimplePayment;
    private CheckoutButton launchSimplePayment;
    private LinearLayout layoutSimplePayment;

    protected static final int INITIALIZE_SUCCESS = 0;
    protected static final int INITIALIZE_FAILURE = 1;

    private static final int server = PayPal.ENV_NONE;
    private static final String appID = "APP-80W284485P519543T";
    private static final int request = 1;

    public static String resultTitle;
	public static String resultInfo;
	public static String resultExtra;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initLibrary();

        // Setup our UI.
		scroller = new ScrollView(this);
		scroller.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
		scroller.setBackgroundColor(Color.BLACK);

		LinearLayout content = new LinearLayout(this);
		content.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
		content.setGravity(Gravity.CENTER_HORIZONTAL);
		content.setOrientation(LinearLayout.VERTICAL);
		content.setPadding(10, 10, 10, 10);
		content.setBackgroundColor(Color.TRANSPARENT);

		layoutSimplePayment = new LinearLayout(this);
		layoutSimplePayment.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
		layoutSimplePayment.setGravity(Gravity.CENTER_HORIZONTAL);
		layoutSimplePayment.setOrientation(LinearLayout.VERTICAL);
		layoutSimplePayment.setPadding(0, 5, 0, 5);

        labelSimplePayment = new TextView(this);
		labelSimplePayment.setLayoutParams(new LinearLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
		labelSimplePayment.setPadding(0, 5, 0, 5);
		labelSimplePayment.setGravity(Gravity.CENTER_HORIZONTAL);
		labelSimplePayment.setTextSize(20.0f);
		labelSimplePayment.setVisibility(TextView.VISIBLE);
		labelSimplePayment.setText("Pay the $45 annual subscription fee:");
		content.addView(labelSimplePayment);

        PayPal pp = PayPal.getInstance();
        launchSimplePayment = pp.getCheckoutButton(this, PayPal.BUTTON_194x37, CheckoutButton.TEXT_PAY);
        launchSimplePayment.setOnClickListener(this);
        layoutSimplePayment.addView(launchSimplePayment);
        content.addView(layoutSimplePayment);

        title = new TextView(this);
		title.setLayoutParams(new LinearLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
		title.setPadding(0, 5, 0, 5);
		title.setGravity(Gravity.CENTER_HORIZONTAL);
		title.setTextSize(30.0f);
		title.setVisibility(TextView.GONE);
		content.addView(title);

		info = new TextView(this);
		info.setLayoutParams(new LinearLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
		info.setPadding(0, 5, 0, 5);
		info.setGravity(Gravity.CENTER_HORIZONTAL);
		info.setTextSize(20.0f);
		info.setVisibility(TextView.VISIBLE);
		content.addView(info);

		extra = new TextView(this);
		extra.setLayoutParams(new LinearLayout.LayoutParams(FrameLayout.LayoutParams.FILL_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT));
		extra.setPadding(0, 5, 0, 5);
		extra.setGravity(Gravity.CENTER_HORIZONTAL);
		extra.setTextSize(12.0f);
		extra.setVisibility(TextView.GONE);
		content.addView(extra);

        LinearLayout layoutExit = new LinearLayout(this);
		layoutExit.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT));
		layoutExit.setGravity(Gravity.CENTER_HORIZONTAL);
		layoutExit.setOrientation(LinearLayout.VERTICAL);
		layoutExit.setPadding(0, 15, 0, 5);

        exitApp = new Button(this);
		exitApp.setLayoutParams(new FrameLayout.LayoutParams(200, FrameLayout.LayoutParams.WRAP_CONTENT)); //Semi mimic PP button sizes
		exitApp.setOnClickListener(this);
		exitApp.setText("Exit");
		layoutExit.addView(exitApp);
		content.addView(layoutExit);

        scroller.addView(content);
		setContentView(scroller);
    }

    private void initLibrary() {
        PayPal pp = PayPal.getInstance();
        if (pp == null) {
            pp = PayPal.initWithAppID(this, appID, server);

            pp.setLanguage("en_US");
            pp.setFeesPayer(PayPal.FEEPAYER_EACHRECEIVER);
            pp.setShippingEnabled(true);
            pp.setDynamicAmountCalculationEnabled(false);
        }
    }

    public void setupButtons() {
        PayPal pp = PayPal.getInstance();
        launchSimplePayment = pp.getCheckoutButton(this, PayPal.BUTTON_194x37, CheckoutButton.TEXT_PAY);
        launchSimplePayment.setOnClickListener(this);
        layoutSimplePayment.addView(launchSimplePayment);
    }

	private PayPalPayment simplePayment() {
		PayPalPayment payment = new PayPalPayment();
       	payment.setCurrencyType("USD");
    	payment.setRecipient("example-merchant-1@paypal.com");
    	payment.setSubtotal(new BigDecimal("45.00"));
    	payment.setPaymentType(PayPal.PAYMENT_TYPE_GOODS);
    	PayPalInvoiceData invoice = new PayPalInvoiceData();
    	invoice.setTax(new BigDecimal("4.28"));
    	invoice.setShipping(new BigDecimal("0.00"));
    	PayPalInvoiceItem item1 = new PayPalInvoiceItem();
    	item1.setName("Annual subscription fee");
    	item1.setID("87239");
    	item1.setTotalPrice(new BigDecimal("45.00"));
    	item1.setUnitPrice(new BigDecimal("45.00"));
    	item1.setQuantity(1);
    	invoice.getInvoiceItems().add(item1);
    	payment.setInvoiceData(invoice);
    	payment.setMerchantName("The Nutrition Cal Store");
    	payment.setDescription("Quite a simple payment");
    	payment.setCustomID("8873482296");
    	payment.setIpnUrl("http://www.exampleapp.com/ipn");
    	payment.setMemo("You've bought one year subscription. Thank you!");

    	return payment;
	}

    public void onClick(View view) {

		if(view == launchSimplePayment) {
			PayPalPayment payment = simplePayment();
			Intent checkoutIntent = PayPal.getInstance().checkout(payment, this, new PaymentResultDelegate());
	    	startActivityForResult(checkoutIntent, request);
        }
        else if(view == exitApp) {
            Intent intent = new Intent();
            setResult(RESULT_OK, intent);
			finish();
        }
    }

    public void showFailure() {
        title.setText("FAILURE");
        info.setText("Could not initialize the PayPal library.");
        title.setVisibility(View.VISIBLE);
        info.setVisibility(View.VISIBLE);
    }


	public void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if(requestCode != request)
    		return;

    	if(resultCode == Activity.RESULT_OK)
    	{
    		Membership.MemberActivation(this);
    	}
    	
    	launchSimplePayment.updateButton();

    	title.setText(resultTitle);
    	title.setVisibility(View.VISIBLE);
    	info.setText(resultInfo);
    	info.setVisibility(View.VISIBLE);
    }
}