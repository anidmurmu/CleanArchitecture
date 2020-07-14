package `in`.example.domain.base

import `in`.example.domain.entity.Result

interface BaseUseCase<T : Any, R: Any> {
  suspend operator fun invoke(param: T): Result<R>
}