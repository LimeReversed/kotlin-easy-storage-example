package eu.limereversed.easy_storage_example

import android.app.Application

// Creates the database
class GraphApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}