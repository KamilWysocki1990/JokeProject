package joke.k.myapplication.login.drawer.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.util.Calendar;

import joke.k.myapplication.R;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    TimeSetListenerForParentActivity TimePickerListener;


    public interface TimeSetListenerForParentActivity {
        void providingTimeFromTimePicker(int hour, int minute);
        void cancelSignal();
    }

    public TimePickerFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof TimeSetListenerForParentActivity) {
            TimePickerListener = (TimeSetListenerForParentActivity) context;

        } else {
            throw new ClassCastException(context.toString() + getString(R.string.class_cast_exception_in_time_picker));
        }
    }

    int choosenhour, chosenminutes;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        // Use the current time as the default values for the picker
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);


        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), android.R.style.Theme_Holo_Dialog, this, hour, minute, true);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        choosenhour = hourOfDay;
        chosenminutes = minute;
        sendingTime();
    }

    public void sendingTime() {
        TimePickerListener.providingTimeFromTimePicker(choosenhour, chosenminutes);
    }



    @Override
    public void onDetach() {
        TimePickerListener = null;
        super.onDetach();


    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        TimePickerListener.cancelSignal();
    }

    private void sendingCancelButtonClick() {
        TimePickerListener.cancelSignal();
    }
}