package com.example.michael.gnssagent.ui.main;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.michael.gnssagent.R;
import com.example.michael.gnssagent.data_processing.VisibleUsableSatelites;

public class SatStatusFragment extends Fragment {

    private TextView gpsUsable, samp, samp2;
    private TextView gpsVisible;
    private TextView galileoUsable;
    private TextView galileoVisible;
    private TextView glonassUsable;
    private TextView glonassVisible;
    private TextView sbasUsable;
    private TextView sbasVisible;
    private TextView beidouUsable;
    private TextView beidouVisible;
    private TextView qzssUsable;
    private TextView qzssVisible;
    private TextView gpsFq;
    private TextView galileoFq;
    private float gps = 0, gal = 0, glo = 0, sba = 0, bei = 0, qzss = 0;
    private View myView;
    private String fin = "";
    private Switch aSwitch;
    private int flag=0;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_sat_status, container, false);

//        setUpWidgets();

        return myView;
    }

    @Override
    public void onResume() {
        setUpWidgets();
        super.onResume();
    }

    public void setUpWidgets() {

        gpsUsable = myView.findViewById(R.id.gpsUsable);
        gpsVisible = myView.findViewById(R.id.gpsVisible);

        galileoUsable = myView.findViewById(R.id.galileoUsable);
        galileoVisible = myView.findViewById(R.id.galileoVisible);

        glonassUsable = myView.findViewById(R.id.glonassUsable);
        glonassVisible = myView.findViewById(R.id.glonassVisible);

        sbasUsable = myView.findViewById(R.id.sbasUsable);
        sbasVisible = myView.findViewById(R.id.sbasVisible);

        beidouUsable = myView.findViewById(R.id.beidouUsable);
        beidouVisible = myView.findViewById(R.id.beidouVisible);

        qzssUsable = myView.findViewById(R.id.qzssUsable);
        qzssVisible = myView.findViewById(R.id.qzssVisible);

        gpsFq = myView.findViewById(R.id.gpsFq);
        galileoFq = myView.findViewById(R.id.galileoFq);
        samp = myView.findViewById(R.id.textView2);
        samp2=myView.findViewById(R.id.textView3);
        aSwitch=myView.findViewById(R.id.switch1);

        setDefaultSateliteStatus();
    }

    public void setDefaultSateliteStatus() {

        try {
            //gps
            gpsUsable.setText("0");
            gpsVisible.setText("0");

            // Galileo
            galileoUsable.setText("0");
            galileoVisible.setText("0");

            // Glonnas
            glonassUsable.setText("0");
            glonassVisible.setText("0");

            // Beidou
            beidouUsable.setText("0");
            beidouVisible.setText("0");

            // SBAS
            sbasUsable.setText("0");
            sbasVisible.setText("0");

            // QZSS
            qzssUsable.setText("0");
            qzssVisible.setText("0");

            // frequencies
            gpsFq.setText("0/0");
            galileoFq.setText("0/0");
        } catch (java.lang.NullPointerException e) {
        }
    }

    public void setSateliteStatus(final VisibleUsableSatelites sats) {

        try {
            //gps
            if (sats.gpsUsable.toString() != null) gpsUsable.setText(sats.gpsUsable.toString());
            if (sats.gpsVisible.toString() != null) gpsVisible.setText(sats.gpsVisible.toString());

            // Galileo
            if (sats.galileoUsable.toString() != null)
                galileoUsable.setText(sats.galileoUsable.toString());
            if (sats.galileoVisible.toString() != null)
                galileoVisible.setText(sats.galileoVisible.toString());

            // Glonnas
            if (sats.glonassUsable.toString() != null)
                glonassUsable.setText(sats.glonassUsable.toString());
            if (sats.glonassVisible.toString() != null)
                glonassVisible.setText(sats.glonassVisible.toString());

            // Beidou
            if (sats.beidouUsable.toString() != null)
                beidouUsable.setText(sats.beidouUsable.toString());
            if (sats.beidouVisible.toString() != null)
                beidouVisible.setText(sats.beidouVisible.toString());

            // SBAS
            if (sats.sbasUsable.toString() != null) sbasUsable.setText(sats.sbasUsable.toString());
            if (sats.sbasVisible.toString() != null)
                sbasVisible.setText(sats.sbasVisible.toString());

            // QZSS
            if (sats.qzssUsable.toString() != null) qzssVisible.setText(sats.qzssUsable.toString());
            if (sats.qzssVisible.toString() != null)
                qzssUsable.setText(sats.qzssVisible.toString());

            // frequencies
            if (sats.gpsL1.toString() != null && sats.gpsL5.toString() != null) {
                gpsFq.setText(sats.gpsL1.toString() + "/" + sats.gpsL5.toString());
            }

            if (sats.galileoE1.toString() != null && sats.galileoE5.toString() != null) {
                galileoFq.setText(sats.galileoE1.toString() + "/" + sats.galileoE5.toString());
            }


            //  samp.setText(sats.gpsVisible.toString());

        } catch (NullPointerException e) {

        }
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    gpsUsable.setText("0");
                    glonassUsable.setText("0");
                    galileoUsable.setText("0");
                    beidouUsable.setText("0");
                    qzssUsable.setText("0");
                    sbasUsable.setText("0");
                    samp2.setText("Satellites used:-");

                    for (Pair<String, Double> s : sats.satStrengthList) {   //samp.setText(s.first);
                        // samp2.setText(s.second.toString());
                        if (s.first.charAt(0) == 'G')
                            gps += s.second.floatValue();
                        if (s.first.charAt(0) == 'R')
                            glo += s.second.floatValue();
                        if (s.first.charAt(0) == 'E')
                            gal += s.second.floatValue();
                        if (s.first.charAt(0) == 'B')
                            bei += s.second.floatValue();
                        if (s.first.charAt(0) == 'S')
                            sba += s.second.floatValue();
                        if (s.first.charAt(0) == 'Q')
                            qzss += s.second.floatValue();
                    }
                    if (gps > glo && gps > gal && gps > bei && gps > qzss) {

                        if (sats.gpsUsable >= 4) {
                            for (Pair<String, Double> s : sats.satStrengthList) {
                                if (s.first.charAt(0) == 'G') {
                                    fin = fin + "  " + s.first;
                                }
                            }
                            samp.setText(fin);
                            fin = "        ";
                        }
                    } else if (glo > gal && glo > bei && glo > qzss) {

                        if (sats.glonassUsable >= 4) {
                            for (Pair<String, Double> s : sats.satStrengthList) {
                                if (s.first.charAt(0) == 'R') {
                                    fin = fin + "  " + s.first;
                                }
                            }
                            samp.setText(fin);
                            fin = "         ";
                        }
                    } else if (gal > bei && gal > qzss) {

                        if (sats.galileoUsable >= 4) {
                            for (Pair<String, Double> s : sats.satStrengthList) {
                                if (s.first.charAt(0) == 'E') {
                                    fin = fin + "  " + s.first;
                                }
                            }
                            samp.setText(fin);
                            fin = "         ";
                        }
                    } else if (bei > qzss) {

                        if (sats.beidouUsable >= 4) {
                            for (Pair<String, Double> s : sats.satStrengthList) {
                                if (s.first.charAt(0) == 'B') {
                                    fin = fin + "  " + s.first;
                                }
                            }
                            samp.setText(fin);
                            fin = "       ";
                        }
                    } else {

                        if (sats.sbasUsable >= 4) {
                            for (Pair<String, Double> s : sats.satStrengthList) {
                                if (s.first.charAt(0) == 'Q') {
                                    fin = fin + "  " + s.first;
                                }
                            }
                            samp.setText(fin);
                            fin = "     ";
                        }
                    }
                    flag=1;

                }
                else{
                    samp.setText("");
                    samp2.setText("");
                    flag=0;
                }
            }
        });
if(flag==1){
    gpsUsable.setText("0");
    glonassUsable.setText("0");
    galileoUsable.setText("0");
    beidouUsable.setText("0");
    qzssUsable.setText("0");
    sbasUsable.setText("0");
}


    }
}
