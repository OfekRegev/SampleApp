package com.ofek.sample.domain.base

abstract class BaseUseCase<in Params : Any, out Type : Any?> {

    abstract fun run(params: Params?): Type

    open operator fun invoke(params: Params?): Type {
        return run(params)
    }

    open operator fun invoke(): Type {
        return run(null)
    }

    class None
}