package com.kryptkode.farmz.screens.datedialog

import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import androidx.navigation.fragment.navArgs
import com.kryptkode.farmz.app.utils.date.DateParser
import com.kryptkode.farmz.screens.common.dialog.BaseDialog
import com.kryptkode.farmz.screens.common.dialog.DialogEventBus
import javax.inject.Inject

class DateDialog : BaseDialog(),
    DatePickerDialog.OnDateSetListener {

    @Inject
    lateinit var dialogEventBus: DialogEventBus

    @Inject
    lateinit var dateParser: DateParser

    private val args by navArgs<DateDialogArgs>()


    override fun onAttach(context: Context) {
        super.onAttach(context)
        controllerComponent.inject(this)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val yearMonthDayTriple = dateParser.convertToYearMonthDay(args.date)
        val year = yearMonthDayTriple.first
        val month = yearMonthDayTriple.second
        val day = yearMonthDayTriple.third
        return DatePickerDialog(requireContext(), this, year, month, day)
    }

    override fun onDateSet(p0: DatePicker?, year: Int, month: Int, day: Int) {
        val date = dateParser.convertToDate(year, month, day)
        dialogEventBus.postEvent(DateSelectedEvent(date))
    }
}