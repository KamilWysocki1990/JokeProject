package joke.k.myapplication.login.drawer.fragments;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    newInterface timeListener;

    public interface newInterface{
        void providingTimeFromTimePicker(int hour, int minute);

    }
    public  TimePickerFragment(){}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof newInterface ){
            timeListener = (newInterface) context;

        } else {
            throw new ClassCastException(context.toString()+"tralalala");
        }
    }

    int choosenhour,chosenminutes;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the current time as the default values for the picker

        final Calendar c = Calendar.getInstance();
        int  hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);



        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(),android.R.style.Theme_Holo_Dialog, this, hour, minute, true );
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        choosenhour = hourOfDay;
        chosenminutes = minute;
        sendingTime();
    }

    public void sendingTime(){
        timeListener.providingTimeFromTimePicker(choosenhour,chosenminutes);
    }

    @Override
    public void onDetach() {
        timeListener = null;
        super.onDetach();


    }
}