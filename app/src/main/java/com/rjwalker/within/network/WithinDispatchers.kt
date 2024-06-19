package com.rjwalker.within.network

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val withinDispatcher: WithinDispatchers)

enum class WithinDispatchers {
    Default,
    IO
}