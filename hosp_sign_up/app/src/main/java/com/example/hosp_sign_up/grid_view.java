package com.example.hosp_sign_up;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link grid_view#newInstance} factory method to
 * create an instance of this fragment.
 */
public class grid_view extends Fragment {
    ArrayList<bookings> arrayList = new ArrayList<bookings>();
    TableLayout gridLayout;
    TableRow r1;
    bookings b1;
    int count;
    DatabaseReference wdatabase1,ww;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public grid_view() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment grid_view.
     */
    // TODO: Rename and change types and number of parameters
    public static grid_view newInstance(String param1, String param2) {
        grid_view fragment = new grid_view();
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

        wdatabase1= FirebaseDatabase.getInstance("https://hosp-db-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference();
        wdatabase1.child("users").child("bookings").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                bookings value =dataSnapshot.getValue(bookings.class);
                arrayList.add(new bookings(Double.valueOf(value.geteta()),value.getemr()));
                gridadapter aAdapter = new gridadapter(gridLayout.getContext(), arrayList);
//                gridLayout.addView(aAdapter.getItem(1));
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                bookings value =snapshot.getValue(bookings.class);
                for (int i = 0; i < arrayList.size(); i++) {
                    if (arrayList.get(i).getemr().equals(value.getemr())) {
                        arrayList.remove(i);
                    }
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid_view, container, false);
    }
}