package com.libreswitch

data class AppMapping(
    val proprietaryPackage: String,
    val proprietaryName: String,
    val fossName: String,
    val fossDescription: String,
    val fossUrl: String
)

object Database {
    val alternatives = listOf(
        AppMapping(
            "com.google.android.youtube", "YouTube", 
            "NewPipe", 
            "A lightweight YouTube frontend. No ads, background play.",
            "https://newpipe.net/"
        ),
        AppMapping(
            "com.android.chrome", "Google Chrome", 
            "Mull", 
            "Privacy oriented browser based on Firefox.",
            "https://f-droid.org/packages/us.spotco.fennec_dos/"
        ),
        AppMapping(
            "com.twitter.android", "Twitter/X", 
            "Squawker", 
            "Anonymous Twitter/X client.",
            "https://github.com/j-fbriere/squawker"
        ),
         AppMapping(
            "com.google.android.apps.maps", "Google Maps", 
            "Organic Maps", 
            "Offline maps based on OpenStreetMap.",
            "https://organicmaps.app/"
        )
    )
}
