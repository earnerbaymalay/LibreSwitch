package com.libreswitch

data class AppMapping(
    val proprietaryPackage: String,
    val proprietaryName: String,
    val fossName: String,
    val fossDescription: String,
    val fossUrl: String,
    val category: String
)

object Database {
    val alternatives = listOf(
        // BROWSERS
        AppMapping("com.android.chrome", "Google Chrome", "Mull", "Hardened Firefox.", "https://f-droid.org/packages/us.spotco.fennec_dos/", "Browser"),
        AppMapping("com.google.android.googlequicksearchbox", "Google App", "Whoogle", "Self-hosted search.", "https://github.com/benbusby/whoogle-search", "Browser"),
        AppMapping("com.microsoft.emmx", "Microsoft Edge", "Fennec", "Firefox with no telemetry.", "https://f-droid.org/packages/org.mozilla.fennec_fdroid/", "Browser"),
        AppMapping("com.sec.android.app.sbrowser", "Samsung Internet", "Bromite", "Chromium with adblock.", "https://www.bromite.org/", "Browser"),

        // MEDIA
        AppMapping("com.google.android.youtube", "YouTube", "NewPipe", "No ads, background play.", "https://newpipe.net/", "Video"),
        AppMapping("com.google.android.apps.youtube.music", "YT Music", "InnerTune", "Material You YT Music.", "https://github.com/z-huang/InnerTune", "Music"),
        AppMapping("com.spotify.music", "Spotify", "Spotube", "Open source Spotify client.", "https://spotube.krtirtho.com/", "Music"),
        AppMapping("tv.twitch.android.app", "Twitch", "Twire", "Ad-free Twitch.", "https://github.com/twireapp/Twire", "Video"),

        // SOCIAL
        AppMapping("com.twitter.android", "Twitter/X", "Squawker", "Anonymous Twitter.", "https://github.com/j-fbriere/squawker", "Social"),
        AppMapping("com.zhiliaoapp.musically", "TikTok", "ProxiTok", "Open frontend for TikTok.", "https://proxitok.herokuapp.com/", "Social"),
        AppMapping("com.instagram.android", "Instagram", "Instander", "Privacy Instagram mod.", "https://thedise.me/instander/", "Social"),
        AppMapping("com.facebook.katana", "Facebook", "Frost", "Facebook Wrapper.", "https://github.com/AllanWang/Frost-for-Facebook", "Social"),
        AppMapping("com.reddit.frontpage", "Reddit", "Infinity", "Feature-rich Reddit client.", "https://github.com/Docile-Alligator/Infinity-For-Reddit", "Social"),
        AppMapping("com.discord", "Discord", "Aliucord", "Discord with plugins.", "https://github.com/Aliucord/Aliucord", "Chat"),

        // MESSAGING
        AppMapping("com.whatsapp", "WhatsApp", "Signal", "Encrypted messaging.", "https://signal.org/", "Chat"),
        AppMapping("org.telegram.messenger", "Telegram", "NekoX", "Telegram fork.", "https://github.com/NekoX-Dev/NekoX", "Chat"),
        AppMapping("com.facebook.orca", "Messenger", "SimpleX", "Anonymous chat.", "https://simplex.chat/", "Chat"),
        AppMapping("com.google.android.apps.messaging", "Google Messages", "QKSMS", "Open source SMS.", "https://github.com/moezbhatti/qksms", "Chat"),

        // TOOLS
        AppMapping("com.google.android.gm", "Gmail", "K-9 Mail", "Advanced Email.", "https://k9mail.app/", "Mail"),
        AppMapping("com.google.android.calendar", "Google Calendar", "Etar", "Open source calendar.", "https://github.com/Etar-Group/Etar-Calendar", "Tool"),
        AppMapping("com.google.android.apps.docs", "Google Docs", "LibreOffice", "Document viewer.", "https://www.libreoffice.org/", "Office"),
        AppMapping("com.google.android.inputmethod.latin", "Gboard", "Heliboard", "Privacy keyboard.", "https://github.com/Helium314/HeliBoard", "Tool"),
        
        // SYSTEM
        AppMapping("com.google.android.apps.maps", "Google Maps", "Organic Maps", "Offline Maps.", "https://organicmaps.app/", "Maps"),
        AppMapping("com.google.android.apps.photos", "Google Photos", "Aves", "Privacy Gallery.", "https://github.com/deckerst/aves", "Gallery"),
        AppMapping("com.google.android.apps.authenticator2", "Google Auth", "Aegis", "Secure 2FA.", "https://getaegis.app/", "Security"),
        AppMapping("com.android.vending", "Play Store", "Aurora Store", "Anonymous Store.", "https://auroraoss.com/", "Store")
    )
}
