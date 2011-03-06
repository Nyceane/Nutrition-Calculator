package com.paschar.nutrition;

import com.paypal.android.MEP.PayPalResultDelegate;

import java.io.Serializable;

import java.io.Serializable;

import com.paypal.android.MEP.PayPalResultDelegate;

public class PaymentResultDelegate implements PayPalResultDelegate, Serializable {

	private static final long serialVersionUID = 10001L;

    public void onPaymentSucceeded(String payKey, String paymentStatus) {
		Payment.resultTitle = "SUCCESS";
		Payment.resultInfo = "You have successfully completed your transaction.";
		Payment.resultExtra = "Key: " + payKey;
	}

	public void onPaymentFailed(String paymentStatus, String correlationID,
			String payKey, String errorID, String errorMessage) {
		Payment.resultTitle = "FAILURE";
		Payment.resultInfo = errorMessage;
		Payment.resultExtra = "Error ID: " + errorID + "\nCorrelation ID: "
				+ correlationID + "\nPay Key: " + payKey;
	}

	public void onPaymentCanceled(String paymentStatus) {
		Payment.resultTitle = "CANCELED";
		Payment.resultInfo = "The transaction has been cancelled.";
		Payment.resultExtra = "";
	}
}