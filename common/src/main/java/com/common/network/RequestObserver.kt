package com.common.network

import com.common.throwe.BaseResponseThrowable
import com.yx.coroutine.ThrowableHandler.handleThrowable
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.exceptions.CompositeException
import io.reactivex.exceptions.Exceptions
import io.reactivex.functions.Consumer
import io.reactivex.internal.disposables.DisposableHelper
import io.reactivex.internal.functions.Functions
import io.reactivex.observers.LambdaConsumerIntrospection
import io.reactivex.plugins.RxJavaPlugins
import java.util.concurrent.atomic.AtomicReference

/**
 * create by 2020/9/12
 * rxjavaObserver
 * @author yx
 */
class RequestObserver<T>(
    val onSuccesss:(T)->Unit,
    val onErrors: (BaseResponseThrowable)->Unit
) : AtomicReference<Disposable?>(), SingleObserver<T>, Disposable {
    override fun onError(e: Throwable) {
        lazySet(DisposableHelper.DISPOSED)
        try {
            onErrors(handleThrowable(e))
        } catch (ex: Throwable) {
            Exceptions.throwIfFatal(ex)
            RxJavaPlugins.onError(CompositeException(e, ex))
        }
    }

    override fun onSubscribe(d: Disposable) {
        DisposableHelper.setOnce(this, d)
    }

    override fun onSuccess(value: T) {
        lazySet(DisposableHelper.DISPOSED)
        try {
            onSuccesss(value)
        } catch (ex: Throwable) {
            Exceptions.throwIfFatal(ex)
            RxJavaPlugins.onError(ex)
        }
    }

    override fun dispose() {
        DisposableHelper.dispose(this)
    }

    override fun isDisposed(): Boolean {
        return get() === DisposableHelper.DISPOSED
    }


    companion object {
        private const val serialVersionUID = -7012088219455310786L
    }

}