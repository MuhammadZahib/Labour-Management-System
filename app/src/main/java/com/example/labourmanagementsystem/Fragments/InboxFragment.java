package com.example.labourmanagementsystem.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labourmanagementsystem.Adapters.InboxAdapter;
import com.example.labourmanagementsystem.Models.CarWashModel;
import com.example.labourmanagementsystem.Models.InboxModel;
import com.example.labourmanagementsystem.Models.PojoModel;
import com.example.labourmanagementsystem.R;
import com.example.labourmanagementsystem.SubAdpater.InteriorAdapter;
import com.example.labourmanagementsystem.SubClass.InteriorActivity;
import com.example.labourmanagementsystem.SubClass.RequestActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link InboxFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class InboxFragment extends Fragment implements InboxAdapter.GetItem {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView rv;
    ArrayList<InboxModel> list;
    String userID;
    TextView ndts;
    DatabaseReference database;
    ProgressDialog progressDialog;
    ArrayList<InboxModel> listInbox =  new ArrayList<>();
    InboxAdapter adapter;
    public InboxFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment InboxFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static InboxFragment newInstance(String param1, String param2) {
        InboxFragment fragment = new InboxFragment();
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
        View view= inflater.inflate(R.layout.fragment_inbox, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Loading Data");
        progressDialog.setCanceledOnTouchOutside(false);
        ndts=view.findViewById(R.id.ndts);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        userID=user.getUid();

        progressDialog.show();
        rv=view.findViewById(R.id.rv_inbox);
        database= FirebaseDatabase.getInstance().getReference("Request").child(userID);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){


                    InboxModel inboxModel= dataSnapshot.getValue(InboxModel.class);
                    listInbox.add(inboxModel);
                }
                progressDialog.dismiss();
                adapter= new InboxAdapter(getContext(),listInbox);

                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                rv.setAdapter(adapter);
                adapter.setClickListener(InboxFragment.this::itemPosition);
                adapter.notifyDataSetChanged();

                if(listInbox.size()>0){
                    ndts.setVisibility(View.GONE);
                }else{
                    ndts.setVisibility(View.VISIBLE);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void itemPosition(InboxModel pos) {

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.layout_notification_detail);
        TextView name= dialog.findViewById(R.id.noti_name);
        TextView description=dialog.findViewById(R.id.noti_desc);
        TextView phone=dialog.findViewById(R.id.noti_phone);
        TextView address=dialog.findViewById(R.id.noti_address);
        TextView dateandtime=dialog.findViewById(R.id.dandt);
        TextView price=dialog.findViewById(R.id.noti_price);
        TextView skill=dialog.findViewById(R.id.noti_skill);
        Button accept=dialog.findViewById(R.id.btn_accept_request);
        Button decline=dialog.findViewById(R.id.btn_decline_request);


        name.setText(pos.getCustomerName());
        description.setText(pos.getCustomerDescription());
        phone.setText(pos.getCustomerPhone());
        address.setText(pos.getCustomerAddress());
        dateandtime.setText(pos.getDate()+" , "+pos.getTime());
        price.setText(pos.getCustomerPrice());
        skill.setText(pos.getSkill());

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProgressDialog pd= new ProgressDialog(getContext());
                pd.setTitle("Please Wait");

                final HashMap<String,Object> mapp= new HashMap<>();
                mapp.put("CustomerId",pos.getCustomerId());
                mapp.put("Skill",pos.getSkill());
                mapp.put("CustomerName",pos.getCustomerName());
                mapp.put("CustomerAddress",pos.getCustomerAddress());
                mapp.put("CustomerPhone",pos.getCustomerPhone());
                mapp.put("CustomerPrice",pos.getCustomerPrice());
                mapp.put("Date",pos.getDate());
                mapp.put("Time",pos.getTime());
                mapp.put("CustomerDescription",pos.getCustomerDescription());
                mapp.put("Status","In Process..");

                pd.show();
                FirebaseDatabase.getInstance().getReference().child("Project").child(userID).child(pos.getCustomerId()).setValue(mapp)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getContext(),"Your New Project is Started",Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                                dialog.dismiss();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(),"Error: Please Try Again",Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                                dialog.dismiss();

                            }
                        });
            }
        });

        decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();
    }

    private boolean validate(EditText e) {
        // check the lenght of the enter data in EditText and give error if its empty
        if (e.getText().toString().trim().length() > 0) {
            return true; // returs true if field is not empty
        }
        e.setError("Please Fill This");
        e.requestFocus();
        return false;
    }

}