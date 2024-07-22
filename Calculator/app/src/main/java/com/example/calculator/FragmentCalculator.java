package com.example.calculator;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class FragmentCalculator extends Fragment implements View.OnClickListener{

    Button btnAdd,btnSub,btnMulti,btnDiv;
    EditText textN1,textN2;
    TextView textView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

           View view=inflater.inflate(R.layout.fragment_calculator, container, false);

            btnAdd=view.findViewById(R.id.plus_btn);
            btnSub=view.findViewById(R.id.minus_btn);
            btnMulti=view.findViewById(R.id.multi_btn);
            btnDiv=view.findViewById(R.id.div_btn);
            textN1=view.findViewById(R.id.num1);
            textN2=view.findViewById(R.id.num2);
            textView=view.findViewById(R.id.ans_box);

            btnAdd.setOnClickListener(this);
            btnSub.setOnClickListener(this);
            btnMulti.setOnClickListener(this);
            btnDiv.setOnClickListener(this);

            return view;

        }
        public Float getIntEditText(EditText editText){
            if(editText.getText().toString().isEmpty()){
                Toast.makeText(requireActivity().getApplicationContext(),"Enter Number",Toast.LENGTH_SHORT).show();
                return (float) 0;
            }
            return Float.parseFloat(editText.getText().toString());
        }
        @Override
        public void onClick(View view) {

            Float num1=getIntEditText(textN1);
            Float num2=getIntEditText(textN2);
            String res="Invalid";
            if(textN1.getText().toString().isEmpty() || textN2.getText().toString().isEmpty()){
                res="Please enter numbers";
            }
            else if(view.getId()==R.id.plus_btn){
                res="Answer = "+Float.toString(num1+num2);
            } else if (view.getId()==R.id.minus_btn) {
                res="Answer = "+Float.toString(num1-num2);
            } else if (view.getId()==R.id.multi_btn) {
                res="Answer = "+Float.toString(num1*num2);
            }
            else{
                if(num2!=0){
                    res="Answer = "+Float.toString((float)num1/(float)num2);
                }
                else{
                    res="Undefined";
                }
            }

            textView.setText(res);


        }

    }
