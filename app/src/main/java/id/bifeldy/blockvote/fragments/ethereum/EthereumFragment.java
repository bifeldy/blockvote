package id.bifeldy.blockvote.fragments.ethereum;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Objects;

import id.bifeldy.blockvote.R;
import id.bifeldy.blockvote.helpers.GethNodeHolder;

public class EthereumFragment extends Fragment {

    // MVCs
    private EthereumViewModel ethereumViewModel;

    // UI elements
    private TextView textView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ethereumViewModel = new ViewModelProvider(this).get(EthereumViewModel.class);
        return inflater.inflate(R.layout.fragment_ethereum, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize View
        textView = view.findViewById(R.id.text_ethereum);

        // -- Custom functions
        appRun();
    }

    public void appRun() {

        // Set TextView with data from ViewModel
        ethereumViewModel.getText().observe(getViewLifecycleOwner(), s -> textView.setText(s));

        try {
            GethNodeHolder gethNodeHolder = GethNodeHolder.getInstance(getActivity(), "Bifeldy");
            gethNodeHolder.getNode().start();
        } catch (Exception e) {
            Log.e("[ETH_ERROR]", Objects.requireNonNull(e.getMessage()));
            e.printStackTrace();
        }

    }

}

// geth --identity "node_kpu" --datadir "node_kpu" --syncmode "full" --networkid 9999 --cache 4096 --http --http.addr "0.0.0.0" --http.vhosts "*" --http.corsdomain "*" --http.api "admin,debug,web3,eth,txpool,personal,clique,miner,net" --ws --ws.addr "0.0.0.0" --ws.origins "*" --ws.api "admin,debug,web3,eth,txpool,personal,clique,miner,net" --allow-insecure-unlock --unlock "0xfd34e338336fdd5f25e9280c06b5f9df62aa689d" --password "node_kpu/pass.key" --mine --v5disc --ipcdisable --gasprice 0 --ethstats Bifeldy-Fx553vd:bifeldyz@ethstats.bifeldy.id --light.serve 100 --light.maxpeers 50