package `in`.example.cleanarchitecture.base.adapter

import `in`.example.cleanarchitecture.base.ViewOnClickListener
import androidx.databinding.ViewDataBinding

abstract class BaseViewHolderBindingFactory {
    abstract fun create(binding: ViewDataBinding, viewType: Int, viewClickCallback: ViewOnClickListener?): BaseBindingViewHolder<out BaseBindingRVModel>
}
