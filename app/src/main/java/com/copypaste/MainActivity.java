package com.copypaste;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		final EditText content = (EditText) findViewById(R.id.content);

		findViewById(R.id.paste).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				String text = getFirstClipItemText();
				content.setText(text);
				Intent intent = new Intent(Intent.ACTION_SEND)
						.setType("text/plain")
						.putExtra(Intent.EXTRA_TEXT, text);
				startActivity(Intent.createChooser(intent, "Share via"));
			}
		});
	}

	private String getFirstClipItemText() {

		String pastContent = "";
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
			ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			ClipData clipData = clipboardManager.getPrimaryClip();
			ClipData.Item item = clipData.getItemAt(0);
			if (item != null) {
				pastContent = item.getText().toString();
			}
		} else {
			android.text.ClipboardManager clipboardManager = (android.text.ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
			pastContent = clipboardManager.getText().toString();
		}
		return pastContent;
	}
}
