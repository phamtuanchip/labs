package com.demo.music.service;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnInfoListener;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;

public class MusicService extends Service implements OnInfoListener {
	public static final String MEDIA_PATH = "MEDIA PATH";
	private MediaPlayer player;
	private String path;
	private final IBinder mBinder = new LocalBinder();

	@Override
	public IBinder onBind(Intent intent) {
		path = intent.getStringExtra(MEDIA_PATH);
		return mBinder;
	}

	@Override
	public void onCreate() {

		super.onCreate();
		player = new MediaPlayer();
		player.setOnInfoListener(this);
	}

	public void playMusic() {
		try {
			Uri myUri = Uri.parse(path);
			player.setDataSource(getApplicationContext(), myUri);
			player.prepare();
			player.start();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void pauseMusic() {
		player.pause();
	}

	public void resumeMusic() {
		player.start();
	}

	public class LocalBinder extends Binder {
		public MusicService getService() {
			return MusicService.this;
		}
	}

	@Override
	public boolean onInfo(MediaPlayer arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}

}
