package com.android.myruns1;



import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.myruns1.*;

public class ProfileActivity extends Activity {

	private static final String TAG = "VTCA";
	public static final String PREFS_MYRUNS = "MyPrefs";	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTheme(android.R.style.Theme_Holo);
		setContentView(R.layout.activity_profile);
		
		//Auto complete major
		final String[] AutoMajors=getResources().getStringArray(R.array.majors_array);
		ArrayAdapter<String> AutoArrayAdapter = new ArrayAdapter<String>(getApplicationContext(),
				android.R.layout.simple_dropdown_item_1line,AutoMajors);
		
		
		// Create reference to UI ACTV and set the adapter
		AutoCompleteTextView Majors_AutoCTV= (AutoCompleteTextView) findViewById(com.android.myruns1.R.id.autoMajors);
		Majors_AutoCTV.setThreshold(2);
		Majors_AutoCTV.setAdapter(AutoArrayAdapter);
		
		// Load user data to screen using loadUserData()
		loadUserData();
	}

	public void onSaveClicked(View v) {
		// Save current user data using saveUserData()
		saveUserData();
		
		// Toast to display save message
		Toast.makeText(getApplicationContext(),
				getString(R.string.save_message), Toast.LENGTH_SHORT).show();
		
	}
	
	public void onCancelClicked(View v){
		// Toast to display end of application
		Toast.makeText(getApplicationContext(),
				getString(R.string.cancel_message), Toast.LENGTH_SHORT).show();
						
		// Close Activity
		finish();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.profile, menu);
		return true;
	}

	// ****************** private helper functions ***************************//

	// save the user data to the shared preferences 
	private void saveUserData() {

		Log.d(TAG, "saveUserData()");

		// Getting the shared preferences editor

		String mKey = getString(R.string.preference_name);
		SharedPreferences mPrefs = getSharedPreferences(mKey, MODE_PRIVATE);

		SharedPreferences.Editor mEditor = mPrefs.edit();
		mEditor.clear();

		// Save Name information
		mKey = getString(R.string.preference_key_profile_name);
		String mValue = (String) ((EditText) findViewById(R.id.editName))
				.getText().toString();
		mEditor.putString(mKey, mValue);		
				
		// Save email information
		mKey = getString(R.string.preference_key_profile_email);
		mValue = (String) ((EditText) findViewById(R.id.editEmail))
				.getText().toString();
		mEditor.putString(mKey, mValue);

		// Save phone number
		mKey = getString(R.string.preference_key_profile_phone);
		mValue = (String) ((EditText) findViewById(R.id.editPhone))
				.getText().toString();
		mEditor.putString(mKey, mValue);		
		
	
		// Save Radio button selection
		mKey = getString(R.string.preference_key_profile_gender);
		RadioGroup mRadioGroup = (RadioGroup) findViewById(R.id.radioGender);
		int mIntValue = mRadioGroup.indexOfChild(findViewById(mRadioGroup
				.getCheckedRadioButtonId()));
		mEditor.putInt(mKey, mIntValue);

			
		//Save class spinner information
		mKey = getString(R.string.preference_key_profile_class);
		mIntValue = (int) ((Spinner) findViewById(R.id.spinnerClass)).getSelectedItemPosition();
		mEditor.putInt(mKey, mIntValue);
		
		// Save major information		
		mKey = getString(R.string.preference_key_profile_major);
		mValue =(String) ((AutoCompleteTextView) findViewById(R.id.autoMajors))
				.getText().toString();
		mEditor.putString(mKey, mValue);
		
		
		// Commit all the changes into the shared preference
		mEditor.commit();


		// Close Activity/Application
		finish();
		
	}	
	
	// load the user data from shared preferences if there is no data make sure
	// that we set it to something reasonable
	private void loadUserData() {

		// We can also use log.d to print to the LogCat

		Log.d(TAG, "loadUserData()");

		// Load and update all profile views

		// Get the shared preferences - create or retrieve the activity
		// preference object

		String mKey = getString(R.string.preference_name);
		SharedPreferences mPrefs = getSharedPreferences(mKey, MODE_PRIVATE);

		// Load the user name
		mKey = getString(R.string.preference_key_profile_name);
		String mValue = mPrefs.getString(mKey, " ");
		((EditText) findViewById(R.id.editName)).setText(mValue);
		
		// Load the user email
		mKey = getString(R.string.preference_key_profile_email);
		mValue = mPrefs.getString(mKey, " ");
		((EditText) findViewById(R.id.editEmail)).setText(mValue);

		// Load the user phone
		mKey = getString(com.android.myruns1.R.string.preference_key_profile_phone);
		mValue = mPrefs.getString(mKey, " ");
		((EditText) findViewById(R.id.editPhone)).setText(mValue);

		
		// Load the user class
		mKey = getString(com.android.myruns1.R.string.preference_key_profile_class);
		int mIntValue = mPrefs.getInt(mKey, -1);
		((Spinner) findViewById(R.id.spinnerClass)).setSelection(mIntValue);
		
		//Load the user major		
		mKey = getString(com.android.myruns1.R.string.preference_key_profile_major);
		mValue = mPrefs.getString(mKey, " ");
		((AutoCompleteTextView) findViewById(R.id.autoMajors)).setText(mValue);
				
		
		// Please Load gender info and set radio box
		mKey = getString(com.android.myruns1.R.string.preference_key_profile_gender);

		mIntValue = mPrefs.getInt(mKey, -1);
		// In case there isn't one saved before:
		if (mIntValue >= 0) {
			// Find the radio button that should be checked.
			RadioButton radioBtn = (RadioButton) ((RadioGroup) findViewById(R.id.radioGender))
					.getChildAt(mIntValue);
			// Check the button.
			radioBtn.setChecked(true);
		}

	}


	
	
}

