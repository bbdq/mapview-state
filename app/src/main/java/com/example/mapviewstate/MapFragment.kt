package com.example.mapviewstate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.libraries.maps.MapView

class MapFragment: Fragment() {

    private var mapViewBundle: Bundle? = null

    private var _mapView: MapView? = null
    private val mapView: MapView
        get() = _mapView!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_map, container, false)

        _mapView = view.findViewById(R.id.map)
        mapView.onCreate(mapViewBundle)

        view.findViewById<Button>(R.id.open_details).setOnClickListener {
            findNavController().navigate(R.id.detailsFragment)
        }

        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY) ?: Bundle().also {
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, it)
        }
        mapView.onSaveInstanceState(mapViewBundle)
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        mapView.onPause()
        super.onPause()
    }

    override fun onStop() {
        mapView.onStop()
        super.onStop()
    }

    override fun onDestroyView() {
        mapView.onDestroy()
        _mapView = null
        super.onDestroyView()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }


    companion object {
        private const val MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey"
    }
}