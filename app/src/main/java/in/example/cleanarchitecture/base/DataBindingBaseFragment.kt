package `in`.example.cleanarchitecture.base

import `in`.example.cleanarchitecture.NoInternetException
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 *This is the base class for all the fragments
 * @param V is a viewbinding object ( usually autogenerated) belonging to the screen
 * @param VM is a viewmodel ( the new architecture is going to be MVVM) and the viewmodel class
 * that is passed needs to extend the androids lifecycle aware viewmode class
 *
 **/
abstract class DataBindingBaseFragment<in V : ViewDataBinding> : Fragment() {

  companion object {
    const val NO_LAYOUT = 0
  }

  protected open val layoutResource = NO_LAYOUT

  protected var loadingState: LiveData<BaseViewState<*>>? = null

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    if (layoutResource == NO_LAYOUT) return null

    val binding = DataBindingUtil.inflate<V>(inflater, layoutResource, container, false)
    binding.lifecycleOwner = this
    onViewDataBindingCreated(binding)
    setBaseStates()
    observeChanges()
    return binding.root
  }

  private fun observeChanges() {
    loadingState?.observe(viewLifecycleOwner, Observer {
      it?.let {
        if (it.isLoading()) {
          showProgress()
        } else {
          hideProgress()
        }

        if (it.error is NoInternetException) {
          showInternetError()
        } else {
          hideInternetError()
        }
      }
    })
  }

  protected abstract fun onViewDataBindingCreated(binding: V)

  protected abstract fun setBaseStates()

  private fun showProgress() {
    activity?.also {
      if (it is BaseView) {
        it.showProgress()
      }
    }
  }

  private fun hideProgress() {
    activity?.also {
      if (it is BaseView) {
        it.hideProgress()
      }
    }
  }

  private fun showInternetError() {
    activity?.also {
      if (it is BaseView) {
        it.showInternetError()
      }
    }
  }

  private fun hideInternetError() {
    activity?.also {
      if (it is BaseView) {
        it.hideInternetError()
      }
    }
  }

  fun getDisplayMetrics(): DisplayMetrics {
    val display = activity?.windowManager?.defaultDisplay
    val metrics = DisplayMetrics()
    display?.getMetrics(metrics)
    return metrics
  }
}
