
package com.justhire.common;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public abstract class RestWSHandler extends Handler
{
	public void handleMessage(Message msg)
	{
		Log.d("Handler", "Got message");
		super.handleMessage(msg);
		switch (msg.what)
		{
			case 1:// success
				Log.d("Handler", "Got success result");
				resultSuccess(msg);
				break;
			case 2:// failed
				Log.d("Handler", "Got failed result");
				resultFailed(msg);
				break;
			case 3:
				Log.d("Handler", "Got no internet result");
				resultFailed(msg);
				break;
		}
	}

	public void resultNoInternet(Context context)
	{
		Toast.makeText(context, "Sorry, no internet detected!", Toast.LENGTH_SHORT).show();
	}

	// abstract methods
	public abstract void resultSuccess(Message msg);

	public abstract void resultFailed(Message msg);
}
