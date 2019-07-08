package com.softenger.sofq.Dashboard.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.cache.DiskBasedCache;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.softenger.sofq.Dashboard.adapter.RoleAccessAdapter;
import com.softenger.sofq.R;
import com.softenger.sofq.SofqApplication;
import com.softenger.sofq.api.URLS;
import com.softenger.sofq.api.response.PrivilegeResponse;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


public class RoleAccessFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView rvRoleAccessList;

    //private OnFragmentInteractionListener mListener;

    public RoleAccessFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RoleAccessFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RoleAccessFragment newInstance(String param1, String param2) {
        RoleAccessFragment fragment = new RoleAccessFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_role_access, container, false);
        rvRoleAccessList = view.findViewById(R.id.rvRoleAccessList);
        rvRoleAccessList.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadViewPrivilegesData();
    }

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    /*@Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }*/

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
    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
    private void loadViewPrivilegesData() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URLS.URL_GET_PRIVILEGES, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                if (response.length() > 0) {
                    Log.e("Response", "Success::" + response.toString());
                    PrivilegeResponse[] privilegeResponses = new Gson().fromJson(response.toString(), PrivilegeResponse[].class);
                    Log.e("Response", "onRespose::size::" + privilegeResponses.length);

                    List<PrivilegeResponse> dataList = new ArrayList<>();
                    for (PrivilegeResponse privilegeResponse : privilegeResponses) {
                        dataList.add(privilegeResponse);
                    }
                    //dataList.addAll(privilegeResponses);
                    rvRoleAccessList.setAdapter(new RoleAccessAdapter(RoleAccessFragment.this.getContext(), dataList));

                } else {
                    Log.e("Response", "Failure::" + response.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Response", "Failure::" + error.toString());
            }
        });

        Log.e("req", "Request starting::Url::" + jsonArrayRequest.getUrl());
        Cache cache = new DiskBasedCache(getActivity().getCacheDir(), 4096 * 4096);
        cache.clear();
        jsonArrayRequest.setShouldCache(false);
        Volley.newRequestQueue(SofqApplication.getInstance().getApplicationContext()).add(jsonArrayRequest);
    }
}
