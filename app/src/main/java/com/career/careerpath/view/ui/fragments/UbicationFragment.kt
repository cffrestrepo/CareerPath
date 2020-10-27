package com.career.careerpath.view.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.career.careerpath.R
import com.career.careerpath.model.Ubication
import com.google.android.gms.dynamic.SupportFragmentWrapper
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

class UbicationFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_ubication, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.mapa) as SupportMapFragment
        mapFragment.getMapAsync(this@UbicationFragment)
    }

    override fun onMapReady(googleMap: GoogleMap?) {

        val ubication = Ubication()
        val zoom = 16f
        val centerMap = LatLng(ubication.latitude.toDouble(), ubication.longitude.toDouble())

        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(centerMap, zoom))

        val centerMark = LatLng(ubication.latitude.toDouble(), ubication.longitude.toDouble())
        val markeroptions = MarkerOptions()
        markeroptions.position(centerMark)
        markeroptions.title("Cris")
/*
        val bitmapDraw = context?.applicationContext?.let {
            ContextCompat.getDrawable(it, R.drawable.ic_map_marker)
        } as BitmapDrawable

        val smallMarker = Bitmap.createScaledBitmap(bitmapDraw.bitmap, 150, 150, false)
        markeroptions.icon(BitmapDescriptorFactory.fromBitmap(smallMarker))
        
 */

        googleMap?.addMarker(markeroptions)
        googleMap?.setOnMarkerClickListener(this)

    }

    override fun onMarkerClick(p0: Marker?): Boolean {
        findNavController().navigate(R.id.ubicationDetailDialogFragment)
        return true
    }

}