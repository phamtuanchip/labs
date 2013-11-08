package com.climhz.mapsdemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements OnMarkerClickListener {

	private GoogleMap gMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		LatLng pos = new LatLng(21.002471, 105.856018);
		gMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		gMap.addMarker(new MarkerOptions().position(pos).title("Ha Noi"));

		gMap.setOnMarkerClickListener(this);
		CameraUpdate center = CameraUpdateFactory.newLatLng(pos);
		CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);

		gMap.moveCamera(center);
		gMap.animateCamera(zoom);
	}

	@Override
	public boolean onMarkerClick(Marker arg0) {
		arg0.getPosition();
		return false;
	}
}
