package br.edu.ifrn.cda.ui;

import android.app.Dialog;
import android.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;


public class DatePickerFragment extends DialogFragment implements OnDateSetListener {

	private FragmentComunicator fc;
	
	@Override
	public void onAttach(Activity activity) {
		
		super.onAttach(activity);
		fc = (FragmentComunicator) activity;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		final Calendar c = Calendar.getInstance();
		
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}
	
	
	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
		
		fc.onPassData(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
		
	}

}
