package `in`.example.cleanarchitecture.base

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import org.koin.core.KoinComponent

open class RxAwareViewModel : ViewModel(), KoinComponent {

    val disposable = CompositeDisposable()

    override fun onCleared() {
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
        super.onCleared()
    }
}
