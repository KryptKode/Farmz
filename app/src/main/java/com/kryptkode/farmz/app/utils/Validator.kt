package com.kryptkode.farmz.app.utils

import android.content.Context
import android.util.Patterns
import androidx.lifecycle.MutableLiveData
import com.kryptkode.farmz.R
import com.kryptkode.farmz.app.utils.livedata.event.Event
import javax.inject.Inject

/**
 * Created by kryptkode on 11/24/2019.
 */
class Validator @Inject constructor(private val context: Context) {

    val emailError = MutableLiveData<Event<String>>()
    val fullNameError = MutableLiveData<Event<String>>()
    val passwordError = MutableLiveData<Event<String>>()


    fun validateEmailAddress(
        email: String?,
        emailError: MutableLiveData<Event<String>>? = null
    ): Boolean {
        if (email.isNullOrEmpty()) {
            emailError?.postValue(Event(context.getString(R.string.validation_email_required_message)))
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailError?.postValue(Event(context.getString(R.string.validation_valid_email_required_message)))
            return false
        }

        return true
    }


    fun validateFullName(
        fullName: String?,
        fullNameError: MutableLiveData<Event<String>>? = null
    ): Boolean {
        if (fullName.isNullOrEmpty()) {
            fullNameError?.postValue(Event(context.getString(R.string.validation_name_required_message)))
            return false
        }

        if (fullName.length < MIN_NAME_CHARS) {
            fullNameError?.postValue(
                Event(
                    context.getString(
                        R.string.validation_name_short_message,
                        MIN_NAME_CHARS
                    )
                )
            )
            return false
        }

        return true
    }

    fun validatePassword(
        password: String?,
        passwordError: MutableLiveData<Event<String>>? = null
    ): Boolean {

        if (password.isNullOrEmpty()) {
            passwordError?.postValue(Event(context.getString(R.string.validation_password_required_message)))
            return false
        }

        if (password.length < MIN_PASSWORD_CHARS) {
            passwordError?.postValue(
                Event(
                    context.getString(
                        R.string.validation_password_short_message,
                        MIN_PASSWORD_CHARS
                    )
                )
            )
            return false
        }
        return true
    }

    fun validateLogin(email: String?, password: String?): Boolean {
//        Hack to ensure all fields are validated at once
        var result = validateEmailAddress(email, emailError)
        result = validatePassword(password, passwordError) || result
        return result &&
                validateEmailAddress(email, emailError) &&
                validatePassword(password, passwordError)
    }

    companion object {
        const val MIN_NAME_CHARS = 4
        const val MIN_PASSWORD_CHARS = 6
    }
}
