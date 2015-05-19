package com.example.administrador.myapplication.controllers.material;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.util.AppUtil;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class ServiceOrderActivity extends AppCompatActivity {

    public static final String EXTRA_SERVICE_ORDER = "EXTRA_SERVICE_ORDER";
    public static final String EXTRA_START_BENCHMARK = "EXTRA_START_BENCHMARK";

    private static final String TAG = ServiceOrderActivity.class.getSimpleName();

    private EditText mEditTextClientName, mEditTextAddress, mEditTextDate, mEditTextTime, mEditTextValue, mEditTextDescription;
    private Switch mSwitchPaid;
    private ServiceOrder mServiceOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_order);

        this.bindElements();

        final Bundle extras = super.getIntent().getExtras();
        if (extras == null) {
            mServiceOrder = new ServiceOrder();
        } else {
            mServiceOrder = AppUtil.get(extras.getParcelable(EXTRA_SERVICE_ORDER));

            final long startTime = AppUtil.get(extras.getLong(EXTRA_START_BENCHMARK));
            final long endTime = SystemClock.elapsedRealtime();
            Log.d(TAG, String.format("Benchmark Extras (Serializable x Parcelable): %dms", endTime - startTime));

            mEditTextClientName.setText(mServiceOrder.getClient());
            mEditTextAddress.setText(mServiceOrder.getAddress());
            final DateFormat dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", AppUtil.LOCALE_PT_BR);
            final String[] dateTimeText = dateTimeFormat.format(mServiceOrder.getDate()).split("[ ]");
            mEditTextDate.setText(dateTimeText[0]);
            mEditTextTime.setText(dateTimeText[1]);
            final DecimalFormat decimalFormat = AppUtil.get(NumberFormat.getNumberInstance(Locale.US));
            decimalFormat.applyPattern("#.00");
            mEditTextValue.setText(decimalFormat.format(mServiceOrder.getValue()));
            mSwitchPaid.setChecked(mServiceOrder.isPaid());
            mEditTextDescription.setText(mServiceOrder.getDescription());
        }
    }

    private void bindElements() {
        mEditTextClientName = AppUtil.get(this.findViewById(R.id.editTextClientName));
        mEditTextAddress = AppUtil.get(this.findViewById(R.id.editTextAddress));
        mEditTextDate = AppUtil.get(this.findViewById(R.id.editTextDate));
        mEditTextTime = AppUtil.get(this.findViewById(R.id.editTextTime));
        mEditTextValue = AppUtil.get(this.findViewById(R.id.editTextValue));
        mSwitchPaid = AppUtil.get(this.findViewById(R.id.switchPaid));
        mEditTextDescription = AppUtil.get(this.findViewById(R.id.editTextDescription));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_service_order, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionSave:
                saveServiceOrder();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveServiceOrder() {
        final Calendar serviceOrderCalendar = Calendar.getInstance(AppUtil.LOCALE_PT_BR);

        boolean isValid = this.verifyMandatoryFields(mEditTextClientName, mEditTextDate, mEditTextTime, mEditTextValue);

        isValid = isValid & this.verifyServiceOrderDate(serviceOrderCalendar);

        isValid = isValid & this.verifyServiceOrderTime(serviceOrderCalendar);

        isValid = isValid & this.verifyServiceOrderValue();

        if (isValid) {
            mServiceOrder.setClient(mEditTextClientName.getText().toString().trim());
            mServiceOrder.setAddress(mEditTextAddress.getText().toString().trim());
            mServiceOrder.setDate(serviceOrderCalendar.getTime());
            mServiceOrder.setValue(Double.valueOf(mEditTextValue.getText().toString().trim()));
            mServiceOrder.setPaid(mSwitchPaid.isChecked());
            mServiceOrder.setDescription(mEditTextDescription.getText().toString().trim());
            mServiceOrder.save();
            super.finish();
        }
    }

    private boolean verifyMandatoryFields(EditText... fields) {
        boolean isValid = true;
        for (EditText field : fields) {
            field.setError(null);
            if (TextUtils.isEmpty(field.getText())) {
                field.setError(getString(R.string.msg_mandatory));
                if(isValid) {
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    private boolean verifyServiceOrderDate(Calendar serviceOrderCalendar) {
        final String dateText = mEditTextDate.getText().toString().trim();
        if (!TextUtils.isEmpty(dateText)) {
            try {
                final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", AppUtil.LOCALE_PT_BR);
                dateFormat.setLenient(false);
                serviceOrderCalendar.setTime(dateFormat.parse(dateText));
            } catch (ParseException parseException) {
                mEditTextDate.setError(super.getString(R.string.msg_invalid_date));
                return false;
            }
        }
        return true;
    }

    private boolean verifyServiceOrderTime(Calendar serviceOrderCalendar) {
        final String timeText = mEditTextTime.getText().toString().trim();
        if (!TextUtils.isEmpty(timeText)) {
            try {
                final DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", AppUtil.LOCALE_PT_BR);
                timeFormat.setLenient(false);
                timeFormat.parse(timeText);
                if (serviceOrderCalendar != null) {
                    final String[] timeTextArray = timeText.split("[:]");
                    serviceOrderCalendar.set(Calendar.HOUR, Integer.valueOf(timeTextArray[0]));
                    serviceOrderCalendar.set(Calendar.MINUTE, Integer.valueOf(timeTextArray[1]));
                    serviceOrderCalendar.set(Calendar.SECOND, Integer.valueOf(timeTextArray[2]));
                }
            } catch (ParseException parseException) {
                mEditTextTime.setError(this.getString(R.string.msg_invalid_time));
                return false;
            }
        }
        return true;
    }

    private boolean verifyServiceOrderValue() {
        final String valueText = mEditTextValue.getText().toString().trim();
        if (!TextUtils.isEmpty(valueText)) {
            final Pattern pattern = Pattern.compile("[0-9]+([.][0-9]{1,2})?");
            if (!pattern.matcher(valueText).matches()) {
                mEditTextValue.setError(super.getString(R.string.msg_invalid_value));
                return false;
            }
        }
        return true;
    }
}
