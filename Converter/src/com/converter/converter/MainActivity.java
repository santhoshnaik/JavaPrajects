package com.converter.converter;




import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final	EditText number=(EditText)findViewById(R.id.MultiNumber);
			
			Button button=(Button)findViewById(R.id.button1);
			
			button.setOnClickListener(new OnClickListener()
			{
				
				public void onClick(View a)
				{    
					
					double numr=Double.valueOf(number.getText().toString());
					if(numr==0.0)
					{
						number.setText("missing number??");
					}
					double value=numr*2;
					
					number.setText(String.valueOf(value));
				}
				
			});
			
	}

}
