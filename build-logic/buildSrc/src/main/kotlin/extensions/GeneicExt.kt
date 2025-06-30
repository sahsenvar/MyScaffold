package extensions

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun <T> T.applyIf(`if`: T.() -> Boolean, noinline block: T.() -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }
    if (`if`())
        block()
    return this
}

@OptIn(ExperimentalContracts::class)
inline fun <T> T.applyIfNot(`if`: T.() -> Boolean, noinline block: T.() -> Unit): T {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }
    if (!`if`(this))
        block()
    return this
}

@OptIn(ExperimentalContracts::class)
inline fun <T> T.require(`if`: T.() -> Boolean, noinline lazyMessage: T.() -> String): T {
    contract {
        callsInPlace(`if`, InvocationKind.EXACTLY_ONCE)
        callsInPlace(lazyMessage, InvocationKind.AT_MOST_ONCE)
    }
    if (!`if`(this)) {
        error(lazyMessage(this))
    }
    return this
}