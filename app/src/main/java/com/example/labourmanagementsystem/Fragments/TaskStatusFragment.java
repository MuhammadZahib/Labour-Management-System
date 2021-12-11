package com.example.labourmanagementsystem.Fragments;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.solver.PriorityGoalRow;
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
import com.example.labourmanagementsystem.Adapters.TaskStatusAdapter;
import com.example.labourmanagementsystem.Models.InboxModel;
import com.example.labourmanagementsystem.Models.TaskStatusModel;
import com.example.labourmanagementsystem.R;
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
 * Use the {@link TaskStatusFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskStatusFragment extends Fragment implements TaskStatusAdapter.GetItem,TaskStatusAdapter.Bill {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ProgressDialog progressDialog;
    TextView ndts;
    String userID;
    int pricep;
    RecyclerView rv;
    DatabaseReference database;
    ArrayList<TaskStatusModel> listTaskStatus =  new ArrayList<>();
    TaskStatusAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TaskStatusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskStatusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskStatusFragment newInstance(String param1, String param2) {
        TaskStatusFragment fragment = new TaskStatusFragment();
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
        View view= inflater.inflate(R.layout.fragment_task_status, container, false);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Loading Data");
        progressDialog.setCanceledOnTouchOutside(false);
        ndts=view.findViewById(R.id.ndts);

        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        userID=user.getUid();

        progressDialog.show();
        rv=view.findViewById(R.id.rv_status);
        database= FirebaseDatabase.getInstance().getReference("Project").child(userID);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){


                    TaskStatusModel taskStatusModel= dataSnapshot.getValue(TaskStatusModel.class);
                    listTaskStatus.add(taskStatusModel);
                }
                progressDialog.dismiss();
                adapter= new TaskStatusAdapter(getContext(),listTaskStatus);

                rv.setLayoutManager(new LinearLayoutManager(getContext()));
                rv.setAdapter(adapter);
                adapter.setClickListener(TaskStatusFragment.this::itemPosition);
                adapter.setClick(TaskStatusFragment.this::item);
                adapter.notifyDataSetChanged();

                if(listTaskStatus.size()>0){
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


        return  view;
    }

    @Override
    public void itemPosition(TaskStatusModel pos) {

        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.layout_edit_price);
        EditText new_price=dialog.findViewById(R.id.new_price);
        Button donet=dialog.findViewById(R.id.btn_price_done);

        donet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final HashMap<String,Object> mapp= new HashMap<>();
                mapp.put("CustomerId",pos.getCustomerId());
                mapp.put("Skill",pos.getSkill());
                mapp.put("CustomerName",pos.getCustomerName());
                mapp.put("CustomerAddress",pos.getCustomerAddress());
                mapp.put("CustomerPhone",pos.getCustomerPhone());
                mapp.put("CustomerPrice",new_price.getText().toString().trim());
                mapp.put("Date",pos.getDate());
                mapp.put("Time",pos.getTime());
                mapp.put("CustomerDescription",pos.getCustomerDescription());
                mapp.put("Status",pos.getStatus());


                pricep=Integer.valueOf(new_price.getText().toString());
                ProgressDialog pd= new ProgressDialog(getContext());
                pd.setTitle("Please Wait");
                pd.show();
                FirebaseDatabase.getInstance().getReference().child("Project").child(userID).child(pos.getCustomerId()).setValue(mapp)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                adapter.notifyDataSetChanged();
                                Toast.makeText(getContext(),"Price is updated",Toast.LENGTH_SHORT).show();
                                pd.dismiss();
                                dialog.dismiss();
                                adapter.notifyDataSetChanged();
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


        dialog.show();
    }

    @Override
    public void item(TaskStatusModel pos) {
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.layout_bill);
        TextView price= dialog.findViewById(R.id.price_bill);
        TextView totalPrice= dialog.findViewById(R.id.total_price);
        Button close=dialog.findViewById(R.id.btn_close_bill);

        price.setText(pos.getCustomerPrice());
        int p=Integer.parseInt(pos.getCustomerPrice())+200;
        totalPrice.setText(String.valueOf(p));


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}