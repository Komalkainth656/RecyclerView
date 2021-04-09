package com.dgl114.assignment4;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class AddItemFragment extends DialogFragment implements View.OnClickListener {

    private Dialog addItemDialog;
    private View initView;
    private EditText etTitle;
    private EditText etDescription;
    private Button addButton;
    private AddItemInterface addItemInterface;

    //Add item fragment constructor to get the instance of the interface
    public AddItemFragment(AddItemInterface addItemInterface) {
        this.addItemInterface = addItemInterface;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        addItemDialog = super.onCreateDialog(savedInstanceState);
        return addItemDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        //to set the size of the dialog
        if (addItemDialog != null) {
            addItemDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initView =  inflater.inflate(R.layout.fragment_add_item, container, false);
        etTitle = initView.findViewById(R.id.etTitle);
        etDescription = initView.findViewById(R.id.etDescription);
        addButton = initView.findViewById(R.id.addButton);
        addButton.setOnClickListener(this);
        return initView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addButton:
            if(etTitle.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Enter color name", Toast.LENGTH_LONG).show();
                return;
            }
            if(etDescription.getText().toString().isEmpty()){
                Toast.makeText(getContext(), "Enter color description", Toast.LENGTH_LONG).show();
                return;
            }
            addItemInterface.addItem(etTitle.getText().toString(), etDescription.getText().toString());
            addItemDialog.dismiss();
            break;
            default: break;
        }

    }
}