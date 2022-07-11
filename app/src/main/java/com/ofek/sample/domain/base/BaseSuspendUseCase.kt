package com.ofek.sample.domain.base

abstract class BaseSuspendUseCase<in Params : Any, out Type : Any?> {

    protected abstract suspend fun run(params: Params?): Type

    open suspend operator fun invoke(params: Params?): Type {
        return run(params)
    }

    open suspend operator fun invoke(): Type {
        return run(null)
    }

    class None
}