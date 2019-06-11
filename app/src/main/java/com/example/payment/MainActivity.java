package com.example.payment;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity  implements PaymentResultListener {
   int paymentamount;
   Button btnpay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Checkout.preload(getApplicationContext());

  btnpay=findViewById(R.id.btnpay);
  btnpay.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          startpayment();
      }
  });

    }

    private void startpayment() {
    final Activity activity=this;

    final Checkout checkout=new Checkout();
        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Corp");
            options.put("description", "Demoing Charges");
            //You can omit the image option to fetch the image from dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            options.put("amount", "100");

            JSONObject preFill = new JSONObject();
            preFill.put("email", "test@razorpay.com");
            preFill.put("contact", "9876543210");

            options.put("prefill", preFill);

            checkout.open(activity, options);
        } catch (Exception e) {
            Toast.makeText(activity, "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
            e.printStackTrace();
        }

    }


    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(this,"success",Toast.LENGTH_LONG).show();


    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this,"Fail",Toast.LENGTH_LONG).show();
    }
}
