package `in`.example.cleanarchitecture.di

import `in`.example.cleanarchitecture.view.WordViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
  viewModel { WordViewModel() }
}