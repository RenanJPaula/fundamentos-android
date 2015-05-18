package com.example.administrador.myapplication.controllers;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;

import com.example.administrador.myapplication.R;
import com.example.administrador.myapplication.models.entities.ServiceOrder;
import com.example.administrador.myapplication.util.CastUtil;

public class ServiceOrderActivity extends AppCompatActivity {

    private EditText mEditTextClientName, mEditTextAddress, mEditTextDate, mEditTextTime, mEditTextValue, mEditTextDescription;
    private Switch mSwitchPaid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_order);

        this.bindElements();
    }

    private void bindElements() {
        mEditTextClientName = CastUtil.get(this.findViewById(R.id.editTextClientName));
        mEditTextAddress = CastUtil.get(this.findViewById(R.id.editTextAddress));
        mEditTextDate = CastUtil.get(this.findViewById(R.id.editTextDate));
        //mEditTextTime = CastUtil.get(this.findViewById(R.id.editTextTime));
        mEditTextValue = CastUtil.get(this.findViewById(R.id.editTextValue));
        mSwitchPaid = CastUtil.get(this.findViewById(R.id.switchPaid));
        mEditTextDescription = CastUtil.get(this.findViewById(R.id.editTextDescription));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_service_order, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveServiceOrder();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveServiceOrder() {
        final boolean isValid = this.verifyMandatoryFields(mEditTextClientName, mEditTextDate, mEditTextTime, mEditTextValue);
        if (isValid) {
            ServiceOrder serviceOrder = new ServiceOrder();
        }
    }

    private boolean verifyMandatoryFields(EditText... fields) {
        boolean isValid = true;
        for (EditText field : fields) {
            field.setError(null);
            if (TextUtils.isEmpty(field.getText())) {
                field.setError(getString(R.string.lbl_mandatory));
                field.requestFocus();
                isValid = false;
            }
        }
        return isValid;
    }
}
