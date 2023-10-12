package com.francomucci.storitest.common

sealed class ErrorField {
    object Mail : ErrorField()
    object Password : ErrorField()
    object All : ErrorField()
    object Unknown : ErrorField()
}