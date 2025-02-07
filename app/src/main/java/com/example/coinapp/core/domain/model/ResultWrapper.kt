package com.example.coinapp.core.domain.model

sealed class ResultWrapper<T> {
    data class Success<T>(
        val data: T
    ) : ResultWrapper<T>()

    data class Error<T>(
        val failure: Failure
    ) : ResultWrapper<T>()

    val isSuccess: Boolean get() = this is Success

    val isError: Boolean get() = this is Error

    companion object {
        inline fun <T> runCatching(block: () -> T): ResultWrapper<T> {
            return try {
                success(block())
            } catch (e: Throwable) {
                error(e)
            }
        }

        fun <T> error(e: Throwable): Error<T> = Error(
            failure = Failure(e)
        )

        fun <T> success(data: T): Success<T> = Success(
            data = data
        )
    }

    fun onSuccess(block: (T) -> Unit): ResultWrapper<T> {
        if (this is Success)
            block(this.data)

        return this
    }

    fun onError(block: (Failure) -> Unit): ResultWrapper<T> {
        if (this is Error)
            block(this.failure)

        return this
    }

    override fun toString(): String {
        return when (this) {
            is Error -> this.failure.toString()
            is Success -> "Success(${this.data})"
        }
    }
}
