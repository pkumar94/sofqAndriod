package com.softenger.sofq.user.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.softenger.sofq.R;


public class LoginFragment extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private EditText etUserId;
    private EditText etPassword;
    //private tvForgotPassword;
    //private btnLogin;
    private View mView;

    public LoginFragment() {
        // Required empty public constructor
    }

    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mListener = null;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_login, container, false);
        initIds();
        return mView;
    }

    private void initIds() {
        etUserId = mView.findViewById(R.id.etUserId);
        etPassword = mView.findViewById(R.id.etPassword);
        Button btnLogin = mView.findViewById(R.id.btnAddUpdateUser);
        TextView tvForgotPassword = mView.findViewById(R.id.tvForgotPassword);
        tvForgotPassword.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvForgotPassword:
                forgotPasswordClicked();
                break;
            case R.id.btnAddUpdateUser:
                loginClicked();
                break;
        }
    }

    private void forgotPasswordClicked() {
        mListener.onForgotPasswordClicked();
    }

    private void loginClicked() {
        mListener.onLoginClicked(etUserId.getText().toString(), etPassword.getText().toString());
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onLoginClicked(String userId, String password);

        void onForgotPasswordClicked();
    }
}
