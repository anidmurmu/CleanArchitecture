package `in`.example.cleanarchitecture

import `in`.example.cleanarchitecture.di.appModule
import `in`.example.data.di.dataModule
import `in`.example.domain.di.domainModule
import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

  override fun onCreate() {
    super.onCreate()

    startKoin {
      androidContext(this@App)
      modules(listOf(appModule, dataModule, domainModule))
    }

    instance = this
  }

  companion object {
    lateinit var instance: App
  }
}
