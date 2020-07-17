package `in`.example.cleanarchitecture.view

import `in`.example.cleanarchitecture.base.DataBindingBaseFragment
import `in`.example.cleanarchitecture.databinding.FragmentWordBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class WordFragment: DataBindingBaseFragment<FragmentWordBinding>() {

  private val model: WordViewModel by viewModel()

  override fun onViewDataBindingCreated(binding: FragmentWordBinding) {
    binding.viewModel = model
  }

  override fun setBaseStates() {

  }
}