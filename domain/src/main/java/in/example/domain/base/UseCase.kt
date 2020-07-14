package `in`.example.domain.base

interface UseCase<P> {
    interface Callback {
        fun onSuccess()
        fun onError(throwable: Throwable)
    }

    suspend fun execute(param: P, callback: Callback)
}