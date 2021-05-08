package com.common.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.common.network.RequestObserver
import com.common.throwe.BaseResponseThrowable
import com.uber.autodispose.AutoDispose
import com.uber.autodispose.AutoDisposeConverter
import com.uber.autodispose.SingleSubscribeProxy
import io.reactivex.CompletableObserver
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.annotations.NonNull
import io.reactivex.annotations.SchedulerSupport
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.internal.functions.ObjectHelper
import io.reactivex.internal.operators.observable.ObservableBlockingSubscribe.subscribe
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import com.uber.autodispose.CompletableSubscribeProxy as CompletableSubscribeProxy


/**
 *create by 2019/12/30
 * 扩展函数基类
 *@author yx
 */
fun Activity.toast(msg: String?, duration: Int = Toast.LENGTH_SHORT) {
    if (TextUtils.isEmpty(msg)){
        return
    }
    Toast.makeText(this, msg, duration).show()
}
fun Activity.toast(msg: Int?, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg!!, duration).show()
}

fun<T> Single<T>.single(): Single<T> = this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

//fun<T> AutoDisposeConverter<T>.ad(pr: ScopeProvider): AutoDisposeConverter<T>? = AutoDispose.autoDisposable<T>(pr)


@CheckReturnValue
@SchedulerSupport(SchedulerSupport.NONE)
fun <T> Single<T>.subscribes(
    onSuccess: (T) -> Unit,
    onError: (BaseResponseThrowable)->Unit
): Disposable {
    ObjectHelper.requireNonNull(onSuccess, "onSuccess is null")
    ObjectHelper.requireNonNull(onError, "onError is null")
    val observer: RequestObserver<T> = RequestObserver(onSuccess, onError)
    subscribe(observer)
    return observer
}
fun <T> SingleSubscribeProxy<T>.subscribes(onSuccess: (T) -> Unit,
                                            onError: (BaseResponseThrowable)->Unit) {
    ObjectHelper.requireNonNull(onSuccess, "onSuccess is null")
    ObjectHelper.requireNonNull(onError, "onError is null")
    val observer: RequestObserver<T> = RequestObserver(onSuccess, onError)
    subscribe(observer)
}







