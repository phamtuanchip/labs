package com.demo.music;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.demo.music.receiver.BroadcastCalling;
import com.demo.music.service.MusicService;

public class MainActivity extends Activity implements OnClickListener {
	private String TAG = "MainActivity";
	private Button mBtnPlayMusic;
	private Button mBtnPauseMusic;
	private boolean mIsConnected;
	private BroadcastCalling calling = new BroadcastCalling() {

		@Override
		public void onIncommingCall() {
			Log.e(TAG, "Incomming call");
		}

		@Override
		public void onIdle() {
			Log.e(TAG, "Idle call");
		}
	};
	private Button mBtnStartService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mBtnPlayMusic = (Button) findViewById(R.id.btn_play_music);
		mBtnPauseMusic = (Button) findViewById(R.id.btn_pause_music);
		mBtnStartService = (Button) findViewById(R.id.btn_start_service);
		mIsConnected = false;
		mBtnStartService.setOnClickListener(this);
		mBtnPlayMusic.setOnClickListener(this);
		mBtnPauseMusic.setOnClickListener(this);

		IntentFilter filter = new IntentFilter(
				"android.intent.action.PHONE_STATE");
		registerReceiver(calling, filter);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(calling);
	}

	private MusicService mBoundService;

	private ServiceConnection mConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			// This is called when the connection with the service has been
			// established, giving us the service object we can use to
			// interact with the service. Because we have bound to a explicit
			// service that we know is running in our own process, we can
			// cast its IBinder to a concrete class and directly access it.
			mIsConnected = true;
			mBoundService = ((MusicService.LocalBinder) service).getService();

			// Tell the user about this for our demo.
			Toast.makeText(MainActivity.this, "Service connected",
					Toast.LENGTH_SHORT).show();
		}

		public void onServiceDisconnected(ComponentName className) {
			// This is called when the connection with the service has been
			// unexpectedly disconnected -- that is, its process crashed.
			// Because it is running in our same process, we should never
			// see this happen.
			mIsConnected = false;
			mBoundService = null;
			// Tell the user about this for our demo.
			Toast.makeText(MainActivity.this, "Service disconnected",
					Toast.LENGTH_SHORT).show();
		}
	};

	@Override
	public void onClick(View v) {
		if (v == mBtnStartService) {
			if (!mIsConnected) {
				String path = "http://stream2.s3.mp3.zdn.vn/fsfsdfdsfdserwrwq3/68956c50c43a1e7f8624dc527aead55d/51dc16a8/2013/02/11/8/a/8ae06827a6d0311495bcce2295985b8e.mp3";
				Intent intent = new Intent(this, MusicService.class);
				intent.putExtra(MusicService.MEDIA_PATH, path);
				bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
			}
		} else if (v == mBtnPlayMusic) {
			if (mIsConnected)
				mBoundService.playMusic();
		} else if (v == mBtnPauseMusic) {
			if (mIsConnected)
				mBoundService.pauseMusic();
		}
	}

}
