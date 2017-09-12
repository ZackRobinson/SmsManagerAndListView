package com.example.zack.smsmanagerandlistview;

import android.Manifest;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.zack.smsmanagerandlistview.ListViewActivity;
import com.example.zack.smsmanagerandlistview.R;

//Have a feature to send text directly from the app.
//Use SmsManager class. You will have two Edittexts, “Message” and “Recipient”
public class ShortMessageServiceActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 1;
    private static final String TAG = "shortMessageServiceTag";
    Button btnSend;
    EditText etRecipient;
    EditText etMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_short_message_service);
        btnSend = (Button) findViewById(R.id.btnSend);
        etRecipient = (EditText) findViewById(R.id.etRecipient);
        etMessage = (EditText) findViewById(R.id.etMessage);

        btnSend.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String recipient = etRecipient.getText().toString();
                String message = etMessage.getText().toString();
                Log.d(TAG, "onClick: " + recipient);
                Log.d(TAG, "onClick: " + message);
                // Request user permission to send a message.
                // SMSManager will show an error without this.
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_DENIED) {
                        Log.d("permission", "permission denied to SEND_SMS - requesting it");
                        String[] permissions = {Manifest.permission.SEND_SMS};
                        requestPermissions(permissions, PERMISSION_REQUEST_CODE);
                    }
                }

                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(recipient, null, message, null, null);
                } catch (Exception e) {
                    Log.d(TAG, "onClick: ");
                    e.printStackTrace();
                }
                postNotification();
            }
        });
    }

    public void postNotification() {
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this).setSmallIcon(R.drawable.notification_icon).setContentTitle("Way to go! You sent a message.").setContentText("Click to go back to the List View.");
        Intent resultIntent = new Intent(this, ListViewActivity.class);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        int mNotificationId = 001;
        NotificationManager mNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        mNotifyMgr.notify(mNotificationId, builder.build());
    }


}
